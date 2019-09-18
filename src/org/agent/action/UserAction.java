package org.agent.action;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.agent.common.Constants;
import org.agent.common.MD5;
import org.agent.common.PageSuppoert;
import org.agent.common.SQLTools;
import org.agent.pojo.Role;
import org.agent.pojo.User;
import org.agent.service.role.RoleService;
import org.agent.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserAction {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	@RequestMapping("/userlist")
	public String userlist(User user, Map<String, Object> map) {
		List<User> userList = null;
		if (user == null) {
			user = new User();
		}

		if (user.getUserName() != null) {
			user.setUserName(SQLTools.transfer(user.getUserName()));
		}
		if (user.getPage() == null) {
			user.setPage(new PageSuppoert());
		}
		if (user.getPage() != null) {
			user.setStarNum((user.getPage().getPage() - 1) * user.getPage().getPageSize());
			user.setPageSize(user.getPage().getPageSize());
		}
		System.err.println(user);
		System.err.println(user.getPage());
		user.getPage().setTotalCont(userService.count(user));
		userList = userService.getuserList(user);
		List<Role> roleList = roleService.getRoleList(null);
		user.getPage().setItems(userList);
		// map.put("userList", userList);
		map.put("user", user);
		map.put("roleList", roleList);
		System.err.println(user);
		System.err.println(user.getPage());
		return "userlist";

	}

	// 增加和修改用户
	@ResponseBody
	@RequestMapping("/edituser")
	public String edituser(String type, User user, HttpSession session) {
		int row = 0;
		String result = "repeat";
		// System.err.println(user);
		User _user = new User();
		_user.setUserCode(user.getUserCode());
		if (type.equals("add")) {// 添加
			if (userService.getLogUser(_user) == null) {// 登录账号不重复，进行添加
				user.setUserPassword(MD5.MD5Encode(user.getUserPassword()));// 进行密码加密
				user.setCreationTime(new Date());// 创建时间

				user.setCreatedBy(((User) session.getAttribute(Constants.SESSION_USER)).getUserCode());
				userService.tx_addUser(user);

				result = "success";// 添加成功

			}

		} else {// 修改
			_user.setIsStart(user.getIsStart());
			if (userService.getLogUser(_user) == null) {// 登录账号不重复，进行修改
				user.setUserPassword(MD5.MD5Encode(user.getUserPassword()));// 进行密码加密
				user.setLastUpdateTime(new Date());// 最后修改时间
				row = userService.modifyUser(user);
				if (row > 0) {
					result = "success";// 修改成功
				}
			}
		}

		return result;
	}

	@ResponseBody
	@RequestMapping("/deluser")
	public String deluser(User user) {
		String result = "nosuccess";
		int row = userService.deleteUser(user);
		if (row > 0) {
			result = "success";// 删除成功
		}
		return result;
	}
}
