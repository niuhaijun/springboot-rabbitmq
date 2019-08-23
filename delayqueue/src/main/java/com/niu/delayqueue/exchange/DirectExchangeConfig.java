package com.niu.delayqueue.exchange;

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
  public static final String DEAD_LETTER_DIRECT_EXCHANGE = "dead.letter.direct.exchange";

  public boolean durable = false;
  public boolean autoDelete = true;

  @Bean(name = DIRECT_EXCHANGE)
  public DirectExchange directExchange() {

    return new DirectExchange(DIRECT_EXCHANGE, durable, autoDelete, null);
  }

  /**
   * 死信交换器
   */
  @Bean(name = DEAD_LETTER_DIRECT_EXCHANGE)
  public DirectExchange deadLetterExchange() {

    return new DirectExchange(DEAD_LETTER_DIRECT_EXCHANGE, durable, autoDelete, null);
  }
}
