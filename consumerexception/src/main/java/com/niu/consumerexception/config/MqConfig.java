package com.niu.consumerexception.config;

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
 * @description: 设置自动删除是为了便于开发测试，每次启动都是初始环境
 */
@Configuration
public class MqConfig {

	public static final String EXCHANGE_NAME = "NiuExchange";
	public static final String QUEUE_NAME = "NiuQueue";
	public static final String ROUTING_KEY = "routing_key";

	@Bean(name = EXCHANGE_NAME)
	public DirectExchange directExchange() {

		return new DirectExchange(EXCHANGE_NAME, true, true, null);
	}

	@Bean(name = QUEUE_NAME)
	public Queue queueA() {

		return new Queue(QUEUE_NAME, true, false, true, null);
	}

	@Bean
	public Binding directBindingOne(
		@Qualifier(EXCHANGE_NAME) DirectExchange exchange,
		@Qualifier(QUEUE_NAME) Queue queue) {

		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}

}
