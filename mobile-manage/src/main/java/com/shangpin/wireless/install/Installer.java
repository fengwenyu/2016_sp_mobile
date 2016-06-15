package com.shangpin.wireless.install;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.shangpin.wireless.domain.Privilege;
import com.shangpin.wireless.domain.User;

/**
 * 初始化数据
 * 
 * @Author zhouyu
 * @CreatDate 2012-07-12
 */
@Component
public class Installer {

	@Resource(name = "sessionFactoryManage")
	private SessionFactory sessionFactory;

	public void install() {
		Session session = sessionFactory.openSession();
		// Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.beginTransaction();
		// 一、超级管理员用户
		User user = new User();
		user.setNickname("超级管理员");
		user.setLoginName("admin");
		user.setRank(100);
		user.setPwd(DigestUtils.md5Hex("admin")); // 使用MD5摘要
		session.save(user); // 保存

		// 二、权限数据
		Privilege menu0, menu1, menu1a, menu2, menu2a, menu2b, menu3, menu3a, menu4, menu5, menu6, menu7, menu7a, menu8, menu8a, menu9, menu9a;
		// ------------------------------------------------
		menu0 = new Privilege("账户管理", null, null);
		session.save(menu0);
		session.save(new Privilege("账号创建", "userAction_addUI", menu0));
		session.save(new Privilege("修改密码", "userAction_editPasswordUI", menu0));
		session.save(new Privilege("分配权限", "userAction_list", menu0));
		// ------------------------------------------------
		menu1 = new Privilege("渠道管理", null, null);
		session.save(menu1);
		session.save(new Privilege("渠道添加", "channelAction_addUI", menu1));
		menu1a = new Privilege("渠道列表", "channelAction_list", menu1);
		session.save(menu1a);
		session.save(new Privilege("渠道删除", "channelAction_delete", menu1a));
		session.save(new Privilege("渠道修改", "channelAction_edit", menu1a));
		// ------------------------------------------------
		menu8 = new Privilege("产品管理", null, null);
		session.save(menu8);
		session.save(new Privilege("产品添加", "productAction_addUI", menu8));
		menu8a = new Privilege("产品列表", "productAction_list", menu8);
		session.save(menu8a);
		session.save(new Privilege("产品删除", "productAction_delete", menu8a));
		session.save(new Privilege("产品修改", "productAction_edit", menu8a));
		// ------------------------------------------------
		menu2 = new Privilege("上线管理", null, null);
		session.save(menu2);
		menu2a = new Privilege("产品列表", "onlineManageAction_list", menu2);
		session.save(menu2a);
		menu2b = new Privilege("产品上传", "onlineManageAction_addUI", menu2);
		session.save(menu2b);
		// ------------------------------------------------
		menu3 = new Privilege("错误日志管理", null, null);
		session.save(menu3);
		menu3a = new Privilege("错误日志查询", "errorLogAction_list", menu3);
		session.save(menu3a);
		// ------------------------------------------------
		menu7 = new Privilege("操作日志管理", null, null);
		session.save(menu7);
		menu7a = new Privilege("操作日志列表", "operateLogAction_listUI", menu7);
		session.save(menu7a);
		session.save(new Privilege("操作日志删除", "operateLogAction_delete", menu7a));
		// ------------------------------------------------
		menu4 = new Privilege("平台信息统计", "platformAction_list", null);
		session.save(menu4);
		// ------------------------------------------------
		menu5 = new Privilege("用户信息统计", "accountAction_list", null);
		session.save(menu5);
		// ------------------------------------------------
		menu6 = new Privilege("订单量统计", "orderAction_list", null);
		session.save(menu6);
		tx.commit();
		// ------------------------------------------------
		menu9 = new Privilege("信息反馈管理", null, null);
		session.save(menu9);
		menu9a = new Privilege("信息反馈查询", "feedbackAction_list", menu3);
		session.save(menu9a);
		// ------------------------------------------------
	}

	public static void main(String[] args) {
		// DROP DATABASE wirelessmanage;
		// CREATE DATABASE wirelessmanage;
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		installer.install();
		System.out.println("-- end --");
	}
}
