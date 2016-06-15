package com.shangpin.biz.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.biz.bo.AppFirstIndex;
import com.shangpin.biz.bo.CodeMsgEnum;
import com.shangpin.biz.bo.CommonRules;
import com.shangpin.biz.bo.FashionTitle;
import com.shangpin.biz.service.ASPBizIndexInfoService;
import com.shangpin.biz.service.abstraction.AbstractBizIndexInfoService;
import com.shangpin.biz.utils.ApiBizData;
import com.shangpin.biz.utils.StringUtil;

@Service
public class ASPBizIndexInfoServiceImpl extends AbstractBizIndexInfoService implements ASPBizIndexInfoService {
	@Autowired
	private ShangPinService shangPinService;
	
	@Override
	public String getAppFirstIndex(HttpServletRequest request) throws Exception {
		AppFirstIndex appFirstIndex = null;

		final String p = request.getHeader("p");
		final String version = request.getHeader("ver");
		final String userId = request.getHeader("userid");

		if ("102".equals(p) || "2".equals(p)) {
			appFirstIndex = new AppFirstIndex();
			if (com.shangpin.utils.StringUtil.compareVersion("2.9.0", "2.9.4", version) == 0) {
				//CommonRules moreFashion = getFashion();
				appFirstIndex.setGallery(queryGallery("3", "6"));
				appFirstIndex.setAdvert(doBaseAdvertTitle("2", "1", "3"));
				appFirstIndex.setAdvertNew(doBaseAdvertNewTitle());
				appFirstIndex.setEntrance(doBaseEntranceTitle("2", "1", "10"));
				appFirstIndex.setReleases(doBaseReleaseTitle("1", "3", ""));
				appFirstIndex.setWorth(doBaseWorthTitle("2", userId, "1", "6"));
			   //移除潮流趋势
			/*	appFirstIndex.setFashion(doBaseFashionTitle("0", "1", "12"));
				appFirstIndex.setMoreFashion(moreFashion);*/
				appFirstIndex.setFashion(new FashionTitle());
				appFirstIndex.setMoreFashion(new CommonRules());
				appFirstIndex.setNewGoods(doBaseNewGoodsTitle());
				appFirstIndex.setOperation(doBaseAdvertTitle("1", "1", "1"));
			}
			else if (com.shangpin.utils.StringUtil.compareVersion("", "2.9.4", version) == 1) {
				//CommonRules moreFashion = getFashion();
				appFirstIndex.setGallery(queryGallery("3", "6"));
				appFirstIndex.setAdvert(doBaseAdvertTitle("2", "1", "3"));
				appFirstIndex.setModelOne(doBaseModelTitle());
				appFirstIndex.setEntrance(doBaseEntranceTitle("2", "1", "10"));
				appFirstIndex.setReleases(doBaseReleaseTitle("1", "3", ""));
				appFirstIndex.setWorth(doBaseWorthTitle("2", userId, "1", "6"));
				   //移除潮流趋势
			/*	appFirstIndex.setFashion(doBaseFashionTitle("0", "1", "12"));
				appFirstIndex.setMoreFashion(moreFashion);*/
				appFirstIndex.setFashion(new FashionTitle());
				appFirstIndex.setMoreFashion(new CommonRules());
			
				appFirstIndex.setNewGoods(doBaseNewGoodsTitle());
				appFirstIndex.setOperation(doBaseAdvertTitle("1", "1", "1"));
			}
		}

		if (appFirstIndex != null) {

			return ApiBizData.spliceData(appFirstIndex, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
		}

		return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
	}

	@Override
	public String getAppRevealLabel(HttpServletRequest request) throws Exception {
		final String p = request.getHeader("p");
		final String version = request.getHeader("ver");
		final String pageIndex = request.getParameter("pageIndex");
		final String pageSize = request.getParameter("pageSize");
		final String type = "1";

		if ("102".equals(p) || "2".equals(p)) {
			if (StringUtil.compareVer(version,"2.9.3" ) == 1) {
				return getBaseLabel(pageIndex, pageSize, type);
			} else {
				return getBaseLabel(pageIndex, pageSize);
			}
		}

		return ApiBizData.spliceData(CodeMsgEnum.CODE_ERROR.getInfo(), CodeMsgEnum.MSG_ERROR.getInfo());
	}

	@Override
	public String getAppShellWindow(String pageIndex, String pageSize) {
		return doBaseShellWindow(pageIndex, pageSize);
	}
	
	//获取底部导航图片
	@Override
	public String getBottomNavigatePic(String plateForm) {
		//String date = shangPinService.getBottomNavigatePic();
		return shangPinService.getBottomNavigatePic(plateForm);
		/*if(date==null){
			return null;
		}
		
		ResultBaseNew resultBase = ResultBaseNew.formatToPojo(date, BottomNavPojo.class);
		String code = resultBase.getCode();
		if(code==null||!"0".equals(code)){
			return date;
		}
		BottomNavPojo bottomNavPojo = (BottomNavPojo) resultBase.getContent();
		BottomNavPic homeNavPic = (BottomNavPic) bottomNavPojo.getHome();
		BottomNavPic categoryNavPic = (BottomNavPic) bottomNavPojo.getCategory();
		BottomNavPic activityNavPic = (BottomNavPic) bottomNavPojo.getActivity();
		BottomNavPic cartNavPic = (BottomNavPic) bottomNavPojo.getCart();
		BottomNavPic myHomeNavPic = (BottomNavPic) bottomNavPojo.getMyHome();
		//如果五个图片均未传递过来 返回原始数据
		if(homeNavPic == null&&categoryNavPic == null&&activityNavPic == null&&cartNavPic == null&&myHomeNavPic == null){
			return date;
		}
		//将Long型的数据转换成Sting型
		BottomNavPicToApp homeNavPicToApp = convertBottomNavPic(homeNavPic);
		BottomNavPicToApp categoryNavPicToApp = convertBottomNavPic(categoryNavPic);
		BottomNavPicToApp activityNavPicToApp = convertBottomNavPic(activityNavPic);
		BottomNavPicToApp cartNavPicToApp = convertBottomNavPic(cartNavPic);
		BottomNavPicToApp myHomeNavPicToApp = convertBottomNavPic(myHomeNavPic);
		
		BottomNavPojo bottomNavPojoNew = new BottomNavPojo();
		bottomNavPojoNew.setHome(homeNavPicToApp);
		bottomNavPojoNew.setCategory(categoryNavPicToApp);
		bottomNavPojoNew.setActivity(activityNavPicToApp);
		bottomNavPojoNew.setCart(cartNavPicToApp);
		bottomNavPojoNew.setMyHome(myHomeNavPicToApp);
		Map<String,BottomNavPicToApp> resultMap = new HashMap<String, BottomNavPicToApp>();
		resultMap.put("home", homeNavPicToApp);
		resultMap.put("category", categoryNavPicToApp);
		resultMap.put("activity", activityNavPicToApp);
		resultMap.put("cart", cartNavPicToApp);
		resultMap.put("myHome", myHomeNavPicToApp);
		
		ResultBaseNew resultBaseNew = new ResultBaseNew();
		resultBaseNew.setCode(resultBase.getCode());
		resultBaseNew.setMsg(resultBase.getMsg());
		resultBaseNew.setContent(resultMap);
		
		return JsonUtil.toJson(resultBaseNew);*/
	}
	
	/*//转换属性的方法
	private BottomNavPicToApp convertBottomNavPic(BottomNavPic navPic){
		if(navPic == null){
			return null;
		}
		BottomNavPicToApp navPicToApp = new BottomNavPicToApp();
		navPicToApp.setName(navPic.getName());
		navPicToApp.setPic(navPic.getPic());
		navPicToApp.setPicSelect(navPic.getPicSelect());
		navPicToApp.setUrl(navPic.getUrl());
		navPicToApp.setWidth(navPic.getWidth()+"");
		navPicToApp.setHight(navPic.getHight()+"");
		return navPicToApp;
	}*/

}
