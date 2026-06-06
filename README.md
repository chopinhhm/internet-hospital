<div align="center">

<img src="https://capsule-render.vercel.app/api?type=waving&color=0D1117&height=150&section=header&text=%E4%BA%92%E8%81%94%E7%BD%91%E5%8C%BB%E9%99%A2%E9%97%AE%E8%AF%8A%E7%B3%BB%E7%BB%9F&fontSize=36&fontColor=58A6FF&animation=fadeIn" />

[![WebSocket](https://img.shields.io/badge/WebSocket-010101?style=flat-square)]()
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white)]()
[![Redis](https://img.shields.io/badge/Redis-DC382D?style=flat-square&logo=redis&logoColor=white)]()
[![JWT](https://img.shields.io/badge/JWT-black?style=flat-square)]()

</div>

---

## ✨ Features

- 🔌 **自研 WebSocket 聊天室** — 深度耦合医疗业务，多房间并发通信
- 🔄 **HTTP 降级方案** — WebSocket 主协议 + 轮询兜底
- ⚙️ **状态机订单调度** — PENDING → ACCEPTED → IN_PROGRESS → COMPLETED
- 💾 **二级缓存加速** — Redis 会话缓存 + MySQL 全量持久化
- 🔒 **JWT 安全认证** — Spring Security + Token 鉴权

---

## 🏗️ System Design

```
┌──────────┐     WebSocket      ┌──────────────┐
│  Patient │◄──────────────────►│  Chat Room   │
│  Client  │     HTTP Fallback  │  (Handler)   │
└──────────┘                    └──────┬───────┘
                                        │
                               ┌────────▼────────┐
                               │  Order Service   │
                               │  (State Machine) │
                               └────────┬────────┘
                                        │
                              ┌─────────┼─────────┐
                              ▼         ▼         ▼
                           ┌──────┐ ┌──────┐ ┌────────┐
                           │ Redis│ │ MySQL│ │RabbitMQ│
                           │Cache │ │ DB   │ │Notify  │
                           └──────┘ └──────┘ └────────┘
```

---

## 🚀 Quick Start

\`\`\`bash
git clone https://github.com/chopinhhm/internet-hospital.git
cd internet-hospital
mvn spring-boot:run
\`\`\`

---

## 📄 License

MIT © [chopinhhm](https://github.com/chopinhhm)
