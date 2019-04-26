package com.www.seehin.service;

import java.util.ArrayList;
import java.util.List;

import com.seehin.www.dao.loginDao;
import com.seehin.www.po.roomPo;

public class cstmRoomService {

	public static List<roomPo> roomShow(){
		List<roomPo> rooms=new ArrayList<roomPo>();
		loginDao login=new loginDao();
		//取出room数据表里面的数据
		rooms=login.roomShowAll();
		
		return rooms;
	}
}
