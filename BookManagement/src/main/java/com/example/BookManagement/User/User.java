package com.example.BookManagement.User;

public class User {
	private int Id;
	private String UserName;
	private String PassWord;
	private String Email;
	private String PhoneNumber;
	private String Role;
	
	public User() {
		
	}
	
	public User(int id, String userName, String passWord, String email, String phoneNumber, String role) {
		super();
		Id = id;
		UserName = userName;
		PassWord = passWord;
		Email = email;
		PhoneNumber = phoneNumber;
		Role = role;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getPassWord() {
		return PassWord;
	}

	public void setPassWord(String passWord) {
		PassWord = passWord;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		PhoneNumber = phoneNumber;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}
	
}
