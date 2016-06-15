package com.shangpin.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shangpin.web.utils.Constants;

/**
 * 旧的URL转向新的URL
 * 
 * @author zghw
 *
 */
@Controller
public class OldToNewController {
	@RequestMapping(value = "/merchandiseaction!splist")
	public String toSuject(String topicid) {
		return "redirect:/subject/product/list?topicId=" + topicid;
	}

	@RequestMapping(value = "/meet!index")
	public String toMeet(String id) {
		return "redirect:/meet/index?id=" + id;
	}

	@RequestMapping(value = "/merchandiseaction!spdetail")
	public String toProductDetail(String productid) {
		return "redirect:/product/detail?productNo=" + productid;
	}

	@RequestMapping(value = "/accountaction!getSpecialCoupon")
	public String toGetCoupon(String couponcode) {
		return "redirect:/coupon/getCoupon?couponCode=" + couponcode;
	}

	@RequestMapping(value = "/categoryproductsaction!getProductList")
	public String toCategoryList(String categoryNO, String categoryName) {
		try {
			categoryName = URLEncoder.encode(categoryName, Constants.DEFAULT_ENCODE);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "redirect:/category/product/list?categoryNo=" + categoryNO + "&categoryName=" + categoryName;
	}
}
