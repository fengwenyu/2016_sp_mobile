package com.shangpin.wireless.view.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.shangpin.wireless.base.action.BaseAction;

import com.shangpin.wireless.domain.Constants;
import com.shangpin.wireless.domain.DisplayEnum;
import com.shangpin.wireless.domain.FindManage;
import com.shangpin.wireless.domain.IsSliderEnum;
import com.shangpin.wireless.domain.ReturnObject;
import com.shangpin.wireless.domain.TypeEnum;
import com.shangpin.wireless.util.DateUtil;
import com.shangpin.wireless.util.JsonUtil;
import com.shangpin.wireless.util.SqlHelper;
import com.shangpin.wireless.util.WebUtil;
import com.shangpin.wireless.vo.FindManageVO;

@Controller
@Scope("prototype")
public class FindManageAction extends BaseAction<FindManage> {
	private static final long serialVersionUID = 3211260495857737040L;
	
	private String endDateStr;
	private String startDateStr;
	
	
	private String keyWord;
	private String typeStr;
	
	private File imgUrlFile;
	private String imgUrlFileContentType;
	private String imgUrlFileFileName;
	
	private File sliderImgUrlFile;
	private String sliderImgUrlFileFileName;
	private int page = 1;
	private int rows;

	public String input() {
	
		return "input";
	}

	public String add() {
		return "add";
	}

	public String save() {
		ReturnObject returnObject = new ReturnObject();
		try {
			SqlHelper sqlQueryCountHelper = new SqlHelper("findManage");
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sqlQueryCountHelper.addColumn("count(*)");
			sqlQueryCountHelper.addWhereCondition("activityID=" + "'" + model.getActivityID() + "'");
			sqlQueryCountHelper.addWhereCondition("showEndDate>=" + "'" + sdf.format(new Date()) + "'");
			sqlQueryCountHelper.addWhereCondition("type=" + "'" + TypeEnum.ACTIVITY + "'");
		
			Integer count = findManageService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
			if (count > 0) {
				returnObject.setReturnCode("fail");
				returnObject.setReturnInfo("活动已经配置，无需重复配置！");
				ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
				return null;
			}
			TypeEnum typeEnum	=TypeEnum.getValue(typeStr);
			model.setType(typeEnum);
			model.setIsSlider(IsSliderEnum.NO);
			model.setDisplay(DisplayEnum.NO);
			model.setSort("1");
			
			Date date=sdf.parse(DateUtil.getAfterAmountDay(model.getShowStartDate(), 7, "yyyy-MM-dd HH:mm:ss"));
			Date now=new Date();
			if(date.before(now)){
				returnObject.setReturnCode("fail");
				returnObject.setReturnInfo("活动开始时间至现在时间间隔大于七天，配置无效！");
				ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
				return null;
			}
			if(model.getShowEndDate()!=null){
				if(marginDate(model.getShowStartDate(),model.getShowEndDate())>=7){
					model.setShowEndDate(date);
				}
			}else{
				model.setShowEndDate(date);
			}
			findManageService.save(model);
			String minSort=findManageService.findMinSort(model.getId());
			if(StringUtils.isNotEmpty(minSort)){
				int mincount=Integer.valueOf(minSort.split("[.]")[0]);
				if(mincount<=1){
					findManageService.sortRetrude(model.getId());
				}
			}
			returnObject.setReturnCode("success");
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("FindManageAction-save() " + e);
		}
		return null;
	}
	public String editeImgText() {
		ReturnObject returnObject = new ReturnObject();
		try {
			if (model.getId() == null) {
				if(StringUtils.isEmpty(model.getImgUrl())){
					ActionContext.getContext().getValueStack().set("checkImgFile", "图片不能为空");
					return "creatNewImgText";
				}
			
				model.setType(TypeEnum.IMAGETEXT);
				if (imgUrlFile != null) {
					String filename = getImgUrlFileFileName();
					String extension = WebUtil.getExt(filename);
					
					if (!extension.equals("jpg")) {
						ActionContext.getContext().getValueStack().set("checkImgFile", "封面图必须是jpg格式");
						return "creatNewImgText";
					}
					
					model.setImgUrl(uploadPic(imgUrlFile, filename, extension));
				}
				model.setIsSlider(IsSliderEnum.NO);
				model.setDisplay(DisplayEnum.NO);
				model.setSort("1");
				findManageService.save(model);
				String minSort=findManageService.findMinSort(model.getId());
				if(StringUtils.isNotEmpty(minSort)){
					int mincount=Integer.valueOf(minSort.split("[.]")[0]);
					if(mincount<=1){
						findManageService.sortRetrude(model.getId());
					}
				}
				return "input";
			} else {
				FindManage findManage = findManageService.findById(model.getId());
				findManage.setShowStartDate(model.getShowStartDate());
				findManage.setShowEndDate(model.getShowEndDate());
				findManage.setGetUrl(model.getGetUrl());
				findManage.setTitle(model.getTitle());
				findManage.setDescription(model.getDescription());
				findManage.setImgUrl(model.getImgUrl());
				findManage.setImgHeight(model.getImgHeight());
				findManage.setImgWidth(model.getImgWidth());
				findManage.setMobilePreTime(model.getMobilePreTime());
				if (imgUrlFile != null) {
					String filename = getImgUrlFileFileName();
					String extension = WebUtil.getExt(filename);
					findManage.setImgUrl(uploadPic(imgUrlFile, filename, extension));
				}
				findManageService.update(findManage);
				returnObject.setReturnCode("success");
				ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
			}
			
		} catch (Exception e) {
			log.error("FindManageAction-editeImgText() " + e);
		}
		return null;
	}
	
