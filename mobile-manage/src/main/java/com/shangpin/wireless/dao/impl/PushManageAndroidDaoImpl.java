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
import com.shangpin.wireless.dao.PushManageAndroidDao;
import com.shangpin.wireless.domain.PushManageAndroid;

/**
 * 推送消息配置
 * 
 * @Author zhouyu
 * @CreatDate 2013-02-25
 */
@Repository(PushManageAndroidDao.DAO_NAME)
public class PushManageAndroidDaoImpl extends ApiBaseDaoImpl<PushManageAndroid> implements PushManageAndroidDao {
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
		List executeSqlToMap = executeSqlToMap("SELECT MAX(id) FROM pushManage_android");
		return Long.valueOf(((Map) executeSqlToMap.get(0)).get("MAX(id)").toString());
	}

	@Override
	public List<PushManageAndroid> findPushManageAndroid(int gender, BigInteger productNum) throws Exception {
		String sql = "SELECT p.id id,p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent FROM pushManage_android p WHERE p.username='' AND p.productNum=? AND (p.showtime>NOW() OR createtime>DATE_ADD(NOW(), INTERVAL -1 DAY)) AND p.msgType=? ORDER BY createtime";
		// if (gender == 0) {
		// sql = "SELECT p.id id,p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent FROM pushManage_android p WHERE p.username='' AND p.productNum=? AND (p.showtime>NOW() OR createtime>DATE_ADD(NOW(), INTERVAL -1 DAY)) AND (p.msgType=0 OR p.msgType=2) ORDER BY createtime";
		// } else if (gender == 1) {
		// sql = "SELECT p.id id,p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent FROM pushManage_android p WHERE p.username='' AND p.productNum=? AND (p.showtime>NOW() OR createtime>DATE_ADD(NOW(), INTERVAL -1 DAY)) AND (p.msgType=1 OR p.msgType=2) ORDER BY createtime";
		// } else {
		// sql = "SELECT p.id id,p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent FROM pushManage_android p WHERE p.username='' AND p.productNum=? AND (p.showtime>NOW() OR createtime>DATE_ADD(NOW(), INTERVAL -1 DAY)) ORDER BY createtime";
		// }
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setBigInteger(0, productNum).setInteger(1, gender);
		query.addScalar("id", LongType.INSTANCE).addScalar("action", StringType.INSTANCE).addScalar("actionarg", StringType.INSTANCE).addScalar("actionobj", StringType.INSTANCE).addScalar("showTime", TimestampType.INSTANCE).addScalar("createTime", TimestampType.INSTANCE).addScalar("pushContent", StringType.INSTANCE).setResultTransformer(Transformers.aliasToBean(PushManageAndroid.class));
		return query.list();
	}

	@Override
	public List<PushManageAndroid> findPushManageAndroidByUser(String userId, BigInteger productNum) throws Exception {
		String sql = "select p.pushContent pushContent, p.id id,p.action action,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime from pushManage_android p where p.pushType=0 and p.userId=? and p.productNum=? order by p.createtime";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setString(0, userId).setBigInteger(1, productNum);
		query.addScalar("pushContent", StringType.INSTANCE).addScalar("id", LongType.INSTANCE).addScalar("action", StringType.INSTANCE).addScalar("actionarg", StringType.INSTANCE).addScalar("actionobj", StringType.INSTANCE).addScalar("showTime", TimestampType.INSTANCE).addScalar("createTime", TimestampType.INSTANCE).setResultTransformer(Transformers.aliasToBean(PushManageAndroid.class));
		return query.list();
	}

	@Override
	public void updatePushManageAndroidType(Long id) throws Exception {
		String sql = "UPDATE pushManage_android SET pushType=1 WHERE id=?";
		SQLQuery query = this.getSession().createSQLQuery(sql);
		query.setLong(0, id).executeUpdate();
	}
}
