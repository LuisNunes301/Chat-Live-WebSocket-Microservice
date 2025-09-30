package com.dev.chatLive.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class messages {

    @Id
    private String id;
    private String content;
    private String sender;

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
