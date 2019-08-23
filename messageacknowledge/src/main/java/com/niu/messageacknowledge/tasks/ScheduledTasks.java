package com.niu.messageacknowledge.tasks;

import static java.lang.String.format;
import static java.time.LocalTime.now;

import com.niu.messageacknowledge.exchange.DirectBindingConfig;
import com.niu.messageacknowledge.exchange.DirectProducer;
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

  @Scheduled(cron = "0/5 * * * * ?")
  public void sentMessages() {

    directSentMessage();
  }

  private void directSentMessage() {

    String[] routingKeys = {DirectBindingConfig.ROUTING_KEY_A + "--无法路由到队列",
        DirectBindingConfig.ROUTING_KEY_A};
    LongStream.range(0, 10)
        .forEach(t -> {
          String routingKey = routingKeys[random.nextInt(routingKeys.length)];
          String message = format("【这是DirectProducer发送的第%s个消息, 创建消息时间是%s, 路由键是%s】",
              count.addAndGet(1), now(), routingKey);
          directProducer.sendMsg(message, routingKey);
        });
  }
}

