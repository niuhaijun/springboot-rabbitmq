package com.niu.multiplethread.tasks;

import static java.lang.String.format;
import static java.time.LocalTime.now;

import com.niu.multiplethread.exchange.DirectBindingConfig;
import com.niu.multiplethread.exchange.DirectProducer;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.LongStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTasks {

  private final AtomicInteger count = new AtomicInteger(0);
  private final Random random = new Random();

  @Autowired
  private DirectProducer directProducer;

  @Scheduled(cron = "0/30 * * * * ?")
  public void sentMessages() {

    directSentMessage();
  }

  private void directSentMessage() {

    String[] routingKeys = {DirectBindingConfig.ROUTING_KEY_A, DirectBindingConfig.ROUTING_KEY_B};
    LongStream.range(0, 5)
        .forEach(t -> {
          String routingKey = routingKeys[random.nextInt(routingKeys.length)];
          String message = format("【这是DirectProducer发送的第%s个消息, 创建消息时间是%s, 路由键是%s】",
              count.addAndGet(1), now(), routingKey);
          directProducer.sendMsg(message, routingKey);
        });
  }
}

