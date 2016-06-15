package com.shangpin.wireless.api.view.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 给小能提供获取token接口
 *  在小能请求尚品接口时 都要传入此token值,尚品校验token之后才允许访问, 这步暂时没做
 *
 * <br/>
 * Date: 2016/5/27<br/>
 *
 * @author ZRS
 * @since JDK7
 */
public class WeixinXiaoNengToken extends BaseServlet {

    String SHANGPIN_GRANT_TYPE= "client_credential";
    String SHANGPIN_APPID= "shangpin_4006900900";
    String SHANGPIN_SECRET= "shDG5adDJL4d65f1eGD54F5e78eddIJAd4Y8d4f";

    //token暂时固定
    String SHANGPIN_TOKEN= "ijfj5f838tpdewqmB917HYFA58TYMNNzcpzoJDFEX79AD5GE5asd8g4e3TPJORETUW881320YHGKFAGh6t4s6f";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //此处应该有接口次数限制, 小能出问题会影响尚品客服,还是不做了


        //接受参数部分
        String grantType = req.getParameter("grant_type");
        String appid = req.getParameter("appid");
        String secret = req.getParameter("secret");


        //目前只有 SHANGPIN_GRANT_TYPE 一种类型
        if(SHANGPIN_GRANT_TYPE.equals(grantType)){

            //验证id与secret
            if(SHANGPIN_APPID.equals(appid) && SHANGPIN_SECRET.equals(secret)){

                sendMessage(resp, "{\"access_token\":\"" + SHANGPIN_TOKEN + "\",\"expires_in\":7200}");
                return;
            }

            sendMessage(resp, "{\"errcode\":40013,\"errmsg\":\"invalid appid or secret\"}");
            return;
        }

        //grant_type错误情况
        sendMessage(resp, "{\"errcode\":40013,\"errmsg\":\"invalid grant_type\"}");

    }
}
