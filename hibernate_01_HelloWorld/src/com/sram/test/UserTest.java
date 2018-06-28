package com.sram.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.sram.beans.User;

/**
 * 
 * ��ע�������hibernate ��������
 * 
 */
public class UserTest {
	
	/**
	 * hibernate��������
	 */
	public static void main(String[] args) {
		// 1��ȡ�����ļ�(JDBC)
		Configuration con=new Configuration().configure();
		// 2����sessionFactory��JDBC����Դ��
		SessionFactory sf=con.buildSessionFactory();
		// 3����session(Connection)
		Session session=sf.openSession(); 
		// 4��������(��֤�̰߳�ȫ��'һԭ����')
		session.beginTransaction();
		// ����һ��User����
		User user=new User("wx","abc123",25);
		session.save(user);
		// 5�ύ����
		session.getTransaction().commit();
		// 6�ر�session��session����(���ر�session���������ݿ������������࣬���յ������ݿ�ܾ�����)
		session.close();
		sf.close();
	}
	
}
