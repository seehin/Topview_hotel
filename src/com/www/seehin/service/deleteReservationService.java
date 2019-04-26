package com.www.seehin.service;

import com.seehin.www.dao.loginDao;
import com.seehin.www.po.reservationPo;

public class deleteReservationService {

	public static int deleteReservation(reservationPo reservation) {
		int result=-1;
		
		//查看该订单是否存在
		result=loginDao.judgeReservationRoom(reservation);
		if(result==0||result==-1)
			return -1;
		
		//执行删除订单
		result=loginDao.deleteReservation(reservation);
		if(result==1)    //删除成功
			return 1;
		else if(result==0||result==-1)       //删除失败
			return 0;
		
		return -2;
	}
}
