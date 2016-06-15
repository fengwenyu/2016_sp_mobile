package com.shangpin.web.filter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.blocking.LockTimeoutException;
import net.sf.ehcache.constructs.web.AlreadyCommittedException;
import net.sf.ehcache.constructs.web.AlreadyGzippedException;
import net.sf.ehcache.constructs.web.filter.FilterNonReentrantException;
import net.sf.ehcache.constructs.web.filter.SimplePageFragmentCachingFilter;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.util.WebUtils;

import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.utils.CacheOption;
import com.shangpin.utils.RequestUtils;
import com.shangpin.utils.ThreadVariableGlobal;

/**
 * 页面片段缓存过滤器主要用于一个页面中使用引入了其他页面，比如首页引入轮播图、热门、特卖等。对引入的页面片段进行缓存操作。
 * 
 * @author zghw
 *
 */
public class PageFragmentCacheFilter extends SimplePageFragmentCachingFilter {
    /**
     * The name of the filter. This should match a cache name in ehcache.xml
     */
    public static final String NAME = "PageFragmentCacheFilter";
    private static final Logger LOG = LoggerFactory.getLogger(PageFragmentCacheFilter.class);
    /**
     * 在web.xml中定义，用来定义需要缓存的页面片段URI集合.URI不需要带参数 主要为了节约空间web.xml的空间，方便定义页面片段URL.
     * 例如：
     * <p/>
     * <init-param>
     * <p/>
     * <param-name>patterns</param-name>
     * <p/>
     * <param-value>/index/focus,/index/category,/index/hot</param-value>
     * <p/>
     * </init-param>
     */
    private final static String FILTER_URL_PATTERNS = "patterns";
    /**
     * 页面片段URL集合.
     */
    private static String[] cacheURLs;

    /**
     * 初始化需要缓存的页面片段URI集合
     * 
     * @throws CacheException
     */
    private void initUrl() throws CacheException {
        String patterns = filterConfig.getInitParameter(FILTER_URL_PATTERNS);
        cacheURLs = StringUtils.split(patterns, ",");
    }

    @Override
    protected void doFilter(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws AlreadyCommittedException,
            AlreadyGzippedException, FilterNonReentrantException, LockTimeoutException, Exception {
        initUrl();
        String url = request.getRequestURI();
        if (useInclude(request)) {
            url = getRequestIncludeURI(request);
            LOG.debug("The current include url : {}", url);
        }

        // 如果包含我们要缓存的url 就缓存该页面，否则执行其他过滤器
        if (supportCache(url)) {
            LOG.debug("The current request cache key = {}", calculateKey(request));
            super.doFilter(request, response, chain);
        } else {
            chain.doFilter(request, response);
        }
    }

    /**
     * 判断当前请求是否是需要缓存的url
     * 
     * @param url
     * @return
     */
    private boolean supportCache(String url) {
        boolean flag = false;
        if (cacheURLs != null && cacheURLs.length > 0) {
            for (String cacheURL : cacheURLs) {
                if (url.contains(cacheURL.trim())) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 确定给定的请求是一个包含请求,也就是说,不是一个从外面进来顶级HTTP请求
     * <p/>
     * 比如进来顶级HTTP请求是/index,其实包含的请求可能就是 /index/focus
     * 
     * @param request
     * @return
     */
    protected boolean useInclude(HttpServletRequest request) {
        return WebUtils.isIncludeRequest(request);
    }

    /**
     * 确定给定的请求是一个包含请求参数,也就是说,不是一个从外面进来顶级HTTP请求参数
     * 
     * @param request
     * @return
     */
    protected boolean useQueryString(HttpServletRequest request) {
        // 如果使用页面包含的方式进行请求转发，当前的资源请求为你原始的请求，你必须从request.getAttribute("javax.servlet.include.query_string")来取得参数
        return (getRequestIncludeQueryString(request) != null);
    }

    /**
     * 计算一个包含请求对应的key
     * 
     * @param httpRequest
     * @return the key, included the URL plus request parameters
     */
    @Override
    protected String calculateKey(HttpServletRequest request) {
        StringBuffer keyBuffer = new StringBuffer();
        if (useInclude(request)) {
        	String uaClient = ClientUtil.getUaClient(request);
    		if (uaClient != null) {
    			//加入不同的客户端做为key
    			keyBuffer.append(uaClient);
    		}
            String url = getRequestIncludeURI(request);
            keyBuffer.append(url);
            if (useQueryString(request)) {
                String query = getRequestIncludeQueryString(request);
                query = RequestUtils.queryStringFilterOPT(query);
                keyBuffer.append(query);
            }
        }
        return keyBuffer.toString();
    }

    /**
     * 取得给定的请求是一个包含请求 如果使用页面包含的方式进行请求转发，当前的资源请求为你原始的请求，你必须从request.getAttribute(
     * "javax.servlet.include.servlet_path")来取得自己的uri
     * 
     * @param request
     * @return
     */
    private String getRequestIncludeURI(final HttpServletRequest request) {
        if (useInclude(request)) {
            return request.getAttribute(WebUtils.INCLUDE_SERVLET_PATH_ATTRIBUTE).toString();
        }
        return null;
    }

    /**
     * 取得给定的请求是一个包含请求参数
     * 
     * @param request
     * @return
     */
    private String getRequestIncludeQueryString(HttpServletRequest request) {
        if (request.getAttribute(WebUtils.INCLUDE_QUERY_STRING_ATTRIBUTE) != null) {
            return request.getAttribute(WebUtils.INCLUDE_QUERY_STRING_ATTRIBUTE).toString();
        }
        return null;
    }

    /**
     * 空实现，再次进入缓存数据成为可能
     */
    @Override
    protected void checkNoReentry(final HttpServletRequest httpRequest) throws FilterNonReentrantException {
    }

    /**
     * 配置页面缓存操作
     */
    protected boolean filterNotDisabled(final HttpServletRequest request) {
        // 线程中取出缓存参数设置
        CacheOption cp = ThreadVariableGlobal.getCacheOption();
        if (cp != null) {
            switch (cp) {
            case MISS:
                // 跳过不缓存
                return false;
            case REFRESH:
                // 清空再缓存
                blockingCache.put(new Element(calculateKey(request), null));
                return true;
            case HIT:
                return true;
            default:
                return true;
            }
        }
        return true;
    }
}
