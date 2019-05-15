package com.tian.model;

import java.io.Serializable;

public class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;


    private String name;

    private String title;

    private String introduce;

    private Integer payment;


    private String photo;

    private Long createdAt;


    private Long updatedAt;

    public Student(Integer id,  String name, String title, String introduce, Integer payment, String photo, Long createdAt, Long updatedAt) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.introduce = introduce;
        this.payment = payment;
        this.photo = photo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Student() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Integer getPayment() {
        return payment;
    }

    public void setPayment(Integer payment) {
        this.payment = payment;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
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
        return "Student{" + "id=" + id + ", name='" + name + '\'' + ", title='" + title + '\'' + ", introduce='" + introduce + '\'' + ", payment=" + payment + ", photo='" + photo + '\'' + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}