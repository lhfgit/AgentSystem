package org.agent.pojo;

/**
 * 系统配置项（安一级编号分组，组内再编号）
 * 
 * @author lhf
 *
 */

public class Systemconfig {
	private Integer id;
	private Integer configType;// 一级类型编号
	private String configTypeName;// 类型名称
	private Integer configTypeValue;// 二级编号
	private String configValue;// 配置项的值
	private Integer isStart;// 是否启用

	public Integer getConfigType() {
		return configType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setConfigType(Integer configType) {
		this.configType = configType;
	}

	public String getConfigTypeName() {
		return configTypeName;
	}

	public void setConfigTypeName(String configTypeName) {
		this.configTypeName = configTypeName;
	}

	public Integer getConfigTypeValue() {
		return configTypeValue;
	}

	public void setConfigTypeValue(Integer configTypeValue) {
		this.configTypeValue = configTypeValue;
	}

	public String getConfigValue() {
		return configValue;
	}

	public void setConfigValue(String configValue) {
		this.configValue = configValue;
	}

	public Integer getIsStart() {
		return isStart;
	}

	public void setIsStart(Integer isStart) {
		this.isStart = isStart;
	}

	@Override
	public String toString() {
		return "Systemconfig [id=" + id + ", configType=" + configType + ", configTypeName=" + configTypeName
				+ ", configTypeValue=" + configTypeValue + ", configValue=" + configValue + ", isStart=" + isStart
				+ "]";
	}

}
