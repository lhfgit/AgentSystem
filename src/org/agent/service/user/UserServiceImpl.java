package org.agent.service.user;

import java.math.BigDecimal;
import java.util.List;

import org.agent.dao.account.AccountMapper;
import org.agent.dao.user.UserMapper;
import org.agent.pojo.Account;
import org.agent.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AccountMapper accountMapper;

	@Override
	public User getLogUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.checkLogin(user);
	}

	@Override
	public int modifyUser(User user) {
		// TODO Auto-generated method stub
		System.err.println("UserServiceImpl**modifyUser:" + user);
		return userMapper.modifyUser(user);
	}

	@Override
	public List<User> getuserList(User user) {

		// return userMapper.getUserList(user);
		// return userMapper.getUserList(user.getUserName(), user.getUserCode(),
		// user.getRoleId(), user.getIsStart());
		return userMapper.getUserList(user);
	}

	@Override
	public User getuser(User user) {
		// TODO Auto-generated method stub
		return userMapper.getUser(user);
	}

	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.addUser(user);
	}

	@Override
	public int deleteUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.deleteUser(user);
	}

	@Override
	public int count(User user) {
		// TODO Auto-generated method stub
		return userMapper.count(user);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void tx_addUser(User user) {
		// TODO Auto-generated method stub
		System.err.println("UserServiceImpl**modifyUser:" + user);
		int row = userMapper.addUser(user);
		if (row == 0) {
			throw new RuntimeException("用户id不能为0");
		}
		// 给用户创建账户

		Account account = new Account();
		account.setUserId(user.getId());
		account.setMoney(new BigDecimal(0));
		account.setMoneyBak(new BigDecimal(0));
		accountMapper.addAccount(account);

	}

}
