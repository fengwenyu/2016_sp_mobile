package com.shangpin.biz.bo;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName: ProductDetail
 * @Description:商品详情实体类（支持sku不同价格体系）
 * @author wangfeng
 * @date 2015年3月11日
 * @version 1.0
 */
public class ProductDetail implements Serializable {

	private static final long serialVersionUID = 2346494790958332439L;
	private String postArea;// 是否海外商品 1 国内 2海外
	private String promoLogo;// 打标
	private ProductBasic basic;
	private Share share;// 分型对象
	private ProductOverseasInfo overseasInfo;// 海外商品信息
	private String type;
	private List<Tag> tag;// 图片的标签信息
	private Notice notice;// 客户须知
	private ProductComment productComment;// 商品详情页上的评论
	private List<ProductDetailPromotion> promotion;// 促销信息
	private List<RefContent> promotionNew;//促销信息，现在走通用规则
	private String guaranteeImg;// 保障图片
	private String tariff;// 包税
	private String isShowCart;//是否显示购物车0不显示1显示
	public String getTariff() {
		return tariff;
	}

	public void setTariff(String tariff) {
		this.tariff = tariff;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPostArea() {
		return postArea;
	}

	public void setPostArea(String postArea) {
		this.postArea = postArea;
	}

	public ProductBasic getBasic() {
		return basic;
	}

	public void setBasic(ProductBasic basic) {
		this.basic = basic;
	}

	public Share getShare() {
		return share;
	}

	public void setShare(Share share) {
		this.share = share;
	}

	public ProductOverseasInfo getOverseasInfo() {
		return overseasInfo;
	}

	public void setOverseasInfo(ProductOverseasInfo overseasInfo) {
		this.overseasInfo = overseasInfo;
	}

	public List<Tag> getTag() {
		return tag;
	}

	public void setTag(List<Tag> tag) {
		this.tag = tag;
	}

	public Notice getNotice() {
		return notice;
	}

	public void setNotice(Notice notice) {
		this.notice = notice;
	}

	public ProductComment getProductComment() {
		return productComment;
	}

	public void setProductComment(ProductComment productComment) {
		this.productComment = productComment;
	}

	public String getGuaranteeImg() {
		return guaranteeImg;
	}

	public void setGuaranteeImg(String guaranteeImg) {
		this.guaranteeImg = guaranteeImg;
	}

	public List<ProductDetailPromotion> getPromotion() {
		return promotion;
	}

	public void setPromotion(List<ProductDetailPromotion> promotion) {
		this.promotion = promotion;
	}

	public String getPromoLogo() {
		return promoLogo;
	}

	public void setPromoLogo(String promoLogo) {
		this.promoLogo = promoLogo;
	}

    public String getIsShowCart() {
        return isShowCart;
    }

    public void setIsShowCart(String isShowCart) {
        this.isShowCart = isShowCart;
    }

	public List<RefContent> getPromotionNew() {
		return promotionNew;
	}

	public void setPromotionNew(List<RefContent> promotionNew) {
		this.promotionNew = promotionNew;
	}
}
