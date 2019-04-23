package com.niu.springbootrabbitmq.exchange.topic;



import static com.niu.springbootrabbitmq.exchange.topic.TopicQueueConfig.QUEUE_A;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * @author niuhaijun
 * @date 2019-01-15 16:52
 */
@Component
@RabbitListener(queues = {QUEUE_A})
public class TopicReceiver_A {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @RabbitHandler
  public void process(String content) {

    logger.info("Receiver_A 从 QUEUE_A 中接收到的信息是--> {}", content);
  }
}
