package com.niu.messageacknowledge.exchange;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author niuhaijun
 * @date 2019-01-22 23:26
 */
@Configuration
public class DirectExchangeConfig {

  public static final String DIRECT_EXCHANGE = "direct.exchange";
  public boolean durable = false;
  public boolean autoDelete = false;

  @Bean(name = DIRECT_EXCHANGE)
  public DirectExchange directExchange() {

    return new DirectExchange(DIRECT_EXCHANGE, durable, autoDelete, null);
  }

}
