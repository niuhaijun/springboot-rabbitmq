package com.niu.fourexchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FourexchangeApplication {

  public static void main(String[] args) {

    SpringApplication.run(FourexchangeApplication.class, args);
  }

}
