package com.jd.wxz.rocketmq;

import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.jd.wxz.rocketmq.spring.Producer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by wangxingzhe on 2015/11/2.
 */
@Configuration
@RunWith(SpringJUnit4ClassRunner.class)
// @TransactionConfiguration(transactionManager="transactionManager",
// defaultRollback = true)
@ContextConfiguration(locations = { "classpath:spring-config-test.xml" })
public class TestRocketMq {
	@Resource
	private Producer rocketMqProducer;

	@Test
	public void test() {
		for (int i = 0; i < 10; i++) {
			try {
				Message msg = new Message("PushTopic", "push", "" + System.currentTimeMillis(), "Just for test.".getBytes());

				SendResult result = rocketMqProducer.send(msg);
				System.out.println("id:" + result.getMsgId() + " result:" + result.getSendStatus());
			} catch (Exception e) {
				e.printStackTrace();
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			} finally {
			}
		}
	}
}
