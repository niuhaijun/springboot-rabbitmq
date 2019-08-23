package com.niu.alternateexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AlternateexchangeApplication {

  public static void main(String[] args) {

    SpringApplication.run(AlternateexchangeApplication.class, args);
  }

}
