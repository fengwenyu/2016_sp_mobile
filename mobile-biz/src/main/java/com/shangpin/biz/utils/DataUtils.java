package com.shangpin.biz.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 日期util
 * 
 * @author wh
 *
 */
public class DataUtils {
	/**
     * 将字符串日期转换成长整类型日期
     * @param strDate 字符串型时间
     * @return long 长整型时间
     */
    public static long formatDateStringToInt(String strDate) {
        SimpleDateFormat format;
        Date time;
        if (strDate.trim().equals(""))
            return -1;
        String strAry[] = strDate.split(":");
        if (strAry.length > 1)
        {
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        }
        else
        {
            if(strDate.indexOf("-")>0)
            {
                format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            }
            else
            {
                //不带“-”的 如：20150520 格式的日期
                format = new SimpleDateFormat("yyyyMMdd", Locale.getDefault());
            }
        }
        try {
            time = format.parse(strDate + ":00");
            return time.getTime();
        } catch (Exception e) {
            return -1;
        }
    }
    
}
