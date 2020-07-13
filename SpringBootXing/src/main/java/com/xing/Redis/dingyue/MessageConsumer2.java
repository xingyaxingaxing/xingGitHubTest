package com.xing.Redis.dingyue;

import com.xing.Redis.utils.JedisPoolUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * @Author: qlq
 * @Description
 * @Date: 22:34 2018/10/9
 */
public class MessageConsumer2 implements Runnable {
    public static final String CHANNEL_KEY = "channel*";//频道

    public static final String EXIT_COMMAND = "exit";//结束程序的消息

    private MyJedisPubSubDing myJedisPubSub = new MyJedisPubSubDing();//处理接收消息

    public void consumerMessage() {
        Jedis jedis = JedisPoolUtils.getJedis();
        jedis.psubscribe(myJedisPubSub, CHANNEL_KEY);//第一个参数是处理接收消息，第二个参数是订阅的消息频道
    }

    @Override
    public void run() {
        while (true) {
            consumerMessage();
        }
    }

    public static void main(String[] args) {
        MessageConsumer2 messageConsumer = new MessageConsumer2();
        Thread t1 = new Thread(messageConsumer, "thread5");
        Thread t2 = new Thread(messageConsumer, "thread6");
        t1.start();
        t2.start();
    }
}

/**
 * 继承JedisPubSub，重写接收消息的方法
 */
class MyJedisPubSubDing extends JedisPubSub {
    @Override
    public void onPMessage(String pattern, String channel, String message) {
        System.out.println(Thread.currentThread().getName() + "-接收到消息:pattern=" + pattern + ",channel=" + channel + ",message=" + message);
        //接收到exit消息后退出
        if (MessageConsumer2.EXIT_COMMAND.equals(message)) {
            System.exit(0);
        }
    }
}