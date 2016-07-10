package com.jd.wxz.rocketmq.spring;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * 消费者监听接口，业务需要实现此接口并配置到Consumer中。 Created by wangxingzhe on 2015/11/3.
 */
public interface RocketMqMessageListener {
	boolean onMessage(List<MessageExt> messages, ConsumeConcurrentlyContext Context);
}
