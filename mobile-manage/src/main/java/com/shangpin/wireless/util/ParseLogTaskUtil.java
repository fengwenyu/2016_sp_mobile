package com.shangpin.wireless.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.shangpin.wireless.domain.PageView;
import com.shangpin.wireless.manage.service.AccountStatisticsService;

public class ParseLogTaskUtil {
	/**
	 * 时间调度任务
	 * 
	 * @Author: zhouyu
	 * @CreateDate: 2013-05-09
	 * @Return
	 */
	public static void parseYesterdayLog() {
//		 ParseApiLogUtil.parseYesterdayLog();
//		 ParseAoLaiWapLogUtil.parseYesterdayWapLog();
//		 ParseShangPinWapLogUtil.parseYesterdayWapLog();

//		ApplicationContext ac = ApplicationContextUtil.get();
//		AccountStatisticsService accountStatisticsService = (AccountStatisticsService) ac.getBean(AccountStatisticsService.SERVICE_NAME);
//		int num = -1;
//		query(accountStatisticsService, num);
	}

	/**
	 * 统计
	 * 
	 * @throws Exception
	 * 
	 * @Author: zhouyu
	 * @CreatDate 2013-05-22
	 * @Return
	 */
	public static void query(AccountStatisticsService accountStatisticsService, int num) {
		try {
			queryAccountStatistics(accountStatisticsService, num);
			System.out.println("--------------------------");
			queryPageView(accountStatisticsService, num);
			System.out.println("--------------------------");
			queryShangPinTopic(accountStatisticsService, num);
			System.out.println("--------------------------");
			queryThirdLogin(accountStatisticsService, num);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新增注册用户数
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-05-22
	 * @param
	 * @throws Exception
	 * @Return
	 */
	public static String queryAccountStatistics(AccountStatisticsService accountStatisticsService, int num) throws Exception {
		String yesterday = DateUtil.getAfterAmountDay(new Date(), num, "yyyy-MM-dd");
		String sql = "SELECT count(*) FROM accountstatistics WHERE regOrigin='4' AND channelNum=? AND regTime >='" + yesterday + " 00:00:00' AND regTime <='" + yesterday + " 23:59:59'";
		for (int i = 20001; i < 20006; i++) {
			Integer totalCount = accountStatisticsService.executeSqlCount(sql.replace("?", i + ""));
			System.out.println("渠道：" + i + "总的新增注册用户数是：" + totalCount);
			Integer count1 = accountStatisticsService.executeSqlCount(sql.replace("?", i + "") + " AND productNum =102");
			System.out.println("渠道：" + i + "下的102产品新增注册用户数是：" + count1);
			Integer count2 = accountStatisticsService.executeSqlCount(sql.replace("?", i + "") + " AND productNum =10000");
			System.out.println("渠道：" + i + "下的10000产品新增注册用户数是：" + count2);
		}

		return null;
	}

	/**
	 * update访问数
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-05-22
	 * @param
	 * @throws Exception
	 * @Return
	 */
	@SuppressWarnings("unchecked")
	public static String queryPageView(AccountStatisticsService accountStatisticsService, int num) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String yesterday = DateUtil.getAfterAmountDay(new Date(), num, "yyyy-MM-dd");
		List<PageView> list = new ArrayList<PageView>();
		String hql = "FROM PageView WHERE apiname='update' AND channelNum=? GROUP BY imei ORDER BY createTime DESC";
		for (int i = 20001; i < 20006; i++) {
			list = accountStatisticsService.executeHql(hql.replace("?", i + ""));
			int count = 0;
			for (PageView pageView : list) {
				long time = pageView.getCreateTime().getTime();
				if (time >= sdf.parse(yesterday + " 00:00:00").getTime() && sdf.parse(yesterday + " 23:59:59").getTime() >= time && pageView.getProductNum() == 101) {
					count++;
				}
			}
			System.out.println("渠道：" + i + "下的101产品update访问数是：" + count);
		}
		return null;
	}

	/**
	 * shangpintopic访问数
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-05-22
	 * @param
	 * @throws Exception
	 * @Return
	 */
	@SuppressWarnings("unchecked")
	public static String queryShangPinTopic(AccountStatisticsService accountStatisticsService, int num) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String yesterday = DateUtil.getAfterAmountDay(new Date(), num, "yyyy-MM-dd");
		List<PageView> list = new ArrayList<PageView>();
		String hql = "FROM PageView WHERE apiname='shangpintopic' AND channelNum=? GROUP BY imei ORDER BY createTime DESC";
		for (int i = 20001; i < 20006; i++) {
			list = accountStatisticsService.executeHql(hql.replace("?", i + ""));
			int count = 0;
			for (PageView pageView : list) {
				long time = pageView.getCreateTime().getTime();
				if (time >= sdf.parse(yesterday + " 00:00:00").getTime() && sdf.parse(yesterday + " 23:59:59").getTime() >= time && pageView.getProductNum() == 102) {
					count++;
				}
			}
			System.out.println("渠道：" + i + "下的102产品shangpintopic访问数是：" + count);
		}
		return null;
	}

	/**
	 * thirdlogin访问数
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-05-22
	 * @param
	 * @throws Exception
	 * @Return
	 */
	@SuppressWarnings("unchecked")
	public static String queryThirdLogin(AccountStatisticsService accountStatisticsService, int num) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String yesterday = DateUtil.getAfterAmountDay(new Date(), num, "yyyy-MM-dd");
		List<PageView> list = new ArrayList<PageView>();
		String hql = "FROM PageView WHERE apiname='thirdlogin' AND channelNum=? GROUP BY imei ORDER BY createTime DESC";
		for (int i = 20001; i < 20006; i++) {
			list = accountStatisticsService.executeHql(hql.replace("?", i + ""));
			int count = 0;
			for (PageView pageView : list) {
				long time = pageView.getCreateTime().getTime();
				if (time >= sdf.parse(yesterday + " 00:00:00").getTime() && sdf.parse(yesterday + " 23:59:59").getTime() >= time && pageView.getProductNum() == 102) {
					count++;
				}
			}
			System.out.println("渠道：" + i + "下的102产品thirdlogin访问数是：" + count);
		}
		return null;
	}

}
