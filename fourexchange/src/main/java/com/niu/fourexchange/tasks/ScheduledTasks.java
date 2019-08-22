package com.niu.fourexchange.tasks;

import static java.lang.String.format;
import static java.time.LocalTime.now;

import com.niu.fourexchange.exchange.direct.DirectBindingConfig;
import com.niu.fourexchange.exchange.direct.DirectProducer;
import com.niu.fourexchange.exchange.fanout.FanoutProducer;
import com.niu.fourexchange.exchange.topic.TopicBindingConfig;
import com.niu.fourexchange.exchange.topic.TopicProducer;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.LongStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * SpringBoot整合定时器
 *    https://blog.csdn.net/liboyang71/article/details/72781526
 *
 * @author niuhaijun
 * @date 2018/11/15 19:17
 */
@Component
public class ScheduledTasks {

  private final AtomicInteger count = new AtomicInteger(0);
  private final Random random = new Random();

  @Autowired
  private DirectProducer directProducer;

  @Autowired
  private FanoutProducer fanoutProducer;

  @Autowired
  private TopicProducer topicProducer;

  @Scheduled(cron = "0/30 * * * * ?")
  public void sentMessages() {

    int i = 3;
    switch (i) {
      case 1: {
        directSentMessage();
        break;
      }
      case 2: {
        fanoutSentMessage();
        break;
      }
      case 3: {
        topicSentMessage();
        break;
      }
      default: {
        directSentMessage();
      }
    }
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

  private void fanoutSentMessage() {

    final String routingKey = "xxx";
    LongStream.range(0, 5)
        .forEach(t -> {
          String message = format("【这是FanoutProducer发送的第%s个消息, 创建消息时间是%s, 路由键是%s】",
              count.addAndGet(1), now(), routingKey);
          fanoutProducer.sendMsg(message, routingKey);
        });
  }

  private void topicSentMessage() {

    String[] routingKeys = {TopicBindingConfig.ROUTING_KEY_A, TopicBindingConfig.ROUTING_KEY_B,
        TopicBindingConfig.ROUTING_KEY_C};
    LongStream.range(0, 5)
        .forEach(t -> {
          String routingKey = routingKeys[random.nextInt(routingKeys.length)];
          String message = format("【这是TopicProducer发送的第%s个消息, 创建消息时间是%s, 路由键是%s】",
              count.addAndGet(1), now(), routingKey);
          topicProducer.sendMsg(message, routingKey);
        });
  }
}

