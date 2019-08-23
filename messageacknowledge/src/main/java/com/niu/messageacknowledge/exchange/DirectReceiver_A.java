package com.niu.messageacknowledge.exchange;


import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.Random;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(queues = DirectQueueConfig.QUEUE_A, containerFactory = "containerFactory")
@Slf4j
public class DirectReceiver_A {


  private Random random = new Random();

  @RabbitHandler
  public void process(Channel channel, Message message, String content) {

    final long deliveryTag = message.getMessageProperties().getDeliveryTag();
    try {

      // 接收到消息做业务
      boolean result = random.nextInt(1) % 2 == 0;
      if (!result) {
        throw new NullPointerException("模拟处理业务出现异常");
      }
      // 通知 MQ 消息已被成功消费,可以ACK了
      channel.basicAck(deliveryTag, false);
    }
    catch (Exception e) {
      try {
        // 处理失败,重新压入MQ
        channel.basicReject(deliveryTag, true);
//        channel.basicNack(deliveryTag, false, true);
      }
      catch (IOException e1) {
        e1.printStackTrace();
      }

    }

    log.info("Receiver_A 从 QUEUE_A 中接收到的信息是--> {}", content);
  }

}
