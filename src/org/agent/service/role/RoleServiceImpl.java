package org.agent.service.role;

import java.util.List;

import org.agent.dao.role.RoleMapper;
import org.agent.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public List<Role> getRoleList(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.getRoleList(role);
	}

	@Override
	public Role getRole(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.getRole(role);
	}

	@Override
	public int addRole(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.addRole(role);
	}

	@Override
	public int deleteRole(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.deleteRole(role);
	}

	@Override
	public int modifyRole(Role role) {
		// TODO Auto-generated method stub
		return roleMapper.modifyRole(role);
	}

	@Override
	public int isPeatRoleName(String roleName, Integer isStart) {
		// TODO Auto-generated method stub
		return roleMapper.isPeatRoleName(roleName, isStart);
	}

	@Override
	public int countUserByRole(Integer id) {
		// TODO Auto-generated method stub
		return roleMapper.countUserByRole(id);
	}

	@Override
	public List<Role> getRoleIdAndNameList() {
		// TODO Auto-generated method stub
		return roleMapper.getRoleIdAndNameList();
	}

}
