package com.shangpin.pay.utils.weixin;

/**
 * create on 2014-10-08
 */

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sunweiwei
 * @version v1.0
 */
public class WeixinpayPropertyUtil {
    public static Logger logger = LoggerFactory.getLogger(WeixinpayPropertyUtil.class);

    public static final String CHARSET = "UTF-8";

    private static final String CONFIG_FILE_NAME = "weixinpay.properties";
    
    private static final PropertiesConfiguration config = new PropertiesConfiguration();
    
    static {
        config.setEncoding(CHARSET);
        try {
            config.load(WeixinpayPropertyUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
        } catch (ConfigurationException e) {
            logger.error("init config error:", e);
        }
    }
    
    public static boolean devModule = config.getBoolean("dev_module", false);
    
    public static final String APP_ID = config.getString("app_id","wx4e93df954af2f52c");

	public static final String PAYSIGN_KEY = config.getString("paysign_key","RMEhoQkh0FTyI5BLsInFKTDZBe4gAqqj6VYkD0u9Hf5hHwT3A0ybT44S9Y2AhiMdCBW70MCcpxksW7mp9NYpHTDJK3cScPtFhLEjW0nH36yR6iC5WtlxIm8jgazlQWT1");

	public static final String PARTNER_KEY = config.getString("partner_key","030d62e64075b703a28592ce672220da");

	public static final String PARTNER_ID = config.getString("partner_id","1217974201");;
	
    public static void main(String[] args) {
        System.out.println(devModule);
    }

}
