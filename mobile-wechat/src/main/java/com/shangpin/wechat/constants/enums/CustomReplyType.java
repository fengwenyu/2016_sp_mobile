package com.shangpin.wechat.constants.enums;

/**
 * Created by cuibinqiang on 2015/12/11.
 */
public enum CustomReplyType {

    TEXT("test"),//发送文本消息
    IMAGE("image"),//发送图片消息
    VOICE("voice"),//发送语音消息
    VIDEO("video"),//发送视频消息
    MUSIC("music"),//发送音乐消息
    NEWS("news"),//发送图文消息（点击跳转到外链） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
    MPNEWS("mpnews"),//发送图文消息（点击跳转到图文消息页面） 图文消息条数限制在8条以内，注意，如果图文数超过8，则将会无响应。
    WXCARD("wxcard"),//发送卡券
    ;
    private final String type;

    CustomReplyType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
