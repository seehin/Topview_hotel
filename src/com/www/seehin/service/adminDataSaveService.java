package com.www.seehin.service;

import com.seehin.www.dao.loginDao;
import com.seehin.www.po.userPo;

public class adminDataSaveService {

	public static int adminDataSave(userPo adminData) {
		int result=-1;
		
		//update管理员的数据
		result=loginDao.updateAdminData(adminData);
		if(result==1)
			return 1;      //成功
		else if(result==0)
			return 0;       //失败
		else
			return 0;      //系统问题 
 	}
}
