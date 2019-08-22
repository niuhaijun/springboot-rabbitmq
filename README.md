# springboot整合rabbitmq
## 消息队列的基本概念介绍
### 1、Exchange
交换器。它指定消息按什么规则，路由到哪个队列。
### 2、Queue
队列。消息的载体，每个消息都会被投到一个或多个队列。
### 3、Binding
绑定。它的作用就是把Exchange和Queue按照规则绑定起来。
### 4、Routing Key
路由键。
### 5、Binding Key
绑定键
### 6、vhost
虚拟主机。一个Broker里可以有多个vhost。用作不同用户的权限分离。
### 7、Producer
消息生产者
### 8、Consumer
消息消费者
### 9、Broker
它提供一种传输服务,它的角色就是维护一条从生产者到消费者的路线，保证数据能按照指定的方式进行传输。
### 10、Connection
连接。客户端通过Connection连接到Rabbit MQ服务器。
### 11、Channel
信道。在客户端的每个连接Connection里,可建立多个channel。


## 项目模块介绍
### 1、fourexchange 
Direct Exchange交换器的用法、FanoutExchange用法、TopicExchange用法、HeadersExchange用法
## 2、multiplethread 
设置rabbitmq多线程处理消息
### 3、delayqueue 
使用rabbitmq实现延迟队列
### 4、messageacknowledge 
使用消息确认机制防止消息丢失


## 参考链接
* [Spring for RabbitMQ](https://docs.spring.io/spring-boot/docs/{bootVersion}/reference/htmlsingle/#boot-features-amqp)
* [RabbitAdmin与RabbitTemplate](https://www.jianshu.com/p/e647758a7c50)
* [消息确认](https://blog.csdn.net/qq_29663071/article/details/81559032)
* [并发消费](https://blog.csdn.net/qq_40794266/article/details/86513054)
* [定时器](https://blog.csdn.net/liboyang71/article/details/72781526)
* [消息队列基本概念](https://www.cnblogs.com/dwlsxj/p/RabbitMQ.html)
* [springboot整合rabbitmq](https://blog.csdn.net/qq_38455201/article/details/80308771)
 


## 声明交换器参数
### String name
交换器名称
### boolean durable,
是否持久化，true表示持久化，持久化可以将交换器存盘，在服务器重启的时候不会丢失相关信息
### boolean autoDelete
是否自动删除：true表示自动删除，自动删除的前提是至少有一个队列或者交换机与这个交换机绑定，之后所有与这个交换机绑定的队列或者交换器都与此解绑。
### Map<String, Object> arguments
其他参数


## 声明队列参数详解
### String name, 
队列名称
### boolean durable 
是否持久化：true表示持久化，持久化可以将队列存盘，在服务器启动的时候也不会丢失相关信息
### boolean exclusive
是否排他：true表示排他，如果一个队列被声明为排他队列，该队列仅对首次声明它的连接可见，并在连接断开时自动删除。
### boolean autoDelete
是否自动删除：true表示自动删除，自动删除的前提是至少还有消费者连接到这个队列上，以后所有与这个队列连接的消费者都断开。
### Map<String, Object> arguments
其他参数