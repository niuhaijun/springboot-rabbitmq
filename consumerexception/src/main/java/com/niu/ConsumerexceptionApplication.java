package com.niu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 消费者消费消息的时候发生异常时候会下线
 */
@SpringBootApplication
@EnableScheduling
public class ConsumerexceptionApplication {

	public static void main(String[] args) {

		SpringApplication.run(ConsumerexceptionApplication.class, args);
	}

}
