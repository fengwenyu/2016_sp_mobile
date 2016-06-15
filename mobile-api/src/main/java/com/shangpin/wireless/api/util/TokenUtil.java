package com.shangpin.wireless.api.util;

import java.util.Hashtable;

public class TokenUtil {
	
	private static Hashtable<String, User> tokenTable;
	static {
		tokenTable = new Hashtable<String, User>(2^24);
	}
	
	public static final void addUserToken (String imei, String token, String userid, int sex) {
		
		if (imei == null || imei.length() == 0 || token == null) {
			return;
		}
		
		tokenTable.put(imei, new User(imei, token, userid, sex));
	}
	
	static class User {
		String imei;
		String token;
		String userid;
		int sex;
		public User (String imei, String token, String userid, int sex) {
			this.imei = imei;
			this.token = token;
			this.userid = userid;
			this.sex = sex;
		}
	}
}
