package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class ReturnGoods implements Serializable {

	private static final long serialVersionUID = 3037243717338657432L;
	private String tips;
	private String isShow;
	private List<ReturnGoodsText> texDesc;
	private Gift gift;
	private Freebie freebie;

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public List<ReturnGoodsText> getTexDesc() {
		return texDesc;
	}

	public void setTexDesc(List<ReturnGoodsText> texDesc) {
		this.texDesc = texDesc;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public Gift getGift() {
		return gift;
	}

	public void setGift(Gift gift) {
		this.gift = gift;
	}

	public Freebie getFreebie() {
		return freebie;
	}

	public void setFreebie(Freebie freebie) {
		this.freebie = freebie;
	}

}
