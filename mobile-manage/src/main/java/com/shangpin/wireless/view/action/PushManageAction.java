package com.shangpin.wireless.view.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dbay.apns4j.model.Payload;
import com.opensymphony.xwork2.ActionContext;
import com.shangpin.wireless.base.action.BaseAction;
import com.shangpin.wireless.domain.Account;
import com.shangpin.wireless.domain.Constants;
import com.shangpin.wireless.domain.Product;
import com.shangpin.wireless.domain.PushDictionary;
import com.shangpin.wireless.domain.PushManageAndroid;
import com.shangpin.wireless.domain.PushManageIos;
import com.shangpin.wireless.domain.ReturnObject;
import com.shangpin.wireless.util.HqlHelper;
import com.shangpin.wireless.util.JsonUtil;
import com.shangpin.wireless.util.SqlHelper;
import com.shangpin.wireless.util.StringUtils;
import com.shangpin.wireless.util.WebUtil;

/**
 * 推送消息配置Action
 * 
 * @Author zhouyu
 * @CreatDate 2013-02-21
 */
@Controller
@Scope("prototype")
public class PushManageAction extends BaseAction<PushManageIos> {
    private static final long serialVersionUID = 1L;
    protected final Log log = LogFactory.getLog(PushManageAction.class);
    private String CHARSET = "UTF-8";
    private int page = 1;
    private int rows;

    // 奥莱详情
    private String goodsid;
    private String type;
    private String categoryno;
    // 尚品详情
    private String productid;
    private String topicid;

    private String url;
    private String actid;
    private String appuri;
    private String openappuri;
    private String title;
    // 错误信息
    private String error;

    /**
     * 跳转至添加页面
     * 
     * @Author zhouyu
     * @CreatDate 2013-02-21
     * @param
     * @Return
     */
    public String addUI() {
        try {
            List<Product> productList = productService.findAll();
            List<PushDictionary> pushDataList=pushDictionaryService.findList();
            PushDictionary pd=new PushDictionary();
            pd.setId(-1L);
            pd.setDictName("全部");
            pushDataList.add(pd);
            Collections.reverse(pushDataList);
            ActionContext.getContext().put("productList", productList);
            ActionContext.getContext().put("pushDataList", pushDataList);
            ServletActionContext.getRequest().setAttribute("error", error);
        } catch (Exception e) {
            log.error("PushAction-addUI()" + e);
        }
        return "addUI";
    }

