package org.agent.pojo;

import java.util.Date;

public class Permission extends Base {
	private Integer roleId;
	private Integer functionId;
	private Date creationTime;
	private String createdBy;
	private Date lastUpdateTime;
	private Integer isStart;

	@Override
	public String toString() {
		return "Permission [roleId=" + roleId + ", functionId=" + functionId + ", creationTime=" + creationTime
				+ ", createdBy=" + createdBy + ", lastUpdateTime=" + lastUpdateTime + ", isStart=" + isStart
				+ ", getId()=" + getId() + "]";
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getFunctionId() {
		return functionId;
	}

	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
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

}
