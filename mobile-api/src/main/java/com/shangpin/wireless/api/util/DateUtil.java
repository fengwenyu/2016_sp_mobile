package com.shangpin.wireless.api.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateUtil {
	
	protected static final Log log = LogFactory.getLog(DateUtil.class.getSimpleName());
	
	/**
	 * Return current datetime string.
	 * 
	 * @return current datetime, pattern: "yyyy-MM-dd HH:mm:ss".
	 */
	public static String getDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dt = sdf.format(new Date());
		return dt;
	}
	

	public static void main(String[] args) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    log.debug(compareDate(sdf1.parse("2012-10-01 13:03:26.0"), sdf1.parse("2012-10-02 02:00:00")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 比较时分秒
	 * 
	 * @Author zhouyu
	 * @CreatDate 2013-02-04
	 * @param firstDate
	 *            日期
	 * @param secondDate
	 *            日期
	 * @return int
	 * @throws ParseException
	 */
	public static Integer compareDate(Date firstDate, Date secondDate) throws ParseException {
		Calendar first = Calendar.getInstance();
		first.setTime(firstDate);
		int firstHour = first.get(Calendar.HOUR_OF_DAY);
		int firstMinute = first.get(Calendar.MINUTE);
		int firstSecond = first.get(Calendar.SECOND);
		Calendar second = Calendar.getInstance();
		second.setTime(secondDate);
		int secondHour = second.get(Calendar.HOUR_OF_DAY);
		int secondMinute = second.get(Calendar.MINUTE);
		int secondSecond = second.get(Calendar.SECOND);
		if (firstHour > secondHour) {
			return 1;
		} else if (firstHour < secondHour) {
			return -1;
		} else {
			if (firstMinute > secondMinute) {
				return 1;
			} else if (firstMinute < secondMinute) {
				return -1;
			} else {
				if (firstSecond > secondSecond) {
					return 1;
				} else if (firstSecond < secondSecond) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}

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
	public static String getAfterAmountDay(Date date, int amount, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(GregorianCalendar.DATE, amount);
		return sdf.format(cal.getTime());
	}
	/**
	 * 取得给定日期相差天数.
	 * @Author liling
	 */
	public static Integer marginDate(Date startDate, Date endDate) {
		long shengyu = endDate.getTime() - startDate.getTime();
		int marginDate = (int) (shengyu / (1000 * 60 * 60 * 24));
		return marginDate;
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

	public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gc;
	}

	public static Date convertToDate(XMLGregorianCalendar cal) throws Exception {
		GregorianCalendar ca = cal.toGregorianCalendar();
		return ca.getTime();
	}
 
	/**
	 * 取得时间的毫秒
	 * @param dateFormat
	 * @param date
	 * @return
	 */
	public static long getDateNum(String dateFormat, String date){
		long result = 0;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			result = sdf.parse(date).getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * 取得时间的毫秒
	 * @param dateFormat
	 * @param date
	 * @return
	 */
	public static String getDateNumStr(String dateFormat, String date){
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			result = String.valueOf(sdf.parse(date).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/***************************************************************************
	 * //nd=1表示返回的值中包含年度 //yf=1表示返回的值中包含月份 //rq=1表示返回的值中包含日期 //format表示返回的格式 1
	 * 以年月日中文返回 2 以横线-返回 // 3 以斜线/返回 4 以缩写不带其它符号形式返回 // 5 以点号.返回
	 * @throws ParseException 
	 **************************************************************************/
	public static String getStringDateMonth(String date, String nd, String yf, String rq, String format) {
//		SimpleDateFormat sformat = new SimpleDateFormat("yyyy-MM-dd");
//		Date d = sformat.parse(date);
//		String dateString = String.format("%tF %<tT", Long.valueOf(date));
		String s_nd = date.substring(0, 4); // 年份
		String s_yf = date.substring(5, 7); // 月份
		String s_rq = date.substring(8, 10); // 日期
		String sreturn = "";
		if (nd.equals("1")) {
			sreturn = s_nd;
			// 处理间隔符
			if (format.equals("1"))
				sreturn = sreturn + "年";
			else if (format.equals("2"))
				sreturn = sreturn + "-";
			else if (format.equals("3"))
				sreturn = sreturn + "/";
			else if (format.equals("5"))
				sreturn = sreturn + ".";
		}
		// 处理月份
		if (yf.equals("1")) {
			sreturn = sreturn + s_yf;
			if (format.equals("1"))
				sreturn = sreturn + "月";
			else if (format.equals("2"))
				sreturn = sreturn + "-";
			else if (format.equals("3"))
				sreturn = sreturn + "/";
			else if (format.equals("5"))
				sreturn = sreturn + ".";
		}
		// 处理日期
		if (rq.equals("1")) {
			sreturn = sreturn + s_rq;
			if (format.equals("1"))
				sreturn = sreturn + "日";
		}
		
		return sreturn;
	}

}
