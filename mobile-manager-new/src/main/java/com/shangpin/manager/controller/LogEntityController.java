/**
 * <pre>
 * Copyright:		Copyright(C) 2011-2012, shangpin.com
 * Filename:		com.shangpin.manager.controller.LogEntityController.java
 * Class:			LogEntityController
 * Date:			2013-5-26
 * Author:			<a href="mailto:sundful@gmail.com">sundful</a>
 * Version          2.1.0
 * Description:		
 *
 * </pre>
 **/
 
package com.shangpin.manager.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shangpin.core.entity.main.LogEntity;
import com.shangpin.core.log.Log;
import com.shangpin.core.log.LogLevel;
import com.shangpin.core.log.LogMessageObject;
import com.shangpin.core.service.LogEntityService;
import com.shangpin.core.util.dwz.AjaxObject;
import com.shangpin.core.util.dwz.Page;
import com.shangpin.core.util.persistence.DynamicSpecifications;
import com.shangpin.manager.log.impl.LogUitl;

/** 
 * 	
 * @author 	<a href="mailto:sundful@gmail.com">sundful</a>
 * @version  2.1.0
 * @since   2013-5-26 下午1:54:30 
 */
@Controller
@RequestMapping("/management/security/logEntity")
public class LogEntityController {
	@Autowired
	private LogEntityService logEntityService;
	
	private static final String LIST = "management/security/logEntity/list";
	
	@InitBinder
	public void dataBinder(WebDataBinder dataBinder) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		dataBinder.registerCustomEditor(Date.class, 
				new CustomDateEditor(df, true));
	}
	
	@RequiresPermissions("LogEntity:view")
	@RequestMapping(value="/list", method={RequestMethod.GET, RequestMethod.POST})
	public String list(Page page, ServletRequest request, Map<String, Object> map) {
		Specification<LogEntity> spec = DynamicSpecifications.bySearchFilter(request, LogEntity.class);
		
		List<LogEntity> logEntities = logEntityService.findByExample(spec, page); 
		
		map.put("page", page);
		map.put("logEntities", logEntities);
		map.put("logLevels", LogLevel.values());
		
		return LIST;
	}
	
	@Log(message="删除了{0}条日志。")
	@RequiresPermissions("LogEntity:delete")
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public @ResponseBody String deleteMany(Long[] ids) {
		AjaxObject ajaxObject = new AjaxObject("删除日志成功！");
		for (Long id : ids) {
			logEntityService.delete(id);
		}
		
		LogUitl.putArgs(LogMessageObject.newWrite().setObjects(new Object[]{ids.length}));
		ajaxObject.setCallbackType("");
		return ajaxObject.toString();
	}
}
