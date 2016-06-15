package com.shangpin.mobileAolai.platform.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;

import com.shangpin.mobileAolai.common.util.Constants;
import com.shangpin.mobileAolai.common.util.WebUtil;
import com.shangpin.mobileAolai.platform.vo.AccountVO;
import com.shangpin.mobileAolai.platform.vo.ConsigneeAddressVO;





@ParentPackage("mobileAolai")
@Scope("prototype")
@Actions({ @Action(value = ("/oauthFanliAction"), results = { @Result(name = "unAuthzUrl", location = "/WEB-INF/pages/market/brandList.jsp"),
        @Result(name = "authzUrl", location = "/WEB-INF/pages/flagshop/flagshopProducts.jsp") }) })
public class OAuthFanliAction {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 
     * @param tracking_id
     *            效果追踪识别码,预留32位 写入COOKIES，写入订单表
     * @param channel_id
     *            返利网唯一标识, 预留32位 写入COOKIES，写入订单表，不可自定义
     * @param u_id
     *            返利网会员编号 写入COOKIES，并写入用户表和订单表
     * @param target_url
     *            商城目标页面地址, 预留512位 需Urldecode解码，为空时跳转商城首页
     * @param tracking_code
     *            效果追踪识别码,预留32位 写入COOKIES，写入订单表
     * @param code
     *            登录验证参数,预留32位 验证方法（登录API常见问题集有说明）
     * @param syncname
     *            联合登录判断参数:Syncname＝true 联合登录，Syncname＝false或Syncname=""非联合登录
     * @param username
     *            联合登陆的用户名，唯一性。ID@51fanli,预留64位 写入用户表和订单表，不可修改（登录API常见问题集有说明）
     * @param usersafekey
     *            联合登录验证码, 预留16位 写入用户表，用于第二次登录时做验证。如有解绑功能，解绑即是清除本字段值。
     * @param action_time
     *            UNIX时间戳 验证方法（登录API常见问题集有说明）
     * @param email
     *            用户email ,预留64位 （登录API常见问题集有说明）
     * @param show_name
     *            用户昵称, 预留64位 需Urldecode解码
     * @param syncaddress
     *            同步收货人信息判断参数:syncaddress＝true
     *            同步收货人信息，syncaddress＝false或syncaddress ="" 不同步收货人信息
     * @param name
     *            姓名, 预留64位 需Urldecode解码，
     * @param province
     *            省, 预留32位 需Urldecode解码
     * @param city
     *            市, 预留50位 需Urldecode解码
     * @param area
     *            区, 预留32位 需Urldecode解码
     * @param address
     *            地址, 预留64位 需Urldecode解码
     * @param zip
     *            邮编, 预留32位
     * @param phone
     *            电话, 预留32位
     * @param mobile
     *            手机, 预留32位
     * @return
     * @throws Exception
     */
    public void index() throws Exception {
        log.info("**************************************************");
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();

        String channel_id = request.getParameter("channel_id");
        String u_id = request.getParameter("u_id");
        String target_url = request.getParameter("target_url");// urlencode
        String tracking_code = request.getParameter("tracking_code");
        String tracking_id = request.getParameter("tracking_id");
        String username = request.getParameter("username");
        String code = request.getParameter("code");
        String syncname = request.getParameter("syncname");
        //String usersafekey = request.getParameter("usersafekey");
        String show_name = request.getParameter("utf-8");// urlencode
        String action_time = request.getParameter("action_time");
        //String syncaddress = request.getParameter("syncaddress");
        String name = request.getParameter("name");// urlencode
        name = getStringFromGB2312ToUtf8(name);
        //String province = request.getParameter("province");// urlencode
        //String city = request.getParameter("city");// urlencode
        //String area = request.getParameter("area");// urlencode
        //String address = request.getParameter("address"); // urlencode
        String mobile = request.getParameter("mobile");
        //String zip = request.getParameter("zip");

        log.info("tracking_code" + tracking_code);
        log.info("channel_id" + channel_id);
        log.info("u_id" + u_id);
        Cookie c = new Cookie("tracking_code", tracking_code);
        response.addCookie(c);
        c = new Cookie("channel_id", channel_id);
        response.addCookie(c);
        c = new Cookie("u_id", String.valueOf(u_id));
        response.addCookie(c);
        c = new Cookie("tracking_id", String.valueOf(tracking_id));
        response.addCookie(c);

        String channelValue = "51fanli|" + u_id + "|" + tracking_code + "|";

        StringBuffer proStr = new StringBuffer();
        proStr.append(getMainWebValue(channelValue));
        proStr.append("&");
        proStr.append(new Date().getTime());
        c = new Cookie("promotion1st", proStr.toString());
        response.addCookie(c);

        String shangpinUsername = u_id + "@51fanli.com";
        log.info("目标地址Target_url:" + target_url);
        /*target_url = URLDecoder.decode((null == target_url) || ("".equals(target_url)) ? "http://www.aolai.com/":target_url, "gb2312");
        String preUrl = target_url.split("\\?")[0];
        String endUrl = "";
        if (target_url.split("\\?").length > 1) {
            endUrl = URLEncoder.encode(target_url.split("\\?")[1], "utf-8");
        }
        target_url = preUrl + "?" + endUrl;*/
        if(target_url.indexOf("aolai")<0){
            log.info("域名中没有包含aolai跳转" + target_url);
            target_url = Constants.AOLAI_URL;
        }
        // 未同步
        if (!"true".equals(syncname)) {
            log.info("不同步时候跳转" + target_url);
            response.sendRedirect(target_url);
            return;
        }
        if ((System.currentTimeMillis() - Long.valueOf(action_time) * 1000) > 300 * 1000) {
            log.info("超时跳转" + target_url);
            response.sendRedirect(target_url);
            return;
        }

        String shopKey = "354441a781d52848";
        String hashCode = DigestUtils.md5Hex(username + shopKey + action_time);
        if (!code.equals(hashCode)) {
            log.info("验签错误跳转" + target_url);
            response.sendRedirect(target_url);
            return;
        }
        //查询Username是否已存在或者是否已绑定账号
        AccountVO account = findUser(shangpinUsername);
        Map<String, String> createUser = new HashMap<String, String>();
        createUser.put("mailaddress", shangpinUsername);
        createUser.put("password", "12345678");
        createUser.put("gender", "2");
        createUser.put("mobile", mobile);
        createUser.put("name", show_name);
        createUser.put("realname", name);
        createUser.put("invitecode", "");
        createUser.put("invitefriendcode", "");
        if (!Constants.SUCCESS.equals(account.getCode())) { // 没有用户创建
            log.info("用户已经不存在");
            log.info("开始创建用户");
            String createRes = createUser(createUser);
            account = account.json2Obj(createRes);
            log.info("开始同步地址");
            // sysAddress(shangpinUsername, account.getUserid(), name, province,
            // city, area, address, mobile, zip);
            log.info("模拟用户登陆");
            request.getSession().setAttribute(WebUtil.SESSION_USER_PARAM, account);
        } else {
            log.info("用户已经存在");
            if (getAddressNum(account.getUserid()) < 1) {
                log.info("存在的用户没有收货地址，同步收货地址");
                // sysAddress(shangpinUsername, account.getUserid(), name,
                // province, city, area, address, mobile, zip);
            }
            log.info("模拟用户登陆，用户是否为空" + (null == account));
            request.getSession().setAttribute(WebUtil.SESSION_USER_PARAM, account);
        }
        log.info("登陆后跳转" + target_url);
        response.sendRedirect(target_url);

    }

