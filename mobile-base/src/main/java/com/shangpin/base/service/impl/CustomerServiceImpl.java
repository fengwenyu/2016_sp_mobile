package com.shangpin.base.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shangpin.base.service.CustomerService;
import com.shangpin.base.utils.GlobalConstants;
import com.shangpin.base.vo.ConsigneeAddress;
import com.shangpin.utils.HttpClientUtil;

@Service
public class CustomerServiceImpl implements CustomerService {

    // 获取个人信息请求的URL
    private StringBuilder getUserInfoURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("getuserinfo");
    
    // 获取设定默认收货地址的URL
    private StringBuilder modifyDefaultConsigneeAddressURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("setDefaultConsigneeAddress");

    // 新增收货地址请求的URL
    private StringBuilder addConsigneeAddressURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("AddConsigneeAddress");

    // 删除收货地址请求的URL
    private StringBuilder delConsigneeAddressURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("DelConsigneeAddress");

    // 编辑收货地址请求的URL
    private StringBuilder editConsigneeAddressURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("EditConsigneeAddress");

    // 查询收货地址请求的URL
    private StringBuilder getConsigneeAddressURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("getconsigneeaddress");

    // 获取设定根据地址id获取收货地址信息的URL
    private StringBuilder findConsigneeAddressByIdURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("getConsigneeAddressById");

    // 发送手机验证码的URL
    private StringBuilder sendVerifyCodeURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("SendRegMobileVerifyCode");

    // 验证手机号的URL
    private StringBuilder verifyPhoneCodeURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("verifyphoneandcode");

    // 用户手机号与验证码匹配成功后的操作URL
    private StringBuilder verifySuccessURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("codemactchedphone");

    // 根据用户名或手机号查找用户URL
    private StringBuilder checkUserNameURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("checkUsername");

    // 确认收货的URL
    private StringBuilder confirmOrderGoodsURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("ConfirmOrderGoods");
    
