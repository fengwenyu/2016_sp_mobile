package com.shangpin.wireless.api.service.impl;

import com.shangpin.wechat.bo.reply.ReplyImage;
import com.shangpin.wechat.bo.reply.ReplyText;
import com.shangpin.wechat.service.WeChatPublicService;
import com.shangpin.wireless.api.dao.AutoReplyDao;
import com.shangpin.wireless.api.domain.AutoReply;
import com.shangpin.wireless.api.domain.Keywords;
import com.shangpin.wireless.api.domain.Reply;
import com.shangpin.wireless.api.service.WeXinPlatformService;
import com.shangpin.wireless.api.weixinbean.MsgReceived;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 业务理解:
 * 1.微信接受用户信息,返回"";并不直接返回文档要求的xml;
 * 2.用户收到的回复是调用的客服回复接口,可以调用多次回复多条
 * 3.在实现上 第2步后期可以采用异步方式,避免响应微信出现5秒超时,用户收到"此公众号暂停服务"的提示
 *
 * Created by cuibinqiang on 2015/12/9.
 */
@Service(WeXinPlatformService.SERVICE_NAME)
public class WeXinPlatformServiceImpl implements WeXinPlatformService {

    private final Log log = LogFactory.getLog(WeXinPlatformServiceImpl.class);
    @Resource(name = AutoReplyDao.DAO_NAME)
    private AutoReplyDao autoReplyDao;

    @Resource
    WeChatPublicService weChatPublicService;

    @Resource
    WeXinPlatform weXinPlatform;

    @Override
    public boolean business(MsgReceived msg, String token, WeChatPublicService weChatPublicService) {

        try {

            String msgType = msg.getMsgType();

            //自动回复只有文本消息
            if (!"text".equals(msgType)) {
                return false;
            }
            String content = msg.getContent();

            //查询回复规则
            Map<String, Map<String, AutoReply>> map = weXinPlatform.findAll();

            //精确匹配
            Map<String, AutoReply> allMap = map.get("allMap");

            //命中
            if (allMap.containsKey(content)) {
                AutoReply autoReply = allMap.get(content);
                sendAutoReply(msg, autoReply);
                return true;
            }

            //包含匹配
            Map<String, AutoReply> haveMap = map.get("haveMap");

            for (Map.Entry<String, AutoReply> entry : haveMap.entrySet()) {

                //命中
                if (content.contains(entry.getKey())) {
                    AutoReply autoReply = entry.getValue();
                    sendAutoReply(msg, autoReply);
                    return true;
                }
            }

            //没有命中关键字,走默认回复
            AutoReply autoReply = allMap.get("**default**");
            
            if(autoReply != null){
            	sendAutoReply(msg, autoReply);
            	return true;
            }            

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     *
     * @param autoReply
     */
    private void sendAutoReply(MsgReceived msg, AutoReply autoReply){
        List<Reply> replyList = autoReply.getReplyList();

        String replyType = autoReply.getReplyType();

        //全部回复
        if("1".equals(replyType)){
            for (Reply reply : replyList) {
                //发送回复
                sendReply(msg, reply);
            }
        }
        //随机回复
        if("0".equals(replyType)) {
            //随机选一个回复
            int index = 0;
            if(replyList.size()>1){
                index = new Random().nextInt(replyList.size()-1);
            }
            Reply reply = replyList.get(index);
            //发送回复
            sendReply(msg, reply);
        }
    }

    /**
     * 处理回复
     * @param reply
     */
    private void sendReply(MsgReceived msg, Reply reply){
        String type = reply.getType();
        String note = reply.getContent();

        String result = null;
        if("1".equals(type)){//文本回复
            ReplyText replyText = new ReplyText();
            replyText.setTouser(msg.getFromUserName());
            replyText.setMsgtype("text");
            replyText.setText(note);
            //调用客服回复接口
            result = weChatPublicService.sendMsg(replyText);
        }
        if("2".equals(type)) {//图片回复
            //调用图片回复接口
            ReplyImage replyImage = new ReplyImage();
            replyImage.setTouser(msg.getFromUserName());
            replyImage.setMsgtype("image");
            replyImage.setImage(note);
            result = weChatPublicService.sendMsg(replyImage);
        }
        log.info("[微信]调用接口返回信息 result="+result);
    }


}
