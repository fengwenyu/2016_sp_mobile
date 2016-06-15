/**
 * create on 2014-10-08
 */
package com.shangpin.pay.utils.union;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sunweiwei
 * @version v1.0
 */
public class UnionpayPropertyUtil {
    public static Logger logger = LoggerFactory.getLogger(UnionpayPropertyUtil.class);

    public static final String CHARSET = "UTF-8";

    private static final String CONFIG_FILE_NAME = "unionpay.properties";
    private static final PropertiesConfiguration config = new PropertiesConfiguration();

    static {
        config.setEncoding(CHARSET);
        try {
            config.load(UnionpayPropertyUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
        } catch (ConfigurationException e) {
            logger.error("init config error:", e);
        }
    }
	
	public static boolean devModule = config.getBoolean("dev_module", false);

	public static final String MER_ID = config.getString("mer_id","898110248990119"); // 商户编号
	public static final String SECRET= config.getString("secret","FBzgboUwM9I3TmXfdRyg5SKgHCenNa8T");; // 商户密钥
	public static final String URL_BUY=config.getString("trade_url","https://mgate.unionpay.com/gateway/merchant")+"/trade"; // 订单推送地址
	public static final String URL_QUERY=config.getString("trade_url","https://mgate.unionpay.com/gateway/merchant")+"/query";; // 订单查询地址
	public static final String URL_REQUEST = config.getString("request_url","uppay://uppayservice");

    public static void main(String[] args) {
        System.out.println(config.getString("dev_module"));
    }

}
