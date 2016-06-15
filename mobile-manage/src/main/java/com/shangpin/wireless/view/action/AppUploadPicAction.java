package com.shangpin.wireless.view.action;

import java.awt.image.BufferedImage;
import java.io.File;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.shangpin.wireless.base.action.BaseAction;
import com.shangpin.wireless.domain.AppPictures;
import com.shangpin.wireless.domain.Constants;
import com.shangpin.wireless.domain.ProductTypeEnum;
import com.shangpin.wireless.domain.ReturnObject;
import com.shangpin.wireless.util.JsonUtil;
import com.shangpin.wireless.util.SqlHelper;
import com.shangpin.wireless.util.StringUtils;
import com.shangpin.wireless.util.WebUtil;

@Controller
@Scope("prototype")
public class AppUploadPicAction extends BaseAction<AppPictures> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 967626457925960573L;
	protected final Log log = LogFactory.getLog(AccountStatisticsAction.class);
	private int page = 1;
	private int rows;
	private String editFlag;
	private String flag;
	private File imgFile; // 文件
	private String imgFileContentType; // 文件类型
	private String imgFileFileName; // 文件名
	// private AppPictures appPictures;
	private JSONObject entityJson;
	private String shopType;
	public String input() {
		return "input";
	}
	public String aolai() {
		return "aolai";
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String query() {
		try {
			// 1，构建查询条件
			SqlHelper sqlQueryListHelper = new SqlHelper("appPictures", "t");
			SqlHelper sqlQueryCountHelper = new SqlHelper("appPictures");
			sqlQueryListHelper.addColumn("t.id");
			sqlQueryListHelper.addColumn("t.imgUrl");
			sqlQueryListHelper.addColumn("t.osType");
			sqlQueryListHelper.addColumn("t.imgWidth");
			sqlQueryListHelper.addColumn("t.imgHeight");
			sqlQueryListHelper.addColumn("t.shareUrl");
			sqlQueryListHelper.addColumn("t.description");
			sqlQueryListHelper.addColumn("t.createDate");
			sqlQueryCountHelper.addColumn("count(*)");
			if (StringUtils.isNotEmpty(model.getOsType())&&!model.getOsType().equals("-1")) {
				sqlQueryListHelper.addWhereCondition("t.osType ='" + model.getOsType() + "'");
				sqlQueryCountHelper.addWhereCondition("osType ='" + model.getOsType() + "'");
			}
			sqlQueryListHelper.addWhereCondition("t.productType !='" + ProductTypeEnum.AOLAI.value + "'");
			sqlQueryCountHelper.addWhereCondition("productType !='" + ProductTypeEnum.AOLAI.value + "'");
			sqlQueryListHelper.addOrderByProperty(true, "t.createDate", false);
			List<HashMap> queryList = appPicturesService.executeSqlToMap(sqlQueryListHelper.getQueryListSql(), page, rows);
			List<HashMap> recordList = new ArrayList<HashMap>(queryList.size());
			for (int i = 0; i < queryList.size(); i++) {
				HashMap record = new HashMap();
				HashMap log = queryList.get(i);
				if (log.get("imgUrl") != null) {
					record.put("imgUrl", log.get("imgUrl").toString().replace("{w}", "180").replace("{h}", "110"));
				}
				if (log.get("imgWidth") != null) {
					record.put("imgWidth", log.get("imgWidth"));
				}
				if (log.get("imgHeight") != null) {
					record.put("imgHeight", log.get("imgHeight"));
				}
				if (log.get("shareUrl") != null) {
					record.put("shareUrl", log.get("shareUrl"));
				}
				if (log.get("description") != null) {
					record.put("description", log.get("description"));
				}
				record.put("id", log.get("id"));
				if (log.get("osType") != null) {
					if(log.get("osType").equals("iphone4Start")){
						record.put("osType", "iphone4启动图");
					}
					if(log.get("osType").equals("iphone5Start")){
						record.put("osType", "iphone5,6,6Plus启动图");
					}
					if(log.get("osType").equals("androidStart")){
						record.put("osType", "安卓启动图");
					}
					if(log.get("osType").equals("ipadStart")){
						record.put("osType", "ipad启动图");
					}
					/*if(log.get("osType").equals("iphoneFindShare")){
						record.put("osType", "iphone发现分享图");
					}
					if(log.get("osType").equals("androidFindShare")){
						record.put("osType", "安卓发现分享图");
					}*/
				}
				if (log.get("createDate") != null) {
					record.put("createDate", log.get("createDate"));
				}
				recordList.add(record);
			}
			Integer count = appPicturesService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
			ReturnObject returnObject = new ReturnObject();
			returnObject.setTotal(count);
			returnObject.setRows(recordList);
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("AppUploadPicAction-query() " + e);
		}
		return null;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String aolaiQuery() {
		try {
			// 1，构建查询条件
			SqlHelper sqlQueryListHelper = new SqlHelper("appPictures", "t");
			SqlHelper sqlQueryCountHelper = new SqlHelper("appPictures");
			sqlQueryListHelper.addColumn("t.id");
			sqlQueryListHelper.addColumn("t.imgUrl");
			sqlQueryListHelper.addColumn("t.osType");
			sqlQueryListHelper.addColumn("t.imgWidth");
			sqlQueryListHelper.addColumn("t.imgHeight");
			sqlQueryListHelper.addColumn("t.shareUrl");
			sqlQueryListHelper.addColumn("t.description");
			sqlQueryListHelper.addColumn("t.createDate");
			sqlQueryCountHelper.addColumn("count(*)");
			if (StringUtils.isNotEmpty(model.getOsType())&&!model.getOsType().equals("-1")) {
				sqlQueryListHelper.addWhereCondition("t.osType ='" + model.getOsType() + "'");
				sqlQueryCountHelper.addWhereCondition("osType ='" + model.getOsType() + "'");
			}
			sqlQueryListHelper.addWhereCondition("t.productType ='" + ProductTypeEnum.AOLAI.value + "'");
			sqlQueryCountHelper.addWhereCondition("productType ='" + ProductTypeEnum.AOLAI.value + "'");
			sqlQueryListHelper.addOrderByProperty(true, "t.createDate", false);
			List<HashMap> queryList = appPicturesService.executeSqlToMap(sqlQueryListHelper.getQueryListSql(), page, rows);
			List<HashMap> recordList = new ArrayList<HashMap>(queryList.size());
			for (int i = 0; i < queryList.size(); i++) {
				HashMap record = new HashMap();
				HashMap log = queryList.get(i);
				if (log.get("imgUrl") != null) {
					record.put("imgUrl", log.get("imgUrl").toString().replace("{w}", "180").replace("{h}", "110"));
				}
				if (log.get("imgWidth") != null) {
					record.put("imgWidth", log.get("imgWidth"));
				}
				if (log.get("imgHeight") != null) {
					record.put("imgHeight", log.get("imgHeight"));
				}
				if (log.get("shareUrl") != null) {
					record.put("shareUrl", log.get("shareUrl"));
				}
				if (log.get("description") != null) {
					record.put("description", log.get("description"));
				}
				record.put("id", log.get("id"));
				if (log.get("osType") != null) {
					if(log.get("osType").equals("iphone4Start")){
						record.put("osType", "iphone4启动图");
					}
					if(log.get("osType").equals("iphone5Start")){
						record.put("osType", "iphone5,6,6Plus启动图");
					}
					if(log.get("osType").equals("androidStart")){
						record.put("osType", "安卓启动图");
					}
					if(log.get("osType").equals("ipadStart")){
						record.put("osType", "ipad启动图");
					}
					/*if(log.get("osType").equals("iphoneFindShare")){
						record.put("osType", "iphone发现分享图");
					}
					if(log.get("osType").equals("androidFindShare")){
						record.put("osType", "安卓发现分享图");
					}*/
				}
				if (log.get("createDate") != null) {
					record.put("createDate", log.get("createDate"));
				}
				recordList.add(record);
			}
			Integer count = appPicturesService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
			ReturnObject returnObject = new ReturnObject();
			returnObject.setTotal(count);
			returnObject.setRows(recordList);
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("AppUploadPicAction-query() " + e);
		}
		return null;
	}
	
	public String editUI() {
	
	return "edit";
	}
	public String aolaiEditUI() {
		
		return "aolaiEdit";
	}
	public String info() {
		AppPictures appPictures = new AppPictures();
		try {
			appPictures = appPicturesService.findById(model.getId());
			JSONObject jsonObj = JSONObject.fromObject(appPictures);
			HashMap record = new HashMap();
			if (appPictures.getId()!=null ) {
				record.put("id", appPictures.getId());
			}
			if (StringUtils.isNotEmpty(appPictures.getDescription())) {
				record.put("description", appPictures.getDescription());
			}
			if (StringUtils.isNotEmpty(appPictures.getShareUrl())) {
				record.put("shareUrl", appPictures.getShareUrl());
			}
			if (StringUtils.isNotEmpty(appPictures.getOsType())) {
				record.put("osType", appPictures.getOsType());
			}
			if(StringUtils.isNotEmpty(appPictures.getImgUrl())){
				record.put("imgUrl",appPictures.getImgUrl().replace("{w}", "160").replace("{h}", "110"));
			}
			if (StringUtils.isNotEmpty(appPictures.getShareTitle())) {
				record.put("shareTitle", appPictures.getShareTitle());
			}
			
			ActionContext.getContext().getValueStack().set("prompt", "如未重新上传图片。则默认使用原图");
			ActionContext.getContext().getValueStack().push(record);
		} catch (Exception e) {
			log.error("appPicturesService-info() " + e);
			e.printStackTrace();
		}
		if(StringUtils.isNotEmpty(shopType)){
			if(shopType.equals("0")){
				return "aolaiEdit";
			}
		}
		
		return "edit";
	}

	public String delete() {
		try {
			appPicturesService.delete(model.getId());
			ServletActionContext.getResponse().getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String edit() {
		try {
			if(model.getId()==null){
				File file = getImgFile();
				int width;
				int height;
				String extension;
				if(StringUtils.isEmpty(model.getOsType())||model.getOsType().equals("-1")){
					ActionContext.getContext().getValueStack().set("checkType", "图类型不能为空");
					ActionContext.getContext().getValueStack().set("imgUrl", "");
					return "edit";
				}else{
					if(model.getOsType().indexOf("Share")>-1){
						if(StringUtils.isEmpty(model.getDescription())){
							ActionContext.getContext().getValueStack().set("checkDesc", "文字描述不能为空");
							ActionContext.getContext().getValueStack().set("imgUrl", "");
							return "edit";
						}
						if(StringUtils.isEmpty(model.getShareUrl())){
							ActionContext.getContext().getValueStack().set("checkShare", "分享不能为空");
							ActionContext.getContext().getValueStack().set("imgUrl", "");
							return "edit";
						}
						if(StringUtils.isEmpty(model.getShareTitle())){
							ActionContext.getContext().getValueStack().set("checkTitle", "title不能为空");
							ActionContext.getContext().getValueStack().set("imgUrl", "");
							return "edit";
						}
					}
				}
				
				if (file == null) {
					ActionContext.getContext().getValueStack().set("checkImgFile", "请选择一张图片");
					return "edit";
				
				} else {
					 extension = WebUtil.getExt(getImgFileFileName());
					 if (!extension.equals("jpg")&&!extension.equals("png")&&!extension.equals("gif")) {
							
							ActionContext.getContext().getValueStack().set("checkImgFile", "图片必须是jpg,png,gif格式");
							return "edit";
						}
					BufferedImage bufferedImage = ImageIO.read(file);
					width = bufferedImage.getWidth();
					height = bufferedImage.getHeight();
					if(model.getOsType().indexOf("android")>-1){
						if (width != 720||height!=1280) {
							ActionContext.getContext().getValueStack().set("imgUrl", "");
							ActionContext.getContext().getValueStack().set("checkImgFile", "图尺寸不符合要求,尺寸必须为720*1280");
							return "edit";
						}
					}else if (model.getOsType().indexOf("iphone4")>-1){
					    if (width != 640) {
                            ActionContext.getContext().getValueStack().set("imgUrl", "");
                            ActionContext.getContext().getValueStack().set("checkImgFile", "图尺寸不符合要求,宽度必须为640");
                            return "edit";
                        }
					}
					else{
						if (width != 1242) {
							ActionContext.getContext().getValueStack().set("imgUrl", "");
							ActionContext.getContext().getValueStack().set("checkImgFile", "图尺寸不符合要求,宽度必须为1242");
							return "edit";
						}
					}
					
				}
				
			
				model.setImgUrl(uploadPic(file,getImgFileFileName(),extension));
				model.setCreateDate(new Date());
				model.setImgHeight(String.valueOf(height));
				model.setImgWidth(String.valueOf(width));
				model.setProductType(ProductTypeEnum.SHANGPIN);
				appPicturesService.add(model);
				ServletActionContext.getResponse().getWriter().write("success");
				
			}else{
				ActionContext.getContext().getValueStack().set("prompt", "如未重新上传图片。则默认使用原图");
				String extension = null;
				File file = getImgFile();
				int width = 0;
				int height = 0;
			
				AppPictures appPictures = new AppPictures();
				appPictures=appPicturesService.findById(model.getId());
				ActionContext.getContext().getValueStack().set("imgUrl", appPictures.getImgUrl().replace("{w}", "160").replace("{h}", "110"));
				if(StringUtils.isEmpty(model.getOsType())||model.getOsType().equals("-1")){
					ActionContext.getContext().getValueStack().set("checkType", "图类型不能为空");
					return "edit";
				}
				if (file == null) {
					
					
					model.setImgUrl(appPictures.getImgUrl());
					model.setImgHeight(appPictures.getImgHeight());
					model.setImgWidth(appPictures.getImgWidth());
				} else {
					extension = WebUtil.getExt(getImgFileFileName());
					
					if (!extension.equals("jpg")&&!extension.equals("png")&&!extension.equals("gif")) {
						
						ActionContext.getContext().getValueStack().set("checkImgFile", "图片必须是jpg,png,gif格式");
						return "edit";
					}
					BufferedImage bufferedImage = ImageIO.read(file);
					width = bufferedImage.getWidth();
					height = bufferedImage.getHeight();
					if(model.getOsType().indexOf("android")>-1){
						if (width != 720||height!=1280) {
							ActionContext.getContext().getValueStack().set("checkImgFile", "图尺寸不符合要求,尺寸必须为720*1280");
							return "edit";
						}
					}else if (model.getOsType().indexOf("iphone4")>-1){
					    if (width != 640) {
                            ActionContext.getContext().getValueStack().set("checkImgFile", "图尺寸不符合要求,宽度必须为640");
                            return "edit";
                        }
					}
					else{
						if (width != 1242) {
							ActionContext.getContext().getValueStack().set("checkImgFile", "图尺寸不符合要求,宽度必须为1242");
							return "edit";
						}
					}
					model.setProductType(appPictures.getProductType());
					model.setImgUrl(uploadPic(file,getImgFileFileName(),extension));
					model.setImgHeight(String.valueOf(height));
					model.setImgWidth(String.valueOf(width));
				}
				model.setCreateDate(new Date());
				appPicturesService.update(model);
				ServletActionContext.getResponse().getWriter().write("success");
			}
			
		} catch (Exception e) {
			log.error("appPicturesService-edit() " + e);
		}
		return "input";
	}
	
	
	public String aolaiEdit() {
		try {
			if(model.getId()==null){
				File file = getImgFile();
				int width;
				int height;
				String extension;
				if(StringUtils.isEmpty(model.getOsType())||model.getOsType().equals("-1")){
					ActionContext.getContext().getValueStack().set("checkType", "图类型不能为空");
					ActionContext.getContext().getValueStack().set("imgUrl", "");
					return "aolaiEdit";
				}else{
					if(model.getOsType().indexOf("Share")>-1){
						if(StringUtils.isEmpty(model.getDescription())){
							ActionContext.getContext().getValueStack().set("checkDesc", "文字描述不能为空");
							ActionContext.getContext().getValueStack().set("imgUrl", "");
							return "aolaiEdit";
						}
						if(StringUtils.isEmpty(model.getShareUrl())){
							ActionContext.getContext().getValueStack().set("checkShare", "分享不能为空");
							ActionContext.getContext().getValueStack().set("imgUrl", "");
							return "aolaiEdit";
						}
						if(StringUtils.isEmpty(model.getShareTitle())){
							ActionContext.getContext().getValueStack().set("checkTitle", "title不能为空");
							ActionContext.getContext().getValueStack().set("imgUrl", "");
							return "aolaiEdit";
						}
					}
				}
				
				if (file == null) {
					ActionContext.getContext().getValueStack().set("checkImgFile", "请选择一张图片");
					return "aolaiEdit";
				
				} else {
					 extension = WebUtil.getExt(getImgFileFileName());
					if (!extension.equals("jpg")&&!extension.equals("png")&&!extension.equals("gif")) {
						
						ActionContext.getContext().getValueStack().set("checkImgFile", "图片必须是jpg,png,gif格式");
						return "aolaiEdit";
					}
					BufferedImage bufferedImage = ImageIO.read(file);
					width = bufferedImage.getWidth();
					height = bufferedImage.getHeight();
					if(model.getOsType().indexOf("android")>-1){
						if (width != 720||height!=1280) {
							ActionContext.getContext().getValueStack().set("imgUrl", "");
							ActionContext.getContext().getValueStack().set("checkImgFile", "图尺寸不符合要求,尺寸必须为720*1280");
							return "aolaiEdit";
						}
					}else if (model.getOsType().indexOf("iphone4")>-1){
					    if (width != 640) {
                            ActionContext.getContext().getValueStack().set("imgUrl", "");
                            ActionContext.getContext().getValueStack().set("checkImgFile", "图尺寸不符合要求,宽度必须为640");
                            return "aolaiEdit";
                        }
					}else{
						if (width != 1242) {
							ActionContext.getContext().getValueStack().set("imgUrl", "");
							ActionContext.getContext().getValueStack().set("checkImgFile", "图尺寸不符合要求,宽度必须为1242");
							return "aolaiEdit";
						}
					}
					
				}
				
			
				model.setImgUrl(uploadPic(file,getImgFileFileName(),extension));
				model.setCreateDate(new Date());
				model.setImgHeight(String.valueOf(height));
				model.setImgWidth(String.valueOf(width));
				model.setProductType(ProductTypeEnum.AOLAI);
				appPicturesService.add(model);
				ServletActionContext.getResponse().getWriter().write("success");
				
			}else{
				ActionContext.getContext().getValueStack().set("prompt", "如未重新上传图片。则默认使用原图");
				String extension = null;
				File file = getImgFile();
				int width = 0;
				int height = 0;
			
				AppPictures appPictures = new AppPictures();
				appPictures=appPicturesService.findById(model.getId());
				ActionContext.getContext().getValueStack().set("imgUrl", appPictures.getImgUrl().replace("{w}", "160").replace("{h}", "110"));
				if(StringUtils.isEmpty(model.getOsType())||model.getOsType().equals("-1")){
					ActionContext.getContext().getValueStack().set("checkType", "图类型不能为空");
					return "aolaiEdit";
				}
				if (file == null) {
					
					
					model.setImgUrl(appPictures.getImgUrl());
					model.setImgHeight(appPictures.getImgHeight());
					model.setImgWidth(appPictures.getImgWidth());
				} else {
					extension = WebUtil.getExt(getImgFileFileName());
					if (!extension.equals("jpg")&&!extension.equals("png")&&!extension.equals("gif")) {
						
						ActionContext.getContext().getValueStack().set("checkImgFile", "图片必须是jpg,png,gif格式");
						return "aolaiEdit";
					}
					BufferedImage bufferedImage = ImageIO.read(file);
					width = bufferedImage.getWidth();
					height = bufferedImage.getHeight();
					if(model.getOsType().indexOf("android")>-1){
						if (width != 720||height!=1280) {
							ActionContext.getContext().getValueStack().set("checkImgFile", "图尺寸不符合要求,尺寸必须为720*1280");
							return "aolaiEdit";
						}
					}else if (model.getOsType().indexOf("iphone4")>-1){
					    if (width != 640) {
					        ActionContext.getContext().getValueStack().set("checkImgFile", "图尺寸不符合要求,宽度必须为640");
	                        return "aolaiEdit";
					    }					   
					}else{
						if (width != 1242) {
							ActionContext.getContext().getValueStack().set("checkImgFile", "图尺寸不符合要求,宽度必须为1242");
							return "aolaiEdit";
						}
					}
					model.setProductType(appPictures.getProductType());
					model.setImgUrl(uploadPic(file,getImgFileFileName(),extension));
					model.setImgHeight(String.valueOf(height));
					model.setImgWidth(String.valueOf(width));
				}
				model.setCreateDate(new Date());
				appPicturesService.update(model);
				ServletActionContext.getResponse().getWriter().write("success");
			}
			
		} catch (Exception e) {
			log.error("appPicturesService-edit() " + e);
		}
		return "aolai";
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

	public String getEditFlag() {
		return editFlag;
	}

	public void setEditFlag(String editFlag) {
		this.editFlag = editFlag;
	}

	public Log getLog() {
		return log;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public JSONObject getEntityJson() {
		return entityJson;
	}

	public void setEntityJson(JSONObject entityJson) {
		this.entityJson = entityJson;
	}

	public File getImgFile() {
		return imgFile;
	}

	public void setImgFile(File imgFile) {
		this.imgFile = imgFile;
	}

	public String getImgFileContentType() {
		return imgFileContentType;
	}

	public void setImgFileContentType(String imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
	}

	public String getImgFileFileName() {
		return imgFileFileName;
	}

	public void setImgFileFileName(String imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}
	public String getShopType() {
		return this.shopType;
	}
	public void setShopType(String shopType) {
		this.shopType = shopType;
	}
	
	
	
}
