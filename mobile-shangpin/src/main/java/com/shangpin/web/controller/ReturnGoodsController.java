package com.shangpin.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.Orderdetails;
import com.shangpin.biz.bo.ReturnContent;
import com.shangpin.biz.bo.ReturnInfo;
import com.shangpin.biz.bo.ReturnProgress;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.base.ResultBase;
import com.shangpin.biz.bo.base.ResultBaseNew;
import com.shangpin.biz.service.SPBizReturnGoodsService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.utils.StringUtil;
import com.shangpin.web.utils.Constants;


@Controller
@RequestMapping("/returnGoods")
public class ReturnGoodsController extends BaseController {
	/** 跳转到退货详情页面 */
	private static final String ReturnGoods_DETAIL = "return_goods/return_progress";
	/** 跳转到退货说明页面 */
	private static final String ReturnGoods_EXPLAIN = "return_goods/return_explain_app";
	/**填写退货物流信息页面*/
	private static final String LOGISTICS_INFO = "return_goods/logistics_info";
	/**提交退货物流信息页面成功页面*/
	private static final String LOGISTICS_SUCCESS = "return_goods/logistics_success";     
	
	private static final Logger logger = LoggerFactory.getLogger(ReturnGoodsController.class);
	
	@Autowired
	private SPBizReturnGoodsService bizReturnGoodsService;
	
	/**
	 * @Title: returnDetail
	 * @Description: 跳转到退货详情页适用于app
	 * @author By fengwenyu
	 * @Create Date 2015年11月26日
	 */
	@RequestMapping("/returnProgress")
	public String returnDetail(String orderNo, String productNo,
			String orderDetailNo, String skuNo, String returnId,HttpServletRequest request,Model model){
		//app获取用户id
		String userId = request.getHeader("userid");
		String ver = request.getHeader("ver");
		if(StringUtils.isBlank(userId)){
			userId = request.getParameter("userId");
			if(StringUtils.isBlank(userId)){
				return null;
			}
		}
		
		try {
			ResultBaseNew resultBaseNew = bizReturnGoodsService.returnDetailPojo(userId, returnId);
			if(resultBaseNew==null){
				return null;
			}
			
			if(!"0".equals(resultBaseNew.getCode())){
				return null;
			}
			ReturnContent returnContent = (ReturnContent) resultBaseNew.getContent();
			List<Orderdetails> orderDetails = returnContent.getOrderdetails();
			ReturnInfo returnInfo = returnContent.getReturnInfo();
			List<ReturnProgress> returnProgress = returnContent.getReturnProgress();
			//转换时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			for (ReturnProgress returnProgress2 : returnProgress) {
				returnProgress2.setTimestamp(sdf.format(new Date(Long.parseLong(returnProgress2.getTimestamp()))));
			}
			returnInfo.setTimestamp(sdf.format(new Date(Long.parseLong(returnInfo.getTimestamp()))));
			model.addAttribute("returnProgress", returnProgress);
			model.addAttribute("orderdetails", orderDetails);
			model.addAttribute("returnInfo", returnInfo);
			return ReturnGoods_DETAIL;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	/**
	 * @Title: returnDetail
	 * @Description: 跳转到退货详情页适用于m站
	 * @author By fengwenyu
	 * @Create Date 2015年11月26日
	 */
	@RequestMapping("/mReturnProgress")
	public String mReturnDetail(String orderNo, String productNo,
			String orderDetailNo, String skuNo, String returnId,HttpServletRequest request,Model model){
		User user = getSessionUser(request);
		String userId = user != null ? user.getUserid() : "";
		if(StringUtils.isBlank(userId)){
			return null;
		}
		try {
			ResultBaseNew resultBaseNew = bizReturnGoodsService.returnDetailPojo(userId, returnId);
			if(resultBaseNew==null){
				return null;
			}
			
			if(!"0".equals(resultBaseNew.getCode())){
				return null;
			}
			ReturnContent returnContent = (ReturnContent) resultBaseNew.getContent();
			List<Orderdetails> orderDetails = returnContent.getOrderdetails();
			ReturnInfo returnInfo = returnContent.getReturnInfo();
			List<ReturnProgress> returnProgress = returnContent.getReturnProgress();
			
			model.addAttribute("returnProgress", returnProgress);
			model.addAttribute("orderdetails", orderDetails);
			model.addAttribute("returnInfo", returnInfo);
			return ReturnGoods_DETAIL;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @Title: @return
	 * @Description: 
	 * @author By fengwenyu
	 * @Create Date 2015年11月27日
	 */
	@RequestMapping("/returnExplain")
	public String returnExplain(){
		return ReturnGoods_EXPLAIN;
	}
	
	/**
	 * 填写退货物流信息
	 * @author qinyingchun
	 * @param orderDetailNo
	 * @param productNo
	 * @param skuNo
	 * @param returnId
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logistics/info", method = {RequestMethod.GET, RequestMethod.POST})
	public String logisticsInfo(String userId, String orderNo, String orderDetailNo, String productNo, String skuNo, String returnId, Model model, HttpServletRequest request){
		String userid;
		String useragent = request.getHeader("User-Agent").toLowerCase();
		if(ClientUtil.CheckIOS(useragent)){//IOS
			userid = request.getHeader(Constants.APP_COOKIE_NAME_UID);
		}else{
			if(org.springframework.util.StringUtils.isEmpty(userId)){
				userid = getUserId(request);//m站
			}else {
				userid = userId;//Android
			}
			
		}
		logger.info("logistics info userId================{}", userid);
		request.getSession().setAttribute(Constants.SESSION_USERID, userid);
		ReturnContent returnContent = bizReturnGoodsService.returnDetailObj(userid, returnId);
		if(null == returnContent){
			return LOGISTICS_INFO;
		}
		List<ReturnProgress> returnProgresses = returnContent.getReturnProgress();
		for(ReturnProgress progress : returnProgresses){
			String timeStamp = progress.getTimestamp();
			progress.setTimestamp(this.convert(timeStamp));
		}
		ReturnInfo returnInfo = returnContent.getReturnInfo();
		String time = returnInfo.getTimestamp();
		returnInfo.setTimestamp(this.convert(time));
		model.addAttribute("returnContent", returnContent);
		return LOGISTICS_INFO;
	}
	
	/**
	 * 提交物流信息
	 * @author qinyingchun
	 * @param applyNo
	 * @param logisticsNo
	 * @param logisticsCompany
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/logistics/submit", method = {RequestMethod.GET, RequestMethod.POST})
	@ResponseBody
	public ResultBase logisticsSubmit(String applyNo, String logisticsNo, String logisticsCompany, HttpServletRequest request){
		String userId = (String)request.getSession().getAttribute(Constants.SESSION_USERID);
		ResultBase resultBase = bizReturnGoodsService.logisticsSubmitObj(userId, applyNo, logisticsNo, logisticsCompany);
		return resultBase;
	}
	
	/**
	 * 物流信息提交成功页
	 * @return
	 */
	@RequestMapping(value = "/logistics/success", method = RequestMethod.GET)
	public String logisticsResult(){
		return LOGISTICS_SUCCESS;
	}
	
	private String convert(String timeStamp){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(new Date(Long.parseLong(timeStamp)));
	}
	
	public static void main(String[] args) {
		ReturnGoodsController controller = new ReturnGoodsController();
		System.out.println(controller.convert("1452510124000"));
	}
		
}
