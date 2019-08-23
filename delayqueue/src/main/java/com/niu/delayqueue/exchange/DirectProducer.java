package com.niu.delayqueue.exchange;


import java.util.UUID;
import org.springframework.amqp.core.MessageProperties;
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

  @Autowired
  public DirectProducer(RabbitTemplate rabbitTemplate) {

    this.rabbitTemplate = rabbitTemplate;
  }


  public void sendMsg(String content, String routingKey) {

    CorrelationData correlationData = new CorrelationData(
        UUID.randomUUID().toString().replaceAll("-", "").toUpperCase());

//    rabbitTemplate.convertAndSend(DirectExchangeConfig.DIRECT_EXCHANGE,
//        routingKey,
//        content,
//        correlationData);

    rabbitTemplate.convertAndSend(DirectExchangeConfig.DIRECT_EXCHANGE,
        routingKey,
        content, message -> {
          MessageProperties messageProperties = message.getMessageProperties();
          // 设置消息的过期时间(使用rabbitmq实现的延迟队列，过期时间是固定的）
//          messageProperties.setExpiration(5 * 1000 + "");
          return message;
        },
        correlationData);
  }
}
