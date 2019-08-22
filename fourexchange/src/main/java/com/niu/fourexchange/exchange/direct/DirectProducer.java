package com.niu.fourexchange.exchange.direct;


import java.util.UUID;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 * @author niuhaijun
 * @date 2019-01-15 16:46
 */
@Component
public class DirectProducer {

  private RabbitTemplate rabbitTemplate;

  /**
   * 由于rabbitTemplate的scope属性设置为
   * ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
   *
   * 构造方法注入rabbitTemplate。rabbitTemplate如果为单例的话，那回调就是最后设置的内容
   */
  @Autowired
  public DirectProducer(RabbitTemplate rabbitTemplate) {

    this.rabbitTemplate = rabbitTemplate;
  }

  /**
   * 发送消息
   * @param content 消息体
   * @param routingKey 路由键
   */
  public void sendMsg(String content, String routingKey) {

    CorrelationData correlationData = new CorrelationData(
        UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());

    rabbitTemplate.convertAndSend(DirectExchangeConfig.DIRECT_EXCHANGE, routingKey, content,
        correlationData);
  }
}
