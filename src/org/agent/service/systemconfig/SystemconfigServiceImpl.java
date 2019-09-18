package org.agent.service.systemconfig;

import java.util.List;

import org.agent.dao.systemconfig.SystemconfigMapper;
import org.agent.pojo.Systemconfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemconfigServiceImpl implements SystemconfigService {
	@Autowired
	private SystemconfigMapper systemconfigMapper;

	@Override
	public List<Systemconfig> getSystemConfig(Systemconfig systemconfig) {
		// TODO Auto-generated method stub
		return systemconfigMapper.getSystemConfig(systemconfig);
	}

	@Override
	public List<Systemconfig> getSystemConfigIsStart(Systemconfig systemconfig) {
		// TODO Auto-generated method stub
		return systemconfigMapper.getSystemConfigIsStart(systemconfig);
	}

	@Override
	public int addSystemConfigIsStart(Systemconfig systemconfig) {
		// TODO Auto-generated method stub
		return systemconfigMapper.addSystemConfigIsStart(systemconfig);
	}

	@Override
	public int modifySystemConfigIsStart(Systemconfig systemconfig) {
		// TODO Auto-generated method stub
		return systemconfigMapper.modifySystemConfigIsStart(systemconfig);
	}

	@Override
	public int isPeatConfig(Systemconfig systemconfig) {
		// TODO Auto-generated method stub
		return systemconfigMapper.isPeatConfig(systemconfig);
	}

	@Override
	public int deleteSystemConfig(Systemconfig systemconfig) {
		// TODO Auto-generated method stub
		return systemconfigMapper.deleteSystemConfig(systemconfig);
	}

	@Override
	public int maxTypeValueByType(int type) {
		// TODO Auto-generated method stub
		return systemconfigMapper.maxTypeValueByType(type);
	}

}
