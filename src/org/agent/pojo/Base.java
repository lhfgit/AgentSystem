package org.agent.pojo;

import java.util.Date;

import org.agent.common.PageSuppoert;
import org.springframework.format.annotation.DateTimeFormat;

public class Base {
	private Integer id;
	private Integer starNum;// 分页的起始行
	private Integer pageSize;// 页显示条数
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;// 日期查询起始范围
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;// 日期查询结束范围
	private String searchStr;// 搜索字符串
	private PageSuppoert page;

	public PageSuppoert getPage() {
		return page;
	}

	public void setPage(PageSuppoert page) {
		this.page = page;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getStarNum() {
		return starNum;
	}

	public void setStarNum(Integer starNum) {
		this.starNum = starNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date entTime) {
		this.endTime = entTime;
	}

	public String getSearchStr() {
		return searchStr;
	}

	public void setSearchStr(String searchStr) {
		this.searchStr = searchStr;
	}

}
