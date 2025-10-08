package com.dev.chatLive.controller;

import java.util.Map;

import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import com.dev.chatLive.models.ChatMessage;
import com.dev.chatLive.service.ChatService;

@Controller
public class ChatController {
    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/public")
    public ChatMessage sendMessage(ChatMessage message,
            @Header("simpSessionAttributes") Map<String, Object> sessionAttributes) {
        String username = (String) sessionAttributes.get("username");
        message.setSender(username);
        return chatService.processMessage(message);
    }

    @MessageMapping("/sendToUser")
    @SendToUser("/queue/messages")
    public ChatMessage sendToUser(ChatMessage message,
            @Header("simpSessionAttributes") Map<String, Object> sessionAttributes) {
        String username = (String) sessionAttributes.get("username");
        message.setSender(username);
        return chatService.processMessage(message);
    }
}
