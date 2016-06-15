package com.shangpin.base.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.utils.BaseDataUtil;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.base.vo.Area;
import com.shangpin.base.vo.Brand;
import com.shangpin.base.vo.Category;
import com.shangpin.base.vo.City;
import com.shangpin.base.vo.ConsigneeAddress;
import com.shangpin.base.vo.Province;
import com.shangpin.base.vo.ResultObjList;
import com.shangpin.base.vo.SearchResult;
import com.shangpin.utils.HttpClientUtil;
import com.shangpin.utils.JsonUtil;
import com.shangpin.utils.StringUtil;

@Service
public class CommonServiceImpl implements CommonService {
	
	// 登录请求的URL
	private StringBuilder loginURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("login");

	// 注册请求的URL
	private StringBuilder registerURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("register");

	// 第三方登陆的URL
	private StringBuilder thirdLoginURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("thirdlogin");

	// 找回密码 URL
	private StringBuilder sendRetrievePwdEmailURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("sendretrievepwdemail");

	// 收藏商品URL
	private StringBuilder addProductToCollectURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("AddProductToCollect");

	// 查询收藏商品URL
	private StringBuilder selectCollectListURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("SelectCollectList");

	// 获取新增发票地址URL
	private StringBuilder addInvoiceAddressURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("addinvoiceaddress");

	// 获取删除发票地址的URL
	private StringBuilder deleteInvoiceAddressURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("delinvoiceaddress");

	// 获取编辑发票地址的URL
	private StringBuilder modifyInvoiceAddressURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("editinvoiceaddress");

	// 获取省级行政区信息URL
	private StringBuilder findProvinceListURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("provincelist");

	// 获取城市行政区信息URL
	private StringBuilder findCityListURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("citylist");

	// 获取地区行政区信息URL
	private StringBuilder findAreaListURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("arealist");
	//获取区下面的街道
	private StringBuilder findTownListURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("townlist");

	// 获取会场内容数据URL
	private StringBuilder findHallContentURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("getVenue");

	// 获取二级分类商品URL
	private StringBuilder findSecondLevelURL = new StringBuilder(GlobalConstants.BASE_URL_SEARCH).append("CategoryProductList");
	// 获取轮播图新品，商城
	private StringBuilder galleryURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("gallery");
	// 根据图片编号获取图片地址
	private StringBuilder getPicURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("GetPicUrlsByGet");

	// 获取收藏品牌列表URL
    private StringBuilder collectedBrandlistURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/collectedBrandlist");
  
    // 获取收藏商品列表URL
    private StringBuilder collectedProductListURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/collectedProductList");
    //推荐品牌
    private StringBuilder categoryCommendBrandlistURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/categoryCommendBrand");
    
   
    //收藏品牌URL
    private StringBuilder collectBrandURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/collectBrand");
  
    //取消收藏品牌URL
    private StringBuilder cancelCollectBrandURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/cancelCollectBrand");

    //取得用户信息
    private StringBuilder userInfoURL = new StringBuilder(GlobalConstants.BASE_URL_AOLAI_AOLAI).append("getuserinfo");

    //页面无内容时获得推荐
    private StringBuilder recProductURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/recProduct");
    
    // 为你推荐 
    private StringBuilder recProductForURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/popularityAndWorth");
    
    // 根据手机号查找用户接口
    private StringBuilder checkUserURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("listingcatalog/checkUser");
    // 支付日志写入接口
	private StringBuilder addPayLogURL= new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("order/AddPayLog");
	
	// 判断手机号是否为尚品用户接口
    private StringBuilder checkPhoneUserURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("checkPhoneUser");
    
    //批量查询用户是否注册接口
    private StringBuilder checkUserListURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/checkUserList");
    //女神活动验证
    private StringBuilder checkPhoneActivityURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("checkIsLuck");
    //查询是否参与活动
    private StringBuilder isCheckPhoneActivityURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("checkIsExistPhone");
    // 登录后在个人中心修改密码或修改礼品卡支付密码
 	private StringBuilder modifyPasswordURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("modifyPassword");
 	
 	private StringBuilder searchKeywordURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("ListingCatalog/searchKeyword");
 	
