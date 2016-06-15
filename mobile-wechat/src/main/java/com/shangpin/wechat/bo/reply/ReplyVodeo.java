package com.shangpin.wechat.bo.reply;

import com.shangpin.utils.StringUtil;

import java.util.Map;

/**
 * Created by cuibinqiang on 2015/12/11.
 */
public class ReplyVodeo extends CustomReply {

    private Map<String,String> video;

    public Map<String, String> getVideo() {
        return video;
    }

    /**
     *
     * @param mediaId 视频id
     * @param thumbMediaId 缩略图
     */
    public void setVideo(String mediaId,String thumbMediaId ) {
        this.video.put("media_id", mediaId);
        this.video.put("thumb_media_id", thumbMediaId);
    }
    /**
     *
     * @param mediaId 视频id
     * @param thumbMediaId 缩略图
     * @param title 标题 可不传
     * @param description 描述 可不传
     */
    public void setVideo(String mediaId,String thumbMediaId, String title, String description ) {
        this.video.put("media_id", mediaId);
        this.video.put("thumb_media_id", thumbMediaId);
        if(!StringUtil.isBlank(title))
            this.video.put("title", title);
        if(!StringUtil.isBlank(description))
            this.video.put("description", description);
    }

}
