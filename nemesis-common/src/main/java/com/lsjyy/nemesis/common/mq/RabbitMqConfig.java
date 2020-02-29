package com.lsjyy.nemesis.common.mq;

//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.BindingBuilder;
//import org.springframework.amqp.core.DirectExchange;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.config.ConfigurableBeanFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Scope;
//
///**
// * @Authoer LsjYy
// * @DATE 2020-02-09 16:58
// * @Description:
// */
//@Configuration
//public class RabbitMqConfig {
//
//
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1", 5672);
//        connectionFactory.setUsername("guest");
//        connectionFactory.setPassword("guest");
//        connectionFactory.setVirtualHost("/");
//        connectionFactory.setPublisherConfirms(true);
//        return connectionFactory;
//    }
//
//    @Bean
//    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
//    //必须是prototype类型
//    public RabbitTemplate rabbitTemplate() {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory());
//        return template;
//    }
//
//    //消息交换机,它指定消息按什么规则,路由到哪个队列。
//    public static final String EXCHANGE_A = "my-mq-exchange_A";
//    public static final String EXCHANGE_B = "my-mq-exchange_B";
//    public static final String EXCHANGE_C = "my-mq-exchange_C";
//
//    //消息的载体,每个消息都会被投到一个或多个队列。
//    public static final String QUEUE_ORDER = "QUEUE_ORDER"; //秒杀队列
//    public static final String QUEUE_LOG = "QUEUE_LOG"; //日志队列
//    public static final String QUEUE_EMAIL = "QUEUE_EMAIL"; //邮箱队列
//
//    //路由关键字,exchange根据这个关键字进行消息投递。
//    public static final String ROUTINGKEY_A = "order_routing";
//    public static final String ROUTINGKEY_B = "sys_routing";
//    public static final String ROUTINGKEY_C = "email_routing";
//
//    /*
//     * 针对消费者配置
//     * 1. 设置交换机类型
//     * 2. 将队列绑定到交换机
//     FanoutExchange: 将消息分发到所有的绑定队列，无routingkey的概念
//     HeadersExchange ：通过添加属性key-value匹配
//     DirectExchange:按照routingkey分发到指定队列
//     TopicExchange:多关键字匹配
//     */
//    @Bean
//    public DirectExchange directExchangeA() {
//        return new DirectExchange(EXCHANGE_A);
//    }
//    /*
//     * 获取队列A
//     * @return
//     */
//    @Bean
//    public Queue queueA() {
//        return new Queue(QUEUE_ORDER, true); //队列持久
//    }
//
//
//    /**
//     * 队列a绑定到路由a 路由a与交换机a
//     * @return
//     */
//    @Bean
//    public Binding bindingA() {
//        return BindingBuilder.bind(queueA()).to(directExchangeA()).with(ROUTINGKEY_A);
//    }
//
//
//    @Bean
//    public DirectExchange directExchangeB() {
//        return new DirectExchange(EXCHANGE_B);
//    }
//
//    /**
//     * 持久化队列B
//     * @return
//     */
//    @Bean
//    public Queue queueB() {
//        return new Queue(QUEUE_LOG, true); //队列持久
//    }
//
//    /**
//     * 队列B绑定 路由B 交换机B
//     * @return
//     */
//    @Bean
//    public Binding bindingB(){
//        return BindingBuilder.bind(queueB()).to(directExchangeB()).with(ROUTINGKEY_B);
//    }
//
//
//    /**
//     * 交换机c
//     * @return
//     */
//    @Bean
//    public DirectExchange directExchangeC() {
//        return new DirectExchange(EXCHANGE_C);
//    }
//
//    /**
//     * 持久化队列C
//     * @return
//     */
//    @Bean
//    public Queue queueC() {
//        return new Queue(QUEUE_EMAIL, true); //队列持久
//    }
//
//    /**
//     * 队列C绑定 路由C 交换机c
//     * @return
//     */
//    @Bean
//    public Binding bindingC(){
//        return BindingBuilder.bind(queueC()).to(directExchangeC()).with(ROUTINGKEY_C);
//    }
//
//
//    /**
//     * 匹配以topic开头的路由键routing key
//     * 交换机绑定多个队列
//     */
//
//    /*@Bean
//    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {
//        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");
//    }*/
//
//}
//