package com.sram.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.sram.beans.User;

/**
 * 
 * ��֤CURD
 * 
 * ��ע�������hibernate��������
 *
 */
public class UserTest {
	
	@Test
	public void testSave(){//���Ա���
		// 1��ȡ�����ļ�(JDBC)
		Configuration con=new Configuration().configure();
		// 2����sessionFactory(JDBC����Դ)
		SessionFactory sf=con.buildSessionFactory();
		// 3����session(Connection)
		Session session=sf.openSession();
		// 4��������(��֤�̰߳�ȫ��'һԭ����')
		session.beginTransaction();
		// ����������һ��User����
		User user=new User("wx","wx112233",25);
		session.save(user);
		// 5�ύ����(�����ݿ���������Ե�)
		session.getTransaction().commit();
		// 6�ر�session��sessionFactory(���ر�session���������ݿ������������࣬���յ������ݿ�ܾ�����)
		session.close();
		sf.close();
	}
	
	@Test
	public void testDelete(){//����ɾ��
		// 1��ȡ�����ļ�(JDBC)
		Configuration con=new Configuration().configure();
		// 2����sessionFactory(JDBC����Դ)
		SessionFactory sf=con.buildSessionFactory();
		// 3����session(Connection)
		Session session=sf.openSession();
		// 4��������(��֤�̰߳�ȫ��'һԭ����')
		session.beginTransaction();
		// ������ɾ��һ��User����
		User user=new User();
		user.setId(6);
		session.delete(user);
		// 5�ύ����(�����ݿ�Ĳ����������Ե�)
		session.getTransaction().commit();
		// 6�ر�session��sessionFactory
		session.close();
		sf.close();
	}
	
	@Test
	public void testUpdate(){//���Ա༭
		// 1��ȡ�����ļ�(JDBC)
		Configuration con=new Configuration().configure();
		// 2����sessionFactory(JDBC����Դ)
		SessionFactory sf=con.buildSessionFactory();
		// 3����session(Connection)
		Session session=sf.openSession();
		// 4��������(��֤�̰߳�ȫ��'һԭ����')
		session.beginTransaction();
		// �������༭һ��User����
		User user=new User("zs","abc123",20);
		user.setId(2);
		session.update(user);
		// 5�ύ����(�����ݿ�Ĳ����������Ե�)
		session.getTransaction().commit();
		// 6�ر�session��sessionFactory
		session.close();
		sf.close();
	}
	
	@Test
	public void testGet(){//���Բ�ѯ��������---��ע����ѯ���ؿ������񣬲�ѯ������������ݿ�
		// 1��ȡ�����ļ�(JDBC)
		Configuration con=new Configuration().configure();
		// 2����sessionFactory(JDBC����Դ)
		SessionFactory sf=con.buildSessionFactory();
		// 3����session(Connection)
		Session session=sf.openSession();
		// ��������ѯ��������
		User user=(User) session.get(User.class, 1);
		System.out.println(user);
		// 4�ر�session��sessionFactory(���ر�session���������ݿ������������࣬���յ������ݿ�ܾ�����)
		session.close();
		sf.close();
	}
	
	@Test
	public void testList(){//���Բ�ѯ�����б�---��ע����ѯ���ؿ������񣬲�ѯ������������ݿ�
		// 1��ȡ�����ļ�(JDBC)
		Configuration con=new Configuration().configure();
		// 2����sessionFactory(JDBC����Դ)
		SessionFactory sf=con.buildSessionFactory();
		// 3����session(Connection)
		Session session=sf.openSession();
		// ��������ѯ�����б�
		List<User>lists=session.createQuery("from User").list();
		System.out.println(lists);
		// 4�ر�session��sessionFctory(���ر�session���������ݿ������������࣬���յ������ݿ�ܾ�����)
		session.close();
		sf.close();
	}
	
	@Test
	public void testSession(){// ����session������ʽopensession��getCurrentSession
		// 1��ȡ�����ļ�(JDBC)
		Configuration con=new Configuration().configure();
		// 2����sessionFactory(JDBC����Դ������)
		SessionFactory sf=con.buildSessionFactory();
		// 3����session��Connection��������
		//Session session1=sf.openSession();
		//Session session2=sf.openSession();
		Session session1=sf.getCurrentSession();
		Session session2=sf.getCurrentSession();
		System.out.println(session1==session2);
		// 4��ʼ���񣨱�֤�̰߳�ȫ��'һԭ����'��
		session1.beginTransaction();
		User user=new User("zsf","123",25);
		session1.save(user);
		// 5�ύ�������ݿ���������Եģ�
		session1.getTransaction().commit();
		// 6�ر�session��sessionFactory
		//session1.close();
		//session2.close();
		sf.close();
	}
	
	@Test
	public void testGetLoad(){// ����get/load
		// 1��ȡ�����ļ���JDBC��
		Configuration con=new Configuration().configure();
		// 2����sessionFactory��JDBC����Դ������
		SessionFactory sf=con.buildSessionFactory();
		// 3����session��Connection��������
		Session session=sf.openSession();
		// 4�������񣨱�֤�̰߳�ȫ��'һԭ����'��
		session.beginTransaction();
		// ����
		// get��������sql
		//User user=(User) session.get(User.class, 1);
		// load������������sql��ֻ�õ��ö���ŷ���sql������user������󣬸ö���ֻ��һ��idֵ
		User user=(User) session.load(User.class, 1);
		System.out.println(user.getId());
		// 5�ύ����(���ݿ���������Ե�)
		session.getTransaction().commit();
		// 6�ر�session��sessionFactory�����ر�session���������ݿ������������ӣ����յ������ݿ�ܾ����ʣ�
		session.close();
		sf.close();
	}
	
}
