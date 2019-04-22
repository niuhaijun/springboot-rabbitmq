package com.niu.springbootrabbitmq.tasks;

import static com.niu.springbootrabbitmq.exchange.direct.DirectBindingConfig.ROUTING_KEY_A;
import static com.niu.springbootrabbitmq.exchange.direct.DirectBindingConfig.ROUTING_KEY_B;
import static java.lang.String.format;
import static java.time.LocalTime.now;

import com.niu.springbootrabbitmq.exchange.direct.DirectProducer;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.LongStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final AtomicInteger count = new AtomicInteger(0);
  private final Random random = new Random();

  @Autowired
  private DirectProducer directProducer;

  @Scheduled(cron = "0/30 * * * * ?")
  public void sentMessages() {

    directSentMessage();
  }


  private void directSentMessage() {

    String[] routingKeys = {ROUTING_KEY_A, ROUTING_KEY_B};
    LongStream.range(0, 5)
        .forEach(t -> {
          String routingKey = routingKeys[random.nextInt(routingKeys.length)];
          String message = format("【这是DirectProducer发送的第%s个消息, 创建消息时间是%s, 路由键是%s】", count.addAndGet(1),
              now(), routingKey);
          directProducer.sendMsg(message, routingKey);
        });
  }

}

