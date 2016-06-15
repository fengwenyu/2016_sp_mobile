package com.shangpin.wireless.view.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.wireless.api.service.AccountService;
import com.shangpin.wireless.api.service.PushconfigAolaiService;
import com.shangpin.wireless.domain.AccountStatistics;
import com.shangpin.wireless.domain.OrderStatistics;
import com.shangpin.wireless.manage.service.AccountStatisticsService;
import com.shangpin.wireless.manage.service.OrderStatisticsService;
import com.shangpin.wireless.util.HqlHelper;
import com.shangpin.wireless.util.IPUtil;
import com.shangpin.wireless.util.ParseApiLogUtil;
import com.shangpin.wireless.util.ParseShangPinWapLogUtil;
import com.shangpin.wireless.util.ParseAoLaiWapLogUtil;
import com.shangpin.wireless.util.StringUtils;

/**
 * log解析Action
 * 
 * @Author zhouyu
 * @CreatDate 2012-07-12
 */
@Controller
@Scope("prototype")
public class ParseLogAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource(name = AccountService.SERVICE_NAME)
	protected AccountService accountService;
	@Resource(name = AccountStatisticsService.SERVICE_NAME)
	protected AccountStatisticsService accountStatisticsService;
	@Resource(name = PushconfigAolaiService.SERVICE_NAME)
	protected PushconfigAolaiService pushconfigAolaiService;
	@Resource(name = OrderStatisticsService.SERVICE_NAME)
	protected OrderStatisticsService orderStatisticsService;
