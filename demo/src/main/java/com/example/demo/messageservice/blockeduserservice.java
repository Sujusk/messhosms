package com.example.demo.messageservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class blockeduserservice {

@Autowired
    private RedisTemplate redisTemplate;

public boolean blockeduserservice(String userid){
    redisTemplate.opsForValue().set("1111111111","true");
    if(redisTemplate.opsForValue().get(userid).equals("true")){
        return true;
    }else{
        return false;
    }

}

}
