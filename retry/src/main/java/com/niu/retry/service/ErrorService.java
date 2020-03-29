package com.niu.retry.service;

import com.niu.retry.model.MqAccount;

/**
 * @author niuhaijun
 * @date 2020-03-29 15:06
 * @version 1.0
 * @description: xxx
 */
public interface ErrorService {

	void save(MqAccount account, Throwable e);

}
