package com.tao.entity.query;

import java.util.Date;

public class UserQuery extends BaseParam {
    /**
     * 用户id
     */
    public String userId;

    public String userIdFuzzy;
    /**
     * 用户账号
     */
    public String userName;

    public String userNameFuzzy;
    /**
     * 用户电话
     */
    public String phone;

    public String phoneFuzzy;
    /**
     * 用户密码
     */
    public String password;

    public String passwordFuzzy;
    /**
     * 用户名称
     */
    public String displayName;

    public String displayNameFuzzy;
    /**
     * 用户角色
     */
    public Integer role;
    /**
     * 注册时间
     */
    public Date startTime;

    public String startTimeStart;

    public String startTimeEnd;
    /**
     * 用户状态
     */
    public Integer status;
    /**
     * 禁用时间
     */
    public Date disableTime;

    public String disableTimeStart;

    public String disableTimeEnd;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getDisableTime() {
        return disableTime;
    }

    public void setDisableTime(Date disableTime) {
        this.disableTime = disableTime;
    }

    public String getUserIdFuzzy() {
        return userIdFuzzy;
    }

    public void setUserIdFuzzy(String userIdFuzzy) {
        this.userIdFuzzy = userIdFuzzy;
    }

    public String getUserNameFuzzy() {
        return userNameFuzzy;
    }

    public void setUserNameFuzzy(String userNameFuzzy) {
        this.userNameFuzzy = userNameFuzzy;
    }

    public String getPhoneFuzzy() {
        return phoneFuzzy;
    }

    public void setPhoneFuzzy(String phoneFuzzy) {
        this.phoneFuzzy = phoneFuzzy;
    }

    public String getPasswordFuzzy() {
        return passwordFuzzy;
    }

    public void setPasswordFuzzy(String passwordFuzzy) {
        this.passwordFuzzy = passwordFuzzy;
    }

    public String getDisplayNameFuzzy() {
        return displayNameFuzzy;
    }

    public void setDisplayNameFuzzy(String displayNameFuzzy) {
        this.displayNameFuzzy = displayNameFuzzy;
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

    public String getDisableTimeStart() {
        return disableTimeStart;
    }

    public void setDisableTimeStart(String disableTimeStart) {
        this.disableTimeStart = disableTimeStart;
    }

    public String getDisableTimeEnd() {
        return disableTimeEnd;
    }

    public void setDisableTimeEnd(String disableTimeEnd) {
        this.disableTimeEnd = disableTimeEnd;
    }
	
}