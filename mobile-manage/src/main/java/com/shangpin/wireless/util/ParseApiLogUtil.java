package com.shangpin.wireless.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import com.shangpin.wireless.domain.AccountStatistics;
import com.shangpin.wireless.domain.DeviceInfo;
import com.shangpin.wireless.domain.OrderStatistics;
import com.shangpin.wireless.domain.PageView;
import com.shangpin.wireless.manage.service.AccountStatisticsService;
import com.shangpin.wireless.manage.service.DeviceInfoService;
import com.shangpin.wireless.manage.service.OrderStatisticsService;
import com.shangpin.wireless.manage.service.PageViewService;

/**
 * 
 * @Author wangwenguan
 * @CreateDate 2012-11-21
 */
public class ParseApiLogUtil {

	public static void main(String[] args) throws ParseException {

	}

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
	 * 递归删除文件
	 * 
	 * @Return
	 */
	public static void deleteFile(File file) {
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
				// file.renameTo(new File(file.getParent() + File.separator + "old" + file.getName()));
				file.delete();
			} else if (file.isDirectory()) { // 否则如果它是一个目录
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					deleteFile(files[i]);
				}
			}
		} else {
			System.out.println("deleteFile(" + file.getName() + ") not found" + '\n');
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
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// System.out.println("line " + line + ": " + tempString);
				Entity entity = parse(tempString);
				if (entity != null) {
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
					e1.printStackTrace();
				}
			}
		}
	}

	public static void parse1006_1019() {
		try {
			// final String directory = "D:\\parselog\\1006_1019\\";
			final String directory = "/home/parselog/1006_1019/";
			List<Entity> logList = new ArrayList<Entity>();
			for (int i = 1006; i <= 1019; i++) {
				StringBuilder sb = new StringBuilder();
				sb.append(i);
				sb.insert(2, "-");
				sb.insert(0, "2012-");
				sb.append(".txt");
				readFile(new File(directory + sb.toString()), logList);
			}
			for (int i = 0; i < logList.size(); i++) {
				for (int j = 0; j < i; j++) {
					Entity entityi = logList.get(i);
					Entity entityj = logList.get(j);
					if (entityi.logtime <= entityj.logtime) {
						Entity temp = entityi;
						logList.set(i, entityj);
						logList.set(j, temp);
					}
				}
			}
			ApplicationContext ac = ApplicationContextUtil.get();
			AccountStatisticsService accountService = (AccountStatisticsService) ac.getBean(AccountStatisticsService.SERVICE_NAME);
			DeviceInfoService deviceService = (DeviceInfoService) ac.getBean(DeviceInfoService.SERVICE_NAME);
			OrderStatisticsService orderService = (OrderStatisticsService) ac.getBean(OrderStatisticsService.SERVICE_NAME);
			PageViewService pageviewService = (PageViewService) ac.getBean(PageViewService.SERVICE_NAME);

			List<Entity> orderSubmitList = new ArrayList<Entity>();
			List<Entity> orderSubmitResultList = new ArrayList<Entity>();
			List<Entity> payOrderList = new ArrayList<Entity>();
			List<Entity> paySuccessList = new ArrayList<Entity>();
			while (logList.size() > 0) {
				Entity entity = logList.get(0);
				if (!"orderStatistics".equals(entity.name) && !"paysuccess".equals(entity.name)) {
					entity.saveAccountStatistics(accountService);
					entity.saveDeviceInfo(deviceService);
					entity.savePageView(pageviewService);
				}
				if ("order".equals(entity.name)) {
					orderSubmitList.add(entity);
				}
				if ("orderStatistics".equals(entity.name)) {
					orderSubmitResultList.add(entity);
				}
				if ("payorder".equals(entity.name)) {
					payOrderList.add(entity);
				}
				if ("paysuccess".equals(entity.name)) {
					paySuccessList.add(entity);
				}

				logList.remove(0);
			}
			for (int i = 0; i < orderSubmitResultList.size(); i++) {
				Entity orderResult = orderSubmitResultList.get(i);
				Map<String, String> orderResultParamMap = orderResult.paramMap;
				for (int j = 0; j < orderSubmitList.size(); j++) {
					Entity order = orderSubmitList.get(j);
					Map<String, String> orderParamMap = order.paramMap;
					if (orderResult.imei.equals(order.imei) && orderResult.logtime == order.logtime) {
						orderResultParamMap.put("userid", orderParamMap.get("userid"));
						orderResultParamMap.put("orderorign", orderParamMap.get("orderorign"));
						orderResultParamMap.put("ordertype", orderParamMap.get("ordertype"));
						break;
					}
				}
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
			for (int i = 1006; i <= 1019; i++) {
				StringBuilder sb = new StringBuilder();
				sb.append(i);
				sb.insert(2, "-");
				sb.insert(0, "2012-");
				sb.append(".txt");
				File file = new File(directory + sb.toString());
				// java.nio.file.Files file;
				if (file.exists()) {
					file.renameTo(new File(directory + "old" + sb.toString()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void parse1020_1109_1121() {
		try {
			// final String directory = "D:\\parselog\\1020_1109_1121\\";
			final String directory = "/home/parselog/1020_1109_1121/";
			List<Entity> logList = new ArrayList<Entity>();

			for (int i = 1020; i <= 1031; i++) {
				StringBuilder sb = new StringBuilder();
				sb.append(i);
				sb.insert(2, "-");
				sb.insert(0, "2012-");
				sb.append(".txt");
				readFile(new File(directory + sb.toString()), logList);
			}
			for (int i = 1101; i <= 1121; i++) {
				StringBuilder sb = new StringBuilder();
				sb.append(i);
				sb.insert(2, "-");
				sb.insert(0, "2012-");
				sb.append(".txt");
				readFile(new File(directory + sb.toString()), logList);
			}

			// System.out.println("===========================================");
			// for (int i=0;i<logList.size();i++) {
			// Entity entityi = logList.get(i);
			// System.out.println(i + "=>" + entityi.logtime + "|" + entityi.name + "|" + entityi.params);
			// }
			ApplicationContext ac = ApplicationContextUtil.get();
			AccountStatisticsService accountService = (AccountStatisticsService) ac.getBean(AccountStatisticsService.SERVICE_NAME);
			DeviceInfoService deviceService = (DeviceInfoService) ac.getBean(DeviceInfoService.SERVICE_NAME);
			OrderStatisticsService orderService = (OrderStatisticsService) ac.getBean(OrderStatisticsService.SERVICE_NAME);
			PageViewService pageviewService = (PageViewService) ac.getBean(PageViewService.SERVICE_NAME);

			List<Entity> orderSubmitList = new ArrayList<Entity>();
			List<Entity> orderSubmitResultList = new ArrayList<Entity>();
			List<Entity> payOrderList = new ArrayList<Entity>();
			List<Entity> paySuccessList = new ArrayList<Entity>();
			while (logList.size() > 0) {
				Entity entity = logList.get(0);
				if (!"orderStatistics".equals(entity.name) && !"paysuccess".equals(entity.name)) {
					entity.saveAccountStatistics(accountService);
					entity.saveDeviceInfo(deviceService);
					entity.savePageView(pageviewService);
				}
				if ("order".equals(entity.name)) {
					orderSubmitList.add(entity);
				}
				if ("orderStatistics".equals(entity.name)) {
					orderSubmitResultList.add(entity);
				}
				if ("payorder".equals(entity.name)) {
					payOrderList.add(entity);
				}
				if ("paysuccess".equals(entity.name)) {
					paySuccessList.add(entity);
				}

				logList.remove(0);
			}
			for (int i = 0; i < orderSubmitResultList.size(); i++) {
				Entity orderResult = orderSubmitResultList.get(i);
				Map<String, String> orderResultParamMap = orderResult.paramMap;
				for (int j = 0; j < orderSubmitList.size(); j++) {
					Entity order = orderSubmitList.get(j);
					Map<String, String> orderParamMap = order.paramMap;
					if (orderResult.imei.equals(order.imei) && orderResult.logtime == order.logtime) {
						orderResultParamMap.put("userid", orderParamMap.get("userid"));
						orderResultParamMap.put("orderorign", orderParamMap.get("orderorign"));
						orderResultParamMap.put("ordertype", orderParamMap.get("ordertype"));
						break;
					}
				}
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
			for (int i = 1020; i <= 1031; i++) {
				StringBuilder sb = new StringBuilder();
				sb.append(i);
				sb.insert(2, "-");
				sb.insert(0, "2012-");
				sb.append(".txt");
				File file = new File(directory + sb.toString());
				if (file.exists()) {
					file.renameTo(new File(directory + "old" + sb.toString()));
				}
			}
			for (int i = 1101; i <= 1121; i++) {
				StringBuilder sb = new StringBuilder();
				sb.append(i);
				sb.insert(2, "-");
				sb.insert(0, "2012-");
				sb.append(".txt");
				File file = new File(directory + sb.toString());
				if (file.exists()) {
					file.renameTo(new File(directory + "old" + sb.toString()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void parseApiLog() {
		try {
			// final File directory = new File("/home/parselog/new/");
			// if (directory.isDirectory()) { // 否则如果它是一个目录
			// File files[] = directory.listFiles();
			// for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
			// parseOnedayLog(files[i]);
			// }
			// }
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date first = sdf.parse("2013-01-18");
			for (int i = 0; i < 14; i++) {
				String name = DateUtil.getAfterAmountDay(first, i, "yyyy-MM-dd");
				File file = new File("/home/parselog/api_logs/" + name + ".txt");
				if (file.exists()) {
					parseOnedayLog(new File("/home/parselog/api_logs/" + name + ".txt"));
				}
			}
			Date second = sdf.parse("2013-02-01");
			for (int i = 0; i < 28; i++) {
				String name = DateUtil.getAfterAmountDay(second, i, "yyyy-MM-dd");
				File file = new File("/home/parselog/api_logs/" + name + ".txt");
				if (file.exists()) {
					parseOnedayLog(new File("/home/parselog/api_logs/" + name + ".txt"));
				}
			}
			Date third = sdf.parse("2013-03-01");
			for (int i = 0; i < 31; i++) {
				String name = DateUtil.getAfterAmountDay(third, i, "yyyy-MM-dd");
				File file = new File("/home/parselog/api_logs/" + name + ".txt");
				if (file.exists()) {
					parseOnedayLog(new File("/home/parselog/api_logs/" + name + ".txt"));
				}
			}
			Date four = sdf.parse("2013-04-01");
			for (int i = 0; i < 30; i++) {
				String name = DateUtil.getAfterAmountDay(four, i, "yyyy-MM-dd");
				File file = new File("/home/parselog/api_logs/" + name + ".txt");
				if (file.exists()) {
					parseOnedayLog(new File("/home/parselog/api_logs/" + name + ".txt"));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void parseYesterdayLog() {
		String fileName = DateUtil.getAfterAmountDay(new Date(), -1, "yyyy-MM-dd");
		parseOnedayLog(new File("/home/parselog/api_logs/" + fileName + ".txt"));
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

			ApplicationContext ac = ApplicationContextUtil.get();
			AccountStatisticsService accountService = (AccountStatisticsService) ac.getBean(AccountStatisticsService.SERVICE_NAME);
			DeviceInfoService deviceService = (DeviceInfoService) ac.getBean(DeviceInfoService.SERVICE_NAME);
			OrderStatisticsService orderService = (OrderStatisticsService) ac.getBean(OrderStatisticsService.SERVICE_NAME);
			PageViewService pageviewService = (PageViewService) ac.getBean(PageViewService.SERVICE_NAME);

			List<Entity> orderSubmitList = new ArrayList<Entity>();
			List<Entity> orderSubmitResultList = new ArrayList<Entity>();
			List<Entity> payOrderList = new ArrayList<Entity>();
			List<Entity> paySuccessList = new ArrayList<Entity>();
			
			BufferedReader reader = null;
			try {
				System.out.println("readFileInLine:" + file.getName());
				reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
				String tempString = null;
				int line = 1;
				final String filename = file.getName();
				int start = filename.indexOf("#");
				if (start < 0) start = 0;
				final int end = filename.lastIndexOf(".");
				final String date = filename.substring(start, end);
				String tread = Thread.currentThread().getId() + "--" + Thread.currentThread().getName();
				// 一次读入一行，直到读入null为文件结束
				while ((tempString = reader.readLine()) != null) {
//					System.out.println("line " + line + ": " + tempString);
					if (line % 1000 == 0) {
						System.out.println(file.getName() + " line: " + line + "==>" + tread + "\n" + tempString);
					}
					Entity entity = parse(tempString);
					if (entity != null) {
						entity.date = date;
						entity.lineNum = String.valueOf(line);
						if (!"orderStatistics".equals(entity.name) && !"paysuccess".equals(entity.name)
								&& !"IpLoginLimit".equals(entity.name)
								&& !"pushManage".equals(entity.name)) {
							entity.saveAccountStatistics(accountService);
							entity.saveDeviceInfo(deviceService);
							entity.savePageView(pageviewService);
						}
						if ("order".equals(entity.name)) {
							orderSubmitList.add(entity);
						}
						if ("orderStatistics".equals(entity.name)) {
							orderSubmitResultList.add(entity);
						}
						if ("payorder".equals(entity.name)) {
							payOrderList.add(entity);
						}
						if ("paysuccess".equals(entity.name)) {
							paySuccessList.add(entity);
						}

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
						e1.printStackTrace();
					}
				}
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
		String[] headers = infos[3].split(",");
		try {
			if ("null".equals(headers[0]) || "#".equals(headers[0])) {
				headers[0] = "";
			}
			entity.imei = headers[0];
			entity.platform = headers[1];
			entity.osv = headers[2];
			try {
				entity.productNum = Long.parseLong(headers[3]);
				entity.channelNum = Long.parseLong(headers[4]);
			} catch (Exception e) {
			}
			entity.ver = headers[5];
			entity.apn = headers[6];
			entity.resolution = headers[7];
			entity.phoneType = headers[8];
			entity.phoneModel = headers[9];
			if (headers.length > 10) {
				entity.operator = headers[10];
			} else {
				entity.operator = "";
			}
			entity.params = infos[4];
			entity.paramMap = new HashMap<String, String>();
			String[] paramPairs = infos[4].split(",");
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
		String params;
		Map<String, String> paramMap;
		
		String date;
		String lineNum;

		public void saveAccountStatistics(AccountStatisticsService accountService) {
			if (!"login".equals(name) && !"regist".equals(name) && !"thirdlogin".equals(name)) {// 
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
					if ("regist".equals(name)) {
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
					} else if ("login".equals(name) || "thirdlogin".equals(name)) {
						final String userId = paramMap.get("userId");
						final String username = paramMap.get("username");
						HqlHelper hqlHelper = new HqlHelper(AccountStatistics.class, "a");
						hqlHelper.addWhereCondition("a.userId=?", userId);
//						hqlHelper.addWhereCondition("a.username=?", username);
						hqlHelper.addWhereCondition("a.productNum=?", productNum);
						hqlHelper.addWhereCondition("a.channelNum=?", channelNum);
						hqlHelper.addWhereCondition("a.logTime BETWEEN '" + date + " 00:00:00' AND '" + date + " 23:59:59'");
						try {
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
							account.setMode(paramMap.get("mode"));
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
						} catch (Exception e) {
							System.out.println("error: userid:" + paramMap.get("userId") + "imei:" + imei + "loginTime:" + paramMap.get("loginTime") + "createTime:" + paramMap.get("createTime"));
							e.printStackTrace();
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void saveDeviceInfo(DeviceInfoService deviceService) {
			if ("index".equals(name) || "aolaiindex".equals(name) || "shangpintopic".equals(name) || "shangpinindex".equals(name)) {
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
					deviceinfo.setReserve5(lineNum);

					deviceService.save(deviceinfo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		public void saveOrderStatistics(OrderStatisticsService orderService) {
			if (!"orderStatistics".equals(name) && !"payorder".equals(name) && !"paysuccess".equals(name)) {
				return;
			}
			try {
				String city = null;
				if(StringUtils.isNotEmpty(ip) && !"#".equals(ip)) {
					city = IPUtil.getCountryCityByIp(ip);
				}
				Date now = new Date();
				if ("orderStatistics".equals(name)) {
					final String orderid = paramMap.get("orderid");
					if (orderid == null || orderid.length() == 0) {
						return;
					}
					HqlHelper hqlHelper = new HqlHelper(OrderStatistics.class, "a");
					hqlHelper.addWhereCondition("a.orderId=?", orderid);
					OrderStatistics order = orderService.getByCondition(hqlHelper);
					final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
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
						order.setOrderOrigin(paramMap.get("orderorign"));
						order.setOrderType(paramMap.get("ordertype"));
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
						order.setOrderOrigin(paramMap.get("orderorign"));
						order.setOrderType(paramMap.get("ordertype"));
						order.setOrderRetcode(paramMap.get("code"));
						order.setIp(ip);
						orderService.update(order);
					}
				} else if ("payorder".equals(name)) {
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
						order.setOrderOrigin(paramMap.get("orderOrigin"));

						orderService.update(order);
					} else {
						order = new OrderStatistics();
						order.setOrderId(paramMap.get("orderid"));
						order.setUserId(paramMap.get("userid"));
						order.setPayProduct(productNum);
						order.setPayChannel(channelNum);
						order.setMainPayMode(paramMap.get("mainpaymode"));
						order.setSubPayMode(paramMap.get("subpaymode"));
						order.setOrderAmount(Float.parseFloat(paramMap.get("orderamount")));
						order.setOrderOrigin(paramMap.get("orderOrigin"));
						order.setIp(ip);
						if(StringUtils.isNotEmpty(ip))
							order.setCity(city);
						order.setCreateTime(now);
						orderService.save(order);
					}
				} else if ("paysuccess".equals(name)) {
					final String orderid = paramMap.get("orderid");
					HqlHelper hqlHelper = new HqlHelper(OrderStatistics.class, "a");
					hqlHelper.addWhereCondition("a.orderId=?", orderid);
					OrderStatistics order = orderService.getByCondition(hqlHelper);
					// 登录需要记录到order表的信息
					if (order != null) {
						if (!"1".equals(order.getIspaid())) {
							order.setIspaid(paramMap.get("success"));
							order.setPayTime(new Date(logtime));
							order.setMainPayMode(paramMap.get("payid"));
							order.setSubPayMode(paramMap.get("paychildid"));

							orderService.update(order);
						}
					} else {
						order = new OrderStatistics();
						String paymode = paramMap.get("payid");
						order.setOrderId(paramMap.get("orderid"));
						order.setIspaid("2".equals(paymode)?"0":paramMap.get("success"));
						order.setPayTime(new Date(logtime));
						order.setMainPayMode(paymode);
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

		public void savePageView(PageViewService pageviewService) {
			if ("orderStatistics".equals(name) || "paysuccess".equals(name)
					|| "userbuyinfo".equals(name) || "errorlog".equals(name)
					|| "IpLoginLimit".equals(name)) {
				return;
			}
			PageView pageview = new PageView();
			try {
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
//				pageview = new PageView();
//				pageview.setApiname(name);
//				pageview.setVisitIp(ip);
//				pageview.setImei(imei);
//				pageview.setOs(platform);
//				pageview.setOsv(osv);
//				pageview.setProductNum(productNum);
//				pageview.setChannelNum(channelNum);
//				pageview.setApn(apn);
//				pageview.setVer(ver);
//				pageview.setPhoneType(phoneType);
//				pageview.setPhoneModel(phoneModel);
//				pageview.setLogTime(new Date(logtime));
//				pageview.setCreateTime(new Date());
//				try {
//					pageviewService.save(pageview);
//				} catch (Exception e2) {
//					// TODO: handle exception
//				}
				e.printStackTrace();
				System.out.println(name+"-->"+ip+"-->"+imei+"-->"+platform+"-->"+osv+"-->"+productNum+"-->"+channelNum+"-->"+imei+"-->"+params+"-->"+logtime);
			}
		}
	}
}
