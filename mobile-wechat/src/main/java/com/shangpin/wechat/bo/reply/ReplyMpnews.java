package com.shangpin.wechat.bo.reply;

import java.util.Map;

/**
 * 发送图文消息（点击跳转到图文消息页面） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
 * Created by cuibinqiang on 2015/12/11.
 */
public class ReplyMpnews extends CustomReply {

    private Map<String,String> mpnews;

    public Map<String,String> getMpnews() {
        return mpnews;
    }

    public void setMpnews(String mpnews) {
        this.mpnews.put("media_id", mpnews);
    }
}
