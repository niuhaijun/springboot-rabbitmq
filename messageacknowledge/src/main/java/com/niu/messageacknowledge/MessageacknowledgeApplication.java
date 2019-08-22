package com.niu.messageacknowledge;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

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
