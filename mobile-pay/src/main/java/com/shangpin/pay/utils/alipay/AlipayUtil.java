/**
 * create on 2014-10-08
 */
package com.shangpin.pay.utils.alipay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangpin.pay.utils.common.MD5Util;
import com.shangpin.pay.utils.common.StringUtil;
import com.shangpin.utils.Encodes;
import com.shangpin.utils.HttpClientUtil;

/**
 * @author sunweiwei
 * @version v1.0
 */
public class AlipayUtil {
    public static Logger logger = LoggerFactory.getLogger(AlipayUtil.class);
    public static final String TRUE = "true";
    public static final String FALSE = "false";
    public static final String ERROR = "-1";
    public static final String SUCCESS="success";
    public static final String TRADE_FINISHED="TRADE_FINISHED";
    public static final String TRADE_SUCCESS="TRADE_SUCCESS";
    public static final String CHARSET = "UTF-8";
	public static final String FORMAT = "xml";
	public static final String VERSION = "2.0";
	public static final String SERVICE = "alipay.wap.trade.create.direct";
	
    /**
     * 将Map组装成待签名数据。
     * 待签名的数据必须按照一定的顺序排列 这个是支付宝提供的服务的规范，否则调用支付宝的服务会通不过签名验证
     * @param params
     * @return
     */
    public static String getSignData(Map<String, String> params) {
        StringBuffer content = new StringBuffer();

        // 按照key做排序
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            if ("sign".equals(key)||"sign_type".equals(key)) {
                continue;
            }
            String value = (String) params.get(key);
            if (value != null) {
                content.append((i == 0 ? "" : "&") + key + "=" + value);
            } else {
                content.append((i == 0 ? "" : "&") + key + "=");
            }

        }

        return content.toString();
    }

    /**
     * 将Map中的数据组装成url
     * @param params
     * @return
     * @throws UnsupportedEncodingException 
     */
    public static String mapToUrl(Map<String, String> params) throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (String key : params.keySet()) {
            String value = params.get(key);
            if (isFirst) {
                sb.append(key + "=" + URLEncoder.encode(value, CHARSET));
                isFirst = false;
            } else {
                if (value != null) {
                    sb.append("&" + key + "=" + URLEncoder.encode(value, CHARSET));
                } else {
                    sb.append("&" + key + "=");
                }
            }
        }
        return sb.toString();
    }

    /**
     * 取得URL中的参数值。
     * <p>如不存在，返回空值。</p>
     * 
     * @param url
     * @param name
     * @return
     */
    public static String getParameter(String url, String name) {
        if (name == null || name.equals("")) {
            return null;
        }
        name = name + "=";
        int start = url.indexOf(name);
        if (start < 0) {
            return null;
        }
        start += name.length();
        int end = url.indexOf("&", start);
        if (end == -1) {
            end = url.length();
        }
        return url.substring(start, end);
    }
    
    /** 
     * 除去数组中的空值和签名参数
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>();

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")
                || key.equalsIgnoreCase("sign_type")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
    
    /** 
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createEncodeLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);

            if (i == keys.size() - 1) {//拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + Encodes.urlEncode(value);
            } else {
                prestr = prestr + key + "=" + Encodes.urlEncode(value) + "&";
            }
        }

        return prestr;
    }
    
    /**
     * 获取远程服务器ATN结果,验证返回URL
     * @param notify_id 通知校验ID
     * @return 服务器ATN结果
     * 验证结果集：
     * invalid命令参数不对 出现这个错误，请检测返回处理中partner和key是否为空 
     * true 返回正确信息
     * false 请检查防火墙或者是服务器阻止端口问题以及验证时间是否超过一分钟
     */
    public static boolean verifyNotifyId(String notifyId) {
        try {

            String url = AlipayPropertyUtil.OUTSIDE_URL;
            String partner = AlipayPropertyUtil.OUTSIDE_PARTNER;

            Map<String, String> params = new HashMap<String, String>();
            params.put("service", AlipayPropertyUtil.OUTSIDE_NOTIFY_VERIFY_SERVICE);
            params.put("partner", partner);
            params.put("notify_id", notifyId);

            return new Boolean(HttpClientUtil.doPost(url, params));

        } catch (Exception e) {
            logger.error("AlipayUtil verifyNotifyId : Verify Error");
            return false;
        }
    }
    
    /**
     * 根据反馈回来的信息，生成签名结果
     * @param Params 通知返回来的参数数组
     * @param sign 比对的签名结果
     * @return 生成的签名结果
     */
    public static boolean verifySignRSA(Map<String, String> Params, String sign) {
        // 过滤空值、sign与sign_type参数
        Map<String, String> sParaNew = paraFilter(Params);
        // 获取待签名字符串
        String preSignStr = createLinkString(sParaNew);
        // 获得签名验证结果
        boolean isSign = false;
        isSign = RSA.verify(preSignStr, sign, AlipayPropertyUtil.OUTSIDE_RSA_PUBLIC_KEY, AlipayPropertyUtil.CHARSET);
        return isSign;
    }
    
    /**
     * 根据反馈回来的信息，生成签名结果
     * @param Params 通知返回来的参数数组
     * @param sign 比对的签名结果
     * @return 生成的签名结果
     */
    public static boolean verifySignMD5(Map<String, String> Params, String sign) {
        // 过滤空值、sign与sign_type参数
        Map<String, String> sParaNew = paraFilter(Params);
        // 获取待签名字符串
        String preSignStr = createLinkString(sParaNew);
        // 获得签名验证结果
        boolean isSign = false;
        isSign = MD5Util.verify(preSignStr, sign, AlipayPropertyUtil.OUTSIDE_MD5_PRIVATE_KEY, AlipayPropertyUtil.CHARSET);
        return isSign;
    }
    
    /**
     * 判断订单的支付结果
     * @param status
     * @return
     */
    public static boolean verifyOrderStatus(String status) {
        if (StringUtil.isEmpty(status)) {
            return false;
        }
        if (TRADE_FINISHED.equals(status) || TRADE_SUCCESS.equals(status)) {
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<String,String>();
        map.put("trade_no", "2015032827923518");
        map.put("out_trade_no", "20150328007366");
        map.put("trade_status", "TRADE_FINISHED");
        map.put("currency", "USD");
        map.put("total_fee", "0.00");
        map.put("rmb_fee", "1.00");
        map.put("sign_type", "RSA");
        map = AlipayUtil.paraFilter(map);
        String sign = "UaaeYq6XDOOX+DcMgguFUQEKpPQz2Gdbjmz4s7Hsy2bIGWcxrw/gxe2s3XI+3Tggbh1cmkG1oytA90gCB/kB7tTMc9oMNSf5bG7x1TM9qKVjb+eb0hRhl8I1FsR4ny8b+jHMpS89jn8mnKCeE3OSpyU+xrtXFEf6ryPzKkt0Ne4=";
        AlipayUtil.verifySignRSA(map, sign);
        
    }
    
}