	public String editeStaticAtc() {
		ReturnObject returnObject = new ReturnObject();
		try {
			if (model.getId() == null) {
				if(model.getDisplay()==DisplayEnum.YES){
					Map<String, Object> map = new HashMap<String, Object>();
					map=findManageService.isExist(model.getShowStartDate(),model.getShowEndDate());
					if(map.get("isExist").equals("true")){
						ActionContext.getContext().getValueStack().set("checkPrompt", "当前时间段内已存在开启的静态页");
						return "creatNewStaticAtc";
						
					}
				}
				if(StringUtils.isEmpty(model.getGetUrl())){
					ActionContext.getContext().getValueStack().set("checkGetUrl", "跳转链接不能为空");
					return "creatNewStaticAtc";
				}
				if(model.getShowStartDate()==null){
					ActionContext.getContext().getValueStack().set("checkShowStartDate", "开始时间不能为空");
					return "creatNewStaticAtc";
				}
				if(model.getShowEndDate()==null){
					ActionContext.getContext().getValueStack().set("checkShowEndDate", "结束时间不能为空");
					return "creatNewStaticAtc";
				}
				if(StringUtils.isEmpty(model.getImgHeight())){
					ActionContext.getContext().getValueStack().set("checkImgHeight", "图片高度不能为空");
					return "creatNewStaticAtc";
				}
				if(StringUtils.isEmpty(model.getImgWidth())){
					ActionContext.getContext().getValueStack().set("checkImgWidth", "图片宽度不能为空");
					return "creatNewStaticAtc";
				}
				if(StringUtils.isEmpty(model.getTitle())){
					ActionContext.getContext().getValueStack().set("checkTitle", "标题不能为空");
					return "creatNewStaticAtc";
				}
				if(StringUtils.isEmpty(model.getImgUrl())){
					ActionContext.getContext().getValueStack().set("checkImgUrl", "图片不能为空");
					return "creatNewStaticAtc";
				}
				if (imgUrlFile != null) {
					String filename = getImgUrlFileFileName();
					String extension = WebUtil.getExt(filename);
					
					if (!extension.equals("jpg")) {
						ActionContext.getContext().getValueStack().set("checkImgUrl", "请上传jpg格式的图片");
						return "creatNewStaticAtc";
					}
					
					model.setImgUrl(uploadPic(imgUrlFile, filename, extension));
				}
				model.setType(TypeEnum.STATICATC);
				model.setIsSlider(IsSliderEnum.NO);
				model.setDisplay(model.getDisplay());
				model.setSort("0");
				findManageService.save(model);
				ServletActionContext.getResponse().getWriter().write("success");
			} else {
				
				if(model.getShowStartDate()==null){
					returnObject.setReturnCode("fail");
					returnObject.setReturnInfo("开始时间不能为空");
					return null;
				}
				if(model.getShowEndDate()==null){
					returnObject.setReturnCode("fail");
					returnObject.setReturnInfo("结束时间不能为空");
					return null;
				}
				if(StringUtils.isEmpty(model.getImgHeight())){
					returnObject.setReturnCode("fail");
					returnObject.setReturnInfo("图片高度不能为空");
					return null;
				}
				if(StringUtils.isEmpty(model.getImgWidth())){
					returnObject.setReturnCode("fail");
					returnObject.setReturnInfo("图片宽度不能为空");
					return null;
				}
				if(StringUtils.isEmpty(model.getTitle())){
					returnObject.setReturnCode("fail");
					returnObject.setReturnInfo("标题不能为空");
					return null;
				}
				
				if (imgUrlFile != null) {
					String filename = getImgUrlFileFileName();
					String extension = WebUtil.getExt(filename);
					
					if (!extension.equals("jpg")) {
						returnObject.setReturnCode("fail");
						returnObject.setReturnInfo("请上传jpg格式的图片");
						return null;
					}
					
					model.setImgUrl(uploadPic(imgUrlFile, filename, extension));
				}
				model.setType(TypeEnum.STATICATC);
				findManageService.update(model);
				returnObject.setReturnCode("success");
				ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
				return null;
			}
			
		} catch (Exception e) {
			log.error("FindManageAction-editeStaticAtc() " + e);
		}
		return "input";
	}

