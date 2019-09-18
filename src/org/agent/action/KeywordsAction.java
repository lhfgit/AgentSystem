package org.agent.action;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.agent.common.Constants;
import org.agent.common.PageSuppoert;
import org.agent.pojo.Account;
import org.agent.pojo.Custom;
import org.agent.pojo.Keywords;
import org.agent.pojo.Systemconfig;
import org.agent.pojo.User;
import org.agent.service.account.AccountService;
import org.agent.service.custom.CustomSerevice;
import org.agent.service.keywords.KeywordsService;
import org.agent.service.systemconfig.SystemconfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
//import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

@Controller
public class KeywordsAction extends BaseAction {

	@Autowired
	private KeywordsService keywordsService;
	@Autowired
	private CustomSerevice customSerevice;
	@Autowired
	private AccountService accountService;
	@Autowired
	private SystemconfigService systemconfigService;

	// 跳转到关键词申请页面
	@RequestMapping("/keyword")
	public String keyword(Map<String, Object> map, HttpSession session) {
		map.put("serviceType", Constants.serviceConfigList);
		map.put("youhuiType", Constants.youhuiConfigList);
		Account account = new Account();

		account.setUserId(((User) session.getAttribute(Constants.SESSION_USER)).getId());
		map.put("account", accountService.getAccount(account));
		return "keyword";
	}

	// 关键词是否被占用
	@ResponseBody
	@RequestMapping("/valikey")
	public String valikey(Keywords k) {
		String result = "success";
		int row = keywordsService.valikey(k);
		if (row > 0)
			result = "nosuccess";
		return result;
	}

	// 动态查询客户
	@ResponseBody
	@RequestMapping(value = "/searchcustom", produces = "text/html;charset=UTF-8")
	public String searchcustom(Custom custom) {
		List<Custom> list = customSerevice.getCustomBySearch(custom);
		return JSON.toJSONString(list);
	}

	@ResponseBody
	@RequestMapping("/account")
	public String account(Map<String, Object> map, HttpSession session) {
		Account account = new Account();

		account.setUserId(((User) session.getAttribute(Constants.SESSION_USER)).getId());
		Account account2 = accountService.getAccount(account);
		map.put("account", account2);
		return account2 == null ? "failed" : account2.getMoney().toString();
	}

