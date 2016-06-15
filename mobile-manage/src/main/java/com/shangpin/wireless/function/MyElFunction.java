package com.shangpin.wireless.function;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyElFunction {
	public static String getDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
}
