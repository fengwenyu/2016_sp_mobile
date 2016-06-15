package com.shangpin.manager.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
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
import com.shangpin.core.entity.main.StaticActivity;
import com.shangpin.core.exception.ExistedException;
import com.shangpin.core.exception.ServiceException;
import com.shangpin.core.log.Log;
import com.shangpin.core.log.LogMessageObject;
import com.shangpin.core.service.StaticActivityService;
import com.shangpin.core.util.dwz.AjaxObject;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.persistence.DynamicSpecifications;
import com.shangpin.core.util.persistence.SearchFilter;
import com.shangpin.core.util.persistence.SearchFilter.Operator;
import com.shangpin.manager.log.impl.LogUitl;
@Controller
@RequestMapping("/management/maintain/staticActivity")
public class StaticActivityController extends BaseController{
    @Autowired
    private StaticActivityService staticActivityService;
    
    private static final String LIST = "management/maintain/staticActivity/list";
    private static final String CREATE = "management/maintain/staticActivity/create";
    private static final String UPDATE = "management/maintain/staticActivity/update";
    @InitBinder
    protected void initBinder(HttpServletRequest request, 
                    ServletRequestDataBinder binder,WebDataBinder dataBinder) throws Exception {
//         binder.registerCustomEditor(Display.class, new DisplayEditor()); 
//         binder.convertIfNecessary(Display.class,  new DisplayEditor())
         DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         dataBinder.registerCustomEditor(Date.class, 
                 new CustomDateEditor(df, true));
    }
    
    @RequiresPermissions("StaticActivity:view")
    @RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
    public String list(Page page,HttpServletRequest request, Map<String, Object> map) {
//        Set<SearchFilter> filterSet = new HashSet<SearchFilter>();
        SearchFilter  filterSet= new SearchFilter("endTime", Operator.GT, new Date());
//        filterSet.add(new SearchFilter("endTime", Operator.GT, new Date()));
        Specification<StaticActivity> specification = DynamicSpecifications.bySearchFilter(request, StaticActivity.class,filterSet);
        List<StaticActivity> staticActivitys = null;
//        List<Push> pushs = pushService.findByExample(specification, page);
        staticActivitys = staticActivityService.findByExample(specification, page);
        map.put("page", page);
        map.put("staticActivitys", staticActivitys);
        return LIST;
    }

    @RequiresPermissions("StaticActivity:save")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String preCreate() {
        return CREATE;
    }

    @Log(message = "添加了{0}静态活动。")
    @RequiresPermissions("StaticActivity:save")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody
    String create(@Valid StaticActivity staticActivity) {
        staticActivity.setCreateTime(new Date());
        try {
            staticActivityService.save(staticActivity);
        } catch (ExistedException e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }
//        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { staticActivity.getBrandName() }));
        return AjaxObject.newOk("添加成功！").toString();
    }

    @RequiresPermissions("User:edit")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
        StaticActivity staticActivity = staticActivityService.get(id);
        map.put("staticActivity", staticActivity);
        return UPDATE;
    }

    @ModelAttribute("preloadBrand")
    public StaticActivity getOne(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            StaticActivity staticActivity = staticActivityService.get(id);
            return staticActivity;
        }
        return null;
    }

    @Log(message = "修改了{0}静态活动的信息。")
    @RequiresPermissions("StaticActivity:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    String update(@Valid @ModelAttribute("preloadBrand") StaticActivity staticActivity) {
        staticActivityService.update(staticActivity);
//        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { staticActivity.getBrandName() }));
        return AjaxObject.newOk("修改成功！").toString();
    }

    @Log(message = "删除了{0}静态活动。")
    @RequiresPermissions("StaticActivity:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    String deleteMany(Long[] ids) {
        String[] brandnames = new String[ids.length];
        try {
            for (int i = 0; i < ids.length; i++) {
                StaticActivity staticActivity = staticActivityService.get(ids[i]);
                staticActivityService.delete(staticActivity.getId());
//                brandnames[i] = staticActivity.getBrandName();
            }
        } catch (ServiceException e) {
            return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
        }

        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { Arrays.toString(brandnames) }));
        return AjaxObject.newOk("删除成功！").setCallbackType("").toString();
    }
    
    /**
     * 图片上传
     * 
     * @param request
     * @param file
     * @param map
     * @return
     */
    @RequiresPermissions("StaticActivity:upload")
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

}
