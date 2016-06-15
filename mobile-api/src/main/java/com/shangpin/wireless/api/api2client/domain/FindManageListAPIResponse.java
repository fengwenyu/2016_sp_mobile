package com.shangpin.wireless.api.api2client.domain;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.shangpin.wireless.api.domain.AppPictures;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.domain.FindManage;
import com.shangpin.wireless.api.domain.OS;
import com.shangpin.wireless.api.domain.TypeEnum;
import com.shangpin.wireless.api.service.AppPicturesService;
import com.shangpin.wireless.api.util.ApplicationContextUtil;
import com.shangpin.wireless.api.util.StringUtil;

public class FindManageListAPIResponse{
	
	private String code;
	private String msg;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	private List<FindManage> findManageList;
	private List<FindManage> topFindManageList;
	private FindManage staticActivity;
	//匹配app老版本的数据
	private List<FindManage> oldFindManageList;
	public List<FindManage> getFindManageList() {
		return findManageList;
	}

	public void setFindManageList(List<FindManage> findManageList) {
		this.findManageList = findManageList;
	}
	
	public List<FindManage> getTopFindManageList() {
		return topFindManageList;
	}

	public void setTopFindManageList(List<FindManage> topFindManageList) {
		this.topFindManageList = topFindManageList;
	}
	
	public FindManage getStaticActivity() {
		return staticActivity;
	}

	public void setStaticActivity(FindManage staticActivity) {
		this.staticActivity = staticActivity;
	}

	public List<FindManage> getOldFindManageList() {
		return oldFindManageList;
	}

	public void setOldFindManageList(List<FindManage> oldFindManageList) {
		this.oldFindManageList = oldFindManageList;
	}

