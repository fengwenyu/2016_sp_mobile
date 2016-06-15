package com.shangpin.wireless.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileTest {
	 private static ArrayList<String> filelist = new ArrayList<String>();
	 
	 public static void main(String[] args) throws Exception {
		 FileTest f=new FileTest();
//		 F: SPUserActivityLog175208531.zip
//		 String filePath = "F:/home/terminalLog/2013-05-27";
////	    String filePath = "F:/home/terminalLog/2013-05-27/2c2f4488c321d93b6b6adfb983d3395a";
////		 String filePath = "F:/home/terminalLog/2013-05-27/2c2f4488c321d93b6b6adfb983d3395a/SPUserActivityLog175208531.zip";
//		 String zfilePath = "F:/home/terminalLog/2013-05-27/2c2f4488c321d93b6b6adfb983d3395a/SPUserActivityLog175208531";
//	    getFiles(filePath);
		 String zfilePath = "F:/home/terminalLog/2013-05-27/";
		 File rootFile = new File(zfilePath);
		 rootFile.renameTo(new File(rootFile.getParent() + File.separator + "old" + rootFile.getName()));
//		 getFiles(zfilePath);
//		 File headerLogFile = new File(zfilePath+"/header.log");
//		 readHeaderLog(headerLogFile);
		 
//		f.unzipFile(zfilePath,filePath) ;
//	System.out.println(ParseAppLogUtil.ReadFile(zfilePath));	 
		 
	 } 
	 /*
	  * 通过递归得到某一路径下所有的目录及其文件
	  */
	 static void getFiles(String filePath){
	  File root = new File(filePath);
	    File[] files = root.listFiles();
	    for(File file:files){     
	     if(file.isDirectory()){
	      /*
	       * 递归调用
	       */
	      getFiles(file.getAbsolutePath());
	      filelist.add(file.getAbsolutePath());
	      System.out.println("显示"+filePath+"下所有子目录及其文件"+file.getName());
	     }else{
	      System.out.println("显示"+filePath+"下所有子目录"+file.getName());
	     }     
	    }
	 }
	 
	 /**
	  * 先
	  * @param targetPath
	  * @param zipFilePath
	  */
	 
	 public void unzipFile(String targetPath, String zipFilePath) {
			try {
				File zipFile = new File(zipFilePath);
				InputStream is = new FileInputStream(zipFile);
				ZipInputStream zis = new ZipInputStream(is);
				ZipEntry entry = null;
				System.out.println("开始解压:" + zipFile.getName() + "...");
				while ((entry = zis.getNextEntry()) != null) {
					String zipPath = entry.getName();
					String s = zipPath.substring(zipPath.lastIndexOf("/") + 1);
					System.out.println("内容是" + zipPath);
					System.out.println("lilinh是" + s);
					try {
						if (entry.isDirectory()) {
							File zipFolder = new File(targetPath + File.separator + s);
							System.out.println("llopo" + File.separator);
							if (!zipFolder.exists()) {
								zipFolder.mkdirs();
							}
						} else {
							File file = new File(targetPath + File.separator + s);
							System.out.println("llorrrpo" + File.separator);
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
}
