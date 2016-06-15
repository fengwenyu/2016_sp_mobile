package com.shangpin.manager.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.core.entity.main.HotBrand;
import com.shangpin.core.exception.ExistedException;
import com.shangpin.core.exception.ServiceException;
import com.shangpin.core.log.Log;
import com.shangpin.core.log.LogMessageObject;
import com.shangpin.core.service.HotBrandService;
import com.shangpin.core.util.dwz.AjaxObject;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.manager.log.impl.LogUitl;

/**
 * 
 * @author yangtongchui
 * @since 2014-8-6 上午午9:44:23
 */
@Controller
@RequestMapping("/management/hotBrand")
public class HotBrandController {
	@Autowired
	private HotBrandService hotBrandService;
	
	private static final String LIST = "management/admin/hotBrand/list";
	private static final String CREATE = "management/admin/hotBrand/create";
	private static final String UPDATE = "management/admin/hotBrand/update";
	
	@RequiresPermissions("HotBrand:view")
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Page page, String keywords, Map<String, Object> map) {
		List<HotBrand> hotBrands = null;
		if (StringUtils.isNotBlank(keywords)) {
			hotBrands = hotBrandService.find(page, keywords);
		} else {
			hotBrands = hotBrandService.findAll(page);
		}

		map.put("page", page);
		map.put("hotBrands", hotBrands);
		map.put("keywords", keywords);
		return LIST;
	}

	@RequiresPermissions("HotBrand:save")
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String preCreate() {
		return CREATE;
	}

	@Log(message = "添加了{0}品牌。")
	@RequiresPermissions("HotBrand:save")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public @ResponseBody
	String create(@Valid HotBrand hotBrand) {
		hotBrand.setCreateTime(new Date());
		try {
			hotBrandService.save(hotBrand);
		} catch (ExistedException e) {
			return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
		}
		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { hotBrand.getBrandName() }));
		return AjaxObject.newOk("添加成功！").toString();
	}

	@RequiresPermissions("HotBrand:edit")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
		HotBrand hotBrand = hotBrandService.get(id);
		map.put("hotBrand", hotBrand);
		return UPDATE;
	}

	@ModelAttribute("preloadBrand")
	public HotBrand getOne(@RequestParam(value = "id", required = false) Long id) {
		if (id != null) {
			HotBrand hotBrand = hotBrandService.get(id);
			return hotBrand;
		}
		return null;
	}

	@Log(message = "修改了{0}品牌的信息。")
	@RequiresPermissions("HotBrand:edit")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public @ResponseBody
	String update(@Valid @ModelAttribute("preloadBrand") HotBrand hotBrand) {
		hotBrandService.update(hotBrand);
		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { hotBrand.getBrandName() }));
		return AjaxObject.newOk("修改成功！").toString();
	}

	@Log(message = "删除了{0}品牌。")
	@RequiresPermissions("HotBrand:delete")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody
	String deleteMany(Long[] ids) {
		String[] brandnames = new String[ids.length];
		try {
			for (int i = 0; i < ids.length; i++) {
				HotBrand hotBrand = hotBrandService.get(ids[i]);
				hotBrandService.delete(hotBrand.getId());
				brandnames[i] = hotBrand.getBrandName();
			}
		} catch (ServiceException e) {
			return AjaxObject.newError(e.getMessage()).setCallbackType("").toString();
		}

		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[] { Arrays.toString(brandnames) }));
		return AjaxObject.newOk("删除成功！").setCallbackType("").toString();
	}

}
