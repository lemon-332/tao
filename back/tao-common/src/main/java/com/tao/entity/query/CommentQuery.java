package com.tao.entity.query;

import java.util.Date;

public class CommentQuery extends BaseParam {
    /**
     * 评论Id
     */
    public String commentId;

    public String commentIdFuzzy;
    /**
     * 商品id
     */
    public String godId;

    public String godIdFuzzy;
    /**
     * 用户Id
     */
    public String userId;

    public String userIdFuzzy;
    /**
     * 评论内容
     */
    public String commentDesc;

    public String commentDescFuzzy;
    /**
     * 评论的star个数
     */
    public Integer commentStar;
    /**
     * 评论出现时间
     */
    public Date startTime;

    public String startTimeStart;

    public String startTimeEnd;

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

    public String getCommentIdFuzzy() {
        return commentIdFuzzy;
    }

    public void setCommentIdFuzzy(String commentIdFuzzy) {
        this.commentIdFuzzy = commentIdFuzzy;
    }

    public String getGodIdFuzzy() {
        return godIdFuzzy;
    }

    public void setGodIdFuzzy(String godIdFuzzy) {
        this.godIdFuzzy = godIdFuzzy;
    }

    public String getUserIdFuzzy() {
        return userIdFuzzy;
    }

    public void setUserIdFuzzy(String userIdFuzzy) {
        this.userIdFuzzy = userIdFuzzy;
    }

    public String getCommentDescFuzzy() {
        return commentDescFuzzy;
    }

    public void setCommentDescFuzzy(String commentDescFuzzy) {
        this.commentDescFuzzy = commentDescFuzzy;
    }

    public String getStartTimeStart() {
        return startTimeStart;
    }

    public void setStartTimeStart(String startTimeStart) {
        this.startTimeStart = startTimeStart;
    }

    public String getStartTimeEnd() {
        return startTimeEnd;
    }

    public void setStartTimeEnd(String startTimeEnd) {
        this.startTimeEnd = startTimeEnd;
    }

}