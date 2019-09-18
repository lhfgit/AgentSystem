package org.agent.service.keywords;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.agent.dao.account.AccountMapper;
import org.agent.dao.accountDetail.AccountDetailMapper;
import org.agent.dao.keywords.KeywordsMapper;
import org.agent.dao.logs.LogsMapper;
import org.agent.pojo.Account;
import org.agent.pojo.AccountDetail;
import org.agent.pojo.Keywords;
import org.agent.pojo.Logs;
import org.agent.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class KeywordsServiceImpl implements KeywordsService {
	@Autowired
	private KeywordsMapper keywordsMapper;
	@Autowired
	private LogsMapper logsMapper;
	@Autowired
	private AccountDetailMapper accountDetailMapper;
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public List<Keywords> getKeywordsList(Keywords k) {
		// TODO Auto-generated method stub
		return keywordsMapper.getKeywordsList(k);
	}

	@Override
	public Integer count(Keywords k) {
		// TODO Auto-generated method stub
		return keywordsMapper.count(k);
	}

	@Override
	public int valikey(Keywords k) {
		// TODO Auto-generated method stub
		return keywordsMapper.valikey(k);
	}

	/*
	 * （预注册冻结资金）关键词申请扣款流水类型id（DetailType）为9999 返回预注册冻结资金流水类型id（DetailType）为9998
	 * 扣除申请关键词的所有资金资金类型id（DetailType）为9997 扣除关键词续费流水类型id（DetailType）为9997
	 */
	@Transactional
	@Override
	public boolean txAddKeywords(User user, Keywords k) {
		// TODO Auto-generated method stub
		try {
			Calendar cd = Calendar.getInstance();
			cd.setTime(k.getRegDatetime());
			cd.add(cd.YEAR, k.getServiceYears());
			k.setRegPassDatetime(cd.getTime());
			keywordsMapper.add(k);
			Logs logs = new Logs();
			logs.setOperateDatetime(new Date());
			String operateInfo = "用户[" + user.getUserName() + "]，为[" + k.getCustomName() + "]代理商客户申请了["
					+ k.getKeywords() + "]关键词";
			logs.setOperateInfo(operateInfo);
			logs.setUserId(user.getId());
			logs.setUserName(user.getUserName());
			logsMapper.addLogs(logs);
			// 更新代理商客户的账户余额
			Account account = new Account();
			account.setUserId(user.getId());
			Account account2 = accountMapper.getAccount(account);
			account.setMoney(account2.getMoney().add(k.getPreRegFrozenMoney()));
			account.setMoneyBak(account2.getMoneyBak().add(k.getPreRegFrozenMoney()));
			accountMapper.modifyAccount(account);
			// 添加账务流水
			AccountDetail accountDetail = new AccountDetail();
			accountDetail.setUserId(user.getId());
			accountDetail.setdetailDateTime(new Date());
			accountDetail.setDetailType(9999);
			accountDetail.setDetailTypeName("冻结");
			accountDetail.setMemo(user.getUserName() + "对" + k.getKeywords() + "关键词申请预注册");
			accountDetail.setMoney(k.getPreRegFrozenMoney());
			accountDetail.setAccountMoney(account2.getMoney().add(k.getPreRegFrozenMoney()));
			accountDetailMapper.addAccountDetail(accountDetail);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			new RuntimeException(e);
			return false;
		}
	}

	@Transactional
	@Override
	public void tx_ChangeStatusToOk(Keywords keywords, User user, Date date) {
		// TODO Auto-generated method stub
		/**
		 * 1.修改关键词的状态 2.返回预注册的冻结资金 3.在总账目中扣除正式注册所需的所有资金 4.记录日志
		 * 流水记录两次（第一次流水在步骤2的流水，第二次是在步骤3的流水）
		 */
		int status = keywords.getCheckStatus();
		if (status != 2)
			throw new RuntimeException("通过的状态必须为2");

		keywords = keywordsMapper.getKeywordsById(keywords);
		keywords.setCheckStatus(status);
		keywordsMapper.modifyKeywords(keywords);
		// 返还冻结资金
		Account account = new Account();
		account.setUserId(keywords.getAgentId());
		account = accountMapper.getAccount(account);
		account.setMoney(account.getMoney().add(keywords.getPreRegFrozenMoney()));
		account.setMoneyBak(account.getMoney());
		accountMapper.modifyAccount(account);
		// 第一次记录流水
		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setUserId(user.getId());
		accountDetail.setDetailType(9998);
		accountDetail.setDetailTypeName("返还预注册冻结资金");
		accountDetail.setMoney(keywords.getPreRegFrozenMoney());
		accountDetail.setAccountMoney(account.getMoney());
		accountDetail.setMemo(user.getUserName() + "对" + keywords.getKeywords() + "进行关键词审核通过操作，返回冻结资金"
				+ keywords.getPreRegFrozenMoney());
		accountDetail.setdetailDateTime(date);
		accountDetailMapper.addAccountDetail(accountDetail);
		// 正式扣款
		account.setMoney(account.getMoney().subtract(keywords.getPrice()));
		account.setMoneyBak(account.getMoney());
		accountMapper.modifyAccount(account);
		// 第二次记录流水
		accountDetail = new AccountDetail();
		accountDetail.setUserId(user.getId());
		accountDetail.setDetailType(9997);
		accountDetail.setDetailTypeName("扣除申请关键词" + keywords.getKeywords() + "的所有资金");
		accountDetail.setMoney(new BigDecimal(0).subtract(keywords.getPrice()));
		accountDetail.setAccountMoney(account.getMoney());
		accountDetail.setMemo(
				user.getUserName() + "对" + keywords.getKeywords() + "进行关键词审核通过操作自动扣款操作，扣除正式注册资金" + keywords.getPrice());
		accountDetail.setdetailDateTime(date);
		accountDetailMapper.addAccountDetail(accountDetail);
		// 记录日志
		Logs logs = new Logs();
		logs.setOperateDatetime(new Date());
		String operateInfo = "用户" + user.getUserName() + "，对关键词" + keywords.getKeywords() + "进行关键词状态修改：状态修改为审核通过 并扣款";
		logs.setOperateInfo(operateInfo);
		logs.setUserId(user.getId());
		logs.setUserName(user.getUserName());
		logsMapper.addLogs(logs);

	}

	@Transactional
	@Override
	public void tx_ChangeStatusToNo(Keywords keywords, User user, Date date) {
		// TODO Auto-generated method stub
		int status = keywords.getCheckStatus();
		if (status != 3)
			throw new RuntimeException("不通过的状态必须为3");

		keywords = keywordsMapper.getKeywordsById(keywords);
		keywords.setCheckStatus(status);
		keywordsMapper.modifyKeywords(keywords);
		// 返还冻结资金
		Account account = new Account();
		account.setUserId(keywords.getAgentId());
		account = accountMapper.getAccount(account);
		account.setMoney(account.getMoney().add(keywords.getPreRegFrozenMoney()));
		account.setMoneyBak(account.getMoney());
		accountMapper.modifyAccount(account);
		// 第一次记录流水
		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setUserId(user.getId());
		accountDetail.setDetailType(9998);
		accountDetail.setDetailTypeName("返还预注册冻结资金");
		accountDetail.setMoney(keywords.getPreRegFrozenMoney());
		accountDetail.setAccountMoney(account.getMoney());
		accountDetail.setMemo(user.getUserName() + "对" + keywords.getKeywords() + "进行关键词审核不通过操作，返回冻结资金"
				+ keywords.getPreRegFrozenMoney());
		accountDetail.setdetailDateTime(date);
		accountDetailMapper.addAccountDetail(accountDetail);

		// 记录日志
		Logs logs = new Logs();
		logs.setOperateDatetime(new Date());
		String operateInfo = "用户" + user.getUserName() + "，对关键词" + keywords.getKeywords()
				+ "进行关键词状态修改：状态修改为审核不通过 并返回冻结资金：" + keywords.getPreRegFrozenMoney();
		logs.setOperateInfo(operateInfo);
		logs.setUserId(user.getId());
		logs.setUserName(user.getUserName());
		logsMapper.addLogs(logs);
	}

	@Override
	public int modifyKeywords(Keywords keywords) {
		// TODO Auto-generated method stub
		return keywordsMapper.modifyKeywords(keywords);
	}

	@Override
	public Keywords getKeywordsById(Keywords keywords) {
		// TODO Auto-generated method stub
		return keywordsMapper.getKeywordsById(keywords);
	}

	@Transactional
	@Override
	public void tx_keywordsXufei(Keywords keywords, String productType, String xufeiYears, BigDecimal price,
			Date date) {
		/**
		 * 1.修改关键词的总价与服务到期时间 2.更新账户余额 3.记录流水 4.记录日志
		 */
		Integer type = Integer.valueOf(productType);
		Integer years = Integer.valueOf(xufeiYears);
		keywords = keywordsMapper.getKeywordsById(keywords);
		keywords.setPrice(keywords.getPrice().add(price));
		keywords.setServiceYears(keywords.getServiceYears() + years);
		/*
		 * Calendar calendar =Calendar.getInstance(); calendar.setTime(date);
		 * calendar.add(calendar.DAY_OF_YEAR, 1);//增加一天,负数为减少一天
		 * //calendar.add(calendar.DAY_OF_MONTH, 1);//增加一天
		 * //calendar.add(calendar.DATE,1);//增加一天 //calendar.add(calendar.WEEK_OF_MONTH,
		 * 1);//增加一个礼拜 //calendar.add(calendar.WEEK_OF_YEAR,1);//增加一个礼拜
		 * //calendar.add(calendar.YEAR, 1);//把日期往后增加一年.整数往后推,负数往前移动 date =
		 * calendar.getTime();
		 * 
		 */
		Calendar cd = Calendar.getInstance();
		cd.setTime(keywords.getRegPassDatetime());
		cd.add(cd.YEAR, years);
		keywords.setRegPassDatetime(cd.getTime());
		// keywords.setProductType(type);
		keywordsMapper.modifyKeywords(keywords);
		// 2.更新账户余额
		Account account = new Account();
		account.setUserId(keywords.getAgentId());
		account = accountMapper.getAccount(account);
		account.setMoney(account.getMoney().subtract(keywords.getPrice()));
		account.setMoneyBak(account.getMoney());
		accountMapper.modifyAccount(account);
		// 3.记录流水
		AccountDetail accountDetail = new AccountDetail();
		accountDetail.setUserId(keywords.getAgentId());
		accountDetail.setDetailType(9997);
		accountDetail.setDetailTypeName("扣除续费资金" + years + "年，金额为" + price + "元");
		accountDetail.setAccountMoney(account.getMoney());
		accountDetail.setMoney(price);
		// accountDetail.setMoney(new BigDecimal(0).subtract(price));
		accountDetail.setMemo(keywords.getAgentName() + "对" + keywords.getKeywords() + "进行关键词续费操作，扣除续费资金" + years + "年"
				+ price + "元");
		accountDetail.setdetailDateTime(date);
		accountDetailMapper.addAccountDetail(accountDetail);
		// 记录日志
		Logs logs = new Logs();
		logs.setOperateDatetime(new Date());
		logs.setOperateInfo(keywords.getAgentName() + "对" + keywords.getKeywords() + "进行关键词续费操作，扣除续费资金" + years + "年"
				+ price + "元");
		logs.setUserId(keywords.getAgentId());
		logs.setUserName(keywords.getAgentName());
		logsMapper.addLogs(logs);
	}

	@Override
	public int deleteKeywords(Keywords k) {
		// TODO Auto-generated method stub
		return keywordsMapper.deleteKeywords(k);
	}

}
