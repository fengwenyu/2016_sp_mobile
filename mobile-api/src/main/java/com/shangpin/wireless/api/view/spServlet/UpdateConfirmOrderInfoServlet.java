package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 使用优惠码/券时，费用变化引起的运费变化
 * 
 * @Author:wangwenguan
 * @CreatDate: 2013-11-11
 */
public class UpdateConfirmOrderInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(UpdateConfirmOrderInfoServlet.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String product = request.getHeader("p");
		final String userid = request.getParameter("userid");
		final String addrid = request.getParameter("addrid");
		final String coupon = request.getParameter("coupon");
		final String sessionid = request.getParameter("sessionid");
		final String imei = request.getHeader("imei");
		final String couponflag = request.getParameter("couponflag");
		String buysids = request.getParameter("buysids");
		String orderSource=request.getParameter("orderSource");
		final String ver = request.getHeader("ver");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号

		if (StringUtil.isNotEmpty(product, userid, imei, sessionid, buysids)) {
			
			buysids = buysids.replace(',', '|');
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", userid);
			map.put("addrid", addrid);
			map.put("coupon", null == coupon?"0":coupon);
			map.put("couponflag", couponflag);
			map.put("buysids", buysids);
			map.put("orderSource", orderSource);
			if (SessionUtil.validate(userid, imei, sessionid)) {
				String url = Constants.BASE_TRADE_URL+ "Trade/updateConfirmOrderInfo/";
				try {
					String data = WebUtil.readContentFromGet(url, map);
					JSONObject apiResultObj=JSONObject.fromObject(data);
					
					if ((StringUtil.compareVer(ver, "4.0.0") >= 0||StringUtil.compareVer(ver, "2.5.0") >= 0)) {
					    if(Constants.SUCCESS.equals(apiResultObj.getString("code"))){
	                        JSONObject carriageNew=new JSONObject();
	                        JSONObject contentObj=JSONObject.fromObject(apiResultObj.getString("content"));
	                        int amount=Integer.parseInt(contentObj.getString("carriage"));      
	                        int payamount=Integer.parseInt(contentObj.getString("payamount"));
	                        if (amount==0) {
	                        	if ("2".equals(product)) {
	                        		carriageNew.put("amount", "0");
	                        	} else {
	                        		carriageNew.put("amount", "20");
	                        	}
	                            carriageNew.put("reduction", "20");
	                            if(payamount<499){                                
	                                carriageNew.put("desc", "运费减免（钻石/白金会员免运费）");
	                            }else{
	                                carriageNew.put("desc", "运费减免（满499免运费）");
	                            }
	                        }else {
	                        	carriageNew.put("amount", amount);
		                        carriageNew.put("reduction", "0");
		                        carriageNew.put("desc", "");
	                        }                      
	                        contentObj.put("carriage", carriageNew) ;
	                        apiResultObj.put("content", contentObj);                        
	                    }
                 }                                       
                    response.getWriter().print(apiResultObj.toString());
					 
					
				} catch (Exception e) {
					e.printStackTrace();
					log.error("UpdateConfirmOrderInfoServlet：" + e);
					try {
						WebUtil.sendApiException(response);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
				// 记录访问日志
				FileUtil.addLog(request, "updateconfirmorderinfo", channelNo,
						"userid", userid, 
						"couponflag", couponflag, 
						"coupon", coupon, 
						"addrid", addrid);
			} else {
				try {
					WebUtil.sendErrorNoSessionid(response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
