package com.music.domain;

import java.io.Serializable;

public class Rank implements Serializable {

    private Integer id;

    private Integer songId;

    private Integer userId;

    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Rank{" +
                "id=" + id +
                ", songId=" + songId +
                ", userId=" + userId +
                ", score=" + score +
                '}';
    }
}