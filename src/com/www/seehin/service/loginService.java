package com.www.seehin.service;

import com.seehin.www.dao.loginDao;
import com.seehin.www.po.loginDataPo;

public class loginService {


	public static int loginIndentify(loginDataPo login) {
		int result=-1;
		//判断该用户是否为顾客
		result=loginDao.isCstmLogin(login);
		if(result==1)
			return 1;
		else if(result==-1)
			return -1;
		//判断是否为管理员
		result=loginDao.isAdminLogin(login);
		if(result==1)
			return 2;
		else if(result==0)
 			return 0;  
		//除了1 2 0 就剩下  - 1 （执行出错）了
		return -1;
	}
}
