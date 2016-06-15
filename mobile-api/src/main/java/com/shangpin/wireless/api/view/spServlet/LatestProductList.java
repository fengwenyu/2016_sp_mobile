package com.shangpin.wireless.api.view.spServlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.base.service.ShangPinService;
import com.shangpin.base.utils.BrandUtil;
import com.shangpin.base.vo.LatestBrand;
import com.shangpin.base.vo.LatestProduct;
import com.shangpin.base.vo.Picture;
import com.shangpin.base.vo.Product;
import com.shangpin.utils.JsonUtil;
import com.shangpin.wireless.api.util.DateUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;
import com.shangpin.wireless.api.view.servlet.BaseServlet;

/**
 * 新品首页列表
 * 
 * @author huangxiaoliang
 * 
 */
public class LatestProductList extends BaseServlet {

	private static final long serialVersionUID = 6345447459447582412L;

	protected final Log log = LogFactory.getLog(LatestProductList.class);

	ShangPinService shangPinService;

	@Override
	public void init() throws ServletException {
		shangPinService = (ShangPinService) getBean(ShangPinService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final String userId = request.getParameter("userId");
		final String pageIndex = request.getParameter("pageIndex");
		final String pageSize = request.getParameter("pageSize");
		final String returnTime = request.getParameter("returnTime");
		final String returnStatus = request.getParameter("returnStatus");

		if (StringUtil.isNotEmpty(pageIndex,pageSize)) {
			try {
				if (!"0".equals(pageSize)) {
					return;
				}
				StringBuffer strBuffer = new StringBuffer();
				List<Picture> gallerys = null;
				List<LatestProduct> latestProducts = shangPinService.findLatestProductListObj(userId, pageIndex, pageSize);
				if (latestProducts != null && !latestProducts.isEmpty()) {
					List<LatestBrand> latestBrands = new ArrayList<LatestBrand>();
					String now = String.valueOf(System.currentTimeMillis());
					SimpleDateFormat spformat = new SimpleDateFormat("yyyy-MM-dd");
					String time;
					LatestProduct latestProduct1 = latestProducts.get(0);
					time = Long.toString(spformat.parse(latestProduct1.getTime()).getTime());
					// **********首次请求**********
					if ("0".equals(returnTime) && "0".equals(returnStatus)) {
						gallerys = galleryPicture();
						// =========时间分组第一组==========
						latestProduct1.setTitle("今日上新");
						latestProduct1.setStatus("today");
						latestProduct1.setTime(time);
						latestBrands = latestProduct1.getBrandList();
						latestBrands = generalRule(latestBrands, "1");
						latestProduct1.setBrandList(latestBrands);
						latestProducts.set(0, latestProduct1);
						if (1 < latestProducts.size()) {
							// =========时间分组第二组==========
							LatestProduct latestProduct2 = latestProducts.get(1);
							latestProduct2.setTitle("昨日上新");
							latestProduct2.setStatus("yesterday");
							latestProduct2.setTime(time);
							latestBrands = latestProduct2.getBrandList();
							latestBrands = generalRule(latestBrands, "2");
							latestProduct2.setBrandList(latestBrands);
							latestProducts.set(1, latestProduct2);
							if (2 < latestProducts.size()) {
								// =========时间分组剩余组==========
								latestProducts = generalGroup(2, latestProducts);
							}
						}
						// **********上一页为今日上新**********
					} else if ("today".equals(returnStatus)) {
						if (returnTime.equals(time)) {
							latestProduct1.setTitle("今日上新");
							latestProduct1.setStatus("today");
							latestProduct1.setTime(time);
							latestBrands = latestProduct1.getBrandList();
							latestBrands = generalRule(latestBrands, "3");
							latestProduct1.setBrandList(latestBrands);
							latestProducts.set(0, latestProduct1);
							if (1 < latestProducts.size()) {
								LatestProduct latestProduct2 = latestProducts.get(1);
								latestProduct2.setTitle("昨日上新");
								latestProduct2.setStatus("yesterday");
								latestProduct2.setTime(time);
								latestBrands = latestProduct2.getBrandList();
								latestBrands = generalRule(latestBrands, "4");
								latestProduct2.setBrandList(latestBrands);
								latestProducts.set(1, latestProduct2);
								if (2 < latestProducts.size()) {
									latestProducts = generalGroup(2, latestProducts);
								}
							}

						} else if (Long.valueOf(returnTime) > Long.valueOf(time)) {
							latestProduct1.setTitle("昨日上新");
							latestProduct1.setStatus("yesterday");
							latestProduct1.setTime(time);
							latestBrands = latestProduct1.getBrandList();
							latestBrands = generalRule(latestBrands, "5");
							latestProduct1.setBrandList(latestBrands);
							latestProducts.set(0, latestProduct1);
							if (1 < latestProducts.size()) {
								latestProducts = generalGroup(1, latestProducts);
							}
						}
						// **********上一页为昨日上新**********
					} else if ("yesterday".equals(returnStatus)) {
						if (returnTime.equals(latestProduct1.getTime())) {
							latestProduct1.setTitle("昨日上新");
							latestProduct1.setStatus("yesterday");
							latestProduct1.setTime(time);
							latestBrands = latestProduct1.getBrandList();
							latestBrands = generalRule(latestBrands, "6");
							latestProduct1.setBrandList(latestBrands);
							latestProducts.set(0, latestProduct1);
							if (1 < latestProducts.size()) {
								latestProducts = generalGroup(1, latestProducts);
							}
						} else if (Long.valueOf(returnTime) > Long.valueOf(time)) {
							latestProducts = generalGroup(0, latestProducts);
						}
					} else if ("no".equals(returnStatus)) {
						latestProducts = generalGroup(0, latestProducts);
						//**********新--按天分页**********
					} else if ((returnTime == null || returnTime.isEmpty()) && (returnStatus == null || returnStatus.isEmpty())) {
						if ("1".equals(pageIndex)) {
							gallerys = galleryPicture();
							latestProduct1.setTime(time);
							latestProduct1.setTitle("今日上新");
							latestProduct1.setStatus("today");
							latestBrands = latestProduct1.getBrandList();
							latestBrands = generalRule(latestBrands, "1");
							latestProduct1.setBrandList(latestBrands);
							latestProducts.set(0, latestProduct1);
						} else if ("2".equals(pageIndex)) {
							latestProduct1.setTime(time);
							latestProduct1.setTitle("昨日上新");
							latestProduct1.setStatus("yesterday");
							latestBrands = latestProduct1.getBrandList();
							latestBrands = generalRule(latestBrands, "6");
							latestProduct1.setBrandList(latestBrands);
							latestProducts.set(0, latestProduct1);
						} else {
							latestProducts = generalGroup(0, latestProducts);
						}
					}
						//**********新--end**********
					// ========end===========
					String jsonStr1 = JsonUtil.toJson(gallerys, null, false, null, null, false, false);
					String jsonStr2 = JsonUtil.toJson(latestProducts, null, false, null, null, false, false);

					strBuffer.append("{\"code\":\"0\",\"msg\":\"\",\"content\":{");
					strBuffer.append("\"sysTime\":\"" + now + "\",");
					strBuffer.append("\"gallery\":" + jsonStr1 + ",");
					strBuffer.append("\"latestProductList\":" + jsonStr2 + "}}");
					
				} else {
					strBuffer.append("{\"code\":\"0\",\"msg\":\"\",\"content\":{}}");
				}
				response.getWriter().print(strBuffer);
			} catch (Exception e) {
				e.printStackTrace();
				log.error("LatestProductList：" + e);
				try {
					WebUtil.sendApiException(response);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}

			FileUtil.addLog(request, "LatestProductList", "userId", userId, "pageIndex", pageIndex, "pageSize", pageSize, "returnTime", returnTime, "returnStatus", returnStatus);
		} else {
			try {
				WebUtil.sendErrorInvalidParams(response);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	/**
	 * 首页轮播图
	 * @return
	 */
	public List<Picture> galleryPicture() {
		List<Picture> gallerys = shangPinService.findGalleryList("1","3");
		if (gallerys != null && !gallerys.isEmpty()) {
			for (int i1 = 0; i1 < gallerys.size(); i1++) {
				Picture gallery = gallerys.get(i1);
				if("6".equals(gallery.getType())){
					gallery.setType("1");
		        }else if("3".equals(gallery.getType())){
		            String [] refString=gallery.getRefContent().split("\\|");
		            if(!"".equals(gallery.getIsFlagship())){
		                if(BrandUtil.isFlagship(refString[1])){
		                	gallery.setType("5");
		                	gallery.setRefContent(BrandUtil.getFlagShipUrl(refString[1]));
		                }else{
		                	gallery.setRefContent(refString[0]);
		                }
		            }
		        }
				gallerys.set(i1, gallery);
			}
		}
		return gallerys;
	}

	/**
	 * 返回通用的时间分组信息
	 * 
	 * @param index
	 *            下标起始位
	 * @param latestProducts
	 * @param latestProduct
	 * @param latestBrands
	 * @return
	 * @throws ParseException 
	 */
	public List<LatestProduct> generalGroup(int index, List<LatestProduct> latestProducts) throws ParseException {
		LatestProduct latestProduct;
		List<LatestBrand> latestBrands;
		String time;
		SimpleDateFormat spformat = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = index; i < latestProducts.size(); i++) {
			latestProduct = latestProducts.get(i);
			latestProduct.setTitle(DateUtil.getStringDateMonth(latestProduct.getTime(), "0", "1", "1", "1")+"上新");
			time = Long.toString(spformat.parse(latestProduct.getTime()).getTime());
			latestProduct.setTime(time);
			latestProduct.setStatus("no");
			// ------通用规则start
			latestBrands = latestProduct.getBrandList();
			generalRule(latestBrands, "0");
			// ------通用规则end
			latestProduct.setBrandList(latestBrands);
			latestProducts.set(i, latestProduct);
		}
		return latestProducts;
	}

	/**
	 * 返回通用规则
	 * 
	 * @param latestBrands
	 * @param location
	 *            表示当前规则使用在代码中的位置
	 * @return
	 */
	public List<LatestBrand> generalRule(List<LatestBrand> latestBrands, String location) {
		for (int j = 0; j < latestBrands.size(); j++) {
			// =========品牌通用规则start========
			LatestBrand latestBrand = latestBrands.get(j);
			if (!BrandUtil.isFlagship(latestBrand.getNameEN())) {
				latestBrand.setName(latestBrand.getNameEN());
				latestBrand.setType("3");
				latestBrand.setRefContent(latestBrand.getBrandId());
			} else {
				latestBrand.setName(BrandUtil.getFlagShipName(latestBrand.getNameEN()));
				latestBrand.setType("5");
				latestBrand.setRefContent(BrandUtil.getFlagShipUrl(latestBrand.getNameEN()));
			}
			// =========根据通用规则使用的位置处理品牌时间标题start========
			if ("0".equals(location)) {
				latestBrand.setAddTimeTitle(DateUtil.getStringDateMonth(latestBrand.getAddTime(), "0", "1", "1", "1"));
			} else if ("1".equals(location) || "3".equals(location)) {
				latestBrand.setAddTimeTitle("今日");
			} else if ("2".equals(location) || "4".equals(location) || "5".equals(location) || "6".equals(location)) {
				latestBrand.setAddTimeTitle("昨日");
			}
			// =========单品通用规则start========
			List<Product> products = latestBrand.getList();
			for (int k = 0; k < products.size(); k++) {
				Product product = products.get(k);
				product.setName(product.getProductName());
				product.setType("4");
				product.setRefContent(product.getProductId());
				products.set(k, product);
			}

			latestBrand.setList(products);
			latestBrands.set(j, latestBrand);
		}
		return latestBrands;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
