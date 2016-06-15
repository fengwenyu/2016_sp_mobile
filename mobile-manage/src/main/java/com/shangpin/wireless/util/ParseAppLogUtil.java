package com.shangpin.wireless.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import net.sf.json.JSONObject;
import org.springframework.context.ApplicationContext;

import com.shangpin.wireless.domain.UserBehavioralAolaiAndriod;
import com.shangpin.wireless.domain.UserBehavioralAolaiIphone;
import com.shangpin.wireless.domain.UserBehavioralSPAndriod;
import com.shangpin.wireless.domain.UserBehavioralSPIphone;
import com.shangpin.wireless.manage.service.UserBehavioralAolaiAndriodService;
import com.shangpin.wireless.manage.service.UserBehavioralAolaiIphoneService;
import com.shangpin.wireless.manage.service.UserBehavioralSPAndriodService;
import com.shangpin.wireless.manage.service.UserBehavioralSPIphoneService;

public class ParseAppLogUtil {
	private static ArrayList<String> fileList = new ArrayList<String>();

	public static void parseOnedayLog(String filePath) throws Exception {
		try {
			ApplicationContext ac = ApplicationContextUtil.get();
			UserBehavioralAolaiAndriodService userBehavioralAolaiAndriodService = (UserBehavioralAolaiAndriodService) ac.getBean(UserBehavioralAolaiAndriodService.SERVICE_NAME);
			UserBehavioralSPAndriodService userBehavioralSPAndriodService = (UserBehavioralSPAndriodService) ac.getBean(UserBehavioralSPAndriodService.SERVICE_NAME);
			UserBehavioralSPIphoneService userBehavioralSPIphoneService = (UserBehavioralSPIphoneService) ac.getBean(UserBehavioralSPIphoneService.SERVICE_NAME);
			UserBehavioralAolaiIphoneService userBehavioralAolaiIphoneService = (UserBehavioralAolaiIphoneService) ac.getBean(UserBehavioralAolaiIphoneService.SERVICE_NAME);
			File headerLogFile = new File(filePath + "/header.log");
			if (headerLogFile.exists()) {
				String headerlog = readHeaderLog(headerLogFile);
				JSONObject obj = JSONObject.fromObject(headerlog);
				if (obj.get("os").equals("android") && obj.get("productNum").equals("101")) {
					File root = new File(filePath);
					File[] files = root.listFiles();
					for (File file : files) {
						String fileNameNoEx = getFileNameNoEx(file.getName());
						if (!file.getName().equals("header.log")) {
							String compressPath = filePath + "/" + fileNameNoEx;
							String compressFile = filePath + "/" + file.getName();
							unzipFile(compressPath, compressFile);
							File rootZip = new File(compressPath);
							if (rootZip.exists()) {
								File[] fileszip = rootZip.listFiles();
								for (File filezip : fileszip) {
									BufferedReader reader = null;
									try {
										System.out.println("readFileInLine:" + filezip.getName());
										reader = new BufferedReader(new InputStreamReader(new FileInputStream(filezip), "UTF-8"));
										String tempString = null;
										int line = 1;
										// 一次读入一行，直到读入null为文件结束
										while ((tempString = reader.readLine()) != null) {
											System.out.println("line " + line + ": " + tempString);
											// Entity entity = parse(tempString);
											String[] infos = tempString.split("\\|");
											if(obj.get("ver").equals("0.9.1")||obj.get("ver").equals("1.0.0")||obj.get("ver").equals("1.0.1")){
												for(int i=0;i<infos.length;i++){
													Entity entity = new Entity();
													// String imei=obj.get("imei").toString();
													entity.imei = obj.get("imei").toString();
	//												entity.logTime = Long.valueOf(infos[0]);
													entity.name = infos[i];
													if (entity != null) {
														entity.saveUserBehavioralAolaiAndriod(userBehavioralAolaiAndriodService);
													}
												}
											}else{
												Entity entity = new Entity();
												// String imei=obj.get("imei").toString();
												entity.imei = obj.get("imei").toString();
												entity.logTime = Long.valueOf(infos[0]);
												entity.name = infos[1];
												if (entity != null) {
													entity.saveUserBehavioralAolaiAndriod(userBehavioralAolaiAndriodService);
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
								}
								file.renameTo(new File(file.getParent() + File.separator + "old" + file.getName()));
								File parseFile = new File(compressPath);
								deleteFile(parseFile);
							}
						}
					}
					headerLogFile.renameTo(new File(headerLogFile.getParent() + File.separator + "old" + headerLogFile.getName()));
				}
				if (obj.get("os").equals("android") && obj.get("productNum").equals("102")) {
					File root = new File(filePath);
					File[] files = root.listFiles();
					for (File file : files) {
						String fileNameNoEx = getFileNameNoEx(file.getName());
						if (!file.getName().equals("header.log")) {
							String compressPath = filePath + "/" + fileNameNoEx;
							String compressFile = filePath + "/" + file.getName();
							unzipFile(compressPath, compressFile);
							File rootZip = new File(compressPath);
							if (rootZip.exists()) {
								File[] fileszip = rootZip.listFiles();
								for (File filezip : fileszip) {
									BufferedReader reader = null;
									try {
										System.out.println("readFileInLine:" + filezip.getName());
										reader = new BufferedReader(new InputStreamReader(new FileInputStream(filezip), "UTF-8"));
										String tempString = null;
										int line = 1;
										// 一次读入一行，直到读入null为文件结束
										while ((tempString = reader.readLine()) != null) {
											System.out.println("line " + line + ": " + tempString);
											String[] infos = tempString.split("\\|");
											if(obj.get("ver").equals("1.0.0")||obj.get("ver").equals("1.0.2")||obj.get("ver").equals("1.0.3")||obj.get("ver").equals("1.0.4")){
												for(int i=0;i<infos.length;i++){
													Entity entity = new Entity();
													entity.imei = obj.get("imei").toString();
													entity.name = infos[i];
													if (entity != null) {
														entity.saveUserBehavioralSPAndriod(userBehavioralSPAndriodService);
													}
												}
											}else{
												Entity entity = new Entity();
												entity.imei = obj.get("imei").toString();
												entity.logTime = Long.valueOf(infos[0]);
												entity.name = infos[1];
												if (entity != null) {
													entity.saveUserBehavioralSPAndriod(userBehavioralSPAndriodService);
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
								}
								file.renameTo(new File(file.getParent() + File.separator + "old" + file.getName()));
								File parseFile = new File(compressPath);
								deleteFile(parseFile);
							}
						}
					}
					headerLogFile.renameTo(new File(headerLogFile.getParent() + File.separator + "old" + headerLogFile.getName()));
				}
				if (obj.get("os").equals("ios") && obj.get("productNum").equals("1")) {
					File root = new File(filePath);
					File[] files = root.listFiles();
					for (File file : files) {
						String fileNameNoEx = getFileNameNoEx(file.getName());
						if (!file.getName().equals("header.log")) {
							String compressPath = filePath + "/" + fileNameNoEx;
							String compressFile = filePath + "/" + file.getName();
							unzipFile(compressPath, compressFile);
							File rootZip = new File(compressPath);
							if (rootZip.exists()) {
								File[] fileszip = rootZip.listFiles();
								for (File filezip : fileszip) {
									BufferedReader reader = null;
									try {
										System.out.println("readFileInLine:" + filezip.getName());
										reader = new BufferedReader(new InputStreamReader(new FileInputStream(filezip), "UTF-8"));
										String tempString = null;
										int line = 1;
										// 一次读入一行，直到读入null为文件结束
										while ((tempString = reader.readLine()) != null) {
											System.out.println("line " + line + ": " + tempString);
											// Entity entity = parse(tempString);
											String[] infos = tempString.split("\\|");
											Entity entity = new Entity();
											// String imei=obj.get("imei").toString();
											entity.imei = obj.get("imei").toString();
											entity.logTime = Long.valueOf(infos[0]);
											entity.name = infos[1];
											if (entity != null) {
												entity.saveUserBehavioralAolaiIphone(userBehavioralAolaiIphoneService);
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
								file.renameTo(new File(file.getParent() + File.separator + "old" + file.getName()));
								File parseFile = new File(compressPath);
								deleteFile(parseFile);
							}
						}
					}
					headerLogFile.renameTo(new File(headerLogFile.getParent() + File.separator + "old" + headerLogFile.getName()));
				}
				if (obj.get("os").equals("ios") && obj.get("productNum").equals("2")) {
					File root = new File(filePath);
					File[] files = root.listFiles();
					for (File file : files) {
						String fileNameNoEx = getFileNameNoEx(file.getName());
						if (!file.getName().equals("header.log")) {
							String compressPath = filePath + "/" + fileNameNoEx;
							String compressFile = filePath + "/" + file.getName();
							unzipFile(compressPath, compressFile);
							File rootZip = new File(compressPath);
							if (rootZip.exists()) {
								File[] fileszip = rootZip.listFiles();
								for (File filezip : fileszip) {
									BufferedReader reader = null;
									try {
										System.out.println("readFileInLine:" + filezip.getName());
										reader = new BufferedReader(new InputStreamReader(new FileInputStream(filezip), "UTF-8"));
										String tempString = null;
										int line = 1;
										// 一次读入一行，直到读入null为文件结束
										while ((tempString = reader.readLine()) != null) {
											System.out.println("line " + line + ": " + tempString);
											// Entity entity = parse(tempString);
											String[] infos = tempString.split("\\|");
											Entity entity = new Entity();
											// String imei=obj.get("imei").toString();
											entity.imei = obj.get("imei").toString();
											entity.logTime = Long.valueOf(infos[0]);
											entity.name = infos[1];
											if (entity != null) {
												entity.saveUserBehavioralSPIphone(userBehavioralSPIphoneService);
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
								file.renameTo(new File(file.getParent() + File.separator + "old" + file.getName()));
								File parseFile = new File(compressPath);
								deleteFile(parseFile);
							}
						}
					}
					headerLogFile.renameTo(new File(headerLogFile.getParent() + File.separator + "old" + headerLogFile.getName()));
				}
			}
		} catch (Exception e) {
			throw new Exception();
		}
	}

	// 读文件，返回字符串
	public static String readHeaderLog(File file) {
		BufferedReader reader = null;
		String laststr = "";
		try {
			// System.out.println("以行为单位读取文件内容，一次读一整行：");
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				System.out.println("line " + line + ": " + tempString);
				laststr = laststr + tempString;
				line++;
			}
			reader.close();
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
		return laststr;
	}

	static class Entity {
		String imei;
		long logTime;
		String name;

		public void saveUserBehavioralAolaiAndriod(UserBehavioralAolaiAndriodService userBehavioralAolaiAndriodService) {
			try {
				final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				UserBehavioralAolaiAndriod userBehavioralAolaiAndriod = new UserBehavioralAolaiAndriod();
				userBehavioralAolaiAndriod.setImei(imei);
				System.out.println("时间   "+logTime);
				userBehavioralAolaiAndriod.setBehaviorName(name);
				if(logTime!=0){
					userBehavioralAolaiAndriod.setDate(sdf.parse(sdf.format((new Date(logTime)))));
				}
				userBehavioralAolaiAndriodService.save(userBehavioralAolaiAndriod);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void saveUserBehavioralSPAndriod(UserBehavioralSPAndriodService userBehavioralSPAndriodService) {
			try {
				final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				UserBehavioralSPAndriod userBehavioralSPAndriod = new UserBehavioralSPAndriod();
				userBehavioralSPAndriod.setImei(imei);
				userBehavioralSPAndriod.setBehaviorName(name);
				System.out.println("时间   "+logTime);
				if(logTime!=0){
					userBehavioralSPAndriod.setDate(sdf.parse(sdf.format((new Date(logTime)))));
				}
				userBehavioralSPAndriodService.save(userBehavioralSPAndriod);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void saveUserBehavioralAolaiIphone(UserBehavioralAolaiIphoneService userBehavioralAolaiIphoneService) {
			try {
				final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				UserBehavioralAolaiIphone userBehavioralAolaiIphone = new UserBehavioralAolaiIphone();
				userBehavioralAolaiIphone.setImei(imei);
				userBehavioralAolaiIphone.setBehaviorName(name);
				userBehavioralAolaiIphone.setDate(sdf.parse(sdf.format((new Date(logTime)))));
				userBehavioralAolaiIphoneService.save(userBehavioralAolaiIphone);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		public void saveUserBehavioralSPIphone(UserBehavioralSPIphoneService userBehavioralSPIphoneService) {
			try {
				final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				UserBehavioralSPIphone UserBehavioralSPIphone = new UserBehavioralSPIphone();
				UserBehavioralSPIphone.setImei(imei);
				UserBehavioralSPIphone.setBehaviorName(name);
				UserBehavioralSPIphone.setDate(sdf.parse(sdf.format((new Date(logTime)))));
				userBehavioralSPIphoneService.save(UserBehavioralSPIphone);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 解压文件
	 * 
	 * @param targetPath
	 *            解压到的文件路径
	 * @param zipFilePath
	 *            压缩文件路径
	 */
	public static void unzipFile(String targetPath, String zipFilePath) {
		try {
			File zipFile = new File(zipFilePath);
			InputStream is = new FileInputStream(zipFile);
			ZipInputStream zis = new ZipInputStream(is);
			ZipEntry entry = null;
			System.out.println("开始解压:" + zipFile.getName() + "...");
			while ((entry = zis.getNextEntry()) != null) {
				String zipPath = entry.getName();
				String s = zipPath.substring(zipPath.lastIndexOf("/") + 1);
				try {
					if (entry.isDirectory()) {
						File zipFolder = new File(targetPath + File.separator + s);
						if (!zipFolder.exists()) {
							zipFolder.mkdirs();
						}
					} else {
						File file = new File(targetPath + File.separator + s);
						if (!file.exists()) {
							File pathDir = file.getParentFile();
							pathDir.mkdirs();
							file.createNewFile();
						}
						FileOutputStream fos = new FileOutputStream(file);
						int bread;
						while ((bread = zis.read()) != -1) {
							fos.write(bread);
						}
						fos.close();
					}
					System.out.println("成功解压:" + zipPath);
				} catch (Exception e) {
					System.out.println("解压" + zipPath + "失败");
					continue;
				}
			}
			zis.close();
			is.close();
			System.out.println("解压结束");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 得到不带后缀文件名
	 * 
	 * @param fileName
	 *            文件名带后缀
	 * @return String 不带后缀文件名
	 * 
	 */
	public static String getFileNameNoEx(String fileName) {
		if ((fileName != null) && (fileName.length() > 0)) {
			int dot = fileName.lastIndexOf('.');
			if ((dot > -1) && (dot < (fileName.length()))) {
				return fileName.substring(0, dot);
			}
		}
		return fileName;
	}

	/**
	 * 得到文件目录名的list
	 * 
	 * @param root
	 *            文件
	 * @return ArrayList<String> 文件目录名的list
	 * 
	 */
	public static ArrayList<String> getDirectoryNamelist(File root) {
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				fileList.add(file.getName());
			}
		}
		return fileList;
	}

	/**
	 * 递归删除文件
	 * 
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
				file.delete();
			}
		} else {
			System.out.println("deleteFile(" + file.getName() + ") not found" + '\n');
		}
	}
}
