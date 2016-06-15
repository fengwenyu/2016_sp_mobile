package com.shangpin.wireless.api.service;

import com.shangpin.wechat.service.WeChatPublicService;
import com.shangpin.wireless.api.weixinbean.MsgReceived;

/**
 *
 * Created by ZRS on 2015/12/9.
 */
public interface WeXinPlatformService {

    public final static String SERVICE_NAME = "com.shangpin.wireless.api.service.impl.WeXinPlatformServiceImpl";


    /**
     * 查询数据库
     */
    public boolean business(MsgReceived msg, String token, WeChatPublicService weChatPublicService);




}
