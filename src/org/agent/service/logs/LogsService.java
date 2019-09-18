package org.agent.service.logs;

import java.util.List;

import org.agent.pojo.Logs;

public interface LogsService {
	public List<Logs> getList(Logs logs);

	public int addLogs(Logs logs);

	public int count(Logs logs);
}
