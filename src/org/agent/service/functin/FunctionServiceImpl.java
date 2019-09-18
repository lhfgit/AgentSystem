package org.agent.service.functin;

import java.util.List;

import org.agent.dao.function.FunctionMapper;
import org.agent.pojo.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FunctionServiceImpl implements FunctionService {
	@Autowired
	private FunctionMapper functionMapper;

	@Override
	public List<Function> getfunctionList() {
		// TODO Auto-generated method stub
		return functionMapper.getfunctionList();
	}

	@Override
	public Function getfunctionById(Function function) {
		// TODO Auto-generated method stub
		return functionMapper.getfunctionById(function);
	}

	@Override
	public List<Function> getMenuFunction() {
		// TODO Auto-generated method stub
		return functionMapper.getMenuFunction();
	}

	@Override
	public List<Function> getFunctionByParentId(int parentId) {
		// TODO Auto-generated method stub
		return functionMapper.getFunctionByParentId(parentId);
	}

}
