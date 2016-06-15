package com.shangpin.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shangpin.biz.bo.Fashion;
import com.shangpin.biz.bo.FashionDetail;
import com.shangpin.biz.bo.SiteType;
import com.shangpin.biz.service.SPBizFashionService;
import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.core.entity.WxFashionInformation;
import com.shangpin.core.service.IFashionService;
import com.shangpin.web.utils.Constants;

/**
 * @ClassName: FashionController
 * @Description: 潮流资讯信息
 * @author liling
 * @date 2014年12月26日
 * @version 1.0
 */
@Controller
@RequestMapping("/fashion")
public class FashionController extends BaseController {

	private static final String INDEX = "/fashion/index";
	private static final String DETAIL = "/fashion/detail";
	private static final String LIST = "/fashion/list";
	private static final String INFO = "/fashion/info";;;
	@Autowired
	IFashionService fashionService;
	@Autowired
	SPBizFashionService spBizFashionService;
	/**
	 * 
	 * @Title: index
	 * @Description: 潮流资讯信息列表首页
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年12月26日
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		List<WxFashionInformation> fashionList = fashionService.findFashionList(new Date());
		model.addAttribute("fashionList", fashionList);
		return INDEX;
	}
	/**
	 * 
	 * @Title: index
	 * @Description: 尚潮流列表首页
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年3月12日
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model,String type) {
		List<Fashion> fashionList = spBizFashionService.findFashionList(type,Constants.FASHION_START,Constants.FASHION_END);
		if(fashionList!=null){
			for(int i=0;i<fashionList.size();i++){
				Fashion fashion=fashionList.get(i);
				if(!StringUtils.isEmpty(fashion.getPubTime())){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					fashion.setPubTime(sdf.format(new Date(Long.valueOf(fashion.getPubTime()))));
				}
			
			}
		}
	
		model.addAttribute("fashionList", fashionList);
		model.addAttribute("type", type);
		return LIST;
	}
	/**
	 * 
	 * @Title: detail
	 * @Description: 潮流资讯详细信息
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2014年12月26日
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String detail(String id, Model model) {
		WxFashionInformation fashion = fashionService.findByFashionId(Long.valueOf(id));
		model.addAttribute("fashion", fashion);
		return DETAIL;
	}
	/**
	 * 
	 * @Title: info
	 * @Description: 潮流资讯详细信息
	 * @param
	 * @return String
	 * @throws
	 * @Create By liling
	 * @Create Date 2015年3月12日
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info(String id, Model model,HttpServletRequest request) {
		String ua = request.getHeader("User-Agent").toLowerCase();
		String origin = request.getHeader("origin");
        if(origin!=null){
            origin=origin.toLowerCase();
        }
		SiteType siteType = null;
		if (ClientUtil.CheckIosAgent(ua)) {
			siteType = SiteType.IOS_SHANGPIN;
		} else if (ClientUtil.CheckOrigin(origin) || ClientUtil.CheckAndriodAgent(ua)) {
			siteType = SiteType.ANDRIOD_SHANGPIN;
		} else {
			siteType = SiteType.M_SHANGPIN;
		}
		FashionDetail fashionDetail = spBizFashionService.findFashionDetail(id,siteType,true);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(!StringUtils.isEmpty(fashionDetail.getPubTime())){
			fashionDetail.setPubTime(sdf.format(new Date(Long.valueOf(fashionDetail.getPubTime()))));
		}
		model.addAttribute("fashionDetail", fashionDetail);
		return INFO;
	}
}
