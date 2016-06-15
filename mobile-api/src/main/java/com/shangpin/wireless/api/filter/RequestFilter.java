package com.shangpin.wireless.api.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.shangpin.pay.utils.common.StringUtil;

/**
 * 
 * @author sunweiwei
 *
 */
public class RequestFilter extends OncePerRequestFilter {

    private static String DEFAULT_CHARACTER = "UTF-8";

    public String filter(HttpServletRequest request, String input) {
        String ret = input;
        int minVersion=271;//271之后的版本支持urlencode
        String version = request.getHeader("ver");// 版本号
        if (StringUtil.isEmpty(version)) {
            return ret;
        }
        try {
            int currentVersion = Integer.valueOf(version.replaceAll("\\.", ""));
            if (StringUtil.isNotEmpty(ret) && currentVersion > minVersion) {
                return URLDecoder.decode(ret, DEFAULT_CHARACTER);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(new HttpServletRequestWrapper(request) {
            @Override
            public String getParameter(String name) {
                String value = super.getParameter(name);
                return filter(this, value);
            }

            @Override
            public String[] getParameterValues(String name) {
                String[] values = super.getParameterValues(name);
                if (values == null) {
                    return null;
                }
                for (int i = 0; i < values.length; i++) {
                    values[i] = filter(this, values[i]);
                }
                return values;
            }

        }, response);

    }
}