    /**
     * 创建用户
     * 
     */
    private String createUser(Map<String, String> params) {
        String url = Constants.BASE_URL + "/register";
        if (params.get("type") == null) {
			params.put("type", "0");
		}
        String data = null;
        try {
            log.info("主网站创建用户 :" + url + "；参数为：" + params);
            data = WebUtil.readContentFromGet(url, params);
            log.info("主网站创建用户返回结果 :" + data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;

    }

    /**
     * 获取主网站的字符串
     * 
     * @return
     */
    private String getMainWebValue(String channelValue) {
        String url = Constants.SP_COUNT_URL + "/cps_fanliwap.aspx";
        Map<String, String> map = new HashMap<String, String>();
        map.put("channel_id", "51fanli");
        map.put("channel_value", channelValue);
        String data = null;
        try {
            log.info("主网站获取信息 :" + url + "；参数为：" + map);
            data = WebUtil.readContentFromGet(url, map);
            log.info("主网站获取信息 结果:" + data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 检查是有该用户
     * 
     * @param username
     * @return
     */
    private AccountVO findUser(String username) {
        String url = Constants.BASE_URL_AL_SP + "/checkUsername";
        Map<String, String> map = new HashMap<String, String>();
        map.put("username", username);

        String data = null;
        try {
            log.info("主网站查找用户:" + url + "；参数为：" + map);
            data = WebUtil.readContentFromGet(url, map);
            log.info("主网站查找用户结果:" + data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AccountVO account = new AccountVO();
        account = account.json2Obj(data);
        return account;
    }

    @SuppressWarnings("unused")
	private void sysAddress(String username, String uid, String consigneename, String province, String city, String area, String address, String mobile, String postCode)
            throws Exception {
        province = getStringFromGB2312ToUtf8(province);
        city = getStringFromGB2312ToUtf8(city);
        area = getStringFromGB2312ToUtf8(area);
        address = getStringFromGB2312ToUtf8(address);

        address = province + city + area + address;
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", uid);
        params.put("consigneename", StringUtils.isEmpty(consigneename) ? username : consigneename);
        params.put("province", "-1");
        params.put("city", "-1");
        params.put("area", "-1");
        params.put("address", address);
        params.put("postcode", postCode);
        params.put("tel", mobile);
        params.put("setd", "true");

        String url = Constants.BASE_URL + "/AddConsigneeAddress";
        WebUtil.readContentFromGet(url, params);
    }

    @SuppressWarnings({ "unchecked", "static-access" })
	private int getAddressNum(String userid) throws Exception {
        String url = Constants.BASE_URL + "/getconsigneeaddress";
        Map<String, String> params = new HashMap<String, String>();
        params.put("userid", userid);
        List<ConsigneeAddressVO> list = null;
        String json = null;
        try {
            // 获取收货人地址json格式字符串
            json = WebUtil.readContentFromGet(url, params);
        } catch (Exception e) {
            e.printStackTrace();
            json = null;
        }
        if (null != json && !"".equals(json)) {
            JSONObject jsonObj = JSONObject.fromObject(json);
            if (null != jsonObj && Constants.SUCCESS.equals(jsonObj.get("code"))) {
                JSONObject contentObj = (JSONObject) jsonObj.get("content");
                if (!"[ ]".equals(contentObj.get("list").toString())) {
                    JSONArray array = (JSONArray) contentObj.get("list");
                    // 参数1为要转换的JSONArray数据，参数2为要转换的目标数据类，即List装载的对象数据类
                    list = array.toList(array, new ConsigneeAddressVO(), new JsonConfig());
                }
            }
        }
        return list.size();
    }

    private String getStringFromGB2312ToUtf8(String from) throws UnsupportedEncodingException {
        if (StringUtils.isEmpty(from)) {
            return "";
        }
        from = URLDecoder.decode(from, "gb2312");
        String to = URLEncoder.encode(from, "utf-8");
        return to;
    }

}
