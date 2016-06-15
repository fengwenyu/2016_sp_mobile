/**
 * create on 2014-10-08
 */
package com.shangpin.pay.utils.spdb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.shangpin.pay.bo.SPDBPayBackBo;
import com.shangpin.pay.bo.SPDBPayBo;
import com.shangpin.pay.bo.SPDBPayParamsBo;
import com.shangpin.pay.exception.ServiceException;
import com.shangpin.utils.HttpClientUtil;
import com.shangpin.utils.JsonUtil;

/**
 * @author liling
 * @version v1.0
 */
public class SPDBPayUtil {
	public static Logger logger = LoggerFactory.getLogger(SPDBPayUtil.class);
	public SPDBPayParamsBo getSpdbPayObject(SPDBPayBo spdbPayBo)  throws ServiceException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("body", spdbPayBo.getBody());
		params.put("currency", spdbPayBo.getCurrency());
		params.put("customerIp", spdbPayBo.getCustomerIp());
		params.put("gateway", spdbPayBo.getGateway());
		params.put("notifyUrl", spdbPayBo.getNotifyUrl());
		params.put("orderNo", spdbPayBo.getOrderNo());
		params.put("returnUrl", spdbPayBo.getReturnUrl());
		params.put("subject", spdbPayBo.getSubject());
		params.put("timeout", spdbPayBo.getTimeout());
		params.put("totalFee", spdbPayBo.getTotalFee());
		params.put("ext", spdbPayBo.getExt());
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		String parameterJson = gson.toJson(params);
		JsonParser parser = new JsonParser();
		JsonArray jarray = new JsonArray();
		jarray.add(parser.parse(parameterJson));
		Map<String, String> params2 = new HashMap<String, String>();
		params2.put("params", jarray.toString());
		String result = HttpClientUtil.doPost(SPDBPropertyUtil.payFacadeUrl.toString(), params2,SPDBPropertyUtil.CHARSET);
		SPDBPayParamsBo spdbPayParamsBo=JsonUtil.fromJson(result, new TypeToken<SPDBPayParamsBo>(){});
		return spdbPayParamsBo;
	}

	public Map<String, Object> getSpdbPay(SPDBPayBo spdbPayBo)  throws ServiceException{
		Map<String, String> params = new HashMap<String, String>();
		params.put("body", spdbPayBo.getBody());
		params.put("currency", spdbPayBo.getCurrency());
		params.put("customerIp", spdbPayBo.getCustomerIp());
		params.put("gateway", spdbPayBo.getGateway());
		params.put("notifyUrl", spdbPayBo.getNotifyUrl());
		params.put("orderNo", spdbPayBo.getOrderNo());
		params.put("returnUrl", spdbPayBo.getReturnUrl());
		params.put("subject", spdbPayBo.getSubject());
		params.put("timeout", spdbPayBo.getTimeout());
		String totalFee=spdbPayBo.getTotalFee();
	    // 判断是否是测试环境
        if (SPDBPropertyUtil.devModule) {
            totalFee = "0.01";
        }
		params.put("totalFee",totalFee );
		/* params.put("mobilePay", spdbPayBo.getMobilePay()); */
		params.put("ext", spdbPayBo.getExt());
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
				.create();
		String s = gson.toJson(params);
		JsonParser parser = new JsonParser();
		JsonArray jarray = new JsonArray();
		jarray.add(parser.parse(s));

		Map<String, String> params2 = new HashMap<String, String>();
		params2.put("params", jarray.toString());
		String result = HttpClientUtil.doPost(SPDBPropertyUtil.payFacadeUrl.toString(), params2,SPDBPropertyUtil.CHARSET);
		logger.info("SPDBPay getSpdbPay result={}",result);
		Map<String, Object> map = new HashMap<String, Object>();

		map = JsonUtil.fromJson(result,new TypeToken<HashMap<String, Object>>() {});
		map.put("spdbPayInfo", jarray.toString());//向主站写入支付日志时会用到
		return map;
	}

