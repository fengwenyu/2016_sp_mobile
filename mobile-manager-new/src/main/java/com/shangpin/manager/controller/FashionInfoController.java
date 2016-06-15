package com.shangpin.manager.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shangpin.core.entity.main.ApiFashionInfo;
import com.shangpin.core.log.Log;
import com.shangpin.core.log.LogMessageObject;
import com.shangpin.core.service.FashionInfoService;
import com.shangpin.core.util.dwz.AjaxObject;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.persistence.DynamicSpecifications;
import com.shangpin.manager.log.impl.LogUitl;

/**
 * 微信潮流资讯
 * 
 * @author cuibinqiang
 * 
 */
@SuppressWarnings({ "static-access"})
@Controller
@RequestMapping("/management/admin/fashionInfo")
public class FashionInfoController extends BaseController {

    @Autowired
    private FashionInfoService fashionInfoService;

    private static final String LIST = "management/admin/fashionInfo/list";
    private static final String CREATE = "management/admin/fashionInfo/create";
    private static final String UPDATE = "management/admin/fashionInfo/update";

    /**
     * 添加页面
     * 
     * @return
     */
    @RequiresPermissions("FashionInfo:save")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String toAdd(Map<String, Object> map) {
        return CREATE;
    }

    /**
     * 更新页面
     * 
     * @param id
     * @param map
     * @return
     */
    @RequiresPermissions("FashionInfo:edit")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
        ApiFashionInfo apiFashionInfo = fashionInfoService.findById(id);
        map.put("apiFashionInfo", apiFashionInfo);
        return UPDATE;
    }

    /**
     * 图片上传
     * 
     * @param request
     * @param file
     * @param map
     * @return
     */
    @RequiresPermissions("FashionInfo:upload")
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public @ResponseBody
    String uploadImg(HttpServletRequest request, @RequestParam MultipartFile file, Map<String, Object> map) {
        String imgUrl = "";
        try {
            // 对图片服务器返回json进行解析
            String json = uploadPic(file);
            System.out.println("图片服务器返回JSON：" + json);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);

            imgUrl = rootNode.path("content").path("PicUrl").asText();
            String image = imgUrl.replace("{w}", "180").replace("{h}", "110");
            System.out.println(image);
            imgUrl = image;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AjaxObject.newOk(imgUrl).toString();
    }

    /**
     * 保存资讯信息
     * 
     * @return
     */
    @RequiresPermissions("FashionInfo:save")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody
    String save(ApiFashionInfo apiFashionInfo, HttpServletRequest request) {
        AjaxObject ajaxObject = new AjaxObject("添加资讯信息成功！");

        try {
            apiFashionInfo.setCreateTime(new Date());
            apiFashionInfo.setSort(0);
            if (apiFashionInfo.getImgUrl() != null) {// 上传的文件URL不为空
                // 设置图片宽度、高度
                apiFashionInfo.setImgWidth("600");
                apiFashionInfo.setImgHeight("320");
            }

            fashionInfoService.save(apiFashionInfo);

        } catch (Exception e) {
            return ajaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }
        return ajaxObject.toString();
    }

    /**
     * 查询列表页：组合条件
     * 
     * @param page
     * @param request
     * @param map
     * @return
     */
    @RequiresPermissions("FashionInfo:view")
    @RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
    public String list(Page page, ServletRequest request, Map<String, Object> map) {
        Specification<ApiFashionInfo> spec = DynamicSpecifications.bySearchFilter(request, ApiFashionInfo.class);

        List<ApiFashionInfo> apiFashionInfo = fashionInfoService.findByExample(spec, page);
        map.put("page", page);
        map.put("apiFashionInfo", apiFashionInfo);

        return LIST;
    }

    /**
     * 删除资讯信息
     * 
     * @param ids
     * @return
     */

    @RequiresPermissions("FashionInfo:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    String delete(Long[] ids) {
        AjaxObject ajaxObject = new AjaxObject("删除资讯信息成功！");
        try {
            for (int i = 0; i < ids.length; i++) {
                Long id = ids[i];
                fashionInfoService.delete(id);
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
    @Log(message = "修改了{0}资讯的信息。")
    @RequiresPermissions("FashionInfo:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    String update(@ModelAttribute("preloadApiFashionInfo") ApiFashionInfo apiFashionInfo) {
        AjaxObject ajaxObject = new AjaxObject("修改资讯信息成功！");
        try {
            fashionInfoService.update(apiFashionInfo);
            LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { apiFashionInfo.getTitle() }));
        } catch (Exception e) {
            return ajaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }

        return ajaxObject.toString();
    }

    @ModelAttribute("preloadApiFashionInfo")
    public ApiFashionInfo getOne(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            ApiFashionInfo apiFashionInfo = fashionInfoService.findById(id);
            return apiFashionInfo;
        }
        return null;
    }

    /**
     * 解决前台字符串日期传至后台报错问题
     * 
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
        binder.registerCustomEditor(Date.class, dateEditor);
    }

}
