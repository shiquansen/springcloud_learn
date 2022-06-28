package com.example.rocketmq.config;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RocketConfig {

    @Bean
    public DefaultMQProducer mqProducer() throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer("sqs-produce");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        return producer;
    }

    @Bean
    public DefaultMQPushConsumer mqConsumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("sqs-produce");
        consumer.setNamesrvAddr("localhost:9876");
        return consumer;
    }

}
