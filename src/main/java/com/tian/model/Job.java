package com.tian.model;

import java.io.Serializable;

public class Job implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String picture;


    private String direction;

    private String introduce;

    private String profession;

    private Integer threshold;

    private Integer difficulty;

    private String growthCycle;

    private String scarcityLevel;

    private Integer ageLimitone;


    private String paymentone;

    private Integer ageLimittwo;

    private String paymenttwo;

    private Integer ageLimitthree;

    private String paymentthree;

    private Integer peopleCounting;

    private long createdAt;

    private long updatedAt;

    public Job() {
    }

    public Job(Integer id, String picture, String direction, String introduce, String profession, Integer threshold, Integer difficulty, String growthCycle, String scarcityLevel, Integer ageLimitone, String paymentone, Integer ageLimittwo, String paymenttwo, Integer ageLimitthree, String paymentthree, Integer peopleCounting, long createdAt, long updatedAt) {
        this.id = id;
        this.picture = picture;
        this.direction = direction;
        this.introduce = introduce;
        this.profession = profession;
        this.threshold = threshold;
        this.difficulty = difficulty;
        this.growthCycle = growthCycle;
        this.scarcityLevel = scarcityLevel;
        this.ageLimitone = ageLimitone;
        this.paymentone = paymentone;
        this.ageLimittwo = ageLimittwo;
        this.paymenttwo = paymenttwo;
        this.ageLimitthree = ageLimitthree;
        this.paymentthree = paymentthree;
        this.peopleCounting = peopleCounting;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public Integer getThreshold() {
        return threshold;
    }

    public void setThreshold(Integer threshold) {
        this.threshold = threshold;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String getGrowthCycle() {
        return growthCycle;
    }

    public void setGrowthCycle(String growthCycle) {
        this.growthCycle = growthCycle;
    }

    public String getScarcityLevel() {
        return scarcityLevel;
    }

    public void setScarcityLevel(String scarcityLevel) {
        this.scarcityLevel = scarcityLevel;
    }

    public Integer getAgeLimitone() {
        return ageLimitone;
    }

    public void setAgeLimitone(Integer ageLimitone) {
        this.ageLimitone = ageLimitone;
    }

    public String getPaymentone() {
        return paymentone;
    }

    public void setPaymentone(String paymentone) {
        this.paymentone = paymentone;
    }

    public Integer getAgeLimittwo() {
        return ageLimittwo;
    }

    public void setAgeLimittwo(Integer ageLimittwo) {
        this.ageLimittwo = ageLimittwo;
    }

    public String getPaymenttwo() {
        return paymenttwo;
    }

    public void setPaymenttwo(String paymenttwo) {
        this.paymenttwo = paymenttwo;
    }

    public Integer getAgeLimitthree() {
        return ageLimitthree;
    }

    public void setAgeLimitthree(Integer ageLimitthree) {
        this.ageLimitthree = ageLimitthree;
    }

    public String getPaymentthree() {
        return paymentthree;
    }

    public void setPaymentthree(String paymentthree) {
        this.paymentthree = paymentthree;
    }

    public Integer getPeopleCounting() {
        return peopleCounting;
    }

    public void setPeopleCounting(Integer peopleCounting) {
        this.peopleCounting = peopleCounting;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Job{" + "id=" + id + ", picture='" + picture + '\'' + ", direction='" + direction + '\'' + ", introduce='" + introduce + '\'' + ", profession='" + profession + '\'' + ", threshold=" + threshold + ", difficulty=" + difficulty + ", growthCycle='" + growthCycle + '\'' + ", scarcityLevel='" + scarcityLevel + '\'' + ", ageLimitone=" + ageLimitone + ", paymentone='" + paymentone + '\'' + ", ageLimittwo=" + ageLimittwo + ", paymenttwo='" + paymenttwo + '\'' + ", ageLimitthree=" + ageLimitthree + ", paymentthree='" + paymentthree + '\'' + ", peopleCounting=" + peopleCounting + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + '}';
    }
}