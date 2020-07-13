//package com.xing.KafkaDemo.util;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.annotation.PartitionOffset;
//import org.springframework.kafka.annotation.TopicPartition;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.core.ConsumerFactory;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//public class KafkaConsumer {
//    // 消费监听
//    @KafkaListener(topics = {"topic1"})
//    public void onMessage1(ConsumerRecord<?, ?> record){
//        // 消费的哪个topic、partition的消息,打印出消息内容
//        System.out.println("简单消费："+record.topic()+"-"+record.partition()+"-"+record.value());
//    }
//
//    /**
//    id:消费者ID
//    groupId:消费组ID
//    topics:监听的topic，可监听多个
//    topicPartitions:可配置更加详细的监听信息，可指定topic、parition、offset监听。
//    上面onMessage2监听的含义:监听topic1的0号分区，同时监听topic2的0号分区和topic2的1号分区里面offset从8开始的消息。
//    注意:topics和topicPartitions不能同时使用
//    */
//
//    @KafkaListener(id = "consumer1",groupId = "felix-group",topicPartitions = {
//            @TopicPartition(topic = "topic1", partitions = { "0" }),
//            @TopicPartition(topic = "topic2", partitions = "0", partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "8"))
//    })
//    public void onMessage2(ConsumerRecord<?, ?> record) {
//        System.out.println("topic:"+record.topic()+"|partition:"+record.partition()+"|offset:"+record.offset()+"|value:"+record.value());
//    }
//
//    /**
//    设置application.prpertise开启批量消费即可
//    # 设置批量消费
//    spring.kafka.listener.type=batch
//    # 批量消费每次最多消费多少条消息
//    spring.kafka.consumer.max-poll-records=50
//    接收消息时用List来接收，监听代码如下
//    */
//    @KafkaListener(id = "consumer2",groupId = "felix-group", topics = "topic1")
//    public void onMessage3(List<ConsumerRecord<?, ?>> records) {
//        System.out.println(">>>批量消费一次，records.size()="+records.size());
//        for (ConsumerRecord<?, ?> record : records) {
//            System.out.println(record.value());
//        }
//    }
//
//
//
//
//
//    /**消息过滤器可以在消息抵达consumer之前被拦截
//     * 配置消息过滤只需要为 监听器工厂 配置一个RecordFilterStrategy（消息过滤策略），
//     * 返回true的时候消息将会被抛弃，返回false时，消息能正常抵达监听容器。
//     * */
//    @Autowired
//    ConsumerFactory consumerFactory;
//    // 消息过滤器
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory filterContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
//        factory.setConsumerFactory(consumerFactory);
//        // 被过滤的消息将被丢弃
//        factory.setAckDiscarded(true);
//        // 消息过滤策略
//        factory.setRecordFilterStrategy(consumerRecord -> {
//            if (Integer.parseInt(consumerRecord.value().toString()) % 2 == 0) {
//                return false;
//            }
//            //返回true消息则被过滤
//            return true;
//        });
//        return factory;
//    }
//    // 消息过滤监听
//    @KafkaListener(topics = {"topic1"},containerFactory = "filterContainerFactory")
//    public void onMessage6(ConsumerRecord<?, ?> record) {
//        System.out.println(record.value());
//    }
//
//
//
//
//
//}
