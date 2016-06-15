package com.shangpin.wireless.api.weixin;

import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ThreeDES;
import com.shangpin.wireless.api.weixinbean.MsgReceived;

public class PatternUtil {

	private static final int SHOP_TYPY_SHANGPIN	= 1;
	private static final int SHOP_TYPY_AOLAI	= 2;
	private static final String PREFIX_PARAMS	= "#(";
	private static final String SUFFIX_PARAMS	= ")#";
	private static final String URL_SHANGPIN	= Constants.WEIXIN_ACTION_BASE_URL;

	private static final String PATTERN_WX_USER_OPENID	= "${openid.user.wx}";
	private static final String PATTERN_SP_URL	= "${url.sp}";
	private static final String PATTERN_AL_URL	= "${url.al}";
	private static final String PATTERN_SP_WOMEN_NEW_URL	= "${url.spwomennew}";
	private static final String PATTERN_SP_MEN_NEW_URL	= "${url.spmennew}";
	private static final String PATTERN_SP_PRODUCT_URL	= "${url.spproduct}";
	private static final String PATTERN_SP_PRODUCT_NAME_URL	= "${name.spproduct}";
	private static final String PATTERN_SP_PRODUCT_BRAND_URL	= "${brand.spproduct}";
	private static final String PATTERN_SP_PRODUCT_IMAGE_URL	= "${url.image.spproduct}";
	private static final String PATTERN_ACCOUNT_BIND_URL	= "${url.bind}";
	private static final String PATTERN_USER_INFO_URL	= "${url.userinfo}";
	private static final String PATTERN_COUPONS_URL	= "${url.coupons}";
	private static final String PATTERN_ORDERS_URL	= "${url.orders}";
	private static final String PATTERN_LOGISTICS_URL	= "${url.logistics}";
	private static final String PATTERN_BINDED_LOGINNAME	= "${loginname.binded}";
	private static final String PATTERN_TIME_NOW	= "${now.time}";
//	定义新的pattern，一定要加到数组里面！！！切记！！
	private static final String[] PATTERNS	= {
		PATTERN_WX_USER_OPENID,
		PATTERN_SP_URL,					PATTERN_AL_URL,
		PATTERN_SP_WOMEN_NEW_URL,		PATTERN_SP_MEN_NEW_URL,
		PATTERN_SP_PRODUCT_URL,			PATTERN_SP_PRODUCT_NAME_URL,
		PATTERN_SP_PRODUCT_BRAND_URL,	PATTERN_SP_PRODUCT_IMAGE_URL,
		PATTERN_ACCOUNT_BIND_URL,		PATTERN_USER_INFO_URL,
		PATTERN_COUPONS_URL,			PATTERN_ORDERS_URL,
		PATTERN_LOGISTICS_URL,
		PATTERN_BINDED_LOGINNAME,
		PATTERN_TIME_NOW,
	};
	
	
	
	public static final String replaceAllPattern (String origin, MsgReceived msg) {

		if(PATTERNS.length == 0){
			return origin;
		}
		for (int i=PATTERNS.length-1;i>=0;i--) {
			String replacement;
			do {
				replacement = replacePattern (origin, PATTERNS[i], msg);
				if (replacement != null) {
					origin = replacement;
				} else {
					break;
				}
			} while (true);
		}
		
		return origin;
	}
	
