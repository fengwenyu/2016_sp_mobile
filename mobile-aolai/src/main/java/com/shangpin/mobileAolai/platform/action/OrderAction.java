package com.shangpin.mobileAolai.platform.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.shangpin.mobileAolai.common.util.BaseAction;
import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.CookieUtil;
import com.shangpin.mobileAolai.common.util.DataContainerPool;
import com.shangpin.mobileAolai.common.util.FileUtil;
import com.shangpin.mobileAolai.common.util.ProductUtil;
import com.shangpin.mobileAolai.common.util.StringUtil;
import com.shangpin.mobileAolai.common.util.SysContent;
import com.shangpin.mobileAolai.common.util.WebUtil;
import com.shangpin.mobileAolai.platform.service.CouponCodeService;
import com.shangpin.mobileAolai.platform.service.CouponService;
import com.shangpin.mobileAolai.platform.service.OrderService;
import com.shangpin.mobileAolai.platform.vo.AccountVO;
import com.shangpin.mobileAolai.platform.vo.ConsigneeAddressVO;
import com.shangpin.mobileAolai.platform.vo.CouponVO;
import com.shangpin.mobileAolai.platform.vo.DeliveryVO;
import com.shangpin.mobileAolai.platform.vo.MerchandiseVO;
import com.shangpin.mobileAolai.platform.vo.OrderVO;
import com.shangpin.mobileAolai.platform.vo.PayVO;
import com.shangpin.mobileAolai.platform.vo.ProductsPromotionVO;
import com.shangpin.mobileAolai.platform.vo.ProvinceVO;


/**
 * 订单管理Action
 * 
 * @Author yumeng
 * @CreatDate 2012-11-2
 */
