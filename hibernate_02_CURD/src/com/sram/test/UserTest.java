package com.sram.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.sram.beans.User;

/**
 * 
 * 验证CURD
 * 
 * 【注】，体会hibernate工作流程
 *
 */
public class UserTest {
	
	@Test
	public void testSave(){//测试保存
		// 1读取配置文件(JDBC)
		Configuration con=new Configuration().configure();
		// 2创建sessionFactory(JDBC数据源)
		SessionFactory sf=con.buildSessionFactory();
		// 3创建session(Connection)
		Session session=sf.openSession();
		// 4开启事务(保证线程安全，'一原隔持')
		session.beginTransaction();
		// 操作，保存一个User对象
		User user=new User("wx","wx112233",25);
		session.save(user);
		// 5提交事务(对数据库操作永久性的)
		session.getTransaction().commit();
		// 6关闭session与sessionFactory(不关闭session，导致数据库连接数量增多，最终导致数据库拒绝连接)
		session.close();
		sf.close();
	}
	
	@Test
	public void testDelete(){//测试删除
		// 1读取配置文件(JDBC)
		Configuration con=new Configuration().configure();
		// 2创建sessionFactory(JDBC数据源)
		SessionFactory sf=con.buildSessionFactory();
		// 3创建session(Connection)
		Session session=sf.openSession();
		// 4开启事务(保证线程安全，'一原隔持')
		session.beginTransaction();
		// 操作，删除一个User对象
		User user=new User();
		user.setId(6);
		session.delete(user);
		// 5提交事务(对数据库的操作是永久性的)
		session.getTransaction().commit();
		// 6关闭session与sessionFactory
		session.close();
		sf.close();
	}
	
	@Test
	public void testUpdate(){//测试编辑
		// 1读取配置文件(JDBC)
		Configuration con=new Configuration().configure();
		// 2创建sessionFactory(JDBC数据源)
		SessionFactory sf=con.buildSessionFactory();
		// 3创建session(Connection)
		Session session=sf.openSession();
		// 4开启事务(保证线程安全，'一原隔持')
		session.beginTransaction();
		// 操作，编辑一个User对象
		User user=new User("zs","abc123",20);
		user.setId(2);
		session.update(user);
		// 5提交事务(对数据库的操作是永久性的)
		session.getTransaction().commit();
		// 6关闭session和sessionFactory
		session.close();
		sf.close();
	}
	
	@Test
	public void testGet(){//测试查询单个对象---【注】查询不必开启事务，查询并不会操作数据库
		// 1读取配置文件(JDBC)
		Configuration con=new Configuration().configure();
		// 2创建sessionFactory(JDBC数据源)
		SessionFactory sf=con.buildSessionFactory();
		// 3创建session(Connection)
		Session session=sf.openSession();
		// 操作，查询单个对象
		User user=(User) session.get(User.class, 1);
		System.out.println(user);
		// 4关闭session和sessionFactory(不关闭session，导致数据库连接数量增多，最终导致数据库拒绝连接)
		session.close();
		sf.close();
	}
	
	@Test
	public void testList(){//测试查询对象列表---【注】查询不必开启事务，查询并不会操作数据库
		// 1读取配置文件(JDBC)
		Configuration con=new Configuration().configure();
		// 2创建sessionFactory(JDBC数据源)
		SessionFactory sf=con.buildSessionFactory();
		// 3创建session(Connection)
		Session session=sf.openSession();
		// 操作，查询对象列表
		List<User>lists=session.createQuery("from User").list();
		System.out.println(lists);
		// 4关闭session和sessionFctory(不关闭session，导致数据库连接数量增多，最终导致数据库拒绝连接)
		session.close();
		sf.close();
	}
	
	@Test
	public void testSession(){// 测试session创建方式opensession与getCurrentSession
		// 1读取配置文件(JDBC)
		Configuration con=new Configuration().configure();
		// 2创建sessionFactory(JDBC数据源，建表)
		SessionFactory sf=con.buildSessionFactory();
		// 3创建session（Connection，操作表）
		//Session session1=sf.openSession();
		//Session session2=sf.openSession();
		Session session1=sf.getCurrentSession();
		Session session2=sf.getCurrentSession();
		System.out.println(session1==session2);
		// 4开始事务（保证线程安全，'一原隔持'）
		session1.beginTransaction();
		User user=new User("zsf","123",25);
		session1.save(user);
		// 5提交事务（数据库操作永久性的）
		session1.getTransaction().commit();
		// 6关闭session与sessionFactory
		//session1.close();
		//session2.close();
		sf.close();
	}
	
	@Test
	public void testGetLoad(){// 测试get/load
		// 1读取配置文件（JDBC）
		Configuration con=new Configuration().configure();
		// 2创建sessionFactory（JDBC数据源，建表）
		SessionFactory sf=con.buildSessionFactory();
		// 3创建session（Connection，操作表）
		Session session=sf.openSession();
		// 4开启事务（保证线程安全，'一原隔持'）
		session.beginTransaction();
		// 操作
		// get立即发送sql
		//User user=(User) session.get(User.class, 1);
		// load不会立即发送sql，只用调用对象才发送sql，返回user代理对象，该对象只有一个id值
		User user=(User) session.load(User.class, 1);
		System.out.println(user.getId());
		// 5提交事务(数据库操作永久性的)
		session.getTransaction().commit();
		// 6关闭session与sessionFactory（不关闭session，导致数据库连接数量增加，最终导致数据库拒绝访问）
		session.close();
		sf.close();
	}
	
}
