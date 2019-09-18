package org.agent.service.provincandcity;

import java.util.List;

import org.agent.pojo.Area;
import org.agent.pojo.City;
import org.agent.pojo.Province;

public interface ProvincAndCitySerevice {
	/**
	 * 获取省份
	 * 
	 * @return
	 */
	public List<Province> getProvinceList();

	/**
	 * 根据省份获取城市
	 * 
	 * @param province
	 *            省份
	 * @return
	 */
	public List<City> getCityList(Province province);

	/**
	 * 根据城市获取地区
	 * 
	 * @param city
	 * @return
	 */
	public List<Area> getAreaList(City city);
}
