package org.agent.dao.custom;

import java.util.List;

import org.agent.pojo.Custom;

public interface CustomMapper {
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

}
