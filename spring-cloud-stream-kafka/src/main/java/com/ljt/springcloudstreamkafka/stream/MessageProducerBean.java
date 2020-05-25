package com.ljt.springcloudstreamkafka.stream;

import com.ljt.springcloudstreamkafka.stream.messaging.LjtMessageSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

/**
 * 消息生产者 Bean
 */
@Component
@EnableBinding({Source.class, LjtMessageSource.class})
public class MessageProducerBean {

    @Autowired
    @Qualifier(Source.OUTPUT) // Bean 名称
    private MessageChannel messageChannel;

    @Autowired
    private Source source;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    @Qualifier(LjtMessageSource.NAME) // Bean 名称
    private MessageChannel ljtMessageChannel;


    /**
     * 发送消息
     * @param message 消息内容
     */
    public void send(String message){
        // 通过消息管道发送消息
       // messageChannel.send(MessageBuilder.withPayload(message).build());
        source.output().send(MessageBuilder.withPayload(message).build());
    }

    /**
     * 发送消息到Ljt
     * @param message 消息内容
     */
    public void sendToLjt(String message){
        // 通过消息管道发送消息
        ljtMessageChannel.send(MessageBuilder.withPayload(message).build());
    }
}
