package com.niu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan({"com.niu.retry.mapper"})
public class RetryApplication {

	public static void main(String[] args) {

		SpringApplication.run(RetryApplication.class, args);
	}

}
