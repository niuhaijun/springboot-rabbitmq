package com.consumerretry.retry.model;

/**
 * @author niuhaijun
 * @date 2020-03-29 14:32
 * @version 1.0
 * @description: xxx
 */
public class MqAccount {

	private long id;
	private int money;

	public MqAccount() {

	}

	public MqAccount(long id, int money) {

		this.id = id;
		this.money = money;
	}

	public long getId() {

		return id;
	}

	public void setId(long id) {

		this.id = id;
	}

	public int getMoney() {

		return money;
	}

	public void setMoney(int money) {

		this.money = money;
	}
}
