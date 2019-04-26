package com.seehin.www.po;

public class registerPo {

	String id=null;
	String name=null;
	String apwd=null;
	String bpwd=null;
	String phone=null;
	String type=null;
	String code=null;
	
	public registerPo(String id, String name, String apwd, String bpwd, String phone, String type, String code) {
		super();
		this.id = id;
		this.name = name;
		this.apwd = apwd;
		this.bpwd = bpwd;
		this.phone = phone;
		this.type = type;
		this.code = code;
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
	public String getApwd() {
		return apwd;
	}
	public void setApwd(String apwd) {
		this.apwd = apwd;
	}
	public String getBpwd() {
		return bpwd;
	}
	public void setBpwd(String bpwd) {
		this.bpwd = bpwd;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
}