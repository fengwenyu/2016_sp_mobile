package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.shangpin.base.service.CommonService;
import com.shangpin.utils.AESUtil;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 查找手机是否注册或绑定
 * 
 * @author wangfeng
 * 
 */
public class CheckUserServlet extends BaseServlet {

	private static final long serialVersionUID = 6345447459447582412L;

	protected final Log log = LogFactory.getLog(CheckUserServlet.class);
	private CommonService commonService = null;
	@Override
	public void init() throws ServletException {
	    commonService = (CommonService) getBean(CommonService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	    Map<String,String> params = new ConcurrentHashMap<String,String>();
	    try {
            params = AESUtil.base64ZipAES(request.getParameterMap().toString());
        } catch (Exception e2) {
            e2.printStackTrace();
        }
		final String phoneNum = params.get("phone");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		String type="0";//是否需要创建新用户（0:只查找是否存在；1：查找是否存在用户，不存在创建一个新的用户）

		if (StringUtil.isNotEmpty(phoneNum)) {
		    String data="";
		    JSONObject apiResult=new JSONObject();
			try {
			    data=commonService.checkUser(phoneNum, type);
			    JSONObject resultObject=JSONObject.fromObject(data);
			    if("0".equals(resultObject.getString("code"))){
			        if("{}".equals(resultObject.getJSONObject("content").toString())){
			            apiResult.put("code", "0");
			            apiResult.put("msg", "");
			            apiResult.put("content", new JSONObject());
			        }else{
			            apiResult.put("code", "1");
                        apiResult.put("msg", "该手机号已被注册,尝试找回密码或登录");
                        apiResult.put("content", new JSONObject());
			        }
			        response.getWriter().print(apiResult.toString());
			    }else{			        
			        response.getWriter().print(data);
			    }
			    
			} catch (Exception e) {
				e.printStackTrace();
				log.error("CheckUserServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "CheckUserServlet", channelNo,
					"phoneNum",phoneNum);
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}



	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}		
}
