package com.niu.retry.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author niuhaijun
 * @date 2020-03-29 15:44
 * @version 1.0
 * @description: xxx
 */
@Configuration
public class MqConfig {

	public static final String EXCHANGE_NAME = "exchange";
	public static final String QUEUE_NAME = "queue";
	public static final String ROUTING_KEY = "routing_key";

	@Bean(name = EXCHANGE_NAME)
	public DirectExchange directExchange() {

		return new DirectExchange(EXCHANGE_NAME, true, false, null);
	}

	@Bean(name = QUEUE_NAME)
	public Queue queueA() {

		return new Queue(QUEUE_NAME, true, false, false, null);
	}

	@Bean
	public Binding directBindingOne(
		@Qualifier(EXCHANGE_NAME) DirectExchange exchange,
		@Qualifier(QUEUE_NAME) Queue queue) {

		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}

}
