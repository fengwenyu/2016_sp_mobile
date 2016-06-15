package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.shangpin.base.service.AoLaiService;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.vo.ListOfGoods;
import com.shangpin.wireless.api.api2client.domain.ProductSnapAPIResponse;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 商品快照接口
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-05-02
 */
public class SpProductSnapServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(SpProductSnapServlet.class);

	ShangPinService shangPinService = null;
    AoLaiService  aoLaiService=null;
    @Override
    public void init() throws ServletException {
        shangPinService = (ShangPinService) getBean(ShangPinService.class);
        aoLaiService = (AoLaiService) getBean(AoLaiService.class);
    }


	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// head 获取os、wh属性
		String productid = request.getParameter("productid");
		String shoptype = request.getParameter("shoptype");
		String amount = request.getParameter("amount");
		String count = request.getParameter("count");
		String alcategoryno = request.getParameter("alcategoryno");
		String propstr = request.getParameter("propstr");
		String picw = request.getParameter("picw");
		String pich = request.getParameter("pich");
		String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		// header里获取wh、产品号,如何是iPhone，则赋值图片高宽
		String ver = request.getHeader("ver");
		if (StringUtil.isNotEmpty(productid, picw, pich, ver)) {
			ListOfGoods listOfGoodsVO=new ListOfGoods();
			listOfGoodsVO.setPicw(picw);
			listOfGoodsVO.setPich(pich);
			String data="";
			String result;			
			try {
				if("1".equals(shoptype)){
				    listOfGoodsVO.setProductId(productid);
				    listOfGoodsVO.setTopicId("");
				    data=shangPinService.findSPProductDetail(listOfGoodsVO);
					result = new ProductSnapAPIResponse().objSpJson(data,productid,amount,count,alcategoryno,propstr);
				}else{
					data=aoLaiService.findProductDetail(productid,"1",alcategoryno,listOfGoodsVO.getPicw(),listOfGoodsVO.getPich());                                                                
					result = new ProductSnapAPIResponse().objAlJson(data,productid,amount,count,alcategoryno,propstr);
				}								
				response.getWriter().print(result);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("SpProductSnapServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "SproductSnapServlet", channelNo,
					"productid", productid,
					"alcategoryno", alcategoryno);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
