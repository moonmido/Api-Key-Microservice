package com.Api_Key_demo.Api_Key_demo.Services;

import com.Api_Key_demo.Api_Key_demo.Models.MyApi;
import com.Api_Key_demo.Api_Key_demo.Ports.GetUserByApiKeyPort;
import com.Api_Key_demo.Api_Key_demo.Repositories.MyApiRepo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

@Service
public class GetUserByApiKey implements GetUserByApiKeyPort {

    private final RedisTemplate<String, String> redisTemplate;
    private final MyApiRepo repo;

    public GetUserByApiKey(
            RedisTemplate<String, String> redisTemplate,
            MyApiRepo repo
    ) {
        this.redisTemplate = redisTemplate;
        this.repo = repo;
    }

    @Override
    public String getUserIdByApiKey(String apiKey) {

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException("apiKey is invalid");
        }

        // Encode API key exactly the same way as CreateApiKey
        String encodedApiKey = Base64.getEncoder()
                .encodeToString(
                        apiKey.getBytes(StandardCharsets.UTF_8)
                );

        // IMPORTANT: same Redis key used in CreateApiKey
        String redisKey = "api_key:" + encodedApiKey;

        try {

            // Try Redis first
            String userId = redisTemplate.opsForValue().get(redisKey);

            if (userId != null) {
                return userId;
            }

        } catch (Exception e) {
            throw e;
                }

        // Redis miss/unavailable -> PostgreSQL
        List<MyApi> results = repo.findMyApiByKeyHash(encodedApiKey);

        if (results == null || results.isEmpty()) {
            throw new IllegalArgumentException("API key not found");
        }

        return results.getLast().getUserId();
    }
}