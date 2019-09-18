package org.agent.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.agent.common.Constants;
import org.agent.pojo.Function;
import org.agent.pojo.Permission;
import org.agent.pojo.Role;
import org.agent.pojo.User;
import org.agent.service.functin.FunctionService;
import org.agent.service.permission.PermissionService;
import org.agent.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FunctionAction {
	@Autowired
	private FunctionService functionService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private PermissionService permissionService;

	@RequestMapping("/premission")
	public String roleList(Map<String, Object> map) {

		List<Role> roleList = roleService.getRoleList(new Role());
		map.put("roleList", roleList);
		return "premission1";
	}

	@RequestMapping("/functionlist/{id}")
	public String functionList(@PathVariable("id") Integer id, Map<String, Object> map) {
		List<Function> functionByParentId = functionService.getfunctionList();

		Permission permission = new Permission();
		permission.setRoleId(id);
		permission.setIsStart(1);
		// 获取指定角色的权限列表
		List<Permission> pList = permissionService.getlist(permission);
		// 绑定到指定的功能
		if (pList != null) {
			for (Permission p : pList) {
				for (Function f : functionByParentId) {
					if (p.getFunctionId().equals(f.getId())) {
						f.setCheck(true);
					}
				}
			}
		}

		map.put("funcList", functionByParentId);
		map.put("roleId", id);
		System.err.println(functionByParentId.size());
		return "functionlist";
	}

	@ResponseBody
	@RequestMapping("saverolefunc")
	public String saverolefunc(@RequestParam("checkFuncList") String checkFuncList,
			@RequestParam("roleId") Integer roleId, HttpSession session) {
		String result = "success";
		Permission pm = new Permission();
		pm.setRoleId(roleId);
		pm.setCreationTime(new Date());
		pm.setIsStart(1);

		try {
			pm.setCreatedBy(((User) session.getAttribute(Constants.SESSION_USER)).getUserCode());
			permissionService.tx_delAddpermission(pm, checkFuncList);
		} catch (Exception e) {
			// TODO: handle exception
			result = "nosuccess";
			e.printStackTrace();
		}
		return result;
	}
}
