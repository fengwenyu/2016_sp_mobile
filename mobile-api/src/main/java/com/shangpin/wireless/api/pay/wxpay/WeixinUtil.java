package com.shangpin.wireless.api.pay.wxpay;




import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;





import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.Constants;


public class WeixinUtil {
	protected static final Log log = LogFactory.getLog(WeixinUtil.class.getSimpleName());
	public static final String APPID;
	static final String APPSECRET;
	public static final String PAYSIGNKEY;
	public static final String PARTNERID;
	public static final String PARTNERKEY;
	public static final String URL_NOTIFY;
	static final String CHARSET = "GBK";
	//参数的加密签名
	static final String KEY_SIGNATURE	= "signature";
	//签名方式，目前只支持“SHA1”
	static final String KEY_SIGNATURE_METHOD	= "signMethod";
	//交易状态
	static final String KEY_TRANS_STATUS = "trade_state";
	//商户订单编号
	static final String KEY_ORDER_ID = "out_trade_no";
	//微信URL
	public static final String WEIXINBASE_URL ="https://api.weixin.qq.com/";
	static {
		
		APPID = Constants.WECHAT_APPID_VARIATE;
		APPSECRET = Constants.WECHAT_APPSECRET_VARIATE;
		PAYSIGNKEY = Constants.WECHAT_PAYSIGNKEY_VARIATE;
		PARTNERID = Constants.WECHAT_PARTNERID_VARIATE;
		PARTNERKEY = Constants.WECHAT_PARTNERKEY_VARIATE;
		URL_NOTIFY = Constants.BASE_M_SHANGPIN_URL +"weixinpay/notifyReceiver";
		
	}

	

	public static final String attachParams(Map<String, String> params, boolean sort, boolean encode) {
		ArrayList<String> keys = new ArrayList<String>(params.keySet());
		if (sort) {
			Collections.sort(keys); // 参数排序
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			String value = params.get(key);
			if (encode) {
				try {
						value = URLEncoder.encode(value,CHARSET);
					
				} catch (UnsupportedEncodingException e) {
					log.error("WeixinUtil :"+e);
				}
			}
			sb.append(key).append("=").append(value);
			if (i < keys.size() - 1) {// 拼接时，不包括最后一个&字符
				sb.append("&");
			}
		}
		return sb.toString();
	}


	public static String getAppsecret() {
		return APPSECRET;
	}
	
    
}
