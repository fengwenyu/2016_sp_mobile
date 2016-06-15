package com.shangpin.wireless.api.api2client.domain;

/**
 * 收货方式， 鉴于历史遗留问题，直接写死
 * 1 工作日收货
 * 2 所有日期均可收货
 * 3 双休日、节假日收货
 * @author xupengcheng
 *
 */
public enum Express {

	WORK("1", "只工作日送货(双休日、假日不用送)"), ALL("2", "工作日、双休日与假日均可送货"),HOLIDAY("3", "只双休日、假日送货(工作日不用送)");
	public String id, name;
	
	Express(String id, String name){
		this.id = id;
		this.name = name;
	}
	 
	public static String getId(String name){
		String id = "";
		if (name.indexOf("只工作日送货(双休日、假日不用送)") >= 0) {//只工作日送货(双休日、假日不用送)
			id = Express.WORK.id;
		} else if (name.equals("只双休日、假日送货(工作日不用送)")) {//只双休日、假日送货(工作日不用送)
			id = Express.HOLIDAY.id;
		} else if (name.equals("工作日、双休日与假日均可送货")) {//工作日、双休日与假日均可送货
			id = Express.ALL.id;
		}
		return id;
	}
}
