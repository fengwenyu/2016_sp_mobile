package com.shangpin.wechat.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shangpin.utils.HttpClientUtil;
import com.shangpin.utils.JsonUtil;
import com.shangpin.wechat.constants.PublicPlatformConstants;
import com.shangpin.wechat.service.MaterialService;

/**
 *
 * Created by ZRS on 2015/12/14.
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    @Autowired
    WeChatPublicServiceImpl weChatPublicServiceImpl = new WeChatPublicServiceImpl();

    @Override
    public String getMaterialCount(){
        String url = PublicPlatformConstants.MATERIAL_LIST + "?access_token=" + weChatPublicServiceImpl.getToken();

        String result = null;
        try {
            result = HttpClientUtil.doGet(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getMaterialList(String type, int offset, int count) {
        String url = PublicPlatformConstants.MATERIAL_LIST + "?access_token=" + weChatPublicServiceImpl.getToken();

        Map<String, String> params = new HashMap<String, String>();
        params.put("type", type);
        params.put("offset", offset + "");
        params.put("count", count + "");
        String result = null;
        try {
            result = HttpClientUtil.doPostWithJSON(url, JsonUtil.toJson(params));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String getMaterial(String mediaId) {
        String url = PublicPlatformConstants.MATERIAL_GET + "?access_token=" + weChatPublicServiceImpl.getToken();

        Map<String, String> params = new HashMap<String, String>();
        params.put("mediaI_id", mediaId);
        String result = null;
        try {
            result = HttpClientUtil.doPostWithJSON(url, JsonUtil.toJson(params));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String addMaterial(String type, File media) {
        String url = PublicPlatformConstants.MATERIAL_ADD + "?type=" + type + "&access_token=" + weChatPublicServiceImpl.getToken();

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("media", media);
        String result = null;
        try {
            result = HttpClientUtil.doPostFile(url, params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public String delMaterial(String mediaId) {
        String url = PublicPlatformConstants.MATERIAL_DEL + "?access_token=" + weChatPublicServiceImpl.getToken();

        Map<String, String> params = new HashMap<String, String>();
        params.put("media_id", mediaId);
        String result = null;
        try {
            result = HttpClientUtil.doPostWithJSON(url, JsonUtil.toJson(params));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
