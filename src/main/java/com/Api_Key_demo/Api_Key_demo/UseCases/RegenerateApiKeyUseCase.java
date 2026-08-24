package com.Api_Key_demo.Api_Key_demo.UseCases;

import com.Api_Key_demo.Api_Key_demo.Models.MyApi;
import com.Api_Key_demo.Api_Key_demo.Ports.CreateApiKeyPort;
import com.Api_Key_demo.Api_Key_demo.Repositories.MyApiRepo;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;


import java.util.NoSuchElementException;

@Component
public class RegenerateApiKeyUseCase {

    private final MyApiRepo repo;
    private final CreateApiKeyPort createApiKeyPort;

    public RegenerateApiKeyUseCase(MyApiRepo repo, CreateApiKeyPort createApiKeyPort) {
        this.repo = repo;
        this.createApiKeyPort = createApiKeyPort;
    }

    public String regenerate(String userId) {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("Invalid userId");
        }

        try {
            List<MyApi> byUserId = repo.findByUserId(userId);

            // Fix 1: Handle empty list check before getting the last element
            if (byUserId.isEmpty()) {
                throw new NoSuchElementException("No existing API keys found for this user");
            }

            MyApi last = byUserId.getLast();

            // Fix 2: Correct the expired logic (Is the end date BEFORE now?)
            if (last.getEndedAt().isBefore(LocalDateTime.now())) {
                return createApiKeyPort.create(userId, last.getOffer());
            }

            return "Still Works";

        } catch (NoSuchElementException e) {
            throw e; // Re-throw specific errors cleanly
        } catch (Exception e) {
            throw new RuntimeException("Failed to regenerate API key", e);
        }
    }
}

