package org.agent.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.agent.common.Constants;
import org.agent.common.MD5;
import org.agent.pojo.Account;
import org.agent.pojo.RoleFuntions;
import org.agent.pojo.User;
import org.agent.service.account.AccountService;
import org.agent.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
public class LoginAction extends BaseAction {

	@Autowired
	private UserService userService;
	@Autowired
	private AccountService accountService;

	// 登录实现
	@RequestMapping(value = "/login")
	public String Login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
		User _u = new User();
		_u.setLastLoginTime(new Date());

		_u.setId(((User) request.getSession().getAttribute(Constants.SESSION_USER)).getId());
		userService.modifyUser(_u);
		Account account = new Account();
		account.setUserId(user.getId());
		account.setUserName(user.getUserCode());
		Account account2 = accountService.getAccount(account);

		ArrayList<RoleFuntions> roleFuntionslist = Constants.MENU.get(user.getRoleId());
		session.setAttribute("roleFuntionslist", roleFuntionslist);
		request.setAttribute("account", account2);
		System.out.println(roleFuntionslist);
		// return "redirect:main";
		return "main";
	}

	// 到登录页面
	@RequestMapping(value = "/dologin")
	public String doLogin() {

		return "login";
	}

	// 登录验证
	@ResponseBody
	@RequestMapping(value = "/check")
	public String checked(User user, HttpServletRequest request) {
		String str = "failed";
		String pwd = user.getUserPassword();
		user.setUserPassword(null);
		// user.setUserPassword(MD5.MD5Encode(user.getUserPassword()));
		System.out.println(user.getUserCode());
		System.out.println(user.getUserPassword());

		User logUser = userService.getLogUser(user);
		if (logUser != null) {
			user.setUserPassword(MD5.MD5Encode(pwd));
			if (user.getUserPassword().equals(logUser.getUserPassword())) {
				str = "success";
				request.getSession().setAttribute(Constants.SESSION_USER, logUser);
				// setLog(logUser, Constants.OPERATE_INFO_USER_LOGIN_SUCCESS);// 登录成功的日志
				/*
				 * logUser.setLastLoginTime(new Date()); userService.modifyUser(logUser);
				 */
				ArrayList<RoleFuntions> roleFuntionslist = Constants.MENU.get(user.getRoleId());
				request.setAttribute("roleFuntionslist", roleFuntionslist);
				System.err.println(roleFuntionslist);
			} else {
				// setLog(logUser, Constants.OPERATE_INFO_USER_LOGIN_FAILD);// 登录失败的日志
				str = "pwderoor";
			}
		}
		System.out.println("*******" + str);
		return JSON.toJSONString(str);
	}

	// 修改密码
	@ResponseBody
	@RequestMapping(value = ("/modifypwd"))
	public String modifypwd(User user, HttpServletRequest request) {
		String result = "error";
		// 新密码和确认密码比较
		if (!user.getUserCode().equals(user.getUserName())) {
			// 新密码和确认密码不一致
			result = "2pwddif";
		} else {
			// 获取当前的登陆用户的密码
			User _user = (User) request.getSession().getAttribute(Constants.SESSION_USER);
			System.out.println(_user.getUserPassword());
			if (!MD5.MD5Encode(user.getUserPassword()).equals(_user.getUserPassword())) {// 输入的老密码和当前登陆的密码比较
				// 输入的老密码不正确
				result = "oldpwddif";
			} else {// 执行更新密码操作

				_user.setUserPassword(MD5.MD5Encode(user.getUserCode()));
				int row = userService.modifyUser(_user);
				if (row > 0) {
					result = "success";
					setLog(_user, Constants.OPERATE_INFO_USER_MODIFY_PASSWORD);
				}
			}
		}
		System.err.println("*******result:" + result);
		return JSON.toJSONString(result);
	}

	@RequestMapping("exit")
	public String exit(HttpSession session) {
		if (session.getAttribute(Constants.SESSION_USER) != null)
			setLog((User) session.getAttribute(Constants.SESSION_USER), Constants.OPERATE_INFO_USER_LOGOUT_SUCCESS);

		session.invalidate();// 清空session

		return "redirect:/dologin";
	}

	@RequestMapping("/main")
	public String main(HttpSession session, Map<String, Object> map) {
		Account account = new Account();

		account.setUserId(((User) session.getAttribute(Constants.SESSION_USER)).getId());
		account = accountService.getAccount(account);
		map.put("account", account);
		return "main";
	}

}
