package com.shangpin.mobileShangpin.common.minshengPay;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import net.sf.json.JSONObject;
import com.shangpin.mobileShangpin.common.util.Constants;
import com.shangpin.mobileShangpin.common.util.FileUtil;
import com.shangpin.mobileShangpin.common.util.SysContent;
import com.shangpin.mobileShangpin.common.util.WebUtil;


/**
 * 
 * 民生支付同步调用url
 * 
 * @author 3y
 * @version $Id: CallBack.java, v 0.1 2011-8-25 下午05:16:26 3y Exp $
 */
public class NotityCmbc extends HttpServlet {
	private static final long serialVersionUID = -2234271646410251381L;
	protected final Log log = LogFactory.getLog(NotityCmbc.class.getSimpleName());

	
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String payresult = request.getParameter("payresult");
		String result;
		try {
			result = new MBECShopSecurity().decryptTradeResult(payresult);
			System.out.println("minshengfanhui1234567");
			System.out.print("民生返回参数"+result);
				String [] resultarray=result.split("\\|");
				String orderno=resultarray[0].substring(5);
				String resultstatus=resultarray[5];
				/*for(String s:resultarray){
					int i=0;
					System.out.println("canshu"+i+":"+s);
					++i;
				}
				
				System.out.println("11111orderno"+orderno);
				System.out.println("222222status"+resultstatus);
				System.out.println("3333s0"+resultarray[0]);
				System.out.println("44444s1"+resultarray[1]);
				System.out.println("55555s2"+resultarray[2]);*/
			// 当交易状态成功，处理业务逻辑成功。回写success
			// 交易成功后，调用接口，更新订单状态
				if("0".equals(resultstatus)){
					final String payTypeId = "25";
					final String payTypeChildId = "53";
//					Map<String, String> reqmap = new HashMap<String, String>();
//					reqmap.put("orderno", orderno);
//					reqmap.put("paytypeId", payTypeId);
//					reqmap.put("paytypechildId", payTypeChildId);
//					String url = Constants.BASE_URL + "payconfirmpayment/";
					HashMap<String, String> reqmap = new HashMap<String, String>();
					reqmap.put("mainorderNo", orderno);
					reqmap.put("payTypeId", payTypeId);
					reqmap.put("childPayTypeId", payTypeChildId);
					reqmap.put("orderAmount", Constants.PAY_AMOUNT);
					String url = Constants.BASE_TRADE_URL + "order/UpdateOrderStatus";
					try {
						String data = WebUtil.readContentFromGet(url, reqmap);
						JSONObject content = JSONObject.fromObject(data);
						final String code = content.getString("code");
						if ("0".equals(code)) {
	    					// 记录访问日志
	    					FileUtil.addLog(request, "/minshengpay/notityCmbc", "", "orderid", orderno, "payid", payTypeId, "paychildid", payTypeChildId, "success", "1");
	    				} else {
	    					FileUtil.addLog(request, "/minshengpay/notityCmbc", "",  "orderid", orderno, "payid", payTypeId, "paychildid", payTypeChildId, "success", "0");
	    					log.warn("Alipay failed orderid = " + orderno + " (" + code + ")(" + content.getString("msg") + ")");
	    				}
						System.out.println("NotityCmbc code : " + code);
						SysContent.getResponse().sendRedirect("http://cmbc.m.shangpin.com/unionpayaction!result?parmresultcmbc="+code);
					
					} catch (Exception e) {
						e.printStackTrace();
						log.error("NotityCmbc：" + e);
					}
				}else{
					System.out.println("支付不成功");
					SysContent.getResponse().sendRedirect("http://cmbc.m.shangpin.com/unionpayaction!result?parmresultcmbc=1");
				}
				
		}  catch (Exception e) {
			System.out.println("NotityCmbc 解密失败！");
			e.printStackTrace();
		} 
		System.out.println("NotityCmbc 处理完毕!");
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		doGet(request, response);
	}
}
