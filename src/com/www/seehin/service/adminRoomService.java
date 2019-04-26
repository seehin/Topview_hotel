package com.www.seehin.service;

import java.util.ArrayList;
import java.util.List;

import com.seehin.www.dao.loginDao;
import com.seehin.www.po.reservationPo;
import com.seehin.www.po.roomPo;

public class adminRoomService {
	//将room的信息传出去
	public static List<roomPo> roomShow(){
		
		List<roomPo> rooms=new ArrayList<roomPo>();
		loginDao login=new loginDao();
		//此处将room的信息全部返回
		rooms=login.roomShowAll();
		return rooms;
	}
	//将订单的信息传出去
	public static List<reservationPo> reservationShow(){
		List<reservationPo> reservations=new ArrayList<reservationPo>();
		loginDao login=new loginDao();
		//此处将reservation的信息返回
		reservations=login.selectReservationShow();
		return reservations;
	}
	
}
