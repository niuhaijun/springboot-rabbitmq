package com.niu.springbootrabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringbootRabbitmqApplication {

  public static void main(String[] args) {

    SpringApplication.run(SpringbootRabbitmqApplication.class, args);
  }

}
