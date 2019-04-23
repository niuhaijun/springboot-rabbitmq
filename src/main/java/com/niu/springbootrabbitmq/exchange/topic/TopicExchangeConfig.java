package com.niu.springbootrabbitmq.exchange.topic;

import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author niuhaijun
 * @date 2019-01-22 23:26
 */
@Configuration
public class TopicExchangeConfig {

  public static final String TOPIC_EXCHANGE = "topic.exchange";
  public boolean durable = false;
  public boolean autoDelete = true;

  @Bean(name = TOPIC_EXCHANGE)
  public TopicExchange directExchange() {

    Map<String, Object> arguments = new HashMap<>();
    arguments.put("交换器类型", "话题交换器");

    return new TopicExchange(TOPIC_EXCHANGE, durable, autoDelete, null);
  }

}
