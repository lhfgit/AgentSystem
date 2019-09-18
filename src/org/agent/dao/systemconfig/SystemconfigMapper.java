package org.agent.dao.systemconfig;

import java.util.List;

import org.agent.pojo.Systemconfig;

public interface SystemconfigMapper {
	/**
	 * 查询配置项
	 * 
	 * @param systemconfig
	 *            系统配置项
	 * @return 系统配置项集合
	 */
	public List<Systemconfig> getSystemConfig(Systemconfig systemconfig);

	/**
	 * 查询以启动的配置项
	 * 
	 * @param systemconfig
	 *            系统配置项
	 * @return 系统配置项集合
	 */
	public List<Systemconfig> getSystemConfigIsStart(Systemconfig systemconfig);

	/**
	 * 添加系统配置项
	 * 
	 * @param systemconfig
	 * @return 受影响行数
	 */
	public int addSystemConfigIsStart(Systemconfig systemconfig);

	/**
	 * 更新系统配置项
	 * 
	 * @param systemconfig
	 * @return 受影响行数
	 */
	public int modifySystemConfigIsStart(Systemconfig systemconfig);

	/**
	 * 是否重复
	 * 
	 * @param systemconfig
	 * @return 记录数
	 */
	public int isPeatConfig(Systemconfig systemconfig);

	/**
	 * 删除配置项
	 * 
	 * @param systemconfig
	 * @return
	 */
	public int deleteSystemConfig(Systemconfig systemconfig);

	/**
	 * 一级编号固定时，查询最大的二级编号
	 * 
	 * @param type
	 *            一级编号
	 * @return 二级编号的最大值
	 */
	public int maxTypeValueByType(int type);
}
