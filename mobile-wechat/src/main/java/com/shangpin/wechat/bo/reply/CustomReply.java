package com.shangpin.wechat.bo.reply;

/**
 * Created by cuibinqiang on 2015/12/11.
 */
public abstract class CustomReply {

    private String touser;
    private String msgtype;

    //以某个客服帐号来发消息
//    private String customservice;


    public String getMsgtype() {
        return msgtype;
    }

    public void setMsgtype(String msgtype) {
        this.msgtype = msgtype;
    }

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }
}
