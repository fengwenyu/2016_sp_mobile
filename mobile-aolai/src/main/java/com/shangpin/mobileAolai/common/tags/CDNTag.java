package com.shangpin.mobileAolai.common.tags;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.shangpin.mobileAolai.common.util.ProReader;

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

    private static ProReader instance = ProReader.getInstance();

    /**
     * 处理js的cdn地址，把随机数加入到cdn的url中，如：js14.m.shangpin.com
     * 
     * @return
     */
    public static String jsCDN(HttpServletRequest request) {
        String path = getContextPath(request);
        String jsCDN = instance.getProperty("jsCDN");
        if (StringUtils.isEmpty(jsCDN)) {
            return path;
        }
        if (!checkURL(jsCDN)) {
            return path;
        }
        return jsCDN.replace("1", genRandom());
    }

    /**
     * 处理css的cdn地址，把随机数加入到cdn的url中，如：css11.m.shangpin.com
     * 
     * @return
     */
    public static String cssCDN(HttpServletRequest request) {
        String path = getContextPath(request);
        String jsCDN = instance.getProperty("cssCDN");
        if (StringUtils.isEmpty(jsCDN)) {
            return path;
        }
        if (!checkURL(jsCDN)) {
            return path;
        }
        return jsCDN.replace("1", genRandom());
    }

    /**
     * 处理pic的cdn地址，把随机数加入到cdn的url中，如：pic10.m.shangpin.com
     * 
     * @return
     */
    public static String picCDN(HttpServletRequest request) {
        String path = getContextPath(request);
        String jsCDN = instance.getProperty("picCDN");
        if (StringUtils.isEmpty(jsCDN)) {
            return path;
        }
        if (!checkURL(jsCDN)) {
            return path;
        }
        return jsCDN.replace("1", genRandom());
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
    private static String genRandom() {
        int min = 1;
        int max = 16;
        Random random = new Random();
        int num = random.nextInt(max) % (max - min + 1) + min;
        return num + "";
    }

    public static void main(String[] args) {
        //System.out.println(genRandom());
    }
}
