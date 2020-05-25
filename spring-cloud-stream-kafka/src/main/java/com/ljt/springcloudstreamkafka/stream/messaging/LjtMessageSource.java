package com.ljt.springcloudstreamkafka.stream.messaging;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * 自定义消息通道
 */
public interface LjtMessageSource {
    /**
     * 消息来源管道名称
     */
    String NAME = "ljt";

    @Output(NAME)
    MessageChannel ljt();
}
