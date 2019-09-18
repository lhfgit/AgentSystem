package org.agent.service.permission;

import java.util.List;

import org.agent.dao.permission.PermissionMapper;
import org.agent.pojo.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl implements PermissionService {
	@Autowired
	private PermissionMapper permissionMapper;

	@Override
	public List<Permission> getlist(Permission permission) {
		// TODO Auto-generated method stub
		return permissionMapper.getlist(permission);
	}

	@Override
	public int addPermission(Permission permission) {
		// TODO Auto-generated method stub
		return permissionMapper.addPermission(permission);
	}

	@Override
	public int modifyPermission(Permission permission) {
		// TODO Auto-generated method stub
		return permissionMapper.modifyPermission(permission);
	}

	@Override
	public int deletePermission(Permission permission) {
		// TODO Auto-generated method stub
		return permissionMapper.deletePermission(permission);
	}

	@Override
	public void tx_delAddpermission(Permission pm, String checkFuncList) {
		// 1.组合所有角色和功能的映射Permission
		String[] funcList = checkFuncList.split(",");
		// 2.保存（删除在添加）
		if (funcList != null) {
			permissionMapper.deletePermission(pm);
			for (int i = 0; i < funcList.length; i++) {
				pm.setFunctionId(Integer.parseInt(funcList[i]));
				permissionMapper.addPermission(pm);
			}
		}
	}

}
