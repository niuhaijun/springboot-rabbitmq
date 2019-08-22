package com.niu.messageacknowledge.exchange;


import java.util.UUID;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.InitializingBean;
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
@Slf4j
public class DirectProducer
    implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback,
    InitializingBean {

  @Value("${spring.rabbitmq.template.mandatory}")
  private Boolean mandatory;

  private RabbitTemplate rabbitTemplate;

  @Autowired
  public DirectProducer(RabbitTemplate rabbitTemplate) {

    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendMsg(String content, String routingKey) {

    CorrelationData correlationData = new CorrelationData(
        UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());
    log.info("DirectProducer正在发送一条消息，关联信息是 {}", correlationData);

    rabbitTemplate.convertAndSend(DirectExchangeConfig.DIRECT_EXCHANGE,
        routingKey,
        content,
        correlationData);
  }


  /**
   * 通过实现ConfirmCallBack接口，消息发送到交换器Exchange后触发回调。
   *
   * 触发回调的条件：
   *
   * 使用该功能需要开启确认，spring-boot中配置如下: spring.rabbitmq.publisher-confirms = true
   */
  @Override
  public void confirm(CorrelationData
      correlationData,
      boolean ack,
      String cause) {

    log.info("publisher-confirms回调，关联数据是 {}", correlationData);
    if (ack) {
      log.info("消息从生产者发送到了交换器");
    }
    else {
      log.error("消息从生产者没有发送到交换器, 异常原因是: {}, 需要执行一些补偿机制，例如报警或重发！！！", cause);
    }
  }

  /**
   * 通过实现ReturnCallback接口，如果消息从交换器发送到对应队列失败时触发
   *
   * 触发回调的条件（或者的关系）
   * 1、无队列与交换器绑定
   * 2、有队列与交换器绑定，但是routingKey与bindingKey无一匹配
   *
   * 使用该功能需要开启确认，spring-boot中配置如下: spring.rabbitmq.publisher-returns = true
   */
  @Override
  public void returnedMessage(Message message,
      int replyCode,
      String replyText,
      String exchange,
      String routingKey) {

    log.error("publisher-returns回调");

    log.error("消息主体 message : {}", message);
    log.error("答复码 replyCode : {}", replyCode);
    log.error("答复文本 replyText : {}", replyText);
    log.error("交换器 exchange : {}", exchange);
    log.error("路由键 routingKey : {}", routingKey);
  }

  @Override
  public void afterPropertiesSet() throws Exception {

    /**
     * 设置消息发送到交换器回调
     */
    rabbitTemplate.setConfirmCallback(this);

    /**
     * 设置消息没有匹配的队列回调（没有绑定的队列；有绑定的队列但是无匹配的队列）
     * mandatory参数：only applies if a ReturnCallback had been provided
     */
    rabbitTemplate.setReturnCallback(this);
    rabbitTemplate.setMandatory(mandatory);
  }
}
