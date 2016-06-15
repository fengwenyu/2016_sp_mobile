package com.shangpin.wechat.bo.reply;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cuibinqiang on 2015/12/11.
 */
public class ReplyImage extends CustomReply {

    private Map<String, String> image = new HashMap<>();

    public  Map<String, String> getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image.put("media_id",image);
    }
}
