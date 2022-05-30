package com.example.elasticsearch.operational.business.bean;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Builder
public class ComplexBean {
    private String id;
    private String name;
    private String addressSplit;
    private Date date;
    private String like;
    private List<String> houses;
}
