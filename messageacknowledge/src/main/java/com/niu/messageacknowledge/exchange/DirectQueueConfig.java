package com.niu.messageacknowledge.exchange;

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
  public static final String QUEUE_B = "direct.queueA";
  private boolean durable = false;
  private boolean autoDelete = true;
  private boolean exclusive = false;

  /**
   * 队列A
   */
  @Bean(name = QUEUE_A)
  public Queue queueA() {

    Map<String, Object> arguments = new HashMap<>(2);
    // 通过队列设置消息的过期时间
    arguments.put("x-message-ttl", 6 * 1000);
    // 设置队列的过期时间
    arguments.put("x-expires", 6 * 1000);

    return new Queue(QUEUE_A, durable, exclusive, autoDelete, arguments);
  }

}
