package com.shangpin.mobileShangpin.platform.vo;

import java.util.List;



/**
 * CMS尚品专题vo
 */
public class CmsTopVO {
	
	private String code;		//返回错误码，0为返回成功
	private String msg;		//错误信息
	private String topicid;		//专题编号（加入购物袋是可选项，用于统计通过专题销售的商品）
	private String shareurl;		//分享URL
	private String name;		//专题名称
	private String subtitle;		//专题副标题
	private String topicdesc;		//专题活动描述，描述活动内容
	private String status;		//状态：1开启、2关闭
	private String starttime;		//开始时间(格式：2013-10-08 00:00:00)(日期和小时之间有空格)
	private String endtime;		//结束时间(格式：2013-10-08 00:00:00)
	private BrandVo BrandVO;	//所属品牌
	private String type;		//模板类型：1标准、2楼层、3错落
	private String o1type;	//运营位1的类型：0无、1图片链接、2领取优惠券
	private String o1code;	//领取优惠券类型的优惠券激活码
	private String o1logo;	//图片链接类型的图片文件地址
	private String o1url;	//图片链接类型的跳转链接
	private String headPic;//头图
	private String pricetag; //预热状态的价格标签：0不显示标签、1显示“10月22日狂欢价+促销价”、2显示“10月22日更多惊喜” 最新修改的情况，此字段没用，但保留
	private String pricename;//专题的价格描述
	private String datepretime;	//活动预热时间，如果没有预热状态则为空
	private List<CmsSPGroupMechandiseVO>	cmsSPGroupMechandiseVO;//分组商品
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
	public String getTopicid() {
		return topicid;
	}
	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}
	public String getShareurl() {
		return shareurl;
	}
	public void setShareurl(String shareurl) {
		this.shareurl = shareurl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getTopicdesc() {
		return topicdesc;
	}
	public void setTopicdesc(String topicdesc) {
		this.topicdesc = topicdesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public BrandVo getBrandVO() {
		return BrandVO;
	}
	public void setBrandVO(BrandVo brandVO) {
		BrandVO = brandVO;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getO1type() {
		return o1type;
	}
	public void setO1type(String o1type) {
		this.o1type = o1type;
	}
	public String getO1code() {
		return o1code;
	}
	public void setO1code(String o1code) {
		this.o1code = o1code;
	}
	public String getO1logo() {
		return o1logo;
	}
	public void setO1logo(String o1logo) {
		this.o1logo = o1logo;
	}
	public String getO1url() {
		return o1url;
	}
	public void setO1url(String o1url) {
		this.o1url = o1url;
	}
	public List<CmsSPGroupMechandiseVO> getCmsSPGroupMechandiseVO() {
		return cmsSPGroupMechandiseVO;
	}
	public void setCmsSPGroupMechandiseVO(
			List<CmsSPGroupMechandiseVO> cmsSPGroupMechandiseVO) {
		this.cmsSPGroupMechandiseVO = cmsSPGroupMechandiseVO;
	}
	public String getHeadPic() {
		return headPic;
	}
	public void setHeadPic(String headPic) {
		this.headPic = headPic;
	}
	public String getPricetag() {
		return pricetag;
	}
	public void setPricetag(String pricetag) {
		this.pricetag = pricetag;
	}
	public String getPricename() {
		return pricename;
	}
	public void setPricename(String pricename) {
		this.pricename = pricename;
	}
	public String getDatepretime() {
		return datepretime;
	}
	public void setDatepretime(String datepretime) {
		this.datepretime = datepretime;
	}
	


}
