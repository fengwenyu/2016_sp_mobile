package com.shangpin.biz.bo;

/**
 * @ClassName: Gallery
 * @Description: 轮播图数据
 * @author 秦迎春
 * @date 2014年10月22日 下午3:31:27
 * @version 1.0
 */
public class Gallery extends CommonRules {

	private static final long serialVersionUID = -4600617452454366979L;

	private String isFlagship;// 是否是旗舰店 0为否，1为是
	private String pic;

	public String getIsFlagship() {
		return isFlagship;
	}

	public void setIsFlagship(String isFlagship) {
		this.isFlagship = isFlagship;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}
