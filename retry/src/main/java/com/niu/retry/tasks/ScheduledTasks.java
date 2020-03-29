package com.niu.retry.tasks;

import com.google.gson.Gson;
import com.niu.retry.config.MqConfig;
import com.niu.retry.config.Producer;
import com.niu.retry.model.MqAccount;
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
	private final Gson gson = new Gson();

	@Autowired
	private Producer producer;

	@Scheduled(cron = "0/5 * * * * ?")
	public void sentMessages() {

		for (int i = 0; i < 5; i++) {
			int id = count.incrementAndGet();
			MqAccount account = new MqAccount(id, id);

			producer.sendMsg(gson.toJson(account), MqConfig.ROUTING_KEY);
		}


	}

}

