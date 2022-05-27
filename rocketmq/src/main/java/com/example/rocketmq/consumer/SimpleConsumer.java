package com.example.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SimpleConsumer implements ApplicationRunner {

    @Autowired
    DefaultMQPushConsumer mqConsumer;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        mqConsumer.subscribe("defaultTopic", "*");
        mqConsumer.registerMessageListener((MessageListenerConcurrently) (msgList, context) -> {
            for (MessageExt msg : msgList) {
                String s = new String(msg.getBody());
                //对象json序列化
                log.info("consumer message success: {}", s);
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });

        mqConsumer.start();
        log.info("Consumer Started");
    };
}

