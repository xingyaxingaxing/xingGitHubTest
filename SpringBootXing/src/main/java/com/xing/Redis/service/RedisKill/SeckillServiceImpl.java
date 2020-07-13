package com.xing.Redis.service.RedisKill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SeckillServiceImpl implements SecKillService {
    @Autowired
    private RedisLock redisLock;

    private static final int TIMEOUT = 10 * 1000;//超时时间
    /*
     * 国庆特价，限量1000份
     * */
    static Map<String, Integer> products;
    static Map<String, Integer> stock;
    static Map<String, String> order;

    static {
        /*
         * 模拟多个表，商品信息表，库存表，秒杀成功订单表
         * */
        products = new HashMap<>();
        stock = new HashMap<>();
        order = new HashMap<>();
        products.put("123456", 1000);
        stock.put("123456", 1000);
    }

    private String queryMap(String productId) {
        return "国庆活动，限量份"
                + products.get(productId)
                + "还剩:" + stock.get(productId) + "份" +
                order.size() + "人已购买";
    }

    @Override
    public String querySeckillProuctInfo(String productId) {
        return this.queryMap(productId);
    }

    @Override
    public void orderProductMockDiffUser(String productId) {

        long time = System.currentTimeMillis() + TIMEOUT;
        //加锁
        redisLock.lock(productId, String.valueOf(time));
        if (!redisLock.lock(productId, String.valueOf(true))) {
            throw new SellException(101, "人太多了，稍等一下");
        }
        //查询商品库存，为0择活动结束、
        int stockNum = stock.get(productId);
        if (stockNum == 0) {
            throw new SellException(100, "活动结束");
        } else {

            //下单
            order.put(KeyUtil.genUniqueKey(), productId);
            //减库存
            stockNum = stockNum - 1;
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.printStackTrace();
            }
            stock.put(productId, stockNum);
        }
        //解锁
        redisLock.nulock(productId, String.valueOf(time));
    }
}
