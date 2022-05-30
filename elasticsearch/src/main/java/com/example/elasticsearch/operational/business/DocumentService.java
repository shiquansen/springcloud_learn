package com.example.elasticsearch.operational.business;

import com.alibaba.fastjson.JSON;
import com.example.elasticsearch.operational.business.bean.ComplexBean;
import com.example.elasticsearch.operational.business.bean.ComplexBeanQuery;
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
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DocumentService {

    @Autowired
    private RestHighLevelClient client;

    public void insert(String index, ComplexBean baseBean){
        IndexRequest request = new IndexRequest(index);
        request.type("base");   //6.+版本还有
        request.id(baseBean.getId());
        request.source(JSON.toJSONString(baseBean), XContentType.JSON);
        try {
            client.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, Object> select(ComplexBeanQuery baseBean){
        int start = baseBean.getPageNo() - 1;
        int limit = baseBean.getPageSize();

        //1.创建请求
        SearchRequest request = new SearchRequest();
        //这里是7.4.2不需要指定type了，8以后就没有type了
        request.indices(baseBean.getIndex());
        //2、创建请求参数
        SearchSourceBuilder ssb = new SearchSourceBuilder();
        //分页并排序(第一页是从0开始的，所以上面的start-1)
        int from = start * limit;
        ssb.from(from )
                .size(limit)
                .sort("date", SortOrder.DESC) //排序
                .trackTotalHits(true);//查全部数据(如果不写或者写false当总记录数超过10000时会返回总数10000,配置为true就会返回真实条数)
        //指定返回字段
//        ssb.fetchSource(
//                new String[]{"account_number", "balance", "firstname", "lastname", "age", "gender", "address", "employer", "email", "city", "state"},
//                new String[]{}
//        );

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
        if (!StringUtils.isEmpty(baseBean.getId())) {
            boolQueryBuilder.filter(QueryBuilders.termQuery("Dest.keyword", baseBean.getId()));
        }
        /*
         *精确匹配(可能多字段,例如userId传一个或多个，用逗号隔开：1,2,3,4,5,6)
         **/
        if (!StringUtils.isEmpty(baseBean.getAddressSplit())) {
            String[] addressArray = baseBean.getAddressSplit().split(",");
            StringBuilder addressStr = new StringBuilder();
            if (addressArray.length >= 2) {
                for (String address : addressArray) {
                    addressStr.append(!StringUtils.isEmpty(address) ? (" " + address) : "");
                }
                /*
                 *例如：address匹配 "唐人" 或者 "2号"
                 boolQueryBuilder.must(QueryBuilders.matchQuery("address", "唐人 2号").operator(Operator.OR));
                 如果用Operator.AND就是代表"唐人" 并且 "2号"
                 注意:这里没有.keyword
                 **/
                boolQueryBuilder.filter(QueryBuilders.matchQuery("addressSplit", addressStr.toString()).operator(Operator.OR));
            } else {
                addressStr = new StringBuilder(addressArray[0]);
                boolQueryBuilder.filter(QueryBuilders.termsQuery("addressStr.keyword", addressStr.toString()));
            }

        }
        //模糊匹配
        if (!StringUtils.isEmpty(baseBean.getLike())) {
            boolQueryBuilder.filter(QueryBuilders.matchQuery("like", baseBean.getLike()));
        }
        //多字段模糊匹配要用matchPhraseQuery，否则它会分词，例如：搜索“学校”，会被分为 "学","校"，会查出包含这两个之一的数据，但是我们只想要包含"学校"整体的数据。所以必须用短语查询matchPhraseQuery
//        if (!StringUtils.isEmpty(baseBean.getHouses())) {
//            boolQueryBuilder.
//                    should(QueryBuilders.matchPhraseQuery("lastname", params.getDestCountry())).
//                    should(QueryBuilders.matchPhraseQuery("email", params.getDestCountry())).
//                    should(QueryBuilders.matchPhraseQuery("employer", params.getDestCountry()));
//        }

        if (baseBean.getDateStart() != null && baseBean.getDateEnd() != null) {
            //范围查找(只针对数值，不能针对字符串)
            boolQueryBuilder.filter(QueryBuilders.rangeQuery("date").gte(baseBean.getDateStart()).lte(baseBean.getDateEnd()));
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

}
