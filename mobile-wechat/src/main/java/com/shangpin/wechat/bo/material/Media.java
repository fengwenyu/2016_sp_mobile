package com.shangpin.wechat.bo.material;

import java.io.Serializable;

/**
 * Created by cuibinqiang on 2015/12/14.
 */
public class Media implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String media_id;
    private String update_time;
    private Content content;

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
