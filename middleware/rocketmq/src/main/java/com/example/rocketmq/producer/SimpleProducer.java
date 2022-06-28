package com.example.rocketmq.producer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;


/**
 * 发送消息的模板
 * https://github.com/apache/rocketmq/blob/master/docs/cn/RocketMQ_Example.md
 * 支持同步异步
 * 支持顺序，延时，批量，过滤，事务消息。
 */
@Service
@Slf4j
public class SimpleProducer {

    @Autowired
    DefaultMQProducer mqProducer;

    public SendResult send(String topic, String tags, String message, Integer delayTimeLevel) throws UnsupportedEncodingException, MQBrokerException, RemotingException, InterruptedException, MQClientException {
        Message msg = new Message(topic, tags, message.getBytes("UTF-8"));
        if (delayTimeLevel != null) {
            msg.setDelayTimeLevel(delayTimeLevel);
        }
        log.info("发送MQ: {},{},{}", topic, tags, message);
        SendResult sendResult = mqProducer.send(msg);
        log.info("MQ发送完成，返回结果:" + sendResult);
        return sendResult;
    }

}
