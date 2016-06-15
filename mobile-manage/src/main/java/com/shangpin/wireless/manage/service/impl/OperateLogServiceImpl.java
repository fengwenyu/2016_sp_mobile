package com.shangpin.wireless.manage.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.shangpin.wireless.dao.OperateLogDao;
import com.shangpin.wireless.domain.OperateLog;
import com.shangpin.wireless.domain.User;
import com.shangpin.wireless.manage.service.OperateLogService;

/**
 * 操作日志
 * 
 * @Author zhouyu
 * @CreatDate 2012-07-13
 */
@Service(OperateLogService.SERVICE_NAME)
public class OperateLogServiceImpl implements OperateLogService {
	protected final Log log = LogFactory.getLog(OperateLogServiceImpl.class);
	@Resource(name = OperateLogDao.DAO_NAME)
	private OperateLogDao operateLogDao;

	/**
	 * 保存操作日志
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-16
	 * @param user
	 *            用户
	 * @param content
	 *            操作日志内容
	 * @Return
	 * @throws Exception
	 */
	public void saveLog(User user, String content) {
		try {
			OperateLog operateLog = new OperateLog();
			operateLog.setOperateUserName(user.getLoginName());// 操作人
			operateLog.setContent(content);// 操作内容
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			operateLog.setOperateTime(sdf.parse(sdf.format(new Date())));// 操作时间
			operateLogDao.save(operateLog);// 保存到数据库中
		} catch (Exception e) {
			log.error("OperateLogServiceImpl-saveLog() " + e);
		}
	}

	/**
	 * 通过sql语句获取map形式的分页结果集
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param sql
	 *            查询语句
	 * @param firstRow
	 *            起始位置
	 * @param maxRow
	 *            每页需要显示的条数
	 * @Return List
	 */
	public List executeSqlToMap(String sql, int firstRow, int maxRow) throws Exception {
		return operateLogDao.executeSqlToMap(sql, firstRow, maxRow);// 保存到数据库中
	}

	/**
	 * 根据sql语句获取结果集条数
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param sql
	 *            查询语句
	 * @Return Long
	 */
	public Integer executeSqlCount(String sql) throws Exception {
		return operateLogDao.executeSqlCount(sql);
	}

	/**
	 * 根据id获取实体，如果id为null，则返回null
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param id
	 * @Return T
	 */
	public OperateLog getById(Long id) throws Exception {
		return operateLogDao.getById(id);
	}

	/**
	 * 删除实体
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @param id
	 * @Return
	 */
	public void delete(Long id) throws Exception {
		operateLogDao.delete(id);
	}
}
