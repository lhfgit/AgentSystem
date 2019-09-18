package org.agent.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.agent.common.Constants;
import org.agent.common.PageSuppoert;
import org.agent.pojo.Area;
import org.agent.pojo.City;
import org.agent.pojo.Custom;
import org.agent.pojo.Province;
import org.agent.pojo.User;
import org.agent.service.custom.CustomSerevice;
import org.agent.service.provincandcity.ProvincAndCitySerevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
public class CustomAction extends BaseAction {
	@Autowired
	private CustomSerevice customSerevice;// 客户
	@Autowired
	private ProvincAndCitySerevice provincAndCitySerevice;

	// 代理商客户列表
	@RequestMapping("/customlist")
	public String customlist(Custom custom, Map<String, Object> map) {
		System.err.println(custom);
		if (custom == null)
			custom = new Custom();
		else if (custom.getPage() == null)
			custom.setPage(new PageSuppoert());

		custom.setPageSize(custom.getPage().getPageSize());// 页面大小保持一致
		custom.setStarNum((custom.getPage().getPage() - 1) * custom.getPageSize());// 设置查询的起始行
		custom.getPage().setTotalCont(customSerevice.count(custom));
		custom.getPage().setItems(customSerevice.getList(custom));
		map.put("custom1", custom);
		return "customlist";
	}

	// 跳转到添加代理商客户的页面
	@RequestMapping("/addcustom")
	public String addcustom(Map<String, Object> map) {
		List<Province> provinceList = provincAndCitySerevice.getProvinceList();
		// map.put("provinceList", provinceList);// 省份
		map.put("provinceList", provinceList);
		map.put("cardTypeList", Constants.cardTypeConfigList);// 证件类型
		map.put("customTypeList", Constants.customTypeConfigList);// 客户类型

		return "addcustom";
	}

	// 加载城市
	@ResponseBody
	@RequestMapping(value = "/loadcity", produces = "text/html;charset=UTF-8")
	public String loadcity(Province province, Map<String, Object> map) {
		List<City> cityList = provincAndCitySerevice.getCityList(province);
		map.put("cityList", cityList);
		return JSON.toJSONString(cityList);
	}

	// 加载地区
	@ResponseBody
	@RequestMapping(value = "/loadarea", produces = "text/html;charset=UTF-8")
	public String loadArea(City city, Map<String, Object> map) {
		List<Area> areaList = provincAndCitySerevice.getAreaList(city);
		map.put("areaList", areaList);
		return JSON.toJSONString(areaList);
	}

	// 验证客户名称是否重名
	@ResponseBody
	@RequestMapping("/isExitCustomName")
	public String isExitCustomName(Custom custom) {
		// String result="nopeat";
		return customSerevice.isExitCustomName(custom) > 0 ? "peat" : "nopeat";
		/*
		 * if(row>0) { result="peat"; }
		 */
		// return result;
	}

	// 保存代理商客户
	@RequestMapping("/addsavecustom")
	public String addsavecustom(Custom custom, HttpServletRequest request, Map<String, Object> map) {
		HttpSession session = request.getSession();
		User user = ((User) session.getAttribute(Constants.SESSION_USER));
		custom.setAgentCode(user.getUserCode());
		custom.setAgentId(user.getId());
		custom.setAgentName(user.getUserName());
		boolean flag = customSerevice.tx_addCustomContact(custom, custom.getConstants());
		if (flag) {
			// 记录日志
			this.setLog(((User) session.getAttribute(Constants.SESSION_USER)),
					"用户【" + user.getUserCode() + "】进行客户添加操作：添加客户" + custom.getCustomName());
			return "redirect:/customlist";
		} else {
			map.put("error", "网络繁忙");
			return "addcustom";
		}
	}

	@RequestMapping("/viewcustom")
	public String viewcustom(Custom custom, Map<String, Object> map) {
		custom = customSerevice.getCustomById(custom);

		map.put("custom", custom);
		System.err.println(custom);
		// System.err.println(custom.getConstants());
		return "viewcustom";
	}

	// 更新状态
	@ResponseBody
	@RequestMapping("/modifycustomstatus")
	public String modifycustomstatus(Custom custom) {
		String result = "nosuccess";
		int row = customSerevice.modifyCustomAtatus(custom);
		if (row > 0) {
			result = "success";
		}
		return result;
	}

	// 到更新页面

	@RequestMapping("/modifycustom")
	public String modifycustom(Custom custom, Map<String, Object> map) {
		custom = customSerevice.getCustomById(custom);
		List<Province> provinceList = provincAndCitySerevice.getProvinceList();
		Province province = new Province();
		province.setProvinceID(custom.getProvince());
		List<City> cityList = provincAndCitySerevice.getCityList(province);
		City city = new City();
		city.setCityID(custom.getCity());
		List<Area> areaList = provincAndCitySerevice.getAreaList(city);
		map.put("provinceList", provinceList);// 省
		map.put("cityList", cityList);
		map.put("areaList", areaList);
		map.put("cardTypeList", Constants.cardTypeConfigList);// 证件类型
		map.put("customTypeList", Constants.customTypeConfigList);// 客户类型

		map.put("custom", custom);
		return "modifycustom";
	}

	@RequestMapping("/modifysavecustom")
	public String modifysavecustom(Custom custom) {
		boolean falg = customSerevice.tx_modifyCustomContact(custom, custom.getConstants());
		if (falg)
			return "redirect:/customlist";
		else
			return "modifycustom";
	}
}