	public String datagridList() throws IOException {
		List<FindManageVO> list = new ArrayList<FindManageVO>();
		list = findManageService.subjectList(startDateStr, endDateStr, keyWord);
		ReturnObject returnObject = new ReturnObject();

		returnObject.setRows(list);
		ServletActionContext.getResponse().getWriter().print(JsonUtil.getJsonString4JavaPOJO(returnObject,"yyyy-MM-dd HH:mm:ss"));
		return null;
	}
	public String updateActivity()  {
		ReturnObject returnObject = new ReturnObject();
		FindManage findManage = new FindManage();
		try {
			findManage = findManageService.findById(model.getId());
			if(findManage.getType()==TypeEnum.ACTIVITY){
				findManage.setActivityID(model.getActivityID());
				findManage.setTitle(model.getTitle());
				findManage.setSubTitle(model.getSubTitle());
				findManage.setDescription(model.getDescription());
				findManage.setImgUrl(model.getImgUrl());
				findManage.setImgHeight(model.getImgHeight());
				findManage.setImgWidth(model.getImgWidth());
			}
			findManageService.update(findManage);
			returnObject.setReturnCode("success");
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("FindManageAction-updateActivity() " + e);
//			e.printStackTrace();
		}
		return null;
	}

	public String info() {
		FindManage findManage = new FindManage();
		FindManageVO findManageVO = new FindManageVO();
		try {
			findManage = findManageService.findById(model.getId());
			if(findManage.getType()==TypeEnum.ACTIVITY){
				ServletActionContext.getResponse().getWriter().print(JsonUtil.getJsonString4JavaPOJO(findManageVO.doToUpVo(findManage),"yyyy-MM-dd HH:mm:ss"));
			}else{
				ServletActionContext.getResponse().getWriter().print(JsonUtil.getJsonString4JavaPOJO(findManage,"yyyy-MM-dd HH:mm:ss"));
			}
		} catch (Exception e) {
			log.error("findManageService-info() " + e);
			e.printStackTrace();
		}
		return null;
	}
	
	public String delete() {
		try {
			
			findManageService.delete(model.getId());
		
			findManageService.retrudeSortDel(model.getSort());
			
			ServletActionContext.getResponse().getWriter().write("success");
		} catch (Exception e) {
			log.error("findManageService-delete() " + e);
			e.printStackTrace();
		}
		return null;
	}
	
	public String uploadPic(File file, String filename, String extension) {
		String imgurl = null;
		String json = null;
		json = WebUtil.uploadPic(file, filename, extension);
		JSONObject jsonObj = JSONObject.fromObject(json);
		if (null != jsonObj) {
			if (Constants.CODE_SUCCESS.equals(jsonObj.getString("code"))) {
				if (null != jsonObj.get("content") && !"{}".equals(jsonObj.get("content").toString())) {
					JSONObject contentObj = (JSONObject) jsonObj.get("content");
					imgurl = contentObj.get("PicUrl").toString();
				}
			}
		}
		return imgurl;
	}
	
