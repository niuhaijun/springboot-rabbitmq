package com.consumerretry.retry.model;

/**
 * @author niuhaijun
 * @date 2020-03-29 14:33
 * @version 1.0
 * @description: xxx
 */
public class MgErrorMsg {

	private long id;
	private String errorMsg;

	public MgErrorMsg() {

	}

	public MgErrorMsg(long id, String errorMsg) {

		this.id = id;
		this.errorMsg = errorMsg;
	}

	public long getId() {

		return id;
	}

	public void setId(long id) {

		this.id = id;
	}

	public String getErrorMsg() {

		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {

		this.errorMsg = errorMsg;
	}
}
