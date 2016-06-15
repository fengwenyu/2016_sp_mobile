package com.shangpin.manager.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.core.entity.main.AppNavigation;
import com.shangpin.core.exception.ExistedException;
import com.shangpin.core.exception.ServiceException;
import com.shangpin.core.log.Log;
import com.shangpin.core.log.LogMessageObject;
import com.shangpin.core.service.AppNavigationService;
import com.shangpin.core.util.dwz.AjaxObject;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.manager.log.impl.LogUitl;

/**
 * 奥莱客户端导航编辑
 * 
 * @author yangtongchui
 * @since 2014-8-12
 * 
 */
@Controller
@RequestMapping("/management/appNavigation")
public class AppNavigationController {

	@Autowired
	private AppNavigationService appNavigationService;

	private static final String LIST = "management/admin/appNavigation/list";
	private static final String CREATE = "management/admin/appNavigation/create";
	private static final String UPDATE = "management/admin/appNavigation/update";

	@InitBinder
	public void dataBinder(WebDataBinder dataBinder) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(df, true));
	}

	@RequiresPermissions("AppNavigation:view")
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Page page, String keywords, Map<String, Object> map) {
		List<AppNavigation> appNavigations = null;
		if (StringUtils.isNotBlank(keywords)) {
			appNavigations = appNavigationService.find(page, keywords);
		} else {
			appNavigations = appNavigationService.findAll(page);
		}

		map.put("page", page);
		map.put("appNavigations", appNavigations);
		map.put("keywords", keywords);
		return LIST;
	}

	@RequiresPermissions("AppNavigation:save")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String preCreate() {
		return CREATE;
	}

	@Log(message = "添加了{0}品牌。")
	@RequiresPermissions("AppNavigation:save")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody
	String create(@Valid AppNavigation appNavigation) {
		appNavigation.setCreateTime(new Date());
		try {
			appNavigationService.save(appNavigation);
		} catch (ExistedException e) {
			return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
		}
		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(
				new Object[] { appNavigation.getNavName() }));
		return AjaxObject.newOk("添加成功！").toString();
	}

	@RequiresPermissions("AppNavigation:edit")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
		AppNavigation appNavigation = appNavigationService.get(id);
		map.put("appNavigation", appNavigation);
		return UPDATE;
	}

	@ModelAttribute("preloadBrand")
	public AppNavigation getOne(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			AppNavigation appNavigation = appNavigationService.get(id);
			return appNavigation;
		}
		return null;
	}

	@Log(message = "修改了{0}品牌的信息。")
	@RequiresPermissions("AppNavigation:edit")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	String update(@Valid @ModelAttribute("preloadBrand") AppNavigation appNavigation) {
		appNavigationService.update(appNavigation);
		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(
				new Object[] { appNavigation.getNavName() }));
		return AjaxObject.newOk("修改成功！").toString();
	}

	@Log(message = "删除了{0}品牌。")
	@RequiresPermissions("AppNavigation:delete")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	String deleteMany(Long[] ids) {
		String[] brandnames = new String[ids.length];
		try {
			for (int i = 0; i < ids.length; i++) {
				AppNavigation appNavigation = appNavigationService.get(ids[i]);
				appNavigationService.delete(appNavigation.getId());
				brandnames[i] = appNavigation.getNavName();
			}
		} catch (ServiceException e) {
			return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
		}

		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(
				new Object[] { Arrays.toString(brandnames) }));
		return AjaxObject.newOk("删除成功！").setCallbackType("").toString();
	}

}
