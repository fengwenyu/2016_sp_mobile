package com.shangpin.wechat.bo.reply;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cuibinqiang on 2015/12/11.
 */
public class ReplyText extends CustomReply {

    private Map<String, String> text = new HashMap<>();

    public Map<String, String> getText() {
        return text;
    }

    public void setText(String text) {
        this.text.put("content", text);
    }

}
