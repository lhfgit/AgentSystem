package org.agent.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.agent.common.Constants;
import org.agent.common.PageSuppoert;
import org.agent.pojo.Account;
import org.agent.pojo.AccountDetail;
import org.agent.pojo.Logs;
import org.agent.pojo.User;
import org.agent.service.account.AccountService;
import org.agent.service.accountdetail.AccountDetailService;
import org.agent.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
public class AccountAction extends BaseAction {

	@Autowired
	private AccountService accountService;
	@Autowired
	private UserService userService;
	@Autowired
	private AccountDetailService accountDetailService;

	@RequestMapping("/caiwu")
	public String doCaiwu(Map<String, Object> map) {
		// List<Systemconfig> accountConfigList = Constants.accountConfigList;
		map.put("accountConfigList", Constants.accountConfigList);
		return "caiwu";
	}

	@ResponseBody
	@RequestMapping("/searchuser")
	public String searchuser(User user) {
		if (user == null) {
			user = new User();
		}
		user.setIsStart(1);
		System.err.println(user);
		List<User> userList = userService.getuserList(user);
		if (userList == null) {
			userList = new ArrayList<User>();
		}
		// 将java对象转换为JSON字符串
		return JSON.toJSONString(userList);
		// return JSONArray.fromObject(userList).toString();
		// return null;
	}

	@ResponseBody
	@RequestMapping("/opeaccount")
	public String opeaccount(Account account, AccountDetail accountDetail, HttpSession session) {
		String result = "nosuccess";
		System.err.println(account);
		System.err.println(accountDetail);

		try {
			Account oldaccount = accountService.getAccount(account);
			if (oldaccount != null) {
				Account newAccount = account;
				// 初始化 AccountDetail流水
				accountDetail.setAccountMoney(oldaccount.getMoney().add(newAccount.getMoney()));// 流水余额：完成当前流水后的余额
				accountDetail.setMoney(newAccount.getMoney());
				accountDetail.setUserId(newAccount.getUserId());
				// accountDetail.setDatailDateTime(new Date());
				accountDetail.setdetailDateTime(new Date());
				if (accountDetail.getMemo() == null) {

					accountDetail.setMemo("");
				}
				// 初始化日志
				Logs logs = new Logs();

				logs.setUserId(((User) session.getAttribute(Constants.SESSION_USER)).getId());// 当前登录的用户id
				logs.setUserName(((User) session.getAttribute(Constants.SESSION_USER)).getUserCode());// 当前登录用户的userCode
				logs.setOperateDatetime(new Date());
				logs.setOperateInfo(logs.getUserName() + "对" + newAccount.getUserName() + "进行"
						+ accountDetail.getDetailTypeName() + "操作，金额：" + newAccount.getMoney());
				accountService.tx_operationAccount(oldaccount, newAccount, accountDetail, logs);
				result = "success";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping("/accountdetail")
	public String accountdetail(AccountDetail accountDetail, HttpSession session, Map<String, Object> map) {
		/*
		 * if (accountDetail == null) accountDetail = new AccountDetail(); if
		 * (accountDetail.getPage() == null) accountDetail.setPage(new PageSuppoert());
		 * // accountDetail.getPage().setPageSize(1);
		 * accountDetail.setPageSize(accountDetail.getPage().getPageSize());// 设置页大小
		 * accountDetail.setStarNum((accountDetail.getPage().getPage() - 1) *
		 * accountDetail.getPageSize());// 设置当前页 accountDetail.setUserId(((User)
		 * session.getAttribute(Constants.SESSION_USER)).getId());
		 * 
		 * List<AccountDetail> accountDetailList =
		 * accountDetailService.getAccountDetailList(accountDetail);
		 * 
		 * accountDetail.getPage().setTotalCont(accountDetailService.count(accountDetail
		 * )); System.err.println(accountDetailList);
		 * accountDetail.getPage().setItems(accountDetailList);
		 * System.err.println(accountDetail);
		 * System.err.println(accountDetail.getPage());
		 * 
		 * map.put("accountDetailList", accountDetailList); map.put("accountDetail",
		 * accountDetail);
		 */
		map = yfkAndAccountdetail(accountDetail, null, session, map);
		return "accountdetail";
	}

	@RequestMapping(value = { "/yfk", "/yfklist" })
	public String yfk(AccountDetail accountDetail, User u, HttpSession session, Map<String, Object> map) {
		map = yfkAndAccountdetail(accountDetail, u, session, map);
		if (u != null && u.getId() != null && !"".equals(u.getId())) {
			map.put("User", u);
			return "yfklist";
		} else {
			return "yfk";
		}
	}

	public Map<String, Object> yfkAndAccountdetail(AccountDetail accountDetail, User u, HttpSession session,
			Map<String, Object> map) {
		map.put("accountConfigList", Constants.accountConfigList);
		if (accountDetail == null) {
			accountDetail = new AccountDetail();
			if (accountDetail.getEndTime() != null) {
				DateFormat df = DateFormat.getDateInstance();
				String dfString = df.format(accountDetail.getEndTime()) + " 23:59:59";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				try {
					accountDetail.setEndTime(sdf.parse(dfString));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		if (accountDetail.getPage() == null)
			accountDetail.setPage(new PageSuppoert());
		// accountDetail.getPage().setPageSize(1);
		accountDetail.setPageSize(accountDetail.getPage().getPageSize());// 设置页大小
		accountDetail.setStarNum((accountDetail.getPage().getPage() - 1) * accountDetail.getPageSize());// 设置当前页
		if (u != null && u.getId() != null) {
			accountDetail.setUserId(u.getId());
			accountDetail.setUserName(u.getUserName());
		} else
			accountDetail.setUserId(((User) session.getAttribute(Constants.SESSION_USER)).getId());

		List<AccountDetail> accountDetailList = accountDetailService.getAccountDetailList(accountDetail);

		accountDetail.getPage().setTotalCont(accountDetailService.count(accountDetail));
		System.err.println(accountDetailList);
		accountDetail.getPage().setItems(accountDetailList);
		System.err.println(accountDetail);
		System.err.println(accountDetail.getPage());

		// map.put("accountDetailList", accountDetailList);
		map.put("accountDetail", accountDetail);

		return map;
	}
}
