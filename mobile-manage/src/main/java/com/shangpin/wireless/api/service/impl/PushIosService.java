package com.shangpin.wireless.api.service.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.Callable;

import com.dbay.apns4j.IApnsService;
import com.dbay.apns4j.model.Payload;
import com.shangpin.wireless.util.PushUtil;

public class PushIosService implements Callable<String> {

	private IApnsService service;
	private List<String> tokens;
	private Payload payload;

	/**
	 * 无参构造函数
	 */
	public PushIosService() {
		super();
	}

	/**
	 * 带参数构造函数
	 * 
	 * @param tokens
	 */
	public PushIosService(BigInteger productNum, List<String> tokens,
			Payload payload) {
		super();
		this.tokens = tokens;
		this.payload = payload;
		this.service = PushUtil.getIApnService(productNum);
	}

	@Override
	public String call() throws Exception {
		if (tokens == null || tokens.isEmpty())
			return "send end";
		for (String token : tokens) {
			service.sendNotification(token, payload);
		}
		return "send end";
	}

}
