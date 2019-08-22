package com.niu.fourexchange.exchange.direct;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 消费者
 *
 * RabbitListener可以作用在类、方法上。在前者的情况下，需要在处理的方法使用@RabbitHandler。
 *
 * @author niuhaijun
 * @date 2019-01-15 16:52
 */
@Component
@RabbitListener(queues = {DirectQueueConfig.QUEUE_A})
public class DirectReceiver_A {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @RabbitHandler
  public void process(String content) {

    logger.info("Receiver_A 从 【QUEUE_A | QUEUE_B】 中接收到的信息是--> {}", content);
  }
}
