package org.agent.pojo;

import java.util.Date;

public class User extends Base {
	private String userCode;
	private String userName;
	private String userPassword;
	private Date creationTime;
	private Date lastLoginTime;
	private String createdBy;
	private Date lastUpdateTime;
	private Integer isStart;
	private Integer roleId;
	private Role role;

	@Override
	public String toString() {
		return "User [userCode=" + userCode + ", userName=" + userName + ", userPassword=" + userPassword
				+ ", creationTime=" + creationTime + ", lastLoginTime=" + lastLoginTime + ", createdBy=" + createdBy
				+ ", lastUpdateTime=" + lastUpdateTime + ", isStart=" + isStart + ", roleId=" + roleId + ", role="
				+ role + ", getId()=" + getId() + "]";
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Integer getIsStart() {
		return isStart;
	}

	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getUnll() {
		return null;
	}

}
