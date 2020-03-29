package com.niu.retry;

import com.niu.retry.model.MqAccount;
import com.niu.retry.service.MoneyService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RetryApplicationTests {

	@Autowired
	private MoneyService service;

	@Test
	public void test() {

		int id = 2;
		int money = 1000;

		MqAccount mqAccount = new MqAccount(id, money);
		service.save(mqAccount);
	}

}
