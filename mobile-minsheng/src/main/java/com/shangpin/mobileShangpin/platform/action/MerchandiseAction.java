package com.shangpin.mobileShangpin.platform.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.shangpin.mobileShangpin.common.page.Page;
import com.shangpin.mobileShangpin.common.page.PageContext;
import com.shangpin.mobileShangpin.common.util.BaseAction;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.DataContainerPool;
import com.shangpin.mobileShangpin.common.util.SizeINFOCacheUtil;
import com.shangpin.mobileShangpin.common.util.StringUtil;
import com.shangpin.mobileShangpin.common.util.SysContent;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.service.MerchandiseService;
import com.shangpin.mobileShangpin.platform.vo.AccountVO;
import com.shangpin.mobileShangpin.platform.vo.ActivityVO;
import com.shangpin.mobileShangpin.platform.vo.CmsTopVO;
import com.shangpin.mobileShangpin.platform.vo.SPMerchandiseVO;
import com.shangpin.mobileShangpin.platform.vo.SPTopicVO;

/**
 * 商品模块
 * 
 * @Author zhouyu
 * @CreatDate 2012-11-01
 */
@Controller
@ParentPackage("mobileShangpin")
@Scope("prototype")
@SuppressWarnings("unused")
@Actions({ @Action(value = ("/merchandiseaction"), results = {//
@Result(name = "list", location = "/WEB-INF/pages/market/merchandiseList.jsp"),//
		@Result(name = "spList", location = "/WEB-INF/pages/market/spMerchandiseList.jsp"),//
		@Result(name = "detail", location = "/WEB-INF/pages/market/merchandiseDetail.jsp"),//
		@Result(name = "spdetail", location = "/WEB-INF/pages/market/spMerchandiseDetail.jsp"),//
		@Result(name = "floor",location = "/WEB-INF/pages/subject/floor.jsp"),//
		@Result(name = "toplist",location = "/WEB-INF/pages/subject/toplist.jsp"),//
		@Result(name = "toplist2",location = "/WEB-INF/pages/subject/toplist2.jsp"),//
		@Result(name = "toIndex", type = "redirect", location = "shangpinindex!index"),//
		@Result(name = "A01B01", location = "/WEB-INF/pages/market/size_A01B01.jsp"),//
		@Result(name = "A01B02", location = "/WEB-INF/pages/market/size_A01B02.jsp"),//
		@Result(name = "A02B01", location = "/WEB-INF/pages/market/size_A02B01.jsp"),//
		@Result(name = "getMore", type = "json", params = { "root", "data" }),//
		@Result(name = "getSpNewProductsMore", type = "json", params = { "root", "data" }),//
		@Result(name = "getNewlist",location = "/WEB-INF/pages/market/newMerchandiseList.jsp"),//
		@Result(name = "getMoreList", type = "json", params = {"includeProperties", "merchandiseList.*,pricetag,pageIndex,haveMore,flag,pricename"}),
		@Result(name = "A02B02", location = "/WEB-INF/pages/market/size_A02B02.jsp") }) })
