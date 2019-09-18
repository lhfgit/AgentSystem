package org.agent.systeminit;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.agent.common.Constants;
import org.agent.pojo.Function;
import org.agent.pojo.Permission;
import org.agent.pojo.Role;
import org.agent.pojo.RoleFuntions;
import org.agent.pojo.Systemconfig;
import org.agent.service.functin.FunctionService;
import org.agent.service.permission.PermissionService;
import org.agent.service.role.RoleService;
import org.agent.service.systemconfig.SystemconfigService;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class SystemInit implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// 1.拿到ioc容器
		System.err.println("初始化Spring webApplication Context 开始");
		Constants.ctx = WebApplicationContextUtils.getWebApplicationContext(arg0.getServletContext());
		if (null == Constants.ctx)
			System.err.println("初始化Spring webApplication Context 失败！");
		else {
			System.err.println("初始化Spring webApplication Context 成功");

			// 2.拿到SystemconfigService
			SystemconfigService systemconfigService = Constants.ctx.getBean("systemconfigServiceImpl",
					SystemconfigService.class);
			Systemconfig systemconfig = new Systemconfig();
			systemconfig.setIsStart(1);
			Constants.systemconfigList = systemconfigService.getSystemConfig(systemconfig);
			Constants.configSystemDate();
			System.err.println("------**---财务类型 *accountConfigList" + Constants.accountConfigList.size());
			System.err.println("------**---服务类型 *serviceConfigList" + Constants.serviceConfigList.size());
			System.err.println("------**---客户类型 *customTypeConfigList" + Constants.customTypeConfigList.size());
			System.err.println("------**---证件类型 *cardTypeConfigList" + Constants.cardTypeConfigList.size());
			System.err.println("------**---优惠类型 *youhuiConfigList" + Constants.youhuiConfigList.size());
			System.err.println("初始化配置选项结束！");

			System.err.println("初始化菜单项开始！");

			RoleService roleService = Constants.ctx.getBean("roleServiceImpl", RoleService.class);
			FunctionService functionService = Constants.ctx.getBean("functionServiceImpl", FunctionService.class);
			PermissionService permissionService = Constants.ctx.getBean("permissionServiceImpl",
					PermissionService.class);
			List<Role> rolelist = roleService.getRoleIdAndNameList();
			List<Function> fncList;// 表示这个角色拥有的所有功能
			List<Function> menuFunctionList = functionService.getMenuFunction();// 主菜单
			Permission permission;
			Function function;
			ArrayList<RoleFuntions> roleFunctionsList;// 每一个角色有不同的菜单
			RoleFuntions roleFuntions;
			List<Permission> Permissionlist;// 权限集合（每一个角色的所有功能）
			List<Function> subFunction;// 功能子集合（子功能各列表）
			for (Role role : rolelist) {// 角色--》权限--》功能
				fncList = new ArrayList<Function>();
				roleFunctionsList = new ArrayList<RoleFuntions>();
				permission = new Permission();
				permission.setRoleId(role.getId());
				permission.setIsStart(1);
				Permissionlist = permissionService.getlist(permission);
				for (Permission p : Permissionlist) {
					function = new Function();
					function.setId(p.getFunctionId());
					function = functionService.getfunctionById(function);
					fncList.add(function);
				}

				for (Function mf : menuFunctionList) {
					roleFuntions = new RoleFuntions();
					// 查找主菜单
					roleFuntions.setMainFunction(mf);
					subFunction = new ArrayList<Function>();
					if (fncList != null && fncList.size() > 0) {
						for (Function f : fncList) {// 查找子菜单
							if (f.getParentId().equals(mf.getId())) {
								subFunction.add(f);
							}
						}
					}
					roleFuntions.setSubFuntions(subFunction);
					roleFunctionsList.add(roleFuntions);// 组合该角色的菜单
				}
				Constants.MENU.put(role.getId(), roleFunctionsList);
			}
			System.err.println("初始化菜单项结束！");
		}
	}

}
