package com.shangpin.wireless.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.shangpin.wireless.domain.AccountStatistics;
import com.shangpin.wireless.domain.DeviceInfo;
import com.shangpin.wireless.domain.DownloadStatistics;
import com.shangpin.wireless.domain.OrderStatistics;
import com.shangpin.wireless.domain.ShangPinWapPageView;
import com.shangpin.wireless.manage.service.AccountStatisticsService;
import com.shangpin.wireless.manage.service.DeviceInfoService;
import com.shangpin.wireless.manage.service.DownloadStatisticsService;
import com.shangpin.wireless.manage.service.OrderStatisticsService;
import com.shangpin.wireless.manage.service.ShangPinWapPageViewService;

/**
 * 
 * @Author wangwenguan
 * @CreateDate 2012-11-21
 */
public class ParseShangPinWapLogUtil {
	private static final String SPACE = " ";
	private static final String SEMICOLON = ";";
	private static final String BRACKET_LEFT = "(";
	private static final String BRACKET_RIGHT = ")";
	private static final String LINUX = "Linux";
	private static final String ANDROID = "Android";
	private static final List<String> logStrOutList = new ArrayList<String>();

	/**
	 * 递归读取文件
	 * 
	 * @Return
	 */
	public static void readFile(File file, List<Entity> outList) {
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
				readFileInLine(file, outList);
			} else if (file.isDirectory()) { // 否则如果它是一个目录
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					readFile(files[i], outList);
				}
			}
		} else {
			System.out.println("file(" + file.getName() + ") not found" + '\n');
		}
	}

	/**
	 * 以行为单位读取文件
	 * 
	 * @Return
	 */
	public static void readFileInLine(File file, List<Entity> outList) {
		BufferedReader reader = null;
		try {
			System.out.println("readFileInLine:" + file.getName());
			// System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String tempString = null;
			int line = 1;
			final String filename = file.getName();
			int start = filename.indexOf("#");
			if (start < 0) start = 0;
			final int end = filename.lastIndexOf(".");
			final String date = filename.substring(start, end);
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// System.out.println("line " + line + ": " + tempString);
				Entity entity = parse(tempString);
				if (entity != null) {
					entity.date = date;
					entity.lineNum = String.valueOf(line);
					outList.add(entity);
				}
				line++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public static void parseWapLog() {
		try {
			// final File directory = new File("/home/parselog/wap/");
			// if (directory.isDirectory()) { // 否则如果它是一个目录
			// File files[] = directory.listFiles();
			// for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
			// parseOnedayLog(files[i]);
			// }
			// }
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date twelve = sdf.parse("2012-12-01");
			for (int i = 0; i < 31; i++) {
				String name = DateUtil.getAfterAmountDay(twelve, i, "yyyy-MM-dd");
				File file = new File("/home/parselog/wap_shangpin_logs/" + name + ".txt");
				if (file.exists()) {
					parseOnedayLog(new File("/home/parselog/wap_shangpin_logs/" + name + ".txt"));
				}
			}
			Date first = sdf.parse("2013-01-01");
			for (int i = 0; i < 31; i++) {
				String name = DateUtil.getAfterAmountDay(first, i, "yyyy-MM-dd");
				File file = new File("/home/parselog/wap_shangpin_logs/" + name + ".txt");
				if (file.exists()) {
					parseOnedayLog(new File("/home/parselog/wap_shangpin_logs/" + name + ".txt"));
				}
			}
			Date second = sdf.parse("2013-02-01");
			for (int i = 0; i < 28; i++) {
				String name = DateUtil.getAfterAmountDay(second, i, "yyyy-MM-dd");
				File file = new File("/home/parselog/wap_shangpin_logs/" + name + ".txt");
				if (file.exists()) {
					parseOnedayLog(new File("/home/parselog/wap_shangpin_logs/" + name + ".txt"));
				}
			}
			Date third = sdf.parse("2013-03-01");
			for (int i = 0; i < 31; i++) {
				String name = DateUtil.getAfterAmountDay(third, i, "yyyy-MM-dd");
				File file = new File("/home/parselog/wap_shangpin_logs/" + name + ".txt");
				if (file.exists()) {
					parseOnedayLog(new File("/home/parselog/wap_shangpin_logs/" + name + ".txt"));
				}
			}
			Date four = sdf.parse("2013-04-01");
			for (int i = 0; i < 30; i++) {
				String name = DateUtil.getAfterAmountDay(four, i, "yyyy-MM-dd");
				File file = new File("/home/parselog/wap_shangpin_logs/" + name + ".txt");
				if (file.exists()) {
					parseOnedayLog(new File("/home/parselog/wap_shangpin_logs/" + name + ".txt"));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void parseYesterdayWapLog() {
		String fileName = DateUtil.getAfterAmountDay(new Date(), -1, "yyyy-MM-dd");
		parseOnedayLog(new File("/home/parselog/wap_shangpin_logs/" + fileName + ".txt"));
	}

//	private static ArrayList<String> LoggedFile = new ArrayList<String>();
	public static void parseOnedayLog(File file) {
//		if (!LoggedFile.contains(file.getName())) {	//	防止重复解析
//			LoggedFile.add(file.getName());
//		} else {
//			System.out.println("parseOnedayLog " + file.getName() + " again!!!");
//			return;
//		}
		try {
			List<Entity> logList = new ArrayList<Entity>();
			readFileInLine(file, logList);

			ApplicationContext ac = ApplicationContextUtil.get();
			AccountStatisticsService accountService = (AccountStatisticsService) ac.getBean(AccountStatisticsService.SERVICE_NAME);
			DeviceInfoService deviceService = (DeviceInfoService) ac.getBean(DeviceInfoService.SERVICE_NAME);
			OrderStatisticsService orderService = (OrderStatisticsService) ac.getBean(OrderStatisticsService.SERVICE_NAME);
			ShangPinWapPageViewService pageviewService = (ShangPinWapPageViewService) ac.getBean(ShangPinWapPageViewService.SERVICE_NAME);
			DownloadStatisticsService downloadService = (DownloadStatisticsService) ac.getBean(DownloadStatisticsService.SERVICE_NAME);

			List<Entity> orderSubmitList = new ArrayList<Entity>();
			List<Entity> orderSubmitResultList = new ArrayList<Entity>();
			List<Entity> payOrderList = new ArrayList<Entity>();
			List<Entity> paySuccessList = new ArrayList<Entity>();
			while (logList.size() > 0) {
				Entity entity = logList.get(0);
				if (!"orderStatistics".equals(entity.name) && !"/alipay/notifyReceiver".equals(entity.name)) {
					entity.saveAccountStatistics(accountService);
					entity.saveDeviceInfo(deviceService);
					entity.saveDownloadStatistics(downloadService);
					entity.savePageView(pageviewService);
				}
				if ("order".equals(entity.name)) {
					orderSubmitList.add(entity);
				}
				if ("orderStatistics".equals(entity.name)) {
					orderSubmitResultList.add(entity);
				}
				if ("alipay/trade".equals(entity.name)) {
					payOrderList.add(entity);
				}
				if ("/alipay/notifyReceiver".equals(entity.name)) {
					paySuccessList.add(entity);
				}

				logList.remove(0);
			}
			for (int i = 0; i < orderSubmitResultList.size(); i++) {
				Entity orderResult = orderSubmitResultList.get(i);
				orderResult.saveOrderStatistics(orderService);
			}
			orderSubmitList.clear();
			orderSubmitResultList.clear();
			for (int i = 0; i < payOrderList.size(); i++) {
				Entity pay = payOrderList.get(i);
				pay.saveOrderStatistics(orderService);
			}
			payOrderList.clear();
			for (int i = 0; i < paySuccessList.size(); i++) {
				Entity pay = paySuccessList.get(i);
				pay.saveOrderStatistics(orderService);
			}
			paySuccessList.clear();

			// 已解析的做记录
			file.renameTo(new File(file.getParent() + File.separator + "old" + file.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Entity parse(String line) {

		String[] infos = line.split("\\|");
		if (infos.length != 5)
			return null;

		Entity entity = new Entity();
		String[] remoteAddrRoute = infos[0].split(",");
		for (int i=0;i<remoteAddrRoute.length;i++) {
			String ip = remoteAddrRoute[i].trim();
			if (ip.length() > 0 && !"127.0.0.1".equals(ip)) {
				entity.ip = ip;break;
			}
		}
		entity.logtime = Long.parseLong(infos[1]);
		entity.name = infos[2];
		String[] headers = infos[3].split("%");
		if (headers.length == 1) {
			headers = infos[3].split(",");
		}
		try {
			entity.ua = "";
			if (headers.length > 12) {
				for (int i = 11; i < headers.length; i++) {
					entity.ua += headers[i];
				}
			} else {
				entity.ua = headers[headers.length - 1];
			}
			Map<String, String> parseUA = parseUA(entity.ua);
			entity.imei = "";
			entity.platform = parseUA.get("platform");
			entity.osv = parseUA.get("osv");
			entity.productNum = 20000L;
			try {
			if (!"#".equals(headers[4]) && !"/".equals(headers[4])) {
				if ("shopType".equals(headers[4]) || "success".equals(headers[4]))
					entity.channelNum = 32;
				else
					entity.channelNum = Long.parseLong(headers[4]);
			} else {
				entity.channelNum = 3;
			}
			} catch (Exception e) {
			}
			entity.browser = parseUA.get("browser");
			entity.browserInfo = parseUA.get("browserInfo");
			entity.ver = "";
			entity.apn = "";
			entity.resolution = parseUA.get("resolution");
			entity.phoneType = "";
			entity.phoneModel = parseUA.get("phoneModel");
			entity.operator = "";
			String[] paramPairs = null;
			entity.paramMap = new HashMap<String, String>();
			paramPairs = infos[4].split(",");
			for (int i = 0; i < paramPairs.length; i++) {
				String[] paramPair = paramPairs[i].split(":");
				if (paramPair.length == 2) {
					entity.paramMap.put(paramPair[0], "#".equals(paramPair[1]) ? "" : paramPair[1]);
				} else if (paramPair.length > 2) {
					StringBuilder sb = new StringBuilder();
					for (int k = 1; k < paramPair.length; k++) {
						sb.append(paramPair[k]);
						if (k < paramPair.length - 1) {
							sb.append(":");
						}
					}
					entity.paramMap.put(paramPair[0], sb.toString());
				}
			}
			entity.params = infos[4];
		} catch (Exception e) {
			System.out.println(line);
			e.printStackTrace();
			return null;
		}
		return entity;
	}

	static class Entity {
		String ip;
		long logtime;
		String name;
		String imei;
		String platform;
		String osv;
		long productNum;
		long channelNum;
		String ver;
		String apn;
		String resolution;
		String phoneType;
		String phoneModel;
		String operator;
		String ua;
		String browser;
		String browserInfo;
		String params;
		Map<String, String> paramMap;
		
		String date;
		String lineNum;

		public void saveAccountStatistics(AccountStatisticsService accountService) {
			if (!"accountaction!login".equals(name) && !"accountaction!register".equals(name)) {
				return;
			}
			try {
				final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				final Map<String, String> paramMap = this.paramMap;
				if ("0".equals(paramMap.get("code"))) {
					String city = null;
					if(StringUtils.isNotEmpty(ip)) {
						city = IPUtil.getCountryCityByIp(ip);
					}
					if ("accountaction!register".equals(name)) {
						final String userId = paramMap.get("userId");
						final String username = paramMap.get("username");
						HqlHelper hqlHelper = new HqlHelper(AccountStatistics.class, "a");
						hqlHelper.addWhereCondition("a.userId=?", userId);
//						hqlHelper.addWhereCondition("a.username=?", username);
						hqlHelper.addWhereCondition("a.productNum=?", productNum);
						hqlHelper.addWhereCondition("a.channelNum=?", channelNum);
						hqlHelper.addWhereCondition("a.logTime BETWEEN '" + date + " 00:00:00' AND '" + date + " 23:59:59'");
						AccountStatistics account = accountService.getByCondition(hqlHelper);
						if (account == null) {
							account = new AccountStatistics();
							account.setImei(imei);
							account.setPlatform(platform);
							account.setProductNum(productNum);
							account.setChannelNum(channelNum);
							account.setRegOrigin(paramMap.get("regOrigin"));
							account.setGender(paramMap.get("gender"));
							account.setUserId(paramMap.get("userId"));
							account.setUsername(username);
							account.setIp(ip);
							if(StringUtils.isNotEmpty(ip))
								account.setCity(city);
							account.setRegTime(sdf.parse(paramMap.get("regTime")));
							account.setLoginTime(sdf.parse(paramMap.get("loginTime")));
							account.setLogTime(new Date(logtime));
							account.setCreateTime(new Date());
							account.setLogincountoneday(1);
							account.setMarkup("regist");
							accountService.save(account);
						}
					} else if ("accountaction!login".equals(name)) {
						final String userId = paramMap.get("userId");
						final String username = paramMap.get("username");
						HqlHelper hqlHelper = new HqlHelper(AccountStatistics.class, "a");
						hqlHelper.addWhereCondition("a.userId=?", userId);
//						hqlHelper.addWhereCondition("a.username=?", username);
						hqlHelper.addWhereCondition("a.productNum=?", productNum);
						hqlHelper.addWhereCondition("a.channelNum=?", channelNum);
						hqlHelper.addWhereCondition("a.logTime BETWEEN '" + date + " 00:00:00' AND '" + date + " 23:59:59'");
						AccountStatistics account = accountService.getByCondition(hqlHelper);
						// 登录需要记录到account表的信息
						if (account != null) {
							account.setLoginTime(sdf.parse(paramMap.get("loginTime")));
							final int logincountoneday = account.getLogincountoneday();
							account.setLogincountoneday(logincountoneday + 1);

							accountService.update(account);
						} else {
							account = new AccountStatistics();
							account.setUserId(paramMap.get("userId"));
							account.setUsername(username);
							account.setImei(imei);
							account.setPlatform(platform);
							account.setProductNum(productNum);
							account.setChannelNum(channelNum);
							account.setRegOrigin(paramMap.get("regOrigin"));
							account.setGender(paramMap.get("gender"));
							account.setIp(ip);
							if(StringUtils.isNotEmpty(ip))
								account.setCity(city);
							account.setRegTime(sdf.parse(paramMap.get("regTime")));
							account.setLoginTime(sdf.parse(paramMap.get("loginTime")));
							account.setLogTime(new Date(logtime));
							account.setCreateTime(new Date());
							account.setLogincountoneday(1);
							account.setMarkup("login");
							accountService.save(account);
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void saveDeviceInfo(DeviceInfoService deviceService) {
			if ("merchandiseaction!splist".equals(name)) {
				try {
					DeviceInfo deviceinfo = new DeviceInfo();
					deviceinfo.setPlatform(platform);
					deviceinfo.setOsv(osv);
					deviceinfo.setImei(imei);
					deviceinfo.setApn(apn);
					deviceinfo.setResolution(resolution);
					deviceinfo.setPhoneType(phoneType);
					deviceinfo.setPhoneModel(phoneModel);
					deviceinfo.setOperator(operator);
					deviceinfo.setIp(ip);
					deviceinfo.setLogTime(new Date(logtime));
					deviceinfo.setCreateTime(new Date());
					deviceinfo.setUa(ua);
					deviceinfo.setBrowser(browser);
					deviceinfo.setBrowserInfo(browserInfo);
					deviceinfo.setReserve5(lineNum);
					deviceService.save(deviceinfo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		public void saveOrderStatistics(OrderStatisticsService orderService) {
			if (!"orderStatistics".equals(name) && !"alipay/trade".equals(name) && !"/alipay/notifyReceiver".equals(name)) {
				return;
			}
			try {
				String city = null;
				if(StringUtils.isNotEmpty(ip)) {
					city = IPUtil.getCountryCityByIp(ip);
				}
				Date now = new Date();
				if ("orderStatistics".equals(name)) {
					final String orderid = paramMap.get("orderid");
					if (orderid == null || orderid.length() == 0) {
						return;
					}
					final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
					HqlHelper hqlHelper = new HqlHelper(OrderStatistics.class, "a");
					hqlHelper.addWhereCondition("a.orderId=?", orderid);
					OrderStatistics order = orderService.getByCondition(hqlHelper);
					if (order == null) {
						order = new OrderStatistics();
						order.setUserId(paramMap.get("userid"));
						order.setOrderId(orderid);
						order.setOrderAmount(Float.parseFloat(paramMap.get("amount")));
						order.setSkucounts(Integer.parseInt(paramMap.get("skucounts")));
						order.setObjectcounts(Integer.parseInt(paramMap.get("objectcounts")));
						order.setOrderProduct(productNum);
						order.setOrderChannel(channelNum);
						order.setIsok(paramMap.get("isOK"));
						order.setOrderTime(sdf.parse(paramMap.get("date")));
//						order.setOrderOrigin(paramMap.get("orderorign"));	2013-11-04
//						order.setOrderType(paramMap.get("ordertype"));	m站记录错误，统一设为空，后续再补充
						order.setOrderRetcode(paramMap.get("code"));
						order.setIp(ip);
						if(StringUtils.isNotEmpty(ip))
							order.setCity(city);
						order.setCreateTime(now);
						orderService.save(order);
					} else {
						order.setUserId(paramMap.get("userid"));
						order.setOrderId(orderid);
						order.setOrderAmount(Float.parseFloat(paramMap.get("amount")));
						order.setSkucounts(Integer.parseInt(paramMap.get("skucounts")));
						order.setObjectcounts(Integer.parseInt(paramMap.get("objectcounts")));
						order.setOrderProduct(productNum);
						order.setOrderChannel(channelNum);
						order.setIsok(paramMap.get("isOK"));
						order.setOrderTime(sdf.parse(paramMap.get("date")));
//						order.setOrderOrigin(paramMap.get("orderorign"));
//						order.setOrderType(paramMap.get("ordertype"));
						order.setOrderRetcode(paramMap.get("code"));
						order.setIp(ip);
						orderService.update(order);
					}
				} else if ("alipay/trade".equals(name)) {
					final String orderid = paramMap.get("orderid");
					HqlHelper hqlHelper = new HqlHelper(OrderStatistics.class, "a");
					hqlHelper.addWhereCondition("a.orderId=?", orderid);
					OrderStatistics order = orderService.getByCondition(hqlHelper);
					// 登录需要记录到order表的信息
					if (order != null) {
						if (order.getUserId() == null || order.getUserId().length() == 0) {
							order.setUserId(paramMap.get("userid"));
						}
						order.setPayProduct(productNum);
						order.setPayChannel(channelNum);
						order.setMainPayMode(paramMap.get("mainpaymode"));
						order.setSubPayMode(paramMap.get("subpaymode"));

						orderService.update(order);
					} else {
						order = new OrderStatistics();
						order.setOrderId(paramMap.get("orderid"));
						order.setUserId(paramMap.get("userid"));
						order.setPayProduct(productNum);
						order.setPayChannel(channelNum);
						order.setMainPayMode(paramMap.get("mainpaymode"));
						order.setSubPayMode(paramMap.get("subpaymode"));
						if (paramMap.get("orderamount") != null) {
							order.setOrderAmount(Float.parseFloat(paramMap.get("orderamount")));
						} else if (paramMap.get("onlineamount") != null) {
							order.setOrderAmount(Float.parseFloat(paramMap.get("onlineamount")));
						} else {
							order.setOrderAmount(null);
						}
						order.setOrderOrigin(paramMap.get("orderOrigin"));
						order.setIp(ip);
						if(StringUtils.isNotEmpty(ip))
							order.setCity(city);
						order.setCreateTime(now);
						final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
						order.setOrderTime(sdf.parse(paramMap.get("ordertime")));
						orderService.save(order);
					}
				} else if ("/alipay/notifyReceiver".equals(name)) {
					final String orderid = paramMap.get("orderid");
					HqlHelper hqlHelper = new HqlHelper(OrderStatistics.class, "a");
					hqlHelper.addWhereCondition("a.orderId=?", orderid);
					OrderStatistics order = orderService.getByCondition(hqlHelper);
					// 登录需要记录到order表的信息
					if (order != null) {
						order.setIspaid(paramMap.get("success"));
						order.setPayTime(new Date(logtime));
						order.setMainPayMode(paramMap.get("payid"));
						order.setSubPayMode(paramMap.get("paychildid"));

						orderService.update(order);
					} else {
						order = new OrderStatistics();
						order.setOrderId(paramMap.get("orderid"));
						order.setIspaid(paramMap.get("success"));
						order.setPayTime(new Date(logtime));
						order.setMainPayMode(paramMap.get("payid"));
						order.setSubPayMode(paramMap.get("paychildid"));
						order.setIp(ip);
						if(StringUtils.isNotEmpty(ip))
							order.setCity(city);
						order.setCreateTime(now);
						orderService.save(order);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void saveDownloadStatistics(DownloadStatisticsService downloadService) {
			if (!"shangpinindex!toAppStore".equals(name)
					&& !"download".equals(name)) {
				return;
			}
			try {
				DownloadStatistics statistics = new DownloadStatistics();
				statistics.setApiname(name);
				statistics.setVisitIp(ip);
				statistics.setOs(platform);
				statistics.setOsv(osv);
				statistics.setReferer(paramMap.get("referer"));
				String downproduct = paramMap.get("p");
				if (downproduct == null || downproduct.length() <= 0) {
					downproduct = params.substring(params.lastIndexOf(":") + 1).trim();
				}
				statistics.setDownloadProductNum(Long.parseLong(downproduct));
				String ch = paramMap.get("ch");
				statistics.setChannelNum((ch==null||ch.length()<=0)?channelNum:(Long.parseLong(ch)));
				statistics.setPhoneModel(phoneModel);
				statistics.setParams(params);
				statistics.setLogTime(new Date(logtime));
				statistics.setCreateTime(new Date());
				statistics.setReserve5(lineNum);

				downloadService.save(statistics);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void savePageView(ShangPinWapPageViewService pageviewService) {
			if ("orderStatistics".equals(name)
					|| "/alipay/notifyReceiver".equals(name)
					|| "accountAction!info".equals(name)
					|| "accountaction!registerUI".equals(name)
					|| "accountaction!loginUI".equals(name)) {
				return;
			}
			try {
				ShangPinWapPageView pageview = new ShangPinWapPageView();
				pageview.setApiname(name);
				pageview.setVisitIp(ip);
				pageview.setImei(imei);
				pageview.setOs(platform);
				pageview.setOsv(osv);
				pageview.setProductNum(productNum);
				pageview.setChannelNum(channelNum);
				pageview.setApn(apn);
				pageview.setVer(ver);
				pageview.setPhoneType(phoneType);
				pageview.setPhoneModel(phoneModel);
				pageview.setParams(params);
				pageview.setLogTime(new Date(logtime));
				pageview.setCreateTime(new Date());
				pageview.setReserve5(lineNum);

				pageviewService.save(pageview);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 解析UA
	 * 
	 * @Author zhouyu
	 * @CreateDate 2012-12-11
	 * @param list
	 * @Return
	 */
	public static Map<String, String> parseUA(String ua) {
		Map<String, String> map = new HashMap<String, String>();
		final int androidIndex = ua.indexOf(ANDROID);
		final int linuxIndex = ua.indexOf(LINUX);
		// System.out.println(ua);

		if (ua.indexOf(ANDROID) > -1 && ua.indexOf("Intel Mac OS") < 0) {
			map.put("platform", ANDROID);

			/*** 记录系统版本号和手机型号 start ***/
			findAndroidPhoneModel(ua, map, androidIndex, linuxIndex);
			/*** 记录系统版本号和手机型号 end ***/
		} else if (ua.indexOf("iPhone OS") > -1) {
			map.put("platform", "ios");// 平台
			findIOSPhoneModel(ua, map, "iPhone OS", ua.indexOf("iPhone OS"));
		} else if (ua.indexOf("iPad") > -1 && ua.indexOf("OS") > -1) {
			map.put("platform", "ios");// 平台
			findIOSPhoneModel(ua, map, "OS", ua.indexOf("OS"));
		} else if (ua.indexOf("Intel Mac OS") > -1) {
			map.put("platform", "mac");// 平台
			findIOSPhoneModel(ua, map, "Mac OS X", ua.indexOf("Mac OS X"));
		} else if (ua.indexOf("MQQBrowser") > -1) {
			if (ua.indexOf("Adr") > -1) {
				map.put("platform", ANDROID);
				findAndroidPhoneModel(ua, map, androidIndex, linuxIndex);
			} else if (ua.indexOf("iOS") > -1) {
				map.put("platform", "ios");
				findIOSPhoneModel(ua, map, "iOS", ua.indexOf("iOS"));
			}
		} else if (ua.indexOf("UC") > -1) {
			if (ua.indexOf("JUC") > -1) {
				map.put("platform", ANDROID);
				findAndroidPhoneModel(ua, map, androidIndex, linuxIndex);
			} else if (ua.indexOf("IUC") > -1) {
				map.put("platform", "ios");
				findIOSPhoneModel(ua, map, "iOS", ua.indexOf("iOS"));
			}
		} else if (ua.indexOf("Chrome") > -1) {
			final int left = ua.indexOf(BRACKET_LEFT);
			final int right = ua.indexOf(BRACKET_RIGHT);
			if (left > -1) {
				String temp = ua.substring(left + 1, right);
				String[] splits = temp.split(SPACE);
				if (splits.length > 1) {
					map.put("platform", splits[0]);
				}
			}
		} else {
			logStrOutList.add(ua);
		}
		findBrowserInfo(ua, map, ANDROID.equals(map.get("platform")));

		final int starIndex = ua.indexOf("*");
		int resStrat = 0, resEnd = 0;
		if (starIndex > -1) {
			for (int i = starIndex - 1; i >= 0; i--) {
				char c = ua.charAt(i);
				if (c < '0' || c > '9') {
					resStrat = i + 1;
					break;
				}
			}
			for (int i = starIndex + 1; i < ua.length(); i++) {
				char c = ua.charAt(i);
				if (c < '0' || c > '9') {
					resEnd = i;
					break;
				}
			}
			map.put("resolution", ua.substring(resStrat, resEnd));
		}
		return map;
	}

	private static void findAndroidPhoneModel(final String ua, final Map<String, String> out, final int androidIndex, final int linuxIndex) {
		int osvStart = 0, osvEnd = 0;
		if (androidIndex > -1) {
			if (ua.charAt(androidIndex + ANDROID.length()) == '/') {
				osvStart = androidIndex + ANDROID.length() + 1;
				osvEnd = ua.indexOf(SPACE, osvStart);
				if (osvEnd == -1) {
					osvEnd = ua.length();
				}
			} else {
				osvStart = androidIndex + ANDROID.length();
				osvEnd = ua.indexOf(SEMICOLON, osvStart);
				if (osvEnd == -1) {
					osvEnd = ua.indexOf(BRACKET_RIGHT, osvStart);
					if (osvEnd == -1) {
						osvEnd = ua.length();
					}
				}
			}
		} else if (linuxIndex > -1) {
			for (int i = linuxIndex + LINUX.length(); i < ua.length(); i++) {
				char c = ua.charAt(i);
				if (c >= '0' && c <= '9') {
					osvStart = i;
					break;
				}
			}
			osvEnd = ua.indexOf(SEMICOLON, osvStart);
			if (osvEnd == -1) {
				osvEnd = ua.length();
			}
		}
		final int buildIndex = ua.indexOf("Build");
		if (buildIndex > -1) {
			String temp = ua.substring(0, buildIndex);
			int modelStart = temp.lastIndexOf(SEMICOLON);
			out.put("phoneModel", temp.substring(modelStart + 1).trim());// 手机型号
			out.put("osv", ua.substring(osvStart, osvEnd).trim()); // 系统版本
		} else {
			final int motoIndex = ua.toUpperCase().indexOf("MOT");
			final int samsungIndex = ua.toUpperCase().indexOf("SAMSUNG");
			final int coolpadIndex = ua.toUpperCase().indexOf("COOLPAD");
			final int zteIndex = ua.toUpperCase().indexOf("ZTE");
			final int huaweiIndex = ua.toUpperCase().indexOf("HUAWEI");
			final int lenovoIndex = ua.toUpperCase().indexOf("LENOVO");
			if (motoIndex > -1) {
				String subua = ua;
				if (motoIndex < linuxIndex) {
					subua = ua.substring(0, linuxIndex);
				}
				int modelEnd = findEndIndex(subua, motoIndex);
				out.put("phoneModel", ua.substring(motoIndex, modelEnd).trim());// 手机型号
				out.put("osv", ua.substring(osvStart, osvEnd).trim()); // 系统版本
			} else if (samsungIndex > -1) {
				String subua = ua;
				if (samsungIndex < linuxIndex) {
					subua = ua.substring(0, linuxIndex);
				}
				int modelEnd = findEndIndex(subua, samsungIndex);
				out.put("phoneModel", ua.substring(samsungIndex, modelEnd).trim());// 手机型号
				out.put("osv", ua.substring(osvStart, osvEnd).trim()); // 系统版本
			} else if (coolpadIndex > -1) {
				String subua = ua;
				if (coolpadIndex < linuxIndex) {
					subua = ua.substring(0, linuxIndex);
				}
				int modelEnd = findEndIndex(subua, coolpadIndex);
				out.put("phoneModel", ua.substring(coolpadIndex, modelEnd).trim());// 手机型号
				out.put("osv", ua.substring(osvStart, osvEnd).trim()); // 系统版本
			} else if (zteIndex > -1) {
				String subua = ua;
				if (zteIndex < linuxIndex) {
					subua = ua.substring(0, linuxIndex);
				}
				int modelEnd = findEndIndex(subua, zteIndex);
				out.put("phoneModel", ua.substring(zteIndex, modelEnd).trim());// 手机型号
				out.put("osv", ua.substring(osvStart, osvEnd).trim()); // 系统版本
			} else if (huaweiIndex > -1) {
				String subua = ua;
				if (huaweiIndex < linuxIndex) {
					subua = ua.substring(0, linuxIndex);
				}
				int modelEnd = findEndIndex(subua, huaweiIndex);
				out.put("phoneModel", ua.substring(huaweiIndex, modelEnd).trim());// 手机型号
				out.put("osv", ua.substring(osvStart, osvEnd).trim()); // 系统版本
			} else if (lenovoIndex > -1) {
				String subua = ua;
				if (lenovoIndex < linuxIndex) {
					subua = ua.substring(0, linuxIndex);
				}
				int modelEnd = findEndIndex(subua, lenovoIndex);
				out.put("phoneModel", ua.substring(lenovoIndex, modelEnd).trim());// 手机型号
				out.put("osv", ua.substring(osvStart, osvEnd).trim()); // 系统版本
			} else {
				out.put("phoneModel", "");// 手机型号
				out.put("osv", ua.substring(osvStart, osvEnd).trim()); // 系统版本
			}
		}
	}

	private static void findIOSPhoneModel(final String ua, final Map<String, String> out, final String osName, final int osIndex) {
		int osvStart = 0, osvEnd = 0;
		if (osIndex > -1) {
			osvStart = osIndex + osName.length();
			final int semiIndex = ua.indexOf(SEMICOLON, osvStart + 1);
			final int spaceIndex = ua.indexOf(SPACE, osvStart + 1);
			if (semiIndex > -1 && spaceIndex > -1) {
				osvEnd = semiIndex < spaceIndex ? semiIndex : spaceIndex;
			} else if (semiIndex > -1) {
				osvEnd = semiIndex;
			} else if (spaceIndex > -1) {
				osvEnd = spaceIndex;
			}
			if (osvEnd == -1) {
				osvEnd = ua.length();
			}
		}
		final int iphoneIndex = ua.toUpperCase().indexOf("IPHONE");
		final int ipodIndex = ua.toUpperCase().indexOf("IPOD");
		final int ipadIndex = ua.toUpperCase().indexOf("IPAD");
		final int macintoshIndex = ua.toUpperCase().indexOf("MACINTOSH");
		if (ipodIndex > -1) {
			int modelEnd = findEndIndex(ua, ipodIndex);
			out.put("phoneModel", ua.substring(ipodIndex, modelEnd).trim());// 手机型号
			out.put("osv", ua.substring(osvStart, osvEnd).trim().replace('_', '.')); // 系统版本
		} else if (iphoneIndex > -1) {
			int modelEnd = findEndIndex(ua, iphoneIndex);
			out.put("phoneModel", ua.substring(iphoneIndex, modelEnd).trim());// 手机型号
			out.put("osv", ua.substring(osvStart, osvEnd).trim().replace('_', '.')); // 系统版本
		} else if (ipadIndex > -1) {
			int modelEnd = findEndIndex(ua, ipadIndex);
			out.put("phoneModel", ua.substring(ipadIndex, modelEnd).trim());// 手机型号
			out.put("osv", ua.substring(osvStart, osvEnd).trim().replace('_', '.')); // 系统版本
		} else if (macintoshIndex > -1) {
			int modelEnd = findEndIndex(ua, macintoshIndex);
			out.put("phoneModel", ua.substring(macintoshIndex, modelEnd).trim());// 手机型号
			out.put("osv", ua.substring(osvStart, osvEnd).trim().replace('_', '.')); // 系统版本
		} else {
			out.put("phoneModel", "");// 手机型号
			out.put("osv", ua.substring(osvStart, osvEnd).trim()); // 系统版本
		}
	}

	private static void findBrowserInfo(final String ua, final Map<String, String> out, boolean isAndroid) {
		final int ucIndex = ua.indexOf("UC");
		final int ucwebIndex = ua.indexOf("UCWEB");
		final int mqqIndex = ua.indexOf("MQQBrowser");
		final int qqIndex = ua.indexOf("QQBrowser");
		final int chromeIndex = ua.indexOf("Chrome");
		final int criosIndex = ua.indexOf("CriOS");
		final int _360Index = ua.indexOf("360");
		final int ieIndex = ua.indexOf("MSIE");
		final int safariIndex = ua.indexOf("Safari");
		final int browserIndex = ua.indexOf("Browser");
		final int mobileIndex = ua.indexOf("Mobile");
		if (ucwebIndex > -1) {
			int infoEnd = findEndIndex(ua, ucwebIndex);
			out.put("browser", "UCWEB");
			out.put("browserInfo", ua.substring(ucwebIndex, infoEnd));
		} else if (ucIndex > -1) {
			int infoEnd = findEndIndex(ua, ucIndex);
			out.put("browser", "UC");
			out.put("browserInfo", ua.substring(ucIndex, infoEnd));
		} else if (mqqIndex > -1) {
			int infoEnd = findEndIndex(ua, mqqIndex);
			out.put("browser", "MQQBrowser");
			out.put("browserInfo", ua.substring(mqqIndex, infoEnd));
		} else if (qqIndex > -1) {
			int infoEnd = findEndIndex(ua, qqIndex);
			out.put("browser", "QQBrowser");
			out.put("browserInfo", ua.substring(qqIndex, infoEnd));
		} else if (chromeIndex > -1) {
			int infoEnd = findEndIndex(ua, chromeIndex);
			out.put("browser", "Chrome");
			out.put("browserInfo", ua.substring(chromeIndex, infoEnd));
		} else if (criosIndex > -1) {
			int infoEnd = findEndIndex(ua, criosIndex);
			out.put("browser", "CriOS");
			out.put("browserInfo", ua.substring(criosIndex, infoEnd));
		} else if (_360Index > -1) {
			int infoEnd = findEndIndex(ua, _360Index);
			out.put("browser", "360browser");
			out.put("browserInfo", ua.substring(_360Index, infoEnd));
		} else if (ieIndex > -1) {
			int infoEnd = findEndIndex(ua, ieIndex);
			out.put("browser", "MSIE");
			out.put("browserInfo", ua.substring(ieIndex, infoEnd));
		} else if (browserIndex > -1) {
			int infoEnd = findEndIndex(ua, browserIndex);
			out.put("browser", "Browser");
			out.put("browserInfo", ua.substring(browserIndex, infoEnd));
		} else {
			if (!isAndroid && safariIndex > -1) {
				int infoEnd = findEndIndex(ua, safariIndex);
				out.put("browser", "Safari");
				out.put("browserInfo", ua.substring(safariIndex, infoEnd));
			} else if (mobileIndex > -1) {
				int infoEnd = ua.indexOf(SPACE, safariIndex > mobileIndex ? safariIndex : mobileIndex);
				if (infoEnd > -1) {
					out.put("browser", ua.substring(infoEnd + 1));
					out.put("browserInfo", ua.substring(infoEnd + 1));
				} else {
					out.put("browser", "");
					out.put("browserInfo", "");
				}

			} else {
				out.put("browser", "");
				out.put("browserInfo", "");
			}
		}
	}

	private static final int findEndIndex(final String ua, final int startIndex) {
		int infoEnd = ua.indexOf(SEMICOLON, startIndex);
		if (infoEnd == -1) {
			infoEnd = ua.indexOf(SPACE, startIndex);
			if (infoEnd == -1) {
				infoEnd = ua.length();
			}
		}
		return infoEnd;
	}

	public static void main(String[] args) {
	}
}
