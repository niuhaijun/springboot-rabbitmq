package com.niu.fourexchange.exchange.fanout;


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
  public Binding bindingOne(
      @Qualifier(FanoutExchangeConfig.FANOUT_EXCHANGE) FanoutExchange exchange,
      @Qualifier(FanoutQueueConfig.QUEUE_A) Queue queue) {

    return BindingBuilder.bind(queue)
        .to(exchange);
  }

  @Bean
  public Binding bindingTwo(
      @Qualifier(FanoutExchangeConfig.FANOUT_EXCHANGE) FanoutExchange exchange,
      @Qualifier(FanoutQueueConfig.QUEUE_B) Queue queue) {

    return BindingBuilder.bind(queue)
        .to(exchange);
  }

}
