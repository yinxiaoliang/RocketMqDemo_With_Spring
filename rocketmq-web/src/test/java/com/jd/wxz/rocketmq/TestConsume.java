package com.jd.wxz.rocketmq;

import java.util.List;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;

public class TestConsume {
	public static void main(String[] args) throws MQClientException {
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("ConsumerGroupName");
		consumer.setNamesrvAddr("127.0.0.1:9876");
		consumer.setInstanceName("Consumber");
		//Message(String topic, String tags, String keys, byte[] body)
		//Message msg = new Message("PushTopic", "push", "" + System.currentTimeMillis(), "Just for test.".getBytes());
		consumer.subscribe("PushTopic", "*");
		
		consumer.registerMessageListener(new MessageListenerConcurrently() {
			
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
				System.out.println(Thread.currentThread().getName() + " Receive New Messages: msg.size()= " + msgs.size());

				MessageExt msg = msgs.get(0);
				if (msg.getTopic().equals("PushTopic")) {
					//执行pushTopic的消费逻辑
					System.out.println("PushTopic -> " + new String(msg.getBody()));
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		consumer.start();

		System.out.println("Consumer Started.");
	}
}