    /**
     * 添加产品
     * 
     * @Author zhouyu
     * @CreatDate 2013-02-21
     * @param
     * @Return
     */
    public String add() {
        try {
            if (StringUtils.isNotEmpty(model.getUsername())) {
                HqlHelper hqlHelper = new HqlHelper(Account.class, "a");
                hqlHelper.addWhereCondition("a.loginName=?", model.getUsername());
                Account user = accountService.getByCondition(hqlHelper);
                if (user != null) {
                    model.setUserId(user.getUserId());
                } else {
                    ActionContext.getContext().put("error", "用户名不存在");
                    return "toAddUI";
                }
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            model.setCreateTime(new Date());
            model.setPushType(0);
            Long productNum = model.getProductNum();
            if ("detail".equals(model.getAction()) && productNum != null && (productNum.intValue() == 1 || productNum.intValue() == 101)
                    && StringUtils.isNotEmpty(goodsid, categoryno, type)) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("goodsid", goodsid);
                map.put("type", type);
                map.put("categoryno", categoryno);
                map.put("picw", "420");
                map.put("pich", "560");
                String url = Constants.BASE_URL_AL_AL + "GetProductDetail/";
                if (!checkURL(url, map)) {
                    ActionContext.getContext().put("error", "事件参数填写有误");
                    return "toAddUI";
                }
                JSONObject obj = new JSONObject();
                obj.put("productid", goodsid);
                obj.put("type", type);
                obj.put("categoryno", categoryno);
                model.setActionobj(obj.toString());
            } else if ("detail".equals(model.getAction()) && productNum != null && (productNum.intValue() == 2 || productNum.intValue() == 102) && StringUtils.isEmpty(goodsid)) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("productid", productid);
                map.put("picw", "420");
                map.put("pich", "560");
                String url = Constants.BASE_URL_SP_SP + "SPProductDetail/";
                if (!checkURL(url, map)) {
                    ActionContext.getContext().put("error", "事件参数填写有误");
                    return "toAddUI";
                }
                if (productNum.intValue() == 102) {// andriod
                    JSONObject obj = new JSONObject();
                    obj.put("productid", productid);
                    obj.put("topicid", topicid);
                    model.setActionobj(obj.toString());
                } else if (productNum.intValue() == 2) {// iphone
                    JSONObject obj = new JSONObject();
                    obj.put("productid", productid);
                    model.setActionobj(obj.toString());
                }
            } else if ("link".equals(model.getAction()) && StringUtils.isNotEmpty(url)) {
                model.setActionarg(url);
            } else if ("topic".equals(model.getAction()) && productNum != null && (productNum.intValue() == 1 || productNum.intValue() == 101) && StringUtils.isNotEmpty(topicid)) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("topicNo", topicid);
                map.put("picurlw", "282");
                map.put("picurlh", "376");
                String url = Constants.BASE_URL_AL_AL + "GetTopic/";
                if (!checkURL(url, map)) {
                    ActionContext.getContext().put("error", "事件参数填写有误");
                    return "toAddUI";
                }
                model.setActionarg(topicid);
            } else if ("topic".equals(model.getAction()) && productNum != null && (productNum.intValue() == 2 || productNum.intValue() == 102) && StringUtils.isNotEmpty(topicid)) {
                Map<String, String> map = new HashMap<String, String>();
                map.put("pagesize", "2");
                map.put("pageindex", "1");
                map.put("gender", "0");
                map.put("topicid", topicid);
                map.put("picw", "{w}");
                map.put("pich", "{h}");
                String url = Constants.BASE_URL_SP_SP + "SPNewTopicProducts/";
                if (!checkURL(url, map)) {
                    ActionContext.getContext().put("error", "事件参数填写有误");
                    return "toAddUI";
                }
                model.setActionarg(topicid);
            } else if ("activity".equals(model.getAction()) && StringUtils.isNotEmpty(actid)) {
                Map<String, String> map = new HashMap<String, String>();
                String url = null;
                JSONObject jsonObject = null;
                String activityName = "";
                if (productNum==2||productNum==102) {
                    map.put("userId", "");
                    map.put("id", actid);
                    map.put("type", "1");
                    url = Constants.BASE_TRADE_URL + "ListingCatalog/headInfo/";
                    String data = WebUtil.readContentFromGet(url, map);
                    jsonObject = JSONObject.fromObject(data);
                    if ("0".equals(jsonObject.getString("code"))) {
                        JSONObject contentJson = jsonObject.getJSONObject("content");
                        JSONObject headJson = contentJson.getJSONObject("head");
                        activityName = headJson.getString("name");
                    } else {
                        ActionContext.getContext().put("error", "事件参数填写有误");
                        return "toAddUI";
                    }
                } else {                   
                    map.put("subjectNo", actid);
                    map.put("picurlw", "282");
                    map.put("picurlh", "376");
                    url = Constants.BASE_URL_AL_AL + "subjectproductlist/";
                    if (!checkURL(url, map)) {
                        ActionContext.getContext().put("error", "事件参数填写有误");
                        return "toAddUI";
                    }
                }
                model.setActionarg(actid);
                model.setTitle(activityName);
            } else if ("openapp".equals(model.getAction()) && StringUtils.isNotEmpty(appuri, openappuri)) {
                model.setActionarg(openappuri);
                model.setActionobj(appuri);
            }
            // --------------转成apple服务器需要的json字符串--------------
            Payload payLoad = new Payload();
            payLoad.setBadge(1);
            payLoad.setAlert(model.getNotice());
            payLoad.setSound("default");
            payLoad.addParam("action", model.getAction());
            String actionArg = model.getActionarg();
            if (actionArg != null) {
                payLoad.addParam("actionarg", actionArg);
            }
            String actionObj = model.getActionobj();
            if (actionObj != null) {
                payLoad.addParam("actionobj", actionObj);
            }
            if (model.getTitle() != null) {
                payLoad.addParam("title", model.getTitle());
            } else {
                payLoad.addParam("title", "");
            }
            if (productNum != null && (productNum.intValue() == 101 || productNum.intValue() == 102)) {
                if (model.getShowTime() != null) {
                    payLoad.addParam("showTime", sdf.format(model.getShowTime()));
                }
            }
            // ---------------------------------------------------------
            HqlHelper hqlHelper1 = new HqlHelper(Product.class, "p");
            hqlHelper1.addWhereCondition("p.productNum=?", Long.valueOf(model.getProductNum()));
            Product product = productService.getByCondition(hqlHelper1);
            String platform = product.getPlatform();
            if (StringUtils.isNotEmpty(platform)) {
                if ("ios".equalsIgnoreCase(platform)) {
                    // long maxId = pushManageIosService.getMaxId();
                    // payLoad.addCustomDictionary("id", maxId + 1 + "");
                    String content = payLoad.toString();
                    model.setPushContent(content);
                    if (content.getBytes(CHARSET).length >= 255) {
                        ActionContext.getContext().put("error", "push内容过长，请适当缩减");
                        return "toAddUI";
                    }
                    model.setPlatform("ios");
                    pushManageIosService.save(model);
                    System.out.println("ios id is :" + model.getId());
                    payLoad.addParam("id", model.getId() + "");
                    model.setPushContent(payLoad.toString());
                    pushManageIosService.update(model);
                } else {
                    PushManageAndroid pushManageAndroid = new PushManageAndroid();
                    // long maxId = pushManageAndroidService.getMaxId();
                    // payLoad.addCustomDictionary("id", maxId + 1 + "");
                    String content = payLoad.toString();
                    model.setPushContent(content);
                    if (content.getBytes(CHARSET).length >= 255) {
                        ActionContext.getContext().put("error", "push内容过长，请适当缩减");
                        return "toAddUI";
                    }
                    ConvertUtils.register(new DateLocaleConverter(), Date.class);
                    BeanUtils.copyProperties(pushManageAndroid, model);
                    pushManageAndroid.setPlatform("android");
                    if (pushManageAndroid.getChannelNum() == 0) {
                        pushManageAndroid.setChannelNum(null);
                    }
                    pushManageAndroidService.save(pushManageAndroid);
                    System.out.println("Android id is :" + pushManageAndroid.getId());
                    payLoad.addParam("id", pushManageAndroid.getId() + "");
                    pushManageAndroid.setPushContent(payLoad.toString());
                    pushManageAndroidService.update(pushManageAndroid);
                }
            }
            // else {
            // // ios
            // long maxId = pushManageIosService.getMaxId();
            // payLoad.addCustomDictionary("id", maxId + 1 + "");
            // String content = payLoad.toString();
            // model.setPushContent(content);
            // if (content.length() >= 255) {
            // ActionContext.getContext().put("error", "push内容过长，请适当缩减");
            // return "toAddUI";
            // }
            // model.setPlatform("ios");
            // pushManageIosService.save(model);
            // PushManageAndroid pushManageAndroid = new PushManageAndroid();
            // // android
            // maxId = pushManageAndroidService.getMaxId();
            // payLoad.addCustomDictionary("id", maxId + 1 + "");
            // content = payLoad.toString();
            // model.setPushContent(content);
            // if (content.length() >= 255) {
            // ActionContext.getContext().put("error", "push内容过长，请适当缩减");
            // return "toAddUI";
            // }
            // BeanUtils.copyProperties(pushManageAndroid, model);
            // pushManageAndroid.setPlatform("android");
            // pushManageAndroidService.save(pushManageAndroid);
            // }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "toList";
    }

    /**
     * 数据动态绑定-推送消息列表
     * 
     * @Author zhouyu
     * @createDate 2013-02-27
     * @param
     * @Return
     */
    public String datagridList() {
        try {
            ReturnObject returnObject = new ReturnObject();
            SqlHelper sqlQueryListHelper = null;
            SqlHelper sqlQueryCountHelper = null;
            List recorList = new ArrayList();
            Integer count = 0;
            String platform = ServletActionContext.getRequest().getParameter("platform");
            if ("ios".equalsIgnoreCase(platform)) {
                sqlQueryListHelper = new SqlHelper("pushManage_ios", "c");
                sqlQueryCountHelper = new SqlHelper("pushManage_ios");
                sqlQueryListHelper.addColumn("c.title");
            } else {
                sqlQueryListHelper = new SqlHelper("pushManage_android", "c");
                sqlQueryCountHelper = new SqlHelper("pushManage_android");
            }
            sqlQueryListHelper.addColumn("c.id");
            sqlQueryListHelper.addColumn("c.platform");
            sqlQueryListHelper.addColumn("c.productNum");
            sqlQueryListHelper.addColumn("c.channelNum");
            sqlQueryListHelper.addColumn("c.username");
            sqlQueryListHelper.addColumn("c.action");
            sqlQueryListHelper.addColumn("c.actionarg");
            sqlQueryListHelper.addColumn("c.actionobj");
            sqlQueryListHelper.addColumn("c.pushType");
            sqlQueryListHelper.addColumn("c.notice");
            sqlQueryListHelper.addColumn("c.showTime");
            sqlQueryListHelper.addColumn("c.createTime");
            sqlQueryListHelper.addOrderByProperty(true, "c.createTime", false);
            sqlQueryCountHelper.addColumn("count(*)");

            // 2，查询并准备分页信息
            if ("ios".equals(platform)) {
                recorList = pushManageIosService.executeSqlToMap(sqlQueryListHelper.getQueryListSql(), page, rows);
                count = pushManageIosService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
            } else {
                sqlQueryListHelper.addColumn("c.endTime");
                recorList = pushManageAndroidService.executeSqlToMap(sqlQueryListHelper.getQueryListSql(), page, rows);
                count = pushManageAndroidService.executeSqlCount(sqlQueryCountHelper.getQueryListSql());
            }
            returnObject.setTotal(count);
            returnObject.setRows(recorList);
            ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
        } catch (Exception e) {
            log.error("PushManageAction-datagridList() " + e);
        }
        return null;
    }

    /**
     * 推送消息列表
     * 
     * @Author zhouyu
     * @createDate 2013-02-27
     * @param
     * @Return
     */
    public String list() {
        return "list";
    }

    /**
     * 删除
     * 
     * @Author zhouyu
     * @CreatDate 2013-02-27
     * @param
     * @Return
     */
    public String delete() {
        ReturnObject returnObject = new ReturnObject();
        String platform = ServletActionContext.getRequest().getParameter("platform");
        returnObject.setPlatform(platform);
        try {
            if ("ios".equalsIgnoreCase(platform)) {
                pushManageIosService.delete(model.getId());
            } else {
                pushManageAndroidService.delete(model.getId());
            }
            returnObject.setReturnCode("1");// 正确标识符
        } catch (Exception e) {
            returnObject.setReturnCode("0");// 错误标识符
            returnObject.setReturnInfo("PushManageAction-delete " + e);
            log.error("PushManageAction-delete " + e);
        }
        try {
            ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 检验注册用户名是否存在
     * 
     * @Author zhouyu
     * @CreatDate 2013-03-01
     * @param
     * @Return
     */
    public String checkUserName() {
        try {
            String userName = ServletActionContext.getRequest().getParameter("userName");
            HqlHelper hqlHelper = new HqlHelper(Account.class, "a");
            hqlHelper.addWhereCondition("a.loginName=?", userName);
            Account user = accountService.getByCondition(hqlHelper);
            ReturnObject returnObject = new ReturnObject();
            if (user == null)
                returnObject.setReturnInfo("用户名不存在");
            else
                returnObject.setReturnInfo("");
            ServletActionContext.getResponse().getWriter().print(JsonUtil.convertToJSonStr(returnObject));
        } catch (Exception e) {
            log.error("PushManageAction-list() " + e);
        }
        return null;
    }

    /**
     * 检测URL是否有效
     * 
     * @Author zhouyu
     * @CreatDate 2013-03-01
     * @param
     * @Return
     */
    public boolean checkURL(String url, Map<String, String> map) {
        try {
            String data = WebUtil.readContentFromGet(url, map);
            JSONObject.fromObject(data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 检验注册用户名是否存在
     * 
     * @Author zhouyu
     * @CreatDate 2013-03-01
     * @param
     * @Return
     */
    public String updatePushNotice() {
        try {
            HqlHelper hqlHelper = new HqlHelper(PushManageAndroid.class, "a");
            hqlHelper.addWhereCondition("a.notice=null");
            List<PushManageAndroid> executeHql = pushManageAndroidService.executeHql(hqlHelper.getQueryListHql());
            for (PushManageAndroid push : executeHql) {
                String pushContent = push.getPushContent();
                if (StringUtils.isNotEmpty(pushContent)) {
                    JSONObject fromObject = JSONObject.fromObject(pushContent);
                    JSONObject jsonObject = fromObject.getJSONObject("aps");
                    String alert = jsonObject.getString("alert");
                    if (StringUtils.isNotEmpty(alert)) {
                        System.out.println(push.getId());
                        push.setNotice(alert);
                        pushManageAndroidService.update(push);
                    }
                }
            }
        } catch (Exception e) {
            log.error("PushManageAction-updatePushNotice() " + e);
        }
        return null;
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategoryno() {
        return categoryno;
    }

    public void setCategoryno(String categoryno) {
        this.categoryno = categoryno;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getTopicid() {
        return topicid;
    }

    public void setTopicid(String topicid) {
        this.topicid = topicid;
    }

    public Log getLog() {
        return log;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getActid() {
        return actid;
    }

    public void setActid(String actid) {
        this.actid = actid;
    }

    public String getAppuri() {
        return appuri;
    }

    public void setAppuri(String appuri) {
        this.appuri = appuri;
    }

    public String getOpenappuri() {
        return openappuri;
    }

    public void setOpenappuri(String openappuri) {
        this.openappuri = openappuri;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
