package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.base.service.CommonService;
import com.shangpin.biz.service.ASPBizCollectService;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
/**
 * 收藏商品接口
 * @author liling 
 */
public class CollectedProductListServlet  extends BaseServlet {

	private static final long serialVersionUID = 724316376557127559L;
	protected final Log log = LogFactory.getLog(GiftCartResetPwdServlet.class);
	
	CommonService commonService;
	private ASPBizCollectService aspBizCollectService;
	@Override
	public void init() throws ServletException {
		commonService = (CommonService) getBean(CommonService.class);
		aspBizCollectService=(ASPBizCollectService)getBean(ASPBizCollectService.class);
	}
	

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String productNo =request.getHeader("p");
        final String imei = request.getHeader("imei");
        final String userId = request.getParameter("userId");
        final String shopType = request.getParameter("shopType");
        final String pageIndex = request.getParameter("pageIndex");
        final String pageSize = request.getParameter("pageSize");
        final String postArea=request.getParameter("postArea");
        final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
        if (StringUtil.isNotEmpty(imei, productNo, userId,shopType, pageIndex, pageSize)) {
            try {
                String data =aspBizCollectService.doCollectProductList(userId, shopType, pageIndex, pageSize,postArea).toJsonNullable();
                JSONObject obj = JSONObject.fromObject(data);
                response.getWriter().print(obj.toString());
            } catch (Exception e) {
                e.printStackTrace();
                log.error("CollectedProductListServlet：" + e);
                try {
                    WebUtil.sendApiException(response);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            // 记录访问日志
            FileUtil.addLog(request, "collectedProductList", channelNo,
            		"imei", imei, 
            		"productNo", productNo, 
            		"userId", userId, 
            		"shopType",shopType, 
            		"pageIndex",pageIndex, 
            		"pageSize",pageSize);
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
