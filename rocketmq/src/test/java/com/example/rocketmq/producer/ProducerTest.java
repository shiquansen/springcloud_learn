package com.example.rocketmq.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.UnsupportedEncodingException;

@SpringBootTest
public class ProducerTest {

    @Autowired
    private SimpleProducer simpleProducer;

    @Test
    public void contextLoads() throws MQBrokerException, RemotingException, UnsupportedEncodingException, InterruptedException, MQClientException {
        simpleProducer.send("topic1", "tags1", "hello world", 1000);
    }

}