//
//	/**
//	 * 将account表中创建时间2012-11-09当天的数据插入到accountstatistics
//	 * 
//	 * @throws Exception
//	 */
//	public String addAccountStatistics() throws Exception {
//		HqlHelper hqlHelper = new HqlHelper(Account.class, "a");
//		hqlHelper.addWhereCondition("a.createTime>='2012-10-06 00:00:00'");
//		hqlHelper.addWhereCondition("a.createTime<'2012-11-09 11:20:00'");
//		hqlHelper.addOrderByProperty(true, "a.id", true);
//		List<Account> accountList = accountService.executeHql(hqlHelper.getQueryListHql());
//		for (Account account : accountList) {
//			AccountStatistics entity = new AccountStatistics();
//			entity.setUserId(account.getUserId());
//			entity.setUsername(account.getLoginName());
//			entity.setGender(account.getGender());
//			entity.setRegOrigin(account.getRegOrigin());
//			entity.setPlatform(account.getPlatform());
//			entity.setChannelNum(account.getChannel());
//			entity.setProductNum(account.getProduct());
//			entity.setRegTime(account.getRegTime());
//			entity.setCreateTime(account.getCreateTime());
//			entity.setLoginTime(account.getLoginTime());
//			System.out.println(account.getId());
//			accountStatisticsService.save(entity);
//		}
//		ServletActionContext.getResponse().getWriter().print("addAccountStatistics-success");
//		return null;
//	}

	/**
	 * 
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String updateAccountStatistics() throws Exception {

//		HqlHelper hqlHelper = new HqlHelper(AccountStatistics.class, "a");
//		hqlHelper.addOrderByProperty(true, "a.id", true);
//		List<AccountStatistics> accountStatisticsList = accountStatisticsService.executeHql(hqlHelper.getQueryListHql());
//		for (AccountStatistics account : accountStatisticsList) {
//			if (StringUtils.isEmpty(account.getUsername())) {
//				HqlHelper hqlHelperAccount = new HqlHelper(Account.class, "a");
//				hqlHelperAccount.addWhereCondition("a.userId='" + account.getUserId() + "'");
//				try {
//					List<Account> executeHql = accountService.executeHql(hqlHelperAccount.getQueryListHql());
//					Account account2 = executeHql.get(0);
//					account.setUsername(account2.getLoginName());
//				} catch (Exception e) {
//					e.printStackTrace();
//					System.out.println("userid:" + account.getUserId());
//				}
//				accountStatisticsService.update(account);
//			}
//		}
//		ServletActionContext.getResponse().getWriter().print("updateAccountStatistics-success");
		HqlHelper hqlHelper = new HqlHelper(AccountStatistics.class, "a");
		hqlHelper.addOrderByProperty(true, "a.id", true);
		List<AccountStatistics> accountStatisticsList = accountStatisticsService.executeHql(hqlHelper.getQueryListHql());
		for (AccountStatistics account : accountStatisticsList) {
			for (AccountStatistics account1 : accountStatisticsList) {
				if (account.getUserId().equals(account1.getUserId()) && account.getId() != account1.getId() && StringUtils.isEmpty(account.getUsername(), account1.getUsername())) {
					System.out.println(account1.getUserId() + "-----------------");
					accountStatisticsService.delete(account1.getId());
				} else if (account.getUserId().equals(account1.getUserId()) && account.getId() != account1.getId() && account.getUsername() != null && account1.getUsername() == null) {
					System.out.println(account1.getUserId() + "-----------------");
					accountStatisticsService.delete(account1.getId());
				} else if (account.getUserId().equals(account1.getUserId()) && account.getId() != account1.getId() && account.getUsername() != null && account.getUsername().equals(account1.getUsername())) {
					System.out.println(account1.getUserId() + "-----------------");
					accountStatisticsService.delete(account1.getId());
				}
			}
		}
		ServletActionContext.getResponse().getWriter().print("updateAccountStatistics-success");
		return null;
	}

	public String updateOrderStatisticsCity() throws Exception {
		List<OrderStatistics> orderStatisticsList = orderStatisticsService.findAll();
		for (OrderStatistics order : orderStatisticsList) {
			if (StringUtils.isNotEmpty(order.getIp())) {
				order.setCity(IPUtil.getCountryCityByIp(order.getIp()));
				System.out.println("id:" + order.getId() + "---ip:" + order.getIp() + "---city:" + IPUtil.getCountryCityByIp(order.getIp()));
			}
			orderStatisticsService.update(order);
		}
		ServletActionContext.getResponse().getWriter().print("updateOrderStatisticsCity-success");
		return null;
	}

	// api解析多个文件
	public String parseAPILog() throws Exception {
		// ParseLogUtil.parse1006_1019();
		// ParseLogUtil.parse1020_1109_1121();
//		ParseApiLogUtil.parseApiLog();
		System.out.println("parseAPILog request params = " + ServletActionContext.getRequest().getQueryString());
		String date = ServletActionContext.getRequest().getParameter("date");
		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			Date five = sdf.parse("2013-06-01");
//			for (int i = 0; i < 31; i++) {
//				String date = DateUtil.getAfterAmountDay(five, i, "yyyy-MM-dd");
				System.out.println("parseAPILog::" + date);
				
				File apiLogFile = new File("/home/parselog/api_logs/" + date + ".txt");
				if (apiLogFile.exists()) {
					ParseApiLogUtil.parseOnedayLog(apiLogFile);
					System.out.println("api--" + date + ".txt");
				}
				
				System.out.println("parseLog:: finished!!");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "privilegeError";
	}

	// wap解析多个文件
	public String parseWapLog() throws Exception {
		// ParseLogUtil.parse1006_1019();
		// ParseLogUtil.parse1020_1109_1121();
		// ParseWapLogUtil.parseWapLog();
//		ParseShangPinWapLogUtil.parseWapLog();
		System.out.println("parseWapLog request params = " + ServletActionContext.getRequest().getQueryString());
		String date = ServletActionContext.getRequest().getParameter("date");
		try {
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//			Date five = sdf.parse("2013-06-01");
//			for (int i = 0; i < 31; i++) {
//				String date = DateUtil.getAfterAmountDay(five, i, "yyyy-MM-dd");
				System.out.println("parseAoLaiWapLog::" + date);
				
				File aoLaiWapLogFile = new File("/home/parselog/wap_aolai_logs/" + date + ".txt");
				if (aoLaiWapLogFile.exists()) {
					ParseAoLaiWapLogUtil.parseOnedayLog(aoLaiWapLogFile);
					System.out.println("aoLaiWap--" + date + ".txt");
				}
				System.out.println("parseShangPinWapLog::" + date);

				File shangPinWapLogFile = new File("/home/parselog/wap_shangpin_logs/" + date + ".txt");
				if (shangPinWapLogFile.exists()) {
					ParseShangPinWapLogUtil.parseOnedayLog(shangPinWapLogFile);
					System.out.println("shangPinWap--" + date + ".txt");
				}
				
				System.out.println("parseLog:: finished!!");
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "privilegeError";
	}
	public String parseLog() throws Exception {
		System.out.println("parseLog new " );
		System.out.println("parseLog new request params = " + ServletActionContext.getRequest().getQueryString());
		final String startdate = ServletActionContext.getRequest().getParameter("startdate");
		final String enddate = ServletActionContext.getRequest().getParameter("enddate");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				parseLogNewThread(startdate, enddate);
			}
		}).start();
		return null;
	}

	public String parseLogNewThread(String startdate, String enddate)/* throws Exception*/ {
		System.out.println("parseLogNewThread" );
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date start = sdf.parse(startdate);
			Date end = sdf.parse(enddate);
			final String[] maolaiprefix = {"172.20.10.43#", "172.20.10.143#", ""};
			final String[] mshangpinprefix = {"172.20.10.43#", "172.20.10.143#", ""};
			final String[] apiprefix = {"172.20.10.44#", "172.20.10.142#", ""};
			for (int i = 0; i < 31; i++) {
				Calendar cal = new GregorianCalendar();
				cal.setTime(start);
				cal.add(Calendar.DATE, i);
				Date nowdate = cal.getTime();
				if (nowdate.after(end)) break;
				
				String date = sdf.format(nowdate);
				System.out.println("parseLog::" + date);

				for (int k=0;k<maolaiprefix.length;k++) {
					String filename = "/home/parselog/wap_aolai_logs/" + maolaiprefix[k] + date + ".txt";
					System.out.println(filename);
					File aoLaiWapLogFile = new File(filename);
					if (aoLaiWapLogFile.exists()) {
						System.out.println("--start aoLaiWap--" + aoLaiWapLogFile.getName() + " On" + sdf2.format(new Date()));
						ParseAoLaiWapLogUtil.parseOnedayLog(aoLaiWapLogFile);
						System.out.println("--finish aoLaiWap--" + aoLaiWapLogFile.getName() + " On" + sdf2.format(new Date()));
					}
				}

				for (int k=0;k<mshangpinprefix.length;k++) {
					String filename = "/home/parselog/wap_shangpin_logs/" + mshangpinprefix[k] + date + ".txt";
					File shangPinWapLogFile = new File(filename);
					if (shangPinWapLogFile.exists()) {
						System.out.println("--start shangPinWap--" + shangPinWapLogFile.getName() + " On" + sdf2.format(new Date()));
						ParseShangPinWapLogUtil.parseOnedayLog(shangPinWapLogFile);
						System.out.println("--finish shangPinWap--" + shangPinWapLogFile.getName() + " On" + sdf2.format(new Date()));
					}
				}

				for (int k=0;k<apiprefix.length;k++) {
					String filename = "/home/parselog/api_logs/" + apiprefix[k] + date + ".txt";
					File apiLogFile = new File(filename);
					if (apiLogFile.exists()) {
						System.out.println("--start api--" + apiLogFile.getName() + " On" + sdf2.format(new Date()));
						ParseApiLogUtil.parseOnedayLog(apiLogFile);
						System.out.println("--finish api--" + apiLogFile.getName() + " On" + sdf2.format(new Date()));
					}
				}

				System.out.println("parse " + date + " Log:: finished!!");
			}
			System.out.println("parseLog:: finished!!" + sdf2.format(new Date()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "privilegeError";
	}

	// api解析昨天文件
	public String parseAPIYesterdayLog() throws Exception {
		ParseApiLogUtil.parseYesterdayLog();
		ServletActionContext.getResponse().getWriter().print("parseAPILog-success");
		return null;
	}

	// wap解析昨天文件
	public String parseWapYesterdayLog() throws Exception {
		ParseAoLaiWapLogUtil.parseYesterdayWapLog();
		ServletActionContext.getResponse().getWriter().print("parseWapLog-success");
		return null;
	}
}
