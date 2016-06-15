package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wireless.api.domain.Constants;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 获取更多品牌
 * 
 * @Author:yumeng
 * @CreatDate: 2012-12-25
 */
public class MoreBrandsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected final Log log = LogFactory.getLog(MoreBrandsServlet.class);

	@Override
	public void init() throws ServletException {
		super.init();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String categoryid = request.getParameter("categoryid");
		final String gender = request.getParameter("gender");
		final String picw = request.getParameter("picw");
		final String pich = request.getParameter("pich");
		final String channelNo = ChannelNoUtil.getChannelNo(request);//获取渠道号
		if (StringUtil.isNotEmpty(categoryid, gender)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("categoryid", categoryid);
			map.put("gender", null == gender || !"1".equals(gender) ? "0" : gender);
			map.put("picw", StringUtils.isEmpty(picw) ? "50" : picw);
			map.put("pich", StringUtils.isEmpty(pich) ? "50" : pich);
			String url = Constants.SP_BASE_URL + "SPSearchMoreBrands/";
			try {
				// 通过categoryid品类ID，获取缓存中对应的数据
				// Object obj = DataContainerPool.brandContainer.get(categoryid);
				// String res = "";
				// if (null == obj) {
				// res = MoreBrandUtil.failCateid();
				// } else {
				// res = obj.toString();
				// }
				String data = WebUtil.readContentFromGet(url, map);
				response.getWriter().print(data);
//				log.debug(data);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("MoreBrandsServlet：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
			// 记录访问日志
			FileUtil.addLog(request, "shangpinmorebrands", channelNo,
					"categoryid", categoryid,
					"gender", gender);
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
