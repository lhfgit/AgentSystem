package org.agent.dao.role;

import java.util.List;

import org.agent.pojo.Role;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
	/**
	 * 获取角色列表
	 * 
	 * @param role
	 *            角色对象
	 * @return 角色集合
	 */
	public List<Role> getRoleList(Role role);

	/**
	 * 获取所有角色id和名称列表
	 * 
	 * @return
	 */
	public List<Role> getRoleIdAndNameList();

	/**
	 * 获取单个角色对象
	 * 
	 * @param role
	 *            角色对象的字段作为条件
	 * @return 角色对象
	 */
	public Role getRole(Role role);

	/**
	 * 添加角色
	 * 
	 * @param role
	 *            要添加的角色对象
	 * @return 受影响行数
	 */
	public int addRole(Role role);

	/**
	 * 删除角色
	 * 
	 * @param role
	 *            要删除的角色对象
	 * @return 受影响行数
	 */
	public int deleteRole(Role role);

	/**
	 * 更新角色
	 * 
	 * @param role
	 *            要跟新的角色对象
	 * @return 受影响行数
	 */
	public int modifyRole(Role role);

	/**
	 * 角色名称是否重复
	 * 
	 * @param roleName
	 *            角色名称
	 * @param isStart
	 *            是否启用
	 * @return 记录数
	 */
	public int isPeatRoleName(@Param("roleName") String roleName, @Param("isStart") Integer isStart);

	/**
	 * 当前角色下的用户数
	 * 
	 * @param id
	 *            角色id
	 * @return 总记录数
	 */
	public int countUserByRole(Integer id);
}
