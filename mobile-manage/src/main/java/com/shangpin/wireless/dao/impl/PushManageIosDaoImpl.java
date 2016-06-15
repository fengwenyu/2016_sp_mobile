package com.shangpin.wireless.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.LongType;
import org.hibernate.type.StringType;
import org.hibernate.type.TimestampType;
import org.springframework.stereotype.Repository;

import com.shangpin.wireless.base.dao.hibernate.ApiBaseDaoImpl;
import com.shangpin.wireless.dao.PushManageIosDao;
import com.shangpin.wireless.domain.PushManageIos;

/**
 * 推送消息配置
 * 
 * @Author zhouyu
 * @CreatDate 2013-02-25
 */
@Repository(PushManageIosDao.DAO_NAME)
public class PushManageIosDaoImpl extends ApiBaseDaoImpl<PushManageIos> implements PushManageIosDao {
	/**
	 * 查询最大id
	 * 
	 * @throws Exception
	 * 
	 * @Author zhouyu
	 * @CreatDate 2012-07-11
	 * @Return long
	 */
	public long getMaxId() throws Exception {
		List executeSqlToMap = executeSqlToMap("SELECT MAX(id) FROM pushManage_ios");
		return Long.valueOf(((Map) executeSqlToMap.get(0)).get("MAX(id)").toString());
	}

	@Override
	public List<PushManageIos> findPushManageIOS(int gender, BigInteger productNum) throws Exception {
		String sql = "";
		sql = "SELECT p.id id,p.title,p.notice, p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent FROM pushManage_ios p WHERE p.username='' AND p.productNum=? AND pushType=0 AND (p.showtime>DATE_ADD(NOW(), INTERVAL -2 HOUR) AND p.showtime< NOW() OR createtime>DATE_ADD(NOW(), INTERVAL -1 DAY)) AND p.msgType=? ORDER BY createtime";
//		if (gender == 0) {
//			sql = "SELECT p.id id,p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent FROM pushManage_ios p WHERE p.username='' AND p.productNum=? AND pushType=0 AND (p.showtime>DATE_ADD(NOW(), INTERVAL -2 HOUR) AND p.showtime< NOW() OR createtime>DATE_ADD(NOW(), INTERVAL -1 DAY)) AND p.msgType=0 ORDER BY createtime";
//		} else if (gender == 1) {
//			sql = "SELECT p.id id,p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent FROM pushManage_ios p WHERE p.username='' AND p.productNum=? AND pushType=0 AND (p.showtime>DATE_ADD(NOW(), INTERVAL -2 HOUR) AND p.showtime< NOW() OR createtime>DATE_ADD(NOW(), INTERVAL -1 DAY)) AND p.msgType=1 ORDER BY createtime";
//		} else {
//			sql = "SELECT p.id id,p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent FROM pushManage_ios p WHERE p.username='' AND p.productNum=? AND pushType=0 AND (p.showtime>DATE_ADD(NOW(), INTERVAL -2 HOUR) AND p.showtime< NOW() OR createtime>DATE_ADD(NOW(), INTERVAL -1 DAY)) AND p.msgType=2 ORDER BY createtime";
//		}
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setBigInteger(0, productNum).setInteger(1, gender);
		query.addScalar("id", LongType.INSTANCE).addScalar("title", StringType.INSTANCE).addScalar("action", StringType.INSTANCE).addScalar("notice", StringType.INSTANCE).addScalar("actionarg", StringType.INSTANCE).addScalar("actionobj", StringType.INSTANCE).addScalar("showTime", TimestampType.INSTANCE).addScalar("createTime", TimestampType.INSTANCE).addScalar("pushContent", StringType.INSTANCE).setResultTransformer(Transformers.aliasToBean(PushManageIos.class));
		return query.list();
	}

	@Override
	public List<PushManageIos> findPushManageIOSByUser(BigInteger productNum) throws Exception {
		String sql = "SELECT p.id id,p.action ACTION,p.title,p.notice, p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent,p.userId userId FROM pushManage_ios p WHERE p.pushType=0 AND p.username!='' AND p.username is not null AND p.pushContent is not null AND p.productNum=? ORDER BY p.createtime";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setBigInteger(0, productNum);
		query.addScalar("id", LongType.INSTANCE).addScalar("title", StringType.INSTANCE).addScalar("action", StringType.INSTANCE).addScalar("notice", StringType.INSTANCE).addScalar("actionarg", StringType.INSTANCE).addScalar("actionobj", StringType.INSTANCE).addScalar("showTime", TimestampType.INSTANCE).addScalar("createTime", TimestampType.INSTANCE).addScalar("pushContent", StringType.INSTANCE).addScalar("userId", StringType.INSTANCE).setResultTransformer(Transformers.aliasToBean(PushManageIos.class));
		return query.list();
	}

	@Override
	public void updatePushManageIOSType(Long id) throws Exception {
		String sql = "UPDATE pushManage_ios SET pushType=1 WHERE id=?";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setLong(0, id).executeUpdate();
	}
}