	/**
	 * 返给客户端的json数据
	 */
	public JSONObject obj2Json(String verType) {
		JSONObject obj = new JSONObject();
		obj.put("code", getCode());
		obj.put("msg", getMsg() == null ? "" : getMsg());
		JSONObject findManageJson = new JSONObject();
		if (Constants.SUCCESS.equals(getCode())) {
			long now = System.currentTimeMillis();
			findManageJson.put("systime", String.valueOf(now));
			// 轮播
			JSONArray toplist = new JSONArray();
			// 活动图
			JSONArray list = new JSONArray();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			if(verType.equals("1")){
				if (findManageList != null && findManageList.size() > 0) {
						JSONArray group = new JSONArray();
						JSONObject topresultlist = new JSONObject();
						
						if (!group.isEmpty()) {
							topresultlist.put("group", group);
							toplist.add(topresultlist);
						}
						JSONArray group1 = new JSONArray();
						JSONObject resultlist = new JSONObject();
						for (int i = 0; i <findManageList.size(); i++) {
							FindManage findManage = findManageList.get(i);
//							if (findManage.getShowPosition() == j) {
								// 图片
								JSONObject childlist = new JSONObject();
								JSONObject iconObj = new JSONObject();
								if (!TypeEnum.ACTIVITY.name().equals(findManage.getType())) {
									if (StringUtil.isNotEmpty(findManage.getImgUrl())) {
										iconObj.put("url", findManage.getImgUrl());
										iconObj.put("w", findManage.getImgWidth());
										iconObj.put("h", findManage.getImgHeight());
									}
								} else {
									if (StringUtil.isNotEmpty(findManage.getImgUrl())) {
										iconObj.put("url", findManage.getImgUrl());
										iconObj.put("w", findManage.getImgWidth());
										iconObj.put("h", findManage.getImgHeight());
									} else {
										iconObj.put("url", findManage.getIphonePic());
										iconObj.put("w", "692");
										iconObj.put("h", "292");
									}
								}
								childlist.put("icon", iconObj);
								childlist.put("name", findManage.getTitle());
								childlist.put("desc", findManage.getDescription());
								if(StringUtils.isEmpty(findManage.getActivityID())){
									childlist.put("subjectno", "");
								}else{
									childlist.put("subjectno", findManage.getActivityID());
								}
								
								if (TypeEnum.ACTIVITY.name().equals(findManage.getType())) {
									childlist.put("url", findManage.getShareUrl());
									childlist.put("type", "1");
								} else {
									childlist.put("url", findManage.getGetUrl());
									childlist.put("type", "2");
								}
								childlist.put("starttime", sdf.format(findManage.getShowStartDate()));
								childlist.put("endtime", sdf.format(findManage.getShowEndDate()));
								if(findManage.getMobilePreTime()!=null){
									childlist.put("pretime", sdf.format(findManage.getMobilePreTime()));
								}else{
									childlist.put("pretime", "");
								}
								
								group1.add(childlist);
						}
						if (!group1.isEmpty()) {
							resultlist.put("group", group1);
							list.add(resultlist);
						}
				}
			}else{
				if (oldFindManageList != null && oldFindManageList.size() > 0) {
					JSONArray group1 = new JSONArray();
					JSONObject resultlist = new JSONObject();
					for (int i = 0; i < oldFindManageList.size(); i++) {
						FindManage findManage = oldFindManageList.get(i);
						// if (findManage.getShowPosition() == j) {
						// 图片
						JSONObject childlist = new JSONObject();
						JSONObject iconObj = new JSONObject();
						if (!TypeEnum.ACTIVITY.name().equals(findManage.getType())) {
							if (StringUtil.isNotEmpty(findManage.getImgUrl())) {
								iconObj.put("url", findManage.getImgUrl());
								iconObj.put("w", findManage.getImgWidth());
								iconObj.put("h", findManage.getImgHeight());
							}
						} else {
							if (StringUtil.isNotEmpty(findManage.getImgUrl())) {
								iconObj.put("url", findManage.getImgUrl());
								iconObj.put("w", findManage.getImgWidth());
								iconObj.put("h", findManage.getImgHeight());
							} else {
								iconObj.put("url", findManage.getIphonePic());
								iconObj.put("w", "640");
								iconObj.put("h", "584");
							}
						}
						childlist.put("icon", iconObj);
						childlist.put("name", findManage.getTitle());
						childlist.put("desc", findManage.getDescription());
						childlist.put("subjectno", findManage.getActivityID());
						if (!TypeEnum.ACTIVITY.name().equals(findManage.getType())) {
							childlist.put("url", findManage.getShareUrl());
							childlist.put("type", "1");
						} else {
							childlist.put("url", findManage.getGetUrl());
							childlist.put("type", "2");
						}
						childlist.put("starttime", sdf.format(findManage.getShowStartDate()));
						childlist.put("endtime", sdf.format(findManage.getShowEndDate()));
						if (findManage.getMobilePreTime() != null) {
							childlist.put("pretime", sdf.format(findManage.getMobilePreTime()));
						} else {
							childlist.put("pretime", "");
						}
						group1.add(childlist);
					}
					if (!group1.isEmpty()) {
						resultlist.put("group", group1);
						list.add(resultlist);
					}
					
				}
			}
			
			// 分享图
			JSONObject shareObj = new JSONObject();
			AppPictures appPictures = getAppPicture();
			if (appPictures != null) {
				shareObj.put("sharedesc", appPictures.getDescription());
				shareObj.put("shareurl", appPictures.getShareUrl());
				JSONObject picObj = new JSONObject();
				picObj.put("url", appPictures.getImgUrl());
				picObj.put("w", "640");
				picObj.put("h", "920");
				shareObj.put("pic", picObj);
				shareObj.put("sharetitle", appPictures.getShareTitle());
			}
			JSONObject staticatc = new JSONObject();
			if (staticActivity != null) {
				staticatc.put("starttime", sdf.format(staticActivity.getShowStartDate()));
				staticatc.put("endtime", sdf.format(staticActivity.getShowEndDate()));
				staticatc.put("isdisplay", String.valueOf(staticActivity.getDisplay().value));
				staticatc.put("url", staticActivity.getGetUrl());
			}
			findManageJson.put("staticact", staticatc);
			findManageJson.put("surprised", shareObj);
			findManageJson.put("toplist", toplist);
			findManageJson.put("list", list);
		}
		obj.put("content", findManageJson);
		return obj;
	}
	/**
	 * 取得分享图片
	 * @return
	 */
	private AppPictures getAppPicture(){
		ApplicationContext ac = ApplicationContextUtil.get("ac");
		AppPicturesService appPicturesService = (AppPicturesService) ac.getBean("appPicturesService");
		List<AppPictures> list;
		try {
			list = appPicturesService.findOne(OS.IPHONESHARE.toString());
			if(list != null && list.size() > 0){
				return list.get(0);
			}else{
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
