package com.niu.retry.config;

import com.google.gson.Gson;
import com.niu.retry.model.MqAccount;
import com.niu.retry.service.MoneyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author niuhaijun
 * @date 2020-03-29 15:49
 * @version 1.0
 * @description: xxx
 */
@Component
@RabbitListener(queues = MqConfig.QUEUE_NAME)
@Slf4j
public class Consumer {

	private final Gson gson = new Gson();

	@Autowired
	private MoneyService service;

	@RabbitHandler
	public void process(String content) {

		MqAccount account = gson.fromJson(content, MqAccount.class);

		service.save(account);
	}

}