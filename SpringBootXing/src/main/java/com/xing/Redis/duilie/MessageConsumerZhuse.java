package com.xing.Redis.duilie;

import com.xing.Redis.utils.JedisPoolUtils;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @Author: qlq
 * @Description
 * @Date: 22:34 2018/10/9
 * 消息生产者:(开启5个线程生产消息)
 * brpop和blpop实现阻塞读取(重要)
 */
public class MessageConsumerZhuse implements Runnable {
    public static final String MESSAGE_KEY = "message:queue";
    private volatile int count;
    private Jedis jedis = JedisPoolUtils.getJedis();

    public void consumerMessage() {
        List<String> brpop = jedis.brpop(0, MESSAGE_KEY);//0是timeout,返回的是一个集合，第一个是消息的key，第二个是消息的内容
        System.out.println(brpop);
    }

    @Override
    public void run() {
        while (true) {
            consumerMessage();
        }
    }

    public static void main(String[] args) {
        MessageConsumerZhuse messageConsumer = new MessageConsumerZhuse();
        Thread t1 = new Thread(messageConsumer, "thread6");
        Thread t2 = new Thread(messageConsumer, "thread7");
        t1.start();
        t2.start();
    }
}