	// 提交
	@ResponseBody
	@RequestMapping("/submitkeyword")
	public String submitkeyword(String p, Keywords k, HttpSession session) {
		String result = "nosuccess";
		String[] s = p.split("-");
		if (s[1].contains("id_")) {
			String[] split = StringUtils.split(s[1], "_");
			k.setServiceYears(Integer.parseInt(split[1]));
		} else
			k.setServiceYears(Integer.parseInt(s[1]));

		k.setProductType(Integer.parseInt(s[0]));

		Account account = new Account();
		account.setUserId(((User) session.getAttribute(Constants.SESSION_USER)).getId());
		Account account2 = accountService.getAccount(account);
		if (new BigDecimal(jisuanprice(p)).compareTo(account2.getMoney()) != 1)
			return "nomoney";

		User user = (User) session.getAttribute(Constants.SESSION_USER);
		Systemconfig systemconfig = new Systemconfig();
		systemconfig.setId(Integer.parseInt(s[0]));

		k.setRegDatetime(new Date());
		k.setAgentId(user.getId());
		k.setAgentName(user.getUserName());
		k.setCheckStatus(0);
		k.setIsUse(0);
		k.setIsPass(0);
		k.setPreRegFrozenMoney(
				new BigDecimal(systemconfigService.getSystemConfig(systemconfig).get(0).getConfigValue()));
		k.setPrice(new BigDecimal(jisuanprice(p)));
		boolean falg = keywordsService.txAddKeywords(user, k);
		if (!falg) {
			result = "exception";
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/jisuan")
	public String jisuan(String p) {
		String jisuanprice = jisuanprice(p);
		return jisuanprice;

	}

	/**
	 * 
	 * @return exceptiona：发生错误 其他为实际价格
	 */
	public String jisuanprice(String p) {
		String result = "exception";
		// 计算
		Integer serviceTypeId = 0;
		Integer serviceYears = 0;
		if (p != null) {
			String[] params = p.split("-");
			// 获取服务类型中的单价
			serviceTypeId = Integer.parseInt(params[0]);
			Systemconfig systemconfig = new Systemconfig();
			systemconfig.setId(serviceTypeId);
			List<Systemconfig> list = systemconfigService.getSystemConfig(systemconfig);
			BigDecimal st = new BigDecimal(list.get(0).getConfigValue());
			// 获取服务年限
			if (!params[1].contains("id_")) {
				serviceYears = Integer.parseInt(params[1]);
				BigDecimal years = new BigDecimal(serviceYears);
				result = st.multiply(years).toString();// 获得结果
			} else {
				String[] youhuiId = StringUtils.split(params[1], "_");
				systemconfig.setId(Integer.parseInt(youhuiId[1]));
				List<Systemconfig> config = systemconfigService.getSystemConfig(systemconfig);
				if (config.size() == 1) {
					serviceYears = Integer.valueOf(config.get(0).getConfigValue());// 优惠类型的价格计算年限，对应数据库configValue
					BigDecimal years = new BigDecimal(serviceYears);
					result = st.multiply(years).toString();// 获得结果
				}
			}

		}
		return result;
	}

	@RequestMapping("/keywordmanage")
	public String keywordmanage(Keywords k, HttpSession session, Map<String, Object> map) {
		if (k.getPage() == null)
			k.setPage(new PageSuppoert());
		k.setPageSize(k.getPage().getPageSize());

		k.setAgentId(((User) session.getAttribute(Constants.SESSION_USER)).getId());
		k.setStarNum((k.getPage().getPage() - 1) * k.getPageSize());
		k.getPage().setTotalCont(keywordsService.count(k));
		k.getPage().setItems(keywordsService.getKeywordsList(k));
		map.put("keywords", k);
		return "keywordmanage";
	}

	// 跳转到关键词审核页面
	@RequestMapping("/checkkeyword")
	public String checkkeyword(Keywords k, HttpSession session, Map<String, Object> map) {
		if (k.getPage() == null)
			k.setPage(new PageSuppoert());
		// k.setCustomId(((User) session.getAttribute(Constants.SESSION_USER)).getId());
		k.setPageSize(k.getPage().getPageSize());
		k.setStarNum((k.getPage().getPage() - 1) * k.getPageSize());
		k.getPage().setTotalCont(keywordsService.count(k));
		k.getPage().setItems(keywordsService.getKeywordsList(k));
		map.put("keywords", k);
		return "checkkeyword";
	}

	@ResponseBody
	@RequestMapping("/updatekeyword")
	public String updatekeyword(Keywords keywords, HttpSession session) {
		String result = "exception";
		User user = (User) session.getAttribute(Constants.SESSION_USER);
		if (keywords != null) {
			try {
				// 修改为审核中
				if (keywords.getCheckStatus() != null && keywords.getCheckStatus().equals(1)) {
					keywordsService.modifyKeywords(keywords);
					this.setLog(user, "用户进行关键词状态修改：关键词状态修改为审核中");
				} else if (keywords.getCheckStatus() != null && keywords.getCheckStatus().equals(2)) {
					keywordsService.tx_ChangeStatusToOk(keywords, user, new Date());
				} else if (keywords.getCheckStatus() != null && keywords.getCheckStatus().equals(3)) {
					keywordsService.tx_ChangeStatusToNo(keywords, user, new Date());
				} else {
					keywordsService.modifyKeywords(keywords);
					if (keywords.getIsUse().equals(1))
						this.setLog(user, "用户进行关键词状态修改：关键词状态修改为已使用");
					else
						this.setLog(user, "用户进行关键词状态修改：关键词状态修改为未使用");
				}
				result = "success";
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	@RequestMapping("/openapp")
	public String openapp(Keywords keywords, Map<String, Object> map) {
		keywords = keywordsService.getKeywordsById(keywords);
		map.put("keywords", keywords);
		Systemconfig systemconfig = new Systemconfig();
		systemconfig.setId(keywords.getProductType());

		List<Systemconfig> list = systemconfigService.getSystemConfig(systemconfig);
		map.put("systemconfig", list.get(0));
		return "openapp";
	}

	@RequestMapping("/modifyapp")
	public String modifyapp(Keywords keywords, Map<String, Object> map) {
		System.err.println(keywords);
		if (null != keywords && keywords.getAppPassword() != null && !keywords.getAppPassword().equals("")
				&& keywords.getAppUserName() != null && !keywords.getAppUserName().equals("")) {

			try {
				keywords.setLoginUrl(Constants.appMakeUrlConfig.getConfigValue());
				keywordsService.modifyKeywords(keywords);
				map.put("message", "恭喜开通App成功！");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("message", "对不起开通App失败，稍后重试");
			}
		} else
			map.put("message", "登录密码不能为空");

		return "openapp";
	}

	@RequestMapping("/xufei")
	public String keywordsXufei(Keywords keywords, HttpSession session, Map<String, Object> map) {
		keywords = keywordsService.getKeywordsById(keywords);
		map.put("keywords", keywords);
		map.put("serviceType", Constants.serviceConfigList);

		Account account = new Account();
		account.setUserId(((User) session.getAttribute(Constants.SESSION_USER)).getId());
		map.put("account", accountService.getAccount(account));

		return "xufei";
	}

	@ResponseBody
	@RequestMapping("/keywordsxufei")
	public String keywordsxufei(String p, Keywords keywords, HttpSession s) {
		String result = "sucess";
		try {
			Account account = new Account();

			account.setUserId(((User) s.getAttribute(Constants.SESSION_USER)).getId());
			account = accountService.getAccount(account);
			if (p != null && keywords != null) {
				String price = jisuanprice(p);
				if (account.getMoney() != null
						&& account.getMoney().doubleValue() >= new BigDecimal(price).doubleValue()) {
					String[] pa = StringUtils.split(p, "-");
					keywordsService.tx_keywordsXufei(keywords, pa[0], pa[1], new BigDecimal(price), new Date());
					account = accountService.getAccount(account);
					result = account.getMoney().toString();
				} else
					result = "nomoney";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = "exception";
		}
		return result;
	}

	@ResponseBody
	@RequestMapping("/deletekeyword")
	public String deletekeyword(Keywords k) {
		String result = "nomoney";
		int row = keywordsService.deleteKeywords(k);
		if (row > 0) {
			result = "success";
		}
		return result;
	}
}
