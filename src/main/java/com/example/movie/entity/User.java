package com.example.movie.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String userTelephone;
    private String userPassword;
    private boolean userStatus;
    private LocalDateTime userAddtime;
    private LocalDateTime userLasteditTime;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userTelephone='" + userTelephone + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userStatus=" + userStatus +
                ", userAddtime=" + userAddtime +
                ", userLasteditTime=" + userLasteditTime +
                '}';
    }

    public User(Integer id, String userName, String userTelephone, String userPassword, boolean userStatus, LocalDateTime userAddtime, LocalDateTime userLasteditTime) {
        this.id = id;
        this.userName = userName;
        this.userTelephone = userTelephone;
        this.userPassword = userPassword;
        this.userStatus = userStatus;
        this.userAddtime = userAddtime;
        this.userLasteditTime = userLasteditTime;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserTelephone() {
        return userTelephone;
    }

    public void setUserTelephone(String userTelephone) {
        this.userTelephone = userTelephone;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isUserStatus() {
        return userStatus;
    }

    public void setUserStatus(boolean userStatus) {
        this.userStatus = userStatus;
    }

    public LocalDateTime getUserLasteditTime() {
        return userLasteditTime;
    }

    public void setUserLasteditTime(LocalDateTime userLasteditTime) {
        this.userLasteditTime = userLasteditTime;
    }

    public LocalDateTime getUserAddtime() {
        return userAddtime;
    }

    public void setUserAddtime(LocalDateTime userAddtime) {
        this.userAddtime = userAddtime;
    }
}
