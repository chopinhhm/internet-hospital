package com.hospital.model.dto;

import lombok.Data;

/**
 * 聊天消息 DTO
 * WebSocket 通信的消息格式
 */
@Data
public class ChatMessage {
    private String orderId;
    private String senderId;
    private String senderRole;   // patient / doctor
    private String type;        // TEXT / IMAGE / PRESCRIPTION
    private String content;
    private Long timestamp;
}
