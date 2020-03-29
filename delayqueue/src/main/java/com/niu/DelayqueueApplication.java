package com.niu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DelayqueueApplication {

  public static void main(String[] args) {

    SpringApplication.run(DelayqueueApplication.class, args);
  }

}