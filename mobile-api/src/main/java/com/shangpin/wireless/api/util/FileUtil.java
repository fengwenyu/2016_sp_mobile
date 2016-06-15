package com.shangpin.wireless.api.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.Constants;

/**
 * 日志的输出和解析
 * 
 * @Author zhouyu
 * @CreatDate 2012-08-31
 */
public class FileUtil {
	private static Vector<String> dataVector = new Vector<String>();
	private static ConcurrentHashMap<String, String> chMap = new ConcurrentHashMap<String, String>();
	protected static final Log log = LogFactory.getLog(FileUtil.class.getSimpleName());
	/**
	 * 将日志写入文件
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-08-31
	 * @param
	 * @Return void
	 */
	public synchronized static void write() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fileName = Constants.SERVER_IP + "_" + sdf.format(new Date()) + ".log";
			// String path = "/home/api/logs/";
			String path = ProReader.getInstance().getProperty("clientOperationLogsPath");
			File filePath = new File(path);
			if (!filePath.exists())
				filePath.mkdirs();
			RandomAccessFile randomFile = new RandomAccessFile(path + fileName, "rw");
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			for (String log : dataVector) {
				randomFile.write((log + "\n").getBytes("UTF-8"));
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
		String ip = null;
		// imei、os、osv、p、ch、ver、apn、wh、model、mt、operator运营商
		String imei = null;
		String os = null;// 手机平台:ios、android
		String osv = null;// 系统版本
		String productNo = null;// 产品号
		String channelNo = null;// 渠道号
		String version = null;// 版本号
		String apn = null;// 接入点
		String wh = null;// 屏幕宽高640x960
		String mt = null; // 手机类型（gsm，cdma）
		String model = null; // 手机型号(iPhone 4S)
		String operator = null; // 运营商
		if (request != null) {
			ip = getIpAddr(request);//request.getRemoteAddr();
			// imei、os、osv、p、ch、ver、apn、wh、model、mt、operator运营商
			imei = request.getHeader("imei");
			os = request.getHeader("os");// 手机平台:ios、android
			osv = request.getHeader("osv");// 系统版本
			productNo = request.getHeader("p");// 产品号
			channelNo = request.getHeader("ch");// 渠道号
			version = request.getHeader("ver");// 版本号
			apn = request.getHeader("apn");// 接入点
			wh = request.getHeader("wh");// 屏幕宽高640x960
			mt = request.getHeader("mt"); // 手机类型（gsm，cdma）
			model = request.getHeader("model"); // 手机型号(iPhone 4S)
			operator = request.getHeader("operator"); // 运营商
		}
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append((ip == null || "".equals(ip)) ? "#" : ip);
		sb.append("|");
		sb.append(System.currentTimeMillis());
		sb.append("|");
		sb.append((interfaceName == null || "".equals(interfaceName)) ? "#" : interfaceName);
		sb.append("|");
		sb.append((imei == null || "".equals(imei)) ? "#" : imei);
		sb.append(",");
		sb.append((os == null || "".equals(os)) ? "#" : os);
		sb.append(",");
		sb.append((osv == null || "".equals(osv)) ? "#" : osv);
		sb.append(",");
		sb.append((productNo == null || "".equals(productNo)) ? "#" : productNo);
		sb.append(",");
		sb.append((channelNo == null || "".equals(channelNo)) ? "#" : channelNo);
		sb.append(",");
		sb.append((version == null || "".equals(version)) ? "#" : version);
		sb.append(",");
		sb.append((apn == null || "".equals(apn)) ? "#" : apn);
		sb.append(",");
		sb.append((wh == null || "".equals(wh)) ? "#" : wh);
		sb.append(",");
		sb.append((mt == null || "".equals(mt)) ? "#" : mt);
		sb.append(",");
		sb.append((model == null || "".equals(model)) ? "#" : model);
		sb.append(",");
		sb.append((operator == null || "".equals(operator)) ? "#" : operator);
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

	public synchronized static void writeRestLogBeforeShutdown() {
		if (dataVector.size() > 0) {
			write();
		}
		if (chMap.size() > 0) {
			writeIMEILog();
		}
	}

	/**
	 * 创建文件
	 * 
	 * @param fileFullName
	 *            文件全名（路径+文件名）
	 * @throws IOException
	 */
	public static boolean CreateFile(String fileFullName) throws IOException {
		File file = new File(fileFullName);
		// 验证文件
		if (file.exists()) {
			log.debug("create file" + fileFullName + "failure,already exists!");
			return true;
		}
		if (fileFullName.endsWith(File.separator)) {
			log.debug("create file" + fileFullName + "failure,is path!");
			return false;
		}
		if (!file.getParentFile().exists()) {
			log.debug("prepare create ...");
			if (!file.getParentFile().mkdirs()) {
				log.debug("create file failure!");
				return false;
			}
		}
		// 创建目标文件
		if (file.createNewFile()) {
			log.debug("create file" + fileFullName + "success!");
			return true;
		} else {
			log.debug("create file" + fileFullName + "failure!");
			return false;
		}
	}

	/**
	 * 创建文件夹
	 * 
	 * @param path
	 *            文件夹路径
	 * 
	 * @return
	 */
	public static void createDir(String path) {
		File dir = new File(path);
		if (dir.exists()) {
			log.debug("create path" + path + " failure,already exists!");
		}
		if (!path.endsWith(File.separator))
			path = path + File.separator;
		if (dir.mkdirs()) {
			log.debug("create path" + path + " success!");
		} else {
			log.debug("create path" + path + " failure!");
		}
	}

	/**
	 * byte数组转换成16进制字符串
	 * 
	 * @param src
	 * @return
	 */
	private static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 通过文件流获取文件真实类型
	 * 
	 * @param is
	 *            文件输入流
	 * @return 文件类型
	 */
	public static String getTypeByStream(InputStream fis) {
		byte[] b = new byte[4];
		try {
			fis.read(b, 0, b.length);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bytesToHexString(b).toUpperCase();
	}

	public synchronized static void addLog(String imei, String oldimei) {
		chMap.put(imei, oldimei);
		if (chMap.size() >= 10) {
			writeIMEILog();
		}
	}

	public synchronized static void writeIMEILog() {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fileName = sdf.format(new Date()) + ".txt";
			String path = ProReader.getInstance().getProperty("imeiLogsPath");
			File filePath = new File(path);
			if (!filePath.exists())
				filePath.mkdirs();
			RandomAccessFile randomFile = new RandomAccessFile(path + fileName, "rw");
			long fileLength = randomFile.length();
			randomFile.seek(fileLength);
			Iterator<Map.Entry<String, String>> it = chMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, String> entry = it.next();
				String key = entry.getKey();
				it.remove();
				randomFile.write((key + "|" + entry.getValue() + "\n").getBytes("UTF-8"));
			}
			randomFile.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static final String getIpAddr (HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
//		System.out.println("x-forwarded-for: " + ip);
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			log.debug("Proxy-Client-IP: " + ip);
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
			log.debug("WL-Proxy-Client-IP: " + ip);
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	// 测试
	public static void main(String[] args) throws Exception {
		String src = "f:/manager.2013-05-16.zip";
		FileInputStream is = null;
		try {
			is = new FileInputStream(src);
		} catch (FileNotFoundException e) {
			//  Auto-generated catch block
			e.printStackTrace();
		}
		// byte[] b = new byte[4];
		// is.read(b, 0, b.length);
		// System.out.println(bytesToHexString(b));
		String type = getTypeByStream(is);
//		System.out.println(type);
		if(is != null)return;
		Thread thread = new Thread(new Runnable() {
			public void run() {
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Date date = new Date();
					sdf.format(date);
					// 写入header到/home/terminalLog/xxxx-xx-xx/imei
					String path = File.separatorChar + "home" + File.separatorChar + "terminalLog" + File.separatorChar + sdf.format(date) + File.separatorChar + "imei" + File.separatorChar;
					FileUtil.createDir(path);
					if (FileUtil.CreateFile(path + "header.log")) {
						FileOutputStream out = new FileOutputStream(path + "header.log");
						out.write("hello world".getBytes());
						out.close();
					}
				} catch (FileNotFoundException e) {
					//  Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					//  Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		thread.start();
		int j = 1;
		if (j == 1) {
			return;
		}
		// for (int i = 0; i < 1000; i++) {
		// dataVector.add("访问" + i);
		// }
		// write();
		// long timeMillis = System.currentTimeMillis();
		// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// System.out.println(sdf.format(new Date(timeMillis)));
		for (int i = 0; i < 2001; i++) {
			final int count = i;
			Thread t = new Thread(new Runnable() {
				public void run() {
					try {
						java.net.URL url = new java.net.URL("http://192.168.5.243:8080/api/activitylist?actid=20120810328");
						// java.net.URL url = new java.net.URL("http://127.0.0.1:8080/api/activitylist?actid=20120810328");
						java.net.HttpURLConnection conn = (java.net.HttpURLConnection) url.openConnection();
						conn.setRequestMethod("GET");
						conn.setRequestProperty("imei", "123");
						int resCode = conn.getResponseCode();
					} catch (Exception e) {
						e.printStackTrace();
						log.error(e);
					}
				}
			});
			t.start();
		}
	}
}
