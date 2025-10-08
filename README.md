# Chat-Live-WebSocket-Microservice

- Este projeto é um estudo técnico sobre comunicação em tempo real com WebSocket (STOMP), autenticação JWT e persistência temporária com Redis.O objetivo foi entender o fluxo completo entre frontend (React) e backend (Spring Boot) em um ambiente com mensagens bidirecionais e usuários autenticados.

### Tecnologias Utilizadas
##### Backend (Java / Spring Boot)

Spring Boot 3
Spring WebSocket (STOMP)
Spring Security com JWT
Redis (cache e histórico de mensagens)
Jackson Datatype JSR310 (para datas LocalDateTime)
Maven

#####  Frontend (React + TypeScript)

React 18
TypeScript
STOMP.js + SockJS (comunicação WebSocket)
Tailwind CSS (estilização)
Vite (build rápido)

### Funcionalidades

- Conexão WebSocket autenticada via cookie JWT
- Envio e recebimento de mensagens em tempo real
- Armazenamento temporário no Redis
- Interface React com envio por botão ou tecla Enter
- Exibição de mensagens em tempo real com atualização automática


### Tecnologias Utilizadas

#### infra
```bash
 docker-compose up -d
```
#### frontend
```bash
 cd frontend/chat-live-front/
npm install
npm run dev
```