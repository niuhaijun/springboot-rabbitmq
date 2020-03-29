package com.niu.retry.service.impl;

import com.niu.retry.mapper.MqMapper;
import com.niu.retry.model.MqAccount;
import com.niu.retry.service.ErrorService;
import com.niu.retry.service.MoneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author niuhaijun
 * @date 2020-03-29 14:59
 * @version 1.0
 * @description: xxx
 */
@Service
public class MoneyServiceImpl implements MoneyService {

	@Autowired
	private MqMapper mqMapper;
	@Autowired
	private ErrorService errorService;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	@Override
	public void save(MqAccount account) {

		try {
			mqMapper.saveAccount(account);
			if (account.getId() % 2 == 0) {
//				int num = 1 / 0;
				throw new OutOfMemoryError();
			}
		}
		catch (Exception | Error ex) {
			errorService.save(account, ex);
			throw ex;
		}
	}
}
