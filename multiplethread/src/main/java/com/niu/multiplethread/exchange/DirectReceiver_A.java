package com.niu.multiplethread.exchange;


import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


/**
 * try-catch只能处理Exception, 不能处理Error
 * 当发生Exception时，消费者不退出
 * 当发生Error时，消费者退出
 */
@Component
@RabbitListener(queues = DirectQueueConfig.QUEUE_A, containerFactory = "containerFactory")
@Slf4j
public class DirectReceiver_A {

  @RabbitHandler
  public void process(String content) {

    int res = 1;

    switch (res) {
      case 0:
        throw new NullPointerException("模拟空指针异常");
      case 1:
        throw new OutOfMemoryError("模拟堆溢出!!！");
    }
    log.info("Receiver_A 从 QUEUE_A 中接收到的信息是--> {}", content);
  }

}
