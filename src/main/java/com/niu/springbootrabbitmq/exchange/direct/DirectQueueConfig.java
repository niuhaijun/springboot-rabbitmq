package com.niu.springbootrabbitmq.exchange.direct;

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
    /**
     * name：队列名称
     * durable：是否持久化：true表示持久化，持久化可以将队列存盘，在服务器启动的时候也不会丢失相关信息
     * exclusive：是否排他：true表示排他，如果一个队列被声明为排他队列，该队列仅对首次声明它的连接可见，并在连接断开时自动删除。
     * autoDelete：是否自动删除：true表示自动删除，自动删除的前提是至少还有消费者连接到这个队列上，以后所有与这个队列连接的消费者都断开。
     * arguments：队列参数
     */
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
