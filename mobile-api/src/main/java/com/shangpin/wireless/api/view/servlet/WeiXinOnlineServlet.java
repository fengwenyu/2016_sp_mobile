package com.shangpin.wireless.api.view.servlet;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.shangpin.wechat.service.WeChatPublicService;
import com.shangpin.wireless.api.domain.WeixinMessage;
import com.shangpin.wireless.api.service.WeixinMessageService;
import com.shangpin.wireless.api.util.HqlHelper;
import com.shangpin.wireless.api.util.ProReader;
import com.shangpin.wireless.api.util.WebUtil;

/**
 * 微信客服接手返回数据
 * 
 * @Author:wangfeng
 * @CreatDate: 2014-03-19
 */
public class WeiXinOnlineServlet extends BaseServlet {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    private static final String URL_BASE = "https://api.weixin.qq.com/cgi-bin/";
    private WeixinMessageService weixinMessageService;
    private static final Log log = LogFactory.getLog(WeiXinOnlineServlet.class.getSimpleName());
    WeChatPublicService weiXinService = null;

    @Override
    public void init() throws ServletException {
        weiXinService = (WeChatPublicService) getBean(WeChatPublicService.class);
        weixinMessageService = (WeixinMessageService) getBean(WeixinMessageService.class);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        try {        	
            StringBuffer info = new StringBuffer();
            InputStream in = request.getInputStream();
            BufferedInputStream buf = new BufferedInputStream(in);
            byte[] buffer = new byte[1024];
            int iRead;
            while ((iRead = buf.read(buffer)) != -1) {
                info.append(new String(buffer, 0, iRead, "UTF-8"));
            }
            
            log.info("[微信] 客服回复消息调用微信客服msg="+info);
            if (!"".equals(info) && info != null) {
                sendMsg(info.toString());
            }

        } catch (Exception e) {
            // Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void sendMsg(String msg) {

        String accesstoken = weiXinService.getToken();
        if (accesstoken == null) {
            log.info("[微信] 客服回复accesstoken is null");
            return;
        }
        String url = URL_BASE + "message/custom/send?access_token=" + accesstoken;
        try {
            String data = WebUtil.readContentFromPost(url, msg);
            
            log.info("[微信] 客服回复消息调用微信客服接口返回data="+data);
            JSONObject obj = JSONObject.fromObject(data);
            final int errcode = obj.getInt("errcode");
            if (0 == errcode) { // 发送成功
                log.debug("send success");
                HqlHelper hqlHelper = new HqlHelper(WeixinMessage.class, "aa");
                hqlHelper.addWhereCondition("aa.fromUser=?", parseJsonOpenid(msg));
                hqlHelper.addOrderByProperty("aa.createTime", false);
                WeixinMessage message = weixinMessageService.getByCondition(hqlHelper);
                if (message == null || message.getReserve0() == null || "".equals(message.getReserve0())) {
                    log.debug("edit service status ON");
                    message.setReserve0("ON");
                    weixinMessageService.update(message);
                }
                if (parseJsonText(msg).indexOf("咨询已结束") > -1) {
                    log.debug("enter into consult end");
                    HqlHelper hqlHelper1 = new HqlHelper(WeixinMessage.class, "aa");
                    hqlHelper1.addWhereCondition("aa.fromUser=?", parseJsonOpenid(msg));
                    hqlHelper1.addOrderByProperty("aa.createTime", false);
                    WeixinMessage message1 = weixinMessageService.getByCondition(hqlHelper1);
                    message1.setReserve0("");
                    weixinMessageService.update(message1);
                }
            } else if (42001 == errcode) { // access_token超时
                log.debug("---111---access_token timeout，repeat send---111---" + accesstoken);
                accesstoken = weiXinService.getToken();
                sendMsg(msg);
                log.debug("---222---access_token timeout，repeat send---222---" + accesstoken);
            } else if (45015 == errcode) { // 用户24小时内没有与官微交互，不能推送消息
                log.debug("Amounted to less than 24 hours ,Don't push the message");
            } else {
                log.debug("data :" + data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("WeiXinOnlineServlet :" + e);
        }

    }

    public static String parseJsonText(String content) {
        JSONObject jsonObj = JSONObject.fromObject(content);
        String tent = null;
        if ("text".equals(jsonObj.get("msgtype").toString())) {
            JSONObject textObj = (JSONObject) jsonObj.get("text");
            tent = textObj.get("content").toString();
        }
        return tent;
    }

    public static String parseJsonOpenid(String content) {
        JSONObject jsonObj = JSONObject.fromObject(content);
        String openid = null;
        openid = jsonObj.get("touser").toString();
        return openid;
    }

    public static void writeLog(String log) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String fileName = sdf.format(new Date()) + ".txt";
            String path = ProReader.getInstance().getProperty("weixinOperationLogsPath");
            File filePath = new File(path);
            if (!filePath.exists())
                filePath.mkdirs();
            RandomAccessFile randomFile = new RandomAccessFile(path + fileName, "rw");
            long fileLength = randomFile.length();
            randomFile.seek(fileLength);
            randomFile.write((log + "\n").getBytes());
            randomFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        String msg = "{\"touser\":\"oFHXijvkFXv7ypscJ-jl3rP3O4lY\",\"msgtype\":\"text\",\"text\":{\"content\":\"咨询已结束。\"}}";
        if (parseJsonText(msg).indexOf("咨询已结束") > -1) {
            // System.out.println("进入咨询已结束");

        }

    }

}
