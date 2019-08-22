package com.niu.springbootrabbitmq.config;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ConfirmCallback;

/**
 * 消息发送到交换器确认
 *
 * producer -> exchange
 *
 * @Author: niuhaijun
 * @Date: 2019-08-22 13:08
 * @Version 1.0
 */
public class MyConfirmCallback implements ConfirmCallback {

  @Override
  public void confirm(CorrelationData correlationData, boolean ack, String cause) {

  }
}
