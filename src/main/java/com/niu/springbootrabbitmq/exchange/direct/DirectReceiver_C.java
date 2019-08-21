package com.niu.springbootrabbitmq.exchange.direct;

import static com.niu.springbootrabbitmq.exchange.direct.DirectQueueConfig.QUEUE_A;
import static com.niu.springbootrabbitmq.exchange.direct.DirectQueueConfig.QUEUE_B;

import java.util.Random;
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
@RabbitListener(queues = {QUEUE_B, QUEUE_A}, containerFactory = "containerFactory")
public class DirectReceiver_C {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final Random random = new Random();

  /**
   * 手动抛出Error，造成Consumer thread error, thread abort,消费者退出。
   *
   * try catch只能捕获异常，不能捕获错误。
   */
  @RabbitHandler
  public void process(String content) {

    try {
      if (random.nextInt(1) % 2 == 0) {
        throw new OutOfMemoryError("手动抛出OOM异常");
      }
    }
    catch (Exception e) {
      logger.info("发生了异常e {}", e.getMessage());
    }

    logger.info("Receiver_C 从 【QUEUE_A | QUEUE_B】 中接收到的信息是--> {}", content);
  }
}
