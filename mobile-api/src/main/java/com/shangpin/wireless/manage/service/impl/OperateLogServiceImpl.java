package com.shangpin.wireless.manage.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.OperateLogDao;
import com.shangpin.wireless.api.domain.OperateLog;
import com.shangpin.wireless.manage.service.OperateLogService;

/**
 * 操作日志
 * 
 * @Author: zhouyu
 * @CreatDate: 2012-08-02
 */
@Service(OperateLogService.SERVICE_NAME)
public class OperateLogServiceImpl extends BaseDaoImpl<OperateLog> implements OperateLogService {

	protected final Log log = LogFactory.getLog(OperateLogServiceImpl.class);
	@Resource(name = OperateLogDao.DAO_NAME)
	private OperateLogDao operateLogDao;

	/**
	 * 保存操作日志
	 * 
	 * @Author: zhouyu
	 * @CreatDate: 2012-08-02
	 * @param userName
	 *            用户
	 * @param content
	 *            操作日志内容
	 * @Return
	 */
	public void saveLog(String userName, String content) {
		try {
			OperateLog operateLog = new OperateLog();
			operateLog.setOperateUserName(userName);// 操作人
			operateLog.setContent(content);// 操作内容
			
			operateLog.setOperateTime(new Date());// 操作时间
			operateLogDao.save(operateLog, "dataSourceManage");// 保存到数据库中
		} catch (Exception e) {
			log.error("OperateLogServiceImpl-saveLog():" + e);
		}
	}

}
