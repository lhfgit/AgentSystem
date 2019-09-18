package org.agent.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.agent.common.Constants;
import org.agent.common.PageSuppoert;
import org.agent.pojo.Logs;
import org.agent.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LogsAction extends BaseAction {
	/*
	 * @Autowired private LogsService logsService;
	 */

	@RequestMapping(value = { "/loglist", "/mylogs" })
	public String logList(User user, Date operateDatetime, HttpSession session, Map<String, Object> map) {

		Logs logs = new Logs();
		logs.setPage(new PageSuppoert());
		if (user != null && user.getId() != null) {
			logs.setUserId(user.getId());
			logs.setUserName(user.getUserCode());
		} else {

			logs.setUserId(((User) session.getAttribute(Constants.SESSION_USER)).getId());
			logs.setUserName(((User) session.getAttribute(Constants.SESSION_USER)).getUserCode());
		}

		if (operateDatetime != null) {
			String otd = new SimpleDateFormat("yyyy-MM-dd").format(operateDatetime);
			logs.setOtd(otd);
			logs.setOperateDatetime(operateDatetime);
		}

		if (user.getPage() == null) {
			user.setPage(new PageSuppoert());
		}
		logs.setStarNum((user.getPage().getPage() - 1) * user.getPage().getPageSize());
		logs.setPageSize(user.getPage().getPageSize());

		List<Logs> loglist = getLogsService().getList(logs);
		logs.getPage().setItems(loglist);
		logs.getPage().setTotalCont(this.getLogsService().count(logs));

		// map.put("page", user.getPage());
		map.put("user", user);
		map.put("logs", logs);
		// System.err.println("user.getPage()" + user.getPage());
		if (user != null && user.getId() != null) {
			return "loglist";
		} else {
			return "logs";
		}
	}
}
