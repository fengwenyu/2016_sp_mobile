package com.shangpin.manager.controller.admin;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.shangpin.core.entity.admin.App;
import com.shangpin.core.entity.admin.AppChannel;
import com.shangpin.core.entity.admin.AppDownload;
import com.shangpin.core.exception.ExistedException;
import com.shangpin.core.log.Log;
import com.shangpin.core.log.LogMessageObject;
import com.shangpin.core.service.admin.AppChannelService;
import com.shangpin.core.service.admin.AppDownloadService;
import com.shangpin.core.service.admin.AppService;
import com.shangpin.core.util.dwz.AjaxObject;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.persistence.DynamicSpecifications;
import com.shangpin.manager.controller.BaseController;
import com.shangpin.manager.log.impl.LogUitl;
import com.shangpin.utils.FastDFSUtil;

/**
 * app产品下载管理
 * 
 * @author sunweiwei
 * 
 */
@Controller
@RequestMapping(value = "/management/admin/app/download")
public class AppDownloadController extends BaseController {

    @Autowired
    private AppService appService;

    @Autowired
    private AppChannelService appChannelService;

    @Autowired
    private AppDownloadService appDownloadService;

    private static final FastDFSUtil dfsInstance = FastDFSUtil.getInstance();

    // 新增应用下载信息页面
    private static final String CREATE = "management/admin/app/download/create";
    // 应用下载信息列表页面
    private static final String LIST = "management/admin/app/download/list";
    // 更新应用下载信息列表页面
    private static final String UPDATE = "management/admin/app/download/update";
    // 弹出app的列表页面
    private static final String LOOK_APP = "management/admin/app/download/lookup_app";
    // 弹出渠道的列表页面
    private static final String LOOK_CHANNEL = "management/admin/app/download/lookup_channel";

    /**
     * 列表显示应用包
     * 
     * @param page
     * @param keywords
     * @param map
     * @return
     */
    @RequiresPermissions("AppDownload:view")
    @RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
    public String list(Page page, String keywords, Map<String, Object> map) {
        List<AppDownload> downloads = null;
        if (!StringUtils.isEmpty(keywords)) {
            downloads = appDownloadService.find(page, keywords);
        } else {
            downloads = appDownloadService.findAll(page);
        }

        map.put("page", page);
        map.put("downloads", downloads);
        map.put("keywords", keywords);
        return LIST;
    }

    /**
     * 预创建应用包,跳转到创建页面
     * 
     * @return
     */
    @RequiresPermissions("AppDownload:save")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String preCreate() {
        return CREATE;
    }

    /**
     * 创建并插入应用包信息
     * 
     * @param channel
     * @return
     */
    @Log(message = "添加了{0}应用包。")
    @RequiresPermissions("AppDownload:save")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody
    String create(@Valid AppDownload download) {
        download.setCreateTime(new Date());
        try {
            appDownloadService.save(download);
        } catch (ExistedException e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { download.getAppName() }));
        return AjaxObject.newOk("添加应用包成功！").toString();
    }

    @ModelAttribute("preloadChannel")
    public AppDownload getOne(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            AppDownload download = appDownloadService.findById(id);
            return download;
        }
        return null;
    }

    @RequiresPermissions("AppDownload:edit")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
        AppDownload download = appDownloadService.findById(id);
        map.put("channel", download);
        return UPDATE;
    }

    @Log(message = "修改了{0}应用包的信息。")
    @RequiresPermissions("AppDownload:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    String update(@Valid @ModelAttribute("preloadChannel") AppDownload download) {
        appDownloadService.update(download);

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { download.getAppName() }));
        return AjaxObject.newOk("修改应用包成功！").toString();
    }

    @Log(message = "上传了{0}。")
    @RequiresPermissions({ "AppDownload:upload" })
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ResponseEntity<String> upload(HttpServletRequest request, @RequestParam MultipartFile file) {

        String filePath = dfsInstance.uploadFileExcludeGroup(file);
        if (StringUtils.isEmpty(filePath)) {
            return new ResponseEntity<String>("上传文件错误。", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<String>(filePath, HttpStatus.OK);
    }

    @RequiresPermissions(value = { "AppDownload:edit", "AppDownload::save" }, logical = Logical.OR)
    @RequestMapping(value = "/lookup2app", method = { RequestMethod.GET })
    public String lookupApp(Page page,ServletRequest request, Map<String, Object> map) {
        Specification<App> spec = DynamicSpecifications.bySearchFilter(request, App.class);

        List<App> apps  = appService.findByExample(spec, page);
        map.put("page", page);
        map.put("apps", apps);
        
        return LOOK_APP;
    }
    
    @RequiresPermissions(value = { "AppDownload:edit", "AppDownload::save" }, logical = Logical.OR)
    @RequestMapping(value = "/lookup2channel", method = { RequestMethod.GET })
    public String lookupChannel(Page page,ServletRequest request, Map<String, Object> map) {
        Specification<AppChannel> spec = DynamicSpecifications.bySearchFilter(request, AppChannel.class);

        List<AppChannel> channels  = appChannelService.findByExample(spec, page);
        map.put("page", page);
        map.put("channels", channels);
        
        return LOOK_CHANNEL;
    }
}
