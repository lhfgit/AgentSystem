package org.agent.pojo;

/**
 * 城市
 * 
 * @author lhf
 *
 */
public class City extends Base {
	// cityID;city;father
	private String cityID;
	private String city;
	private String father;

	public String getCityID() {
		return cityID;
	}

	public void setCityID(String cityID) {
		this.cityID = cityID;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

}
