package com.tao.entity.po;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author lemon
 * @date 2025-01-20 08:37
 * @desc 
 */
public class User implements Serializable {
	/**
	 * 用户id
	 */
	public String userId;
	/**
	 * 用户账号
	 */
	public String userName;
	/**
	 * 用户电话
	 */
	public String phone;
	/**
	 * 用户密码
	 */
	public String password;
	/**
	 * 用户名称
	 */
	public String displayName;
	/**
	 * 用户角色
	 */
	public Integer role;
	/**
	 * 注册时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date startTime;
	/**
	 * 用户状态
	 */
	public Integer status;
	/**
	 * 禁用时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date disableTime;

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

	@Override
	public String toString(){
		return "User{" +
				"userId = " + userId + 
				", userName = " + userName + 
				", phone = " + phone + 
				", password = " + password + 
				", displayName = " + displayName + 
				", role = " + role + 
				", startTime = " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startTime) + 
				", status = " + status + 
				", disableTime = " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(disableTime) + 
				'}';
	}
}