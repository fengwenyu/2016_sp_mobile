package com.shangpin.wechat.service;

import java.io.File;

/**
 * 微信素材管理
 * Created by ZRS on 2015/12/14.
 */
public interface MaterialService {

    /**
     * 获取素材总数量
     * @return
     */
    public String getMaterialCount();


    /**
     * 批量获取永久素材
     * @param type 类型
     * @param offset 起始位置
     * @param count 需要数量
     */
    public String getMaterialList(String type, int offset, int count);

    /**
     * 获取多媒体
     * @param mediaId 多媒体id
     * @return String
     */
    public String getMaterial(String mediaId);

    /**
     * 添加多媒体
     * @param type 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @param media form-data中媒体文件标识，有filename、filelength、content-type等信息
     * @return String
     */
    public String addMaterial(String type, File media);

    /**
     * 删除多媒体
     * @param mediaId 多媒体id
     * @return String
     */
    public String delMaterial(String mediaId);


}
