package com.www.seehin.service;

import java.util.ArrayList;
import java.util.List;

import com.seehin.www.dao.loginDao;
import com.seehin.www.po.reservationPo;
import com.seehin.www.po.roomPo;
import com.seehin.www.po.userPo;

public class adminChangeService {

	//用于调出管理员的自身数据
	public static userPo adminData(String adminId) {
		//用Po实体类来接受数据
		userPo adminData=null;
		adminData=loginDao.selectAdmin(adminId);
		return adminData;
	}
	
	//用于调出所有的顾客的数据
	public static List<userPo> adminUseCstmDataShow(){
		//定义容器来接收顾客的所有信息
		List<userPo> cstmAllData=new ArrayList<userPo>();
		
		loginDao login=new loginDao();
		cstmAllData=login.selectAll();
		
		return cstmAllData;
	}
	
	//用于调出所有的房间信息
	public static List<roomPo> adminShowRoom(){
		List<roomPo> rooms=new ArrayList<roomPo>();
		
		loginDao login=new loginDao();
		rooms=login.roomShowAll();
		return rooms;
	}
	
	//用于调出所有的订单数据
	public static List<reservationPo> allReservationsShow(){
		//定义集合来接收订单信息
		List<reservationPo> allReservations=new ArrayList<reservationPo>();
		
		loginDao login=new loginDao();
		allReservations=login.selectReservationShow();
		return allReservations;
		
	}
}




