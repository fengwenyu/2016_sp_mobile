package com.shangpin.manager.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.core.entity.main.Channel;
import com.shangpin.core.log.Log;
import com.shangpin.core.log.LogMessageObject;
import com.shangpin.core.service.ChannelService;
import com.shangpin.core.util.dwz.AjaxObject;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.persistence.DynamicSpecifications;
import com.shangpin.manager.log.impl.LogUitl;

/**
 * 渠道管理
 * 
 * @author cuibinqiang
 * 
 */
@SuppressWarnings("static-access")
//@Controller
@RequestMapping("/management/base/channel")
public class ChannelController {

    @Autowired
    private ChannelService channelService;

    private static final String LIST = "management/admin/channel/list";
    private static final String CREATE = "management/admin/channel/create";
    private static final String UPDATE = "management/admin/channel/update";

    /**
     * 查询列表页：组合条件
     * 
     * @param page
     * @param request
     * @param map
     * @return
     */
    @RequiresPermissions("Channel:view")
    @RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
    public String list(Page page, ServletRequest request, Map<String, Object> map) {
        Specification<Channel> spec = DynamicSpecifications.bySearchFilter(request, Channel.class);

        List<Channel> channel = channelService.findByExample(spec, page);
        map.put("page", page);
        map.put("channel", channel);

        return LIST;
    }

    /**
     * 添加页面
     * 
     * @return
     */
    @RequiresPermissions("Channel:save")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String toAdd(Map<String, Object> map) {
        // 生成唯一渠道编码
        Long maxCode = channelService.findMaxCode();
        maxCode++;
        map.put("maxCode", maxCode);
        return CREATE;
    }

    /**
     * 更新页面
     * 
     * @param id
     * @param map
     * @return
     */
    @RequiresPermissions("Channel:edit")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
        Channel channel = channelService.findById(id);
        map.put("channel", channel);
        return UPDATE;
    }

    /**
     * 添加渠道信息
     * 
     * @return
     */
    @RequiresPermissions("Channel:save")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody
    String save(Channel channel) {
        AjaxObject ajaxObject = new AjaxObject("添加渠道信息成功！");
        try {
            channel.setCreateTime(new Date());
            channelService.save(channel);
        } catch (Exception e) {
            return ajaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }
        return ajaxObject.toString();
    }

    /**
     * 删除渠道信息
     * 
     * @param ids
     * @return
     */

    @RequiresPermissions("Channel:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    String delete(Long[] ids) {
        AjaxObject ajaxObject = new AjaxObject("删除渠道信息成功！");
        try {
            for (int i = 0; i < ids.length; i++) {
                Long id = ids[i];
                channelService.delete(id);
            }
        } catch (Exception e) {
            return ajaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }
        return ajaxObject.setCallbackType("").toString();
    }

    /**
     * 修改渠道信息
     * 
     * @param channel
     * @return
     */
    @Log(message = "修改了{0}渠道的信息。")
    @RequiresPermissions("Channel:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    String update(@ModelAttribute("preloadChannel") Channel channel) {
        AjaxObject ajaxObject = new AjaxObject("修改渠道信息成功！");
        try {
            channelService.update(channel);
            LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { channel.getChannelName() }));
        } catch (Exception e) {
            return ajaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }

        return ajaxObject.toString();
    }

    @ModelAttribute("preloadChannel")
    public Channel getOne(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            Channel channel = channelService.findById(id);
            return channel;
        }
        return null;
    }
}
