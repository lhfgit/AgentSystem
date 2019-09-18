package org.agent.service.account;

import java.math.BigDecimal;
import java.util.List;

import org.agent.dao.account.AccountMapper;
import org.agent.dao.accountDetail.AccountDetailMapper;
import org.agent.dao.logs.LogsMapper;
import org.agent.pojo.Account;
import org.agent.pojo.AccountDetail;
import org.agent.pojo.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountMapper accountMapper;
	@Autowired
	private AccountDetailMapper accountDetailMapper;
	@Autowired
	private LogsMapper logsmapper;

	@Override
	public List<Account> getAccountList(Account account) {
		// TODO Auto-generated method stub
		return accountMapper.getAccountList(account);
	}

	@Override
	public Account getAccount(Account account) {
		// TODO Auto-generated method stub
		return accountMapper.getAccount(account);
	}

	@Override
	public int addAccount(Account account) {
		// TODO Auto-generated method stub
		return accountMapper.addAccount(account);
	}

	@Override
	public int modifyAccount(Account account) {
		// TODO Auto-generated method stub
		return accountMapper.modifyAccount(account);
	}

	@Override
	public int deleteAccount(Account account) {
		// TODO Auto-generated method stub
		return accountMapper.deleteAccount(account);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public boolean tx_operationAccount(Account oldaccount, Account newAccount, AccountDetail accountDetail, Logs logs) {
		boolean falg = false;
		// 资金计算
		BigDecimal money = oldaccount.getMoney().add(newAccount.getMoney());
		BigDecimal moneyBak = oldaccount.getMoneyBak().add(newAccount.getMoney());
		oldaccount.setMoneyBak(moneyBak);
		oldaccount.setMoney(money);
		// 修改账户资金
		accountMapper.modifyAccount(oldaccount);
		// 记录流水
		accountDetailMapper.addAccountDetail(accountDetail);

		// 记录日志
		logsmapper.addLogs(logs);
		falg = true;
		return falg;
	}

}
