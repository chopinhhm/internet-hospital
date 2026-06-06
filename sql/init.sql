-- 互联网医院问诊系统 数据库初始化脚本
CREATE DATABASE IF NOT EXISTS internet_hospital DEFAULT CHARACTER SET utf8mb4;
USE internet_hospital;

-- 问诊订单表
CREATE TABLE consultation_order (
    id VARCHAR(36) PRIMARY KEY,
    patient_id VARCHAR(36) NOT NULL,
    doctor_id VARCHAR(36) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    chief_complaint VARCHAR(500),
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    end_time DATETIME,
    INDEX idx_patient (patient_id),
    INDEX idx_doctor (doctor_id)
) ENGINE=InnoDB COMMENT='问诊订单表';

-- 聊天记录表
CREATE TABLE chat_record (
    id VARCHAR(36) PRIMARY KEY,
    order_id VARCHAR(36) NOT NULL,
    sender_id VARCHAR(36) NOT NULL,
    sender_role VARCHAR(20) NOT NULL,
    type VARCHAR(20) NOT NULL DEFAULT 'TEXT',
    content TEXT NOT NULL,
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP,
    INDEX idx_order (order_id)
) ENGINE=InnoDB COMMENT='聊天记录表';
