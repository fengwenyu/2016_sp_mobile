package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import java.io.PrintWriter;
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
 * 定位servlet,根据传入的经纬度确定所在的一级省份
 * 
 * @author sunweiwei
 * 
 */
public class LocationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    protected final Log log = LogFactory.getLog(LocationServlet.class);
    private static final String URL = "http://api.map.baidu.com/geocoder/v2/";
    private static final String AK = "203f3dd4fbcadce0f72d8cf162d3ebce";
    private static final String OK = "0";

    @Override
    public void init() throws ServletException {
        super.init();
    }

    /**
     * 1.根据客户端传入的经纬度，定位到具体的位置。
     * 
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lng = request.getParameter("lng");
        String lat = request.getParameter("lat");
        PrintWriter writer = response.getWriter();
        String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
        JSONObject resObj = new JSONObject();

        if (StringUtil.isNotEmpty(lng, lat)) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("ak", AK);
            map.put("location", lat + "," + lng);
            map.put("output", "json");
            map.put("pois", "0");
            try {
                String data = WebUtil.readContentFromGet(URL, map);
                JSONObject obj = JSONObject.fromObject(data);
                JSONObject resultObj = obj.getJSONObject("result");
                String status = obj.getString("status");
                if (OK.equalsIgnoreCase(status)) {
                    resObj = genSuccessObj(resultObj);
                    writer.print(resObj.toString());
                    writer.close();
                } else {
                    resObj = genFailObj();
                    writer.print(resObj.toString());
                    writer.close();
                    return;
                }
                // 打印日志
                FileUtil.addLog(request, "LocationServlet", channelNo,
                		"lng", lng, 
                		"lat", lat);
            } catch (Exception e) {
                log.error("LocationServlet：" + e);
                try {
                    WebUtil.sendApiException(response);
                } catch (Exception e1) {
                    log.error("LocationServlet：" + e);
                }
            }
        }

    }

    /**
     * 生成失败的json对象
     * 
     * @return
     */
    private JSONObject genFailObj() {
        JSONObject resobj = new JSONObject();
        resobj.put("code", Constants.ERROR);
        resobj.put("msg", Constants.ERROR_INVALIDPARAMS_PROMPT);
        resobj.put("content", "");
        return resobj;
    }

    /**
     * 生成成功的json对象
     * 
     * @param resultObj
     * @return
     */
    private JSONObject genSuccessObj(JSONObject resultObj) {
        JSONObject resObj = new JSONObject();
        resObj.put("code", Constants.SUCCESS);
        resObj.put("msg", "");
        JSONObject contentObj = new JSONObject();
        contentObj.put("location", resultObj.get("location"));
        contentObj.put("formatted_address", resultObj.getString("formatted_address"));
        contentObj.put("business", resultObj.getString("business"));
        contentObj.put("addressComponen", resultObj.get("addressComponent"));
        resObj.put("content", contentObj);
        return resObj;
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
