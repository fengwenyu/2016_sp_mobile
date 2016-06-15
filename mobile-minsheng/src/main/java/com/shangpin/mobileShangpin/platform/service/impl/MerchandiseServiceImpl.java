package com.shangpin.mobileShangpin.platform.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import com.opensymphony.xwork2.ActionContext;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.service.MerchandiseService;
import com.shangpin.mobileShangpin.platform.vo.ActivityVO;
import com.shangpin.mobileShangpin.platform.vo.BrandVo;
import com.shangpin.mobileShangpin.platform.vo.CmsSPGroupMechandiseVO;
import com.shangpin.mobileShangpin.platform.vo.CmsTopGroupVO;
import com.shangpin.mobileShangpin.platform.vo.CmsTopVO;
import com.shangpin.mobileShangpin.platform.vo.MerchandiseVO;
import com.shangpin.mobileShangpin.platform.vo.SPMerchandiseVO;
import com.shangpin.mobileShangpin.platform.vo.SPTopicVO;


@Service("merchandiseService")
@SuppressWarnings("unchecked")
// @Transactional
public class MerchandiseServiceImpl implements MerchandiseService {
	/**
	 * 商品列表
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-01
	 * @param activityId
	 *            活动或者专题id
	 * @param typeFlag
	 *            0：专题；1：活动
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页数据量
	 * @Return ActivityVO 活动传输对象
	 */
	public ActivityVO activityVO(String activityId, String typeFlag, Integer pageIndex, Integer pageSize) throws Exception {
		ActivityVO activityVO = new ActivityVO();
		List<MerchandiseVO> merchandiseList = new ArrayList<MerchandiseVO>();
		Map<String, String> map = new HashMap<String, String>();
		map.put("picurlw", Constants.MERCHANDISE_LIST_PICTURE_WIDTH);
		map.put("picurlh", Constants.MERCHANDISE_LIST_PICTURE_HEIGHT);
		map.put("pageindex", pageIndex == null ? "" : pageIndex + "");
		map.put("pagesize", pageSize == null ? "" : pageSize + "");
		String url = "";
		if ("1".equals(typeFlag)) {
			url = Constants.BASE_URL + "subjectproductlist/";
			map.put("subjectNo", activityId);// 20120810340
		} else if ("0".equals(typeFlag)) {
			url = Constants.BASE_URL + "GetTopic/";
			map.put("topicNo", activityId);// 20120810340
		}
		String data = null;
		try {
			// 获取活动json格式字符串
			data = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			data = null;
		}
		if (null != data && !"".equals(data)) {
			JSONObject jsonObj = JSONObject.fromObject(data);
			if (Constants.SUCCESS.equals(jsonObj.get("code")) && null != jsonObj && !"{}".equals(jsonObj.get("content").toString())) {
				JSONObject obj = (JSONObject) jsonObj.get("content");
				JSONArray array = obj.getJSONArray("list");
				activityVO.setName(obj.getString("name"));
				if (array.size() > 0) {
					// 参数1为要转换的JSONArray数据，参数2为要转换的目标数据类，即List装载的对象数据类
					merchandiseList = JSONArray.toList(array, new MerchandiseVO(), new JsonConfig());
				}
				if ("1".equals(typeFlag)) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String startTime = obj.getString("starttime");
					activityVO.setStartTime(sdf.format(new Date(Long.valueOf(startTime))));
					String endTime = obj.getString("endtime");
					activityVO.setEndTime(sdf.format(new Date(Long.valueOf(endTime))));
					// 1:开启；0:未开启；3:结束
					if (System.currentTimeMillis() >= Long.valueOf(endTime)) {
						activityVO.setOpenFlag("3");
					} else if (System.currentTimeMillis() >= Long.valueOf(startTime)) {
						activityVO.setOpenFlag("1");
					} else {
						activityVO.setOpenFlag("0");
					}
				} else if ("0".equals(typeFlag)) {
					activityVO.setOpenFlag("1");
				}
			}
		}
		activityVO.setMerchandiseList(merchandiseList);
		return activityVO;
	}

	/**
	 * 尚品专题商品列表
	 * 
	 * @param topicID
	 *            活动或者专题id
	 * @param typeFlag
	 *            0：专题；1：活动
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页数据量
	 * @Return SPTopicVO 尚品专题传输对象
	 */
	public SPTopicVO getTopicProducts(String topicID, String gender, String userid, Integer pageIndex, Integer pageSize) throws Exception {
		SPTopicVO spTopicVO = null;
		if (StringUtils.isEmpty(topicID)) {
			return spTopicVO;
		}
		// 组装访问参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("picw", Constants.MERCHANDISE_LIST_PICTURE_WIDTH);
		map.put("pich", Constants.MERCHANDISE_LIST_PICTURE_HEIGHT);
		map.put("pageindex", pageIndex == null ? "" : pageIndex + "");
		map.put("pagesize", pageSize == null ? "" : pageSize + "");
		map.put("filtersold", "0");// 包含售罄的商品
		map.put("gender", StringUtils.isEmpty(gender) ? "0" : gender);
		map.put("userid", StringUtils.isEmpty(userid) ? "" : userid);
		map.put("topicid", topicID);// 20120810340
		String url = Constants.BASE_SP_URL + "SPTopicProducts/";
		String data = null;
		try {
			// 获取尚品专题json格式字符串
			data = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			data = null;
		}
		if (null != data && !"".equals(data)) {
			JSONObject jsonObj = JSONObject.fromObject(data);
			if (Constants.SUCCESS.equals(jsonObj.get("code")) && null != jsonObj && !"{}".equals(jsonObj.get("content").toString())) {
				JSONObject obj = (JSONObject) jsonObj.get("content");
				JSONArray array = obj.getJSONArray("list");
				spTopicVO = new SPTopicVO();
				spTopicVO.setTopicid(obj.getString("topicid"));
				spTopicVO.setShareurl(obj.getString("shareurl"));
				spTopicVO.setTopicdesc(obj.getString("topicdesc"));
				if (array.size() > 0) {
					// 参数1为要转换的JSONArray数据，参数2为要转换的目标数据类，即List装载的对象数据类
					spTopicVO.setSpMerchandiseList(JSONArray.toList(array, new SPMerchandiseVO(), new JsonConfig()));
				}
			}
		}
		return spTopicVO;
	}

	/**
	 * 进入商品详情页
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-11-06
	 * @param goodsid
	 *            商品id
	 * @param typeFlag
	 *            代表来自专题还是活动
	 * @param categoryno
	 *            商品所在活动或者专题的id
	 * @Return List 商品集合
	 */
	public void merchandiseDetail(String goodsid, String typeFlag, String categoryno) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("goodsid", goodsid);
		map.put("type", typeFlag);
		map.put("categoryno", categoryno);
		map.put("picw", Constants.MERCHANDISE_DETAIL_PICTURE_WIDTH);
		map.put("pich", Constants.MERCHANDISE_DETAIL_PICTURE_HEIGHT);
		String url = Constants.BASE_URL + "GetProductDetail/";
		String data = WebUtil.readContentFromGet(url, map);
		// String data = new String(ProReader.getInstance().getProperty("data").getBytes("ISO-8859-1"), "UTF-8");
		MerchandiseVO merchandise = new MerchandiseVO();
		merchandise.json2Bean(data);
		ActionContext.getContext().getValueStack().set("merchandise", merchandise);
	}

	/**
	 * 进入尚品商品详情页
	 * 
	 * @param productid
	 *            商品id
	 * @param userid
	 *            用户id
	 * @param topicid
	 *            专题id
	 * @Return List 商品集合
	 */
	public SPMerchandiseVO spMerchandiseDetail(String productid, String userid, String topicid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("productid", productid);
		map.put("userid", userid == null ? "" : userid);
		map.put("topicid", topicid == null ? "" : topicid);
		map.put("picw", Constants.MERCHANDISE_DETAIL_PICTURE_WIDTH);
		map.put("pich", Constants.MERCHANDISE_DETAIL_PICTURE_HEIGHT);
		String url = Constants.BASE_SP_URL + "SPProductDetail/";
		String data = WebUtil.readContentFromGet(url, map);
		return new SPMerchandiseVO().json2Bean(data);
	}

	/**
	 * 根据类别获取商品列表
	 * 
	 * @param category
	 *            类别id
	 * 
	 * @param topicid
	 *            专题id
	 * @Return List 商品集合
	 */
	public List<SPMerchandiseVO> getCategoryByProducts(String category,
			String gender,String userId) throws Exception {
		List<SPMerchandiseVO> list=null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("gender", gender);
		map.put("pageIndex", "1");
		map.put("pagesize", "3");
		map.put("userid", userId);
		map.put("categoryid", category);
		map.put("filtersold", "0");
		map.put("picw", Constants.MERCHANDISE_LIST_PICTURE_WIDTH);
		map.put("pich", Constants.MERCHANDISE_LIST_PICTURE_HEIGHT);
		String url=Constants.BASE_SP_URL+"SPProductsInCateAndBrand/";
		String json = null;
		try {
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}

		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj && Constants.SUCCESS.equals(jsonObj.get("code"))) {
				if (null != jsonObj.get("content") && !"{}".equals(jsonObj.get("content").toString())) {
					JSONObject contentObj = (JSONObject) jsonObj.get("content");
					if (null != contentObj.get("list") && !"[]".equals(contentObj.get("list").toString())) {
						JSONArray array = (JSONArray) contentObj.get("list");
						list = new ArrayList<SPMerchandiseVO>();
						
						list=JSONArray.toList(array, new SPMerchandiseVO(), new JsonConfig());
					}
				}
			}
		}
		return list;
		
	}

	/**
	 * 获取新品商品列表
	 * 
	 * @param gender
	 *            性别
	 * 
	 * @param topicid
	 *            专题id
	 * @Return List 商品集合
	 */
	@Override
	public List<SPMerchandiseVO> getNewProducts(String categoryid,String gender, String userid,Integer pageIndex,
			Integer pageSize) {
		List<SPMerchandiseVO> spList=null;
		Map<String, String> map = new HashMap<String, String>();
		map.put("categoryid", categoryid);
		map.put("gender", gender);
		map.put("pageindex", pageIndex == null ? "" : pageIndex + "");
		map.put("pageSize", pageSize == null ? "" : pageSize + "");
		map.put("userid", userid);
		map.put("picw", Constants.MERCHANDISE_LIST_PICTURE_WIDTH);
		map.put("pich", Constants.MERCHANDISE_LIST_PICTURE_HEIGHT);
		String url=Constants.BASE_SP_URL+"SPNewProducts/";
		String json = null;
		try {
			json = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			json = null;
		}
		if (null != json && !"".equals(json)) {
			JSONObject jsonObj = JSONObject.fromObject(json);
			if (null != jsonObj && Constants.SUCCESS.equals(jsonObj.get("code"))) {
				if (null != jsonObj.get("content") && !"{}".equals(jsonObj.get("content").toString())) {
					JSONObject contentObj = (JSONObject) jsonObj.get("content");
					if (null != contentObj.get("list") && !"[]".equals(contentObj.get("list").toString())) {
						JSONArray array = (JSONArray) contentObj.get("list");
						if (array.size() > 0) {
							spList=new ArrayList<SPMerchandiseVO>();
							// 参数1为要转换的JSONArray数据，参数2为要转换的目标数据类，即List装载的对象数据类
							spList=JSONArray.toList(array, new SPMerchandiseVO(), new JsonConfig());
						}
						}
					}
				}
			}
		return spList;
	}
	/**
	 * 尚品新专题商品列表
	 * 
	 * @param topicID
	 *            活动或者专题id
	 * @param typeFlag
	 *            0：专题；1：活动
	 * @param pageIndex
	 *            页码
	 * @param pageSize
	 *            每页数据量
	 * @Return SPTopicVO 尚品专题传输对象
	 */
	public CmsTopVO getNewTopicProducts(String topicID, String gender, String userid, Integer pageIndex, Integer pageSize) throws Exception {
		CmsTopVO cmsTopVO = null;
		if (StringUtils.isEmpty(topicID)) {
			return cmsTopVO;
		}
		// 组装访问参数
		Map<String, String> map = new HashMap<String, String>();
		map.put("topicid", topicID);// 20120810340
		map.put("userid", StringUtils.isEmpty(userid) ? "" : userid);
		map.put("gender", StringUtils.isEmpty(gender) ? "0" : gender);
		map.put("pich", Constants.MERCHANDISE_LIST_PICTURE_HEIGHT);
		map.put("picw", Constants.MERCHANDISE_LIST_PICTURE_WIDTH);
		map.put("pageindex", pageIndex == null ? "" : pageIndex + "");
		map.put("pagesize", pageSize == null ? "" : pageSize + "");
		map.put("filtersold", "0");// 包含售罄的商品

		String url = Constants.BASE_SP_URL + "SPNewTopicProducts/";
		String data = null;
		try {
			// 获取尚品专题json格式字符串
			data = WebUtil.readContentFromGet(url, map);
		} catch (Exception e) {
			e.printStackTrace();
			data = null;
		}
		if (null != data && !"".equals(data)) {
			JSONObject jsonObj = JSONObject.fromObject(data);
			if (Constants.SUCCESS.equals(jsonObj.get("code")) && null != jsonObj && !"{}".equals(jsonObj.get("content").toString())) {
				JSONObject obj = (JSONObject) jsonObj.get("content");
				cmsTopVO=new CmsTopVO();
				cmsTopVO.setTopicid(obj.getString("topicid"));
				cmsTopVO.setShareurl(obj.getString("shareurl"));
				cmsTopVO.setName(obj.getString("name"));
				cmsTopVO.setSubtitle(obj.getString("subtitle"));
				cmsTopVO.setTopicdesc(obj.getString("topicdesc"));
				cmsTopVO.setO1type(obj.getString("o1type"));
				cmsTopVO.setO1code(obj.getString("o1code"));
				cmsTopVO.setO1logo(obj.getString("o1logo"));
				cmsTopVO.setO1url(obj.getString("o1url"));
				cmsTopVO.setHeadPic(obj.getString("headPic"));
				cmsTopVO.setStatus(obj.getString("status"));
				cmsTopVO.setStarttime(obj.getString("starttime"));
				cmsTopVO.setEndtime(obj.getString("endtime"));
				cmsTopVO.setType(obj.getString("type"));
				cmsTopVO.setPricename(obj.getString("pricename"));
				cmsTopVO.setPricetag(obj.getString("pricetag"));
				cmsTopVO.setDatepretime(obj.getString("datepretime"));
				JSONArray brandarray=obj.getJSONArray("brands");
				if(brandarray.size()>0){
					JSONObject brandobj=brandarray.getJSONObject(0);
					BrandVo brandVo=new BrandVo();
					brandVo.setName(brandobj.getString("name"));
					brandVo.setId(brandobj.getString("id"));
					brandVo.setChname(brandobj.getString("chname"));
					cmsTopVO.setBrandVO(brandVo);			
				}
				JSONArray array = obj.getJSONArray("list");
				if(array.size()>0){
					List<CmsSPGroupMechandiseVO> CmsSPGroupMechandiseVOList=new ArrayList<CmsSPGroupMechandiseVO>();
					for(int i=0;i<array.size();i++){
						JSONObject groupproductobj=array.getJSONObject(i);
						CmsSPGroupMechandiseVO cmsSPGroupMechandiseVO=new CmsSPGroupMechandiseVO();
						CmsTopGroupVO cmsTopGroupVO=new CmsTopGroupVO();
						cmsTopGroupVO.setGrouplogo(groupproductobj.getString("grouplogo"));
						cmsTopGroupVO.setGroupname(groupproductobj.getString("groupname"));
						cmsTopGroupVO.setGrouporder(groupproductobj.getString("grouporder"));
						cmsTopGroupVO.setGroupurl(groupproductobj.getString("groupurl"));
						cmsSPGroupMechandiseVO.setGmsTopGroupVO(cmsTopGroupVO);
						JSONArray productarray=groupproductobj.getJSONArray("products");
						cmsSPGroupMechandiseVO.setsPMerchandiseVO(JSONArray.toList(productarray, new SPMerchandiseVO(), new JsonConfig()));
						CmsSPGroupMechandiseVOList.add(cmsSPGroupMechandiseVO);
					}
					cmsTopVO.setCmsSPGroupMechandiseVO(CmsSPGroupMechandiseVOList);
				}
			}else{
				if(!jsonObj.get("msg").equals("")){
					cmsTopVO = new CmsTopVO();
					cmsTopVO.setMsg((String)jsonObj.get("msg"));
				}
			}
		}
		return cmsTopVO;
	}

}
