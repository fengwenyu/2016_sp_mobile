package com.shangpin.manager.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shangpin.core.entity.main.Account;
import com.shangpin.core.entity.main.Aps;
import com.shangpin.core.entity.main.Channel;
import com.shangpin.core.entity.main.Product;
import com.shangpin.core.entity.main.Push;
import com.shangpin.core.entity.main.PushInfo;
import com.shangpin.core.service.AccountService;
import com.shangpin.core.service.ChannelService;
import com.shangpin.core.service.ProductService;
import com.shangpin.core.service.PushService;
import com.shangpin.core.util.ManagerConstants;
import com.shangpin.core.util.dwz.AjaxObject;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.persistence.DynamicSpecifications;
import com.shangpin.manager.utils.JsonUtil;

@Controller()
@RequestMapping("/management/maintain/push")
public class PushController{
    
    private static final String LIST = "management/maintain/push/list";
    private static final String CREATE = "management/maintain/push/create";
    private static final String UPDATE = "management/maintain/push/update";
    private static final String LOOK_PRODUCT = "management/maintain/push/product";
    private static final String LOOK_CHANNEL = "management/maintain/push/channel";
    
    @Autowired
    private PushService pushService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ChannelService channelService;
    @Autowired
    private AccountService accountService;
    
    @RequiresPermissions("Push:view")
    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(Page page, HttpServletRequest request,  Map<String, Object> map){
        Specification<Push> specification = DynamicSpecifications.bySearchFilter(request, Push.class);
        List<Push> pushs = pushService.findByExample(specification, page);
        map.put("page", page);
        map.put("pushs", pushs);
        return LIST;
    }
    
    @RequiresPermissions("Push:save")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String preCreate(){
        return CREATE;
    }
    
    @RequiresPermissions("Push:save")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public String create(@Valid Push push, String platformName){
        if("android".equals(platformName.trim())){
            push.setPlatformType(ManagerConstants.ANDROID);
        }else {
            push.setPlatformType(ManagerConstants.IOS);
        }
        push.setCreateTime(new Date());
        String actionType = push.getActionType();
        if("link".equals(actionType)){
            push.setActionParam(push.getPushContent().getUrl());
        } else if ("topic".equals(actionType)) {
            push.setActionParam(push.getPushContent().getTopicid());
        } else if ("activity".equals(actionType)) {
            push.setActionParam(push.getPushContent().getActid());
        } else if ("openapp".equals(actionType)) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("openurl", push.getPushContent().getOpenurl());
            map.put("otherurl", push.getPushContent().getOtherurl());
            String param = JsonUtil.toJson(map);
            push.setActionParam(param);
        } else if ("detail".equals(actionType)){
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("goodsid", push.getPushContent().getProductid());
            map.put("type", push.getPushContent().getProductSource());
            map.put("categoryno", push.getPushContent().getCategoryid());
            String detailParam = JsonUtil.toJson(map);
            push.setActionDetailParam(detailParam);
        }
        PushInfo pushInfo = new PushInfo();
        if("detail".equals(actionType)){
            pushInfo.setActionarg(push.getActionDetailParam());
        }else {
            pushInfo.setActionarg(push.getActionParam());
        }
        Aps aps = new Aps();
        aps.setSound("default");
        aps.setAlert(push.getNotice());
        aps.setBadge("1");
        pushInfo.setTitle(push.getTitle());
        pushInfo.setAps(aps);
        pushInfo.setAction(push.getActionType());
        pushInfo.setShowTime(push.getStartTime());
        String pushContent = JsonUtil.toJson(pushInfo);
        push.setContent(pushContent);
        try {
            pushService.save(push);
        } catch (Exception e) {
            return AjaxObject.newError("消息添加失败").toString();
        }
        
        return AjaxObject.newOk("消息添加成功！").toString();
    }
    
    @ModelAttribute("preloadPush")
    public Push getOne(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            Push push = pushService.get(id);
            return push;
        }
        return null;
    }
    
    @RequiresPermissions("Push:edit")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable Long id, Map<String, Object> map){
        Push push = pushService.get(id);
        String actionType = push.getActionType();
        if("link".equals(actionType)){
            map.put("url", push.getActionParam());
        }else if("activity".equals(actionType)){
            map.put("activity", push.getActionParam());
        }else if("topic".equals(actionType)){
            map.put("topic", push.getActionParam());
        }else if("detail".equals(actionType)){
            String param = push.getActionDetailParam();
            ObjectMapper mapper = new ObjectMapper();
            try {
                JsonNode rootNode = mapper.readTree(param);
                map.put("goodsid", rootNode.path("goodsid").asText());
                map.put("type", rootNode.path("type").asText());
                map.put("categoryno", rootNode.path("categoryno").asText());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if("openapp".equals(actionType)){
            String openapp = push.getActionParam();
            ObjectMapper mapper = new ObjectMapper();
            try {
                JsonNode rootNode = mapper.readTree(openapp);
                map.put("openurl", rootNode.path("openurl").asText());
                map.put("otherurl", rootNode.path("otherurl").asText());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        map.put("push", push);
        return UPDATE;
    }
    
    @RequiresPermissions("Push:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(@Validated @ModelAttribute("preloadPush")Push push){
        pushService.update(push);
        return AjaxObject.newOk("消息更新成功!").toString();
    }
    
    @RequiresPermissions("Push:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(Long[] ids){
        for(int i = 0; i < ids.length; i++){
            pushService.delete(ids[i]);
        }
        return AjaxObject.newOk("删除成功！").setCallbackType("").toString();
    }
    
    @RequiresPermissions(value = {"Push:save","Push:edit"}, logical = Logical.OR)
    @RequestMapping(value = "/lookup2product", method = {RequestMethod.GET, RequestMethod.POST})
    public String lookProduct(Page page, HttpServletRequest request, Map<String, Object> map){
        Specification<Product> specification = DynamicSpecifications.bySearchFilter(request, Product.class);
        List<Product> products = productService.findAll(specification, page);
        map.put("products", products);
        map.put("page", page);
        return LOOK_PRODUCT;
    }
    
    @RequiresPermissions(value = {"Push:save","Push:edit"}, logical = Logical.OR)
    @RequestMapping(value = "/lookup2channel", method = {RequestMethod.GET, RequestMethod.POST})
    public String lookChannel(Page page, HttpServletRequest request, Map<String, Object> map){
        Specification<Channel> specification = DynamicSpecifications.bySearchFilter(request, Channel.class);
        List<Channel> channels = channelService.findByExample(specification, page);
        map.put("channels", channels);
        map.put("page", page);
        return LOOK_CHANNEL;
    }
    
    @RequestMapping(value = "/checkuser", method = RequestMethod.POST)
    @ResponseBody
    public Object checkUser(String username){
        Account account = accountService.findByLoginName(username);
        return account != null ? account : AjaxObject.newError("用户不存在");
    }
   
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        CustomDateEditor  dateEditor = new CustomDateEditor(dft, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }
    
}
