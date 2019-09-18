package org.agent.dao.keywords;

import java.util.List;

import org.agent.pojo.Keywords;

public interface KeywordsMapper {

	public List<Keywords> getKeywordsList(Keywords k);

	public Integer count(Keywords k);

	public int valikey(Keywords k);

	public void add(Keywords k);

	public Keywords getKeywordsById(Keywords keywords);

	public int modifyKeywords(Keywords keywords);

	public int deleteKeywords(Keywords k);
}
