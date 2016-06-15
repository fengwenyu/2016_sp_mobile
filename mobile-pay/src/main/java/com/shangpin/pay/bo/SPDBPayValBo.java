package com.shangpin.pay.bo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class SPDBPayValBo {
	private String gatewayUrl;
	private SPDBPayRequestParamsBo  requestParams;
	public String getGatewayUrl() {
		return gatewayUrl;
	}
	public void setGatewayUrl(String gatewayUrl) {
		this.gatewayUrl = gatewayUrl;
	}
	public SPDBPayRequestParamsBo getRequestParams() {
		return requestParams;
	}
	public void setRequestParams(SPDBPayRequestParamsBo requestParams) {
		this.requestParams = requestParams;
	}
	
}
