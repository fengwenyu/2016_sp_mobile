package com.shangpin.mobileAolai.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	/**
	 * 取得给定日期加上一定天数后的日期对象.
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-30
	 * @param date
	 *            给定的日期对象
	 * @param amount
	 *            需要添加的天数，如果是向前的天数，使用负数就可以.
	 * @param format
	 *            输出格式.
	 * @return Date 加上一定天数以后的Date对象
	 */
	public static Date getAfterAmountDay(Date date, int amount) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.DATE, amount);
		return cal.getTime();
	}

	/**
	 * 将日期转成星期
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-10-30
	 * @param date
	 *            给定的日期对象
	 * @return String 返回当前日期所对应的星期
	 */
	public static String dateToWeek(Date date) {
		String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDaysName[intWeek];
	}
}
