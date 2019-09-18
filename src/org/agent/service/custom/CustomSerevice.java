package org.agent.service.custom;

import java.util.List;

import org.agent.pojo.Contact;
import org.agent.pojo.Custom;

public interface CustomSerevice {
	public List<Custom> getList(Custom custom);

	public int addCustom(Custom custom);

	public int modifyCustom(Custom custom);

	public int deleteCustom(Custom custom);

	public int count(Custom custom);

	// 客户名称是否存在
	public int isExitCustomName(Custom custom);

	public Custom getCustomById(Custom custom);

	// 搜索客户： 仅仅显示前十的用户
	public List<Custom> getCustomBySearch(Custom custom);

	// 修改客户状态
	public int modifyCustomAtatus(Custom custom);

	/**
	 * 使用事物处理：添加代理商客户的时候同时添加一个或多个联系人
	 * 
	 * @param custom
	 *            代理商客户对象
	 * @param contacts
	 *            联系人集合
	 * @return
	 */
	public boolean tx_addCustomContact(Custom custom, List<Contact> contacts);

	/**
	 * 使用事物处理：修改代理商客户
	 * 
	 * @param custom
	 *            custom 代理商客户对象
	 * @param contacts
	 *            contacts 联系人集合
	 * @return
	 */
	public boolean tx_modifyCustomContact(Custom custom, List<Contact> contacts);
}
