package com.jd.wxz.rocketmq;

import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.common.message.MessageExt;
import com.jd.wxz.rocketmq.spring.RocketMqMessageListener;

import java.util.List;

/**
 * 业务监听实现Demo
 * Created by wangxingzhe on 2015/11/3.
 */
public class SimpleRocketMqListener implements RocketMqMessageListener {
    public boolean onMessage(List<MessageExt> messages, ConsumeConcurrentlyContext Context) {
    	System.out.println(">>>>> 进入到SimpleRocketMqListener");
        for (int i = 0; i < messages.size(); i++) {
            Message msg = messages.get(i); 
            System.out.println(new String(msg.getBody()));
            System.out.println("haha =" + msg.toString());
        }
        return true;
    }
}
