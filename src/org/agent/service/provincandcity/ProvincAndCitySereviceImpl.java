package org.agent.service.provincandcity;

import java.util.List;

import org.agent.dao.provincandcity.ProvinceAndCityMapper;
import org.agent.pojo.Area;
import org.agent.pojo.City;
import org.agent.pojo.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvincAndCitySereviceImpl implements ProvincAndCitySerevice {
	@Autowired
	private ProvinceAndCityMapper provinceAndCityMapper;

	@Override
	public List<Province> getProvinceList() {
		// TODO Auto-generated method stub
		return provinceAndCityMapper.getProvinceList();
	}

	@Override
	public List<City> getCityList(Province province) {
		// TODO Auto-generated method stub
		return provinceAndCityMapper.getCityList(province);
	}

	@Override
	public List<Area> getAreaList(City city) {
		// TODO Auto-generated method stub
		return provinceAndCityMapper.getAreaList(city);
	}

}
