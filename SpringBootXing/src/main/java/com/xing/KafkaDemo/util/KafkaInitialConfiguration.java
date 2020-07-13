//package com.xing.KafkaDemo.util;
//
//import org.apache.kafka.clients.admin.NewTopic;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;
//
//import java.util.List;
//
//@Configuration
//public class KafkaInitialConfiguration {
//    // 创建一个名为testtopic的Topic并设置分区数为8，分区副本数为2
//    @Bean
//    public NewTopic initialTopic() {
//        return new NewTopic("testtopic", 8, (short) 2);
//    }
//
//    // 如果要修改分区数，只需修改配置值重启项目即可
//    // 修改分区数并不会导致数据的丢失，但是分区数只能增大不能减小
//    @Bean
//    public NewTopic updateTopic() {
//        return new NewTopic("testtopic", 10, (short) 2);
//    }
//
//
//
//    /**
//    新建一个 ConsumerAwareListenerErrorHandler 类型的异常处理方法
//    用@Bean注入，BeanName默认就是方法名，然后我们将这个异常处理器的BeanName 放到
//    @KafkaListener注解的errorHandler属性里面，当监听抛出异常的时候，则会自动调用异常处理器
//    */
//    // 新建一个异常处理器，用@Bean注入
//    @Bean
//    public ConsumerAwareListenerErrorHandler consumerAwareErrorHandler() {
//        return (message, exception, consumer) -> {
//            System.out.println("消费异常："+message.getPayload());
//            return null;
//        };
//    }
//    // 将这个异常处理器的BeanName放到@KafkaListener注解的errorHandler属性里面
//    @KafkaListener(topics = {"topic1"},errorHandler = "consumerAwareErrorHandler")
//    public void onMessage4(ConsumerRecord<?, ?> record) throws Exception {
//        throw new Exception("简单消费-模拟异常");
//    }
//    // 批量消费也一样，异常处理器的message.getPayload()也可以拿到各条消息的信息
//    @KafkaListener(topics = "topic1",errorHandler="consumerAwareErrorHandler")
//    public void onMessage5(List<ConsumerRecord<?, ?>> records) throws Exception {
//        System.out.println("批量消费一次...");
//        throw new Exception("批量消费-模拟异常");
//    }
//
//
//}