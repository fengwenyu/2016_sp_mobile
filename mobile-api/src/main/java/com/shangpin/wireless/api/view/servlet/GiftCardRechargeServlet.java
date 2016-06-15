package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import com.shangpin.utils.AESUtil;
import com.shangpin.wireless.api.api2client.domain.CommonAPIResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.sun.tools.classfile.Annotation.element_value;

/**
 * 礼品卡充值
 * 
 * @author liling
 */
public class GiftCardRechargeServlet extends HttpServlet {
	private static final long serialVersionUID = 1243901640191985569L;
	protected final Log log = LogFactory.getLog(GiftCardRechargeServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String productNo =request.getHeader("p");
		final String imei = request.getHeader("imei");
		final String ver = request.getHeader("ver");
        Map<String, String> params = new ConcurrentHashMap<String, String>();
        String userId = "", cardNo = "", cardPwd = "";
        log.info("GiftCardRechargeServlet :ver------：" +ver);
        if (StringUtil.compareVer(ver, "2.9.8") == 1) {
            try {
                params = AESUtil.base64ZipAES(request.getParameterMap().toString());
                log.info("GiftCardRechargeServlet :ver----AESUtil--：ses");
            } catch (Exception e2) {
                log.info("GiftCardRechargeServlet: 接口cardRecharge ：Api解密错误" + e2.getMessage());
            }
            userId = params.get("userId");
            cardNo = params.get("cardNo");
            cardPwd = params.get("cardPwd");
        }else {
		  userId = request.getParameter("userId");
		  cardNo = request.getParameter("cardNo");
		  cardPwd = request.getParameter("cardPwd");
        }
        final String channelNo = ChannelNoUtil.getChannelNo(request);// 获取渠道号
	 	if (StringUtil.isNotEmpty(imei, productNo, userId, cardNo, cardPwd)) {
			String url = Constants.BASE_URL + "cardRecharge/";
		    String url2 = Constants.BASE_URL + "getGiftCardStatus/";
			try {
				Map<String, String> checkMap = new HashMap<String, String>();
				checkMap.put("cardNo", cardNo); 
				Map<String, String> map = new HashMap<String, String>();
                map.put("userId", userId);
                map.put("cardNo", cardNo);
                map.put("cardPwd", cardPwd);
                log.info("GiftCardRechargeServlet: ver----call--getGiftCardStatus：" +ver);
				String data2 = WebUtil.readContentFromGet(url2, checkMap);
				log.info("GiftCardRechargeServlet :ver----call--getGiftCardStatus--return：" +data2);
				String statusMag =checkGIftStatus(data2);
				if (statusMag!=null) {
				    CommonAPIResponse apiResponse = new CommonAPIResponse();
			        apiResponse.setCode("100");
			        apiResponse.setMsg(statusMag);
			        response.getWriter().print(apiResponse);
                }else {
                    String data = WebUtil.readContentFromGet(url, map);
                    log.info("GiftCardRechargeServlet: ver----call--cardRecharge--return：" +data2);
                    response.getWriter().print(data);
                }
			} catch (Exception e) {
				e.printStackTrace();
				log.info("GiftCardRechargeServlet: GiftCardRechargeServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "cardRecharge", channelNo,
					"imei", imei, 
					"productNo", productNo, 
					"userId", userId);
			
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
    /***
     * 掉接口查询礼品卡状态
     * @param json
     * @return
     */
	private String checkGIftStatus(String json) {
	    if (json!=null) {
            JSONObject obj = JSONObject.fromObject(json);
            if (obj.getString("code").equals("0")) {
                JSONObject obj1 = obj.getJSONObject("content");
                JSONObject obj2 = JSONObject.fromObject(obj1);
                String status =obj2.getString("statusCode");
                if (!StringUtils.isEmpty(status)) {
                    if (status.equals("3")) {
                        return null;
                    } else if (status.equals("6") || status.equals("7")) {
                        return "该礼品卡已经被充值，如有问题，请联系尚品网客服4006-900-900";
                    } 
                } 
            }
        }
	    return "该礼品卡的状态异常，请联系尚品网客服4006-900-900";
    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
