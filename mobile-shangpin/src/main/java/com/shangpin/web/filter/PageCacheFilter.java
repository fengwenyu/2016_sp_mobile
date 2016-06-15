package com.shangpin.web.filter;

import javax.servlet.http.HttpServletRequest;

import net.sf.ehcache.Element;
import net.sf.ehcache.constructs.web.filter.SimplePageCachingFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangpin.biz.utils.ClientUtil;
import com.shangpin.utils.CacheOption;
import com.shangpin.utils.RequestUtils;
import com.shangpin.utils.ThreadVariableGlobal;

/**
 * 页面缓存过滤器用于缓存整个请求资源
 * 
 * @author zghw
 *
 */
public class PageCacheFilter extends SimplePageCachingFilter {
	 private static final Logger LOG = LoggerFactory.getLogger(PageCacheFilter.class);
	/**
	 * The name of the filter. This should match a cache name in ehcache.xml
	 */
	public static final String NAME = "PageCacheFilter";

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

	/**
	 * 复写生成缓存的key，主要过滤OPT参数
	 */
	protected String calculateKey(HttpServletRequest httpRequest) {
		StringBuffer stringBuffer = new StringBuffer();
		String uaClient = ClientUtil.getUaClient(httpRequest);
		if (uaClient != null) {
			//加入不同的客户端做为key
			stringBuffer.append(uaClient);
		}
		String queryString = RequestUtils.queryStringFilterOPT(httpRequest.getQueryString());
		stringBuffer.append(httpRequest.getMethod()).append(httpRequest.getRequestURI()).append(queryString);
		String key = stringBuffer.toString();
		LOG.debug("cache page key :  " + key);
		return key;
	}
}
