package com.sram.beans;

/**
 * 
 * User ʵ����	
 *
 */
public class User {
	
	// �������û�id
	private int id;
	
	// �û��� 
	private String username;
	
	// ����
	private String password;
	
	// ����
	private int age;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String password, int age) {
		super();
		this.username = username;
		this.password = password;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
