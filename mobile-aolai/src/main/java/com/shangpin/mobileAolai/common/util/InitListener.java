package com.shangpin.mobileAolai.common.util;

import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.shangpin.mobileAolai.platform.service.SysParametersService;
import com.shangpin.mobileAolai.platform.vo.DeliveryVO;
import com.shangpin.mobileAolai.platform.vo.ProvinceVO;


/**
 * 此监听器随容器启动，作用是初始化数据。
 * 
 * @author yumeng
 * @date: 2012-11-8
 */
@SuppressWarnings("unchecked")
public class InitListener implements ServletContextListener {

	private static Log logger = LogFactory.getLog(InitListener.class);

	/**
	 * Default constructor.
	 */
	public InitListener() {
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent context) {
		this.initPay();
		this.initDelivery();
		this.initProvince(context);
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// 清理容器
		if (!DataContainerPool.sysContainer.isEmpty())
			DataContainerPool.sysContainer.clear();
	}

	/**
	 * spring整合listener。
	 * 
	 * @param context
	 *            servlet上下文事件
	 * 
	 * @param str
	 *            spring bean 名字
	 * 
	 * @return bean实例
	 */
	private Object getBean(ServletContextEvent context, String str) {
		// 通过spring获取bean
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context.getServletContext());
		return wac.getBean(str);
	}

	/**
	 * 通过读取sysParameters.xml，初始化支付方式，并放入缓存池中。
	 */
	private void initPay() {
		/*try {
			ResourceBundle rb = ResourceBundle.getBundle("sysParameters");
			String str = rb.getString("pay");
			JSONObject jObj = JSONObject.fromObject(str);
			JSONArray jarray = (JSONArray) jObj.get("pay");
			List<PayVO> list = JSONArray.toList(jarray, new PayVO(), new JsonConfig());
			DataContainerPool.sysContainer.putOrReplace("payList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}*/
		ProReader.load("paymentch");
	}

	/**
	 * 通过读取sysParameters.xml，初始化配送方式，并放入缓存池中。
	 */
	private void initDelivery() {
		try {
			ResourceBundle rb = ResourceBundle.getBundle("sysParameters");
			String str = rb.getString("delivery");
			JSONObject jObj = JSONObject.fromObject(str);
			JSONArray jarray = (JSONArray) jObj.get("delivery");
			List<DeliveryVO> list = JSONArray.toList(jarray, new DeliveryVO(), new JsonConfig());
			DataContainerPool.sysContainer.putOrReplace("deliveryList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}

	/**
	 * 将省级信息列表，放入缓存池中。
	 */
	private void initProvince(ServletContextEvent context){
		try {
			SysParametersService sysParametersService = (SysParametersService)getBean(context,"sysParametersService");
			List<ProvinceVO> list = sysParametersService.getProvinceList();
			DataContainerPool.sysContainer.putOrReplace("provinceList", list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			e.printStackTrace();
		}
	}
}