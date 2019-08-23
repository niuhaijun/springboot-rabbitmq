package com.niu.alternateexchange.exchange;

import java.util.HashMap;
import java.util.Map;
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
  public static final String ALTERNATE_DIRECT_EXCHANGE = "alternate.direct.exchange";

  public boolean durable = false;
  public boolean autoDelete = true;

  @Bean(name = DIRECT_EXCHANGE)
  public DirectExchange directExchange() {

    /**
     * 设置交换器的备份交换器
     */
    Map<String, Object> arguments = new HashMap<>();
    arguments.put("alternate-exchange", ALTERNATE_DIRECT_EXCHANGE);

    return new DirectExchange(DIRECT_EXCHANGE, durable, autoDelete, arguments);
  }


  /**
   * 备份交换器
   */
  @Bean(name = ALTERNATE_DIRECT_EXCHANGE)
  public DirectExchange alternateExchange() {

    return new DirectExchange(ALTERNATE_DIRECT_EXCHANGE, durable, autoDelete, null);
  }


}
