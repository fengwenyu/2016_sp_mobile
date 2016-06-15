package com.shangpin.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shangpin.biz.bo.BuyNow;
import com.shangpin.biz.bo.PriceShowVo;
import com.shangpin.biz.bo.Province;
import com.shangpin.biz.bo.Receive;
import com.shangpin.biz.service.SPBizAddressService;
import com.shangpin.biz.service.SPBizBuyNowService;
import com.shangpin.biz.utils.StringUtil;
import com.shangpin.web.utils.Constants;

/**
 * @author qinyingchun 立即购买控制层
 */

@Controller
@RequestMapping("/buy")
public class BuyNowController extends BaseController {

    @SuppressWarnings("unused")
	private static final Logger logger = LoggerFactory.getLogger(BuyNowController.class);
    private static final String BUY_NOW = "/buy/buy_now";

    @Autowired
    private SPBizBuyNowService bizBuyNowService;
    @Autowired
    private SPBizAddressService bizAddressService;
    
    @RequestMapping(value = "/now", method = RequestMethod.GET)
    public String now(String productId, String skuId, String activityId, String amount, String region, Model model, HttpServletRequest request) {
        String userId = getUserId(request);
        //增加商品客的参数chanelNo chanelId
		HttpSession session = request.getSession();
		String channelNo = (String) session.getAttribute(Constants.THRID_TOKEN_ChannelNo);
    	String channelId = (String) session.getAttribute(Constants.THRID_TOKEN_ChannelId);
    	String spuNo = (String) session.getAttribute(Constants.THRID_TOKEN_SpuNo);
    	String topicId = (String) session.getAttribute(Constants.THRID_TOKEN_TopicId);
    	boolean isTopic = false;
    	boolean isSpu = false;
    	//校验活动id
    	if(StringUtil.isNotEmpty(topicId,channelNo,channelId)){
    		if(topicId.equals(activityId)){
    			isTopic = true;
    		}
    	}
    	//验证商品是否是那件spu商品
    	if(StringUtil.isNotEmpty(spuNo,channelNo,channelId)){
    		logger.info("productId:"+productId+"===>spuNo"+spuNo);
    		if(spuNo.equals(productId)){
    			isSpu = true;
    		}
    	}
    	BuyNow buyNow = null;
    	if(isTopic||isSpu){
    		buyNow = bizBuyNowService.buyNowObj(userId, skuId, productId, activityId, amount, region,channelNo,channelId);
    	}else{
    		buyNow = bizBuyNowService.buyNowObj(userId, skuId, productId, activityId, amount, region);
    	}
        if (null == buyNow) {
            return BUY_NOW;
        }
        // 判断收货地址
        List<Receive> addresses = buyNow.getReceive();
        if (addresses.size() == 0) {
            model.addAttribute("haveAddress", false);
        } else {
            model.addAttribute("haveAddress", true);
            model.addAttribute("address", address(addresses, buyNow.getLastReceiveId()));
        }
        List<PriceShowVo> priceShowVos = buyNow.getPriceShow();
        for(PriceShowVo priceShowVo : priceShowVos){
			if("5".equals(priceShowVo.getType())){
				model.addAttribute("ori_carriage", priceShowVo);
			}else if("6".equals(priceShowVo.getType())){
				model.addAttribute("ori_nocarriage", priceShowVo);
			}
		}
        double realPay = Double.parseDouble(buyNow.getPayAmount());
        List<Province> provinces = bizAddressService.findProvinceListObj();
        model.addAttribute("provinces", provinces);
        model.addAttribute("buyNow", buyNow);
        model.addAttribute("real_pay", realPay);
        return BUY_NOW;
    }

    private Receive address(List<Receive> addresses, String lastAddressId) {
        if (StringUtils.isEmpty(lastAddressId)) {
            return defaultAddress(addresses);
        } else {
            for (Receive address : addresses) {
                String addressId = address.getId();
                if (addressId.equals(lastAddressId)) {
                    return address;
                }
            }
            return defaultAddress(addresses);
        }
    }

    private Receive defaultAddress(List<Receive> addresses) {
        for (Receive address : addresses) {
            if ("1".equals(address.getIsd())) {
                return address;
            }
        }
        return addresses.get(0);
    }

}
