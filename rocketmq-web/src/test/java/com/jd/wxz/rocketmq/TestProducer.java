package com.jd.wxz.rocketmq;

import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

public class TestProducer {
	public static void main(String[] args) {
		DefaultMQProducer producer = new DefaultMQProducer("Producer");
		producer.setNamesrvAddr("127.0.0.1:9876");
		try {
			producer.start();

			try {
				Message msg = new Message("PushTopic", "tag", "" + System.currentTimeMillis(), "Just for test.".getBytes());

				SendResult result = producer.send(msg);
				System.out.println("id:" + result.getMsgId() + " result:" + result.getSendStatus());
			} catch (Exception e) {
				e.printStackTrace();
				Thread.sleep(2000);
			} finally {
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			producer.shutdown();
		}
	}
}