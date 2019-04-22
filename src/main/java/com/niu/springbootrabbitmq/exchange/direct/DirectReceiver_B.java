package com.niu.springbootrabbitmq.exchange.direct;

import static com.niu.springbootrabbitmq.exchange.direct.DirectQueueConfig.QUEUE_A;
import static com.niu.springbootrabbitmq.exchange.direct.DirectQueueConfig.QUEUE_B;

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
@RabbitListener(queues = {QUEUE_B, QUEUE_A})
public class DirectReceiver_B {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @RabbitHandler
  public void process(String content) {

    logger.info("Receiver_B 从 【QUEUE_A | QUEUE_B】 中接收到的信息是--> {}", content);
  }
}
