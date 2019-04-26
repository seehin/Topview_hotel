package com.seehin.www.po;

public class userPo {

	String id;
	String name;
	String pwd;
	String phone;
	
	
	public userPo(String id, String name, String pwd,String phone) {
		super();
		this.id = id;
		this.name = name;
		this.pwd = pwd;
		this.phone=phone;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getphone() {
		return phone;
	}
	public void setphone(String phone) {
		this.phone = phone;
	}
	
}
