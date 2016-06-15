package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.utils.AESUtil;
import com.shangpin.utils.IDCard;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.SessionUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 编辑收货地址
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-08-29
 */
public class EditAddressServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(EditAddressServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String consigneename = request.getParameter("consigneename");
		String addrid = request.getParameter("addrid");
		String province = request.getParameter("province");
		String city = request.getParameter("city");
		String area = request.getParameter("area");
		String town = request.getParameter("town");
		String address = request.getParameter("address");
		String postcode = request.getParameter("postcode");
		String tel = request.getParameter("tel");
		String cardID = request.getParameter("cardID");
		if(StringUtil.isNotEmpty(cardID)){
			cardID=cardID.replaceAll(" ", "\\+");
		}
		String setdefault = request.getParameter("default");
		String sessionid = request.getParameter("sessionid");
		String imei = request.getHeader("imei");
		final String version = request.getHeader("ver");
		
		PrintWriter writer = response.getWriter();
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(imei, userid, consigneename, addrid, province, city, area, address, postcode, tel, sessionid)) {
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
				if(StringUtil.compareVer(version,"2.7.0")>=0&&(null==town||"0".equals(town))){
					JSONObject resultObj = new JSONObject();
					resultObj.put("code", "3001");
					resultObj.put("msg", "没有四级地址");
					resultObj.put("content", new JSONObject());
					writer.print(resultObj);
					writer.close();
					return;
				}
				if(StringUtils.isNotEmpty(town)){
					map.put("town", town);
				}
				map.put("postcode", postcode);
				map.put("tel", tel);
				map.put("addrid", addrid);
				if(StringUtils.isNotEmpty(cardID)){
					if(StringUtil.compareVer(version,"2.7.0")>=0){
						try {
							String originalCardID = AESUtil.decrypt(cardID, AESUtil.IDCARD_KEY);
							if(!IDCard.checkIDCard(originalCardID)){
								JSONObject resultObj = new JSONObject();
								resultObj.put("code", "1");
								resultObj.put("msg", "身份证不符合规范，请重新输入");
								resultObj.put("content", new JSONObject());
								writer.print(resultObj);
								writer.close();
								return;
							}
						} catch (Exception e) {
							JSONObject resultObj = new JSONObject();
							resultObj.put("code", "1");
							resultObj.put("msg", "身份证不符合规范，请重新输入");
							resultObj.put("content", new JSONObject());
							writer.print(resultObj);
							writer.close();
							return;
						}
					}
					map.put("cardID", cardID);
				}
				if (setdefault != null && setdefault.length() > 0) {
					map.put("setd", setdefault);
				}
				String url = Constants.BASE_URL_SP + "EditConsigneeAddress/";
				try {
					String data = WebUtil.readContentFromGet(url, map);
					writer.print(data);
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
					log.error("EditAddressServlet：" + e);
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
			FileUtil.addLog(request, "editaddr", channelNo,
					"userid", userid, 
					"addrid", addrid, 
					"consigneename", consigneename, 
					"provinceid", province, 
					"cityid", city, 
					"areaid", area, 
					"address", address, 
					"postcode", postcode, 
					"tel", tel,
					"cardID","cardID");
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
