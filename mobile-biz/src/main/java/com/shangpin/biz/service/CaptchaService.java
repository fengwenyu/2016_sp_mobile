package com.shangpin.biz.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 验证码相关接口
 * 
 * @author cuibinqiang
 */
public interface CaptchaService {

    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws IOException
     */
    public void writeImage(HttpServletRequest request, HttpServletResponse response) throws IOException;

    /**
     * 验证码是否一致
     * @param session
     * @param captcha 需要验证的验证码
     * @return 
     */
    public boolean verifyCaptcha(HttpSession session, String captcha);

}
