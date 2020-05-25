package com.ljt.springcloudstreamkafka.raw.api;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Kafka Producer Demo (使用原始API)
 */
public class KafkaProducerDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 初始化配置
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","localhost:9092");
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        // 创建 Kafka Producer 消息 = ProducerRecord

        KafkaProducer<String,String> kafkaProducer = new KafkaProducer(properties);
        // 发送 Kafka 消息
        String topic = "ljt";
        Integer partition = 0;
        Long timestamp = System.currentTimeMillis();
        String key = "message-key";
        String value = "zhangsan";
        ProducerRecord<String,String> record =
                new ProducerRecord<>(topic,partition,timestamp,key,value);

        // 发送 Kafka 消息
        Future<RecordMetadata> metadataFuture = kafkaProducer.send(record);

        // 强制执行
        metadataFuture.get();
    }
}
