/**
 * create on 2014-10-08
 */
package com.shangpin.pay.utils.spdb;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sunweiwei
 * @version v1.0
 */
public class SPDBPropertyUtil {
    public static Logger logger = LoggerFactory.getLogger(SPDBPropertyUtil.class);

    public static final String CHARSET = "UTF-8";

    private static final String CONFIG_FILE_NAME = "spdbpay.properties";
    private static final PropertiesConfiguration config = new PropertiesConfiguration();

    static {
        config.setEncoding(CHARSET);
        try {
            config.load(SPDBPropertyUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
        } catch (ConfigurationException e) {
            logger.error("init config error:", e);
        }
    }
	
	public static boolean devModule = config.getBoolean("dev_module", false);

	public static final String payHandleUrl = config.getString("pay_handle_url", "http://211.151.125.135/facade/json/com.shangpin.payment.pay/PayFacade/handlePayNotify");

	public static final String payFacadeUrl = config.getString("pay_facade_url","http://211.151.125.135/facade/json/com.shangpin.payment.pay/PayFacade/request"); // 商户编号
	public static final String payQueryUrl = config.getString("pay_query_url", "http://211.151.125.135/facade/json/com.shangpin.payment.pay/PayFacade/queryPayStatusByOrderNoAndGateway");


    public static void main(String[] args) {
        System.out.println(config.getString("dev_module"));
    }

}
