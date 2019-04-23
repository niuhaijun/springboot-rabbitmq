package com.niu.springbootrabbitmq.exchange.topic;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author niuhaijun
 * @date 2019-01-17 17:44
 */
@Configuration
public class TopicQueueConfig {

  public static final String QUEUE_A = "topic.queueA";
  public static final String QUEUE_B = "topic.queueB";
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
