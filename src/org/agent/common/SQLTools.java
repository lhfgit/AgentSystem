package org.agent.common;

public class SQLTools {
	/**
	 * mybaits 模糊查询防止SQL注入
	 * 
	 * @param keyword
	 * @return
	 */
	public static String transfer(String keyword) {
		if (keyword.contains("%") || keyword.contains("_")) {
			keyword = keyword.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\%", "\\\\%").replaceAll("\\_", "\\\\_");
		}

		return keyword;
	}
}
