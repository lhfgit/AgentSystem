package org.agent.service.user;

import java.util.List;

import org.agent.pojo.User;

public interface UserService {
	/**
	 * 登录及验证userCode是否存在
	 * 
	 * @param user
	 *            user对象
	 * @return 返回查询出的user对象
	 */
	public User getLogUser(User user);

	/**
	 * 根新用户
	 * 
	 * @param user
	 *            用户对象
	 * @return 受影响行数
	 */
	public int modifyUser(User user);

	/**
	 * 获取用户集合
	 * 
	 * @param user
	 *            用户对象
	 * @return 用户集合
	 */
	public List<User> getuserList(User user);

	/**
	 * 获取单个用户
	 * 
	 * @param user
	 *            用户
	 * @return 用户
	 */
	public User getuser(User user);

	/**
	 * 添加用户
	 * 
	 * @param user
	 *            用户对象
	 * @return 添加的用户id
	 */
	public int addUser(User user);

	/**
	 * 删除用户
	 * 
	 * @param user
	 *            用户对象
	 * @return 受影响的行数
	 */
	public int deleteUser(User user);

	/**
	 * 查询用户记录数
	 * 
	 * @param user
	 *            用户对象
	 * @return 总记录数
	 */
	public int count(User user);

	/**
	 * 事务处理的添加方法为每一个用户创建一个资金账户
	 * 
	 * @param user
	 *            user对象
	 */
	public void tx_addUser(User user);

}
