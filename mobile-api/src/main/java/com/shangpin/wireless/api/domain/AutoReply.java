package com.shangpin.wireless.api.domain;

import com.google.gson.reflect.TypeToken;
import com.shangpin.utils.JsonUtil;

import java.util.List;

/**
 * Created by cuibinqiang on 2015/12/4.
 */
public class AutoReply implements java.io.Serializable{

    private long id;
    private String name;
    private String type;
    private String keywords;
    private List<Keywords> keywordsList;
    private String keywordMode;
    private String reply;
    private List<Reply> replyList;
    private String replyType;
    private String status;

    private int textNum = 0;
    private int picNum = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getKeywordMode() {
        return keywordMode;
    }

    public void setKeywordMode(String keywordMode) {
        this.keywordMode = keywordMode;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getReplyType() {
        return replyType;
    }

    public void setReplyType(String replyType) {
        this.replyType = replyType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Keywords> getKeywordsList() {
        keywordsList = JsonUtil.fromJson(keywords, new TypeToken<List<Keywords>>(){} );
        return keywordsList;
    }

    public void setKeywordsList(List<Keywords> keywordsList) {
        this.keywordsList = keywordsList;
    }

    public List<Reply> getReplyList() {
        replyList = JsonUtil.fromJson(reply, new TypeToken<List<Reply>>(){} );
        return replyList;
    }

    public void setReplyList(List<Reply> replyList) {
        this.replyList = replyList;
    }

    public int getTextNum() {
        return textNum;
    }

    public void setTextNum(int textNum) {
        this.textNum = textNum;
    }

    public int getPicNum() {
        return picNum;
    }

    public void setPicNum(int picNum) {
        this.picNum = picNum;
    }
}




