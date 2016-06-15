package com.shangpin.web.utils;

import com.shangpin.utils.DateUtils;

import java.util.Date;

/**
 * <br/>
 * Date: 2016/5/26<br/>
 *
 * @author ZRS
 * @since JDK7
 */
public class ActivifyUtil {

    /**
     * 活动是否进行中
     * @return
     */
    public static boolean isRunActivify(){
        Date date = new Date();
        Boolean isStart = date.after(DateUtils.getDate("2016-05-20 00:00:00","yyyy-MM-dd HH:mm:ss"));
        Boolean noEnd = date.before(DateUtils.getDate("2016-05-27 00:00:00","yyyy-MM-dd HH:mm:ss"));

        return isStart && noEnd;
    }

    /**
     * 买赠活动是否进行中
     * @return
     */
    public static boolean isfreeActivify(){
        Date date = new Date();
        Boolean isStart = date.after(DateUtils.getDate("2016-05-20 00:00:00","yyyy-MM-dd HH:mm:ss"));
        Boolean noEnd = date.before(DateUtils.getDate("2016-06-05 00:00:00","yyyy-MM-dd HH:mm:ss"));

        return isStart && noEnd;
    }
}
