package com.shangpin.manager.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.validation.Valid;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.core.entity.main.Feedback;
import com.shangpin.core.log.Log;
import com.shangpin.core.log.LogMessageObject;
import com.shangpin.core.service.FeedbackService;
import com.shangpin.core.util.dwz.AjaxObject;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.persistence.DynamicSpecifications;
import com.shangpin.manager.log.impl.LogUitl;

/**
 * 反馈信息控制层
 * 
 * @author zhanghongwei
 * 
 */
@Controller
@RequestMapping(value = "/management/admin/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // 新增反馈信息页面
    private static final String CREATE = "management/admin/feedback/create";
    // 反馈信息列表页面
    private static final String LIST = "management/admin/feedback/list";
    // 更新反馈信息列表页面
    private static final String UPDATE = "management/admin/feedback/update";

    @InitBinder
    public void dataBinder(WebDataBinder dataBinder) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
    }

    @RequiresPermissions("Feedback:save")
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String preCreate() {
        return CREATE;
    }

    @Log(message = "添加了{0}反馈信息。")
    @RequiresPermissions("Feedback:save")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public @ResponseBody
    String create(@Valid Feedback feedback) {
        feedback.setCreateTime(new Date());
        feedbackService.save(feedback);
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { feedback.getId() }));
        return AjaxObject.newOk("反馈信息添加成功！").toString();
    }

    @RequiresPermissions("Feedback:view")
    @RequestMapping(value = "/list", method = { RequestMethod.POST, RequestMethod.GET })
    public String list(Page page, ServletRequest request, Map<String, Object> map) {
        Specification<Feedback> spec = DynamicSpecifications.bySearchFilter(request, Feedback.class);
        List<Feedback> feedbackList = feedbackService.findAll(spec, page);
        map.put("feedbacks", feedbackList);
        map.put("page", page);
        return LIST;
    }

    @RequiresPermissions("Feedback:edit")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
        Feedback feedback = feedbackService.get(id);
        map.put("feedback", feedback);
        return UPDATE;
    }

    @Log(message = "修改{0}反馈信息。")
    @RequiresPermissions("Feedback:edit")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody
    String update(@Valid @ModelAttribute("preloadFeedback") Feedback feedback) {
        feedbackService.update(feedback);
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { feedback.getId() }));
        return AjaxObject.newOk("反馈信息修改成功！").toString();
    }

    @Log(message = "删除了{0}反馈信息。")
    @RequiresPermissions("Feedback:delete")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public @ResponseBody
    String delete(@PathVariable Long id) {
        Feedback feedback = feedbackService.get(id);
        feedbackService.delete(feedback.getId());
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { feedback.getId() }));
        return AjaxObject.newOk("删除反馈信息成功！").setCallbackType("").toString();
    }

    @Log(message = "删除了{0}反馈信息。")
    @RequiresPermissions("Feedback:delete")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    String deleteMany(Long[] ids) {
        Long[] feedbackId = new Long[ids.length];
        for (int i = 0; i < ids.length; i++) {
            Feedback feedback = feedbackService.get(ids[i]);
            feedbackService.delete(feedback.getId());
            feedbackId[i] = feedback.getId();
        }
        LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { Arrays.toString(feedbackId) }));
        return AjaxObject.newOk("删除成功！").setCallbackType("").toString();
    }

    @ModelAttribute("preloadFeedback")
    public Feedback getOne(@RequestParam(value = "id", required = false) Long id) {
        if (id != null) {
            Feedback feedback = feedbackService.get(id);
            return feedback;
        }
        return null;
    }
}
