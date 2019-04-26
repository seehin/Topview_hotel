package com.www.seehin.service;

import com.seehin.www.dao.loginDao;
import com.seehin.www.po.registerPo;

public class registerService {
	
public static String register(registerPo register) {
	
	String id=register.getId();
	String name=register.getName();
	String apwd=register.getApwd();
	String bpwd=register.getBpwd();
	String phone=register.getPhone();
	String type=register.getType();
	String code=register.getCode();
	int judge;
	
	//判断注册者有哪个选框没有填写信息
	if(id==null||name==null||apwd==null||bpwd==null||phone==null|type==null)
		return "dataNull";
	
	//判断两次输入的密码是否一致
	if(!apwd.equals(bpwd))  return "pwdDiff";
	
	//判断注册对象是否为授权管理员
	if(type.equals("admin")) {          
		if(code==null||!code.equals("admin-123456")) {
			return "codeError";
		}
	}
	
	//判断该注册对象是否符合条件
	judge=loginDao.judgeRegister(id);
	if(judge==-1||judge==0) return "userExsist";
	
	//对管理员进行添加
	if(type.equals("admin")) {
		loginDao.addAdmin(register);   
		return "ok";
	}
	if(type.equals("cstm")) {
		judge=loginDao.addCstm(register);
		if(judge==1)
		return "ok"; 
		else
			return "error";
	}
	
	return null;
}

}
