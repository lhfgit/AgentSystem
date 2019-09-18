package org.agent.service.permission;

import java.util.List;

import org.agent.pojo.Permission;

public interface PermissionService {
	public List<Permission> getlist(Permission permission);

	public int addPermission(Permission permission);

	public int modifyPermission(Permission permission);

	public int deletePermission(Permission permission);

	/**
	 * 添加一个权限，先删除在添加，保证权限的唯一性，避免冗余
	 * 
	 * @param pm
	 *            Permission对象
	 * @param checkFuncList
	 *            功能列表
	 */
	public void tx_delAddpermission(Permission pm, String checkFuncList);
}
