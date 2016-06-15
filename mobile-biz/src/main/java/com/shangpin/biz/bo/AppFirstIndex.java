package com.shangpin.biz.bo;

import java.io.Serializable;

public class AppFirstIndex implements Serializable {

	private static final long serialVersionUID = -2065289768211102541L;

	private GalleryList gallery;

	private EntranceTitle entrance;

	private AdvertTitle advert;

	private AdvertNewTitle advertNew;

	private ModelTitle modelOne;

	private ReleasesSPActivity releases;

	private WorthTitle worth;

	private FashionTitle fashion;

	private CommonRules moreFashion;

	private NewGoodsTitle newGoods;

	private AdvertTitle operation;

	public GalleryList getGallery() {
		return gallery;
	}

	public void setGallery(GalleryList gallery) {
		this.gallery = gallery;
	}

	public EntranceTitle getEntrance() {
		return entrance;
	}

	public void setEntrance(EntranceTitle entrance) {
		this.entrance = entrance;
	}

	public AdvertTitle getAdvert() {
		return advert;
	}

	public void setAdvert(AdvertTitle advert) {
		this.advert = advert;
	}

	public ModelTitle getModelOne() {
		return modelOne;
	}

	public void setModelOne(ModelTitle modelOne) {
		this.modelOne = modelOne;
	}

	public AdvertNewTitle getAdvertNew() {
		return advertNew;
	}

	public void setAdvertNew(AdvertNewTitle advertNew) {
		this.advertNew = advertNew;
	}

	public ReleasesSPActivity getReleases() {
		return releases;
	}

	public void setReleases(ReleasesSPActivity releases) {
		this.releases = releases;
	}

	public WorthTitle getWorth() {
		return worth;
	}

	public void setWorth(WorthTitle worth) {
		this.worth = worth;
	}

	public FashionTitle getFashion() {
		return fashion;
	}

	public void setFashion(FashionTitle fashion) {
		this.fashion = fashion;
	}

	public CommonRules getMoreFashion() {
		return moreFashion;
	}

	public void setMoreFashion(CommonRules moreFashion) {
		this.moreFashion = moreFashion;
	}

	public NewGoodsTitle getNewGoods() {
		return newGoods;
	}

	public void setNewGoods(NewGoodsTitle newGoods) {
		this.newGoods = newGoods;
	}

	public AdvertTitle getOperation() {
		return operation;
	}

	public void setOperation(AdvertTitle operation) {
		this.operation = operation;
	}

}
