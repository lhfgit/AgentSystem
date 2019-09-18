package org.agent.service.account;

import java.util.List;

import org.agent.pojo.Account;
import org.agent.pojo.AccountDetail;
import org.agent.pojo.Logs;

public interface AccountService {
	/**
	 * 获取所有账户资金列表
	 * 
	 * @param account
	 * @return
	 */
	public List<Account> getAccountList(Account account);

	public Account getAccount(Account account);

	public int addAccount(Account account);

	public int modifyAccount(Account account);

	public int deleteAccount(Account account);

	/**
	 * 操作账户资金
	 * 
	 * @param oldaccount
	 *            原始数据（原来账户的金额信息）
	 * @param newAccount
	 *            新的数据（当前需要增加或者减少的金额）
	 * @param accountDetail
	 *            当前的流水信息
	 * @param logs
	 *            日志信息
	 * @return
	 */
	public boolean tx_operationAccount(Account oldaccount, Account newAccount, AccountDetail accountDetail, Logs logs);
}
