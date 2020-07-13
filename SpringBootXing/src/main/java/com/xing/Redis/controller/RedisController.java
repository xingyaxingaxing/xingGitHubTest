//package com.xing.Redis.controller;
//
//import com.xing.Redis.service.MyConfigRedisTemplate;
//import com.xing.Redis.service.RedisKill.SecKillService;
//import io.lettuce.core.dynamic.annotation.Param;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.util.Map;
//
//@Controller
//@RequestMapping("/redis")
//public class RedisController {
//    @Autowired
//    private MyConfigRedisTemplate myConfigRedisTemplate;
//    @ResponseBody
//    @RequestMapping("/test")
//    public Map<String, Object> wer(){
//        return myConfigRedisTemplate.qwe();
//    }
//
//    @Autowired
//    private SecKillService secKillService;
//    @ResponseBody
//    @RequestMapping("/redisKill")
//    public void redisKill(@Param("product")String product){
//
//        secKillService.orderProductMockDiffUser(product);
//    }
//
//    @ResponseBody
//    @RequestMapping("/KillSearch")
//    public String KillSearch(@Param("product")String product){
//        return secKillService.querySeckillProuctInfo(product);
//    }
//}