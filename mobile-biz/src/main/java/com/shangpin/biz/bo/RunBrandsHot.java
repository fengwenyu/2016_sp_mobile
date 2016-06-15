package com.shangpin.biz.bo;

import java.io.Serializable;

/**
 * 跑步馆品牌热卖数据
 * @author Twl
 *
 */
public class RunBrandsHot extends CommonRules implements Serializable {
	
	private static final long serialVersionUID=1L;
	
	private String pic;

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}
	
	

}
