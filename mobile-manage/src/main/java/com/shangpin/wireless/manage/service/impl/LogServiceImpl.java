package com.shangpin.wireless.manage.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.LogDao;
import com.shangpin.wireless.domain.Header;
import com.shangpin.wireless.domain.LogObject;
import com.shangpin.wireless.domain.PageView;
import com.shangpin.wireless.manage.service.LogService;
import com.shangpin.wireless.manage.service.PageViewService;

/**
 * 日志解析
 * 
 * @Author zhouyu
 * @CreatDate 2012-10-24
 */
@Service(LogService.SERVICE_NAME)
public class LogServiceImpl implements LogService {
	@Resource(name = PageViewService.SERVICE_NAME)
	private PageViewService pageViewService;
	@Resource(name = LogDao.DAO_NAME)
	private LogDao logDao;
	/**
	 * 保存PV记录
	 * 
	 * @Author zhouyu
	 * @CreateDate 2012-10-24
	 * @param logObj
	 *            log实体
	 * @Return
	 */
	public void savePageView(LogObject logObj) throws Exception {
		Header header = logObj.getHeader();
		PageView pageView = new PageView();
		pageView.setApiname(logObj.getInterfaceName());
		pageView.setVisitIp(logObj.getIp());
		pageView.setImei(header.getImei());
		pageView.setOs(header.getOs());
		pageView.setOsv(header.getOsv());
		pageView.setProductNum(Long.valueOf(header.getProductNum()));
		pageView.setChannelNum(Long.valueOf(header.getChannelNum()));
		pageView.setApn(header.getApn());
		pageView.setVer(header.getVer());
		pageView.setParams(logObj.getParams());
		pageView.setPhoneType(header.getMt());
		pageView.setPhoneModel(header.getModel());
		pageView.setCreateTime(logObj.getDate());
//		pageViewService.save(pageView);
	}
}
