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
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 验证手机验证码的接口，新接口，需要单独提供。 验证短信码的Servlet 需要提供用户id,手机号码，验证码
 * 
 * @author sunweiwei
 * 
 */
public class VerifySMSCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(VerifySMSCodeServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userid");
		String phoneNum = request.getParameter("phonenum");
		String verifycode = request.getParameter("smscode");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		String url = Constants.BASE_URL_SP_AL + "verifyphoneandcode/";
		if (StringUtil.isNotEmpty(userId, phoneNum,verifycode)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("userid", userId);
			map.put("phonenum", phoneNum);
			map.put("verifycode", verifycode);
			try {
				String data = WebUtil.readContentFromGet(url, map);
				JSONObject obj = JSONObject.fromObject(data);
				if("0".equals(obj.getString("code"))){				    
				    response.getWriter().print(obj.toString());
				}else{
				    JSONObject apiResult=new JSONObject();
                    apiResult.put("code", "1");
                    apiResult.put("msg", "您输入验证码不正确，请重新输入");
                    apiResult.put("content", new JSONObject());
                    response.getWriter().print(apiResult.toString());
				}

				// 打印日志
				FileUtil.addLog(request, "verifyphoneandcode", channelNo,
						"userid", userId,
						"phonenum", phoneNum, 
						"verifycode", verifycode);
			} catch (Exception e) {
				log.error("verifyphoneandcode：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					log.error("verifyphoneandcode：" + e);
				}
			}
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
