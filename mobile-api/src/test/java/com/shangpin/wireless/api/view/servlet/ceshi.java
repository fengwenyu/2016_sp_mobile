package com.shangpin.wireless.api.view.servlet;

import java.util.Set;
import java.util.TreeSet;

public class ceshi {

	public static void main(String[] args) {
	/*	Set productNos =new TreeSet();
		productNos.add("1");
		productNos.add("2");
		productNos.add("3");
		productNos.add("4");
		productNos.add("5");
		for (int i = 0; i < productNos.size(); i++) {
			System.out.println(productNos);
		}*/
		
		/*String number = "123.456";
		System.out.println(number.split(".")[0]);*/
		String number = "123.456";
		String intNumber = number.substring(0,number.indexOf("."));
		System.out.println(intNumber); 
	}
	
}
