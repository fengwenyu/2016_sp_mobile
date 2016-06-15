package com.shangpin.base.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shangpin.base.service.AoLaiService;
import com.shangpin.base.utils.BaseDataUtil;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.base.vo.ListOfGoods;
import com.shangpin.utils.HttpClientUtil;

@Service
public class AoLaiServiceImpl implements AoLaiService {

	// 奥莱按起止时间获取活动展示URL
	private StringBuilder subjectListURL = new StringBuilder(GlobalConstants.BASE_URL_AOLAI_AOLAI).append("SubjectList");
	
	// 奥莱iPhone用专题URL
	private StringBuilder topicListURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("TopicList");

	// 奥莱专题商品列表页面URL
	private StringBuilder getTopicURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("GetTopic");

	// 奥莱活动商品列表页URL
	private StringBuilder subjectProductListURL = new StringBuilder(GlobalConstants.BASE_URL_AOLAI_AOLAI).append("subjectproductlist");

	// 获取CMS尚品专题商品列表
	private StringBuilder findChannelActivityURL = new StringBuilder(GlobalConstants.BASE_URL_AOLAI_AOLAI).append("aolaiChannelActivity");

	// 奥莱Mobile网站用专题
	private StringBuilder findTopicListURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("mobileTopicList");

	// 奥莱商品详细页面
	private StringBuilder getProductDetailURL = new StringBuilder(GlobalConstants.BASE_URL_AOLAI_AOLAI).append("GetProductDetail");

	// 奥莱获取所有未结束和今天即将开始的活动URL
	private StringBuilder selectAllSubjectListAndTodayURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("SelectAllSubjectListAndToday");

	// 奥莱获取所有即将结束的活动URL
	private StringBuilder selectEndingSubjectListURL = new StringBuilder(GlobalConstants.BASE_URL_AOLAI_AOLAI).append("SelectEndingSubjectList");

	// 奥莱获取频道请求URL
	private StringBuilder findChannelURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("getChannel");

	// 活动分组时间
	private StringBuilder groupTimeURL = new StringBuilder(GlobalConstants.BASE_URL_AOLAI_AOLAI).append("QueryGroupTime");

	// 奥莱首页信息
	private StringBuilder aoLaiIndexURL = new StringBuilder(GlobalConstants.BASE_URL_AOLAI_AOLAI).append("aolaiIndex");

	// 奥莱首页信息
	private StringBuilder aoLaiADListURL = new StringBuilder(GlobalConstants.BASE_URL_AOLAI_AOLAI).append("queryADList");
	// 活动商品列表
    private StringBuilder querySubjectProductURL = new StringBuilder(GlobalConstants.BASE_URL_AOLAI_AOLAI).append("querySubjectProductList");
    //奥莱活动列表筛选尺码列表
    private StringBuilder queryCategorySizeListURL = new StringBuilder(GlobalConstants.BASE_URL_AOLAI_AOLAI).append("queryCategorySizeList");
    
    //查询用户购买信息
    private StringBuilder findUserBuyInfoURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("userbuyinfo");
    
