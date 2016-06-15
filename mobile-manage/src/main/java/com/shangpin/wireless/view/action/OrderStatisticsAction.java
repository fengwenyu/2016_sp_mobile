package com.shangpin.wireless.view.action;

import java.awt.Font;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.shangpin.wireless.base.action.BaseAction;
import com.shangpin.wireless.domain.OrderStatistics;
import com.shangpin.wireless.domain.ReturnObject;
import com.shangpin.wireless.util.DateUtil;
import com.shangpin.wireless.util.IPUtil;
import com.shangpin.wireless.util.JsonUtil;
import com.shangpin.wireless.util.SqlHelper;
import com.shangpin.wireless.util.StringUtils;

/**
 * 订单统计Action
 * 
 * @Author zhouyu
 * @CreatDate 2013-01-23
 */
@Controller
@Scope("prototype")
public class OrderStatisticsAction extends BaseAction<OrderStatistics> {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(OrderStatisticsAction.class);
	private String date;
	private int page = 1;
	private int rows;

	/**
	 * 订单总量
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-24
	 * @param
	 * @throws Exception
	 * @Return
	 */
	public String query() throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		/** 获取查询时间用于页面展示 */
		String startDate = ServletActionContext.getRequest().getParameter("startDate");
		String endDate = ServletActionContext.getRequest().getParameter("endDate");
		if (StringUtils.isEmpty(startDate, endDate)) {
			Date date = new Date();
			endDate = sdf.format(date);
			startDate = DateUtil.getAfterAmountDay(date, -30, "yyyy-MM-dd");
		} else {
			Date start = sdf.parse(startDate);
			Date end = sdf.parse(endDate);
			long days = (end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24);
			if (days > 15) {
				ActionContext.getContext().getValueStack().set("picFlag", "0");// 0为有表格没图，1为有图没表格
				/** 订单总量统计 */
				Map<String, String> orderMap = orderStatisticsService.sortOrderByDate(startDate, endDate);
				ServletActionContext.getRequest().setAttribute("orderMap", orderMap);
			} else {
				ActionContext.getContext().getValueStack().set("picFlag", "1");// 0为有表格没图，1为有图没表格
			}
		}
		ActionContext.getContext().getValueStack().set("startDate", startDate);
		ActionContext.getContext().getValueStack().set("endDate", endDate);
		/** 订单总量统计 */
		SqlHelper sqlQueryCountHelper = new SqlHelper("orderstatistics");
		SqlHelper sqlQuerySuccCountHelper = new SqlHelper("orderstatistics");
		SqlHelper sqlQueryFailCountHelper = new SqlHelper("orderstatistics");
		SqlHelper sqlQueryNonPaymentCountHelper = new SqlHelper("orderstatistics");
		sqlQueryCountHelper.addColumn("count(*)");
		sqlQueryCountHelper.addWhereCondition("orderTime>='" + startDate + " 00:00:00" + "'");
		sqlQueryCountHelper.addWhereCondition("orderTime<='" + endDate + " 23:59:59" + "'");
		Integer totalCount = orderStatisticsService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
		ActionContext.getContext().getValueStack().set("totalCount", totalCount);
		sqlQuerySuccCountHelper.addColumn("count(*)");
		sqlQuerySuccCountHelper.addWhereCondition("orderTime>='" + startDate + " 00:00:00" + "'");
		sqlQuerySuccCountHelper.addWhereCondition("orderTime<='" + endDate + " 23:59:59" + "'");
		sqlQuerySuccCountHelper.addWhereCondition("ispaid=1");
		Integer successPaymentCount = orderStatisticsService.executeSqlCount(sqlQuerySuccCountHelper.getQueryListSql());
		ActionContext.getContext().getValueStack().set("successPaymentCount", successPaymentCount);
		sqlQueryFailCountHelper.addColumn("count(*)");
		sqlQueryFailCountHelper.addWhereCondition("orderTime>='" + startDate + " 00:00:00" + "'");
		sqlQueryFailCountHelper.addWhereCondition("orderTime<='" + endDate + " 23:59:59" + "'");
		sqlQueryFailCountHelper.addWhereCondition("ispaid=0");
		Integer failPaymentCount = orderStatisticsService.executeSqlCount(sqlQueryFailCountHelper.getQueryListSql());
		ActionContext.getContext().getValueStack().set("failPaymentCount", failPaymentCount);
		sqlQueryNonPaymentCountHelper.addColumn("count(*)");
		sqlQueryNonPaymentCountHelper.addWhereCondition("orderTime>='" + startDate + " 00:00:00" + "'");
		sqlQueryNonPaymentCountHelper.addWhereCondition("orderTime<='" + endDate + " 23:59:59" + "'");
		sqlQueryNonPaymentCountHelper.addWhereCondition("ispaid IS NULL");
		Integer nonPaymentCount = orderStatisticsService.executeSqlCount(sqlQueryNonPaymentCountHelper.getQueryListSql());
		ActionContext.getContext().getValueStack().set("nonPaymentCount", nonPaymentCount);
		return "query";
	}

	/**
	 * 获取图片
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-03-22
	 * @param
	 * @throws Exception
	 * @Return
	 */
	public String image() {
		try {
			String type = ServletActionContext.getRequest().getParameter("type");
			// 打开一个输出流
			OutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
			ServletActionContext.getResponse().setHeader("Pragma", "No-cache");
			ServletActionContext.getResponse().setHeader("Cache-Control", "no-cache");
			ServletActionContext.getResponse().setDateHeader("Expires", 0);
			ServletActionContext.getResponse().setContentType("image/png");
			String startDate = ServletActionContext.getRequest().getParameter("startDate");
			String endDate = ServletActionContext.getRequest().getParameter("endDate");
			if ("day".equals(type)) {
				outputStream = orderStatisticsService.getOrderImageByDate(startDate, endDate, outputStream);
			} else if ("hour".equals(type)) {
				outputStream = orderStatisticsService.getOrderImageByHour(startDate, endDate, outputStream);
			}
		} catch (Exception e) {

		}
		return null;
	}

	/**
	 * 获取用户下单频率
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-03-22
	 * @param
	 * @throws Exception
	 * @Return
	 */
	public String orderFrequencyOfUserQuery() throws Exception {
		try {
			if (date != null && !"".equals(date.trim())) {
				String recordListSql = "SELECT COUNT(*) num, o.userId, a.username FROM orderstatistics o, accountstatistics a WHERE a.userId = o.userId AND a.createTime>='" + date + " 00:00:00' AND a.createTime<'" + date + " 23:59:59' GROUP BY o.userId ORDER BY num DESC";
				List<HashMap> queryList = orderStatisticsService.executeSqlToMap(recordListSql, page, rows);
				List<HashMap> executeSqlToMap = orderStatisticsService.executeSqlToMap(recordListSql);
				ReturnObject returnObject = new ReturnObject();
				returnObject.setTotal(executeSqlToMap.size());
				returnObject.setRows(queryList);
				ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("OrderStatisticsAction-orderFrequencyOfUserQuery() " + e);
		}
		return null;
	}

	/**
	 * 获取订单变化率
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-03-22
	 * @param
	 * @throws Exception
	 * @Return
	 */
	public String rateOfChange() throws Exception {
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String yesterday = DateUtil.getAfterAmountDay(sdf.parse(date), -1, "yyyy-MM-dd");
			String lastWeek = DateUtil.getAfterAmountDay(sdf.parse(date), -7, "yyyy-MM-dd");
			String lastMonth = DateUtil.getLastMonth(date, 0, -1, 0);
			ActionContext.getContext().getValueStack().set("yesterday", yesterday);
			ActionContext.getContext().getValueStack().set("lastWeek", lastWeek);
			ActionContext.getContext().getValueStack().set("lastMonth", lastMonth);
			List dateList = orderStatisticsService.getOrderByDate(date, date);
			double dateNum = dateList.size();
			List yesterdayList = orderStatisticsService.getOrderByDate(yesterday, yesterday);
			double yesterdayNum = yesterdayList.size();
			List lastWeekList = orderStatisticsService.getOrderByDate(lastWeek, lastWeek);
			double lastWeekNum = lastWeekList.size();
			List lastMonthList = orderStatisticsService.getOrderByDate(lastMonth, lastMonth);
			int lastMonthNum = lastMonthList.size();
			double yesterdayRate = 0l;
			double lastWeekRate = 0l;
			double lastMonthRate = 0l;
			DecimalFormat df = new DecimalFormat("#.##");
			if (yesterdayNum > 0) {
				yesterdayRate = Double.valueOf(df.format((dateNum - yesterdayNum) / yesterdayNum));
			}
			if (lastWeekNum > 0) {
				lastWeekRate = Double.valueOf(df.format((dateNum - lastWeekNum) / lastWeekNum));
			}
			if (lastMonthNum > 0) {
				lastMonthRate = Double.valueOf(df.format((dateNum - lastMonthNum) / lastMonthNum));
			}
			String str = "";
			if (yesterdayRate > 0) {
				str = "增加" + df.format(yesterdayRate * 100) + "%";
			} else if (yesterdayRate < 0) {
				str = "降低" + df.format(Math.abs(yesterdayRate * 100)) + "%";
			} else {
				str = "暂无数据";
			}
			ActionContext.getContext().getValueStack().set("yesterdayRate", str);
			if (lastWeekRate > 0) {
				str = "增加" + df.format(lastWeekRate * 100) + "%";
			} else if (lastWeekRate < 0) {
				str = "降低" + df.format(Math.abs(lastWeekRate * 100)) + "%";
			} else {
				str = "暂无数据";
			}
			ActionContext.getContext().getValueStack().set("lastWeekRate", str);
			if (lastMonthRate > 0) {
				str = "增加" + df.format(lastMonthRate * 100) + "%";
			} else if (lastMonthRate < 0) {
				str = "降低" + df.format(Math.abs(lastMonthRate * 100)) + "%";
			} else {
				str = "暂无数据";
			}
			ActionContext.getContext().getValueStack().set("lastMonthRate", str);

		}
		return "rateOfChange";
	}

	public String orderFrequencyOfUser() throws Exception {
		return "orderFrequencyOfUser";
	}

	public String updateCityByIp() throws Exception {
		List<OrderStatistics> findAll = orderStatisticsService.findAll();
		for (OrderStatistics order : findAll) {
			if (StringUtils.isNotEmpty(order.getIp()) && StringUtils.isEmpty(order.getCity())) {
				order.setCity(IPUtil.getCountryCityByIp(order.getIp()));
				orderStatisticsService.update(order);
			}
		}
		System.out.println("success");
		return null;
	}

	// 成功订单和失败订单对比图
	@Deprecated
	public String succVsFail() throws Exception {
		SqlHelper sqlQueryCountHelper = new SqlHelper("orderstatistics");
		SqlHelper sqlQuerySuccCountHelper = new SqlHelper("orderstatistics");
		SqlHelper sqlQueryFailCountHelper = new SqlHelper("orderstatistics");
		sqlQueryCountHelper.addColumn("count(*)");
		Integer totalCount = orderStatisticsService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());

		sqlQuerySuccCountHelper.addColumn("count(*)");
		sqlQuerySuccCountHelper.addWhereCondition("isok=1");
		Integer successCount = orderStatisticsService.executeSqlCount(sqlQuerySuccCountHelper.getQueryListSql());

		sqlQueryFailCountHelper.addColumn("count(*)");
		sqlQueryFailCountHelper.addWhereCondition("isok=0");
		Integer failCount = orderStatisticsService.executeSqlCount(sqlQueryFailCountHelper.getQueryListSql());

		// 打开一个输出流
		// OutputStream outputStream = new FileOutputStream("d:\\PieChart.png");
		OutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
		ServletActionContext.getResponse().setHeader("Pragma", "No-cache");
		ServletActionContext.getResponse().setHeader("Cache-Control", "no-cache");
		ServletActionContext.getResponse().setDateHeader("Expires", 0);
		ServletActionContext.getResponse().setContentType("image/png");
		// 获取数据集对象
		DefaultPieDataset dataset = new DefaultPieDataset();
		dataset.setValue("成功", successCount);
		dataset.setValue("失败", failCount);
		// 创建图形对象
		JFreeChart jfreechart = ChartFactory.createPieChart3D("订单统计分析", dataset, true, true, false);
		// 获得图表区域对象
		PiePlot pieplot = (PiePlot) jfreechart.getPlot();
		// 设置图表区域的标签字体
		pieplot.setLabelFont(new Font("宋体", 0, 12));
		// 设置图表区域无数据时的默认显示文字
		pieplot.setNoDataMessage("没有订单数据");
		// 设置图表区域不是圆形，由于是3D的饼形图，建议设置为false
		pieplot.setCircular(false);
		// 设置图表区域文字与图表区域的间隔距离，0.02表示2%
		pieplot.setLabelGap(0.02D);
		// 将图表已数据流的方式返回给客户端
		ChartUtilities.writeChartAsPNG(outputStream, jfreechart, 500, 270);
		return null;
	}

	// 根据时间查询订单
	@Deprecated
	public String getOrder(String type) throws Exception {
		// 打开一个输出流
		OutputStream outputStream = ServletActionContext.getResponse().getOutputStream();
		ServletActionContext.getResponse().setHeader("Pragma", "No-cache");
		ServletActionContext.getResponse().setHeader("Cache-Control", "no-cache");
		ServletActionContext.getResponse().setDateHeader("Expires", 0);
		ServletActionContext.getResponse().setContentType("image/png");

		String startDate = ServletActionContext.getRequest().getParameter("startDate");
		String endDate = ServletActionContext.getRequest().getParameter("endDate");
		if ("day".equals(type)) {
			outputStream = orderStatisticsService.getOrderImageByDate(startDate, endDate, outputStream);
		} else if ("hour".equals(type)) {
			outputStream = orderStatisticsService.getOrderImageByHour(startDate, endDate, outputStream);
		}
		return null;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}
}
