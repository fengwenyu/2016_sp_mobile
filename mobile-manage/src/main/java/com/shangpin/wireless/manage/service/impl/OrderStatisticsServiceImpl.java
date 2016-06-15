package com.shangpin.wireless.manage.service.impl;

import java.awt.Color;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.ParseException;
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
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.OrderStatisticsDao;
import com.shangpin.wireless.domain.OrderStatistics;
import com.shangpin.wireless.manage.service.OrderStatisticsService;
import com.shangpin.wireless.util.DateUtil;
import com.shangpin.wireless.util.HqlHelper;

/**
 * 订单统计
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-24
 */
@Service(OrderStatisticsService.SERVICE_NAME)
public class OrderStatisticsServiceImpl implements OrderStatisticsService {
	@Resource(name = OrderStatisticsDao.DAO_NAME)
	private OrderStatisticsDao orderStatisticsDao;

	/**
	 * 保存实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	public void save(OrderStatistics entity) throws Exception {
		orderStatisticsDao.save(entity);
	}

	/**
	 * 根据组合条件获取实体，如果条件为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-17
	 * @param hqlHelper
	 * @Return T
	 */
	public OrderStatistics getByCondition(HqlHelper hqlHelper) throws Exception {
		return orderStatisticsDao.getByCondition(hqlHelper);
	}
	
	/**
	 * 根据组合条件获取实体集合
	 * 
	 * @Author: wangwenguan
	 * @CreateDate: 2013-11-18
	 * @param hqlHelper
	 *            查询对象
	 * @param dbType
	 *            数据库类型
	 * @Return: T
	 */
	public List<OrderStatistics> getListByCondition(HqlHelper hqlHelper) throws Exception {
		return orderStatisticsDao.getListByCondition(hqlHelper);
	}

	/**
	 * 更新实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-19
	 * @param entity
	 * @Return
	 */
	public void update(OrderStatistics entity) throws Exception {
		orderStatisticsDao.update(entity);
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
		return orderStatisticsDao.executeSqlCount(sql);
	}

	/**
	 * 根据时间查询订单量
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-28
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @Return List
	 */
	public List getOrderByDate(String startDate, String endDate) throws Exception {
		return orderStatisticsDao.executeHql("FROM OrderStatistics WHERE orderTime >='" + startDate + " 00:00:00" + "' AND orderTime <='" + endDate + " 23:59:59" + "' ORDER BY orderTime");
	}

