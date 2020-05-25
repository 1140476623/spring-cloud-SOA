package com.ljt.springcloudstreamkafka.stream.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 *  stream方式消息消费Bean
 */
@Component
@EnableBinding({Sink.class})
public class MessageConsumerBean {
    @Autowired
    @Qualifier(Sink.INPUT) // Bean名称
    SubscribableChannel subscribableChannel;

    @Autowired
    private Sink sink;

    // 第一种接收消息方式
    // 当字段注入完成后的回调
    @PostConstruct
    public void init(){
        subscribableChannel.subscribe(new MessageHandler() {// 异步监听处理方式
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                System.out.println("subscribe方式接收处理消息：" + message.getPayload());
            }
        });
    }
    // 第二种接收消息方式
    // 通过@ServiceActivator
    /*@ServiceActivator(inputChannel = Sink.INPUT)
    public void onMessage(Object message){
        System.out.println("onMessage方式接收处理消息：" + message);
    }*/

    // 第三种接收消息方式
    @StreamListener(Sink.INPUT)
    public void onMessage(String message){
        System.out.println("@StreamListener方式接收处理消息：" + message);
    }
}
