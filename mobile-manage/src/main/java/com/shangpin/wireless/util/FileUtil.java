package com.shangpin.wireless.util;

import java.io.File;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志的输出和解析
 * 
 * @Author zhouyu
 * @CreatDate 2012-08-31
 */
public class FileUtil {
	private static Vector<String> dataVector = new Vector<String>();

	/**
	 * 将日志写入文件
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-08-31
	 * @param
	 * @Return void
	 */
	public static void write() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fileName = sdf.format(new Date()) + ".txt";
			// String path = "/home/api/logs/";
			String path = ProReader.getInstance().getProperty("clientOperationLogsPath");
			File filePath = new File(path);
			if (!filePath.exists())
				filePath.mkdirs();
			RandomAccessFile randomFile = new RandomAccessFile(path + fileName, "rw");
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);

			for (String log : dataVector) {
				randomFile.write((log + "\n").getBytes());
			}
			dataVector.clear();

			// for (int i = dataVector.size() - 1; i >= 0; i--) {
			// String log = dataVector.get(i);
			// randomFile.write((log + "\n").getBytes());
			// dataVector.remove(i);
			// }
			randomFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析日志文件
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-08-31
	 * @param
	 * @Return void
	 */
	public static void parse() {

	}

	/**
	 * 将日志加入内存中
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-08-31
	 * @param request
	 *            作用域对象
	 * @param interfaceName
	 *            接口名称
	 * @param params
	 *            动态参数绑定
	 * @Return void
	 */
	public synchronized static void addLog(HttpServletRequest request, String interfaceName, String... params) {
		String ip = request.getRemoteAddr();
		// imei、os、osv、p、ch、ver、apn、wh、model、mt、operator运营商
		String imei = request.getHeader("imei");
		String os = request.getHeader("os");// 手机平台:ios、android
		String osv = request.getHeader("osv");// 系统版本
		String productNo = request.getHeader("p");// 产品号
		String channelNo = request.getHeader("ch");// 渠道号
		String version = request.getHeader("ver");// 版本号
		String apn = request.getHeader("apn");// 接入点
		String wh = request.getHeader("wh");// 屏幕宽高640x960
		String mt = request.getHeader("mt"); // 手机类型（gsm，cdma）
		String model = request.getHeader("model"); // 手机型号(iPhone 4S)
		String operator = request.getHeader("operator"); // 运营商
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append(ip == null || "".equals(ip) ? "#" : ip);
		sb.append("|");
		sb.append(System.currentTimeMillis());
		sb.append("|");
		sb.append(interfaceName == null || "".equals(interfaceName) ? "#" : interfaceName);
		sb.append("|");
		sb.append(imei == null || "".equals(imei) ? "#" : imei);
		sb.append(",");
		sb.append(os == null || "".equals(os) ? "#" : os);
		sb.append(",");
		sb.append(osv == null || "".equals(osv) ? "#" : osv);
		sb.append(",");
		sb.append(productNo == null || "".equals(productNo) ? "#" : productNo);
		sb.append(",");
		sb.append(channelNo == null || "".equals(channelNo) ? "#" : channelNo);
		sb.append(",");
		sb.append(version == null || "".equals(version) ? "#" : version);
		sb.append(",");
		sb.append(apn == null || "".equals(apn) ? "#" : apn);
		sb.append(",");
		sb.append(wh == null || "".equals(wh) ? "#" : wh);
		sb.append(",");
		sb.append(mt == null || "".equals(mt) ? "#" : mt);
		sb.append(",");
		sb.append(model == null || "".equals(model) ? "#" : model);
		sb.append(",");
		sb.append(operator == null || "".equals(operator) ? "#" : operator);
		sb.append("|");
		final int length = params.length >> 1;
		for (int i = 0; i < length; i++) {// #为占位符
			sb.append(params[i << 1] == null ? "#" : params[i << 1]);
			sb.append(":");
			sb.append(params[(i << 1) + 1] == null ? "#" : params[(i << 1) + 1]);
			if (length - 1 != i)
				sb.append(",");
		}
		dataVector.add(sb.toString());
		if (dataVector.size() >= 10) {
			write();
		}
	}

	public static Vector<String> getDataVector() {
		return dataVector;
	}
}
