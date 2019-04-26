package com.www.seehin.service;

import com.seehin.www.dao.loginDao;
import com.seehin.www.po.reservationPo;

public class reservationRoomService {

public static  int reservationRoom(reservationPo  reservation) {
	int result=-1;
	result=loginDao.judgeReservationRoom(reservation);
	if(result==1)
		return -2;     //该用户已经预定了
	else if(result==-1)
		return -1;      //系统出错
	result=loginDao.judgeOtherReservtioin(reservation.getHotel(),reservation.getRoom(),reservation.getTime());   
	if(result==0)
		return -3;
	else if(result==-1)
		return 0;
	System.out.println(reservation.getHotel());
	result=loginDao.reservationRoom(reservation);
	if(result==1)
		return 1;
	else
		return 0;
}
}
