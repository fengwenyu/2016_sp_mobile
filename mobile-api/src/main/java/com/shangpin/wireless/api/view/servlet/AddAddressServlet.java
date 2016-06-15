package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 新增收货地址
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-08-29
 */
public class AddAddressServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(AddAddressServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		String userid = request.getParameter("userid");
		String consigneename = request.getParameter("consigneename");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String town = request.getParameter("town");
		String address = request.getParameter("address");
		String postcode = request.getParameter("postcode");
		String tel = request.getParameter("tel");
		String cardID = request.getParameter("cardID");
		String setdefault = request.getParameter("default");
		String sessionid = request.getParameter("sessionid");
		String imei = request.getHeader("imei");
		PrintWriter writer = response.getWriter();
		if (StringUtil.isNotEmpty(userid, consigneename, province, city, area, address, postcode, tel, sessionid, imei)) {
			//验证手机号码的合法性
//			if(!StringUtil.checkMobliePhone(tel)){
//				JSONObject respobj = new JSONObject();
//				respobj.put("code", Constants.ERROR);
//				respobj.put("msg", Constants.PHONE_INVALID_PROMPT);
//				respobj.put("content","");
//				writer.print(respobj.toString());
//				writer.close();
//				return;
//			}
			if (SessionUtil.validate(userid, imei, sessionid)) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("userid", userid);
				map.put("consigneename", consigneename);
				map.put("address", address);
				map.put("province", province);
				map.put("city", city);
				map.put("area", area);
				if(StringUtils.isNotEmpty(town)){
					map.put("town", town);
				}
				if(StringUtils.isNotEmpty(cardID)){
					map.put("cardID", cardID);
				}
				map.put("postcode", postcode);
				map.put("tel", tel);
				if (setdefault != null && setdefault.length() > 0) {
					map.put("setd", setdefault);
				}
				String url = Constants.BASE_URL_SP + "AddConsigneeAddress/";
				try {
					String data = WebUtil.readContentFromGet(url, map);
					writer.print(data);
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
					log.error("AddAddressServlet：" + e);
					try {
						WebUtil.sendApiException(response);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			} else {
				try {
					WebUtil.sendErrorNoSessionid(response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "addaddress", channelNo,
					"userid", userid, 
					"consigneename", consigneename, 
					"province", province, 
					"city", city, 
					"area", area, 
					"address", address, 
					"postcode", postcode, 
					"tel", tel,
					"cardID",cardID,
					"code","success");
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
