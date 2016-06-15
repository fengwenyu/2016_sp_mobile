/**
 * create on 2014-12-05
 */
package com.shangpin.wireless.api.util;

/**
 * @author wangfeng
 * @version v1.0
 */
public class AppUrlRedirectUtil {

    public static String appUrl(String url) {
        String parm = url.substring(url.indexOf("?") + 1);
        String[] array = parm.split("&");
        String id = "";
        String topListUrl = "/merchandiseaction!splist";
        String categoryListUrl = "/categoryproductsaction!getProductList";
        String brandListUrl = "/brandaction!getBrandProductsList";
        String detailUrl = "/merchandiseaction!spdetail";
        if (url.indexOf(topListUrl) > -1) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].indexOf("topicid=") > -1) {
                    id = array[i].substring(array[i].indexOf("=") + 1);
                    return id;
                }
            }
        } else if (url.indexOf(categoryListUrl) > -1) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].indexOf("categoryNO=") > -1) {
                    id = array[i].substring(array[i].indexOf("=") + 1);
                    return id;
                }
            }
        } else if (url.indexOf(brandListUrl) > -1) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].indexOf("brandNO=") > -1) {
                    id = array[i].substring(array[i].indexOf("=") + 1);
                    return id;
                }
            }
        } else if (url.indexOf(detailUrl) > -1) {
            for (int i = 0; i < array.length; i++) {
                if (array[i].indexOf("productid=") > -1) {
                    id = array[i].substring(array[i].indexOf("=") + 1);
                    return id;
                }
            }
        }
        return url;
    }

    public static String appType(String url) {
        String type = "5";
        String topListUrl = "/merchandiseaction!splist";
        String brandListUrl = "/brandaction!getBrandProductsList";
        String categoryListUrl = "/categoryproductsaction!getProductList";
        String detailUrl = "/merchandiseaction!spdetail";
        if (url.indexOf(topListUrl) > -1) {
            type = "1";
        } else if (url.indexOf(categoryListUrl) > -1) {
            type = "2";
        } else if (url.indexOf(brandListUrl) > -1) {
            type = "3";
        } else if (url.indexOf(detailUrl) > -1) {
            type = "4";
        }
        return type;
    }
}
