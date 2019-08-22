package com.niu.messageacknowledge.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author niuhaijun
 * @date 2019-01-15 16:44
 */
@Configuration
public class RabbitMQConfig {

  @Value("${spring.rabbitmq.host}")
  private String host;
  @Value("${spring.rabbitmq.port}")
  private int port;
  @Value("${spring.rabbitmq.username}")
  private String username;
  @Value("${spring.rabbitmq.password}")
  private String password;
  @Value("${spring.rabbitmq.virtual-host}")
  private String virtualHost;


  @Value("${spring.rabbitmq.publisher-confirms}")
  private Boolean publisherConfirms;
  @Value("${spring.rabbitmq.publisher-returns}")
  private Boolean publisherReturns;


  @Value("${spring.rabbitmq.listener.concurrency}")
  private Integer concurrency;
  @Value("${spring.rabbitmq.listener.max-concurrency}")
  private Integer maxConcurrency;
  @Value("${spring.rabbitmq.listener.prefetch}")
  private Integer prefetch;


  @Value("${spring.rabbitmq.listener.simple.acknowledge-mode}")
  private String acknowledgeMode;


  @Bean
  public ConnectionFactory connectionFactory() {

    CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);

    connectionFactory.setUsername(username);
    connectionFactory.setPassword(password);
    connectionFactory.setVirtualHost(virtualHost);

    // 生产者消息确认设置
    connectionFactory.setPublisherConfirms(publisherConfirms);
    connectionFactory.setPublisherReturns(publisherReturns);

    // 消费者消息确认机制
    return connectionFactory;
  }

  @Bean
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  public RabbitTemplate rabbitTemplate() {

    return new RabbitTemplate(connectionFactory());
  }

  /**
   * 消费者并发设置
   */
  @Bean("containerFactory")
  public SimpleRabbitListenerContainerFactory containerFactory(
      SimpleRabbitListenerContainerFactoryConfigurer configurer,
      ConnectionFactory connectionFactory) {

    SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory = new SimpleRabbitListenerContainerFactory();
    simpleRabbitListenerContainerFactory.setPrefetchCount(prefetch);
    simpleRabbitListenerContainerFactory.setConcurrentConsumers(concurrency);
    simpleRabbitListenerContainerFactory.setMaxConcurrentConsumers(maxConcurrency);
    configurer.configure(simpleRabbitListenerContainerFactory, connectionFactory);
    return simpleRabbitListenerContainerFactory;
  }

}
