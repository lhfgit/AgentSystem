package org.agent.pojo;

import java.util.Date;

public class Function extends Base {
	private String functionCode;
	private String functionName;
	private Date creationTime;
	private String createdBy;
	private Date lastUpdateTime;
	private String funcUrl;
	private Integer isStart;
	private Integer parentId;
	private boolean isCheck;

	@Override
	public String toString() {
		return "Function [functionCode=" + functionCode + ", functionName=" + functionName + ", creationTime="
				+ creationTime + ", createdBy=" + createdBy + ", lastUpdateTime=" + lastUpdateTime + ", funcUrl="
				+ funcUrl + ", isStart=" + isStart + ", parentId=" + parentId + ", getId()=" + getId() + "]";
	}

	public String getFunctionCode() {
		return functionCode;
	}

	public void setFunctionCode(String functionCode) {
		this.functionCode = functionCode;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
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

	public String getFuncUrl() {
		return funcUrl;
	}

	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}

	public Integer getIsStart() {
		return isStart;
	}

	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}

}