@Controller
@ParentPackage("mobileAolai")
@Scope("prototype")
@SuppressWarnings("unchecked")
@Actions({ @Action(value = ("/orderaction"), results = { 
	@Result(name = "order", location = "/WEB-INF/pages/order/order.jsp"), 
	@Result(name = "ajaxOrderList", type = "json", params = { "root", "entityJson" }), 
	@Result(name = "cancelorder", type = "json", params = { "root", "entityJson" }), 
	@Result(name = "orderDetail", location = "/WEB-INF/pages/order/orderdetail.jsp"), 
	@Result(name = "notifysuccess", location = "/WEB-INF/pages/order/paysuccess.jsp"),
	@Result(name = "orderList", location = "/WEB-INF/pages/order/orderlist.jsp"), 
	@Result(name = "updateConfirmOrderInfo", type = "json", params = { "root", "entityJson" }),//
	@Result(name = "login", type = "redirect", location = "accountaction!loginui?loginFrom=2&ch=${ch}"), 
	@Result(name = "save", type = "json", params = { "root", "entityJson" }), 
	@Result(name = "success", location = "/WEB-INF/pages/order/success.jsp"),
	@Result(name = "chagePayMode", type = "json", params = { "root", "entityJson" }),
	@Result(name = "getCouponCodeInfo", type = "json", params = { "root", "entityJson" }),
	@Result(name = "payment", location = "/WEB-INF/pages/order/payment.jsp"),
	@Result(name = "failure", type = "redirect", params = { "msg", "${msg}" }, location = "orderaction!order"),
	@Result(name = "cart", type = "redirect", params = { "msg", "${msg}" }, location = "allcartaction!listCart")
// @Result(name = "failureToCart", type = "redirect", location =
// "cartaction!showcart")
}) })
public class OrderAction extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6271510975548384066L;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CouponService couponService;
	@Autowired
	private CouponCodeService couponCodeService;
	/** 商品列表 */
	private List<MerchandiseVO> merchandiseList;
	/** 支付方式列表 */
	private List<PayVO> payList;
	/** 配送方式列表 */
	private List<DeliveryVO> deliveryList;
	/** 省级数据列表 */
	private List<ProvinceVO> provinceList;
	/** 收货人地址列表 */
	private List<ConsigneeAddressVO> consigneeAddressList;
	/** 发票地址列表 */
	private List<ConsigneeAddressVO> invoiceaddrsList;
	/** 订单列表 */
	private List<OrderVO> orderList;
	/** 优惠券列表 */
	private List<CouponVO> couponList;
	/** 实体数据转json */
	private JSONObject entityJson;
	/** 订单前台展示对象 */
	private OrderVO orderVO;
	/** 订单类型 */
	private String statusType;
	/** 信息提示 */
	private String msg;
	private String ch;
	@SuppressWarnings("unused")
	private String platform;
	private HttpServletRequest request=WebUtil.getRequest();
	private String accept;
	boolean flag = true;//是否显示优惠码
	private String shopDetailId;
	/** 新商品列表 */
    private List<ProductsPromotionVO> productsPromotionVOList;
	/**
	 * 跳转订单生成页面
	 * 
	 * @return
	 */
	public String order() {
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "login";
		}
		boolean isFlag=orderService.getCheckShopId(user.getUserid(),shopDetailId,"2");
		if(isFlag){
    		Map<String, Object> res = orderService.getPreparedOrder(user.getUserid(),"2");
    		// orderVO = orderService.getPreparedOrder(user.getUserid());
    		String code = res.get("code").toString();
    		if (!Constants.SUCCESS.equals(code)) {
    			msg = res.get("msg").toString();
    			return "cart";
    		}
    		orderVO = (OrderVO) res.get("orderVO");
    		consigneeAddressList = (List<ConsigneeAddressVO>) res.get("consigneeAddressList");
            // 获取用户购物袋中商品列表
            // merchandiseList = cartService
            // .getCartOfMerchandiseList(user.getUserid());
            
            productsPromotionVOList = (List<ProductsPromotionVO>) res.get("merchandiseList");
            // 获取发票列表
            invoiceaddrsList = (List<ConsigneeAddressVO>) res.get("invoiceaddrsList");
            // 获取省级数据
            provinceList = (List<ProvinceVO>) DataContainerPool.sysContainer.get("provinceList");
            
            //payList = (List<PayVO>) DataContainerPool.sysContainer.get("payList");
            // 配送支付方式
            deliveryList = (List<DeliveryVO>) DataContainerPool.sysContainer.get("deliveryList");
            // 优惠券列表
            couponList = (List<CouponVO>) res.get("couponList");
    		//判断订单是否含有配置文件中的商品，如果有不可以使用优惠劵
            //checkCouponList();
            //ActionContext.getContext().getValueStack().set("flag", flag);//如果为false则不显示优惠码
		}
		return "order";
	}

	@SuppressWarnings("unused")
	private void checkCouponList() {
        JSONArray array = (JSONArray) DataContainerPool.productCouponContainer.get("productCoupon");
        if(array == null || array.size() == 0){
            ProductUtil.loadProductOfCoupon();
            array = (JSONArray) DataContainerPool.productCouponContainer.get("productCoupon");
        }
        if(array != null && array.size() > 0){
            
            for(int i =0 ; i < array.size(); i++){
                if(!flag){
                    break;
                }
                JSONObject coupon = (JSONObject) array.get(i);
                String productId = coupon.getString("id");
                String brand = coupon.getString("brandName");
                if(merchandiseList != null && merchandiseList.size() > 0){
                    for(MerchandiseVO merchandiseVO : merchandiseList){
                        String googsId = merchandiseVO.getGoodsid();
                        String brandNo = merchandiseVO.getBrand();
                        
                        //控制品牌
                        if(StringUtil.isNotEmpty(brandNo, brand) && brand.equals(brandNo)){
                            couponList = new JSONArray();
                            flag = false;
                            break;
                        }
                        
                        //控制商品
                        if(StringUtil.isNotEmpty(productId, googsId) && productId.equals(googsId)){
                            couponList = new JSONArray();
                            flag = false;
                            break;
                        }
                    }
                }
                
            }
        }
    }
	/**
	 * 跳转到订单详情页面
	 * 
	 * @return
	 */
	public String orderdetail() {
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "login";
		}
		if (null == orderVO || StringUtils.isEmpty(orderVO.getOrderid())) {
			return "orderList";
		}
		orderVO = orderService.getOrderDetail(user.getUserid(), orderVO.getOrderid());
		return "orderDetail";
	}

	/**
	 * 跳转到订单列表页面
	 * 
	 * @return
	 */
	public String orderlist() {
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "login";
		}
		if (StringUtils.isEmpty(statusType))
			statusType = "2";
		entityJson = orderService.getOrderInfoOfJson(user.getUserid(), statusType);
		if(entityJson.isEmpty()){
			entityJson.put("count", "0");
		}
		if(entityJson != null){
			JSONArray list = entityJson.getJSONObject("content").getJSONArray("list");
			if(list != null && list.size() > 0){
				entityJson.put("count", "1");
			}else{
				entityJson.put("count", "0");
			}
		}
		return "orderList";
	}

	/**
	 * 获取Json格式的订单数据列表
	 * 
	 * @return
	 */
	public String ajaxorderlist() {
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "login";
		}
		entityJson = orderService.getOrderInfoOfJson(user.getUserid(), statusType);
		if(entityJson.isEmpty()){
			entityJson.put("count", "0");
		}
		if(entityJson != null){
			JSONArray list = entityJson.getJSONObject("content").getJSONArray("list");
			if(list != null && list.size() > 0){
				entityJson.put("count", "1");
			}else{
				entityJson.put("count", "0");
			}
		}
		return "ajaxOrderList";
	}

	/**
	 * 保存订单
	 * 
	 * @return
	 */
	public String saveorder() {
		String ch = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(ch)) {
			if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
				ch = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
			}else{
				ch=StringUtils.isEmpty(ch)?Constants.AOLAI_WAP_DEFAULT_CHANNELNO:ch;
			}
			
		}
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "login";
		}
		orderVO.setUserid(user.getUserid());
		orderVO = orderService.addOrder(orderVO);
		entityJson = new JSONObject();
		String carriage="";
		String payamount="";
		if (!StringUtils.isEmpty(orderVO.getOrderid())) {
			entityJson.put("msgcode", "success");
			entityJson.put("orderid", orderVO.getOrderid());
			if(orderVO.getCarriage()==null||"".equals(orderVO.getCarriage())){
				carriage="0";
			}else{
				carriage=orderVO.getCarriage();
			}
			payamount=String.valueOf(Integer.parseInt(orderVO.getAmount())+Integer.parseInt(carriage));
			entityJson.put("amount", payamount);
		//	entityJson.put("paytypeid", orderVO.getPaytypeid());
		} else if ("54c2b76f".equals(orderVO.getCode()) || // 该商品已售磬，请删除本商品
				"6194cca8".equals(orderVO.getCode()) || // 无可结算商品
				"f6390f2e".equals(orderVO.getCode()) || // 该商品库存小于您的购买数量。如继续提交则可以购买库存剩余数量的商品；如放弃购买请删除本商品
				"b2c267e0".equals(orderVO.getCode()) || // 商品不属于奥莱
				"a00b3c5a".equals(orderVO.getCode()) || // 库存数量小于购买数量
				"2bc1c5c3".equals(orderVO.getCode()) || // 商品所属专题已过期
				"ac46383e".equals(orderVO.getCode()) || // 购物袋中有商品发生变化，请检查
				"9912c033".equals(orderVO.getCode()) || // 订单信息不正确
				"9f98c417".equals(orderVO.getCode())) {// 购物袋中有商品发生变化，请检查
			entityJson.put("msgcode", "cart");
		} else {
			entityJson.put("msgcode", "");
		}
		entityJson.put("msg", orderVO.getMsg());
		
		if(Integer.parseInt(orderVO.getAmount())==0 && Integer.parseInt(carriage) == 0){ //使用优惠券等支付金额为0时的处理
			final String payTypeId = "0";
			final String payTypeChildId = "0";
			
			HashMap<String, String> reqmap = new HashMap<String, String>();
			reqmap.put("mainorderNo", orderVO.getOrderid());
			reqmap.put("payTypeId", payTypeId);
			reqmap.put("childPayTypeId", payTypeChildId);
//			reqmap.put("orderAmount", StringUtil.cutOffDecimal(total_fee.toString()));
			reqmap.put("orderAmount", Constants.PAY_AMOUNT);
			String url = Constants.BASE_NEWAPI_URL_SP + "order/UpdateOrderStatus";
//			Map<String, String> reqmap = new HashMap<String, String>();
//			reqmap.put("orderno", orderVO.getOrderid());
//			reqmap.put("paytypeId", payTypeId);
//			reqmap.put("paytypechildId", payTypeChildId);
//			String url = Constants.BASE_URL + "payconfirmpayment/";
			try {
				String data = WebUtil.readContentFromGet(url, reqmap);
				JSONObject content = JSONObject.fromObject(data);
				final String code = content.getString("code");
				if ("0".equals(code)) {
					// 记录访问日志
					FileUtil.addLog(request, 
							"saveorder", 
							ch, 
							"orderid",orderVO.getOrderid(),
							"payid", payTypeId, 
							"paychildid", payTypeChildId, 
							"code", "success");
				} else {
					FileUtil.addLog(request, 
							"saveorder", 
							ch,  
							"orderid", orderVO.getOrderid(), 
							"payid", payTypeId, "paychildid", 
							payTypeChildId, 
							"code", "fail");
					
				}
				entityJson.put("msgcode", "notifysuccess");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "save";
	}

	/**
	 * 保存订单成功
	 * 
	 * @return
	 */
	public String success() {
		//获取accept用于js判断是否是UC浏览器
		accept=request.getHeader("Accept");
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "login";
		}
		String payresult=SysContent.getRequest().getParameter("payresult");
		if(payresult!=null&&!"".equals(payresult)){
			String orderid=SysContent.getRequest().getParameter("orderid");
			SysContent.getRequest().setAttribute("orderid", orderid);
			return "notifysuccess";
		}
		return "success";
	}
	
	/**
	 * 跳转到选择支付方式页
	 * 
	 * @return
	 */
	public String payment(){
		ch = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(ch)) {
			if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.CHANNEL_PARAM_NAME) != null) {
				ch = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.CHANNEL_PARAM_NAME).getValue();
			}else{
				ch=StringUtils.isEmpty(ch)?Constants.AOLAI_WAP_DEFAULT_CHANNELNO:ch;
			}
			
		}
		//获取accept用于js判断是否是UC浏览器
		accept=request.getHeader("Accept");
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "login";
		}
		payList = orderService.getPayment(ch);
		//跳转到支付选择页
		return "payment";
		
	}
	
	/**
	 * 点击支付后更新支付方式
	 * 
	 * @return
	 */
	public String chagePayMode() {

		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "login";
		}
		
		String orderid=request.getParameter("orderid");
		String mainpaymode=request.getParameter("paytypeid");
		String subpaymode=request.getParameter("paytypechildid");
		Map<String, Object> res = new HashMap<String, Object>();
		res = orderService.chagePayMode(user.getUserid(),orderid,mainpaymode,subpaymode);
		entityJson = new JSONObject();
		if(res.get("msgcode").equals("success")){
			entityJson.put("msgcode", res.get("msgcode"));
		}else{
			entityJson.put("msgcode", res.get("msgcode"));
			entityJson.put("msg",res.get("msg"));
		}
		
		return "chagePayMode";
	}
	/**
	 * 取消订单
	 * 
	 * @return
	 */
	public String cancelorder() {
		AccountVO user = WebUtil.getSessionUser();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "login";
		}
		if (null == orderVO || StringUtils.isEmpty(orderVO.getOrderid())) {
			return "orderList";
		}
		entityJson = orderService.cancelOrder(user.getUserid(), orderVO.getOrderid());
		return "cancelorder";
	}

	/**
	 * 确认订单信息时更改地址和优惠码/券内容
	 * 
	 * @return
	 */
	public String updateConfirmOrderInfo() {
		AccountVO user = WebUtil.getSessionUser();
		Map<String, Object> res = new HashMap<String, Object>();
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "login";
		}
		res = couponCodeService.updateConfirmOrderInfo(user.getUserid(), orderVO.getCoupon(),orderVO.getCouponflag(),orderVO.getBuysids().replace(",", "|"),orderVO.getAddrid());
		entityJson = new JSONObject();
		if(res.get("msgcode").equals("success")){
			entityJson.put("msgcode", res.get("msgcode"));
			entityJson.put("discountamount", res.get("discountamount"));
			entityJson.put("couponcode", res.get("couponcode"));
			entityJson.put("payamount", res.get("payamount"));
			entityJson.put("carriage", res.get("carriage"));
		}else{
			entityJson.put("msgcode", res.get("msgcode"));
			entityJson.put("msg",res.get("msg"));
		}
		
		return "updateConfirmOrderInfo";
	}
	/**
	 * 验证优惠码
	 * 
	 * @return
	 */
	public String getCouponCodeInfo() {
		AccountVO user = WebUtil.getSessionUser();
		Map<String, Object> res = new HashMap<String, Object>();
		String couponcode=ServletActionContext.getRequest().getParameter("couponcode");
		if (null == user || !StringUtil.isNotEmpty(user.getName())) {
			return "login";
		}
		res = couponCodeService.getCouponCodeInfo(user.getUserid(), couponcode,orderVO.getBuysids());
		entityJson = new JSONObject();
		if(res.get("msgcode").equals("success")){
			entityJson.put("msgcode", res.get("msgcode"));
			entityJson.put("discountamount", res.get("discountamount"));
			entityJson.put("couponcode", res.get("couponcode"));
			entityJson.put("payamount", res.get("payamount"));
		}else{
			entityJson.put("msgcode", res.get("msgcode"));
			entityJson.put("msg",res.get("msg"));
		}
		
		return "getCouponCodeInfo";
	}
	
	public List<ConsigneeAddressVO> getConsigneeAddressList() {
		return consigneeAddressList;
	}

	public void setConsigneeAddressList(List<ConsigneeAddressVO> consigneeAddressList) {
		this.consigneeAddressList = consigneeAddressList;
	}

	public List<ConsigneeAddressVO> getInvoiceaddrsList() {
		return invoiceaddrsList;
	}

	public void setInvoiceaddrsList(List<ConsigneeAddressVO> invoiceaddrsList) {
		this.invoiceaddrsList = invoiceaddrsList;
	}

	public JSONObject getEntityJson() {
		return entityJson;
	}

	public void setEntityJson(JSONObject entityJson) {
		this.entityJson = entityJson;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<MerchandiseVO> getMerchandiseList() {
		return merchandiseList;
	}

	public void setMerchandiseList(List<MerchandiseVO> merchandiseList) {
		this.merchandiseList = merchandiseList;
	}

	public List<PayVO> getPayList() {
		return payList;
	}

	public void setPayList(List<PayVO> payList) {
		this.payList = payList;
	}

	public List<DeliveryVO> getDeliveryList() {
		return deliveryList;
	}

	public void setDeliveryList(List<DeliveryVO> deliveryList) {
		this.deliveryList = deliveryList;
	}

	public List<OrderVO> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderVO> orderList) {
		this.orderList = orderList;
	}

	public OrderVO getOrderVO() {
		return orderVO;
	}

	public void setOrderVO(OrderVO orderVO) {
		this.orderVO = orderVO;
	}

	public List<ProvinceVO> getProvinceList() {
		return provinceList;
	}

	public void setProvinceList(List<ProvinceVO> provinceList) {
		this.provinceList = provinceList;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public List<CouponVO> getCouponList() {
		return couponList;
	}

	public void setCouponList(List<CouponVO> couponList) {
		this.couponList = couponList;
	}

	public String getCh() {
		ch = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(ch)) {
			if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
				ch = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
			}else{
				ch=StringUtils.isEmpty(ch)?Constants.AOLAI_WAP_DEFAULT_CHANNELNO:ch;
			}
			
		}
		return ch;
	}

	public void setCh(String ch) {
		ch = SysContent.getRequest().getParameter(Constants.CHANNEL_PARAM_NAME);
		if (StringUtils.isEmpty(ch)) {
			if (CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME) != null) {
				ch = CookieUtil.getCookieByName(SysContent.getRequest(), Constants.COOKIE_CHANNEL_PARAM_NAME).getValue();
			}else{
				ch=StringUtils.isEmpty(ch)?Constants.AOLAI_WAP_DEFAULT_CHANNELNO:ch;
			}
			
		}
		this.ch = ch;
	}

	public String getPlatform() {
		Object platform = SysContent.getSession().getAttribute("platform");
		if(platform != null) {
			return (String) platform;
		}
		return "";
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getAccept() {
		
		return accept;
	}

	public void setAccept(String accept) {
	
		this.accept = accept;
	}

    public void setCouponCodeService(CouponCodeService couponCodeService) {
        this.couponCodeService = couponCodeService;
    }

    public String getShopDetailId() {
        return shopDetailId;
    }

    public void setShopDetailId(String shopDetailId) {
        this.shopDetailId = shopDetailId;
    }

    public List<ProductsPromotionVO> getProductsPromotionVOList() {
        return productsPromotionVOList;
    }

    public void setProductsPromotionVOList(List<ProductsPromotionVO> productsPromotionVOList) {
        this.productsPromotionVOList = productsPromotionVOList;
    }
	
	
}
