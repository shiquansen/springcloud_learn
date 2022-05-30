package com.example.elasticsearch.operational.kibana.demo;

import com.alibaba.fastjson.JSON;
import com.example.elasticsearch.operational.kibana.BaseBean;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Document {

    @Autowired
    private RestHighLevelClient client;


    public void insert(String index, BaseBean baseBean){
        IndexRequest request = new IndexRequest(index);
        request.id(baseBean.getId());
        request.source(JSON.toJSONString(baseBean), XContentType.JSON);
        try {
            client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> complexQuery(PageBean page, String index, BaseBean params) {
        int start = page.getPageNo() == null ? 0 : page.getPageNo() - 1;
        int limit = page.getPageSize() == null ? 0 : page.getPageSize();

        //1.创建请求
        SearchRequest request = new SearchRequest();
        //这里是7.4.2不需要指定type了，8以后就没有type了
        request.indices(index);
        //2、创建请求参数
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        //分页并排序(第一页是从0开始的，所以上面的start-1)
        int from = start * limit;
        ssb.from(from )
                .size(limit)
                .sort("balance", SortOrder.DESC) //排序
                .trackTotalHits(true);//查全部数据(如果不写或者写false当总记录数超过10000时会返回总数10000,配置为true就会返回真实条数)
        //指定返回字段
        ssb.fetchSource(
                new String[]{"account_number", "balance", "firstname", "lastname", "age", "gender", "address", "employer", "email", "city", "state"},
                new String[]{}
        );

        BoolQueryBuilder boolQueryBuilder = new BoolQueryBuilder();
        /*
         * //QueryBuilders.matchQuery()表示模糊查询----用来做keyWord的搜索
         * //QueryBuilders.termQuery()表示精确查询
         /**
         * 精确匹配:要采用 字段.keyword 才匹配得到，直接匹配那么匹配不到。原因:
         * elasticsearch 里默认的IK分词器是会将每一个中文都进行了分词的切割，所以你直接想查一整个词，或者一整句话是无返回结果的
         *
         */
        //精确匹配
        if (!StringUtils.isEmpty(params.getDest())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("Dest.keyword", params.getDest()));
        }
        /*
         *精确匹配(可能多字段,例如userId传一个或多个，用逗号隔开：1,2,3,4,5,6)
         **/
        if (!StringUtils.isEmpty(params.getCarrier())) {
            String[] cars = params.getCarrier().split(",");
            String userIdsStr = "";
            if (cars.length >= 2) {
                for (String oneUserId : cars) {
                    userIdsStr += !StringUtils.isEmpty(userIdsStr) ? (" " + oneUserId) : oneUserId;
                }
                /*
                 *例如：address匹配 "唐人" 或者 "2号"
                 boolQueryBuilder.must(QueryBuilders.matchQuery("address", "唐人 2号").operator(Operator.OR));
                 如果用Operator.AND就是代表"唐人" 并且 "2号"
                 注意:这里没有.keyword
                 **/
                boolQueryBuilder.filter(QueryBuilders.matchQuery("Carrier", userIdsStr).operator(Operator.OR));
            } else {
                userIdsStr = cars[0];
                boolQueryBuilder.filter(QueryBuilders.termsQuery("userId.keyword", userIdsStr));
            }

        }
        //模糊匹配
        if (!StringUtils.isEmpty(params.getDestAirportID())) {
            boolQueryBuilder.filter(QueryBuilders.matchQuery("DestAirportID", params.getDestAirportID()));
        }
        //多字段模糊匹配要用matchPhraseQuery，否则它会分词，例如：搜索“学校”，会被分为 "学","校"，会查出包含这两个之一的数据，但是我们只想要包含"学校"整体的数据。所以必须用短语查询matchPhraseQuery
        if (!StringUtils.isEmpty(params.getDestCountry())) {
            boolQueryBuilder.
                    should(QueryBuilders.matchPhraseQuery("lastname", params.getDestCountry())).
                    should(QueryBuilders.matchPhraseQuery("email", params.getDestCountry())).
                    should(QueryBuilders.matchPhraseQuery("employer", params.getDestCountry()));
        }

        if (params.getDayOfWeek() != null && params.getAvgTicketPrice() != null) {
            //范围查找(只针对数值，不能针对字符串)
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("balance").gte(params.getDayOfWeek()).lte(params.getAvgTicketPrice()));
        }

        ssb.query(boolQueryBuilder);
        request.source(ssb);

        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        SearchResponse response;
        RestStatus status;
        try {
            response = client.search(request, RequestOptions.DEFAULT);
            status = response.status();
            map.put("status", status);
            long totalHits = response.getHits().getTotalHits();
            Integer totalPage = (int) Math.ceil((double) totalHits / limit);
            map.put("currPage", start);
            map.put("pageSize ", limit);
            map.put("totalPage", totalPage);
            map.put("totalCount ", totalHits);
            SearchHit[] searchHits = response.getHits().getHits();
            for (SearchHit hit : searchHits) {
                Map<String, Object> sourceAsMap = hit.getSourceAsMap();
                list.add(sourceAsMap);
            }
            map.put("list", list);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("query exception");
    }

    public void update(String index, BaseBean baseBean){

    }


}
