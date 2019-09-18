package org.agent.common;
/*
 * 分页支持
 */

import java.util.ArrayList;
import java.util.List;

public class PageSuppoert {
	private Integer totalCont = 0;// 总记录数
	private Integer pageCount;// 总页数
	private Integer pageSize = 5;// 页大小
	private Integer page = 1;// 当前页
	private Integer num = 3;// 在当前页前后显示页数
	private List<Object> items = new ArrayList<>();

	public Integer getTotalCont() {
		return totalCont;
	}

	public void setTotalCont(Integer totalCont) {
		if (totalCont > 0) {
			this.totalCont = totalCont;
			this.pageCount = totalCont % pageSize > 0 ? totalCont / pageSize + 1 : totalCont / pageSize;
		}

	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public List getItems() {
		return items;
	}

	public void setItems(List items) {
		this.items = items;
	}

	/**
	 * 前一页
	 * 
	 * @return
	 */
	public Integer getPrev() {
		return this.page - 1;
	}

	/**
	 * 后一页
	 * 
	 * @return
	 */
	public Integer getenxt() {
		return this.page + 1;
	}

	/**
	 * 最后一页
	 * 
	 * @return
	 */
	public Integer getLast() {
		return this.pageCount;
	}

	// 是否有前一页
	public boolean getIsPrev() {
		if (page > 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean getIsLast() {
		if (pageCount != null && page < pageCount) {
			return true;
		} else {
			return false;
		}
	}

	public List<Integer> getPrevPages() {
		List<Integer> list = new ArrayList<Integer>();
		Integer _front = 1;
		if (page > num) {
			_front = page + num;
		}
		for (Integer i = _front; i < page; i++) {
			list.add(i);
		}
		return list;
	}

	public List<Integer> getNextPages() {
		List<Integer> list = new ArrayList<Integer>();
		Integer _end = 1;
		if (pageCount != null) {
			if (num < pageCount && (page + num) < pageCount) {
				_end = page + num;
			} else
				_end = pageCount;
		}
		for (Integer i = page + 1; i <= _end; i++) {
			list.add(i);
		}

		return list;
	}

	@Override
	public String toString() {
		return "PageSuppoert [totalCont=" + totalCont + ", pageCount=" + pageCount + ", pageSize=" + pageSize
				+ ", page=" + page + ", num=" + num + ", items=" + items + "]";
	}

}
