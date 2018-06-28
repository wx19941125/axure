package com.sram.beans;

/**
 * 
 * User 实体类	
 *
 */
public class User {
	
	// 主键，用户id
	private int id;
	
	// 用户名 
	private String username;
	
	// 密码
	private String password;
	
	// 年龄
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
