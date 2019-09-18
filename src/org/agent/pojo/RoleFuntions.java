package org.agent.pojo;

import java.util.List;

public class RoleFuntions {
	private Function mainFunction;
	private List<Function> subFuntions;

	@Override
	public String toString() {
		return "RoleFuntions [mainFunction=" + mainFunction + ", subFuntions=" + subFuntions + "]";
	}

	public Function getMainFunction() {
		return mainFunction;
	}

	public void setMainFunction(Function mainFunction) {
		this.mainFunction = mainFunction;
	}

	public List<Function> getSubFuntions() {
		return subFuntions;
	}

	public void setSubFuntions(List<Function> subFuntions) {
		this.subFuntions = subFuntions;
	}

}
