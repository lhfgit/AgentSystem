package org.agent.dao.report;

import java.util.List;

import org.agent.pojo.Account;
import org.agent.pojo.ReportProduct;

public interface ReportMapper {
	/**
	 * 代理商余额报表
	 * 
	 * @param account
	 *            账户对象
	 * @return
	 */
	public List<Account> accountBalance(Account account);

	public List<ReportProduct> getReportProduct(ReportProduct reportProduct);
}
