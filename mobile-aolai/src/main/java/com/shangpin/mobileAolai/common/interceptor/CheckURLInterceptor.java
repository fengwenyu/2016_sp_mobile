package com.shangpin.mobileAolai.common.interceptor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.shangpin.mobileAolai.common.util.SysContent;

/**
 * XSS漏洞拦截器
 * 
 * @Author wangwenguan
 * @CreatDate 2013-10-20
 */
@SuppressWarnings("serial")
public class CheckURLInterceptor extends AbstractInterceptor {
    public String intercept(ActionInvocation invocation) throws Exception {
        HttpServletRequest request = SysContent.getRequest();
        String query = request.getQueryString();
        if (query != null && query.length() > 0) {
            if (!query.contains("51fanli")) {
                // 过滤特殊字符
                boolean isNotValidate = (query.indexOf("--") > 0) || (query.indexOf("%22") > 0) // "
                        || (query.indexOf("%3C") > 0) // <
                        || (query.indexOf("%3E") > 0) // >
                        // || (query.indexOf("\'")>0) // "
                        // || (query.indexOf(";")>0)
                        || (query.indexOf("?") > 0) || (query.indexOf("script") > 0);
                // System.out.println("222CheckURLInterceptor query : isNotValidate = "
                // + isNotValidate + "; " + Thread.currentThread().getName());
                if (isNotValidate) {
                    return com.opensymphony.xwork2.Action.ERROR;
                }
            }
        }

        // System.out.println("000CheckURLInterceptor : url = " +
        // request.getRequestURL() + "; query = " + query + "; " +
        // Thread.currentThread().getName());
        // System.out.println("111CheckURLInterceptor : url = " +
        // request.getRequestURL() + "; ch = " + request.getParameter("ch") +
        // "; " + Thread.currentThread().getName());
        String ch = (String) request.getParameter("ch");
        if (ch != null && ch.length() > 0) {
            Pattern pattern = Pattern.compile("[0-9]{1,6}");
            Matcher m = pattern.matcher(ch);
            boolean isNum = m.matches();
            // System.out.println("222CheckURLInterceptor : isNum = " + isNum +
            // "; " + Thread.currentThread().getName());
            if (!isNum) {
                return com.opensymphony.xwork2.Action.NONE;
            }
        }
        return invocation.invoke();
    }
}