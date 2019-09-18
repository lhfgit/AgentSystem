package org.agent.dao.accountDetail;

import java.util.List;

import org.agent.pojo.AccountDetail;

public interface AccountDetailMapper {
	public List<AccountDetail> getAccountDetailList(AccountDetail accountDetail);

	public Integer count(AccountDetail AccountDetail);

	public int addAccountDetail(AccountDetail accountDetail);

	public int modifyAccountDetail(AccountDetail accountDetail);

	public int deleteAccountDetail(AccountDetail accountDetail);

}
