package com.tian.model;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private String userName;

    private String userPassword;

    private String phoneNumber;

    private String email;

    private String salt;

    private Long createdAt;


    private Long updatedAt;

    public User(Integer id, String userName, String userPassword, String phoneNumber, String email, String salt, Long createdAt, Long updatedAt) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.salt = salt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt == null ? null : salt.trim();
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }

    public Long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", userName='" + userName + '\'' + ", userPassword='" + userPassword + '\'' + ", phoneNumber=" + phoneNumber + ", email='" + email + '\'' + ", salt='" + salt + '\'' + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}