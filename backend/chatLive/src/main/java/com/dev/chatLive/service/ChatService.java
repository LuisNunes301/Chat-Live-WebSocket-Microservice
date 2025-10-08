package com.dev.chatLive.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.dev.chatLive.models.ChatEntity;
import com.dev.chatLive.models.ChatMessage;
import com.dev.chatLive.repository.ChatRepository;

@Service
public class ChatService {
    private final ChatRepository chatRepository;
    private final RedisTemplate<String, ChatMessage> redisTemplate;

    public ChatService(ChatRepository chatRepository, RedisTemplate<String, ChatMessage> redisTemplate) {
        this.chatRepository = chatRepository;
        this.redisTemplate = redisTemplate;
    }

    public ChatMessage processMessage(ChatMessage message) {
        message.setTimestamp(LocalDateTime.now());

        chatRepository.save(new ChatEntity(null, message.getSender(), message.getContent(), message.getTimestamp()));

        String key = "chat:" + message.getSender();
        redisTemplate.opsForList().leftPush(key, message);
        redisTemplate.opsForList().trim(key, 0, 9);

        return message;
    }

    public List<ChatMessage> getLastMessages(String sender) {
        String key = "chat:" + sender;
        List<ChatMessage> cached = redisTemplate.opsForList().range(key, 0, 9);
        return cached != null ? cached : List.of();
    }
}
