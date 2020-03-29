package com.consumerretry.retry.mapper;

import com.consumerretry.retry.model.MgErrorMsg;
import com.consumerretry.retry.model.MqAccount;

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
