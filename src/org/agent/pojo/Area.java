package org.agent.pojo;

/**
 * 地区
 * 
 * @author lhf
 *
 */
public class Area extends Base {
	// areaID;AREA;father;
	private String areaID;
	private String area;
	private String father;

	public String getAreaID() {
		return areaID;
	}

	public void setAreaID(String areaID) {
		this.areaID = areaID;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

}
