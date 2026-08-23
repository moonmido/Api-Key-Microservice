package com.Api_Key_demo.Api_Key_demo.Services;

import com.Api_Key_demo.Api_Key_demo.Ports.RemoveApiKeyPort;
import com.Api_Key_demo.Api_Key_demo.Repositories.MyApiRepo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RemoveApiKey implements RemoveApiKeyPort {

    private final RedisTemplate<String,String> redisTemplate;
    private final MyApiRepo repo;


    public RemoveApiKey(RedisTemplate<String, String> redisTemplate, MyApiRepo repo) {
        this.redisTemplate = redisTemplate;
        this.repo = repo;
    }

    public void remove(String userId , String encodedApiKey){
         redisTemplate.delete("api_key:" + encodedApiKey);
        repo.deleteByUserId(userId);
    }

}
