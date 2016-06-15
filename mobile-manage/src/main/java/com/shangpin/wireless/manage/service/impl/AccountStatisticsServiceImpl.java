package com.shangpin.wireless.manage.service.impl;

import java.awt.Color;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.AccountStatisticsDao;
import com.shangpin.wireless.domain.AccountStatistics;
import com.shangpin.wireless.manage.service.AccountStatisticsService;
import com.shangpin.wireless.util.DateUtil;
import com.shangpin.wireless.util.HqlHelper;

/**
 * 日志解析
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-24
 */
@Service(AccountStatisticsService.SERVICE_NAME)
public class AccountStatisticsServiceImpl implements AccountStatisticsService {
	@Resource(name = AccountStatisticsDao.DAO_NAME)
	private AccountStatisticsDao accountStatisticsDao;

	public void update(AccountStatistics entity) throws Exception {
		accountStatisticsDao.update(entity);
	}

	/**
	 * 根据HQL语句获取数据集合
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param hql
	 * @Return List
	 */
	public List executeHql(String hql) throws Exception {
		return accountStatisticsDao.executeHql(hql);
	}

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param entity
	 * @Return
	 */
	public void save(AccountStatistics entity) throws Exception {
		accountStatisticsDao.save(entity);
	}

	/**
	 * 根据组合条件获取实体，如果条件为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-17
	 * @param hqlHelper
	 * @Return T
	 */
	public AccountStatistics getByCondition(HqlHelper hqlHelper) throws Exception {
		return accountStatisticsDao.getByCondition(hqlHelper);
	}

	/**
	 * 根据sql语句获取结果集条数
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param sql
	 *            查询语句
	 * @Return Long
	 */
	public Integer executeSqlCount(String sql) throws Exception {
		return accountStatisticsDao.executeSqlCount(sql);
	}

