package org.agent.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.agent.pojo.Logs;
import org.agent.pojo.User;
import org.agent.service.logs.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

@Controller
public class BaseAction {
	@Autowired
	private LogsService logsService;
	// private PageSuppoert page;

	/*
	 * public BaseAction() { super(); if (page == null) { page = new PageSuppoert();
	 * } }
	 */

	public void setLog(User user, String operateInfo) {
		Logs logs = new Logs();
		logs.setUserId(user.getId());
		logs.setUserName(user.getUserCode());
		logs.setOperateInfo(operateInfo);
		logs.setOperateDatetime(new Date());
		logsService.addLogs(logs);
	}

	@InitBinder
	public void initBinder(ServletRequestDataBinder binder) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}

	public LogsService getLogsService() {
		return logsService;
	}

	public void setLogsService(LogsService logsService) {
		this.logsService = logsService;
	}

	/*
	 * public PageSuppoert getPage() { return page; }
	 * 
	 * public void setPage(PageSuppoert page) { this.page = page; }
	 */

}
