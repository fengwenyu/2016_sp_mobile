package com.aolai.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 旧的URL转向新的URL
 * 
 * @author zghw
 *
 */
@Controller
public class OldToNewController {

	/**
	 * 转向新的活动呢列表
	 * 
	 * @param activityId
	 * @param typeFlag
	 * @param pageType
	 * @return
	 * @author zghw
	 */
	@RequestMapping("/merchandiseaction!list")
	public String toActivityList(String activityId,@RequestParam(value = "typeFlag", defaultValue = "1", required = false) String typeFlag,@RequestParam(value = "pageType", defaultValue = "1", required = false) String pageType) {
		return "redirect:/activity/lv2?activityId=" + activityId + "&typeFlag=" + typeFlag + "&pageType=" + pageType;
	}

	/**
	 * 转向新的购物车
	 * 
	 * @author zghw
	 */
	@RequestMapping(value = "/allcartaction!listCart")
	public String toCardList( HttpServletRequest request,String pich, String picw, String isPromotion, String shopType) {
		return "redirect:/cart/list?pich="+pich+"&picw="+picw+"&isPromotion="+isPromotion+"&shopType="+shopType;
	}

	/**
	 * 转向下载新的地址
	 */
	@RequestMapping(value = "/aolaiindex!toAppStore")
	public String toDownLoad(String store, String p) {
		return "redirect:/appDownload?store=" + store + "&p=" + p;
	}

	/**
	 * 转向详情页App
	 */
	@RequestMapping(value = "/merchandiseaction!aolaiDetail")
	public String toProductDetailApp(String goodsid, String categoryno,@RequestParam(value = "type", defaultValue = "1", required = false) String type) {
		return "redirect:/product/detailApp?goodsId=" + goodsid + "&categoryNo=" + categoryno + "&typeFlag=" + type;
	}

	/**
	 * 转向详情页
	 */
	@RequestMapping(value = "/merchandiseaction!detail")
	public String toProductDetail2(String goodsid, String categoryno, String pageType,@RequestParam(value = "typeFlag", defaultValue = "1", required = false) String typeFlag, String activityId) {
		return "redirect:/activity/detail?goodsId=" + goodsid + "&categoryNo=" + categoryno + "&typeFlag=" + typeFlag + "&pageType=" + pageType + "&activityId=" + activityId;
	}
	
	/**
	 * 尺码对照表
	 * @param cate
	 * @return
	 * @author zghw
	 */
	@RequestMapping(value="/merchandiseaction!sizedesc")
	public String toSizeDesc(String cate){
		return "redirect:/activity/sizespec?cate="+cate;
	}
}

