/**
 * create on 2014-10-08
 */
package com.shangpin.web.utils;

import java.util.Random;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author wh
 */
public class LotteryUtil {
    public static final Logger logger = LoggerFactory.getLogger(LotteryUtil.class);

    private static final String CHARSET = "UTF-8";
    private static final String CONFIG_FILE_NAME = "config/coupon/coupon.properties";
    private static final PropertiesConfiguration config = new PropertiesConfiguration();

    static {
        config.setEncoding(CHARSET);
        try {
            config.load(LotteryUtil.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
        } catch (ConfigurationException e) {
            logger.error("init config error:", e);
        }
    }

    public static String getString(String key) {
        return config.getString(key);
    }
    
    public static boolean getChance(){
        int percentage=Integer.parseInt(LotteryUtil.getString("cash_ratio"));
        Random ran = new Random();
        int probability = ran.nextInt(100000);
       //用户抽中了10%概率，得到了现金红包，下一步去现金红包抽奖方法里给用户抽出具体的中奖金额
        if (probability >= 0 && probability < percentage) {
          return true;
        }else{
            return false;
        }
    }
    
   /***
    * 抽奖方法
    * @param moneys
    * @return
    */
    public static int isActive(String sumMoney) {
        int bonus=0;
        Random ran = new Random();
        int pertage1 = Integer.parseInt(LotteryUtil.getString("cash_dimension"));
        int pertage2 = Integer.parseInt(LotteryUtil.getString("cash_dimension"));
        int pertage3 = Integer.parseInt(LotteryUtil.getString("cash_dimension"));
        int probability = ran.nextInt(100000);
        if (probability >= 0 && probability < pertage1) {
            // 中了3－7元的奖
            bonus= (int) (3 + Math.random() * (7 - 3 + 1));
        } else if (probability >= pertage2 && probability < pertage2) {
            // 中了7-20元的奖
            bonus= (int) (7 + Math.random() * (20 - 7 + 1));
        } else if (probability >= pertage3 && probability < pertage3) {
            // 中了20－200元的奖
            bonus = (int) (20 + Math.random() * (200 - 20 + 1));
        }else {
            bonus=0;
        }
        //控制最后现金发送
        int money= Integer.parseInt(sumMoney);
        if (money>=bonus) {
            return bonus;
        }else {
            return money;
        }
    }
}
