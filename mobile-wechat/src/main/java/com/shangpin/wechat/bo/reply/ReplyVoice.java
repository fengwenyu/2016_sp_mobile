package com.shangpin.wechat.bo.reply;

import java.util.Map;

/**
 * Created by cuibinqiang on 2015/12/11.
 */
public class ReplyVoice extends CustomReply {

    private Map<String,String> voice;

    public Map<String, String> getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice.put("media_id",voice);
    }
}
