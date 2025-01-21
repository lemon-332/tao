package com.tao.entity.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lemon
 * @date 2025-01-20 20:21
 * @desc
 */
public class Comment implements Serializable {
    /**
     * 评论Id
     */
    public String commentId;
    /**
     * 商品id
     */
    public String godId;
    /**
     * 用户Id
     */
    public String userId;
    /**
     * 评论内容
     */
    public String commentDesc;
    /**
     * 评论的star个数
     */
    public Integer commentStar;
    /**
     * 评论出现时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    public Date startTime;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getGodId() {
        return godId;
    }

    public void setGodId(String godId) {
        this.godId = godId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentDesc() {
        return commentDesc;
    }

    public void setCommentDesc(String commentDesc) {
        this.commentDesc = commentDesc;
    }

    public Integer getCommentStar() {
        return commentStar;
    }

    public void setCommentStar(Integer commentStar) {
        this.commentStar = commentStar;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId = " + commentId +
                ", godId = " + godId +
                ", userId = " + userId +
                ", commentDesc = " + commentDesc +
                ", commentStar = " + commentStar +
                ", startTime = " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime) +
                '}';
    }
}