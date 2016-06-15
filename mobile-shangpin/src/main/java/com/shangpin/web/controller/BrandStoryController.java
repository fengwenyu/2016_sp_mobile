package com.shangpin.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shangpin.biz.bo.BrandActivityHead;
import com.shangpin.biz.bo.HeadInfo;
import com.shangpin.biz.bo.User;
import com.shangpin.biz.service.SPBizBrandActivityService;

@Controller
@RequestMapping(value = "/brandStory")
public class BrandStoryController extends BaseController{
	
	@Autowired
	private SPBizBrandActivityService bizBrandActivityService;
	
	private static final String BRAND_STORY_INDEX = "story/index";
	private static final String BRAND_STORY_TOPSHOP = "story/topshop";
	//Add 2015/9/10
    private static final String BRAND_STORY_TOPMANSHOP = "story/index_story";
    private static final String BRAND_STORY_RAIDERS = "story/raiders";
    
    private static final String BRAND_STORY_TOPSHOP_NEW = "story/topshop_new";
    private static final String BRAND_STORY_TOPMANSHOP_NEW = "story/index_story_new";
    private static final String BRAND_STORY_PLICE_NEW = "redirect:http://m.shangpin.com/meet/index";
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(String id, String type, Model model, HttpServletRequest request){
		User user = getSessionUser(request);
		String userId = StringUtils.isEmpty(user) ? null : user.getUserid();
		if("B1885".equals(id)){
			return BRAND_STORY_TOPSHOP_NEW;
		}else if ("B02850".equals(id)){
            return BRAND_STORY_TOPMANSHOP_NEW;
        }else if("B0052".equals(id)){//PLICE品牌館跳轉到會場頁面
			model.addAttribute("id","01245");
			model.addAttribute("type","1");
			return BRAND_STORY_PLICE_NEW;
		}
		BrandActivityHead brandActivityHead = bizBrandActivityService.headInfo(userId, id, type);
		if(null == brandActivityHead || null == brandActivityHead.getHead()){
			return BRAND_STORY_INDEX;
		}
		HeadInfo headInfo = brandActivityHead.getHead();
		model.addAttribute("headInfo", headInfo);
		return BRAND_STORY_INDEX;
	}
	
	/***
	 * 牛仔攻略客户
	 * @param id
	 * @param type
	 * @param model
	 * @param request
	 * @return page
	 */
   @RequestMapping(value = "/raider", method = RequestMethod.GET)
    public String index1(String id, String type, Model model, HttpServletRequest request){
         
        return BRAND_STORY_RAIDERS;
    }

}
