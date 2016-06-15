package com.shangpin.wireless.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shangpin.wireless.domain.Privilege;
import com.shangpin.wireless.manage.service.PrivilegeService;
import com.shangpin.wireless.util.ApplicationContextUtil;

/**
 *  数据初始化监听器
 * @Author  zhouyu
 * @CreatDate  2012-07-12
 */
public class InitServletContextListener implements ServletContextListener {
	protected final Log log = LogFactory.getLog(InitServletContextListener.class);

	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("InitServletContextListener::contextInitialized " + Thread.currentThread().getId() + "--" + Thread.currentThread().getName());
		ServletContext application = sce.getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application); // 获取在Spring的监听器器创建的ApplicationContext对象
		PrivilegeService privilegeService = (PrivilegeService) ac.getBean(PrivilegeService.SERVICE_NAME);
		ApplicationContextUtil.add(ac);
		try {
			// 准备数据（放到了application中），这个操作应在ServletContextListener中做
			List<Privilege> topPrivielgeList = privilegeService.findTopList();
			application.setAttribute("topPrivielgeList", topPrivielgeList);

			// 准备数据（放到了application中），这个操作应在ServletContextListener中做
			List<String> allPrivilegeUrls = privilegeService.getAllPrivilegeUrls();
			application.setAttribute("allPrivilegeUrls", allPrivilegeUrls);
		} catch (Exception e) {
			log.error("----------------InitServletContextListener ----------------" + e);
		}
	}

	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("InitServletContextListener::contextDestroyed " + Thread.currentThread().getId() + "--" + Thread.currentThread().getName());
	}

}
