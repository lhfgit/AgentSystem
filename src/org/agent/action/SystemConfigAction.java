package org.agent.action;

import java.util.List;
import java.util.Map;

import org.agent.common.Constants;
import org.agent.pojo.Systemconfig;
import org.agent.service.systemconfig.SystemconfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SystemConfigAction {
	@Autowired
	private SystemconfigService systemconfigService;

	@RequestMapping(value = "/config/{configType}")
	public String caiwuType(@PathVariable("configType") Integer configType, Map<String, Object> map, Model model) {
		Systemconfig _systemconfig = new Systemconfig();
		_systemconfig.setConfigType(configType);
		List<Systemconfig> systemConfigs = systemconfigService.getSystemConfig(_systemconfig);
		if (configType == 3 || configType == 4) {
			Systemconfig systemconfig = systemConfigs.get(0);
			System.err.println("systemconfig**：" + systemconfig);
			map.put("systemconfig", systemconfig);
		}
		model.addAttribute("systemConfigs", systemConfigs);
		System.err.println("systemConfigs长度：" + systemConfigs.size());
		map.put("configType", configType);

		Constants.configSystemDate();
		return "systemconfig";
	}

	// 是否重复
	@ResponseBody
	@RequestMapping("/isPeatConfig")
	public String isPeatconfig(Systemconfig systemconfig) {
		String result = "nopeat";
		if (systemconfigService.isPeatConfig(systemconfig) > 0) {
			result = "peat";
		}
		System.err.println("/isPeatConfig*result" + result);
		// return JSON.toJSONString(result);
		return result;
	}

	// 更新
	@ResponseBody
	@RequestMapping("/modifyconfig")
	public String modifyConfig(Systemconfig systemconfig) {
		System.err.println("modifyConfig");
		String result = "nosuccess";
		/*
		 * if (systemconfigService.isPeatConfig(systemconfig) > 0) { result = "peat"; }
		 * else { System.out.println(systemconfig.getConfigType());
		 * System.out.println(systemconfig.getConfigTypeName());
		 * System.out.println(systemconfig.getId()); int row =
		 * systemconfigService.modifySystemConfigIsStart(systemconfig); if (row > 0) {
		 * result = "success"; }
		 * 
		 * }
		 */
		int row = systemconfigService.modifySystemConfigIsStart(systemconfig);
		if (row > 0) {
			result = "success";
		}

		return result;
	}

	// 删除
	@ResponseBody
	@RequestMapping("/deleteconfig")
	public String deleteconfig(Systemconfig systemconfig) {
		String result = "nosuccess";
		int row = systemconfigService.deleteSystemConfig(systemconfig);
		if (row > 0) {
			result = "success";
		}
		return result;
	}

	// 添加
	@ResponseBody
	@RequestMapping("/addconfig")
	public String addconfig(Systemconfig systemconfig) {
		System.err.println(systemconfig);
		String result = "nosuccess";
		int max = systemconfigService.maxTypeValueByType(systemconfig.getConfigType());
		systemconfig.setConfigTypeValue(max + 1);
		System.err.println(systemconfig);
		int row = systemconfigService.addSystemConfigIsStart(systemconfig);
		if (row > 0) {
			result = "success";
		}
		return result;
	}
}