/*	public void goSPDBPay(SPDBPayParamsBo spdbPayParamsBo) {

		Map<String, String> params = new HashMap<String, String>();
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization()
				.create();
		params.put("requestParams",
				gson.toJson(spdbPayParamsBo.getVal().getRequestParams()));
		HttpClientUtil.doPost(spdbPayParamsBo.getVal().getGatewayUrl(), params,
				SPDBPropertyUtil.CHARSET);
	}*/

	public SPDBPayBackBo handlePay(Map<String, String[]> parameterMap,
			String gateway) {
		Map<String, String> handleParams = new HashMap<String, String>();
		Map<String, String> params = new HashMap<String, String>();
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		for (Object key : parameterMap.keySet()) {
			String value = parameterMap.get(key)[0].toString();
			if (value == null|| value.equals("")) {
				continue;
			}
			params.put((String) key, value);
		}
		List<Object> list=new ArrayList<Object>();
		list.add(gateway);
		list.add(params);
		params.put("gateway", gateway);
		String parameterJson = gson.toJson(list);
	/*	JsonParser parser = new JsonParser();
		JsonArray jarray = new JsonArray();
		
		jarray.add(parser.parse(parameterJson));*/
		logger.info("SPDBPay back params={}",parameterJson);
		
		handleParams.put("params",parameterJson);
		String result = HttpClientUtil.doPost(SPDBPropertyUtil.payHandleUrl,handleParams, SPDBPropertyUtil.CHARSET);
		logger.info("SPDBPay back result={}",result);
		if (!StringUtils.isEmpty(result)) {
			SPDBPayBackBo spdbPayBackBo = gson.fromJson(result,SPDBPayBackBo.class);
			return spdbPayBackBo;
		}
		return null;

	}
	
	
	public String queryPayStatusByOrderNoAndGateway(String orderNo,String gateway) {
		Map<String, String> handleParams = new HashMap<String, String>();
		List<Object> list=new ArrayList<Object>();
		list.add(orderNo);
		list.add(gateway);
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		String parameterJson = gson.toJson(list);
		handleParams.put("params",parameterJson);
	/*	list.add(params);
		params.put("gateway", gateway);
		String parameterJson = gson.toJson(list);
		JsonParser parser = new JsonParser();
		JsonArray jarray = new JsonArray();
		
		jarray.add(parser.parse(parameterJson));
		logger.info("SPDBPay back params={}",parameterJson);
		
		handleParams.put("params",parameterJson);*/
		String result = HttpClientUtil.doPost(SPDBPropertyUtil.payQueryUrl,handleParams, SPDBPropertyUtil.CHARSET);
		logger.info("SPDBPay back result={}",result);
	/*	if (!StringUtils.isEmpty(result)) {
			SPDBPayBackBo spdbPayBackBo = gson.fromJson(result,SPDBPayBackBo.class);
			return spdbPayBackBo;
		}*/
		return null;

	}
	
	
	
	public static void main(String[] args) {
		
		SPDBPayUtil spdbPayUtil=new SPDBPayUtil();
		
	/*	Map<String, String[]> parameterMap=new HashMap<String, String[]>();
		String[] strArr={"TranAbbr\u003dIPER|AcqSsn\u003d001354476481|MercDtTm\u003d20150508120230|TermSsn\u003d050800001079|RespCode\u003d00|TermCode\u003d00000000|MercCode\u003d912045110000101|TranAmt\u003d0.01|SettDate\u003d20150508"};  ;
		String[] Signature={"992127a3ac89ccc39d8a6cb7ca53c838092563383d31ef5ea3a7bb53135cfc411fe47adf1981edd1f07e76b17ff86dfe8c904b9321ca87e26b9611b999de89475d4cb2b080b4e7e9ddc799204fcd7f4c04701447b080a21909094dbba55a38401cd0d8e8fff994a2e1b4d78a301b81bdf37e393406ddc5603fa9cb9b23779a89"};
		parameterMap.put("Plain", strArr);
		parameterMap.put("Signature", Signature);
		
		spdbPayUtil.handlePay(parameterMap,
				 "SPDB") ;*/
		spdbPayUtil.queryPayStatusByOrderNoAndGateway("20150512038414", "SPDB");
		
	/*	parameterMap.put("12", "ddd");
		for(Entry<String, Object> entry: parameterMap.entrySet()) {
			 System.out.print("循环Map:"+entry.getKey() + ":" + entry.getValue() + "\t");
		}
		Gson gson = new GsonBuilder().enableComplexMapKeySerialization().create();
		System.out.print(gson.toJson(parameterMap));
*/
	}
}
