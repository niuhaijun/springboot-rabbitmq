package com.niu.fourexchange.config;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
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
  @Value("${spring.rabbitmq.template.mandatory}")
  private Boolean mandatory;


  @Bean
  public ConnectionFactory connectionFactory() {

    CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);

    connectionFactory.setUsername(username);
    connectionFactory.setPassword(password);
    connectionFactory.setVirtualHost(virtualHost);

    connectionFactory.setPublisherConfirms(publisherConfirms);
    connectionFactory.setPublisherReturns(publisherReturns);

    return connectionFactory;
  }

  @Bean
  /**
   * 必须是prototype类型
   * 因为要设置回调类，所以应是prototype类型，如果是singleton类型，则回调类为最后一次设置
   */
  @Scope(SCOPE_PROTOTYPE)
  public RabbitTemplate rabbitTemplate() {

    RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory());
    rabbitTemplate.setMandatory(mandatory);
    return rabbitTemplate;
  }

}
