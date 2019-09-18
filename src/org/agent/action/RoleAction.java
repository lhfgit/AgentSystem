package org.agent.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.agent.common.Constants;
import org.agent.pojo.Role;
import org.agent.pojo.User;
import org.agent.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RoleAction {
	@Autowired
	private RoleService roleService;

	@RequestMapping("/rolelist")
	public String getRoleList(HttpServletRequest request) {
		List<Role> roleList = roleService.getRoleList(new Role());

		request.setAttribute("roleList", roleList);
		System.err.println(roleList.size());
		return "rolelist";
	}

	@ResponseBody
	@RequestMapping("/editrole/{type}")
	public String editrole(@PathVariable("type") String type, Role role, HttpSession session) {
		String result = "nosuccess";
		if (roleService.isPeatRoleName(role.getRoleName(), role.getIsStart()) > 0) {// 角色名称是否重复
			result = "repeat";
		} else {
			if (type.equals("add")) {// 新增
				System.err.println(role);
				role.setCreationTime(new Date());
				User user = (User) session.getAttribute(Constants.SESSION_USER);
				role.setCreatedBy(user.getUserCode());
				// role.setCreatedBy("admin");
				int row = roleService.addRole(role);
				if (row > 0) {
					result = "success";
				}
			} else if (type.equals("modify")) {// 修改
				role.setLastUpdateTime(new Date());
				int row = roleService.modifyRole(role);
				if (row > 0) {
					result = "success";
				}

			}
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/delrole")
	public String delrole(Role role) {
		String result = "nosuccess";
		if (!(roleService.countUserByRole(role.getId()) > 0)) {
			int row = roleService.deleteRole(role);
			if (row > 0) {
				result = "success";
			} else {
				result = "failed";
			}
		}

		return result;
	}

}
