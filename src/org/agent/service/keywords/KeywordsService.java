package org.agent.service.keywords;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.agent.pojo.Keywords;
import org.agent.pojo.User;

public interface KeywordsService {
	public List<Keywords> getKeywordsList(Keywords k);

	public Integer count(Keywords k);

	// 验证关键词是否被占用
	public int valikey(Keywords k);

	public boolean txAddKeywords(User user, Keywords k);

	public void tx_ChangeStatusToOk(Keywords keywords, User user, Date date);

	public void tx_ChangeStatusToNo(Keywords keywords, User user, Date date);

	public int modifyKeywords(Keywords keywords);

	public Keywords getKeywordsById(Keywords keywords);

	public void tx_keywordsXufei(Keywords keywords, String string, String string2, BigDecimal bigDecimal, Date date);

	public int deleteKeywords(Keywords k);

}
