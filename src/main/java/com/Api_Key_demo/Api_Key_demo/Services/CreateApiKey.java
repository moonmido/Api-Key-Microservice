package com.Api_Key_demo.Api_Key_demo.Services;

import com.Api_Key_demo.Api_Key_demo.Models.MyApi;
import com.Api_Key_demo.Api_Key_demo.Ports.CreateApiKeyPort;
import com.Api_Key_demo.Api_Key_demo.Repositories.MyApiRepo;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

@Service
public class CreateApiKey implements CreateApiKeyPort {

    private final MyApiRepo repo;
    private final RedisTemplate<String, String> redisTemplate;

    public CreateApiKey(
            MyApiRepo repo,
            RedisTemplate<String, String> redisTemplate
    ) {
        this.repo = repo;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public String create(String userId, String offer) {

        // Validate userId
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("userId cannot be empty");
        }

        // Validate offer
        if (offer == null || offer.isBlank()) {
            throw new IllegalArgumentException("offer cannot be empty");
        }

        String prefix;
        LocalDateTime ending;
        Duration redisTtl;

        if ("PREMIUM".equalsIgnoreCase(offer)) {

            prefix = "prem_";
            ending = LocalDateTime.now().plusDays(365);
            redisTtl = Duration.ofDays(365);

        } else if ("FREE".equalsIgnoreCase(offer)) {

            prefix = "free_";
            ending = LocalDateTime.now().plusDays(30);
            redisTtl = Duration.ofDays(30);

        } else {

            throw new IllegalArgumentException(
                    "Invalid offer. Use FREE or PREMIUM"
            );
        }

        // Generate random secret
        String secret = UUID.randomUUID().toString()
                .replace("-", "");

        // Final API key
        String apiKey = prefix + secret;

        // Base64 encode the API key
        String encodedApiKey = Base64.getEncoder()
                .encodeToString(
                        apiKey.getBytes(StandardCharsets.UTF_8)
                );

        // Save API key information in PostgreSQL
        MyApi myApi = new MyApi(
                prefix,
                encodedApiKey,
                LocalDateTime.now(),
                offer.toUpperCase(),
                userId,
                ending
        );

        repo.save(myApi);

        // Cache API key -> userId in Redis
        String redisKey = "api_key:" + apiKey;

        redisTemplate.opsForValue().set(
                redisKey,
                userId,
                redisTtl
        );

        return apiKey;
    }
}
