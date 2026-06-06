# 互联网医院线上问诊系统

> 基于 Spring Boot + WebSocket 的医患实时通讯问诊系统，支持图文问诊、排班调度、订单管理

## 项目简介

为重医附院打造的互联网医院线上问诊平台，实现了医患实时通讯、排班调度、订单管理等核心功能。采用自研 WebSocket 深度业务耦合聊天室，支持多房间并发通信。

## 技术栈

| 技术 | 说明 |
|------|------|
| Spring Boot | 核心框架 |
| Spring WebSocket | 自研深度业务耦合聊天室 |
| Spring Security + JWT | 认证鉴权 |
| Redis | 二级缓存架构 |
| MySQL | 数据持久化 |
| RabbitMQ | 异步消息（消息通知、超时处理） |

## 核心亮点

- **自研 WebSocket 聊天室**：深度耦合医疗问诊业务，支持消息类型区分（文本/图片/处方）
- **WebSocket + HTTP 降级**：主协议 WebSocket，HTTP 轮询作为降级方案
- **轻量级状态机**：订单调度流程控制（待接诊 → 问诊中 → 已结束）
- **Redis 二级缓存**：会话信息缓存，减少数据库查询
- **数据闭环**：所有业务数据全量持久化至 MySQL

## 模块说明

```
internet-hospital/
├── src/main/java/com/hospital/
│   ├── HospitalApplication.java      # 启动类
│   ├── controller/
│   │   ├── ChatController.java       # 问诊聊天接口
│   │   ├── ScheduleController.java   # 排班管理
│   │   └── OrderController.java      # 订单管理
│   ├── websocket/
│   │   ├── ChatWebSocketHandler.java # WebSocket 核心处理器
│   │   └── WebSocketConfig.java     # WebSocket 配置
│   ├── service/
│   │   ├── ChatService.java          # 聊天服务
│   │   ├── ScheduleService.java     # 排班服务
│   │   └── OrderService.java         # 订单服务
│   ├── config/
│   │   ├── RedisConfig.java
│   │   └── SecurityConfig.java
│   └── model/
│       ├── entity/
│       └── dto/
```

## 快速开始

1. 执行 `sql/init.sql` 初始化数据库
2. 修改 `application.yml` 配置
3. 启动 HospitalApplication

## 许可证

MIT License
