package com.shangpin.wireless.api.view.servlet;

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
 * 礼品卡密码重置
 * 
 * @author liling
 */
public class GiftCartResetPwdServlet extends HttpServlet {

    private static final long serialVersionUID = 6345447459447582412L;

    protected final Log log = LogFactory.getLog(GiftCartResetPwdServlet.class);

    @Override
    public void init() throws ServletException {
        super.init();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String productNo = request.getHeader("p");
        final String imei = request.getHeader("imei");
        final String userId = request.getParameter("userId");
        final String password = request.getParameter("password");
        final String smsCode = request.getParameter("smsCode");
        final String phoneNum = request.getParameter("phoneNum");
        final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
        if (StringUtil.isNotEmpty(imei, productNo, userId, password, phoneNum, smsCode)) {
            String url = Constants.BASE_URL + "verifyphoneandcode/";
            try {
                Map<String, String> map = new HashMap<String, String>();
                map.put("userid", userId);
                map.put("phonenum", phoneNum);
                map.put("verifycode", smsCode);
                String data = WebUtil.readContentFromGet(url, map);
                JSONObject obj = JSONObject.fromObject(data);
                if (obj.getString("code").equals(Constants.SUCCESS)) {
                    String urlResetPwd = Constants.BASE_URL + "setgiftcardpaypass/";
                    Map<String, String> mapResetPwd = new HashMap<String, String>();
                    mapResetPwd.put("userId", userId);
                    mapResetPwd.put("pass", password);
                    String dataResetPwd = WebUtil.readContentFromGet(urlResetPwd, mapResetPwd);
                    response.getWriter().print(dataResetPwd);
                } else {
                    response.getWriter().print(data);
                }
            } catch (Exception e) {
                e.printStackTrace();
                log.error("GiftCartResetPwdServlet：" + e);
                try {
                    WebUtil.sendApiException(response);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            // 记录访问日志
            FileUtil.addLog(request, "setGiftCartPwd", channelNo,
            		"imei", imei, 
            		"productNo", productNo, 
            		"userId", userId, 
            		"phoneNum", phoneNum,
            		"smsCode", smsCode);
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
