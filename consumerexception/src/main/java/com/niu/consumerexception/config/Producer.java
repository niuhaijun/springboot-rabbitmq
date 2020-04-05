package com.niu.consumerexception.config;

import java.util.UUID;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author niuhaijun
 * @date 2020-03-29 15:43
 * @version 1.0
 * @description: xxx
 */
@Component
public class Producer {

	private RabbitTemplate rabbitTemplate;

	@Autowired
	public Producer(RabbitTemplate rabbitTemplate) {

		this.rabbitTemplate = rabbitTemplate;
	}

	public void sendMsg(String content, String routingKey) {

		CorrelationData correlationData = new CorrelationData(
			UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());

		rabbitTemplate.convertAndSend(MqConfig.EXCHANGE_NAME, routingKey, content,
			correlationData);
	}

}
