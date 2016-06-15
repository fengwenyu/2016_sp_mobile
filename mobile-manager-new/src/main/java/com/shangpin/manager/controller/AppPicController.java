package com.shangpin.manager.controller;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shangpin.core.entity.main.AppPic;
import com.shangpin.core.log.Log;
import com.shangpin.core.log.LogMessageObject;
import com.shangpin.core.service.AppPicService;
import com.shangpin.core.util.dwz.AjaxObject;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.persistence.DynamicSpecifications;
import com.shangpin.manager.log.impl.LogUitl;
import com.shangpin.manager.utils.JsonUtil;

/**
 * app上传的图片信息控制层
 * 
 * @author zhanghongwei
 *
 */
@Controller
@RequestMapping(value = "/management/admin/appPic")
public class AppPicController extends BaseController{

    @Autowired
    private AppPicService appPicService;

    // 新增app上传图片信息页面
    private static final String CREATE = "management/admin/appPic/create";
    // app上传图片信息列表页面
    private static final String LIST = "management/admin/appPic/list";
    // 更新app上传图片信息列表页面
    private static final String UPDATE = "management/admin/appPic/update";

    @RequiresPermissions("AppPic:save")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String preCreate() {
        return CREATE;
    }

    @Log(message = "添加了{0}上传图片信息。")
    @RequiresPermissions("AppPic:save")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody String create(@Valid AppPic appPic) {
        appPic.setCreateTime(new Date());
        appPicService.save(appPic);
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { appPic.getId() }));
        return AjaxObject.newOk("app图片信息添加成功！").toString();
    }

    @RequiresPermissions("AppPic:view")
    @RequestMapping(value = "/list", method = { RequestMethod.POST, RequestMethod.GET })
    public String list(Page page, ServletRequest request, Map<String, Object> map) {
        Specification<AppPic> spec = DynamicSpecifications.bySearchFilter(request, AppPic.class);
        List<AppPic> appPics = appPicService.findAll(spec, page);
        map.put("appPics", appPics);
        map.put("page", page);
        return LIST;
    }

    @RequiresPermissions("AppPic:edit")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
        AppPic appPic = appPicService.get(id);
        map.put("appPic", appPic);
        return UPDATE;
    }

    @Log(message = "修改{0}app图片信息。")
    @RequiresPermissions("AppPic:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody String update(@Valid @ModelAttribute("preloadFeedback") AppPic appPic) {
        appPicService.update(appPic);
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { appPic.getId() }));
        return AjaxObject.newOk("app图片信息修改成功！").toString();
    }

    @Log(message = "删除了{0}app图片信息。")
    @RequiresPermissions("AppPic:delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public @ResponseBody String delete(@PathVariable Long id) {
        AppPic appPic = appPicService.get(id);
        appPicService.delete(appPic.getId());
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { appPic.getId() }));
        return AjaxObject.newOk("删除app图片信息成功！").setCallbackType("").toString();
    }

    @Log(message = "删除了{0}app图片信息。")
    @RequiresPermissions("AppPic:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody String deleteMany(Long[] ids) {
        Long[] feedbackId = new Long[ids.length];
        for (int i = 0; i < ids.length; i++) {
            AppPic appPic = appPicService.get(ids[i]);
            appPicService.delete(appPic.getId());
            feedbackId[i] = appPic.getId();
        }
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { Arrays.toString(feedbackId) }));
        return AjaxObject.newOk("删除成功！").setCallbackType("").toString();
    }

    @ModelAttribute("preloadFeedback")
    public AppPic getOne(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            AppPic appPic = appPicService.get(id);
            return appPic;
        }
        return null;
    }

    @Log(message = "上传了{0}。")
    @RequiresPermissions("AppPic:upload")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(HttpServletRequest request, @RequestParam MultipartFile files) {

        String result;
        try {
            BufferedImage bufferedImage = ImageIO.read(files.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            result = uploadPic(files);
            String wh = "\"imgWidth\":\"" + width + "\",\"imgHeight\":\"" + height + "\",";
            result = JsonUtil.joint(result, wh);
        } catch (Exception e) {
            result = null;
        }
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { result }));
        return result.toString();
    }

}
