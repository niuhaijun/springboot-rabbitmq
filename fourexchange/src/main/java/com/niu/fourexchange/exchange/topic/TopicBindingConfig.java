package com.niu.fourexchange.exchange.topic;


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

  public static final String ROUTING_KEY_A = "A.A.TopicExchange to QUEUE_A";
  public static final String ROUTING_KEY_B = "B.B.TopicExchange to QUEUE_B";
  public static final String ROUTING_KEY_C = "A.B.TopicExchange to QUEUE_A, QUEUE_B";


  public static final String BINDING_KEY_A = "A.*.*";
  public static final String BINDING_KEY_B = "*.B.*";


  @Bean
  public Binding topicBindingOne(
      @Qualifier(TopicExchangeConfig.TOPIC_EXCHANGE) TopicExchange exchange,
      @Qualifier(TopicQueueConfig.QUEUE_A) Queue queue) {

    return BindingBuilder.bind(queue)
        .to(exchange)
        .with(BINDING_KEY_A);
  }

  @Bean
  public Binding topicRBindingTwo(
      @Qualifier(TopicExchangeConfig.TOPIC_EXCHANGE) TopicExchange exchange,
      @Qualifier(TopicQueueConfig.QUEUE_B) Queue queue) {

    return BindingBuilder.bind(queue)
        .to(exchange)
        .with(BINDING_KEY_B);
  }

}
