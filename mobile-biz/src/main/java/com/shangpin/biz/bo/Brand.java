package com.shangpin.biz.bo;

import java.util.List;

/**
 * @ClassName: Brand
 * @Description:品牌实体类
 * @author qinyingchun
 * @date 2014年10月23日
 * @version 1.0
 */
public class Brand extends CommonRules {

	private static final long serialVersionUID = 3709863332959739759L;
	private String id;
	private String nameEN;
	private String nameCN;
	private String desc;
	private String isFlagship;
	private String pic;
	private String about;// 品牌故事
	private String isShown;// 是否显示品牌信息 0不显示 1显示
	private String alias;// 店类型名称
	private String title;// 店展示语
	private String count;// 改品牌新品数量
	private String aboutUrl;//品牌故事地址
	private String allNum;//全部商品数量
	private String newestNum;//上新数量
	private String collections;//收藏数量

	private List<Product> productList;// 改品牌新品数量

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNameEN() {
		return nameEN;
	}

	public void setNameEN(String nameEN) {
		this.nameEN = nameEN;
	}

	public String getNameCN() {
		return nameCN;
	}

	public void setNameCN(String nameCN) {
		this.nameCN = nameCN;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

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

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getIsShown() {
		return isShown;
	}

	public void setIsShown(String isShown) {
		this.isShown = isShown;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}

    public String getAboutUrl() {
        return aboutUrl;
    }

    public void setAboutUrl(String aboutUrl) {
        this.aboutUrl = aboutUrl;
    }

    public String getAllNum() {
        return allNum;
    }

    public void setAllNum(String allNum) {
        this.allNum = allNum;
    }



    public String getNewestNum() {
        return newestNum;
    }

    public void setNewestNum(String newestNum) {
        this.newestNum = newestNum;
    }

    public String getCollections() {
        return collections;
    }

    public void setCollections(String collections) {
        this.collections = collections;
    }
	

}

