package com.shangpin.biz.tags;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import com.shangpin.biz.utils.CDNHash;
import com.shangpin.biz.utils.PropertyUtil;

/**
 * cdn服务器标签
 * <p/>
 * jsCDN=http://js1.m.shangpin.com
 * <p/>
 * cssCDN=http://css1.m.shangpin.com
 * <p/>
 * picCDN=http://pic1.m.shangpin.com
 * <p/>
 * 
 * @author sunweiwei
 * 
 */
public class CDNTag {

    /**
     * 处理js的cdn地址，把随机数加入到cdn的url中，如：js14.m.shangpin.com
     * 
     * @return
     */
    public static String jsCDN(HttpServletRequest request) {
        String jsCDN = PropertyUtil.getString("jsCDN");
        if (!checkURL(jsCDN)) {
            return getContextPath(request);
        }
        return jsCDN;
    }

    /**
     * 处理js的cdn地址，把随机数加入到cdn的url中，如：js14.m.shangpin.com
     * 
     * @return
     */
    public static String jsCDN(HttpServletRequest request, String sourceName) {
        String jsCDN = PropertyUtil.getString("jsCDN");
        if (!checkURL(jsCDN)) {
            return getContextPath(request);
        }
        return jsCDN.replace("1", CDNHash.getCDNName(sourceName));
    }

    /**
     * 处理css的cdn地址，把随机数加入到cdn的url中，如：css11.m.shangpin.com
     * 
     * @return
     */
    public static String cssCDN(HttpServletRequest request) {
        String cssCDN = PropertyUtil.getString("cssCDN");
        if (!checkURL(cssCDN)) {
            return getContextPath(request);
        }
        return cssCDN;
    }

    /**
     * 处理css的cdn地址，把随机数加入到cdn的url中，如：css11.m.shangpin.com
     * 
     * @return
     */
    public static String cssCDN(HttpServletRequest request, String sourceName) {
        String cssCDN = PropertyUtil.getString("cssCDN");
        if (!checkURL(cssCDN)) {
            return getContextPath(request);
        }
        return cssCDN.replace("1", CDNHash.getCDNName(sourceName));
    }

    /**
     * 处理pic的cdn地址，把随机数加入到cdn的url中，如：pic10.m.shangpin.com
     * 
     * @return
     */
    public static String picCDN(HttpServletRequest request) {
        String picCDN = PropertyUtil.getString("picCDN");
        if (!checkURL(picCDN)) {
            return getContextPath(request);
        }
        return picCDN;
    }

    /**
     * 处理pic的cdn地址，把随机数加入到cdn的url中，如：pic10.m.shangpin.com
     * 
     * @return
     */
    public static String picCDN(HttpServletRequest request, String sourceName) {
        String picCDN = PropertyUtil.getString("picCDN");
        if (!checkURL(picCDN)) {
            return getContextPath(request);
        }
        return picCDN.replace("1", CDNHash.getCDNName(sourceName));
    }

    /**
     * 从当前request中获取context path
     * 
     * @param request
     * @return
     */
    private static String getContextPath(HttpServletRequest request) {
        if (request == null) {
            return "";
        }
        return request.getContextPath();
    }

    /**
     * 检查url的合法性
     * 
     * @param url
     * @return
     */
    private static boolean checkURL(String url) {
        try {
            new URL(url);
            return true;
        } catch (MalformedURLException e) {
            return false;
        }
    }

    /**
     * 获取1-16的随机数
     * 
     * @return
     */
    @SuppressWarnings("unused")
    private static String genRandom() {
        int min = 1;
        int max = 16;
        Random random = new Random();
        int num = random.nextInt(max) % (max - min + 1) + min;
        return num + "";
    }

    public static void main(String[] args) {
        System.out.println(checkURL(null));
    }
}
