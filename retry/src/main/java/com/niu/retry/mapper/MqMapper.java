package com.niu.retry.mapper;

import com.niu.retry.model.MgErrorMsg;
import com.niu.retry.model.MqAccount;

/**
 * @author niuhaijun
 * @date 2020-03-29 14:31
 * @version 1.0
 * @description: xxx
 */
public interface MqMapper {

	int saveAccount(MqAccount account);

	int saveErrorMsg(MgErrorMsg account);

}
