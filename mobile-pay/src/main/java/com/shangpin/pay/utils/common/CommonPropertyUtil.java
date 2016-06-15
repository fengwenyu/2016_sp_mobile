package com.shangpin.pay.utils.common;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangpin.pay.utils.weixin.WeixinpayPropertyUtil;

public class CommonPropertyUtil {
	public static Logger logger = LoggerFactory.getLogger(WeixinpayPropertyUtil.class);

	public static final String CHARSET = "UTF-8";

	private static final String CONFIG_FILE_NAME = "common.properties";

	private static final PropertiesConfiguration config = new PropertiesConfiguration();

	static {
		config.setEncoding(CHARSET);
		try {
			config.load(CommonPropertyUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
		} catch (ConfigurationException e) {
			logger.error("init config error:", e);
		}
	}
	public static boolean devModule = config.getBoolean("dev_module", false);
	public static final String payReturnUrl = config.getString("pay_return_url","http://192.168.20.131:8081/pay/return");
	public static final String encryptKey=  config.getString("encrypt_key","20d25a781966242057ed941c39e43713");

	public static final String payRequestUrl = config.getString("pay_request_url","http://192.168.3.79:9080/pay/request");
	public static final String payQueryUrl = config.getString("pay_query_url","http://192.168.20.131:8081/pay/query");
	public static final String payFacadeUrl = config.getString("pay_facede_v2_url","http://192.168.3.79:8081/pay");
}
