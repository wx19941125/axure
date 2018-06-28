package com.sram.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sram.beans.User;

/**
 * 
 * 【注】，体会hibernate 工作流程
 * 
 */
public class UserTest {
	
	/**
	 * hibernate工作流程
	 */
	public static void main(String[] args) {
		// 1读取配置文件(JDBC)
		Configuration con=new Configuration().configure();
		// 2创建sessionFactory（JDBC连接源）
		SessionFactory sf=con.buildSessionFactory();
		// 3创建session(Connection)
		Session session=sf.openSession(); 
		// 4开启事务(保证线程安全，'一原隔持')
		session.beginTransaction();
		// 保存一个User对象
		User user=new User("wx","abc123",25);
		session.save(user);
		// 5提交事务
		session.getTransaction().commit();
		// 6关闭session与session工厂(不关闭session，导致数据库连接数量增多，最终导致数据库拒绝连接)
		session.close();
		sf.close();
	}
	
}
