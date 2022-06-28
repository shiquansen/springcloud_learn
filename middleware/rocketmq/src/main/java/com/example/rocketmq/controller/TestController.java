package com.example.rocketmq.controller;

import com.example.rocketmq.producer.SimpleProducer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class TestController {

    @Autowired
    SimpleProducer simpleProducer;

    @RequestMapping("/send")
    public void send(String msg){
        try {
            simpleProducer.send("defaultTopic", "defaultTags", msg, null);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MQBrokerException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
