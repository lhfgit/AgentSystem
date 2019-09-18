package org.agent.dao.function;

import java.util.List;

import org.agent.pojo.Function;

public interface FunctionMapper {
	/**
	 * 查询所有功能列表
	 * 
	 * @return
	 */
	public List<Function> getfunctionList();

	/**
	 * 查询单个功能
	 * 
	 * @param function
	 * @return
	 */
	public Function getfunctionById(Function function);

	/**
	 * 查询一级功能
	 * 
	 * @return
	 */
	public List<Function> getMenuFunction();

	/**
	 * 按父id查询功能列表
	 * 
	 * @param parentId
	 *            父id
	 * @return
	 */
	public List<Function> getFunctionByParentId(int parentId);

}
