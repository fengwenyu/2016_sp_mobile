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

import com.shangpin.core.entity.admin.App;
import com.shangpin.core.exception.ExistedException;
import com.shangpin.core.log.Log;
import com.shangpin.core.log.LogMessageObject;
import com.shangpin.core.service.admin.AppService;
import com.shangpin.core.util.dwz.AjaxObject;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.manager.controller.BaseController;
import com.shangpin.manager.log.impl.LogUitl;

/**
 * app产品管理
 * 
 * @author sunweiwei
 * 
 */
@Controller
@RequestMapping(value = "/management/admin/app")
public class AppController extends BaseController{

    @Autowired
    private AppService appService;

    // 新增app信息页面
    private static final String CREATE = "management/admin/app/create";
    // app信息列表页面
    private static final String LIST = "management/admin/app/list";
    // 更新app信息列表页面
    private static final String UPDATE = "management/admin/app/update";

    /**
     * 列表显示app
     * 
     * @param page
     * @param keywords
     * @param map
     * @return
     */
    @RequiresPermissions("App:view")
    @RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
    public String list(Page page, String keywords, Map<String, Object> map) {
        List<App> apps = null;
        if (!StringUtils.isEmpty(keywords)) {
            apps = appService.find(page, keywords);
        } else {
            apps = appService.findAll(page);
        }

        map.put("page", page);
        map.put("apps", apps);
        map.put("keywords", keywords);
        return LIST;
    }

    /**
     * 预创建app,跳转到创建页面
     * 
     * @return
     */
    @RequiresPermissions("App:save")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String preCreate() {
        return CREATE;
    }

    /**
     * 创建并插入app信息
     * 
     * @param app
     * @return
     */
    @Log(message = "添加了{0}APP。")
    @RequiresPermissions("App:save")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody
    String create(@Valid App app) {
        Long num = genAppNum();
        if (num == 0) {
            return AjaxObject.newError("内部错误").toString();
        }
        app.setNum(String.valueOf(num));
        app.setCreateTime(new Date());
        try {
            appService.save(app);
        } catch (ExistedException e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { app.getName() }));
        return AjaxObject.newOk("添加App成功！").toString();
    }

    @ModelAttribute("preloadApp")
    public App getOne(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            App app = appService.findById(id);
            return app;
        }
        return null;
    }

    @RequiresPermissions("App:edit")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
        App app = appService.findById(id);
        map.put("app", app);
        return UPDATE;
    }

    @Log(message = "修改了{0}App的信息。")
    @RequiresPermissions("App:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    String update(@Valid @ModelAttribute("preloadApp") App app) {
        appService.update(app);

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { app.getName() }));
        return AjaxObject.newOk("修改App成功！").toString();
    }

    /**
     * 
     * @return
     */
    private Long genAppNum() {
        Long maxNum = this.appService.findMaxNum();
        if (StringUtils.isEmpty(maxNum)) {
            return new Long(0);
        }
        return maxNum + 1;
    }
}
