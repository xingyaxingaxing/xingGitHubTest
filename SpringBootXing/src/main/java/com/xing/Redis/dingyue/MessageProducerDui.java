package com.xing.Redis.dingyue;

import com.xing.Redis.utils.JedisPoolUtils;
import redis.clients.jedis.Jedis;

/**
 * @Author: qlq
 * @Description
 * @Date: 21:29 2018/10/9
 * 1.生产者
 */
public class MessageProducerDui extends Thread {
    public static final String CHANNEL_KEY = "channel:1";
    private volatile int count;

    public void putMessage(String message) {
        Jedis jedis = JedisPoolUtils.getJedis();
        Long publish = jedis.publish(CHANNEL_KEY, message);//返回订阅者数量
        System.out.println(Thread.currentThread().getName() + " put message,count=" + count + ",subscriberNum=" + publish);
        count++;
    }

    @Override
    public synchronized void run() {
        for (int i = 0; i < 5; i++) {
            putMessage("message" + count);
        }
    }

    public static void main(String[] args) {
        MessageProducerDui messageProducer = new MessageProducerDui();
        Thread t1 = new Thread(messageProducer, "thread1");
        Thread t2 = new Thread(messageProducer, "thread2");
        Thread t3 = new Thread(messageProducer, "thread3");
        Thread t4 = new Thread(messageProducer, "thread4");
        Thread t5 = new Thread(messageProducer, "thread5");
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}