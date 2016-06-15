package com.shangpin.wireless.view.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.shangpin.wireless.base.action.BaseAction;
import com.shangpin.wireless.domain.Constants;
import com.shangpin.wireless.domain.ReturnObject;
import com.shangpin.wireless.domain.WXFashionInformation;
import com.shangpin.wireless.util.JsonUtil;
import com.shangpin.wireless.util.SqlHelper;
import com.shangpin.wireless.util.StringUtils;
import com.shangpin.wireless.util.WebUtil;
/**
 * 微信潮流资讯
 * @author liling
 * @date   2014-01-26
 */
@Controller
@Scope("prototype")
public class WxFashionInformationAction extends BaseAction<WXFashionInformation> {

	private static final long serialVersionUID = -1788808828914053575L;
	private File coverFile;
	private String coverFileContentType;
	private String coverFileFileName;
	
	private File upload; // 文件
	private String uploadContentType; // 文件类型
	private String uploadFileName; // 文件名
	private int page = 1;
	private int rows;

	public String input() {
		return "input";
	}

	/** 添加页面 */
	public String addUI() {
		return "addUI";
	}

	public String add() {
		try {
			if(model.getId()==null){
				if (StringUtils.isEmpty(model.getTitle())) {
					ActionContext.getContext().getValueStack().set("checkTitle", "标题不能为空");
					return "addUI";
				}
				if (model.getTitle().length() > 50) {
					ActionContext.getContext().getValueStack().set("checkTitle", "标题长度不能大于50个汉字");
					return "addUI";
				}
				if (StringUtils.isNotEmpty(model.getAuthor())) {
					if (model.getAuthor().length() > 20) {
						ActionContext.getContext().getValueStack().set("checkAuthor", "作者名不能大于20个汉字");
						return "addUI";
					}
				}
				if (StringUtils.isNotEmpty(model.getDigest())) {
					if (model.getDigest().length() > 150) {
						ActionContext.getContext().getValueStack().set("checkDigest", "摘要长度不能多于150个汉字");
						return "addUI";
					}
				}
				File coverImgFile = getCoverFile();
				String coverImgExtension = null;
			
			
				if (coverImgFile == null){
					ActionContext.getContext().getValueStack().set("checkCoverImgFile", "封面图必须上传");
					return "addUI";
				}else{
						coverImgExtension = WebUtil.getExt(getCoverFileFileName());
						if (!coverImgExtension.equals("jpg")&&!coverImgExtension.equals("png")&&!coverImgExtension.equals("gif")) {
							ActionContext.getContext().getValueStack().set("checkCoverImgFile", "封面图必须是jpg格式");
							return "addUI";
						}
						BufferedImage bufferedImage = ImageIO.read(coverImgFile);
						int width = bufferedImage.getWidth();
						int height = bufferedImage.getHeight();
						if (width != 600 || height != 320) {
							ActionContext.getContext().getValueStack().set("checkCoverImgFile", "封面图尺寸不符合要求");
							return "addUI";
						}
						if (coverImgFile.length() > 200 * 1024) {
							ActionContext.getContext().getValueStack().set("checkCoverImgFile", "封面图不能大于200k");
							return "addUI";
						}
				}		
				if (StringUtils.isEmpty(model.getContent())) {
					ActionContext.getContext().getValueStack().set("checkContent", "资讯内容不能为空");
					return "addUI";
				}
				if(coverImgFile!=null){
					model.setCoverImg(uploadPic(coverImgFile, getCoverFileFileName(), coverImgExtension));
					model.setCoverImgWidth("600");
					model.setCoverImgheight("320");
					
				}
				
//				model.setSort(0);
			
				
				Date date= new Date();
				if(model.getReleaseDate()==null){
					final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					model.setReleaseDate(sdf.parse(sdf.format(date)));
				}
				model.setCreateDate(date);
				model.setSort(1);
				
				wxFashionInformationService.add(model);
				String minSort=wxFashionInformationService.findMinSort(model.getId());
				if(StringUtils.isNotEmpty(minSort)){
					int mincount=Integer.valueOf(minSort.split("[.]")[0]);
					if(mincount<=1){
						wxFashionInformationService.sortRetrude(model.getId());
					}
				}
				
				
				ServletActionContext.getResponse().getWriter().write("success");
			}else{
				WXFashionInformation wxFashionInformation = new WXFashionInformation();
				
				wxFashionInformation = wxFashionInformationService.findById(model.getId());
				
				if (StringUtils.isEmpty(model.getTitle())) {
					ActionContext.getContext().getValueStack().set("checkTitle", "标题不能为空");
					return "addUI";
				}
				if (model.getTitle().length() > 50) {
					ActionContext.getContext().getValueStack().set("checkTitle", "标题长度不能大于50个汉字");
					return "addUI";
				}
				if (StringUtils.isNotEmpty(model.getAuthor())) {
					if (model.getAuthor().length() > 20) {
						ActionContext.getContext().getValueStack().set("checkAuthor", "作者名不能大于20个汉字");
						return "addUI";
					}
				}
				if (StringUtils.isNotEmpty(model.getDigest())) {
					if (model.getDigest().length() > 150) {
						ActionContext.getContext().getValueStack().set("checkDigest", "摘要长度不能多于150个汉字");
						return "addUI";
					}
				}
				File coverImgFile = getCoverFile();
				String coverImgExtension = null;
			
			
				if (coverImgFile == null){
					if(wxFashionInformation.getCoverImg()!=null){
						model.setCoverImg(wxFashionInformation.getCoverImg());
						model.setCoverImgWidth("600");
						model.setCoverImgheight("320");
					
					}
					
				}else {
					final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
					ActionContext.getContext().getValueStack().set("releaseDate",sdf.format(wxFashionInformation.getReleaseDate()));
					coverImgExtension = WebUtil.getExt(getCoverFileFileName());
					if (!coverImgExtension.equals("jpg")) {
						ActionContext.getContext().getValueStack().set("checkCoverImgFile", "封面图必须是jpg格式");
						return "addUI";
					}
					BufferedImage bufferedImage = ImageIO.read(coverImgFile);
					int width = bufferedImage.getWidth();
					int height = bufferedImage.getHeight();
					if (width != 600 || height != 320) {
						ActionContext.getContext().getValueStack().set("checkCoverImgFile", "封面图尺寸不符合要求");
						return "addUI";
					}
					if (coverImgFile.length() > 200 * 1024) {
						ActionContext.getContext().getValueStack().set("checkCoverImgFile", "封面图不能大于200k");
						return "addUI";
					}
					model.setCoverImg(uploadPic(coverImgFile, getCoverFileFileName(), coverImgExtension));
					model.setCoverImgWidth("600");
					model.setCoverImgheight("320");
				
				
				}
				if (StringUtils.isEmpty(model.getContent())) {
					ActionContext.getContext().getValueStack().set("checkContent", "资讯内容不能为空");
					return "addUI";
				}
			
			
				model.setSort(wxFashionInformation.getSort());
				Date date= new Date();
				if(model.getReleaseDate()==null){
					model.setReleaseDate(date);
				}
				model.setCreateDate(date);
				wxFashionInformationService.update(model);
				ServletActionContext.getResponse().getWriter().write("success");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "input";
	}

	public String execute() {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("GBK");
			PrintWriter out;
			out = response.getWriter();
			// 对文件进行校验
			if (upload == null || uploadContentType == null || uploadFileName == null) {
				out.print("<font color=\"red\" size=\"2\">*请选择上传文件</font>");
				return null;
			}
			if ((uploadContentType.equals("image/pjpeg") || uploadContentType.equals("image/jpeg")) && uploadFileName.substring(uploadFileName.length() - 4).toLowerCase().equals(".jpg")) {
				// IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
			} else if (uploadContentType.equals("image/png") && uploadFileName.substring(uploadFileName.length() - 4).toLowerCase().equals(".png")) {
			} else if (uploadContentType.equals("image/gif") && uploadFileName.substring(uploadFileName.length() - 4).toLowerCase().equals(".gif")) {
//			} else if (uploadContentType.equals("image/bmp") && uploadFileName.substring(uploadFileName.length() - 4).toLowerCase().equals(".bmp")) {
			} else {
//				out.print("<font color=\"red\" size=\"2\">*文件格式不正确（必须为.jpg/.gif/.bmp/.png文件）</font>");
				out.print("<font color=\"red\" size=\"2\">*文件格式不正确（必须为.jpg文件）</font>");
				return null;
			}
			
			
			if (upload.length() > 600 * 1024) {
				out.print("<font color=\"red\" size=\"2\">*文件大小不得大于600k</font>");
				return null;
			}
			File f = getUpload();
			String filename = getUploadFileName();
			String uploadExtension = WebUtil.getExt(getUploadFileName());
			
			String imgurl = uploadPic(f, filename, uploadExtension).replace("{w}", "0").replace("{h}", "0");
			// 设置返回“图像”选项卡
			String callback = ServletActionContext.getRequest().getParameter("CKEditorFuncNum");
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + imgurl + "','')");
			out.println("</script>");
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String query() {
		try {
			// 1，构建查询条件
			SqlHelper sqlQueryListHelper = new SqlHelper("wxFashionInformation", "t");
			SqlHelper sqlQueryCountHelper = new SqlHelper("wxFashionInformation");
			sqlQueryListHelper.addColumn("t.title");
			sqlQueryListHelper.addColumn("t.id");
			sqlQueryListHelper.addColumn("t.author");
			sqlQueryListHelper.addColumn("t.digest");
			sqlQueryListHelper.addColumn("t.releaseDate");
			sqlQueryListHelper.addColumn("t.coverImg");
		
			// sqlQueryListHelper.addColumn("t.topImgHeight");
			// sqlQueryListHelper.addColumn("t.topImgWidth");
			// sqlQueryListHelper.addColumn("t.createDate");
			sqlQueryListHelper.addColumn("t.sort");
			sqlQueryCountHelper.addColumn("count(*)");
			if (StringUtils.isNotEmpty(model.getTitle())) {
//				sqlQueryListHelper.addWhereCondition("c.channelName like'%" + model.getChannelName() + "%'");
				sqlQueryListHelper.addWhereCondition("t.title like'%" + model.getTitle() + "%'");
				sqlQueryCountHelper.addWhereCondition("title like'%" + model.getTitle() + "%'");
			}
			if (model.getReleaseDate() != null) {
				final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				String releaseDate=sdf.format(model.getReleaseDate());
				sqlQueryListHelper.addWhereCondition("t.releaseDate>=" + "'" + releaseDate +" 00:00:00"+ "'");
				sqlQueryCountHelper.addWhereCondition("releaseDate>=" + "'" + releaseDate  +" 00:00:00"+"'");
			}
			sqlQueryListHelper.addOrderByProperty(true, "t.sort", true);
			sqlQueryListHelper.addOrderByProperty(true, "t.releaseDate", false);
			List<HashMap> queryList = wxFashionInformationService.executeSqlToMap(sqlQueryListHelper.getQueryListSql(), page, rows);
			List<HashMap> recordList = new ArrayList<HashMap>(queryList.size());
			for (int i = 0; i < queryList.size(); i++) {
				HashMap record = new HashMap();
				HashMap log = queryList.get(i);
				if (log.get("title") != null) {
					record.put("title", log.get("title"));
				}
				if (log.get("coverImg") != null) {
					if (log.get("coverImg").toString().trim()!=""){
						record.put("coverImg", log.get("coverImg").toString().replace("{w}", "180").replace("{h}", "110"));
					}
				
				}
				
				record.put("id", log.get("id"));
				if (log.get("sort") != null) {
					record.put("sort", log.get("sort"));
				}
				if (log.get("releaseDate") != null) {
					record.put("releaseDate", log.get("releaseDate"));
				}
				recordList.add(record);
			}
			Integer count = wxFashionInformationService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
			ReturnObject returnObject = new ReturnObject();
			returnObject.setTotal(count);
			returnObject.setRows(recordList);
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("wxFashionInformationAction-query() " + e);
		}
		return null;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String info() {
		WXFashionInformation wxFashionInformation = new WXFashionInformation();
		try {
			wxFashionInformation = wxFashionInformationService.findById(model.getId());
			// JSONObject jsonObj = JSONObject.fromObject(wxFashionInformation);
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			HashMap record = new HashMap();
			if (StringUtils.isNotEmpty(wxFashionInformation.getTitle()) ) {
				record.put("title", wxFashionInformation.getTitle());
			}
			if (StringUtils.isNotEmpty(wxFashionInformation.getAuthor())) {
				record.put("author", wxFashionInformation.getAuthor());
			}
			if (StringUtils.isNotEmpty(wxFashionInformation.getDigest())) {
				record.put("digest", wxFashionInformation.getDigest());
			}
			if (StringUtils.isNotEmpty(wxFashionInformation.getContent())) {
				record.put("content", wxFashionInformation.getContent());
			}
			if (wxFashionInformation.getReleaseDate()!=null) {
				record.put("releaseDate", sdf.format(wxFashionInformation.getReleaseDate()));
			}
			record.put("id", String.valueOf(wxFashionInformation.getId()));
			if(StringUtils.isNotEmpty(wxFashionInformation.getCoverImg())){
				record.put("coverImg",wxFashionInformation.getCoverImg().replace("{w}", "180").replace("{h}", "110"));
			}else{
				record.put("coverImg",null);
			}
			
			
//			record.put("smallCoverImg()",wxFashionInformation.getSmallCoverImg());
//			wxFashionInformation.setReleaseDate(sdf.parse(sdf.format(wxFashionInformation.getReleaseDate())));
			ActionContext.getContext().getValueStack().set("prompt", "如未重新上传图片。则默认使用原有封面图");
			ActionContext.getContext().getValueStack().push(record);
			// ServletActionContext.getResponse().getWriter().print(wxFashionInformation);
		} catch (Exception e) {
			log.error("wxFashionInformationAction-info() " + e);
			e.printStackTrace();
		}
		return "addUI";
	}

	public String delete() {
		try {
			wxFashionInformationService.delete(model.getId());
			wxFashionInformationService.retrudeSortDel(model.getSort());
			ServletActionContext.getResponse().getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	public String setSort() {
//		WXFashionInformation wxFashionInformation = new WXFashionInformation();
//		try {
//			wxFashionInformation = wxFashionInformationService.findById(model.getId());
//			wxFashionInformation.setSort(model.getSort());
//			// JSONObject jsonObj = JSONObject.fromObject(wxFashionInformation);
//			wxFashionInformationService.update(wxFashionInformation);
//	
//			ServletActionContext.getResponse().getWriter().write("success");
//			// ServletActionContext.getResponse().getWriter().print(wxFashionInformation);
//		} catch (Exception e) {
//			log.error("wxFashionInformationAction-setSort() " + e);
//			e.printStackTrace();
//		}
//		return "addUI";
//	}
//	
	public String setSort() {
		ReturnObject returnObject = new ReturnObject();
		WXFashionInformation wxFashionInformation = new WXFashionInformation();
		try {
			String sort = wxFashionInformationService.findMaxSort();
			wxFashionInformation = wxFashionInformationService.findById(model.getId());
			if (StringUtils.isNotEmpty(sort)) {
				if (Integer.valueOf(sort.split("[.]")[0]) < Integer.valueOf(model.getSort())) {
					returnObject.setReturnCode("fail");
					returnObject.setReturnInfo("当前可设置的最大位置为"+Integer.valueOf(sort.split("[.]")[0]));
					ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
					return null;
				}
			}
			String con = wxFashionInformationService.findBySort(model.getSort());
			if (Integer.valueOf(wxFashionInformation.getSort()) > Integer.valueOf(model.getSort())) {
				if (StringUtils.isNotEmpty(con)) {
					if (Integer.valueOf(con.split("[.]")[0]) > 0) {
						wxFashionInformationService.retrudeByEndSort(wxFashionInformation.getSort(),model.getSort());
					}
				}
			} else {
				if (StringUtils.isNotEmpty(con)) {
					if (Integer.valueOf(con.split("[.]")[0]) > 0) {
						wxFashionInformationService.retrudeByStartSort(wxFashionInformation.getSort(),model.getSort());
					}
				}
			}
			wxFashionInformation.setSort(model.getSort());
			wxFashionInformationService.update(wxFashionInformation);
			returnObject.setReturnCode("success");
			ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
		} catch (Exception e) {
			log.error("findManageAction-setSort() " + e);
			e.printStackTrace();
		}
		return null;
	}
	
	public File getCoverFile() {
		return coverFile;
	}

	public void setCoverFile(File coverFile) {
		this.coverFile = coverFile;
	}

	public String getCoverFileContentType() {
		return coverFileContentType;
	}

	public void setCoverFileContentType(String coverFileContentType) {
		this.coverFileContentType = coverFileContentType;
	}

	public String getCoverFileFileName() {
		return coverFileFileName;
	}

	public void setCoverFileFileName(String coverFileFileName) {
		this.coverFileFileName = coverFileFileName;
	}



	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadContentType() {
		return uploadContentType;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
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
}
