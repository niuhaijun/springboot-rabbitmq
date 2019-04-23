package com.niu.springbootrabbitmq.exchange.fanout;

import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author niuhaijun
 * @date 2019-01-22 23:26
 */
@Configuration
public class FanoutExchangeConfig {

  public static final String FANOUT_EXCHANGE = "fanout.exchange";
  private boolean durable = false;
  private boolean autoDelete = true;

  @Bean(name = FANOUT_EXCHANGE)
  public FanoutExchange fanoutExchange() {

    Map<String, Object> arguments = new HashMap<>();
    arguments.put("交换器类型", "广播交换器");

    return new FanoutExchange(FANOUT_EXCHANGE, durable, autoDelete, arguments);
  }

}
