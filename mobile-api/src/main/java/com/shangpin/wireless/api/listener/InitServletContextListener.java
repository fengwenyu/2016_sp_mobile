package com.shangpin.wireless.api.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shangpin.wireless.api.util.ApplicationContextUtil;
import com.shangpin.wireless.api.util.CategoryCasheUtil;
import com.shangpin.wireless.api.util.FileUtil;
import com.shangpin.wireless.api.util.HotBrandsUtil;
import com.shangpin.wireless.api.util.ProReader;
import com.shangpin.wireless.api.util.ProductCouponUtil;
import com.shangpin.wireless.api.util.PushUtil;

/**
 * 数据初始化监听器
 * 
 * @Author: zhouyu
 * @CreateDate: 2012-07-12
 */
public class InitServletContextListener implements ServletContextListener {
	protected final Log log = LogFactory.getLog(InitServletContextListener.class);

	public void contextInitialized(ServletContextEvent sce) {
		this.initApplicationContext(sce);
		// 初始化push信息内容（android平台广播）
		PushUtil.savePushManageContainer();
		HotBrandsUtil.refreshBrandsList();
		CategoryCasheUtil.updateCache();
		ProductCouponUtil.loadProductOfCoupon();
		// new DiscoverActivtyProductUtil().refreshDiscoverCache();
		ProReader.loadFileType();
	}

	public void contextDestroyed(ServletContextEvent sce) {
		// 关闭容器，将缓存中的数据写入log文件
		FileUtil.writeRestLogBeforeShutdown();
	}

	/**
	 * 初始化ApplicationContextUtil
	 */
	private void initApplicationContext(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		ApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(application); // 获取在Spring的监听器器创建的ApplicationContext对象
		// PrivilegeWsService privilegeService = (PrivilegeWsService)
		// ac.getBean(PrivilegeWsService.SERVICE_NAME);

		ApplicationContextUtil.add(ac);
	}
}
