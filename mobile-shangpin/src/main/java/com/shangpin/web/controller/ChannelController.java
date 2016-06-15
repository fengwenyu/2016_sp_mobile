package com.shangpin.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.ChannelConditions;
import com.shangpin.biz.bo.RunVo;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.bo.UserBuyInfo;
import com.shangpin.biz.service.SPBizRunningService;
import com.shangpin.biz.service.SPBizUserService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.web.utils.Constants;
@Controller
@RequestMapping("/channel")
public class ChannelController extends BaseController{
	private static final String CHANNEL_PRODUCT_LIST = "channel/product_list";
	@Autowired
	private SPBizRunningService spBizRunningService;
	@Autowired
	private SPBizUserService bizUserService;
	
	/**
	 * 
	 * @param searchConditions
	 * @param fromFirst 这个参数代表了是否是第一次，不要从url中传值
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/product/list",  method = { RequestMethod.GET, RequestMethod.POST })
	public String brandProduct(ChannelConditions channelConditions,Model model, HttpServletRequest request){
		try {
			User user = getSessionUser(request);
			String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
			String start = (StringUtils.isEmpty(channelConditions.getStart())) ? "1" : channelConditions.getStart();
			String postArea = (StringUtils.isEmpty(channelConditions.getPostArea())) ? "0" : channelConditions.getPostArea();
			String ua = request.getHeader("User-Agent").toLowerCase();
			if (ClientUtil.CheckApp(ua)){
				String appUserid = request.getHeader(Constants.APP_COOKIE_NAME_UID);
				if(!StringUtils.isEmpty(appUserid)&&!appUserid.equals("null")){
					   UserBuyInfo buyInfo = bizUserService.getUserBuyInfoObj(appUserid, Constants.SITE_NO_SP);
					   userLv=buyInfo.getCurlevel();
				}
			}
			//筛选项
			RunVo filterCondition = spBizRunningService.categoryProductListVo(null, null,null,null, null, null,channelConditions.getChannelCategoryNo(),null,  null, null, postArea);
			RunVo channelResult = spBizRunningService.categoryProductListVo(start, Constants.CHANNEL_END_SIZE,userLv,channelConditions.getPrice(), channelConditions.getSize(), channelConditions.getColor(),channelConditions.getChannelCategoryNo(), channelConditions.getCategoryNo(),  channelConditions.getBrandNo(), channelConditions.getOrder(), postArea);
			model.addAttribute("channelConditions", channelConditions);
			model.addAttribute("channelResult", channelResult);
			model.addAttribute("filterCondition", filterCondition);
			model.addAttribute("userLv", userLv);
			model.addAttribute("hasMore", "1");
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return CHANNEL_PRODUCT_LIST;
	}
	
	/**
	 * 
	* @Title: getNewMore 
	* @Description:品牌商品列表加载更多
	* @param @return
	* @return String
	* @throws 
	* @Create By liling
	* @Create Date 2015年3月6日
	 */
	@ResponseBody
	@RequestMapping(value = "/product/list/more",  method = {RequestMethod.GET,RequestMethod.POST})
	public  Map<String, Object> getNewMore(ChannelConditions channelConditions, HttpServletRequest request, String gender, Model model) {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
			
			User user = getSessionUser(request);
			String userLv = StringUtils.isEmpty(user) ? null : user.getLv();
			String ua = request.getHeader("User-Agent").toLowerCase();
			if (ClientUtil.CheckApp(ua)){
				String appUserid = request.getHeader(Constants.APP_COOKIE_NAME_UID);
				if(!StringUtils.isEmpty(appUserid)&&!appUserid.equals("null")){
					   UserBuyInfo buyInfo = bizUserService.getUserBuyInfoObj(appUserid, Constants.SITE_NO_SP);
					   userLv=buyInfo.getCurlevel();
				}
			}
			String start=String.valueOf(Integer.valueOf(channelConditions.getStart())+1);
			String postArea = (StringUtils.isEmpty(channelConditions.getPostArea())) ? "0" : channelConditions.getPostArea();
			RunVo channelResult = spBizRunningService.categoryProductListVo(start, Constants.CHANNEL_END_SIZE,userLv,channelConditions.getPrice(), channelConditions.getSize(), channelConditions.getColor(),channelConditions.getChannelCategoryNo(), channelConditions.getCategoryNo(),  channelConditions.getBrandNo(), channelConditions.getOrder(), postArea);
			map.put("channelResult", channelResult);
			map.put("start", start);
			map.put("userLv", userLv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	/**
	 * 
	* @Title: getSearchConditions 
	* @Description:品牌商品列表筛选条件
	* @param @return
	* @return String
	* @throws 
	* @Create By liling
	* @Create Date 2015年3月6日
	 */
	@ResponseBody
	@RequestMapping(value = "/search/conditions",  method = {RequestMethod.GET,RequestMethod.POST})
	public  Map<String, Object> getSearchConditions(ChannelConditions channelConditions, HttpServletRequest request, String gender, Model model) {
		Map<String, Object> map=new HashMap<String, Object>();
		try {
		
			RunVo channelResult = spBizRunningService.categoryProductListVo(null, null,null, channelConditions.getPrice(), channelConditions.getSize(),channelConditions.getColor(), channelConditions.getChannelCategoryNo(),channelConditions.getCategoryNo(), channelConditions.getBrandNo(),channelConditions.getOrder(),  null);
			map.put("channelResult", channelResult);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
}
