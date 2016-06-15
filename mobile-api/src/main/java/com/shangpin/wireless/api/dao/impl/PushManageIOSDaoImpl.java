package com.shangpin.wireless.api.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.shangpin.wireless.api.base.dao.hibernate.BaseDaoImpl;
import com.shangpin.wireless.api.dao.PushManageIOSDao;
import com.shangpin.wireless.api.domain.PushManageIos;

/**
 * push数据访问层接口实现类，提供，IOS平台push信息内容
 * 
 * @Author yumeng
 * @CreateDate: 2013-02-22
 */
@Repository(PushManageIOSDao.DAO_NAME)
@SuppressWarnings({ "unchecked" })
public class PushManageIOSDaoImpl extends BaseDaoImpl<PushManageIos> implements PushManageIOSDao {
	@Override
	public List<PushManageIos> findPushManageIOS(int gender, BigInteger productNum) throws Exception {
		String sql = "";
		sql = "SELECT p.id id,p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent FROM pushManage_ios p WHERE p.username='' AND p.productNum=? AND pushType=0 AND (p.showtime>DATE_ADD(NOW(), INTERVAL -2 HOUR) AND p.showtime< NOW() OR createtime>DATE_ADD(NOW(), INTERVAL -1 DAY)) AND p.msgType=? ORDER BY createtime";
//		if (gender == 0) {
//			sql = "SELECT p.id id,p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent FROM pushManage_ios p WHERE p.username='' AND p.productNum=? AND pushType=0 AND (p.showtime>DATE_ADD(NOW(), INTERVAL -2 HOUR) AND p.showtime< NOW() OR createtime>DATE_ADD(NOW(), INTERVAL -1 DAY)) AND p.msgType=0 ORDER BY createtime";
//		} else if (gender == 1) {
//			sql = "SELECT p.id id,p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent FROM pushManage_ios p WHERE p.username='' AND p.productNum=? AND pushType=0 AND (p.showtime>DATE_ADD(NOW(), INTERVAL -2 HOUR) AND p.showtime< NOW() OR createtime>DATE_ADD(NOW(), INTERVAL -1 DAY)) AND p.msgType=1 ORDER BY createtime";
//		} else {
//			sql = "SELECT p.id id,p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent FROM pushManage_ios p WHERE p.username='' AND p.productNum=? AND pushType=0 AND (p.showtime>DATE_ADD(NOW(), INTERVAL -2 HOUR) AND p.showtime< NOW() OR createtime>DATE_ADD(NOW(), INTERVAL -1 DAY)) AND p.msgType=2 ORDER BY createtime";
//		}
		SQLQuery query = this.getSession(null).createSQLQuery(sql);
		query.setBigInteger(0, productNum).setInteger(1, gender);
		query.addScalar("id", StandardBasicTypes.LONG).addScalar("action", StandardBasicTypes.STRING).addScalar("actionarg", StandardBasicTypes.STRING).addScalar("actionobj", StandardBasicTypes.STRING).addScalar("showTime", StandardBasicTypes.TIMESTAMP).addScalar("createTime", StandardBasicTypes.TIMESTAMP).addScalar("pushContent", StandardBasicTypes.STRING).setResultTransformer(Transformers.aliasToBean(PushManageIos.class));
		return query.list();
	}

	@Override
	public List<PushManageIos> findPushManageIOSByUser(BigInteger productNum) throws Exception {
		String sql = "SELECT p.id id,p.action ACTION,p.actionarg actionarg,p.actionobj actionobj,p.showTime showTime,p.createTime createTime,p.pushContent pushContent,p.userId userId FROM pushManage_ios p WHERE p.pushType=0 AND p.username!='' AND p.username is not null AND p.pushContent is not null AND p.productNum=? ORDER BY p.createtime";
		SQLQuery query = this.getSession(null).createSQLQuery(sql);
		query.setBigInteger(0, productNum);
		query.addScalar("id", StandardBasicTypes.LONG).addScalar("action", StandardBasicTypes.STRING).addScalar("actionarg", StandardBasicTypes.STRING).addScalar("actionobj", StandardBasicTypes.STRING).addScalar("showTime", StandardBasicTypes.TIMESTAMP).addScalar("createTime", StandardBasicTypes.TIMESTAMP).addScalar("pushContent", StandardBasicTypes.STRING).addScalar("userId", StandardBasicTypes.STRING).setResultTransformer(Transformers.aliasToBean(PushManageIos.class));
		return query.list();
	}

	@Override
	public void updatePushManageIOSType(Long id) throws Exception {
		String sql = "UPDATE pushManage_ios SET pushType=1 WHERE id=?";
		SQLQuery query = this.getSession(null).createSQLQuery(sql);
		query.setLong(0, id).executeUpdate();
	}
}