public class MerchandiseAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	MerchandiseService merchandiseService;
	private String pageType;
	// 分类导航内容
	private String typeContent;
	// 活动id
	private String activityId;
	// 活动名称
	private String activityName;
	// 活动起始时间
	private String startTime;
	// 活动结束时间
	private String endTime;
	// 当前系统时间
	private String systemTime;
	// 活动是否开启 0：关闭，1：开启
	private String openFlag;
	// 进入商品详情页面的时候区分来自活动还是专题 1：活动，0：专题
	private String typeFlag;
	// 加载更多的时候传输对象
	private JSONObject data;
	// 判断是否有加载更多
	private Integer haveMore;
	// 专题id
	private String topicid;
	/*** 性别，0为女 */
	private String gender;
	//商品列表
	List<SPMerchandiseVO> merchandiseList=new ArrayList<SPMerchandiseVO>();
	private String categoryid;//页面导航分类一级id
	private String categoryname;//页面导航分类一级name
	private String childCategoryid;//页面导航子分类id
	private String childCategoryname;//页面导航子分类name
	private String istop;//页面导航是否从专题入口进入   1：是 
	private CmsTopVO cmsTopVO;
	private boolean flag;//时间比较
	private String  pageIndex;
	private String  pricename;//预热状态的价格标签描述
	private String cmsTitle;
	private String  pricetag;	//预热状态的价格标签：0不显示标签、1显示“10月22日狂欢价+促销价”、2显示“10月22日更多惊喜” 最新修改的情况，此字段没用，但保留”
	

	/**
	 * 进入商品列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-01
	 * @param
	 * @Return
	 */
	public String splist() {
		try {
			AccountVO user = WebUtil.getSessionUser();
			gender = ServletActionContext.getRequest().getParameter("gender");
			activityId = ServletActionContext.getRequest().getParameter("topicid");
			if (null == gender) {				
				if ((user != null && "1".equals(user.getGender())) || "1".equals(gender)) {
					gender = "1";
				} else {
					gender = "0";
				}
			}		
			String userid = "";
			if (null != user && null != user.getUserid()) {
				userid = user.getUserid();
			}
			SysContent.getRequest().getSession().setAttribute(Constants.SP_TOPIC_ID, activityId);
			SysContent.getRequest().getSession().setAttribute("ac", activityId);
			cmsTopVO = merchandiseService.getNewTopicProducts(activityId, gender, userid, 1, Page.DEFAULT_PAGE_SIZE);
			String datepretime=cmsTopVO.getDatepretime();
			String starttime=cmsTopVO.getStarttime();
			if(datepretime!=null&&!"".equals(datepretime)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date pretime=sdf.parse(datepretime);
				Date now=new Date();
				flag=pretime.before(now);
				if(flag){
					Date startpretime=sdf.parse(starttime);
					flag=now.before(startpretime);
				}
			}else{
				flag=false;
			}
			
			istop="1";//活动为1
			ActionContext.getContext().getValueStack().set("activityName", cmsTopVO.getName());
			pageIndex="2";
			if(null != cmsTopVO && null != cmsTopVO.getCmsSPGroupMechandiseVO()){
				pricename=cmsTopVO.getPricename();
				if(!"2".equals(cmsTopVO.getType())){		
						pricetag=cmsTopVO.getPricetag();
						
						for(int i=0;i<cmsTopVO.getCmsSPGroupMechandiseVO().size();i++){
							merchandiseList=cmsTopVO.getCmsSPGroupMechandiseVO().get(i).getsPMerchandiseVO();
							if (merchandiseList.size() < Page.DEFAULT_PAGE_SIZE) {
								haveMore = 0;
							} else {
								haveMore = 1;
							}
							
							}
						}
			}
			//ActionContext.getContext().getValueStack().set("merchandiseList", merchandiseList);
			if("2".equals(cmsTopVO.getType())){
				return "floor";
			}
			else if("3".equals(cmsTopVO.getType())){
				return "toplist2";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toplist2";
	}
	/**
	 * 加载更多
	 * 
	 * @Author wangfeng
	 * @CreatDate 2013-10-10
	 * @param
	 * @Return
	 */
	public String getMoreList() {
		AccountVO user = WebUtil.getSessionUser();
		String userId = "";
		if (null != user && null != user.getUserid()) {
			userId = user.getUserid();
		}
		try {
			if (activityId != null) {
				Pattern pattern = Pattern.compile("[0-9]*");
				boolean isNum = pattern.matcher(activityId).matches();
				if(!isNum){
					return com.opensymphony.xwork2.Action.ERROR;
				}
			}
			cmsTopVO = merchandiseService.getNewTopicProducts(activityId, gender, userId, Integer.parseInt(pageIndex), Page.DEFAULT_PAGE_SIZE);
			String datepretime=cmsTopVO.getDatepretime();
			String starttime=cmsTopVO.getStarttime();
			if(datepretime!=null&&!"".equals(datepretime)){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date pretime=sdf.parse(datepretime);
				Date now=new Date();
				flag=pretime.before(now);
				if(flag){
					Date startpretime=sdf.parse(starttime);
					flag=now.before(startpretime);
				}
			}else{
				flag=false;
			}
			
			if(null != cmsTopVO && null != cmsTopVO.getCmsSPGroupMechandiseVO()){
				pricename=cmsTopVO.getPricename();
				if(!"2".equals(cmsTopVO.getType())){	
					pricetag=cmsTopVO.getPricetag();
					merchandiseList=cmsTopVO.getCmsSPGroupMechandiseVO().get(0).getsPMerchandiseVO();
							if (merchandiseList.size() < Page.DEFAULT_PAGE_SIZE) {
								haveMore = 0;
							} else {
								haveMore = 1;
							}
						}
			}			
			pageIndex=String.valueOf((Integer.parseInt(pageIndex)+1));
		} catch (Exception e) {
			e.printStackTrace();
		}			
		return "getMoreList";
	}
	/**
	 * 进入热卖商品列表
	 * 
	 * @Author wangfeng
	 * @CreatDate 2013-8-02
	 * @param
	 * @Return
	 */
	public String getNewlist() {
		try {
			AccountVO user = WebUtil.getSessionUser();
			gender = ServletActionContext.getRequest().getParameter("gender");
			if (null == gender) {				
				if ((user != null && "1".equals(user.getGender())) || "1".equals(gender)) {
					gender = "1";
				} else {
					gender = "0";
				}
			}		
			String userid = "";
			if (null != user && null != user.getUserid()) {
				userid = user.getUserid();
			}
			List<SPMerchandiseVO> merchandiseList = null;
			merchandiseList = merchandiseService.getNewProducts(categoryid,gender, userid, 1,Page.DEFAULT_PAGE_SIZE);
			if ( null != merchandiseList) {
				if (merchandiseList.size() < Page.DEFAULT_PAGE_SIZE) {
					haveMore = 0;
				} else {
					haveMore = 1;
				}
			} else {
				haveMore = 0;
			}
			ActionContext.getContext().getValueStack().set("merchandiseList", merchandiseList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "getNewlist";
	}
	/**
	 * 尚品商品详情页面
	 * 
	 * @Return
	 */
	public String spdetail() {
		String productid = ServletActionContext.getRequest().getParameter("productid");
		if (StringUtil.isNotEmpty(productid)) {
			try {
				String topicid = ServletActionContext.getRequest().getParameter("topicid");
				AccountVO user = WebUtil.getSessionUser();
				String userid = "";
				if (null != user && null != user.getUserid()) {
					userid = user.getUserid();
				}
				SPMerchandiseVO vo = merchandiseService.spMerchandiseDetail(productid, userid, topicid);
				// 将sizeinfo保存到缓存中，key=商品id，value=sizeinfo
				if (null != vo && StringUtils.isNotEmpty(vo.getSizeinfo())) {
					SizeINFOCacheUtil.put(vo.getGoodscode(), vo.getSizeinfo());
				}
				ActionContext.getContext().getValueStack().set("merchandise", vo);
				ActionContext.getContext().getValueStack().set("activityName", activityName);
				String brandid=ServletActionContext.getRequest().getParameter("brandid");
				if(brandid!=null){
					ActionContext.getContext().getValueStack().set("brandid", brandid);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "spdetail";
	}

	/**
	 * 进入商品列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-01
	 * @param
	 * @Return
	 */
	public String list() {
		try {
			// SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// System.out.println(sdf.sformat(new Date(Long.valueOf(startTime))));
			ActivityVO activityVO = merchandiseService.activityVO(activityId, typeFlag, 1, Page.DEFAULT_PAGE_SIZE);
			AccountVO user = WebUtil.getSessionUser();
			String gender = "0";
			String userid = "";
			if (null != user && null != user.getUserid()) {
				userid = user.getUserid();
			}
			SPTopicVO spTopicVO = merchandiseService.getTopicProducts("20130201575", gender, userid, 1, Page.DEFAULT_PAGE_SIZE);
			if (activityVO.getMerchandiseList().size() < Page.DEFAULT_PAGE_SIZE) {
				haveMore = 0;
			} else {
				haveMore = 1;
			}
			openFlag = activityVO.getOpenFlag();
			startTime = activityVO.getStartTime();
			endTime = activityVO.getEndTime();
			if ("3".equals(openFlag)) {
				return "toIndex";
			}
			this.setActivityName(activityVO.getName());
			ActionContext.getContext().getValueStack().set("merchandiseList", activityVO.getMerchandiseList());			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	/**
	 * 商品详情页面
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-02
	 * @param
	 * @Return
	 */
	public String detail() {
		String categoryno = ServletActionContext.getRequest().getParameter("categoryno");
		String goodsid = ServletActionContext.getRequest().getParameter("goodsid");
		if (StringUtil.isNotEmpty(goodsid, typeFlag, categoryno)) {
			try {
				merchandiseService.merchandiseDetail(goodsid, typeFlag, categoryno);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "detail";
	}

	/**
	 * 尺码说明
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-02
	 * @param
	 * @Return
	 */
	public String sizedesc() {
		String productid = ServletActionContext.getRequest().getParameter("productid");
		// SPMerchandiseVO vo = (SPMerchandiseVO) ActionContext.getContext().getValueStack().findValue("merchandise");
		try {
			if (StringUtils.isNotEmpty(productid)) {
				String sizeinfo = SizeINFOCacheUtil.getValue(productid);
				ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
				ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
				if (StringUtils.isNotEmpty(sizeinfo)) {
					String sizeinfohead = "<html><head><meta name=\"viewport\" content=\"width=device-width, initial-scale=0.8,minimum-scale=0.4,maximum-scale=1.0\"><link href=\"http://spscc.shangpincdn.com/ResourceHandler.ashx?f=~/shangpin/Content/css/core/css&amp;v=B2468645201AD7509445F8C3DC0412C4\" rel=\"stylesheet\" type=\"text/css\" /><link href=\"http://spscc.shangpincdn.com/ResourceHandler.ashx?f=~/shangpin/Content/css/page/new_detail/css&amp;v=818953B8BF21808D57F972DA8EDF290C\" rel=\"stylesheet\" type=\"text/css\" /></head><body>";
					ServletActionContext.getResponse().getWriter().print(sizeinfohead + sizeinfo.toString() + "</body></html>");
				} else {
					ServletActionContext.getResponse().getWriter().print("暂无尺码说明");
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 加载更多
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-22
	 * @param
	 * @Return
	 */
	public String getmore() {
		try {
			ActivityVO activityVO = merchandiseService.activityVO(activityId, typeFlag, PageContext.getOffset() == 0 ? 1 : PageContext.getOffset(), Page.DEFAULT_PAGE_SIZE);
			data = new JSONObject();
			data.put("merchandiseList", JSONArray.fromObject(activityVO.getMerchandiseList()));
			data.put("haveMore", activityVO.getMerchandiseList().size() < Page.DEFAULT_PAGE_SIZE ? "0" : "1");// 1:有，0:没有
			data.put("pageIndex", PageContext.getOffset() == 0 ? 1 : PageContext.getOffset());
			data.put("code", 1);
			data.put("openFlag", activityVO.getOpenFlag());
			data.put("typeFlag", typeFlag);
			data.put("pageType", pageType);
			data.put("activityName", activityName);
			data.put("activityId", activityId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "getMore";
	}

	/**
	 * 加载更多
	 * 
	 * @param
	 * @Return
	 */
	public String getSPMore() {
		try {
			AccountVO user = WebUtil.getSessionUser();
			String gender = "0";
			String userid = "";
			if (null != user && null != user.getUserid()) {
				userid = user.getUserid();
			}
			SPTopicVO spTopicVO = merchandiseService.getTopicProducts(activityId, gender, userid, PageContext.getOffset() == 0 ? 1 : PageContext.getOffset(), Page.DEFAULT_PAGE_SIZE);
			data = new JSONObject();
			List<SPMerchandiseVO> list = spTopicVO.getSpMerchandiseList();
			data.put("merchandiseList", list == null || list.size() < 1 ? "" : JSONArray.fromObject(spTopicVO.getSpMerchandiseList()));
			String haveMoreTmp = "0";
			if (null != spTopicVO.getSpMerchandiseList()) {
				haveMoreTmp = spTopicVO.getSpMerchandiseList().size() < Page.DEFAULT_PAGE_SIZE ? "0" : "1";
			}
			data.put("haveMore", haveMoreTmp);// 1:有，0:没有
			data.put("pageIndex", PageContext.getOffset() == 0 ? 1 : PageContext.getOffset());
			data.put("code", 1);
			data.put("activityName", activityName);
			data.put("activityId", activityId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "getMore";
	}
	/**
	 * 热卖加载更多
	 * 
	 * @param
	 * @Return
	 */
	public String getSpNewProductsMore() {
		try {
			AccountVO user = WebUtil.getSessionUser();
			gender = ServletActionContext.getRequest().getParameter("gender");
			if (null == gender) {				
				if ((user != null && "1".equals(user.getGender())) || "1".equals(gender)) {
					gender = "1";
				} else {
					gender = "0";
				}
			}		
			String userid = "";
			if (null != user && null != user.getUserid()) {
				userid = user.getUserid();
			}
			merchandiseList = merchandiseService.getNewProducts(categoryid,gender,  userid, PageContext.getOffset() == 0 ? 1 : PageContext.getOffset(), Page.DEFAULT_PAGE_SIZE);
			data = new JSONObject();
			data.put("merchandiseList", merchandiseList == null || merchandiseList.size() < 1 ? "" : JSONArray.fromObject(merchandiseList));
			String haveMoreTmp = "0";
			if (null != merchandiseList) {
				haveMoreTmp = merchandiseList.size() < Page.DEFAULT_PAGE_SIZE ? "0" : "1";
			}
			data.put("haveMore", haveMoreTmp);// 1:有，0:没有
			data.put("pageIndex", PageContext.getOffset() == 0 ? 1 : PageContext.getOffset());
			data.put("code", 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "getSpNewProductsMore";
	}
	public String getTypeContent() {
		if ("2".equals(pageType)) {
			typeContent = "预售商品";
		} else if ("01".equals(pageType) || "11".equals(pageType)) {
			typeContent = "最新特卖";
		} else if ("02".equals(pageType) || "12".equals(pageType)) {
			typeContent = "限时特卖";
		} else if ("03".equals(pageType) || "13".equals(pageType)) {
			typeContent = "活动专区";
		}
		return typeContent;
	}

	public void setTypeContent(String typeContent) {
		this.typeContent = typeContent;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getActivityName() {
		String decode = "";
		try {
			if(activityName!=null&&"".equals(activityName)){
			decode = URLDecoder.decode(activityName, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return decode;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getSystemTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date(System.currentTimeMillis()));
	}

	public String getOpenFlag() {
		return openFlag;
	}

	public void setOpenFlag(String openFlag) {
		this.openFlag = openFlag;
	}

	public String getPageType() {
		return pageType;
	}

	public void setPageType(String pageType) {
		this.pageType = pageType;
	}

	public String getTypeFlag() {
		return typeFlag;
	}

	public void setTypeFlag(String typeFlag) {
		this.typeFlag = typeFlag;
	}

	public JSONObject getData() {
		return data;
	}

	public void setData(JSONObject data) {
		this.data = data;
	}

	public Integer getHaveMore() {
		return haveMore;
	}

	public void setHaveMore(Integer haveMore) {
		this.haveMore = haveMore;
	}

	public String getTopicid() {
		return topicid;
	}

	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<SPMerchandiseVO> getMerchandiseList() {
		return merchandiseList;
	}

	public void setMerchandiseList(List<SPMerchandiseVO> merchandiseList) {
		this.merchandiseList = merchandiseList;
	}

	public String getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(String categoryid) {
		this.categoryid = categoryid;
	}



	public String getChildCategoryid() {
		return childCategoryid;
	}

	public void setChildCategoryid(String childCategoryid) {
		this.childCategoryid = childCategoryid;
	}



	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getChildCategoryname() {
		return childCategoryname;
	}

	public void setChildCategoryname(String childCategoryname) {
		this.childCategoryname = childCategoryname;
	}

	public String getIstop() {
		return istop;
	}

	public void setIstop(String istop) {
		this.istop = istop;
	}
	public CmsTopVO getCmsTopVO() {
		return cmsTopVO;
	}
	public void setCmsTopVO(CmsTopVO cmsTopVO) {
		this.cmsTopVO = cmsTopVO;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public String getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}
	public String getPricename() {
		return pricename;
	}
	public void setPricename(String pricename) {
		this.pricename = pricename;
	}
	public String getCmsTitle() {
		return cmsTitle;
	}
	public void setCmsTitle(String cmsTitle) {
		this.cmsTitle = cmsTitle;
	}
	public String getPricetag() {
		return pricetag;
	}
	public void setPricetag(String pricetag) {
		this.pricetag = pricetag;
	}
	public void setMerchandiseService(MerchandiseService merchandiseService) {
		this.merchandiseService = merchandiseService;
	}
	
	
	
}
