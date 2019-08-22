package com.niu.multiplethread.exchange;

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
  public static final String QUEUE_B = "direct.queueB";
  private boolean durable = false;
  private boolean autoDelete = true;
  private boolean exclusive = false;

  /**
   * 队列A
   */
  @Bean(name = QUEUE_A)
  public Queue queueA() {

    return new Queue(QUEUE_A, durable, exclusive, autoDelete, null);
  }

  /**
   * 队列B
   */
  @Bean(name = QUEUE_B)
  public Queue queueB() {

    return new Queue(QUEUE_B, durable, exclusive, autoDelete, null);
  }
}