	/**
	 * 根据日期来划分订单
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-28
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @Return Map
	 */
	public Map<String, String> sortOrderByDate(String startDate, String endDate) throws Exception {
		// System.out.println(startDate + "/" + endDate);
		Map<String, String> map = new TreeMap<String, String>();
		List<OrderStatistics> data = getOrderByDate(startDate, endDate);
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
			for (OrderStatistics orderStatistics : data) {
				long time = orderStatistics.getOrderTime().getTime();
				// System.out.println(orderStatistics.getOrderTime());
				if (time - startTime > 0 && endTime - time > 0) {
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
	 * 根据时间来划分订单
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-01-28
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @Return Map
	 */
	public Map<String, String> sortOrderByHour(String startDate, String endDate) throws Exception {

		try {
			// System.out.println(startDate + "/" + endDate);
			Map<String, String> map = new TreeMap<String, String>();
			List<OrderStatistics> data = getOrderByDate(startDate, endDate);
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
				for (OrderStatistics orderStatistics : data) {
					if (DateUtil.compareDate(orderStatistics.getOrderTime(), startTime.getTime()) > 0 && DateUtil.compareDate(endTime.getTime(), orderStatistics.getOrderTime()) > 0) {
						count++;
						// System.out.println("orderStatistics time is " + orderStatistics.getOrderTime());
						// System.out.println("count is " + count);
						list.add(orderStatistics.getId());
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

	@Deprecated
	public Map<String, String> sortOrderByHour1(String startDate, String endDate) throws Exception {

		try {
			System.out.println(startDate + "/" + endDate);
			Map<String, String> map = new TreeMap<String, String>();
			List<OrderStatistics> data = getOrderByDate(startDate, endDate);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
			SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date begin = sdf.parse(startDate);
			Date end = sdf.parse(endDate);
			double between = (end.getTime() - begin.getTime()) / 1000;// 除以1000是为了转换成秒
			Double day = between / (24 * 3600);
			for (int i = 0; i <= day; i++) {
				Calendar startTemp = Calendar.getInstance();
				startTemp.setTime(sdf.parse(startDate));
				startTemp.add(Calendar.DATE, i);// 增加一天
				for (int j = 0; j < 24; j++) {
					int count = 0;
					Calendar startTime = Calendar.getInstance();
					startTime.setTime(sdf.parse(startDate + " 00:00:00"));
					startTime.add(Calendar.HOUR_OF_DAY, j);
					System.out.println("startTime is " + sdf2.format(startTime.getTime()));
					Calendar endTime = Calendar.getInstance();
					endTime.setTime(sdf.parse(startDate + " 00:00:00"));
					endTime.add(Calendar.HOUR_OF_DAY, j + 1);
					System.out.println("endTime is " + sdf2.format(endTime.getTime()));
					for (OrderStatistics orderStatistics : data) {
						if (DateUtil.compareDate(orderStatistics.getOrderTime(), startTime.getTime()) > 0 && DateUtil.compareDate(endTime.getTime(), orderStatistics.getOrderTime()) > 0) {
							count++;
							System.out.println("orderStatistics time is " + orderStatistics.getOrderTime());
							System.out.println("count is " + count);
						}
					}
					if (map.size() < 24) {
						if ("00:00:00".equals(sdf1.format(endTime.getTime())))
							map.put("24:00:00", String.valueOf(count));
						else {
							map.put(sdf1.format(endTime.getTime()), String.valueOf(count));
							System.out.println("小于24小时map's key/value：" + sdf1.format(endTime.getTime()) + "/" + String.valueOf(count));
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
						System.out.println("大于24小时 map's key/value：" + sdf1.format(endTime.getTime()) + "/" + String.valueOf(count));
					}
				}
			}
			for (Entry<String, String> entry : map.entrySet()) {
				System.out.println(entry.getKey() + "************" + entry.getValue());
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据日期生成订单柱状图
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
	public OutputStream getOrderImageByDate(String startDate, String endDate, OutputStream outputStream) throws Exception {
		// 获取数据集对象
		CategoryDataset dataset = createDatasetByDate(startDate, endDate);
		// 创建图形对象
		JFreeChart jfreechart = ChartFactory.createBarChart3D("按天汇总订单", "按天", "数量", dataset, PlotOrientation.VERTICAL, true, true, false);
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
		barRenderer3d.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barRenderer3d.setBaseItemLabelsVisible(true);
		barRenderer3d.setBaseItemLabelPaint(Color.BLUE);// 设置数值颜色，默认黑色
		// 将图表已数据流的方式返回给客户端
		ChartUtilities.writeChartAsPNG(outputStream, jfreechart, 1000, 270);
		return outputStream;
	}

	/**
	 * 根据日期生成订单柱状图
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
	public OutputStream getOrderImageByHour(String startDate, String endDate, OutputStream outputStream) throws Exception {
		// 获取数据集对象
		CategoryDataset dataset = createDatasetByHour(startDate, endDate);
		// 创建图形对象
		JFreeChart jfreechart = ChartFactory.createBarChart3D("按小时汇总订单", "按小时", "数量", dataset, PlotOrientation.VERTICAL, true, true, false);
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
//		barRenderer3d.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		barRenderer3d.setBaseItemLabelsVisible(true);
		barRenderer3d.setBaseItemLabelPaint(Color.BLUE);// 设置数值颜色，默认黑色
		// 将图表已数据流的方式返回给客户端
		ChartUtilities.writeChartAsPNG(outputStream, jfreechart, 800, 270);
		return outputStream;
	}

	/**
	 * 按日期返回数据集
	 * 
	 * @return
	 * @throws Exception
	 */
	private CategoryDataset createDatasetByDate(String startDate, String endDate) throws Exception {
		Map<String, String> map = sortOrderByDate(startDate, endDate);
		DefaultCategoryDataset defaultdataset = new DefaultCategoryDataset();
		for (Entry<String, String> entry : map.entrySet()) {
			defaultdataset.addValue(Integer.valueOf(entry.getValue()), "订单", entry.getKey());
		}
		return defaultdataset;
	}

	/**
	 * 按小时返回数据集
	 * 
	 * @return
	 * @throws Exception
	 */
	private CategoryDataset createDatasetByHour(String startDate, String endDate) throws Exception {
		Map<String, String> map = sortOrderByHour(startDate, endDate);
		DefaultCategoryDataset defaultdataset = new DefaultCategoryDataset();
		for (Entry<String, String> entry : map.entrySet()) {
			defaultdataset.addValue(Integer.valueOf(entry.getValue()), "订单", entry.getKey());
		}
		return defaultdataset;
	}

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * 
	 * @Author zhouyu
	 * @CreateDate 2012-10-18
	 * @param sql
	 *            查询语句
	 * @param firstRow
	 *            起始位置
	 * @param maxRow
	 *            每页需要显示的条数
	 * @Return List
	 */
	public List executeSqlToMap(String sql, int firstRow, int maxRow) throws Exception {
		return orderStatisticsDao.executeSqlToMap(sql, firstRow, maxRow);
	}

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * 
	 * @Author zhouyu
	 * @CreateDate 2012-10-18
	 * @param sql
	 *            查询语句
	 * @Return List
	 */
	public List executeSqlToMap(String sql) throws Exception {
		return orderStatisticsDao.executeSqlToMap(sql);
	}
	/**
	 * 获取所有实体集合
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-04-16
	 * @param
	 * @Return List
	 */
	public List<OrderStatistics> findAll() throws Exception {
		return orderStatisticsDao.findAll();
	}
	/**
	 * 测试
	 * 
	 * @return
	 * @throws ParseException
	 */
	public static void main(String[] args) throws ParseException {

		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// SimpleDateFormat sdf1 = new SimpleDateFormat("HH:mm:ss");
		//
		// for (int i = 0; i < 24; i++) {
		// Calendar startTemp = Calendar.getInstance();
		// startTemp.setTime(sdf.parse("2012-01-01 00:00:00"));
		// startTemp.add(Calendar.HOUR, i);// 增加一天
		// // System.out.println(sdf.format(startTemp.getTime()));
		// System.out.println(sdf1.format(startTemp.getTime()));
		//
		// }
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar);
		int yy = calendar.get(Calendar.YEAR);
		System.out.println(yy);
		int mm = calendar.get(Calendar.MONTH) + 1;
		System.out.println(mm);
		int dd = calendar.get(Calendar.DATE);
		System.out.println(dd);
		int hh = calendar.get(Calendar.HOUR);
		System.out.println(hh);
		int nn = calendar.get(Calendar.MINUTE);
		System.out.println(nn);
		int ss = calendar.get(Calendar.SECOND);
		System.out.println(ss);
		int mi = calendar.get(Calendar.MILLISECOND);
		System.out.println(mi);

	}
}
