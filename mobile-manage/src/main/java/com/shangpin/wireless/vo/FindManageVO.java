package com.shangpin.wireless.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.shangpin.wireless.domain.FindManage;
import com.shangpin.wireless.domain.TypeEnum;
import com.shangpin.wireless.util.StringUtils;

public class FindManageVO {
	
	private String name;
	private String id;
	private String activityID;
	private String starttime;
	private String endtime;
	private Date showStartDate;
	private Date showEndDate;
	private String imgUrl;
	private String mobilepic;
	private String iphonepic;
	private String shareurl;
	private String pretime;
	private String status;
	private String desc;
	private String subtitle;
	private String timequantum;
	private String type;
	private String showDateDesc;
	private String activityDateDesc;
	private String showPosition;
	private String imgWidth;
	private String imgHeight;
	private String getUrl;
	//移动端预热时间
	private Date mobilePreTime;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public Date getShowStartDate() {
		return showStartDate;
	}

	public void setShowStartDate(Date showStartDate) {
		this.showStartDate = showStartDate;
	}

	public Date getShowEndDate() {
		return showEndDate;
	}

	public void setShowEndDate(Date showEndDate) {
		this.showEndDate = showEndDate;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getShareurl() {
		return shareurl;
	}

	public void setShareurl(String shareurl) {
		this.shareurl = shareurl;
	}

	public String getPretime() {
		return pretime;
	}

	public void setPretime(String pretime) {
		this.pretime = pretime;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getTimequantum() {
		return timequantum;
	}

	public void setTimequantum(String timequantum) {
		this.timequantum = timequantum;
	}

	public String getIphonepic() {
		return iphonepic;
	}

	public void setIphonepic(String iphonepic) {
		this.iphonepic = iphonepic;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMobilepic() {
		return mobilepic;
	}

	public void setMobilepic(String mobilepic) {
		this.mobilepic = mobilepic;
	}

	public String getShowDateDesc() {
		return showDateDesc;
	}

	public void setShowDateDesc(String showDateDesc) {
		this.showDateDesc = showDateDesc;
	}

	public String getActivityDateDesc() {
		return activityDateDesc;
	}

	public void setActivityDateDesc(String activityDateDesc) {
		this.activityDateDesc = activityDateDesc;
	}

	public String getActivityID() {
		return activityID;
	}

	public void setActivityID(String activityID) {
		this.activityID = activityID;
	}
	
	
	public String getShowPosition() {
		return showPosition;
	}

	public void setShowPosition(String showPosition) {
		this.showPosition = showPosition;
	}

	public String getImgWidth() {
		return imgWidth;
	}

	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}

	public String getImgHeight() {
		return imgHeight;
	}

	public void setImgHeight(String imgHeight) {
		this.imgHeight = imgHeight;
	}
	
	
	public String getGetUrl() {
		return getUrl;
	}

	public void setGetUrl(String getUrl) {
		this.getUrl = getUrl;
	}
	

	public Date getMobilePreTime() {
		return mobilePreTime;
	}

	public void setMobilePreTime(Date mobilePreTime) {
		this.mobilePreTime = mobilePreTime;
	}

	public static FindManageVO doToVo(FindManage findManage) {
		if (findManage != null) {
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			FindManageVO findManageVO = new FindManageVO();
			if(findManage.getId()!=null){
				findManageVO.setId(String.valueOf(findManage.getId()));
			}
			if(StringUtils.isNotEmpty(findManage.getActivityID())){
				findManageVO.setActivityID(findManage.getActivityID());
			}
			if(StringUtils.isNotEmpty(findManage.getTitle())){
				findManageVO.setName(findManage.getTitle());
			}
			
			if(findManage.getEndDate()!=null){
				findManageVO.setEndtime(sdf.format(findManage.getEndDate()));
			}
			
			if(findManage.getShowEndDate()!=null){
				findManageVO.setShowEndDate(findManage.getShowEndDate());
			}
			if(findManage.getShowStartDate()!=null){
				findManageVO.setShowStartDate(findManage.getShowStartDate());
			}
			
			if(findManage.getShowEndDate()!=null&&findManage.getShowStartDate()!=null){
				findManageVO.setShowDateDesc(new StringBuilder().append(sdf.format(findManage.getShowStartDate())).append("<br/>").append("至<br/>").append(sdf.format(findManage.getShowEndDate())).append("<br/>").toString());
			}
			
			if(findManage.getEndDate()!=null&&findManage.getStartDate()!=null){
				findManageVO.setActivityDateDesc(new StringBuilder().append(sdf.format(findManage.getStartDate())).append("<br/>").append("至<br/>").append(sdf.format(findManage.getEndDate())).append("<br/>").toString());
			}
			
			
			if(findManage.getStartDate()!=null){
				findManageVO.setStarttime(sdf.format(findManage.getStartDate()));
			}
		
			if (findManage.getPreTime() != null) {
				findManageVO.setPretime(sdf.format(findManage.getPreTime()));
			}
			if (findManage.getType() != null) {
				if (findManage.getType().equals(TypeEnum.IMAGETEXT)) {
					if (StringUtils.isNotEmpty(findManage.getImgUrl())) {
						findManageVO.setImgUrl(findManage.getImgUrl().replace("{w}", "160").replace("{h}", "80"));
					}
					findManageVO.setType("图文");
				} else {
					findManageVO.setType("活动");
					if( StringUtils.isNotEmpty(findManage.getImgUrl())){
						findManageVO.setImgUrl(findManage.getImgUrl().replace("{w}", "160").replace("{h}", "80"));
					}else{
						if(StringUtils.isNotEmpty(findManage.getMobilePic())){
							findManageVO.setImgUrl(findManage.getMobilePic().replace("{w}", "160").replace("{h}", "80"));
						}
					}
					
				}
			}
			
			
			
			if(StringUtils.isNotEmpty(findManage.getShareUrl())){
				findManageVO.setShareurl(findManage.getShareUrl());
			}
			if(StringUtils.isNotEmpty(findManage.getStatus())){
				if(findManage.getStatus().equals("1")){
					findManageVO.setStatus("开启");
				}else{
					findManageVO.setStatus("关闭");
				}
				
			}
			
			if(StringUtils.isNotEmpty(findManage.getSubTitle())){
				findManageVO.setSubtitle(findManage.getSubTitle());
			}
			if(StringUtils.isNotEmpty(findManage.getDescription())){
				findManageVO.setDesc(findManage.getDescription());
			}
			
			return findManageVO;
		}
		return null;
	}
	
	public static FindManageVO doToUpVo(FindManage findManage) {
		if (findManage != null) {
			final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			FindManageVO findManageVO = new FindManageVO();
			if(findManage.getId()!=null){
				findManageVO.setId(String.valueOf(findManage.getId()));
			}
			if(StringUtils.isNotEmpty(findManage.getActivityID())){
				findManageVO.setActivityID(findManage.getActivityID());
			}
			if(StringUtils.isNotEmpty(findManage.getTitle())){
				findManageVO.setName(findManage.getTitle());
			}
			
			if(findManage.getEndDate()!=null){
				findManageVO.setEndtime(sdf.format(findManage.getEndDate()));
			}
			
			if(findManage.getShowEndDate()!=null){
				findManageVO.setShowEndDate(findManage.getShowEndDate());
			}
			if(findManage.getShowStartDate()!=null){
				findManageVO.setShowStartDate(findManage.getShowStartDate());
			}
			
		
			
			if(findManage.getStartDate()!=null){
				findManageVO.setStarttime(sdf.format(findManage.getStartDate()));
			}
		
			if (findManage.getPreTime() != null) {
				findManageVO.setPretime(sdf.format(findManage.getPreTime()));
			}
		
			if(findManage.getType()!=null){
				findManageVO.setType(findManage.getType().toString());
			}
			
			if(StringUtils.isNotEmpty(findManage.getShareUrl())){
				findManageVO.setShareurl(findManage.getShareUrl());
			}
			
			if(StringUtils.isNotEmpty(findManage.getSubTitle())){
				findManageVO.setSubtitle(findManage.getSubTitle());
			}
			if(StringUtils.isNotEmpty(findManage.getDescription())){
				findManageVO.setDesc(findManage.getDescription());
			}
			
			if (findManage.getType() == TypeEnum.IMAGETEXT && StringUtils.isNotEmpty(findManage.getImgUrl())) {
				findManageVO.setImgUrl(findManage.getImgUrl());
				if(StringUtils.isNotEmpty(findManage.getImgHeight())){
					findManageVO.setImgHeight(findManage.getImgHeight());
				}
				
				if(StringUtils.isNotEmpty(findManage.getImgWidth())){
					findManageVO.setImgWidth(findManage.getImgWidth());
				}
			}
				
			if(StringUtils.isNotEmpty(findManage.getGetUrl())){
				findManageVO.setGetUrl(findManage.getGetUrl());
			}
			if (findManage.getMobilePreTime() != null) {
				findManageVO.setMobilePreTime(findManage.getMobilePreTime());
			}
			return findManageVO;
		}
		return null;
	}
	

}
