package com.shangpin.api.utils;

import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.net.ftp.FTPClient;

import com.shangpin.biz.utils.DateTimeUtil;
import com.shangpin.utils.Ftp;
import com.shangpin.utils.FtpUtil;
import com.shangpin.utils.MD5Util;

/**
 * ftp上传接口开发
 * 
 * @author zghw
 *
 */
public class FTPUtil {
	private static final String ipAddr = PropertyUtil.getStrValue("ftp.ipAddr");
	private static final Integer port = PropertyUtil.getInt("ftp.port");
	private static final String userName = PropertyUtil.getStrValue("ftp.userName");
	private static final String pwd = PropertyUtil.getStrValue("ftp.pwd");
	private static final String path = PropertyUtil.getStrValue("ftp.path");

	/**
	 * 链接到FTP服务器
	 * 
	 * @param file
	 * @throws Exception
	 * @author zghw
	 */
	public static boolean connectFtp(FTPClient ftp) throws Exception {
		Ftp f = new Ftp();
		f.setIpAddr(ipAddr);
		f.setPort(port);
		f.setUserName(userName);
		f.setPwd(pwd);
		// 每天创建一个文件夹用来分批保存ftp文件夹
		String date = DateTimeUtil.strForDate(new Date());
		f.setPath(path+"/"+date);
		boolean isCon = FtpUtil.connectFtp(f,ftp);
		return isCon;
	}
	/**
	 * 链接到FTP服务器
	 * 
	 * @param file
	 * @throws Exception
	 * @author zghw
	 */
	public static boolean connectFtp() throws Exception {
		Ftp f = new Ftp();
		f.setIpAddr(ipAddr);
		f.setPort(port);
		f.setUserName(userName);
		f.setPwd(pwd);
		// 每天创建一个文件夹用来分批保存ftp文件夹
		String date = DateTimeUtil.strForDate(new Date());
		f.setPath(path+"/"+date);
		boolean isCon = FtpUtil.connectFtp(f);
		return isCon;
	}

	/**
	 * 上传文件名验证
	 * 
	 * @param imei
	 * @param fileName
	 * @return
	 * @author zghw
	 */
	public static boolean isValidFileName(String imei, String ch, String fileName) {
		/**
		 * 加密串.zip 加密串策略： 取得手机IMEI+ch值，进行32位MD5加密，去除第2位和第4位
		 */
		boolean isMatch = Pattern.compile("((\\d|[a-zA-Z]){30}).zip").matcher(fileName).matches();
		if (isMatch) {
			String encrypt = MD5Util.MD5NoUpper(imei + ch);
			String newEncrypt = replaceString(encrypt, "", 1);
			newEncrypt = replaceString(newEncrypt, "", 2);
			String fileEncrypt = fileName.substring(0, 30);
			if (fileEncrypt.equals(newEncrypt)) {
				return true;
			}
		}
		return false;
	}

	public static void main(String args[]) {
		/*String fileName = "4f1996d944dce04cc48802fd0881ef.zip";
		String imei = "fb3407daf3eb1a7c77878128f1cbfd27";
		String ch = "102";
		System.out.println(isValidFileName(imei, ch, fileName));*/
		try {
			FTPUtil.connectFtp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 替换字符串中的某个位置的字符串
	 * 
	 * @param str
	 * @param rstr
	 * @param startIndex
	 * @return
	 * @author zghw
	 */
	public static String replaceString(String str, String rstr, int startIndex) {
		if (startIndex > str.length() - 1) {
			throw new ArrayIndexOutOfBoundsException("越界了");
		}
		String preStr = str.substring(0, startIndex);
		String subStr = str.substring(startIndex + 1);
		return preStr + rstr + subStr;
	}
}
