package com.www.seehin.service;

import com.seehin.www.dao.loginDao;
import com.seehin.www.po.roomPo;

public class adminAddRoomService {

	public static int adminAddRoom(roomPo room) {
		int result=-1;
		result = loginDao.isHotel(room.getHotel());
		if(result<=0)
			return -2;       //-2代表该酒店不存在，因此不能正常添加
		result=loginDao.addRoom(room);
		if(result==1)
			return 1;
		else 
			return 0;
	}
}
