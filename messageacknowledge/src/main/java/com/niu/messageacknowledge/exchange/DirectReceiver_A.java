package com.niu.messageacknowledge.exchange;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(queues = DirectQueueConfig.QUEUE_A)
@Slf4j
public class DirectReceiver_A {

  @RabbitHandler
  public void process(String content) {

    log.info("Receiver_A 从 QUEUE_A 中接收到的信息是--> {}", content);
  }

}
