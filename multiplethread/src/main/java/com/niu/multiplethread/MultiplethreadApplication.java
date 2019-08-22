package com.niu.multiplethread;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MultiplethreadApplication {

  public static void main(String[] args) {

    SpringApplication.run(MultiplethreadApplication.class, args);
  }

}
