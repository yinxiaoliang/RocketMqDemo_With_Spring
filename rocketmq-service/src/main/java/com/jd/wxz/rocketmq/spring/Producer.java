package com.jd.wxz.rocketmq.spring;


import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;

/**
 * 生产者，初始化MQProducer
 * @author wangxingzhe
 */
public class Producer {
    private String namesrvAddr;
    private String producerGroup;
    private DefaultMQProducer producer;

    public String getNamesrvAddr() {
        return namesrvAddr;
    }

    public void setNamesrvAddr(String namesrvAddr) {
        this.namesrvAddr = namesrvAddr;
    }

    public String getProducerGroup() {
        return producerGroup;
    }

    public void setProducerGroup(String producerGroup) {
        this.producerGroup = producerGroup;
    }

    public void init(){
        producer = new DefaultMQProducer(producerGroup);
        producer.setNamesrvAddr(namesrvAddr);
        try {
            producer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
        }
    }

    /**
     * 发送消息
     * @param message
     * @return
     */
    public SendResult send(Message message) throws
            InterruptedException, RemotingException, MQClientException, MQBrokerException {
        SendResult result = producer.send(message);
        return result;
    }
}