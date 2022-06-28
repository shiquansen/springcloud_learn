package com.example.elasticsearch.operational.kibana.demo;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class Index {

    @Autowired
    private RestHighLevelClient client;

    public void createIndex(String indexName) {
        CreateIndexRequest request = new CreateIndexRequest(indexName);
        try {
            client.indices().create(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean indexExists(String indexName){
        GetIndexRequest request = new GetIndexRequest();
        request.indices(indexName);
        try {
           return client.indices().exists(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void deleteIndex(String indexName) {
        DeleteIndexRequest request = new DeleteIndexRequest(indexName);
        try {
            DeleteIndexResponse response = client.indices().delete(request, RequestOptions.DEFAULT);
            System.out.println(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
