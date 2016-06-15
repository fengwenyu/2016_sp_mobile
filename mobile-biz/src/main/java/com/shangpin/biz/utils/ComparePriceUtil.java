package com.shangpin.biz.utils;

import org.springframework.util.StringUtils;


/**
 * 价格逻辑处理
 * @author qinyingchun
 *
 */
public class ComparePriceUtil {
	
	/**
	 * 根据用户级别及促销状态获取显示价格
	 * @param strs 传入促销状态、用户级别及各种价格
	 * 0 "status":"促销状态"               
	   1 "lv":"用户级别"
	   2 "marketPrice": "1899市场价",
	   3 "limitedPrice": "1899普通会员价",
	   4 "goldPrice": "1899黄金会员价",
	   5 "platinumPrice": "1899白金会员价",
	   6 "diamondPrice": "1899钻石会员价",
	   7 "promotionPrice": "699促销价",
	        会员级别
	    0001	普通会员
		0002	黄金会员
		0003	白金会员
		0004	钻石会员

	 * @return
	 */
	public static String[] compare(String... strs){
		String[] arrs = new String[2];
		String status = strs[0];
		String lv = strs[1];
		if("4".equals(status)){//4表示是促销状态
			arrs[0] = strs[7];
			arrs[1] = strs[2];
			return arrs;
		}
		if(StringUtils.isEmpty(lv) || lv.equals("0001")){
			arrs[0] = strs[3];
			arrs[1] = strs[2];
			return arrs;
		}else if (lv.equals("0002")) {//黄金会员
			arrs[0] = strs[4];
			arrs[1] = strs[2];
			return arrs;
		}else if (lv.equals("0003")) {//白金会员
			arrs[0] = strs[5];
			arrs[1] = strs[2];
			return arrs;
		}else if (lv.equals("0004")) {//钻石会员
			arrs[0] = strs[6];
			arrs[1] = strs[2];
			return arrs;
		}
		return null;
	}

}
