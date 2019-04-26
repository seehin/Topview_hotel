package com.www.seehin.service;

import com.seehin.www.dao.loginDao;

public class deleteCstmService {

	public static int deleteCstm(String cstmId) {
		int result=-1;
		
		//调用dao层的方法来删除顾客
		result=loginDao.deleteCstm(cstmId);
		
		if(result==1)
			return 1;            //说明删除成功
		else if(result==0)
			return 0;           //说明删除失败
		else
			return -1;           //说明系统出错
	}
	public static int deleteAllOfCstm(String cstmId) {
		int result=-1;
		result=loginDao.deleteAllReservationOfOne(cstmId);
		System.out.println("deletecstm----"+result);
		if(result==1)
			return 1;
		else 
			return 0;
	}
}
