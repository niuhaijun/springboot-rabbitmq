package com.niu.springbootrabbitmq.exchange.topic;


import static com.niu.springbootrabbitmq.exchange.topic.TopicQueueConfig.QUEUE_B;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author niuhaijun
 * @date 2019-01-22 23:49
 */
@Component
@RabbitListener(queues = {QUEUE_B})
public class TopicReceiver_B {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @RabbitHandler
  public void process(String content) {

    logger.info("Receiver_B 从 QUEUE_B 中接收到的信息是--> {}", content);
  }
}
