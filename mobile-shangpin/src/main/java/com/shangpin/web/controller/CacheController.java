package com.shangpin.web.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.constructs.blocking.BlockingCache;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 缓存测试controller
 * 
 * @author zghw
 */
@Controller
public class CacheController extends BaseController {

    public static Logger logger = LoggerFactory.getLogger(CacheController.class);
    /** 缓存测试首页 */
    private static final String CACHE_INDEX = "cache/index";
    /** 颜色 */
    private static final String CACHE_COLOR = "cache/color";
    /** 时间 */
    private static final String CACHE_DATETIME = "cache/datetime";
    /** 随机数 */
    private static final String CACHE_RANDOM = "cache/random";
    /** 缓存列表 */
    private static final String CACHE_LIST = "cache/list";

    @RequestMapping(value = "/cache/index")
    public String cacheIndex(HttpServletRequest request, ModelMap model) throws UnknownHostException {
        String ip = request.getLocalAddr();
        String color = getRandColorCode();
        String localIp= InetAddress.getLocalHost().getHostAddress();
        model.addAttribute("IP", ip);
        model.addAttribute("localIp", localIp);
        model.addAttribute("color", color);
        return CACHE_INDEX;
    }

    @RequestMapping(value = "/cache/color")
    public String cacheColor(HttpServletRequest request, ModelMap model, String color) {
        if (StringUtils.isBlank(color)) {
            color = getRandColorCode();
        }
        model.addAttribute("color", color);
        return CACHE_COLOR;
    }

    @RequestMapping(value = "/cache/datetime")
    public String cacheDateTime(HttpServletRequest request, ModelMap model) {
        Long t = System.currentTimeMillis();
        Date now = new Date(t);
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(now);
        model.addAttribute("t", t);
        model.addAttribute("currentTime", currentTime);
        return CACHE_DATETIME;
    }

    @RequestMapping(value = "/cache/random")
    public String cacheRandom(HttpServletRequest request, ModelMap model) {
        Random random = new Random();
        Integer num = random.nextInt() * 1000;
        model.addAttribute("num", num);
        return CACHE_RANDOM;
    }

    @RequestMapping("/cache/list")
    public String cacheList(HttpServletRequest request, ModelMap model) {

        CacheManager manager = CacheManager.getInstance();
        String[] cacheNames = manager.getCacheNames();
        List<String> cacheNameList = Arrays.asList(cacheNames);
        List<BlockingCache> cacheList = new ArrayList<BlockingCache>();
        BlockingCache cache = new BlockingCache(manager.getEhcache("testPageFragmentCachingFilter"));
        cacheList.add(cache);
        model.addAttribute("cacheList", cacheList);
        model.addAttribute("cacheNameList", cacheNameList);
        return CACHE_LIST;
    }

    /**
     * 获取十六进制的颜色代码.例如 "#6E36B4" , For HTML ,
     * 
     * @return String
     */
    private static String getRandColorCode() {
        String r, g, b;
        Random random = new Random();
        r = Integer.toHexString(random.nextInt(256)).toUpperCase();
        g = Integer.toHexString(random.nextInt(256)).toUpperCase();
        b = Integer.toHexString(random.nextInt(256)).toUpperCase();

        r = r.length() == 1 ? "0" + r : r;
        g = g.length() == 1 ? "0" + g : g;
        b = b.length() == 1 ? "0" + b : b;

        return r + g + b;
    }
}
