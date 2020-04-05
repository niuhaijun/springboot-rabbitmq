package com.niu.consumerexception.tasks;

import com.niu.consumerexception.config.MqConfig;
import com.niu.consumerexception.config.Producer;
import java.util.concurrent.atomic.AtomicInteger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author niuhaijun
 * @date 2018/11/15 19:17
 */
@Component
@Slf4j
public class ScheduledTasks {

	private final AtomicInteger count = new AtomicInteger(0);
	@Autowired
	private Producer producer;

	@Scheduled(cron = "0/10 * * * * ?")
	public void sentMessages() {

		int id = count.incrementAndGet();

		producer.sendMsg("" + id, MqConfig.ROUTING_KEY);

	}

}

