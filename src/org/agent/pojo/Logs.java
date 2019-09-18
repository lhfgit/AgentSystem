package org.agent.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Logs extends Base {
	// id;
	private Integer userId;
	private String userName;
	private String operateInfo;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date operateDatetime;
	private String otd;// yyyy-MM-dd

	@Override
	public String toString() {
		return "Logs [userId=" + userId + ", userName=" + userName + ", operateInfo=" + operateInfo
				+ ", operateDatetime=" + operateDatetime + ", otd=" + otd + ", getId()=" + getId() + "]";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOperateInfo() {
		return operateInfo;
	}

	public void setOperateInfo(String operateInfo) {
		this.operateInfo = operateInfo;
	}

	public Date getOperateDatetime() {
		return operateDatetime;
	}

	public void setOperateDatetime(Date operateDatetime) {
		this.operateDatetime = operateDatetime;
	}

	public String getOtd() {
		return otd;
	}

	public void setOtd(String otd) {
		this.otd = otd;
	}

}
