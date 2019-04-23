package com.niu.springbootrabbitmq.exchange.fanout;


import static com.niu.springbootrabbitmq.exchange.fanout.FanoutExchangeConfig.FANOUT_EXCHANGE;
import static com.niu.springbootrabbitmq.exchange.fanout.FanoutQueueConfig.QUEUE_A;
import static com.niu.springbootrabbitmq.exchange.fanout.FanoutQueueConfig.QUEUE_B;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author niuhaijun
 * @date 2019-01-19 01:55
 */
@Configuration
public class FanoutBindingConfig {

  @Bean
  public Binding bindingOne(@Qualifier(FANOUT_EXCHANGE) FanoutExchange exchange,
      @Qualifier(QUEUE_A) Queue queue) {

    return BindingBuilder.bind(queue)
        .to(exchange);
  }

  @Bean
  public Binding bindingTwo(@Qualifier(FANOUT_EXCHANGE) FanoutExchange exchange,
      @Qualifier(QUEUE_B) Queue queue) {

    return BindingBuilder.bind(queue)
        .to(exchange);
  }

}
