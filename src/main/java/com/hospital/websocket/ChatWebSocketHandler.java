package com.hospital.websocket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hospital.model.dto.ChatMessage;
import com.hospital.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 自研 WebSocket 医患聊天室处理器
 * 
 * 核心设计：
 * - 按问诊订单ID建立聊天房间
 * - 支持多房间并发通信
 * - 消息类型区分：TEXT(文本) / IMAGE(图片) / PRESCRIPTION(处方)
 * - 消息全量持久化至 MySQL
 * 
 * 降级策略：WebSocket 连接失败时，客户端降级为 HTTP 轮询
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private final ChatService chatService;
    private final ObjectMapper objectMapper;

    /** 房间ID -> WebSocket Session 映射（支持医患双人聊天室） */
    private static final Map<String, WebSocketSession> ROOMS = new ConcurrentHashMap<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        String orderId = getOrderId(session);
        ROOMS.put(orderId, session);
        log.info("WebSocket 连接建立: orderId={}, sessionId={}", orderId, session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        ChatMessage chatMessage = objectMapper.readValue(message.getPayload(), ChatMessage.class);
        String orderId = chatMessage.getOrderId();

        // Step 1: 持久化消息到数据库
        chatService.saveMessage(chatMessage);

        // Step 2: 转发给房间内对方（医/患）
        WebSocketSession target = ROOMS.get(orderId);
        if (target != null && target.isOpen() && !target.getId().equals(session.getId())) {
            target.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        ROOMS.entrySet().removeIf(e -> e.getValue().getId().equals(session.getId()));
        log.info("WebSocket 连接关闭: sessionId={}", session.getId());
    }

    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        log.error("WebSocket 传输错误: sessionId={}", session.getId(), exception);
    }

    private String getOrderId(WebSocketSession session) {
        return session.getUri().getQuery().split("orderId=")[1].split("&")[0];
    }
}