	@Override
	public String findAoLaiADList(String channelNo, String picw, String pich) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("channelNo", channelNo);
		params.put("picw", picw);
		params.put("pich", pich);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "aolaiIndex", params, aoLaiADListURL.toString());
	}

	public String findAolaiIndex(String type, String picw, String pich, String pageIndex, String pageSize) throws Exception {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		params.put("picw", picw);
		params.put("pich", pich);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "aolaiIndex", params, aoLaiIndexURL.toString());
	}

	/**
	 * 奥莱按起止时间获取活动展示
	 */
	@Override
	public String findSubjectListByPeriod(String gender, String startTime, String endTime, String picw, String pich) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("gender", gender);
		params.put("starttime", startTime);
		params.put("endtime", endTime);
		params.put("picw", picw);
		params.put("pich", pich);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "SubjectList", params, subjectListURL.toString());
	}
    /**
     * 奥莱商品详细页面
     */
    @Override
    public String findProductDetail(String goodId, String type, String categoryNo, String picw, String pich) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("goodsid", goodId);
        params.put("type", type);
        params.put("categoryno", categoryNo);
        params.put("picw", picw);
        params.put("pich", pich);
        return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "GetProductDetail", params, getProductDetailURL.toString());
    }
	/**
	 * 活动分组时间
	 * 
	 * @return
	 */
	public String findGroupTime(String picw, String pich) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("picw", picw);
		params.put("pich", pich);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "QueryGroupTime", params, groupTimeURL.toString());
	}

	/**
	 * 奥莱活动商品列表页
	 */
	@Override
	public String findSubjectProductList(String subjectNo, String picw, String pich, String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("subjectNo", subjectNo);
		params.put("picurlw", picw);
		params.put("picurlh", pich);
		params.put("pageindex", pageIndex);
		params.put("pagesize", pageSize);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "subjectproductlist", params, subjectProductListURL.toString());
	}

	/**
	 * 奥莱iPhone用专题
	 */
	@Override
	public String findTopicListForiPhone(String gender, String picw, String pich) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("gender", gender);
		params.put("picw", picw);
		params.put("pich", pich);
		String result = HttpClientUtil.doGet(topicListURL.toString(), params);
		return result;
	}

	/**
	 * 奥莱专题商品列表页面
	 */
	@Override
	public String findTopicList(String topicId, String picw, String pich, String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("topicid", topicId);
		params.put("picurlw", picw);
		params.put("picurlh", pich);
		params.put("pageindex", pageIndex);
		params.put("pagesize", pageSize);
		String result = HttpClientUtil.doGet(getTopicURL.toString(), params);
		return result;
	}

	/**
	 * 奥莱获取所有未结束和今天即将开始的活动
	 */
	@Override
	public String findEndingSubjectList(String gender, String picw, String pich) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("gender", gender);
		params.put("picw", picw);
		params.put("pich", pich);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "SelectEndingSubjectList", params, selectEndingSubjectListURL.toString());
	}

	/**
	 * 奥莱获取频道中活动列表
	 * 
	 * @author liujie
	 */
	@Override
	public String findChannelActivity(ListOfGoods listOfGoodsVO) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("chid", listOfGoodsVO.getChId());
		params.put("picw", listOfGoodsVO.getPicw());
		params.put("pagesize", listOfGoodsVO.getPageSize());
		params.put("pageindex", listOfGoodsVO.getPageIndex());
		params.put("pich", listOfGoodsVO.getPich());
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "aolaiChannelActivity", params, findChannelActivityURL.toString());
	}


	/**
	 * 奥莱获取所有未结束和今天即将开始的活动
	 */
	@Override
	public String findTodaySubjectList(String gender, String picw, String pich) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("gender", gender);
		params.put("picw", picw);
		params.put("pich", pich);
		String result = HttpClientUtil.doGet(selectAllSubjectListAndTodayURL.toString(), params);
		return result;
	}

	/**
	 * 奥莱Mobile网站用专题
	 * 
	 * @author liujie
	 */
	@Override
	public String findMobileTopicList(ListOfGoods listOfGoodsVO) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("gender", listOfGoodsVO.getGender());
		params.put("picw", listOfGoodsVO.getPicw());
		params.put("pich", listOfGoodsVO.getPich());
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "mobileTopicList", params, findTopicListURL.toString());
	}

	// 奥莱获取频道
	@Override
	public String findChannel() {
		return HttpClientUtil.doGet(findChannelURL.toString(), null);
	}
	/**
     *活动商品列表
     * 
     * @author wangfeng
     */
    @Override
    public String querySubjectProductList(String sizeNo,String pageIndex,String pageSize,String subjectNo,String isHaveStock,String pich,String picw,String orderType,String categoryNo) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("sizeNo", sizeNo);
        params.put("pageSize", pageSize);
        params.put("pageIndex", pageIndex);
        params.put("subjectNo", subjectNo);
        params.put("isHaveStock", isHaveStock);
        params.put("pich", pich);
        params.put("picw", picw);
        params.put("orderType", orderType);
        params.put("categoryNo", categoryNo);
        return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "querySubjectProductList", params, querySubjectProductURL.toString());
    }

    /**
     * 奥莱活动列表筛选尺码列表
     * 
     * @return
     * @author wangfeng
     */
    @Override
    public String queryCategorySizeList(String subjectNo) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("subjectNo", subjectNo);
        return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "queryCategorySizeList", params, queryCategorySizeListURL.toString());
    }

    /**
     * 查询用户购买信息
     * @author zhanghongwei
     */
    @Override
    public String findUserBuyInfo(String userId, String shopType) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", userId);
        params.put("shoptype", shopType);
        String result = HttpClientUtil.doGet(findUserBuyInfoURL.toString(), params);
        return result;
    }
}
