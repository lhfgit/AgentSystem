package org.agent.dao.user;

import java.util.List;

import org.agent.pojo.User;

public interface UserMapper {
	/**
	 * 查询用户列表
	 * 
	 * @param user
	 *            用户对象
	 * @return 用户集合
	 */
	public List<User> getUserList(User user);
	/*
	 * public List<User> getUserList(@Param("userName") String
	 * userName, @Param("userCode") String userCode,
	 * 
	 * @Param("roleId") Integer roleId, @Param("isStart") Integer isStart);
	 */

	/**
	 * 查询单个用户
	 * 
	 * @param user
	 *            用户对象
	 * @return 用户对象
	 */
	public User getUser(User user);

	/**
	 * 添加用户
	 * 
	 * @param user
	 *            用户对象
	 * @return 受影响行数,user 新生成的id放在指定的属性中keyProperty="id"
	 */
	public int addUser(User user);

	/**
	 * 更新用户
	 * 
	 * @param user
	 *            用户对象
	 * @return 受影响行数
	 */
	public int modifyUser(User user);

	/**
	 * 删除用户
	 * 
	 * @param user
	 *            用户对象
	 * @return 受影响行数
	 */
	public int deleteUser(User user);

	/**
	 * 用户的总记录数
	 * 
	 * @param user
	 *            用户对象
	 * @return 总条数
	 */
	public int count(User user);

	/**
	 * 登录及登录验证
	 * 
	 * @param user
	 *            登录用户
	 * @return 查询出的user
	 */
	public User checkLogin(User user);

}
