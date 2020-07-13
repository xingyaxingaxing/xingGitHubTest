package com.xing.KafkaDemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/asd")
public class QWA {
    private static final Logger log = LoggerFactory.getLogger(QWA.class);
    @RequestMapping("/zxc")
    public void qwe(){
        for(int i=0;i<10;i++){
            log.info("这是第"+i);
        }
    }
}
