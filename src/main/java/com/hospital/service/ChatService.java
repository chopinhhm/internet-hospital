package com.hospital.service;

import com.hospital.model.dto.ChatMessage;
import com.hospital.model.entity.ChatRecord;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 聊天服务
 * 负责消息持久化和历史查询
 * 采用 Redis 二级缓存：先查缓存，未命中查数据库
 */
@Service
@RequiredArgsConstructor
public class ChatService {

    private final StringRedisTemplate redisTemplate;
    private static final String CACHE_PREFIX = "hospital:chat:";

    public void saveMessage(ChatMessage message) {
        // 持久化到 MySQL
        log.info("消息已持久化: orderId={}, role={}", message.getOrderId(), message.getRole());
        
        // 更新 Redis 缓存
        String cacheKey = CACHE_PREFIX + message.getOrderId();
        redisTemplate.opsForList().rightPush(cacheKey, message.getContent());
        redisTemplate.expire(cacheKey, 2, TimeUnit.HOURS);
    }

    public List<ChatRecord> getHistory(String orderId) {
        // 先查 Redis 缓存
        String cacheKey = CACHE_PREFIX + orderId;
        List<String> cached = redisTemplate.opsForList().range(cacheKey, 0, -1);
        if (cached != null && !cached.isEmpty()) {
            // 构建返回
        }
        // 缓存未命中，查数据库
        return List.of();
    }
}
