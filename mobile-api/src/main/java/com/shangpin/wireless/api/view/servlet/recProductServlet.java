package com.shangpin.wireless.api.view.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.google.gson.reflect.TypeToken;
import com.shangpin.base.service.CommonService;
import com.shangpin.base.vo.ResultObjMapList;
import com.shangpin.biz.bo.CodeMsgEnum;
import com.shangpin.biz.bo.Product;
import com.shangpin.biz.bo.RecNewProductFor;
import com.shangpin.biz.bo.RecResultProduct;
import com.shangpin.biz.service.ASPBizRecommendService;
import com.shangpin.biz.utils.ApiBizData;
import com.shangpin.biz.utils.ComparePriceUtil;
import com.shangpin.utils.AESUtil;
import com.shangpin.utils.JsonUtil;
import com.shangpin.wireless.api.util.ChannelNoUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.StringUtil;
import com.shangpin.wireless.api.util.WebUtil;

public class recProductServlet extends BaseServlet {

	private static final long serialVersionUID = 6682490335263155099L;

	protected final Log log = LogFactory.getLog(recProductServlet.class);

	CommonService commonService;
	
	ASPBizRecommendService aspBizRecommendService; 

	@Override
	public void init() throws ServletException {
		commonService = (CommonService) getBean(CommonService.class);
		aspBizRecommendService = (ASPBizRecommendService) getBean(ASPBizRecommendService.class);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Map<String, String> params = new ConcurrentHashMap<String, String>(8);
		try {
			params = AESUtil.base64ZipAES(request.getParameterMap().toString());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		final String productNo = request.getHeader("p");
		final String imei = request.getHeader("imei");
		final String coord = request.getHeader("lat") + "," + request.getHeader("lon");
		String shopType = "1";
		final String userId = params.get("userId");
		final String type = params.get("type");
		final String pageIndex = params.get("pageIndex");
		final String pageSize = params.get("pageSize");
		final String channelNo = ChannelNoUtil.getChannelNo(request);// 获取渠道号
		if (StringUtil.isNotEmpty(imei, productNo, coord, type, pageIndex, pageSize)) {
			//先放行这个这三个值
			try {
				if ("101".equals(productNo) || "1".equals(productNo)) {
					shopType = "2";
				}
				String data = "";
				if ("3".equals(type)) {
					// type为3 推荐选择  2.9.1.1 推荐服务先注释掉
					if (StringUtil.isNotEmpty(userId)||StringUtil.isNotEmpty(imei)){
						//捕获推荐执行的异常   有异常 走老的推荐
						try{
							resultproduct(request, response, userId, imei,pageIndex,pageSize);
						}catch(Exception e){
							log.error("调用推荐系统出现异常",e);
							data = aspBizRecommendService.doRecProduct(type, userId, imei, coord, "", pageIndex, pageSize);
							response.getWriter().print(data);
						}
					}else{
					data = aspBizRecommendService.doRecProduct(type, userId, imei, coord, "", pageIndex, pageSize);
					response.getWriter().print(data);
					}
				} else {
					StringBuffer strBuffer = new StringBuffer();
					List<Product> productList = new CopyOnWriteArrayList<Product>();
					data = commonService.recProduct(userId, type, shopType, pageIndex, pageSize);
					ResultObjMapList<Product> obj = JsonUtil.fromJson(data, new TypeToken<ResultObjMapList<Product>>() {
					});

					productList = obj.getList("productList");
					if (null == productList || productList.isEmpty()) {
						strBuffer.append("{\"code\":\"0\",\"msg\":\"\",\"content\":{");
						strBuffer.append("\"productList\":\"\"}}");
					} else {
						String jsonStr1 = JsonUtil.toJson(generalRule(productList, userId), null, false, null, null, false, false);

						strBuffer.append("{\"code\":\"0\",\"msg\":\"\",\"content\":{");
						strBuffer.append("\"productList\":" + jsonStr1 + "}}");
					}
					
					response.getWriter().print(strBuffer);
				}

			} catch (Exception e) {
				e.printStackTrace(); 
				log.error("recProductServlet:" + e);
				returnError(response, "api");
			}
			// 记录访问日志
			FileUtil.addLog(request, "recProductServlet", channelNo, "imei", imei, "productNo", productNo, "userId", userId, "type", type, "pageIndex", pageIndex, "pageSize",
					pageSize);
		} else {
			returnError(response, "params");
		}
	}

	/**
	 * 返回错误信息
	 * 
	 * @param response
	 * @param errorType
	 *            params:请求参数不能为空 api:服务器的错误信息
	 */
	private void returnError(HttpServletResponse response, String errorType) {
		try {
			if ("params".equals(errorType)) {
				WebUtil.sendErrorInvalidParams(response);
			} else {
				WebUtil.sendApiException(response);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private List<Product> generalRule(List<Product> recProducts, String userId) {
		String userLv = aspBizRecommendService.getUserLv(userId);
		for (int k = 0; k < recProducts.size(); k++) {
			Product product = recProducts.get(k);
			product.setName(product.getProductName());
			product.setType("4");
			product.setRefContent(product.getProductId());
			String[] strs = ComparePriceUtil.compare(product.getStatus(), userLv, product.getMarketPrice(), product.getLimitedPrice(), product.getGoldPrice(), product.getPlatinumPrice(), product.getDiamondPrice(), product.getPromotionPrice());
        	product.setSalePrice(strs[0]);
        	product.setMarketPriceNew(strs[1]);
			recProducts.set(k, product);
		}

		return recProducts;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 验证推荐服务并选择对应权重服务，响应对应商品列表      	  // type 类型为3 为你推荐  根据推荐权重 选择推荐接口  

	 * @param userid
	 * @param imei
	 * @throws Exception 
	 */
	
    public void recproductList(HttpServletRequest request, HttpServletResponse response,Map<String, String> params) throws Exception{
    		int rechashcode=0;
    		//final String productNo = request.getHeader("p");
    		final String imei = request.getHeader("imei");
    		final String coord = request.getHeader("lat") + "," + request.getHeader("lon");
    		final String userId = params.get("userId");
    		final String type = params.get("type");
    		final String pageIndex = params.get("pageIndex");
    		final String pageSize = params.get("pageSize");
    	    //String offset="";//推荐从第几条返回数据
    		String data = "";
    		if(StringUtil.isNotEmpty(userId)&&!StringUtil.isNotEmpty(imei)){
    			rechashcode=userId.hashCode();
    			log.debug("userId的值-->"+userId+"对应的hashcode值-->"+rechashcode);
    		}
    		if(StringUtil.isNotEmpty(imei)&&!StringUtil.isNotEmpty(userId)){
    			rechashcode=imei.hashCode();
    			log.debug("imei的值-->"+imei+"对应的hashcode值-->"+rechashcode);
    		}
    	/*	
    		if(StringUtil.isNotEmpty(imei)&&StringUtil.isNotEmpty(userId)){
    			rechashcode=userId.hashCode();
    			log.debug("userId的值-->"+imei+"对应的hashcode值-->"+rechashcode);
    		}*/
    		//先注释掉
			int recvalue=rechashcode%100;
			//规则1
			if(recvalue>=0&&recvalue<=14){
	          log.debug("使用规则1");
	          resultproduct(request, response, userId, imei,pageIndex,pageSize);
             //调取对应的规则接口 返回响应的SPU 并走新的搜索
			}else if(recvalue>=15&&recvalue<=29){
				log.debug("使用规则2");
				resultproduct(request, response, userId, imei,pageIndex,pageSize);
			}else if(recvalue>=30&&recvalue<=99){
				log.debug("使用规则3");
				resultproduct(request, response, userId, imei,pageIndex,pageSize);
			}else{
				//老的搜索
				data = aspBizRecommendService.doRecProduct(type, userId, imei, coord, "", pageIndex, pageSize);
				response.getWriter().print(data);
			}
			
			
    }
	
    
    public void resultproduct(HttpServletRequest request, HttpServletResponse response,String userId,String imei,String pageIndex,String pagesize) throws Exception{
    	//start 查询条数
    	String offset="0";//调用搜索的推荐开始位置
    	int limit=Integer.parseInt(pageIndex);
        if(limit>1){
         limit= limit-1;
         int start=limit*Integer.parseInt(pagesize);
         offset=String.valueOf(start);
        }
        log.info("开始数》》》》》》》》》》》"+offset);
    	String data="";
    	data =aspBizRecommendService.doRecRuleProduct(userId, imei,offset,pagesize);
		List<String> product=getproductList(data);
		 List<Product> list =new ArrayList<Product>();
		if(null!=product&&product.size()>0){
			list=aspBizRecommendService.products(product,userId);
			log.info("调用搜索返回产品记录数》》》》》》》》》》》》》》》》"+list.size());
		}
		
		 RecNewProductFor rec =new RecNewProductFor();   
			if (list != null&&list.size()>0) {
				rec.setSystime(String.valueOf(System.currentTimeMillis()));
				rec.setRecommendNum(String.valueOf(list.size()));
				rec.setList(list);
			}else{
				rec.setSystime(String.valueOf(System.currentTimeMillis()));
				rec.setRecommendNum(String.valueOf(list.size()));
				rec.setList(new ArrayList<Product>());
			}
		
				data = ApiBizData.spliceData(rec, CodeMsgEnum.CODE_SUCCESS.getInfo(), CodeMsgEnum.MSG_SUCCESS.getInfo());
		//判断是否有搜索对应数据
        response.getWriter().print(data);
    	
    }
    
    
    
    /**
     * 解析获取产品编码集合
     * @param data
     * @return
     */
	public List<String> getproductList(String data){
    	 List<String> product =new ArrayList<String>();
    	 RecResultProduct recproduct = JsonUtil.fromJson(data, RecResultProduct.class);
			if (null != recproduct) {
			String args[]=recproduct.getResults();
			if(null!=args&&args.length>0){
				for (int i = 0; i < args.length; i++) {
					product.add(args[i]);
				}
			}
			}
			return product;
    }
     
}
