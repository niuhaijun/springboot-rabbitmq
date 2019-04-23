package com.niu.springbootrabbitmq.exchange.topic;


import static com.niu.springbootrabbitmq.exchange.topic.TopicExchangeConfig.TOPIC_EXCHANGE;
import static com.niu.springbootrabbitmq.exchange.topic.TopicQueueConfig.QUEUE_A;
import static com.niu.springbootrabbitmq.exchange.topic.TopicQueueConfig.QUEUE_B;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author niuhaijun
 * @date 2019-01-19 01:55
 */
@Configuration
public class TopicBindingConfig {

  public static final String ROUTING_KEY_A = "topic.exchange to topic.queueA";
  public static final String ROUTING_KEY_B = "topic.exchange to topic.queueB";

  public static final String BINDING_KEY_A = "A.*.*";
  public static final String BINDING_KEY_B = "*.B.*";
  public static final String BINDING_KEY_C = "*.*.C";


  @Bean
  public Binding directBindingOne(@Qualifier(TOPIC_EXCHANGE) TopicExchange exchange,
      @Qualifier(QUEUE_A) Queue queue) {

    return BindingBuilder.bind(queue)
        .to(exchange)
        .with(BINDING_KEY_A);
  }

  @Bean
  public Binding directBindingTwo(@Qualifier(TOPIC_EXCHANGE) TopicExchange exchange,
      @Qualifier(QUEUE_B) Queue queue) {

    return BindingBuilder.bind(queue)
        .to(exchange)
        .with(BINDING_KEY_B);
  }

  @Bean
  public Binding directBindingThree(@Qualifier(TOPIC_EXCHANGE) TopicExchange exchange,
      @Qualifier(QUEUE_B) Queue queue) {

    return BindingBuilder.bind(queue)
        .to(exchange)
        .with(BINDING_KEY_C);
  }

}
