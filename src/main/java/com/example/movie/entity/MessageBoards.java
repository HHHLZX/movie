package com.example.movie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class MessageBoards {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private String content;
    private LocalDateTime createDateTime;
    private LocalDateTime deleteDateTime;

    public MessageBoards(int id, int userId, String content, LocalDateTime CreateDateTime, LocalDateTime deleteDateTime) {
        this.id = id;
        this.userId = userId;
        this.content = content;
        this.createDateTime = CreateDateTime;
        this.deleteDateTime = deleteDateTime;
    }

    public MessageBoards() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreateDateTime() {
        return createDateTime;
    }

    public void setCreateDateTime(LocalDateTime creatDateTime) {
        this.createDateTime = creatDateTime;
    }

    public LocalDateTime getDeleteDateTime() {
        return deleteDateTime;
    }

    public void setDeleteDateTime(LocalDateTime deleteDateTime) {
        this.deleteDateTime = deleteDateTime;
    }
}