    //修改密码
    private StringBuilder changePasswordURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_SHANGPIN).append("resetLoginPasswrod");
 
    //修改个人信息
    private StringBuilder modifyUserInfoURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("modifyUserInfo");
    
    // 获取个人信息请求的URL
    private StringBuilder SPgetUserInfoURL = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("getUserInfo");
    
    //修改个人头像
    private StringBuilder SPgetUserInfoURLIcon = new StringBuilder(GlobalConstants.BASE_URL_SHANGPIN_AOLAI).append("modifyUserInfoIcon");

    //TODO
    //获取我的尺寸
    private StringBuilder getMyTagliaURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("Account/getUserSizeByUserId");
    
    //修改我的尺寸
    private StringBuilder modifyMyTagliaURL = new StringBuilder(GlobalConstants.BASE_URL_TRADE).append("Account/UpdateUserSizeByUserId");
    
    /**
     * 设定根据地址id获取收货地址信息（尚品、奥莱完全相同）
     * 
     * @author liujie
     */
    @Override
    public String findConsigneeAddressById(String userId, String addrId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("addrid", addrId);
        params.put("userid", userId);
        String result = HttpClientUtil.doGet(findConsigneeAddressByIdURL.toString(), params);
        return result;
    }

    /**
     * 设定默认收货地址（尚品、奥莱完全相同）
     * 
     * @author liujie
     */
    @Override
    public String modifyDefaultConsigneeAddress(String userId, String addrId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("addrid", addrId);
        params.put("userid", userId);
        String result = HttpClientUtil.doGet(modifyDefaultConsigneeAddressURL.toString(), params);
        return result;
    }

    /**
     * 获取个人信息
     */
    @Override
    public String findUserInfo(String userId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", userId);
        String result = HttpClientUtil.doGet(getUserInfoURL.toString(), params);
        return result;
    }

    /**
     * 新增收货地址
     */
    @Override
    public String addConsigneeAddress(ConsigneeAddress consigneeAddressVO) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", consigneeAddressVO.getUserId());
        params.put("consigneename", consigneeAddressVO.getConsigneeName());
        params.put("province", consigneeAddressVO.getProvince());
        params.put("city", consigneeAddressVO.getCity());
        params.put("area", consigneeAddressVO.getArea());
        params.put("town", consigneeAddressVO.getTown());
        params.put("address", consigneeAddressVO.getAddress());
        params.put("postcode", consigneeAddressVO.getPostcode());
        params.put("tel", consigneeAddressVO.getTel());
        params.put("cardID", consigneeAddressVO.getCardID());
        params.put("setd", consigneeAddressVO.getSetd());
        String result = HttpClientUtil.doPost(addConsigneeAddressURL.toString(), params);
        return result;
    }

    /**
     * 删除收货地址
     */
    @Override
    public String deleteConsigneeAddress(String userId, String addrId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", userId);
        params.put("addrid", addrId);
        String result = HttpClientUtil.doGet(delConsigneeAddressURL.toString(), params);
        return result;
    }

    /**
     * 编辑收货地址
     */
    @Override
    public String modifyConsigneeAddress(ConsigneeAddress consigneeAddressVO) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", consigneeAddressVO.getUserId());
        params.put("addrid", consigneeAddressVO.getAddrId());
        params.put("consigneename", consigneeAddressVO.getConsigneeName());
        params.put("province", consigneeAddressVO.getProvince());
        params.put("city", consigneeAddressVO.getCity());
        params.put("area", consigneeAddressVO.getArea());
        params.put("address", consigneeAddressVO.getAddress());
        params.put("town", consigneeAddressVO.getTown());
        params.put("postcode", consigneeAddressVO.getPostcode());
        params.put("tel", consigneeAddressVO.getTel());
        params.put("cardID", consigneeAddressVO.getCardID());
        params.put("setd", consigneeAddressVO.getSetd());
        String result = HttpClientUtil.doPost(editConsigneeAddressURL.toString(), params);
        return result;
    }

    /**
     * 查询收货地址
     */
    @Override
    public String findConsigneeAddress(String userId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", userId);
        String result = HttpClientUtil.doGet(getConsigneeAddressURL.toString(), params);
        return result;
    }
    /**
     * 查询收货地址
     */
    @Override
    public String findConsigneeAddress(String userId,String isInvoice) {
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("userid", userId);
    	params.put("isinvoice", isInvoice);
    	String result = HttpClientUtil.doGet(getConsigneeAddressURL.toString(), params);
    	return result;
    }

    /**
     * 根据用户名或手机号查找用户
     */
    @Override
    public String findUserByUserName(String userName) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", userName);
        String result = HttpClientUtil.doGet(checkUserNameURL.toString(), params);
        return result;
    }
    
    @Override
    public String findUserByUserId(String userId){
        Map<String, String> params = new HashMap<String, String>();
        params.put("username", userId);
        params.put("type", "1");
        String result = HttpClientUtil.doGet(checkUserNameURL.toString(), params);
        return result;
    }

    /**
     * 发送手机验证码
     */
    @Override
    public String sendVerifyCode(String userId, String phoneNum, String msgTemplate) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", userId);
        params.put("phonenum", phoneNum);
        params.put("msgtemplate", msgTemplate);
        String result = HttpClientUtil.doGet(sendVerifyCodeURL.toString(), params);
        return result;
    }

    /**
     * 验证手机号
     */
    @Override
    public String verifyPhoneCode(String userId, String phoneNum, String verifyCode) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", userId);
        params.put("phonenum", phoneNum);
        params.put("verifycode", verifyCode);
        String result = HttpClientUtil.doGet(verifyPhoneCodeURL.toString(), params);
        return result;
    }

    /**
     * 用户手机号与验证码匹配成功后的操作
     * 
     * @param userId
     *            用户ID
     * @param type
     *            操作类型: bindPhone 绑定手机； couponReceive优惠券领取
     * @param typeValue
     *            操作类型对应的值:分别对应手机号和优惠劵充值码
     */
    @Override
    public String verifySuccess(String userId, String type, String typeValue) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", userId);
        String baseType = null;
        if ("bindPhone".equals(type)) {
            baseType = "bind:" + typeValue;
        } else if ("couponReceive".equals(type)) {
            baseType = "coupon:" + typeValue;
        }
        params.put("type", baseType);
        String result = HttpClientUtil.doGet(verifySuccessURL.toString(), params);
        return result;
    }
    
    @Override
    public String bindToUser(String userId,String typeInfo){
    	 Map<String, String> params = new HashMap<String, String>();
         params.put("userid", userId);
         params.put("type", typeInfo);
         String result = HttpClientUtil.doGet(verifySuccessURL.toString(), params);
         return result;
    }
    // 确认收货
    @Override
    public String confirmOrderGoods(String userId, String orderNo) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", userId);
        params.put("orderNo", orderNo);
        String result = HttpClientUtil.doGet(confirmOrderGoodsURL.toString(), params);
        return result;
    }
    @Override
	public String changePassword(String userid,String password) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("password", password);
		params.put("userid", userid);
		String result = HttpClientUtil.doGet(changePasswordURL.toString(), params);
		return result;
	}
@Override
    public String modifyUserInfo(String userId, Map<String, String> params) {
        /*String explainUrl = "";
        if(modifyUserInfoURL.indexOf("http://www20.tradeapiliantiao.com/")>-1){
            explainUrl = "http://192.168.20.77/mshangpin/help/associator_introduce.html";
        }else{
            explainUrl = "http://m.shangpin.com/help/associator_introduce.html";
        }*/
        params.put("userId", userId);
       /* params.put("explainUrl", explainUrl);*/
        
        String result = HttpClientUtil.doGet(modifyUserInfoURL.toString(), params);
        return result;
    }

    @Override
    public String getUserInfo(String userId) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", userId);
        String result = HttpClientUtil.doGet(SPgetUserInfoURL.toString(), params);
        return result; 
    }

	@Override
	public String modifyUserInfoIcon(String userId, String picno) {
		Map<String, String> params = new HashMap<String, String>();
        params.put("userid", userId);
        params.put("picno", picno);
        String result = HttpClientUtil.doGet(SPgetUserInfoURLIcon.toString(), params);
        return result; 
	}
	@Override
	public String getMyTaglia(String userId, String os) {
		Map<String, String> params = new HashMap<>();
		params.put("userid", userId);
		params.put("os", os);
		String result = HttpClientUtil.doGet(getMyTagliaURL.toString(), params);
		return result;
	}
	
	@Override
	public String modifyMyTaglia(String userId, String modifyData, String os) {
		Map<String, String> params = new HashMap<>();
		params.put("userid", userId);
		params.put("modifyData", modifyData);
		params.put("os", os);
		String result = HttpClientUtil.doGet(modifyMyTagliaURL.toString(), params);
		return result;
	}
}
