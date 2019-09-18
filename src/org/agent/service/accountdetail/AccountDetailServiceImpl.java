package org.agent.service.accountdetail;

import java.util.List;

import org.agent.dao.accountDetail.AccountDetailMapper;
import org.agent.pojo.AccountDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailServiceImpl implements AccountDetailService {
	@Autowired
	private AccountDetailMapper accountDetailMapper;

	@Override
	public List<AccountDetail> getAccountDetailList(AccountDetail accountDetail) {
		// TODO Auto-generated method stub
		return accountDetailMapper.getAccountDetailList(accountDetail);
	}

	@Override
	public Integer count(AccountDetail AccountDetail) {
		// TODO Auto-generated method stub
		return accountDetailMapper.count(AccountDetail);

	}

	@Override
	public int addAccountDetail(AccountDetail accountDetail) {
		// TODO Auto-generated method stub
		return accountDetailMapper.addAccountDetail(accountDetail);
	}

	@Override
	public int modifyAccountDetail(AccountDetail accountDetail) {
		// TODO Auto-generated method stub
		return accountDetailMapper.modifyAccountDetail(accountDetail);
	}

	@Override
	public int deleteAccountDetail(AccountDetail accountDetail) {
		// TODO Auto-generated method stub
		return accountDetailMapper.deleteAccountDetail(accountDetail);
	}

}
