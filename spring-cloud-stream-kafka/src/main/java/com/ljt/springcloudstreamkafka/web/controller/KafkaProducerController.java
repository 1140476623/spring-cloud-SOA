package com.ljt.springcloudstreamkafka.web.controller;

import com.ljt.springcloudstreamkafka.stream.MessageProducerBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *  Kafka 生产者 Controller
 */
@RestController
public class KafkaProducerController {

    private final KafkaTemplate<String,String> kafkaTemplate;

    private final MessageProducerBean messageProducerBean;

    private final String topic;

    @Autowired
    public KafkaProducerController(KafkaTemplate kafkaTemplate, MessageProducerBean messageProducerBean, @Value("${kafka.topic}") String topic) {
        this.kafkaTemplate = kafkaTemplate;
        this.messageProducerBean = messageProducerBean;
        this.topic = topic;
    }

    /**
     * {@link KafkaTemplate} 发送
     * @param message
     * @return
     */
    @PostMapping("/message/template/send")
    public Boolean sendTemplateMessage(@RequestParam String message){
        kafkaTemplate.send(topic,message);
        return true;// 这里就直接返回true了
    }

    /**
     * 通过{@link MessageProducerBean}消息生产者发送
     * @param message
     * @return
     */
    @PostMapping("/message/stream/send")
    public Boolean sendStreamMessage(@RequestParam String message){
        //messageProducerBean.send(message);
        // 发送到自定义通道
        messageProducerBean.sendToLjt(message);

        return true;// 这里就直接返回true了
    }
}
