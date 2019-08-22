package com.niu.springbootrabbitmq.config;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * RabbitMQ中的基本概念
 *  Exchange：交换器。它指定消息按什么规则，路由到哪个队列。
 *  Queue：队列。消息的载体，每个消息都会被投到一个或多个队列。
 *  Binding：绑定。它的作用就是把Exchange和Queue按照规则绑定起来。
 *  Routing Key：路由键。
 *  Binding Key：绑定键。
 *  vhost：虚拟主机。一个Broker里可以有多个vhost。用作不同用户的权限分离。
 *  Producer：消息生产者。
 *  Consumer：消息消费者。
 *  Broker：它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输。
 *  Connection：连接。客户端通过Connection连接到Rabbit MQ服务器
 *  Channel：信道。在客户端的每个连接Connection里,可建立多个channel。
 *
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


  @Value("${spring.rabbitmq.listener.concurrency}")
  private int concurrency;
  @Value("${spring.rabbitmq.listener.max-concurrency}")
  private int maxConcurrency;
  @Value("${spring.rabbitmq.listener.prefetch}")
  private int prefetch;

  @Bean
  public ConnectionFactory connectionFactory() {

//    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//    connectionFactory.setHost(host);
//    connectionFactory.setPort(port);
    CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);

    connectionFactory.setUsername(username);
    connectionFactory.setPassword(password);
    connectionFactory.setVirtualHost(virtualHost);

    connectionFactory.setPublisherConfirms(publisherConfirms);
    connectionFactory.setPublisherReturns(publisherReturns);

    return connectionFactory;
  }

//  /**
//   * ？？？
//   */
//  @Bean
//  public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
//
//    return new RabbitAdmin(connectionFactory);
//  }

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

  /**
   * 多线程消费消息
   *
   * https://blog.csdn.net/qq_40794266/article/details/86513054
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
