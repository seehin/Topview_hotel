package com.www.seehin.service;



import com.seehin.www.dao.loginDao;
import com.seehin.www.po.userPo;

public class cstmDataUpdateService {

	public static String cstmDataUpdate(userPo cstm) {
		
		//定义result来接受dao层的（修改）执行结果
		int result=-1;
		//此处调用dao层的update方法
		result=loginDao.updateCstm(cstm);
		//判断运行结果
		if(result==-1||result==0)     //此处为修改失败
			return "updateError";
		else
			return "ok";
	
	}
}