	/**
	 * 根据日期生成用户柱状图
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-28
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param outputStream
	 *            图输出流
	 * @Return Map
	 */
	public OutputStream getAccountImageByDate(String startDate, String endDate, OutputStream outputStream, String type, Long productNum, Long channelNum) throws Exception {
		// 获取数据集对象
		CategoryDataset dataset = createDatasetByDate(startDate, endDate, type, productNum, channelNum);
		// 创建图形对象
		JFreeChart jfreechart = ChartFactory.createBarChart3D("按天汇总新增用户数", "按天", "数量", dataset, PlotOrientation.VERTICAL, true, true, false);
		// 获得图表区域对象
		CategoryPlot categoryPlot = (CategoryPlot) jfreechart.getPlot();
		// 数据轴精度
		NumberAxis na = (NumberAxis) categoryPlot.getRangeAxis();
		na.setAutoRangeIncludesZero(true);
		DecimalFormat df = new DecimalFormat("#0");
		na.setNumberFormatOverride(df);
		// 设置网格线可见
		categoryPlot.setDomainGridlinesVisible(true);
		// 获得x轴对象
		CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
		// 设置x轴显示的分类名称的显示位置，如果不设置则水平显示
		// 设置后，可以斜像显示，但分类角度，图表空间有限时，建议采用
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.39269908169872414D));
		categoryAxis.setCategoryMargin(0.5D);
		categoryAxis.setUpperMargin(0.02);
		// 设置距离图片右端距离
		categoryAxis.setLowerMargin(0.02);
		// categoryAxis.setMaximumCategoryLabelWidthRatio(2.0F);
		// 获显示图形对象
		BarRenderer3D barRenderer3d = (BarRenderer3D) categoryPlot.getRenderer();
		// 设置不显示边框线
		barRenderer3d.setDrawBarOutline(false);
		barRenderer3d.setItemMargin(2F);
		barRenderer3d.setMaximumBarWidth(0.5F); // 设置柱子宽度
		// barRenderer3d.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barRenderer3d.setBaseItemLabelsVisible(true);
		barRenderer3d.setBaseItemLabelPaint(Color.BLUE);// 设置数值颜色，默认黑色
		// 将图表已数据流的方式返回给客户端
		ChartUtilities.writeChartAsPNG(outputStream, jfreechart, 1000, 270);
		return outputStream;
	}

	/**
	 * 按日期返回数据集
	 * 
	 * @return
	 * @throws Exception
	 */
	private CategoryDataset createDatasetByDate(String startDate, String endDate, String type, Long productNum, Long channelNum) throws Exception {
		Map<String, String> map = sortAccountByDate(startDate, endDate, type, productNum, channelNum);
		DefaultCategoryDataset defaultdataset = new DefaultCategoryDataset();
		for (Entry<String, String> entry : map.entrySet()) {
			defaultdataset.addValue(Integer.valueOf(entry.getValue()), "用户", entry.getKey());
		}
		return defaultdataset;
	}

	/**
	 * 根据日期来划分用户
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-28
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @Return Map
	 */
	public Map<String, String> sortAccountByDate(String startDate, String endDate, String type, Long productNum, Long channelNum) throws Exception {
		// System.out.println(startDate + "/" + endDate);
		Map<String, String> map = new TreeMap<String, String>();
		List<AccountStatistics> data = getAccountByDate(startDate, endDate, type, productNum, channelNum);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date begin = sdf.parse(startDate);
		Date end = sdf.parse(endDate);
		double between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
		Double day = between / (24 * 3600);
		for (int i = 0; i <= day; i++) {

			Calendar startTemp = Calendar.getInstance();
			startTemp.setTime(sdf.parse(startDate));
			startTemp.add(Calendar.DATE, i);// 增加一天
			// startTemp.add(Calendar.MONTH, n);// 增加一个月

			Calendar endTemp = Calendar.getInstance();
			endTemp.setTime(sdf.parse(startDate));// 增加一天
			endTemp.add(Calendar.DATE, i + 1);

			long startTime = startTemp.getTimeInMillis();
			// System.out.println("start time is " + sdf.format(startTemp.getTime()));
			long endTime = endTemp.getTimeInMillis();
			// System.out.println("end time is " + sdf.format(startTemp.getTime()));

			int count = 0;
			for (AccountStatistics accountStatistics : data) {
				long time = 0l;
				if ("1".equals(type))
					time = accountStatistics.getRegTime().getTime();
				else
					time = accountStatistics.getLoginTime().getTime();
				// System.out.println(accountStatistics.getLoginTime());
				if (time - startTime >= 0 && endTime - time > 0) {
					count++;
				}
			}
			map.put(sdf.format(startTemp.getTime()), String.valueOf(count));
		}
		// for (Entry<String, String> entry : map.entrySet()) {
		// System.out.println(entry.getKey() + "************" + entry.getValue());
		// }
		return map;
	}

	/**
	 * 根据时间查询用户量
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-28
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @Return List
	 */
	public List getAccountByDate(String startDate, String endDate, String type, Long productNum, Long channelNum) throws Exception {
		if ("1".equals(type)) {// 1为查询新增用户，2为查询独立用户
			if (channelNum == null)
				return accountStatisticsDao.executeHql("FROM AccountStatistics WHERE regOrigin='4' AND regTime >='" + startDate + " 00:00:00" + "' AND regTime <='" + endDate + " 23:59:59" + "' AND productNum =" + productNum + " ORDER BY regTime");
			else
				return accountStatisticsDao.executeHql("FROM AccountStatistics WHERE regOrigin='4' AND regTime >='" + startDate + " 00:00:00" + "' AND regTime <='" + endDate + " 23:59:59" + "' AND productNum =" + productNum + " AND channelNum =" + channelNum + " ORDER BY regTime");
		} else {
			if (channelNum == null)
				return accountStatisticsDao.executeHql("FROM AccountStatistics WHERE regOrigin='4' AND loginTime >='" + startDate + " 00:00:00" + "' AND loginTime <='" + endDate + " 23:59:59" + "' AND productNum =" + productNum + " ORDER BY loginTime");
			else
				return accountStatisticsDao.executeHql("FROM AccountStatistics WHERE regOrigin='4' AND loginTime >='" + startDate + " 00:00:00" + "' AND loginTime <='" + endDate + " 23:59:59" + "' AND productNum =" + productNum + " AND channelNum =" + channelNum + " ORDER BY loginTime");

		}
	}

	/**
	 * 根据日期生成用户柱状图
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-28
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @param outputStream
	 *            图输出流
	 * @Return Map
	 */
	public OutputStream getAccountImageByHour(String startDate, String endDate, OutputStream outputStream, String type, Long productNum, Long channelNum) throws Exception {
		// 获取数据集对象
		CategoryDataset dataset = createDatasetByHour(startDate, endDate, type, productNum, channelNum);
		// 创建图形对象
		JFreeChart jfreechart = ChartFactory.createBarChart3D("按小时汇总新增用户数", "按小时", "数量", dataset, PlotOrientation.VERTICAL, true, true, false);
		// 获得图表区域对象
		CategoryPlot categoryPlot = (CategoryPlot) jfreechart.getPlot();
		// 数据轴精度
		NumberAxis na = (NumberAxis) categoryPlot.getRangeAxis();
		na.setAutoRangeIncludesZero(true);
		DecimalFormat df = new DecimalFormat("#0");
		na.setNumberFormatOverride(df);
		// 设置网格线可见
		categoryPlot.setDomainGridlinesVisible(true);
		// categoryPlot.setDomainAxisLocation(AxisLocation.TOP_OR_RIGHT);
		// 获得x轴对象
		CategoryAxis categoryAxis = categoryPlot.getDomainAxis();
		// 设置x轴显示的分类名称的显示位置，如果不设置则水平显示
		// 设置后，可以斜像显示，但分类角度，图表空间有限时，建议采用
		categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(0.39269908169872414D));
		categoryAxis.setCategoryMargin(0.37D);
		categoryAxis.setUpperMargin(0.02);
		// 设置距离图片右端距离
		categoryAxis.setLowerMargin(0.02);
		// categoryAxis.setMaximumCategoryLabelWidthRatio(2.0F);
		// 获显示图形对象
		BarRenderer3D barRenderer3d = (BarRenderer3D) categoryPlot.getRenderer();
		// 设置不显示边框线
		barRenderer3d.setDrawBarOutline(false);
		// barRenderer3d.setSeriesPaint(0, Color.ORANGE); // 设置柱的颜色
		barRenderer3d.setItemMargin(2F); // 设置每个地区所包含的平行柱的之间距离
		barRenderer3d.setMaximumBarWidth(2F); // 设置柱子宽度
		// barRenderer3d.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barRenderer3d.setBaseItemLabelsVisible(true);
		barRenderer3d.setBaseItemLabelPaint(Color.BLUE);// 设置数值颜色，默认黑色
		// 将图表已数据流的方式返回给客户端
		ChartUtilities.writeChartAsPNG(outputStream, jfreechart, 800, 270);
		return outputStream;
	}

	/**
	 * 按小时返回数据集
	 * 
	 * @return
	 * @throws Exception
	 */
	private CategoryDataset createDatasetByHour(String startDate, String endDate, String type, Long productNum, Long channelNum) throws Exception {
		Map<String, String> map = sortOrderByHour(startDate, endDate, type, productNum, channelNum);
		DefaultCategoryDataset defaultdataset = new DefaultCategoryDataset();
		for (Entry<String, String> entry : map.entrySet()) {
			defaultdataset.addValue(Integer.valueOf(entry.getValue()), "用户", entry.getKey());
		}
		return defaultdataset;
	}

	/**
	 * 根据时间来划分用户
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-28
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @Return Map
	 */
	public Map<String, String> sortOrderByHour(String startDate, String endDate, String type, Long productNum, Long channelNum) throws Exception {

		try {
			// System.out.println(startDate + "/" + endDate);
			Map<String, String> map = new TreeMap<String, String>();
			List<AccountStatistics> data = getAccountByDate(startDate, endDate, type, productNum, channelNum);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			List<Long> list = new ArrayList<Long>();
			for (int j = 0; j < 24; j++) {
				int count = 0;
				Calendar startTime = Calendar.getInstance();
				startTime.setTime(sdf2.parse(startDate + " 00:00:00"));
				startTime.add(Calendar.HOUR_OF_DAY, j);
				// System.out.println("startTime is " + sdf2.format(startTime.getTime()));
				Calendar endTime = Calendar.getInstance();
				if (j != 23) {
					endTime.setTime(sdf2.parse(startDate + " 00:00:00"));
					endTime.add(Calendar.HOUR_OF_DAY, j + 1);
				} else {
					endTime.setTime(sdf2.parse(startDate + " 00:59:59"));
					endTime.add(Calendar.HOUR_OF_DAY, j);
				}
				// System.out.println("endTime is " + sdf2.format(endTime.getTime()));
				for (AccountStatistics accountStatistics : data) {
					if (DateUtil.compareDate(accountStatistics.getRegTime(), startTime.getTime()) > 0 && DateUtil.compareDate(endTime.getTime(), accountStatistics.getRegTime()) > 0) {
						count++;
						// System.out.println("orderStatistics time is " + orderStatistics.getOrderTime());
						// System.out.println("count is " + count);
						list.add(accountStatistics.getId());
					}
				}
				if (map.size() < 24) {
					if ("00:00:00".equals(sdf1.format(endTime.getTime())))
						map.put("24:00:00", String.valueOf(count));
					else {
						map.put(sdf1.format(endTime.getTime()), String.valueOf(count));
						// System.out.println("小于24小时map's key/value：" + sdf1.format(endTime.getTime()) + "/" + String.valueOf(count));
					}

				} else {
					String countStr = "";
					if ("00:00:00".equals(sdf1.format(endTime.getTime()))) {
						countStr = map.get("24:00:00");
						count = count + Integer.valueOf(countStr);
						map.put("24:00:00", String.valueOf(count));
					} else {
						countStr = map.get(sdf1.format(endTime.getTime()));
						count = count + Integer.valueOf(countStr);
						map.put(sdf1.format(endTime.getTime()), String.valueOf(count));
					}
					// System.out.println("大于24小时 map's key/value：" + sdf1.format(endTime.getTime()) + "/" + String.valueOf(count));
				}
			}
			// for (Entry<String, String> entry : map.entrySet()) {
			// System.out.println(entry.getKey() + "************" + entry.getValue());
			// }
			// for (OrderStatistics order : data) {
			// if (!list.contains(order.getId()))
			// System.out.println(order.getId());
			// }
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取所有实体集合
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-04-16
	 * @param
	 * @Return List
	 */
	public List<AccountStatistics> findAll() throws Exception {
		return accountStatisticsDao.findAll();
	}

	public void delete(Long id) throws Exception {
		accountStatisticsDao.delete(id);
	}

}
