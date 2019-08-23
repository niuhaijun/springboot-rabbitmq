package com.niu.messageacknowledge.config;


import com.niu.messageacknowledge.exchange.DirectQueueConfig;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author niuhaijun
 * @date 2019-08-23 01:22
 */
@Configuration
@Slf4j
public class DirectReceiver_C {

  @Value("${spring.rabbitmq.listener.concurrency}")
  private Integer concurrency;
  @Value("${spring.rabbitmq.listener.max-concurrency}")
  private Integer maxConcurrency;
  @Value("${spring.rabbitmq.listener.prefetch}")
  private Integer prefetch;

  @Value("${spring.rabbitmq.listener.simple.acknowledge-mode}")
  private String acknowledgeMode;

  /**
   * 消费者
   */
  @Bean
  public SimpleMessageListenerContainer messageContainer(ConnectionFactory connectionFactory,
      @Qualifier(DirectQueueConfig.QUEUE_A) Queue queue) {

    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(
        connectionFactory);
    container.setQueues(queue);
    container.setExposeListenerChannel(true);
    container.setMaxConcurrentConsumers(maxConcurrency);
    container.setConcurrentConsumers(concurrency);
    container.setAcknowledgeMode(AcknowledgeMode.valueOf(acknowledgeMode));
    container.setMessageListener(new ChannelAwareMessageListener() {
      @Override
      public void onMessage(Message message, Channel channel) throws Exception {

        byte[] body = message.getBody();
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
          //业务逻辑
          log.info("消费 receive msg : " + new String(body));
          // 消息的标识，false只确认当前一个消息收到，true确认所有consumer获得的消息
          // channel.basicNack(deliveryTag, false, true);
          channel.basicAck(deliveryTag, false); //手动确认确认消息成功消费
        }
        catch (Exception e) {
          log.info("消费失败: " + new String(body));
          // ack返回false，并重新回到队列
          try {
            // 单个拒绝
            channel.basicReject(deliveryTag, true);
            // 批量拒绝
//            channel.basicNack(deliveryTag, false, true);
          }
          catch (IOException e1) {
            log.error("发生了异常, 异常信息如下{}", e.getMessage());
          }
        }
      }
    });
    return container;
  }

}
