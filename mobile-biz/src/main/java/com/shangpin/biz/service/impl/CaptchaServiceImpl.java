package com.shangpin.biz.service.impl;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shangpin.biz.service.CaptchaService;
import com.shangpin.utils.CaptchaCodeUtil;

@Service
public class CaptchaServiceImpl implements CaptchaService {

    public static final Logger logger = LoggerFactory.getLogger(CaptchaServiceImpl.class);

    private static final String CAPTCHA_TOKEN = "captchaToken";
    /*private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();
    private static Random random = new Random();

    static {
        // cs.setColorFactory(new SingleColorFactory(new Color(25, 60, 170)));
        cs.setColorFactory(new ColorFactory() {
            @Override
            public Color getColor(int x) {
                int[] c = new int[3];
                int i = random.nextInt(c.length);
                for (int fi = 0; fi < c.length; fi++) {
                    if (fi == i) {
                        c[fi] = random.nextInt(71);
                    } else {
                        c[fi] = random.nextInt(256);
                    }
                }
                return new Color(c[0], c[1], c[2]);
            }
        });
        RandomWordFactory wf = new RandomWordFactory();
        //wf.setCharacters("123456789abcdefghgkmnpqrstuvwxyzABCDEFGHGKLMNPQRSTUVWXYZ");
        wf.setCharacters("123456789");
        wf.setMaxLength(4);
        wf.setMinLength(4);
        cs.setWordFactory(wf);
    }*/

    /**
     * 生成验证码
     * 
     * @throws IOException
     */
    @Override
    public void writeImage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*switch (random.nextInt(5)) {
        case 0:
            cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
            break;
        case 1:
            cs.setFilterFactory(new MarbleRippleFilterFactory());
            break;
        case 2:
            cs.setFilterFactory(new DoubleRippleFilterFactory());
            break;
        case 3:
            cs.setFilterFactory(new WobbleRippleFilterFactory());
            break;
        case 4:
            cs.setFilterFactory(new DiffuseRippleFilterFactory());
            break;
        }*/
        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession();
        }
        setResponseHeaders(response);
        OutputStream os = response.getOutputStream();
        String token = null;//EncoderHelper.getChallangeAndWriteImage(cs, "png", os);
        token=CaptchaCodeUtil.img2Stream(os);
        session.setAttribute(CAPTCHA_TOKEN, token);
        logger.debug("SessionID=" + session.getId() + "，code=" + token);
        os.flush();
        os.close();

    }

    /**
     * 校验验证码
     */
    @Override
    public boolean verifyCaptcha(HttpSession session, String captcha) {
        boolean flag = true;
        try {
            String source = (String) session.getAttribute(CAPTCHA_TOKEN);
            flag = StringUtils.equalsIgnoreCase(captcha, source);
        } catch (Exception e) {
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    /**
     * 设置response的header
     * 
     * @param response
     */
    protected void setResponseHeaders(HttpServletResponse response) {
        // 显示类型
        response.setContentType("image/png");
        // 清除缓存
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        response.setDateHeader("Last-Modified", time);
        response.setDateHeader("Date", time);
        response.setDateHeader("Expires", time);
    }

}
