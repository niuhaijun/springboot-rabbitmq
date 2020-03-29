package com.niu.retry;

import com.niu.retry.model.MqAccount;
import com.niu.retry.service.MoneyService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RetryApplicationTests {

	@Autowired
	private MoneyService service;

	@Test
	public void test() {

		int id = 1;
		int money = 1000;

		MqAccount mqAccount = new MqAccount(id, money);
		service.save(mqAccount);
	}

}
