package com.niu.messageacknowledge.exchange;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author niuhaijun
 * @date 2019-01-19 01:55
 */
@Configuration
public class DirectBindingConfig {

  public static final String ROUTING_KEY_A = "DirectExchange to queueA";
  public static final String BINDING_KEY_A = ROUTING_KEY_A;

  @Bean
  public Binding directBindingOne(
      @Qualifier(DirectExchangeConfig.DIRECT_EXCHANGE) DirectExchange exchange,
      @Qualifier(DirectQueueConfig.QUEUE_A) Queue queue) {

    return BindingBuilder.bind(queue).to(exchange).with(BINDING_KEY_A);
  }

}
