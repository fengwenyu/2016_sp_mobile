package com.shangpin.utils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;


public class CaptchTest {
	
	public Map<String,TestBean> testBean(){
		return null;
	}
	public void testx(){
		
	}
	public TestBean testReturnBean(){
		return null;
	}
	
	public static void main(String[] args) {
		for(int i=0;i<100;i++){
			String path="D:/tmp/captch/"+RandomStringUtils.random(4,"0123456789")+".png";
			String code;
			try {//abcdefghijklmnopqrstuvwxyz
				code = CaptchaCodeUtil.img2Stream(new FileOutputStream(path), "0123456789",82,32);
				System.out.println(code);			
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
