package com.niu.messageacknowledge.exchange;


import java.util.UUID;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 生产者
 *
 * @author niuhaijun
 * @date 2019-01-15 16:46
 */
@Component
public class DirectProducer
    implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

  @Value("${spring.rabbitmq.template.mandatory}")
  private Boolean mandatory;

  private RabbitTemplate rabbitTemplate;

  @Autowired
  public DirectProducer(RabbitTemplate rabbitTemplate) {

    rabbitTemplate.setMandatory(mandatory);
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendMsg(String content, String routingKey) {

    CorrelationData correlationData = new CorrelationData(
        UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());

    rabbitTemplate.convertAndSend(DirectExchangeConfig.DIRECT_EXCHANGE, routingKey, content,
        correlationData);
  }


  /**
   * 通过实现ConfirmCallBack接口，消息发送到交换器Exchange后触发回调。
   *
   * 使用该功能需要开启确认，spring-boot中配置如下: spring.rabbitmq.publisher-confirms = true
   */
  @Override
  public void confirm(CorrelationData
      correlationData,
      boolean ack,
      String cause) {

  }

  /**
   * 通过实现ReturnCallback接口，如果消息从交换器发送到对应队列失败时触发（比如根据发送消息时指定的routingKey找不到队列时会触发）
   *
   * 使用该功能需要开启确认，spring-boot中配置如下: spring.rabbitmq.publisher-returns = true
   */
  @Override
  public void returnedMessage(Message message,
      int replyCode,
      String replyText,
      String exchange,
      String routingKey) {

  }
}
