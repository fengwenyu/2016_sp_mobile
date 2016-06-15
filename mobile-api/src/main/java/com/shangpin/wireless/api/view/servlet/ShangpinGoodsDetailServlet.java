package com.shangpin.wireless.api.view.servlet;

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

/**
 * 商品详情接口
 * 
 * @Author:zhouyu
 * @CreatDate: 2012-08-28
 */
public class ShangpinGoodsDetailServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(ShangpinGoodsDetailServlet.class);
	
	private static final String SHANGPIN_URL=Constants.BASE_M_SHANGPIN_URL;

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
		// header里获取wh、产品号,如何是iPhone，则赋值图片高宽
		String productNo = request.getHeader("p");// 产品号
		String wh = request.getHeader("wh");
		String ver = request.getHeader("ver");
		String widthHeight = "10";
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if(ver != null){
			if(StringUtil.compareVer(ver, "2.0.0") >= 0){
				pich = widthHeight;;
				picw = widthHeight;;
			}else{
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
					pich = widthHeight;
					picw = widthHeight;
				}
			}
		}
		if (StringUtil.isNotEmpty(productid, picw, pich, ver)) {
		    ListOfGoods listOfGoodsVO=new ListOfGoods();
		    listOfGoodsVO.setUserId(null == userid ? "" : userid);
		    listOfGoodsVO.setProductId(productid);
		    listOfGoodsVO.setTopicId(topicid);
		    listOfGoodsVO.setPich(pich);
		    listOfGoodsVO.setPicw(picw);
			SPGoodsDetailAPIResponse apiResponse = new SPGoodsDetailAPIResponse();
			SPGoodsDetailServerResponse serverResponse = new SPGoodsDetailServerResponse();
			//设置需要请求尚品的模版地址
			apiResponse.setInfourl(SHANGPIN_URL+"product/app/detail?type=1&productNo="+productid+"&topicId="+topicid);
			try {
				String data = shangPinService.findSPProductDetail(listOfGoodsVO);
				serverResponse.json2Obj(data);// json2obj
				BeanUtils.copyProperties(apiResponse, serverResponse);
				apiResponse.setSizeinfo(apiResponse.getSizeinfoii());
				response.getWriter().print(apiResponse.obj2Json().replace("10-10.", "{w}-{h}."));// obj2json
			} catch (Exception e) {
				e.printStackTrace();
				log.error("ProductDetailServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "shangpinproductdetail", channelNo,
					"userid", userid, 
					"productid", productid, 
					"topicid", topicid);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	public static void main(String[] args) {
//		System.out.println(StringUtil.compareVer("2.0.0", "2.0.0"));
//		System.out.println(StringUtil.compareVer("2.0.1", "2.0.0"));
//		System.out.println(StringUtil.compareVer("1.0.1", "2.0.0"));
	}
}
