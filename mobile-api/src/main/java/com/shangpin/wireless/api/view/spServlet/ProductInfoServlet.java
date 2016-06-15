package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.ListOfGoods;
import com.shangpin.wireless.api.api2client.domain.SPGoodsDetailAPIResponse;
import com.shangpin.wireless.api.api2server.domain.SPGoodsDetailServerResponse;
import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

/**
 * 商品简要信息接口
 * 
 * @Author:yumeng
 * @CreatDate: 2013-02-4
 */
public class ProductInfoServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(ProductInfoServlet.class);

	ShangPinService shangPinService = null;
    
    @Override
    public void init() throws ServletException {
        shangPinService = (ShangPinService) getBean(ShangPinService.class);
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// head 获取os、wh属性
		String userid = request.getParameter("userid");
		String productid = request.getParameter("productid");
		String topicid = request.getParameter("topicid");
		String picw = request.getParameter("picw");
		String pich = request.getParameter("pich");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		// header里获取wh、产品号,如何是iPhone，则赋值图片高宽
		String productNo = request.getHeader("p");// 产品号
		String wh = request.getHeader("wh");
		// iphone
		if (StringUtils.isNotEmpty(productNo) && Constants.SP_IPHONE_PRODUCTNO.equals(productNo)) {
			if ("320x480".equals(wh)) {
				pich = Constants.SP_PRODUCT_DETAIL_PICH_LOW;
				picw = Constants.SP_PRODUCT_DETAIL_PICW_LOW;
			} else {
				pich = Constants.SP_PRODUCT_DETAIL_PICH_HIGH;
				picw = Constants.SP_PRODUCT_DETAIL_PICW_HIGH;
			}
		} else {
			pich = "{h}";
			picw = "{w}";
		}
		if (StringUtil.isNotEmpty(productid, picw, pich)) {
			ListOfGoods listOfGoodsVO=new ListOfGoods();
	        listOfGoodsVO.setProductId(productid);
	        listOfGoodsVO.setUserId(userid == null ? "" : userid);
	        listOfGoodsVO.setTopicId(topicid == null ? "" : topicid);
	        listOfGoodsVO.setPicw(picw);
	        listOfGoodsVO.setPich(pich);
			SPGoodsDetailAPIResponse apiResponse = new SPGoodsDetailAPIResponse();
			SPGoodsDetailServerResponse serverResponse = new SPGoodsDetailServerResponse();
			try {
			    String data=shangPinService.findSPProductDetail(listOfGoodsVO);
				serverResponse.json2Obj(data);// json2obj
				BeanUtils.copyProperties(apiResponse, serverResponse);
				response.getWriter().print(apiResponse.obj2InfoJson());// obj2json
			} catch (Exception e) {
				e.printStackTrace();
				log.error("ProductInfoServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "shangpinproductinfo", channelNo,
					"userid", userid, 
					"productid", productid, 
					"topicid", topicid);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);

	}
}
