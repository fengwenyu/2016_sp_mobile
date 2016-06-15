package com.shangpin.wechat.constants;

/**
 * create on 2014-10-08
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shangpin.wechat.utils.common.WeChatPropertyUtil;

/**
 * @author sunweiwei
 * @version v1.0
 */
public class PublicPlatformConstants extends CommonConstants {
    public static Logger logger = LoggerFactory.getLogger(PublicPlatformConstants.class);

    private static final String BASE_URL = "https://api.weixin.qq.com";
    private static final String BASE_CGI = BASE_URL + "/cgi-bin";

    public static final String PUBLIC_PLATFORM_CACHE = "WeChatPublicPlatform";
    
    public static final String PUBLIC_GRANT_TYPE = "client_credential";
    
    public static final String PUBLIC_JSAPI_TYPE = "jsapi";
    
    public static final String GRANT_TYPE = "authorization_code";
    
    public static final String PUBLIC_TOKEN_URL = WeChatPropertyUtil.getStrValue("public_token_url","https://api.weixin.qq.com/cgi-bin/token");
    
    public static final String PUBLIC_TICKET_URL = WeChatPropertyUtil.getStrValue("public_ticket_url","https://api.weixin.qq.com/cgi-bin/ticket/getticket");
    
    public static final String PUBLIC_APP_ID = WeChatPropertyUtil.getStrValue("public_app_id","wx4e93df954af2f52c");
	
    public static final String PUBLIC_APP_SECRET = WeChatPropertyUtil.getStrValue("public_app_secret","2a55eede9fbd467e25e6a0eb7b17ce60");
    
    public static final String SHANGPIN_URL = WeChatPropertyUtil.getStrValue("shangpin_url","http://m.shangpin.com");
    
    public static final String AUTHORIZE = "https://open.weixin.qq.com/connect/oauth2/authorize";
    
    public static final String ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";
    
    public static final String USER_INFO = "https://api.weixin.qq.com/sns/userinfo";
    
    public static final String USER_INFO_BASE = "https://api.weixin.qq.com/cgi-bin/user/info";
    
    public static final String SEND_MSG = "https://api.weixin.qq.com/cgi-bin/message/custom/send";
    
    public static final String QRCODE_CREATE = "https://api.weixin.qq.com/cgi-bin/qrcode/create";
    
    public static final String SHOW_QRCODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode";
    
    public static final String UPLOAD = "http://file.api.weixin.qq.com/cgi-bin/media/upload";
    /** 创建卡券 */
    public static final String CREATE_CARD = "https://api.weixin.qq.com/card/create";
    /** 查询卡券 */
    public static final String QUERY_CARD_CODE = "https://api.weixin.qq.com/card/code/get";
    /** 核销卡券 */
    public static final String CONSUME_CARD_CODE = "https://api.weixin.qq.com/card/code/consume";
    /** 上传logo */
    public static final String UPLOAD_LOGO = "https://api.weixin.qq.com/cgi-bin/media/uploadimg";
    /** 查询门店列表 */
    public static final String QUERY_POI_LIST = "https://api.weixin.qq.com/cgi-bin/poi/getpoilist";
    /** 获取用户已领卡券*/
    public static final String QUEYR_USER_CARD="https://api.weixin.qq.com/card/user/getcardlist";
    /** 批量查询卡券列表*/
    public static final String QUEYR_BATCH_CARD="https://api.weixin.qq.com/card/batchget";
    /** 查询卡券详情*/
    public static final String QUERY_CARD_DETAIL="https://api.weixin.qq.com/card/get";    
    /** 发送模板信息*/
    public static final String SEND_TEMPLATE_MSG="https://api.weixin.qq.com/cgi-bin/message/template/send";
    
    /**菜单创建*/
    public static final String MENU_CREATE = "https://api.weixin.qq.com/cgi-bin/menu/create";
    /**菜单查询*/
    public static final String MENU_GET = "https://api.weixin.qq.com/cgi-bin/menu/get";
    /**菜单删除*/
    public static final String MENU_DEL = "https://api.weixin.qq.com/cgi-bin/menu/delete";

    /**素材**/
    public static final String MATERIAL_COUNT = BASE_CGI + "/material/get_materialcount";
    public static final String MATERIAL_LIST = BASE_CGI + "/material/batchget_material";
    public static final String MATERIAL_GET = BASE_CGI + "/material/get_material";
    public static final String MATERIAL_ADD = BASE_CGI + "/material/add_material";
    public static final String MATERIAL_DEL = BASE_CGI + "/material/del_material";

}
