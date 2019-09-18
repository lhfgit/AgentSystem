package org.agent.service.report;

import java.util.List;

import org.agent.dao.report.ReportMapper;
import org.agent.pojo.Account;
import org.agent.pojo.ReportProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportServiceImpl implements ReportService {
	@Autowired
	private ReportMapper reportMapper;

	@Override
	public List<Account> accountBalance(Account account) {
		// TODO Auto-generated method stub
		return reportMapper.accountBalance(account);
	}

	@Override
	public List<ReportProduct> getReportProduct(ReportProduct reportProduct) {
		// TODO Auto-generated method stub
		return reportMapper.getReportProduct(reportProduct);
	}

}
