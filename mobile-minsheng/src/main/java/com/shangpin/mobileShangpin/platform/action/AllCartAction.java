package com.shangpin.mobileShangpin.platform.action;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;
import com.shangpin.mobileShangpin.common.util.StringUtil;
import com.shangpin.mobileShangpin.common.util.WebUtil;
import com.shangpin.mobileShangpin.platform.service.AllCartService;
import com.shangpin.mobileShangpin.platform.vo.AccountVO;
import com.shangpin.mobileShangpin.platform.vo.ProductServerAllCartVO;


/**
 * 新购物车action
 * 
 * @Author wangfeng
 * @CreatDate 2014-07-02
 */
@Controller
@ParentPackage("mobileShangpin")
@Scope("prototype")
@Actions({ @Action(value = ("/allcartaction"), results = {//
        @Result(name = "delCart", type = "json", params = { "root", "entityJson" }),//
		@Result(name = "listCart", location = "/WEB-INF/pages/order/allcart.jsp"),
		@Result(name = "noneCart", location = "/WEB-INF/pages/order/allcart_noresult.jsp"), 
		@Result(name = "loginUI", type = "redirect", location = "accountaction!loginui"), 
		@Result(name = "back", type = "json", params = { "root", "entityJson" }),//
		@Result(name = "updateCart", type = "json", params = { "root", "entityJson" }),//
}) })
public class AllCartAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6241749617103279391L;
	@Autowired
	private AllCartService allCartService;
	private ProductServerAllCartVO productServerAllCartVO;
	private String userId;
	private String skuNo;
	private String pich;
	private String picw;
	private String shopType;
	private String isPromotion;//0为促销，1为不参加促销
	private String shopDetailId;
	private JSONObject entityJson;
	private String quantity;//商品数量
	private String productNo;
	private String categoryNo;
	

	

	/**
	 * 跳转到购物袋列表
	 * 
	 * @return
	 */
	public String listCart() {
	    AccountVO user = WebUtil.getSessionUser();
        if (null != user && null != user.getUserid()) {
            userId = user.getUserid();
        }else{
            return "loginUI";
        }
        if (pich==null||"".equals(pich)) {
            pich="160";
        }
        if(picw==null||"".equals(picw)){
            picw="120";
        }
        if(isPromotion==null||"".equals(isPromotion)){
            isPromotion="1";
        }if(shopType==null||"".equals(shopType)){
            shopType="1";
        }
	    productServerAllCartVO=allCartService.cartProducts(userId,pich,picw,shopType,isPromotion);
	    if(productServerAllCartVO.getProductAllCartVO()==null){
            return "noneCart";
        }
		return "listCart";
	}

	/**
     * 删除购物车商品
     * 
     * @return
     */
    public String delCart() {
        AccountVO user = WebUtil.getSessionUser();
        if (null != user && null != user.getUserid()) {
            userId = user.getUserid();
        }else{
            return "loginUI";
        }    
        entityJson=allCartService.delPrdoucts(userId,shopDetailId);

        return "delCart";
    }


    /**
     * 修改购物车商品数量
     * 
     * @return
     */
    public String updateCartCount() {
        AccountVO user = WebUtil.getSessionUser();
        if (null != user && null != user.getUserid()) {
            userId = user.getUserid();
        }else{
            return "loginUI";
        }        
        entityJson=allCartService.updateCartCount(userId,shopDetailId,quantity);
        return "updateCart";
    }

	/**
     * 加入购物袋
     * 
     * @return
     */
    
    public String addCart() {
        AccountVO user = WebUtil.getSessionUser();      
        entityJson = new JSONObject();
        if (null == user || !StringUtil.isNotEmpty(user.getUserid())) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("productNo", productNo);
            map.put("quantity", quantity);
            map.put("skuNo", skuNo);
            map.put("dynamicattributetext", "");
            if(categoryNo==null||"".equals(categoryNo)){
                map.put("categoryNo", "0");
                map.put("topicSubjectFlag", "1");
            }else{  
                map.put("categoryNo", categoryNo);
                map.put("topicSubjectFlag", "2");
            }
            map.put("skuFrom", "3");
            map.put("vipNo", "0");
            map.put("siteNo", "1");
            ServletActionContext.getRequest().getSession().setAttribute("map", map);
            entityJson.put("code", "1");
            entityJson.put("loginFrom", "1");
            return "back";
        }
        try {
            String data = allCartService.addCart(user.getUserid(),productNo,quantity,skuNo,categoryNo);
            JSONObject obj = JSONObject.fromObject(data);
            String code = obj.getString("code");
            if (!"0".equals(code)) {
                entityJson.put("code", "2");
                entityJson.put("msg", obj.getString("msg"));
            } else {
                entityJson.put("code", "3");// 跳转至购物车列表页面
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("entityJson:-----------" + entityJson);
        return "back";
    }
    public ProductServerAllCartVO getProductServerAllCartVO() {
        return productServerAllCartVO;
    }




    public void setProductServerAllCartVO(ProductServerAllCartVO productServerAllCartVO) {
        this.productServerAllCartVO = productServerAllCartVO;
    }




    public String getUserId() {
        return userId;
    }




    public void setUserId(String userId) {
        this.userId = userId;
    }




    public String getPich() {
        return pich;
    }




    public void setPich(String pich) {
        this.pich = pich;
    }




    public String getPicw() {
        return picw;
    }




    public void setPicw(String picw) {
        this.picw = picw;
    }




    public String getShopType() {
        return shopType;
    }




    public void setShopType(String shopType) {
        this.shopType = shopType;
    }




    public String getIsPromotion() {
        return isPromotion;
    }




    public void setIsPromotion(String isPromotion) {
        this.isPromotion = isPromotion;
    }

    public String getShopDetailId() {
        return shopDetailId;
    }

    public void setShopDetailId(String shopDetailId) {
        this.shopDetailId = shopDetailId;
    }

    public JSONObject getEntityJson() {
        return entityJson;
    }

    public void setEntityJson(JSONObject entityJson) {
        this.entityJson = entityJson;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getSkuNo() {
        return skuNo;
    }

    public void setSkuNo(String skuNo) {
        this.skuNo = skuNo;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo;
    }





	
}
