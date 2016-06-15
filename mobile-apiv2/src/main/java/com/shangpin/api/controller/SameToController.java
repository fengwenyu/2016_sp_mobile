package com.shangpin.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.biz.bo.ReceiveRequest;
import com.shangpin.biz.bo.ReturnReceive;
import com.shangpin.biz.bo.base.ContentBuilder;
import com.shangpin.biz.service.SPBizReceiveService;
import com.shangpin.core.entity.Receive;
import com.shangpin.utils.JsonUtil;

/**
 *  撞衫节返处理返回订单
 *
 * @author 李永桥
 *
 */
@Controller
@RequestMapping("/receives")
public class SameToController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(SameToController.class);
	@Autowired
	private SPBizReceiveService sPBizReceiveService;

	
	/**
	 *  根据订单号查询订单是否有撞衫节赠送订单 没有撞衫订单  0 ，有返回撞衫订单号  data value=orderId 
	 * isNeworder:<br/>
	 * (TODO 描述这个方法的作用). <br/>
	 * @param orderId
	 * @return
	 */
	
	@ResponseBody
	@RequestMapping(value = "/isorder", method = { RequestMethod.POST, RequestMethod.GET })
	public String isNeworder(HttpServletRequest request,String orderId) {
		List <Receive> entiy=sPBizReceiveService.isNeworder(orderId,orderId);
		List<ReturnReceive> list=new ArrayList<ReturnReceive>();
		ContentBuilder builder = new ContentBuilder();
		if(CollectionUtils.isEmpty(entiy)){
			ReturnReceive  is=new ReturnReceive();
			is.setlOrderId("");//不是
			is.setfOrderId("");
			list.add(is);
		}else{
			for (Receive receentiy:entiy) {
				ReturnReceive  is=new ReturnReceive();
				if(!StringUtils.isEmpty(receentiy.getLorderId())){
					is.setlOrderId(receentiy.getLorderId());//领取分享订单号
					is.setfOrderId(receentiy.getForderId());//发起分享单号
					list.add(is);
				}
		}}
		
		
		return JsonUtil.toJson(builder.buildDefSuccess("成功", list));
	}
	/**
	 * 根据撞衫订单号查询明细
	 * isLorder:<br/>
	 * (TODO 描述这个方法的作用). <br/>
	 * @param lorderId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/isorderdetail", method = { RequestMethod.POST, RequestMethod.GET })
	public String isLorder(HttpServletRequest request,String lorderId) {
		List<ReturnReceive> list =new ArrayList<ReturnReceive>();
		List <Receive> receive1=sPBizReceiveService.isorderdetail(lorderId);
		ContentBuilder builder = new ContentBuilder();
		if(!CollectionUtils.isEmpty(receive1)){
			for (Receive receive:receive1) {
			ReturnReceive entity =new ReturnReceive();
			logger.debug("根据撞衫订单号查询普通订单号为"+receive.getForderId());
			entity.setfOrderId(receive.getForderId()==null?"":receive.getForderId());
			entity.setlOrderId(receive.getLorderId()==null?"":receive.getLorderId());
			entity.setlSkuNO(receive.getSkuId()==null?"":receive.getSkuId());
			entity.setfSkuNo(receive.getFspuId()==null?"":receive.getFspuId());//发起订单skuNo
			list.add(entity);
			}
		}
		return JsonUtil.toJson(builder.buildDefSuccess("成功", list));
	}
	
}
