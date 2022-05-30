package com.example.elasticsearch.operational.business.bean;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class ComplexBeanQuery {
    private String id;
    private String name;
    private String addressSplit;
    private String like;
    private List<String> houses;


    private Date dateStart;
    private Date dateEnd;

    private int pageNo = 1;
    private int pageSize = 10;
    private String index = "document";
}
