package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

public class AppIndexFirst implements Serializable {

	private static final long serialVersionUID = 1115214539724594508L;
	private String sysTime;
	private List<Entrance> entrance;// 入口
	private List<Gallery> gallery;// 轮播图
	private List<SPActivity> releases;// 最新上架
	private List<StyleTheme> themes;// 风格主题
	private List<Sale> sale;// 今日特卖
	private List<Fashion> fashion;// 尚潮流
	private CommonRules moreFashion;// 更多尚潮流
	private List<CustomBrand> brand;// 可管理品牌
	private List<Product> worth;// 值得买
	private List<Brand> newGoods;// 新品到货
	private List<Operation> operation;// 运营
	private List<Operation> advert;// 推广广告
	private List<Advert> advertNew;// 广告

	public List<Gallery> getGallery() {
		return gallery;
	}

	public void setGallery(List<Gallery> gallery) {
		this.gallery = gallery;
	}

	public List<SPActivity> getReleases() {
		return releases;
	}

	public void setReleases(List<SPActivity> releases) {
		this.releases = releases;
	}

	public List<StyleTheme> getThemes() {
		return themes;
	}

	public void setThemes(List<StyleTheme> themes) {
		this.themes = themes;
	}

	public List<Sale> getSale() {
		return sale;
	}

	public void setSale(List<Sale> sale) {
		this.sale = sale;
	}

	public String getSysTime() {
		return sysTime;
	}

	public void setSysTime(String sysTime) {
		this.sysTime = sysTime;
	}

	public List<Entrance> getEntrance() {
		return entrance;
	}

	public void setEntrance(List<Entrance> entrance) {
		this.entrance = entrance;
	}

	public List<Fashion> getFashion() {
		return fashion;
	}

	public void setFashion(List<Fashion> fashion) {
		this.fashion = fashion;
	}

	public List<CustomBrand> getBrand() {
		return brand;
	}

	public void setBrand(List<CustomBrand> brand) {
		this.brand = brand;
	}

	public CommonRules getMoreFashion() {
		return moreFashion;
	}

	public void setMoreFashion(CommonRules moreFashion) {
		this.moreFashion = moreFashion;
	}

	public List<Brand> getNewGoods() {
		return newGoods;
	}

	public void setNewGoods(List<Brand> newGoods) {
		this.newGoods = newGoods;
	}

	public List<Operation> getOperation() {
		return operation;
	}

	public void setOperation(List<Operation> operation) {
		this.operation = operation;
	}

	public List<Product> getWorth() {
		return worth;
	}

	public void setWorth(List<Product> worth) {
		this.worth = worth;
	}

	public List<Operation> getAdvert() {
		return advert;
	}

	public void setAdvert(List<Operation> advert) {
		this.advert = advert;
	}

	public List<Advert> getAdvertNew() {
		return advertNew;
	}

	public void setAdvertNew(List<Advert> advertNew) {
		this.advertNew = advertNew;
	}

}
