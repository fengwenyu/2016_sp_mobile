package com.aolai.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangpin.utils.CacheOption;
import com.shangpin.utils.ThreadVariableGlobal;

/**
 * 拦截根据缓存参数设置，清理或刷新缓存
 * 
 * @author zghw
 *
 */
public class CacheOptionFilter implements Filter {
    private static final Logger LOG = LoggerFactory.getLogger(CacheOptionFilter.class);
    /** 缓存设置参数 */
    private static final String OPT_KEY = "OPT";
    /** 缓存设置为不开启 */
    private static final String OPT_MISS = "miss";
    /** 设置缓存刷新 */
    private static final String OPT_REFRESH = "refresh";

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        // 从请求参数中得到操作缓存方式
        String option = getOptionfromURL(request);
        if (StringUtils.isNotBlank(option)) {
            doFilter(request, response, chain, option);
        } else {
            chain.doFilter(request, res);
        }
    }

    /**
     * 从url中取出操作缓存的动作 在url追加： （&|？）OPT=后跟操作的字段 比如：miss、refresh
     * 
     * @param req
     * @return
     */
    private String getOptionfromURL(HttpServletRequest request) {
        String opt = request.getParameter(OPT_KEY);
        return opt;
    }

    /**
     * 缓存操作
     */
    private void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain, String option) throws IOException, ServletException {
        // 默认设置为打开缓存
        CacheOption cacheOption = CacheOption.HIT;
        if (OPT_MISS.equals(option)) {
            // 设置为不缓存
            cacheOption = CacheOption.MISS;
        } else if (OPT_REFRESH.equals(option)) {
            // 设置为刷新缓存
            cacheOption = CacheOption.REFRESH;
        }
        // 加入到线程中
        ThreadVariableGlobal.setCacheOption(cacheOption);
        LOG.debug("add {} cache option to thread var.", cacheOption);
        chain.doFilter(request, response);
        // 移除线程
        ThreadVariableGlobal.removeCacheOption();
        LOG.debug("remove cache option to thread var.");
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }

    @Override
    public void destroy() {

    }

}