    /**
     * 获取用户信息
     */
    @Override
    public String findUserInfo(String userId) {
    	HashMap<String, String> params = new HashMap<String, String>();
    	params.put("userid", userId);
    	return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "getuserinfo", params, userInfoURL.toString());
    }
    
    /**
     * 品类推荐品牌
     * @param categoryId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public String categoryCommendBrandList(String categoryId, String pageIndex, String pageSize){
    	HashMap<String, String> params = new HashMap<String, String>();
		params.put("categoryId", categoryId);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		return HttpClientUtil.doGet(categoryCommendBrandlistURL.toString(), params);
    }
	/**
	 * 获取图片url
	 * @param picNOs
	 * 			图片编号
	 * @param picType
	 * 			图片类型: product
	 * @return
	 */
	public String queryPicUrl(String picNOs, String picType) {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("picnos", picNOs);
		params.put("picw", "{w}");
		params.put("pich", "{h}");
		params.put("pictype", picType);
		return HttpClientUtil.doGet(getPicURL.toString(), params);
	}
	
	public String queryPicUrl(String picNo, String width, String height, String picType){
		Map<String, String> params = new HashMap<String, String>();
		params.put("picnos", picNo);
		params.put("picw", width);
		params.put("pich", height);
		params.put("pictype", picType);
		return HttpClientUtil.doGet(getPicURL.toString(), params);
	}
	
	

	/**
	 * 登录
	 */
	@Override
	public String login(String userName, String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("username", userName);
		params.put("password", password);
		String result = HttpClientUtil.doPost(loginURL.toString(), params);
		return result;
	}

	/**
	 * 注册
	 */
	@Override
	public String register(String email, String phonenum, String password, String gender, String smscode, String type, String invitecode) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("mailaddress", email);
		params.put("phonenum", phonenum);
		params.put("password", password);
		params.put("gender", gender);
		params.put("smscode", smscode);
		params.put("type", type);
		params.put("invitecode", invitecode);
		String result = HttpClientUtil.doPost(registerURL.toString(), params);
		return result;
	}
	@Override
	public String register(String email, String phonenum, String password, String gender, String smscode, String type, String invitecode,String channelNo,String channelId,String param,String channelType) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("mailaddress", email);
		params.put("phonenum", phonenum);
		params.put("password", password);
		params.put("gender", gender);
		params.put("smscode", smscode);
		params.put("type", type);
		params.put("invitecode", invitecode);
		params.put("channelNo", channelNo);
		params.put("channelId", channelId);
		params.put("param", param);
		params.put("channelType", channelType);
		String result = HttpClientUtil.doPost(registerURL.toString(), params);
		return result;
	}

	/**
	 * 第三方登陆
	 */
	@Override
	public String thirdLogin(String mode, String inviteCode, String uid, String sex, String nickname, String trueName) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("mode", mode);
		params.put("invitecode", inviteCode);
		params.put("uid", uid);
		params.put("sex", sex);
		params.put("nickname", nickname);
		params.put("trueName", trueName);
		String result = HttpClientUtil.doPost(thirdLoginURL.toString(), params);
		return result;
	}

	/**
	 * 找回密码（尚品、奥莱共用）
	 */
	@Override
	public String forgotPassword(String email) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("mailaddress", email);
		String result = HttpClientUtil.doGet(sendRetrievePwdEmailURL.toString(), params);
		return result;
	}

	/**
	 * 收藏商品接口（尚品）
	 */
	@Override
	public String addProductToCollect(String userId, String shopType, String sku, String count, String picw, String pich, String detailPicw, String detailPich) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("shoptype", shopType);
		params.put("sku", sku);
		params.put("count", count);
		params.put("picw", picw);
		params.put("pich", pich);
		params.put("detailpich", detailPich);
		params.put("detailpicw", detailPicw);
		String result = HttpClientUtil.doGet(addProductToCollectURL.toString(), params);
		return result;
	}

	/**
	 * 查询收藏商品接口（尚品）
	 */
	@Override
	public String findCollectList(String userId, String shopType, String picw, String pich, String detailPicw, String detailPich) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("shoptype", shopType);
		params.put("picw", picw);
		params.put("pich", pich);
		params.put("detailpich", detailPich);
		params.put("detailpicw", detailPicw);
		String result = HttpClientUtil.doGet(selectCollectListURL.toString(), params);
		return result;
	}

	/**
	 * 新增发票地址（尚品、奥莱完全相同）
	 * 
	 * @author liujie
	 */
	@Override
	public String addInvoiceAddress(ConsigneeAddress consiqneeAddressVO) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", consiqneeAddressVO.getUserId());
		params.put("consigneename", consiqneeAddressVO.getConsigneeName());
		params.put("province", consiqneeAddressVO.getProvince());
		params.put("city", consiqneeAddressVO.getCity());
		params.put("area", consiqneeAddressVO.getArea());
		params.put("town", consiqneeAddressVO.getTown());
		params.put("address", consiqneeAddressVO.getAddress());
		params.put("postcode", consiqneeAddressVO.getPostcode());
		params.put("tel", consiqneeAddressVO.getTel());
		String result = HttpClientUtil.doPost(addInvoiceAddressURL.toString(), params);
		return result;
	}

	/**
	 * 删除发票地址（尚品、奥莱完全相同）
	 * 
	 * @author liujie
	 */
	@Override
	public String deleteInvoiceAddress(String userId, String addrId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("addrid", addrId);
		params.put("userid", userId);
		String result = HttpClientUtil.doGet(deleteInvoiceAddressURL.toString(), params);
		return result;
	}

	/**
	 * 修改发票地址（尚品、奥莱完全相同）
	 * 
	 * @author liujie
	 */
	@Override
	public String modifyInvoiceAddress(ConsigneeAddress consiqneeAddressVO) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", consiqneeAddressVO.getUserId());
		params.put("addrid", consiqneeAddressVO.getAddrId());
		params.put("consigneename", consiqneeAddressVO.getConsigneeName());
		params.put("province", consiqneeAddressVO.getProvince());
		params.put("city", consiqneeAddressVO.getCity());
		params.put("area", consiqneeAddressVO.getArea());
		params.put("address", consiqneeAddressVO.getAddress());
		params.put("postcode", consiqneeAddressVO.getPostcode());
		params.put("tel", consiqneeAddressVO.getTel());
		String result = HttpClientUtil.doPost(modifyInvoiceAddressURL.toString(), params);
		return result;
	}

	// 获取省级行政区信息
	@Override
	public String findProvinceList() {
		Map<String, String> params = new HashMap<String, String>();
		String result = BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "provincelist", params, findProvinceListURL.toString());
		return result;
	}

	// 获取城市行政区信息
	@Override
	public String findCityList(String Id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", Id);
		String result = BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "citylist", params, findCityListURL.toString());
		return result;
	}

	// 获取地区行政区信息
	@Override
	public String findAreaList(String Id) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", Id);
		String result = BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "arealist", params, findAreaListURL.toString());
		return result;
	}
	
	//获取县镇行政区信息
	@Override
	public String findTownList(String areaId){
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", areaId);
		String result = BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "townlist", params, findTownListURL.toString());
		return result;
	}

	// 获取会场内容数据URL
	@Override
	public String findHallContent(String Id,String isChange,String type) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", Id);
		params.put("isChange", isChange);
		params.put("type", type);
	    return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "getVenue", params, findHallContentURL.toString());
	    
	}

	@Override
	public String findCategoryList(String categoryId, String start, String end) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("categoryNO", categoryId);
		params.put("start", start);
		params.put("end", end);
		String result = HttpClientUtil.doGet(findSecondLevelURL.toString(), params);
		return result;
	}

	/**
	 * 获得省份
	 */
	@Override
	public List<Province> findProvinceListObj() {

		String result = findProvinceList();
		ResultObjList<Province> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjList<Province>>() {
		});
		return obj.getList();
	}

	/**
	 * 根据省份id获得市
	 */
	@Override
	public List<City> findCityListObj(String provinceId) {
		String result = findCityList(provinceId);
		ResultObjList<City> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjList<City>>() {
		});
		return obj.getList();
	}

	/**
	 * 根据市ID得到所在的区域
	 */
	@Override
	public List<Area> findAreaListObj(String cityId) {
		String result = findAreaList(cityId);
		ResultObjList<Area> obj = JsonUtil.fromJson(result, new TypeToken<ResultObjList<Area>>() {
		});
		return obj.getList();
	}

	/**
	 * 尚品二级分类商品入口方法
	 */
	@Override
	public List<Category> getSecondCategoryList(String categoryId, String gender, String postArea) {
		List<Category> dataList = new ArrayList<Category>();
		try {
			SearchResult searchResult = new SearchServiceImpl().searchCategoryProductList(null, null,null, null, null, null, null, categoryId, null, null,postArea);
			List<Category> categoryList = searchResult.getCategoryList();
			if(categoryList != null && categoryList.size() > 0){
				for(Category category : categoryList){
					String id = category.getId();
					if(id.length() == 6){
						dataList.add(category);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}

	/**
	 * 尚品品牌集合入口
	 */
	@Override
	public List<Brand> getCategoryBrandList(String categoryId, String gender, String postArea) {
		List<Brand> dataList = new ArrayList<Brand>();
		try {
			SearchResult searchResult = new SearchServiceImpl().searchCategoryProductList(null, null,null, null, null, null, null, categoryId, null, null, postArea);
			List<Brand> brandList = searchResult.getBrandList();
			if(brandList != null && brandList.size() > 0){
				for(Brand brand : brandList){
					brand.setChname(brand.getNameCN());
					dataList.add(brand);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dataList;
	}
	/**
	 * 
	 * @param type
	 *            1：新品顶部轮播图; 2：商城顶部轮播图
	 * 
	 * @return
	 */
	public String queryGalleryList(String type) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("type", type);
		String result = HttpClientUtil.doPost(galleryURL.toString(), params);
		return result;
	}

	@Override
	public String collectedBrandlistObj(String userId,String pageIndex, String pageSize){
		  Map<String, String> params = new HashMap<String, String>();
	      params.put("userId", userId);
	      params.put("pageIndex", pageIndex);
	      params.put("pageSize", pageSize);
	      String result = HttpClientUtil.doGet(collectedBrandlistURL.toString(), params);
	      return result;
	}

	@Override
	public String collectedProductListObj(String userId, String shopType,
			String pageIndex, String pageSize,String postArea) {
		   Map<String, String> params = new HashMap<String, String>();
		   params.put("userId", userId);
		   params.put("shopType", shopType);
		   params.put("pageIndex", pageIndex);
		   params.put("pageSize", pageSize);
		   params.put("postArea", postArea);
		  String result = HttpClientUtil.doGet(collectedProductListURL.toString(), params);
		  return result;
	}
	
	@Override
	public String collectBrandObj(String userId, String brandId) {
		 	Map<String, String> params = new HashMap<String, String>();
		 	params.put("userId", userId);
		 	params.put("brandId", brandId);
		 	String result = HttpClientUtil.doGet(collectBrandURL.toString(), params);
		 	return result;
	}

	@Override
	public String cancelCollectBrandObj(String userId, String brandId) {
		Map<String, String> params = new HashMap<String, String>();
	 	params.put("userId", userId);
	 	params.put("brandId", brandId);
	 	String result = HttpClientUtil.doGet(cancelCollectBrandURL.toString(), params);
	 	return result;
	}

	@Override
	public String addInvoiceAddress(String userId, String name,
			String proviceId, String cityId, String areaId, String townId,
			String addr, String postCode, String tel) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("consigneename", name);
		params.put("province", proviceId);
		params.put("city", cityId);
		params.put("area", areaId);
		params.put("town", townId);
		params.put("address", addr);
		params.put("postcode", postCode);
		params.put("tel", tel);
		return HttpClientUtil.doPost(addInvoiceAddressURL.toString(), params);
	}

	@Override
	public String modifyInvoiceAddress(String invoiceId, String userId,
			String name, String proviceId, String cityId, String areaId,
			String townId, String addr, String postCode, String tel) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userid", userId);
		params.put("addrid", invoiceId);
		params.put("consigneename", name);
		params.put("province", proviceId);
		params.put("city", cityId);
		params.put("area", areaId);
		params.put("town", townId);
		params.put("address", addr);
		params.put("postcode", postCode);
		params.put("tel", tel);
		return HttpClientUtil.doPost(modifyInvoiceAddressURL.toString(), params);
	}
	 /**
     * 通过手机号查找用户是否存在，可直接创造一个新的用户
     * 
     * @param phone
     *            手机号码
     * @param type
     *            是否需要创建新用户（0:只查找是否存在；1：查找是否存在用户，不存在创建一个新的用户）
     * @return
     * @author wangfeng
     */
    @Override
    public String checkUser(String phone, String type) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone", phone);
        params.put("type", type);
        String result = HttpClientUtil.doGet(checkUserURL.toString(), params);
        return result;
    }

	@Override
	public String recProduct(String userId, String type, String shopType, String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("type", type);
		params.put("shopType", shopType);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
	    return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "recProduct", params, recProductURL.toString());
	}
	
	@Override
	public String recProduct(String userId, String type, String shopType, String productId, String pageIndex, String pageSize) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("type", type);
		params.put("shopType", shopType);
		params.put("pageIndex", pageIndex);
		params.put("pageSize", pageSize);
		params.put("productId", productId);
		return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "recProduct", params, recProductURL.toString());
	}
	
	@Override
    public String recProduct(String type, String userId, String vuId, String coord, String ip, String pageIndex, String pageSize) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("type", type);
        if(!StringUtil.isEmpty(userId)){
            params.put("userId", userId);
        }
        if(!StringUtil.isEmpty(vuId)){
            params.put("vuId", vuId);
        }
        
        if(!StringUtil.isEmpty(coord)){
            params.put("coord", coord);
        }
        params.put("ip", ip);
        params.put("pageIndex", pageIndex);
        params.put("pageSize", pageSize);
        return BaseDataUtil.findData(GlobalConstants.CACHE_BASE, "recProduct", params, recProductForURL.toString());
    }

	@Override
	public String addPayLog(String orderId, String payInfo, String payType) {
		Map<String, String> params = new HashMap<String, String>();
	 	params.put("mainOrderNo", orderId);
	 	params.put("payInfo", payInfo);
	 	params.put("payType", payType);
	 	String result = HttpClientUtil.doGet(addPayLogURL.toString(), params);
	 	return result;
	}

	@Override
	public String checkUser(String phone, String type, String source) {
		  Map<String, String> params = new HashMap<String, String>();
	        params.put("phone", phone);
	        params.put("type", type);
	        params.put("source", source);
	        String result = HttpClientUtil.doGet(checkUserURL.toString(), params);
	        return result;
	}

	@Override
	public String checkPhoneUser(String phoneNum, String channelNo) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("phoneNum", phoneNum);
		params.put("channelNo", channelNo);
		return HttpClientUtil.doGet(checkPhoneUserURL.toString(), params);
	}

    @Override
    public String checkUserList(String phones) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("phones", phones);
        return HttpClientUtil.doGet(checkUserListURL.toString(), params);
    }

	@Override
	public String checkPhoneActivity(String phoneNum, String activityNo) {
		Map<String, String> params = new HashMap<String, String>();
	    params.put("phone", phoneNum);
	    params.put("activityId", activityNo);
		return HttpClientUtil.doGet(checkPhoneActivityURL.toString(), params);
	}

	@Override
	public String isCheckPhoneActivity(String phoneNum, String activityNo) {
		Map<String, String> params = new HashMap<String, String>();
	    params.put("phone", phoneNum);
	    params.put("activityId", activityNo);
		return HttpClientUtil.doGet(isCheckPhoneActivityURL.toString(), params);
	}

	@Override
	public String fromModifyPassword(String userId, String type, String nowPassword, String newPassword, String confirmPassword) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("type", type);
		params.put("nowPassword", nowPassword);
		params.put("newPassword", newPassword);
		params.put("confirmPassword", confirmPassword);
		return HttpClientUtil.doGet(modifyPasswordURL.toString(), params);
	}

	@Override
	public String querySearchKeyword() {
		return HttpClientUtil.doGet(searchKeywordURL.toString(), null);
	}

    @Override
    public String getShangpinDomain() {
        return GlobalConstants.BASE_URL_SHANGPIN_M;
    }

}
