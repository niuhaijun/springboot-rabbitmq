package com.niu.consumerexception.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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


	@RabbitHandler
	public void process(String content) {

		int id = Integer.parseInt(content);

		throw new OutOfMemoryError("====> " + id);
	}

}