	public static final String replacePattern (String origin, String pattern, MsgReceived msg) {
		try {
		final int index = origin.indexOf(pattern);
		if (index < 0) return null;
		final int nextindex = index + pattern.length();
		final int startparams = origin.indexOf(PREFIX_PARAMS, nextindex);
		final int endparams = origin.indexOf(SUFFIX_PARAMS, nextindex);
		
		final String weixinid = msg.getFromUserName();
		StringBuilder sb = new StringBuilder();
		
		if (PATTERN_SP_URL.equals(pattern)) {
			sb.append(weixinid).append("|");
			sb.append("spindex!index");
			String encrypt = URLEncoder.encode(ThreeDES.encryptToString(sb.toString()), "UTF-8");
			
			sb = new StringBuilder();
			sb.append(origin.substring(0, index));
			sb.append(URL_SHANGPIN).append(encrypt);
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_AL_URL.equals(pattern)) {
			sb.append(origin.substring(0, index));
			sb.append(Constants.BASE_M_AOLAI_URL +"aolaiindex!index?ch=36");
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_ACCOUNT_BIND_URL.equals(pattern)) {
			sb.append(weixinid).append("|");
			sb.append("weixinaction!loginbind");
			String encrypt = URLEncoder.encode(ThreeDES.encryptToString(sb.toString()), "UTF-8");
			
			sb = new StringBuilder();
			sb.append(origin.substring(0, index));
			sb.append(URL_SHANGPIN).append(encrypt);
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_USER_INFO_URL.equals(pattern)) {
			sb.append(weixinid).append("|");
			sb.append("accountaction!info");
			String encrypt = URLEncoder.encode(ThreeDES.encryptToString(sb.toString()), "UTF-8");
			
			sb = new StringBuilder();
			sb.append(origin.substring(0, index));
			sb.append(URL_SHANGPIN).append(encrypt);
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_COUPONS_URL.equals(pattern)) {
			sb.append(weixinid).append("|");
			sb.append("couponaction!couponlist");
			String encrypt = URLEncoder.encode(ThreeDES.encryptToString(sb.toString()), "UTF-8");
			
			sb = new StringBuilder();
			sb.append(origin.substring(0, index));
			sb.append(URL_SHANGPIN).append(encrypt);
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_ORDERS_URL.equals(pattern)) {
			sb.append(weixinid).append("|");
			sb.append("orderaction!orderlist");
			String encrypt = URLEncoder.encode(ThreeDES.encryptToString(sb.toString()), "UTF-8");
			
			sb = new StringBuilder();
			sb.append(origin.substring(0, index));
			sb.append(URL_SHANGPIN).append(encrypt);
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_LOGISTICS_URL.equals(pattern)) {
			sb.append(weixinid).append("|");
			sb.append("logisticeaction!logisticeInfo");
			String encrypt = URLEncoder.encode(ThreeDES.encryptToString(sb.toString()), "UTF-8");
			
			sb = new StringBuilder();
			sb.append(origin.substring(0, index));
			sb.append(URL_SHANGPIN).append(encrypt);
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_SP_WOMEN_NEW_URL.equals(pattern)) {
			sb.append(weixinid).append("|");
			sb.append("merchandiseaction!getNewlist?gender=0&categoryid=A01");
			String encrypt = URLEncoder.encode(ThreeDES.encryptToString(sb.toString()), "UTF-8");
			
			sb = new StringBuilder();
			sb.append(origin.substring(0, index));
			sb.append(URL_SHANGPIN).append(encrypt);
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_SP_MEN_NEW_URL.equals(pattern)) {
			sb.append(weixinid).append("|");
			sb.append("merchandiseaction!getNewlist?gender=1&categoryid=A02");
			String encrypt = URLEncoder.encode(ThreeDES.encryptToString(sb.toString()), "UTF-8");
			
			sb = new StringBuilder();
			sb.append(origin.substring(0, index));
			sb.append(URL_SHANGPIN).append(encrypt);
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_SP_PRODUCT_URL.equals(pattern)) {
			sb.append(weixinid).append("|");
			sb.append("merchandiseaction!spdetail?");
			if (nextindex == startparams && endparams > startparams) {
				String productid = origin.substring(startparams + PREFIX_PARAMS.length(), endparams);
				sb.append("productid=").append(productid);
				String encrypt = URLEncoder.encode(ThreeDES.encryptToString(sb.toString()), "UTF-8");
				
				sb = new StringBuilder();
				sb.append(origin.substring(0, index));
				sb.append(URL_SHANGPIN).append(encrypt);
				sb.append(origin.substring(endparams + SUFFIX_PARAMS.length()));
				return sb.toString();
			}
		} else if (PATTERN_SP_PRODUCT_NAME_URL.equals(pattern)) {
//			if (nextindex == startparams && endparams > startparams) {
//				String productid = origin.substring(startparams + PREFIX_PARAMS.length(), endparams);
//				SPGoodsDetailServerResponse product = getSPProduct (productid);
//				if (product != null && product.getName() != null) {
//					return origin.substring(0, index) + (product.getName()) + origin.substring(endparams + SUFFIX_PARAMS.length());
//				}
//			}
		} else if (PATTERN_SP_PRODUCT_BRAND_URL.equals(pattern)) {
//			if (nextindex == startparams && endparams > startparams) {
//				String productid = origin.substring(startparams + PREFIX_PARAMS.length(), endparams);
//				SPGoodsDetailServerResponse product = getSPProduct (productid);
//				if (product != null && product.getBrand() != null) {
//					return origin.substring(0, index) + (product.getBrand()) + origin.substring(endparams + SUFFIX_PARAMS.length());
//				}
//			}
		} else if (PATTERN_SP_PRODUCT_IMAGE_URL.equals(pattern)) {
//			if (nextindex == startparams && endparams > startparams) {
//				String productid = origin.substring(startparams + PREFIX_PARAMS.length(), endparams);
//				SPGoodsDetailServerResponse product = getSPProduct (productid);
//				if (product != null && product.getPics() != null) {
//					return origin.substring(0, index) + (product.getPics().getString(0)) + origin.substring(endparams + SUFFIX_PARAMS.length());
//				}
//			}
		} else if (PATTERN_WX_USER_OPENID.equals(pattern)) {
			sb.append(origin.substring(0, index));
			sb.append(msg.getFromUserName());
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_BINDED_LOGINNAME.equals(pattern)) {
			sb.append(origin.substring(0, index));
			sb.append((String)msg.getReserved());
			sb.append(origin.substring(nextindex));
			return sb.toString();
		} else if (PATTERN_TIME_NOW.equals(pattern)) {
			if (nextindex == startparams && endparams > startparams) {
				String timeformat = origin.substring(startparams + PREFIX_PARAMS.length(), endparams);
				SimpleDateFormat sdf = new SimpleDateFormat(timeformat);
				String now = sdf.format(new Date().getTime());
				
				sb.append(origin.substring(0, index));
				sb.append(now);
				sb.append(origin.substring(endparams + SUFFIX_PARAMS.length()));
				return sb.toString();
			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
