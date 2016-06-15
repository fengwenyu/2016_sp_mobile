package com.shangpin.wireless.api.pay.unionpay;

import java.security.SignatureException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.Constants;

public class UnionPayModel {
	
	private String data;
	private String SPID;
	private String sysProvider;

	public String getData () {return data;}
	public String getSPID () {return SPID;}
	public String getSysProvider () {return sysProvider;}

	/**
	 * 银联支付 根据原始数据，获取银联加密后的字符串，数据格式有要求
	 * 
	 * @param orderid
	 *            订单编号 小于32位，字母加数字
	 * @param ordertime
	 *            订单时间 14位，yyyyMMddHHmmss
	 * @param totalFee
	 *            订单金额 12位，以分为单位，长度不足前面补0
	 * @param test
	 *            是否测试环境
	 * @return
	 */
	public UnionPayModel(String orderid, String ordertime, String totalFee, boolean test) throws SignatureException, Exception {

		final String merchant; // 商户编号
		merchant = Constants.UNIONPM_MERCHANT_VARIATE;
		SPID = Constants.UNIONPM_SPID_VARIATE;
		sysProvider = Constants.UNIONPM_SYSPROVIDER_VARIATE;
		final String terminal = "01080209";
//		final String merchantant = "SHANGPIN"; // 商户名称
		final String merchantant = "尚品百姿电子商务有限公司"; // 商户名称
		final String country = "156"; // 货币编号
		StringBuilder digestBuff = new StringBuilder();
		digestBuff.append(ordertime);
		digestBuff.append(orderid);
		digestBuff.append(totalFee);
		digestBuff.append(country);
		digestBuff.append(terminal);
		digestBuff.append(merchant);
		digestBuff.append(merchantant);
		final String digest = digestBuff.toString();
		StringBuffer strBuff = new StringBuffer();
		strBuff.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		strBuff.append("<cupMobile application=\"尚品奥莱\" version=\"1.0.1\">\n");
		strBuff.append("<transaction type=\"PurchaseAdvice.MARsp\">\n");
		strBuff.append("<submitTime>");
		strBuff.append(ordertime);
		strBuff.append("</submitTime>\n");
		strBuff.append("<order id=\"");
		strBuff.append(orderid);
		strBuff.append("\">订单信息</order>\n");
		strBuff.append("<transAmount currency=\"");
		strBuff.append(country);
		strBuff.append("\">");
		strBuff.append(totalFee);
		strBuff.append("</transAmount>\n");
		strBuff.append("<terminal id=\"");
		strBuff.append(terminal);
		strBuff.append("\"/>\n");
		strBuff.append("<merchant country=\"");
		strBuff.append(country);
		strBuff.append("\" name=\"");
		strBuff.append(merchantant);
		strBuff.append("\" id=\"");
		strBuff.append(merchant);
		strBuff.append("\"/>\n");
		strBuff.append("</transaction>\n");
		strBuff.append("<senderSignature>");
		String signature = RSASignatureUnionPay.SHA1withRSA(digest, test);

		strBuff.append(signature);
		strBuff.append("</senderSignature>\n");
		strBuff.append("<merchantAuthentication>\n");
		strBuff.append("<digest>");
		strBuff.append(digest);
		strBuff.append("</digest>\n");
		strBuff.append("</merchantAuthentication>\n");
		strBuff.append("</cupMobile>\n");

		data = strBuff.toString();
	}

}
