//package com.shangpin.api.controller;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.shangpin.biz.bo.PointBuyProduct;
//import com.shangpin.biz.bo.PointBuyTimesList;
//import com.shangpin.biz.bo.base.ResultObjOne;
//import com.shangpin.biz.service.ASPBizPointBuyService;
///**
// * 整点抢购功能
// * @author Administrator
// *
// */
//@Controller
//public class PointBuyController extends BaseController {
//	private static final Logger logger = LoggerFactory.getLogger(PointBuyController.class);
//	@Autowired
//	private ASPBizPointBuyService pointBuyService;
//	/**
//	 * 获取挣点抢购的时间轴信息
//	 * @param request
//	 * @return
//	 */
//	@ResponseBody
//	@RequestMapping(value = "/pointBuyPhase", method = { RequestMethod.POST, RequestMethod.GET })
//	public String pointBuyPhase( HttpServletRequest request) {
//		ResultObjOne<PointBuyTimesList> pointBuyPhase = pointBuyService.findTimesList();
//		String result = pointBuyPhase.toJsonNullable();
//		return toResult(result);
//	}
//	
//	@ResponseBody
//	@RequestMapping(value = "/pointBuy", method = { RequestMethod.POST, RequestMethod.GET })
//	public String pointBuyProductList( HttpServletRequest request,String pharseId) {
//		ResultObjOne<PointBuyProduct> pointBuyPhase = pointBuyService.findProductList(pharseId);
//		String result = pointBuyPhase.toJsonNullable();
//		return toResult(result);
//	}
//}