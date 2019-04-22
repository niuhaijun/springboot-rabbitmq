package com.niu.springbootrabbitmq.exchange.direct;

import java.util.HashMap;
import java.util.Map;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author niuhaijun
 * @date 2019-01-22 23:26
 */
@Configuration
public class DirectExchangeConfig {

  public static final String DIRECT_EXCHANGE = "direct.exchange";
  public boolean durable = false;
  public boolean autoDelete = true;

  @Bean(name = DIRECT_EXCHANGE)
  public DirectExchange directExchange() {

    /**
     * name：交换器的名称
     * durable：是否持久化：true表示持久化，持久化可以将交换器存盘，在服务器重启的时候不会丢失相关信息
     * autoDelete：是否自动删除：true表示自动删除，自动删除的前提是至少有一个队列或者交换机与这个交换机绑定，之后所有与这个交换机绑定的队列或者交换器都与此解绑。
     * arguments：交换机的其他信息
     */
    Map<String, Object> arguments = new HashMap<>();
    arguments.put("交换器类型", "直连交换器");


    return new DirectExchange(DIRECT_EXCHANGE, durable, autoDelete, null);
  }

}
