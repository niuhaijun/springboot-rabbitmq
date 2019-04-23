package com.niu.springbootrabbitmq.exchange.direct;


import static com.niu.springbootrabbitmq.exchange.direct.DirectExchangeConfig.DIRECT_EXCHANGE;
import static com.niu.springbootrabbitmq.exchange.direct.DirectQueueConfig.QUEUE_A;
import static com.niu.springbootrabbitmq.exchange.direct.DirectQueueConfig.QUEUE_B;

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

  public static final String ROUTING_KEY_A = "direct.exchange to direct.queueA";
  public static final String ROUTING_KEY_B = "direct.exchange to direct.queueB";

  public static final String BINDING_KEY_A = ROUTING_KEY_A;
  public static final String BINDING_KEY_B = ROUTING_KEY_B;

  @Bean
  public Binding directBindingOne(@Qualifier(DIRECT_EXCHANGE) DirectExchange exchange,
      @Qualifier(QUEUE_A) Queue queue) {

    return BindingBuilder.bind(queue).to(exchange).with(BINDING_KEY_A);
  }

  @Bean
  public Binding directBindingTwo(@Qualifier(DIRECT_EXCHANGE) DirectExchange exchange,
      @Qualifier(QUEUE_B) Queue queue) {

    return BindingBuilder.bind(queue).to(exchange).with(BINDING_KEY_B);
  }

}
