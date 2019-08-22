package com.niu.messageacknowledge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 验证消息发送到exchange确认机制，可尝试在
 * 管理界面删除exchange操作，查看日志变化
 *
 * 验证消息发送到queue确认机制，尝试修改消息的routingKey
 * 或者删除队列来观察日志的变化。
 *
 *
 * 验证消费者确认机制，消息是否重新入队的情况，可尝试
 *
 *
 */
@SpringBootApplication
@EnableScheduling
@Slf4j
public class MessageacknowledgeApplication {

  public static void main(String[] args) {

    SpringApplication.run(MessageacknowledgeApplication.class, args);

    // 防止JVM退出
    new Thread(() -> {
      try {
        Thread.sleep(1000);
      }
      catch (InterruptedException e) {
        log.error("发生中断异常 {}", e.getMessage());
      }
    }).start();
  }

}
