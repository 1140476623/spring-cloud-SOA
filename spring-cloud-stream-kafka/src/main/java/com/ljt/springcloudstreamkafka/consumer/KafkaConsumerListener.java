package com.ljt.springcloudstreamkafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * Kafka 消费者监听器
 */
@Component
public class KafkaConsumerListener {
    /**
     * spring kafka接收
     * @param message
     */
    @KafkaListener(topics = "${kafka.topic}")
    public void onMessage(String message){
        System.out.println("Kafka 消费者监听器，接收到消息：" + message);
    }

    /**
     *  spring kafka 接收stream方式自定义通道消息
     * @param message
     */
    @KafkaListener(topics = "ljtMessage")
    public void onLjtMessage(String message){
        System.out.println("Kafka 消费者监听器，接收到消息：" + message);
    }
}