	/**
	 * 数据动态绑定-推送消息列表
	 * 
	 * @Author liling
	 * @createDate 2014-03-27
	 * @param
	 * @Return
	 */
	public String query() {
		try {
			SqlHelper sqlQueryListHelper = new SqlHelper("findManage", "t");
			SqlHelper sqlQueryCountHelper = new SqlHelper("findManage");
			if (typeStr.equals("static")) {
				sqlQueryListHelper.addColumn("t.showStartDate");
				sqlQueryListHelper.addColumn("t.showEndDate");
				sqlQueryListHelper.addColumn("t.getUrl");
				sqlQueryListHelper.addColumn("t.id");
				sqlQueryListHelper.addColumn("t.display");
				sqlQueryListHelper.addColumn("t.imgUrl");
				sqlQueryListHelper.addColumn("t.imgWidth");
				sqlQueryListHelper.addColumn("t.imgHeight");
				sqlQueryListHelper.addColumn("t.description");
				sqlQueryListHelper.addColumn("t.title");
				sqlQueryCountHelper.addColumn("count(*)");
				final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sqlQueryListHelper.addWhereCondition("t.showEndDate>=" + "'" + sdf.format(new Date()) + "'");
				sqlQueryListHelper.addWhereCondition("t.type="+ "'"  + TypeEnum.STATICATC+ "'");
//				sqlQueryListHelper.addWhereCondition("t.display=" + DisplayEnum.YES.value);
				sqlQueryCountHelper.addWhereCondition("showEndDate>=" + "'" + sdf.format(new Date()) + "'");
				sqlQueryCountHelper.addWhereCondition("type="+ "'"  + TypeEnum.STATICATC+ "'");
//				sqlQueryCountHelper.addWhereCondition("display=" + DisplayEnum.YES.value);
				// sqlQueryListHelper.addOrderByProperty(true, "t.isTop", false);
				sqlQueryListHelper.addOrderByProperty(true, "t.showStartDate", false);
				List<HashMap> queryList = findManageService.executeSqlToMap(sqlQueryListHelper.getQueryListSql(), page, rows);
				List<HashMap> recordList = new ArrayList<HashMap>(queryList.size());
				for (int i = 0; i < queryList.size(); i++) {
					HashMap record = new HashMap();
					HashMap log = queryList.get(i);
					FindManageVO findManageVO = new FindManageVO();
					if (log.get("id") != null) {
						record.put("id", log.get("id"));
					}
					if (log.get("showStartDate") != null && log.get("showEndDate") != null) {
						record.put("showDateDesc", new StringBuilder().append(sdf.format(log.get("showStartDate"))).append("<br/>").append("至<br/>").append(sdf.format(log.get("showEndDate"))).append("<br/>").toString());
					}
					if (log.get("getUrl") != null) {
						record.put("getUrl", log.get("getUrl"));
					}
					if (log.get("display") != null) {
						if (log.get("display").toString().equals("1")) {
							record.put("display", "是");
							
						} else {
							record.put("display", "否");
						}
					}
					if (log.get("imgUrl") != null) {
						record.put("imgUrl", log.get("imgUrl").toString().replace("{w}", "160").replace("{h}", "80"));
					}
					if (log.get("title") != null) {
						record.put("title", log.get("title"));
					}
					if (log.get("description") != null) {
						record.put("desc", log.get("description"));
					}
					// record.put("isTop", log.get("isTop"));
					recordList.add(record);
				}
				
				
				Integer count = findManageService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
				ReturnObject returnObject = new ReturnObject();
				returnObject.setTotal(count);
				returnObject.setRows(recordList);
				ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
			} else {
				sqlQueryListHelper.addColumn("t.activityID");
				sqlQueryListHelper.addColumn("t.startDate");
				sqlQueryListHelper.addColumn("t.endDate");
				sqlQueryListHelper.addColumn("t.showStartDate");
				sqlQueryListHelper.addColumn("t.showEndDate");
				sqlQueryListHelper.addColumn("t.mobilePic");
				sqlQueryListHelper.addColumn("t.iphonePic");
				sqlQueryListHelper.addColumn("t.shareUrl");
				sqlQueryListHelper.addColumn("t.preTime");
				sqlQueryListHelper.addColumn("t.status");
				sqlQueryListHelper.addColumn("t.description");
				sqlQueryListHelper.addColumn("t.subTitle");
				sqlQueryListHelper.addColumn("t.imgUrl");
				sqlQueryListHelper.addColumn("t.imgWidth");
				sqlQueryListHelper.addColumn("t.imgHeight");
				sqlQueryListHelper.addColumn("t.type");
				sqlQueryListHelper.addColumn("t.title");
				sqlQueryListHelper.addColumn("t.getUrl");
				sqlQueryListHelper.addColumn("t.id");
				sqlQueryListHelper.addColumn("t.isSlider");
				sqlQueryListHelper.addColumn("t.sliderImgUrl");
				sqlQueryListHelper.addColumn("t.mobilePreTime");
				sqlQueryListHelper.addColumn("t.sort");
				sqlQueryCountHelper.addColumn("count(*)");
				final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				sqlQueryListHelper.addWhereCondition("t.showEndDate>=" + "'" + sdf.format(new Date()) + "'");
				sqlQueryCountHelper.addWhereCondition("showEndDate>=" + "'" + sdf.format(new Date()) + "'");
				sqlQueryListHelper.addWhereCondition("t.type!="+ "'"  + TypeEnum.STATICATC+ "'");
				sqlQueryCountHelper.addWhereCondition("type!="+ "'" +TypeEnum.STATICATC+ "'" );
				sqlQueryListHelper.addOrderByProperty(true, "ABS(t.sort)", true);
				sqlQueryListHelper.addOrderByProperty(true, "t.showStartDate", false);
				List<HashMap> queryList = findManageService.executeSqlToMap(sqlQueryListHelper.getQueryListSql(), page, rows);
				Integer count = findManageService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
				List<HashMap> recordList = new ArrayList<HashMap>(queryList.size());
				for (int i = 0; i < queryList.size(); i++) {
					HashMap record = new HashMap();
					HashMap log = queryList.get(i);
					FindManageVO findManageVO = new FindManageVO();
					if (log.get("id") != null) {
						record.put("id", log.get("id"));
					}
					if (log.get("activityID") != null) {
						record.put("activityID", log.get("activityID"));
					}
					if (log.get("title") != null) {
						record.put("name", log.get("title"));
					}
					if (log.get("endDate") != null) {
						record.put("endDate", sdf.format(log.get("endDate")));
					}
					if (log.get("showEndDate") != null) {
						record.put("showEndDate", log.get("showEndDate"));
					}
					if (log.get("showStartDate") != null) {
						record.put("showStartDate", log.get("showStartDate"));
					}
					if (log.get("showStartDate") != null && log.get("showEndDate") != null) {
						record.put("showDateDesc", new StringBuilder().append(sdf.format(log.get("showStartDate"))).append("<br/>").append("至<br/>").append(sdf.format(log.get("showEndDate"))).append("<br/>").toString());
					}
					if (log.get("endDate") != null && log.get("startDate") != null) {
						record.put("activityDateDesc", new StringBuilder().append(sdf.format(log.get("startDate"))).append("<br/>").append("至<br/>").append(sdf.format(log.get("endDate"))).append("<br/>").toString());
					}
					if (log.get("startDate") != null) {
						record.put("starttime", log.get("startDate"));
					}
					if (log.get("preTime") != null) {
						record.put("preTime", sdf.format(log.get("preTime")));
					}
					if (log.get("type") != null) {
						if (log.get("type").equals("IMAGETEXT")) {
							if (log.get("imgUrl") != null) {
								record.put("imgUrl", log.get("imgUrl").toString().replace("{w}", "160").replace("{h}", "80"));
							}
							record.put("type", "图文");
						} else {
							record.put("type", "活动");
							if (log.get("imgUrl") != null) {
								record.put("imgUrl", log.get("imgUrl").toString().replace("{w}", "160").replace("{h}", "80"));
							} else {
								if (log.get("iphonePic") != null) {
									record.put("imgUrl", log.get("iphonePic").toString().replace("{w}", "160").replace("{h}", "80"));
								}
							}
						}
					}
					if (log.get("status") != null) {
						if (log.get("status").equals("1")) {
							record.put("status", "开启");
						} else {
							record.put("status", "关闭");
						}
					}
					if (log.get("subTitle") != null) {
						record.put("subTitle", log.get("subTitle"));
					}
					if (log.get("description") != null) {
						record.put("desc", log.get("description"));
					}
					
					if (log.get("isSlider") != null) {
						if (log.get("isSlider").toString().equals("1")) {
							if (log.get("sliderImgUrl") != null) {
								record.put("isSlider", log.get("sliderImgUrl").toString().replace("{w}", "160").replace("{h}", "100"));
							}
						} else {
							record.put("isSlider", "否");
						}
					}
					if (log.get("sort") != null ) {
						record.put("sort",log.get("sort"));
					}
					if (log.get("mobilePreTime") != null) {
						record.put("mobilePreTime", log.get("mobilePreTime"));
					}
					// record.put("isTop", log.get("isTop"));
					recordList.add(record);
				}
			
				ReturnObject returnObject = new ReturnObject();
				returnObject.setTotal(count);
				returnObject.setRows(recordList);
				ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error("findManageAction-datagridList() " + e);
		}
		return null;
	}

	public String setAppSliderImg() {
		if (model.getId() != null) {
			// findManageService.updateTopPostion();
			try {
				ReturnObject returnObject = new ReturnObject();
				// model.setType(TypeEnum.IMAGETEXT);
				if (sliderImgUrlFile != null) {
					String filename = getSliderImgUrlFileFileName();
					String extension = WebUtil.getExt(filename);
					if (!extension.equals("jpg")) {
						returnObject.setReturnCode("fail");
						returnObject.setReturnInfo("请上传jpg格式的图片");
						ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
						return null;
					}
					BufferedImage bufferedImage = ImageIO.read(sliderImgUrlFile);
					int width = bufferedImage.getWidth();
					if (width != 640 ) {
						returnObject.setReturnCode("fail");
						returnObject.setReturnInfo("轮播图宽度必须为640");
						ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
						return null;
					}
					
					FindManage findManage = findManageService.findById(model.getId());
					
					findManage.setSliderImgUrl(uploadPic(sliderImgUrlFile, filename, extension));
					findManage.setIsSlider(IsSliderEnum.YES);
					findManageService.update(findManage);
					returnObject.setReturnCode("success");
					ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
				} else {
					returnObject.setReturnCode("fail");
					returnObject.setReturnInfo("请上传轮播图片");
					ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
				}
				// FindManage findManage = findManageService.findById(model.getId());
				// IsSliderEnum isSlider=IsSliderEnum.YES;
				// findManage.setIsSlider(isSlider);
				// findManageService.update(findManage);
				// ServletActionContext.getResponse().getWriter().write("success");
			} catch (Exception e) {
				log.error("findManageAction-setAppSliderImg() " + e);
				e.printStackTrace();
			}
		}
		return null;
	}
	public String cancelAppSliderImg(){
		if(model.getId()!=null){
			try {
				FindManage findManage = findManageService.findById(model.getId());
				findManage.setIsSlider(IsSliderEnum.NO);
				findManage.setSliderImgUrl(null);
				findManageService.update(findManage);
				ServletActionContext.getResponse().getWriter().write("success");
			} catch (Exception e) {
				log.error("findManageAction-setTop() " + e);
			}
		}
		return null;
		
	}
	
	public String setDisplay() {
		if (model.getId() != null) {
			try {
				ReturnObject returnObject = new ReturnObject();
				FindManage findManage = findManageService.findById(model.getId());
			
				Map<String, Object> map = new HashMap<String, Object>();
				map=findManageService.isExist(findManage.getShowStartDate(),findManage.getShowEndDate(),model.getId());
				if(map.get("isExist").equals("true")){
					returnObject.setReturnCode("fail");
					returnObject.setReturnInfo("已存在静态页,请先关闭或取消原有的静态页设置！");
					ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
					return null;
					
				}
				findManage.setDisplay(DisplayEnum.YES);
				findManageService.update(findManage);
				returnObject.setReturnCode("success");
				ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
			} catch (Exception e) {
				log.error("findManageAction-setAppSliderImg() " + e);
				e.printStackTrace();
			}
		}
		return null;
	}
	public String setNotDisplay(){
		if(model.getId()!=null){
			try {
				ReturnObject returnObject = new ReturnObject();
				FindManage findManage = findManageService.findById(model.getId());
				findManage.setDisplay(DisplayEnum.NO);
				findManageService.update(findManage);
				returnObject.setReturnCode("success");
				ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
			} catch (Exception e) {
				log.error("findManageAction-setTop() " + e);
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	public String setTop(){
		if(model.getId()!=null){
			try {
				ReturnObject returnObject = new ReturnObject();
				FindManage findManage = findManageService.findById(model.getId());
				findManageService.updateTopPostion();
				findManage.setSort("0");
				findManageService.update(findManage);
				returnObject.setReturnCode("success");
				ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
			} catch (Exception e) {
				log.error("findManageAction-setTop() " + e);
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	public String cancelTop(){
		if(model.getId()!=null){
			try {
				ReturnObject returnObject = new ReturnObject();
				FindManage findManage = findManageService.findById(model.getId());
				findManage.setSort("1");
				findManageService.update(findManage);
				returnObject.setReturnCode("success");
				ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
			} catch (Exception e) {
				log.error("findManageAction-setTop() " + e);
				e.printStackTrace();
			}
		}
		return null;
		
	}
	
	public Integer marginDate(Date startDate, Date endDate) {
		long shengyu = endDate.getTime() - startDate.getTime();
		int marginDate = (int) (shengyu / (1000 * 60 * 60 * 24));
		return marginDate;
	}
	
	public String setSort() {
		ReturnObject returnObject = new ReturnObject();
		FindManage findManage = new FindManage();
		try {
//			SqlHelper sqlQueryCountHelper = new SqlHelper("findManage");
//			sqlQueryCountHelper.addColumn("count(*)");
//			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			sqlQueryCountHelper.addWhereCondition("showEndDate>=" + "'" + sdf.format(new Date()) + "'");
//			sqlQueryCountHelper.addWhereCondition("type!=" + "'" + TypeEnum.STATICATC + "'");
//			sqlQueryCountHelper.addWhereCondition("sort=" + "'" + model.getSort()+ "'");
//			sqlQueryCountHelper.addWhereCondition("id!=" + "'" + model.getId()+ "'");
//			Integer count = findManageService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
//			if(count>0){
//				returnObject.setReturnCode("fail");
//				returnObject.setReturnInfo("此排序位置已存在！");
//				ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
//				return null;
//			}
			String sort = findManageService.findMaxSort(model.getId());
			findManage = findManageService.findById(model.getId());
			if (StringUtils.isNotEmpty(sort)) {
				if (Integer.valueOf(sort.split("[.]")[0]) < Integer.valueOf(model.getSort())) {
					returnObject.setReturnCode("fail");
					returnObject.setReturnInfo("当前可设置的最大位置为"+Integer.valueOf(sort.split("[.]")[0]));
					ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
					return null;
				}
			}
			String con = findManageService.findBySort(model.getSort());
			if (Integer.valueOf(findManage.getSort()) > Integer.valueOf(model.getSort())) {
				if (StringUtils.isNotEmpty(con)) {
					if (Integer.valueOf(con.split("[.]")[0]) > 0) {
						findManageService.retrudeByEndSort(findManage.getSort(),model.getSort());
					}
				}
			} else {
				if (StringUtils.isNotEmpty(con)) {
					if (Integer.valueOf(con.split("[.]")[0]) > 0) {
						findManageService.retrudeByStartSort(findManage.getSort(),model.getSort());
					}
				}
			}
			findManage.setSort(model.getSort());
			findManageService.update(findManage);
			returnObject.setReturnCode("success");
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("findManageAction-setSort() " + e);
			e.printStackTrace();
		}
		return null;
	}
	public String creatNewActivity() {
		return "creatNewActivity";
	}

	public String creatNewImgText() {
		return "creatNewImgText";
	}
	public String creatNewStaticAtc() {
		return "creatNewStaticAtc";
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}
	

	
	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getTypeStr() {
		return typeStr;
	}

	public void setTypeStr(String typeStr) {
		this.typeStr = typeStr;
	}

	public File getImgUrlFile() {
		return imgUrlFile;
	}

	public void setImgUrlFile(File imgUrlFile) {
		this.imgUrlFile = imgUrlFile;
	}

	public String getImgUrlFileContentType() {
		return imgUrlFileContentType;
	}

	public void setImgUrlFileContentType(String imgUrlFileContentType) {
		this.imgUrlFileContentType = imgUrlFileContentType;
	}

	public String getImgUrlFileFileName() {
		return imgUrlFileFileName;
	}

	public void setImgUrlFileFileName(String imgUrlFileFileName) {
		this.imgUrlFileFileName = imgUrlFileFileName;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public File getSliderImgUrlFile() {
		return sliderImgUrlFile;
	}

	public void setSliderImgUrlFile(File sliderImgUrlFile) {
		this.sliderImgUrlFile = sliderImgUrlFile;
	}

	public String getSliderImgUrlFileFileName() {
		return sliderImgUrlFileFileName;
	}

	public void setSliderImgUrlFileFileName(String sliderImgUrlFileFileName) {
		this.sliderImgUrlFileFileName = sliderImgUrlFileFileName;
	}

	

	
}



