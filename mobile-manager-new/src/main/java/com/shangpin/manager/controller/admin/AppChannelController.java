package com.shangpin.manager.controller.admin;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.core.entity.admin.AppChannel;
import com.shangpin.core.exception.ExistedException;
import com.shangpin.core.log.Log;
import com.shangpin.core.log.LogMessageObject;
import com.shangpin.core.service.admin.AppChannelService;
import com.shangpin.core.util.dwz.AjaxObject;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.manager.controller.BaseController;
import com.shangpin.manager.log.impl.LogUitl;

/**
 * app产品渠道管理
 * 
 * @author sunweiwei
 * 
 */
@Controller
@RequestMapping(value = "/management/admin/app/channel")
public class AppChannelController extends BaseController{

    @Autowired
    private AppChannelService appChannelService;

    // 新增渠道信息页面
    private static final String CREATE = "management/admin/app/channel/create";
    // 渠道信息列表页面
    private static final String LIST = "management/admin/app/channel/list";
    // 更新渠道信息列表页面
    private static final String UPDATE = "management/admin/app/channel/update";

    /**
     * 列表显示渠道
     * 
     * @param page
     * @param keywords
     * @param map
     * @return
     */
    @RequiresPermissions("AppChannel:view")
    @RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
    public String list(Page page, String keywords, Map<String, Object> map) {
        List<AppChannel> channels = null;
        if (!StringUtils.isEmpty(keywords)) {
            channels = appChannelService.find(page, keywords);
        } else {
            channels = appChannelService.findAll(page);
        }

        map.put("page", page);
        map.put("channels", channels);
        map.put("keywords", keywords);
        return LIST;
    }

    /**
     * 预创建渠道,跳转到创建页面
     * 
     * @return
     */
    @RequiresPermissions("AppChannel:save")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String preCreate() {
        return CREATE;
    }

    /**
     * 创建并插入渠道信息
     * 
     * @param channel
     * @return
     */
    @Log(message = "添加了{0}渠道。")
    @RequiresPermissions("AppChannel:save")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody
    String create(@Valid AppChannel channel) {
        Long num = genAppNum();
        if (num == 0) {
            return AjaxObject.newError("内部错误").toString();
        }
        channel.setNum(String.valueOf(num));
        channel.setCreateTime(new Date());
        try {
            appChannelService.save(channel);
        } catch (ExistedException e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { channel.getName() }));
        return AjaxObject.newOk("添加渠道成功！").toString();
    }

    @ModelAttribute("preloadChannel")
    public AppChannel getOne(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            AppChannel channel = appChannelService.findById(id);
            return channel;
        }
        return null;
    }

    @RequiresPermissions("AppChannel:edit")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
        AppChannel channel = appChannelService.findById(id);
        map.put("channel", channel);
        return UPDATE;
    }

    @Log(message = "修改了{0}渠道的信息。")
    @RequiresPermissions("AppChannel:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    String update(@Valid @ModelAttribute("preloadChannel") AppChannel channel) {
        appChannelService.update(channel);

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { channel.getName() }));
        return AjaxObject.newOk("修改渠道成功！").toString();
    }

    /**
     * 
     * @return
     */
    private Long genAppNum() {
        Long maxNum = this.appChannelService.findMaxNum();
        if (StringUtils.isEmpty(maxNum)) {
            return new Long(0);
        }
        return maxNum + 1;
    }
}
