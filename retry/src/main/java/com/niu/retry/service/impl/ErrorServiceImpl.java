package com.niu.retry.service.impl;

import com.niu.retry.mapper.MqMapper;
import com.niu.retry.model.MgErrorMsg;
import com.niu.retry.model.MqAccount;
import com.niu.retry.service.ErrorService;
import com.niu.retry.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author niuhaijun
 * @date 2020-03-29 15:06
 * @version 1.0
 * @description: xxx
 */
@Service
@Slf4j
public class ErrorServiceImpl implements ErrorService {

	@Autowired
	private MqMapper mqMapper;

	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
	@Override
	public void save(MqAccount account, Throwable e) {

		String errorMsg = ExceptionUtils.getStackTrace(e);
		mqMapper.saveErrorMsg(new MgErrorMsg(account.getId(), errorMsg));
		log.info("错误信息保存成功, id = {}", account.getId());
	}
}
