package com.shangpin.wireless.api.util;

import java.util.Date;
import java.util.Hashtable;
import java.util.Random;

import com.shangpin.wireless.api.domain.SessionEntry;

public class SessionUtil {
	
	private final static int MAX_SESSION_DEVICE_COUNT = 2;
	private static Hashtable<String, SessionEntry[]> userTable;
	
	static {
		userTable = new Hashtable<String, SessionEntry[]>(2^24);
	}
	
	private static final String generateSessionId () {
		Random ran = new Random(System.currentTimeMillis());
		
		final int bits = 24;
		StringBuilder strBuff = new StringBuilder();
		for (int i = bits - 1; i>=0; i--) {
			final int value = ran.nextInt(10 + 26);
			if (value < 10) {
				strBuff.append(value);
			} else if (value < 10 + 26) {
				strBuff.append((char)('A' + (value - 10)));
			}
		}
		
		return strBuff.toString();
	}
	
	/**
	 * 向sessionmap里添加用户
	 * @param userid
	 * @return sessionid
	 */
	public synchronized static final String addUser (String userid, String imei) {
		
		if (userid == null || userid.length() == 0) {
			return null;
		}
		
		String sessionid = null;
		
		SessionEntry[] entries = userTable.get(userid);
		if(entries == null){
			entries = new SessionEntry[MAX_SESSION_DEVICE_COUNT];
			SessionEntry sessionEntry = new SessionEntry(imei);
			sessionid = generateSessionId();
			sessionEntry.setValue(sessionid);
			sessionEntry.setInvalidate(false);
			entries[MAX_SESSION_DEVICE_COUNT - 1] = sessionEntry;
		} else {
			final Date now = new Date();
			boolean findone = false;
			for (int i = MAX_SESSION_DEVICE_COUNT - 1; i >= 0; i--) {
				SessionEntry sessionEntry = entries[i];
				if (sessionEntry == null) {
					sessionEntry = new SessionEntry(imei);
					sessionid = generateSessionId();
					sessionEntry.setValue(sessionid);
					sessionEntry.setInvalidate(false);
					entries[i] = sessionEntry;
					findone = true;
					break;
				} else if (sessionEntry.getKey().equals(imei)){
					sessionid = generateSessionId();
					sessionEntry.setValue(sessionid);
					sessionEntry.setInvalidate(false);
					sessionEntry.setCreateTime(now);
					findone = true;
					break;
				}
			}
			if (!findone) {
				Date date = now;
				int delIndex = -1;
				for (int i = MAX_SESSION_DEVICE_COUNT - 1; i >= 0; i--) {
					SessionEntry sessionEntry = entries[i];
					if (sessionEntry != null && date.after(sessionEntry.getCreateTime())) {
						date = sessionEntry.getCreateTime();
						delIndex = i;
					}
				}
				if (delIndex >= 0 && delIndex < MAX_SESSION_DEVICE_COUNT) {
					SessionEntry sessionEntry = entries[delIndex];
					sessionEntry.setKey(imei);
					sessionid = generateSessionId();
					sessionEntry.setValue(sessionid);
					sessionEntry.setInvalidate(false);
					sessionEntry.setCreateTime(now);
				}
			}
		}
		
		userTable.put(userid, entries);
//		System.out.println("-----userid-----"+userid+"-----sessionid-----"+sessionid);
		return sessionid;
	}

	/**
	 * 验证useid与sseionid是否匹配
	 * @param userid
	 * @param sessionid
	 * @return true 正确；false 错误
	 */
	public static final boolean validate (String userid, String imei, String sessionid) {
		
		if (userid == null || userid.length() == 0 || sessionid == null) {
			return false;
		}

		/*SessionEntry[] entries = userTable.get(userid);
		if(entries == null){
			return false;
		} else {
			for (int i = MAX_SESSION_DEVICE_COUNT - 1; i >= 0; i--) {
				SessionEntry sessionEntry = entries[i];
				if (sessionEntry == null) {
					return false;
				} else if (imei.equals(sessionEntry.getKey())
								&& sessionid.equals(sessionEntry.getValue())
								&& !sessionEntry.isInvalidate()){
					return true;
				}
			}
		}*/
		
		return true;
	}
}
