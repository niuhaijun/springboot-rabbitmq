package com.niu.springbootrabbitmq.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;

/**
 * 消息发送到队列确认
 *
 * exchange -> queue
 *
 * @Author: niuhaijun
 * @Date: 2019-08-22 13:09
 * @Version 1.0
 */
public class MyReturnCallback implements ReturnCallback {

  @Override
  public void returnedMessage(Message message, int replyCode, String replyText, String exchange,
      String routingKey) {

  }
}
