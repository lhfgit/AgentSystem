package org.agent.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.agent.pojo.Account;
import org.agent.pojo.AccountDetail;
import org.agent.pojo.ReportProduct;
import org.agent.service.accountdetail.AccountDetailService;
import org.agent.service.report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReportAction extends BaseAction {
	@Autowired
	private ReportService reportService;
	@Autowired
	private AccountDetailService accountDetailService;

	@RequestMapping(value = "/report", method = RequestMethod.POST)
	public String report(Integer reportType, Date startTime, Date endTime, Map<String, Object> map) {

		if (reportType != null) {
			if (null != endTime) {
				DateFormat df = DateFormat.getDateInstance();
				String dfString = df.format(endTime) + " 23:59:59";
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					endTime = sdf.parse(dfString);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (reportType == 1) {// 代理商账户余额报表
				Account account = new Account();
				List<Account> accountList = reportService.accountBalance(account);
				map.put("accountList", accountList);
			} else if (reportType == 2) {// 预付款流水报表
				AccountDetail accountDetail = new AccountDetail();
				accountDetail.setDetailType(9999);
				accountDetail.setStartTime(startTime);

				accountDetail.setEndTime(endTime);

				List<AccountDetail> accountDetailList = accountDetailService.getAccountDetailList(accountDetail);
				map.put("accountDetailList", accountDetailList);
			} else if (reportType == 3) {// 代理商流水报表
				AccountDetail accountDetail = new AccountDetail();
				// accountDetail.setDetailType(9997);
				accountDetail.setStartTime(startTime);

				accountDetail.setEndTime(endTime);

				List<AccountDetail> accountDetailList = accountDetailService.getAccountDetailList(accountDetail);
				map.put("accountDetailList", accountDetailList);
			} else if (reportType == 4) {// 代理商流水报表
				ReportProduct reportProduct = new ReportProduct();
				reportProduct.setStartTime(startTime);
				reportProduct.setEndTime(endTime);
				List<ReportProduct> reportProductList = reportService.getReportProduct(reportProduct);
				map.put("reportProductList", reportProductList);
			}
		}
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("reportType", reportType);
		return "report";

	}

	@RequestMapping(value = "/report", method = RequestMethod.GET)
	public String report() {
		return "report";
	}

}
