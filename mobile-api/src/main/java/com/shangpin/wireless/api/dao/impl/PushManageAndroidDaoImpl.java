package com.shangpin.wireless.api.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.PushManageAndroidDao;
import com.shangpin.wireless.api.domain.PushManageAndroid;

/**
 * push数据访问层接口实现类，提供，android平台push信息内容
 * 
 * @Author yumeng
 * @CreateDate: 2013-02-22
 */
@Repository(PushManageAndroidDao.DAO_NAME)
@SuppressWarnings({ "unchecked"})
public class PushManageAndroidDaoImpl extends BaseDaoImpl<PushManageAndroid> implements PushManageAndroidDao {
	@Override
	public List<PushManageAndroid> findPushManageAndroid(int gender, BigInteger productNum) throws Exception {
//		String sql = "SELECT p.id id,p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent FROM pushManage_android p WHERE p.username='' AND p.productNum=? AND (p.showtime>NOW() OR createtime>DATE_ADD(NOW(), INTERVAL -1 DAY)) AND p.msgType=? ORDER BY createtime";
		String sql;
		if (gender == 0) {
//			sql = "SELECT p.id id,p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent FROM pushManage_android p WHERE p.username='' AND p.productNum=? AND (p.showtime>NOW() OR createtime>DATE_ADD(NOW(), INTERVAL -1 DAY)) AND (p.msgType=0 OR p.msgType=2) ORDER BY createtime";
			sql = "SELECT p.id id,p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent FROM pushManage_android p WHERE p.username='' AND p.productNum=? AND (p.showtime<NOW() AND p.endTime>NOW()) AND (p.msgType=0 OR p.msgType=2) ORDER BY createtime";
		} else if (gender == 1) {
			sql = "SELECT p.id id,p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent FROM pushManage_android p WHERE p.username='' AND p.productNum=? AND (p.showtime<NOW() AND p.endTime>NOW()) AND (p.msgType=1 OR p.msgType=2) ORDER BY createtime";
		} else {
//			sql = "SELECT p.id id,p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent FROM pushManage_android p WHERE p.username='' AND p.productNum=? AND (p.showtime>NOW() OR createtime>DATE_ADD(NOW(), INTERVAL -1 DAY)) ORDER BY createtime";
			sql = "SELECT p.id id,p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent FROM pushManage_android p WHERE p.username='' AND p.productNum=? AND (p.showtime<NOW() AND p.endTime>NOW()) AND p.msgType=2 ORDER BY createtime";
		}
		SQLQuery query = this.getSession(null).createSQLQuery(sql);
		query.setBigInteger(0, productNum);
//		query.setInteger(1, gender);
		query.addScalar("id", StandardBasicTypes.LONG).addScalar("action", StandardBasicTypes.STRING).addScalar("actionarg", StandardBasicTypes.STRING).addScalar("actionobj", StandardBasicTypes.STRING).addScalar("showTime", StandardBasicTypes.TIMESTAMP).addScalar("createTime", StandardBasicTypes.TIMESTAMP).addScalar("pushContent", StandardBasicTypes.STRING).setResultTransformer(Transformers.aliasToBean(PushManageAndroid.class));
		return query.list();
	}

	@Override
	public List<PushManageAndroid> findPushManageAndroidByUser(String userId, BigInteger productNum) throws Exception {
		String sql = "select p.pushContent pushContent, p.id id,p.action action,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime from pushManage_android p where p.pushType=0 and p.userId=? and p.productNum=? order by p.createtime";
		SQLQuery query = this.getSession(null).createSQLQuery(sql);
		query.setString(0, userId);
		query.setBigInteger(1, productNum);
		query.addScalar("pushContent", StandardBasicTypes.STRING).addScalar("id", StandardBasicTypes.LONG).addScalar("action", StandardBasicTypes.STRING).addScalar("actionarg", StandardBasicTypes.STRING).addScalar("actionobj", StandardBasicTypes.STRING).addScalar("showTime", StandardBasicTypes.TIMESTAMP).addScalar("createTime", StandardBasicTypes.TIMESTAMP).setResultTransformer(Transformers.aliasToBean(PushManageAndroid.class));
		return query.list();
	}

	@Override
	public void updatePushManageAndroidType(Long id) throws Exception {
		String sql = "UPDATE pushManage_android SET pushType=1 WHERE id=?";
		SQLQuery query = this.getSession(null).createSQLQuery(sql);
		query.setLong(0, id).executeUpdate();
	}
}