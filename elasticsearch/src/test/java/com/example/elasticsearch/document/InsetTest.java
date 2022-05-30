package com.example.elasticsearch.document;

import cn.hutool.core.util.IdUtil;
import com.example.elasticsearch.operational.business.DocumentService;
import com.example.elasticsearch.operational.business.bean.ComplexBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class InsetTest {

    @Autowired
    DocumentService documentService;

    @Test
    void contextLoads() {

        for(int i = 0; i < 300; i++){
            List<String> houses = new ArrayList<>();
            houses.add("a"+i);
            houses.add("b"+i);
            houses.add("c"+i);

            ComplexBean bean = ComplexBean.builder()
                    .id(IdUtil.getSnowflakeNextIdStr())
                    .like(IdUtil.randomUUID())
                    .addressSplit(IdUtil.fastSimpleUUID()+","+IdUtil.fastSimpleUUID()+","+IdUtil.fastSimpleUUID()+","+IdUtil.fastSimpleUUID())
                    .date(new Date())
                    .name(IdUtil.randomUUID())
                    .houses(houses)
                    .build();

            documentService.insert("document", bean);
        }

    }

}
