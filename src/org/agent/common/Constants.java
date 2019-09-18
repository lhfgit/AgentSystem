package org.agent.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.agent.pojo.RoleFuntions;
import org.agent.pojo.Systemconfig;
import org.springframework.web.context.WebApplicationContext;

public class Constants {

	public static WebApplicationContext ctx;// ioc 容器
	public static final String SESSION_USER = "userSession";// 当前登录的用户
	/*********** 系统日志 *************/
	public static final String OPERATE_INFO_USER_LOGIN_SUCCESS = "用户进行登录操作成功";
	public static final String OPERATE_INFO_USER_LOGIN_FAILD = "用户进行登录操作失败";

	public static final String OPERATE_INFO_USER_LOGOUT_SUCCESS = "用户进行注销操作成功";
	public static final String OPERATE_INFO_USER_MODIFY_PASSWORD = "用户进行修改密码成功";
	/************** 菜单 ************/
	public static HashMap<Integer, ArrayList<RoleFuntions>> MENU = new HashMap<Integer, ArrayList<RoleFuntions>>();
	/********* 系统配置项 *************/
	public static List<Systemconfig> systemconfigList;// 所有的配置项
	// 账务类型 1
	public static List<Systemconfig> accountConfigList = new ArrayList<Systemconfig>();
	// 服务类型 2
	public static List<Systemconfig> serviceConfigList = new ArrayList<Systemconfig>();
	// 服务年限 3 最大的服务年限
	public static Systemconfig maxServiceYearsconfig;
	// APP 地址4
	public static Systemconfig appMakeUrlConfig;
	// 客户类型 5
	public static List<Systemconfig> customTypeConfigList = new ArrayList<Systemconfig>();
	// 证件类型 6
	public static List<Systemconfig> cardTypeConfigList = new ArrayList<Systemconfig>();
	// 优惠类型 7
	public static List<Systemconfig> youhuiConfigList = new ArrayList<Systemconfig>();

	// 遍历所有配置项，并按不同类型归类
	public static void configSystemDate() {
		// 防止重复添加
		accountConfigList.clear();
		accountConfigList.clear();
		customTypeConfigList.clear();
		cardTypeConfigList.clear();
		youhuiConfigList.clear();
		for (Systemconfig con : systemconfigList) {
			switch (con.getConfigType()) {
			case 1:
				accountConfigList.add(con);
				break;
			case 2:
				serviceConfigList.add(con);
				break;
			case 3:
				maxServiceYearsconfig = con;
				break;
			case 4:
				appMakeUrlConfig = con;
				break;
			case 5:
				customTypeConfigList.add(con);
				break;
			case 6:
				cardTypeConfigList.add(con);
				break;
			case 7:
				youhuiConfigList.add(con);
				break;

			}
		}
	}
}
