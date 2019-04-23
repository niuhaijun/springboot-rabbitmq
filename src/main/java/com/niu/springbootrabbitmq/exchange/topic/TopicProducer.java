package com.niu.springbootrabbitmq.exchange.topic;

import static com.niu.springbootrabbitmq.exchange.topic.TopicExchangeConfig.TOPIC_EXCHANGE;

import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class TopicProducer {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  private RabbitTemplate rabbitTemplate;

  @Autowired
  public TopicProducer(RabbitTemplate rabbitTemplate) {

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

    rabbitTemplate.convertAndSend(TOPIC_EXCHANGE, routingKey, content,
        correlationData);
  }
}
