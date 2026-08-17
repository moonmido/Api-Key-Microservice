package com.Api_Key_demo.Api_Key_demo.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class MyApi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String prefix;

    @Column(name = "key_hash")
    private String keyHash;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private String offer;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    public MyApi() {
    }

    public MyApi(String prefix, String keyHash, LocalDateTime createdAt, String offer, String userId, LocalDateTime endedAt) {
        this.prefix = prefix;
        this.keyHash = keyHash;
        this.createdAt = createdAt;
        this.offer = offer;
        this.userId = userId;
        this.endedAt = endedAt;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyHash() {
        return keyHash;
    }

    public void setKeyHash(String keyHash) {
        this.keyHash = keyHash;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getEndedAt() {
        return endedAt;
    }

    public void setEndedAt(LocalDateTime endedAt) {
        this.endedAt = endedAt;
    }
}
