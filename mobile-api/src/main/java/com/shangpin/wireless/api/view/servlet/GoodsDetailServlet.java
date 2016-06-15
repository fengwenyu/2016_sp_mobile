package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.shangpin.base.service.AoLaiService;
import com.shangpin.base.service.ShangPinService;
import com.shangpin.wireless.api.api2client.domain.AolaiGoodsDetailAPIResponse;
import com.shangpin.wireless.api.api2server.domain.AolaiGoodsDetailServerResponse;
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
public class GoodsDetailServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(GoodsDetailServlet.class);
	
	private static final String AOLAI_URL=Constants.BASE_M_AOLAI_URL;

	AoLaiService  aoLaiService=null;
    @Override
    public void init() throws ServletException {
        aoLaiService = (AoLaiService) getBean(AoLaiService.class);
    }

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String productNo = request.getHeader("p");// 产品号
		String ver = request.getHeader("ver");
		String goodsid = request.getParameter("goodsid");
		String type = request.getParameter("type");
		String picquality = request.getParameter("picquality");
		String categoryno = request.getParameter("categoryno");
		String pich = request.getParameter("pich");
		String picw = request.getParameter("picw");
		String thumbnailh = request.getParameter("thumbnailh");// 属性缩略图尺寸
		String thumbnailw = request.getParameter("thumbnailw");
		if (StringUtil.isNotEmpty(goodsid, type, categoryno, productNo, ver)) {			
		    String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		
			String thumbnailSize = null;
			if (StringUtils.isNotEmpty(picw) && StringUtils.isNotEmpty(picw) && StringUtils.isNotEmpty(thumbnailw) && StringUtils.isNotEmpty(thumbnailh)) {
				thumbnailSize = "-" + thumbnailw + "-" + thumbnailh;
			} else {
				if ("high".equals(picquality)) {
					picw="420";
					pich="560";
					thumbnailSize = "-140-186";
				} else if ("low".equals(picquality)) {
					picw="210";
					pich="280";
					thumbnailSize = "-70-93";
				} else {
					picw="420";
					pich="560";
					thumbnailSize = "-140-186";
				}
			}
			AolaiGoodsDetailAPIResponse apiResponse = new AolaiGoodsDetailAPIResponse();
			AolaiGoodsDetailServerResponse serverResponse = new AolaiGoodsDetailServerResponse();
			//设置需要请求奥莱的模版地址
			apiResponse.setInfourl(AOLAI_URL+"merchandiseaction!aolaiDetail?goodsid="+goodsid+"&categoryno="+categoryno+"&type="+type);
			try {
				String data = aoLaiService.findProductDetail(goodsid,type,categoryno,picw,pich);
				serverResponse.json2Obj(data);// json2obj
				BeanUtils.copyProperties(apiResponse, serverResponse);
				// if ("0".equals(serverResponse.getCode())) {
				// apiResponse.getInfo().add(0, "商品编号：" + apiResponse.getGoodscode());
				// if ("1".equals(productNo) && "3.0.0".equals(ver)) {
				// if (apiResponse.getSpecialinfo().length() > 0) {
				// apiResponse.getInfo().add("特殊说明：" + apiResponse.getSpecialinfo());
				// }
				// }
				// }
				if ("8f0c7cf4".equals(serverResponse.getCode())) {
					type="1".equals(type) ? "0" : "1";
					data =aoLaiService.findProductDetail(goodsid,type,categoryno,picw,pich);
					serverResponse.json2Obj(data);// json2obj
					BeanUtils.copyProperties(apiResponse, serverResponse);
				}
				response.getWriter().print(apiResponse.obj2Json(thumbnailSize));// obj2json
			} catch (Exception e) {
				e.printStackTrace();
				log.error("GoodsDetailServlet：" + e);
				try {
					type="1".equals(type) ? "0" : "1";
					String data = aoLaiService.findProductDetail(goodsid,type,categoryno,picw,pich);
					serverResponse.json2Obj(data);// json2obj
					BeanUtils.copyProperties(apiResponse, serverResponse);
					response.getWriter().print(apiResponse.obj2Json(thumbnailSize));// obj2json
				} catch (Exception e1) {
					e1.printStackTrace();
					log.error("GoodsDetailServlet：" + e1);
					try {
						WebUtil.sendApiException(response);
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "detail", channelNo,
					"goodsid", goodsid, 
					"type", type, 
					"categoryno", categoryno);
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
