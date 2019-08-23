package com.niu.delayqueue.exchange;

import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author niuhaijun
 * @date 2019-01-17 17:44
 */
@Configuration
public class DirectQueueConfig {

  public static final String QUEUE_A = "direct.queueA";
  public static final String QUEUE_B = "dead.letter.direct.queueB";
  private final boolean durable = false;
  private final boolean autoDelete = true;
  private final boolean exclusive = false;

  /**
   * 队列A
   */
  @Bean(name = QUEUE_A)
  public Queue queueA() {

    Map<String, Object> arguments = new HashMap<>(2);
    // 设置死信交换器
    arguments.put("x-dead-letter-exchange", DirectExchangeConfig.DEAD_LETTER_DIRECT_EXCHANGE);
    // 设置死信的路由键, 无特殊设置，不推荐使用
    arguments.put("x-dead-letter-routing-key", DirectBindingConfig.ROUTING_KEY_B);
    // 设置消息的过期时间 10s
    arguments.put("x-message-ttl", 10 * 1000);

    return new Queue(QUEUE_A, durable, exclusive, autoDelete, arguments);
  }

  /**
   * 死信队列
   */
  @Bean(name = QUEUE_B)
  public Queue queueB() {

    return new Queue(QUEUE_B, durable, exclusive, autoDelete, null);
  }


}
