package com.seehin.www.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.seehin.www.po.loginDataPo;
import com.seehin.www.po.newHotelPo;
import com.seehin.www.po.registerPo;
import com.seehin.www.po.reservationPo;
import com.seehin.www.po.roomPo;
import com.seehin.www.po.userPo;



public class loginDao{

	private static String URL="jdbc:mysql://localhost:3306/hotel?useUnicode=true&characterEncoding=UTF-8";
	private static String sqlpwd="";
	private static String sqlname="root";

// TODO Auto-generated method stub
	
	public static Connection jdbc(){
		Connection connection = null;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		connection=DriverManager.getConnection(URL,sqlname, sqlpwd);
		return connection;
		}catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
//判断是否为已有酒店
public static int isHotel(String hotel) {
	String sql="select count(*) from newhotel where name=?";
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	int result=-1;
	
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1,hotel);
		rs=pstmt.executeQuery();
		
		if(rs.next())
			result=rs.getInt(1);
		if(result>0)         //大于零说明该酒店存在
			return 1;
		else if(result<=0)       //小于等于零说明该酒店不存在
			return 0;
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null) pstmt.close();
			if(rs!=null) rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return 0;
}
//用于登陆时判断 ---普通顾客--- 是否存在
public static int isCstmLogin(loginDataPo login) {
		int count=-1;  //判断用户是否存在
		String userSql="SELECT COUNT(*) FROM customer WHERE id=? and name=? and pwd=?";
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
		pstmt = jdbc().prepareStatement(userSql);
		pstmt.setString(1, login.getId());
		pstmt.setString(2, login.getName());
		pstmt.setString(3, login.getPwd());
		rs=pstmt.executeQuery();
		if(rs.next()) {
		count=rs.getInt(1);
		}
		if(count==0) {
			return 0;          //说明顾客表里面没有此人
		}
		else if(count>0) {
			return 1;          //说明顾客表有此人
		}
		else {
			return -1;         //系统出错
		}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
			}catch(Exception e) {
				throw new RuntimeException(e);
			}
		}
		return 0;
	}

//判断管理员是否存在  ---此处的pstmt过程可以放进util层（以便简化代码）
public static int isAdminLogin(loginDataPo login) {
	int count=-1;
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	String adminSql="select count(*) from administrator where id=? and name=? and pwd=?";
	try {
		pstmt = jdbc().prepareStatement(adminSql);
		pstmt.setString(1, login.getId());
		pstmt.setString(2, login.getName());
		pstmt.setString(3, login.getPwd());
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			count=rs.getInt(1);
		}
		if(count==1) {
			return 1;
		}
		else if(count==0) {
			return 0;
		}
		else {
			return -1;
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
		if(rs!=null) rs.close();
		if(pstmt!=null)  pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return 0;
}


//注册判断用户(管理员或顾客)是否存在
public static int judgeRegister(String ID) {
	String sql="select count(*) from administrator where id=? or (select count(*) from customer where id=?)>0";
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	int count=-1;
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1,ID);
		pstmt.setString(2, ID);
		rs=pstmt.executeQuery();
		if(rs.next())
			count=rs.getInt(1);
		if(count>0) 
			return -1;        //-1说明 用户已经存在，新用户不能注册该账号
		if(count==0) 
			return 1;         //1说明 用户不存在，新用户可以注册该账号
	}catch(SQLException e) {
		e.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null) pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return 0;
}


//判断某个顾客是否已经预定了某个房间
public static int judgeReservationRoom(reservationPo reservation) {
	String sql="select count(*) from reservationroom where hotel=? and customer=? and room=? and time=?";
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	int count=-1;

	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1, reservation.getHotel());
		pstmt.setString(2, reservation.getCstm());
		pstmt.setString(3, reservation.getRoom());
		pstmt.setString(4, reservation.getTime());
		rs=pstmt.executeQuery();
		
		if(rs.next())
			count=rs.getInt(1);
		if(count==1)
			return 1;       //1表示该用户已经预定
		else if(count==0)
			return 0;      //0表示该用户还没预定
		else
			return -1;      //-1系统出错
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null) pstmt.close();
			if(rs!=null) rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return -1;
}

//查看该房间是否被其他顾客预定
public static int judgeOtherReservtioin(String hotel,String roomId,String time) {
	String sql="select count(*) from reservationroom where hotel=? and room=? and time=?";
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	int count=-1;
	
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1, hotel);
		pstmt.setString(2, roomId);
		pstmt.setString(3, time);
		rs=pstmt.executeQuery();
	
		if(rs.next())
			count=rs.getInt(1);
		if(count==1) 
			return 0;           //0代表不可行，说明有其他用户预定了房间
		else if(count==-1)
			return -1;             //系统异常
		else if(count==0)
			return 1;              //1代表可行，说明可预订
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
		if(rs!=null) rs.close();
		if(pstmt!=null)  pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return 0;
}

//数据库的增加(顾客类型)
public static int addCstm(registerPo register) {
	String sql="insert into customer (id,name,pwd,phone) values (?,?,?,?)";
	PreparedStatement pstmt=null;
	int count=-1;
	try {
	pstmt=jdbc().prepareStatement(sql);
	pstmt.setString(1, register.getId());
	pstmt.setString(2, register.getName());
	pstmt.setString(3, register.getApwd());
	pstmt.setString(4, register.getPhone());
	count=pstmt.executeUpdate();
	if(count==-1) {
		return -1;         //系统出错
	}
	else if(count==0) {
		return 0;     //说明执行错误
	}
	else {
		return 1;   //说明add成功
	}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null) pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return 0;
}

//数据库的添加(管理员类型)
public static int addAdmin(registerPo register) {

	String sql="insert into administrator (id,name,pwd,phone) values (?,?,?,?)";
	PreparedStatement pstmt=null;
	int count=-1;
	try {
	pstmt=jdbc().prepareStatement(sql);
	pstmt.setString(1, register.getId());
	pstmt.setString(2, register.getName());
	pstmt.setString(3, register.getApwd());
	pstmt.setString(4, register.getPhone());
	count=pstmt.executeUpdate();
	
	if(count==-1) {
		return -1;
	}
	else if(count==0) {
		return 0;
	}
	else {
		return 1;
	}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null) pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return 0;
} 

//增加房间
public static int addRoom(roomPo room) {
	String sql="insert into room values(?,?,?,?,?,?)";
	PreparedStatement pstmt=null;
	int result=-1;
	
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1,room.getHotel());
		pstmt.setString(2,room.getId());
		pstmt.setString(3,room.getType());
		pstmt.setString(4,room.getArea());
		pstmt.setString(5,room.getBed());
		pstmt.setString(6,room.getPrice());
		result=pstmt.executeUpdate();
		
		if(result==1)
			return 1;
		else if(result==0)
			return 0;
		else
			return -1;
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null) pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return 0;
}

//数据库的删除(顾客类型)
public static int deleteCstm(String id) {
	String sql="delete from customer where id=?";
	PreparedStatement pstmt=null;
	int count=-1;
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1, id);
		count=pstmt.executeUpdate();
		
		if(count==-1)
			return -1;
		else if(count==0)
			return 0;
		else
			return 1;
		}catch(Exception e) {
		throw new RuntimeException("e");	
	}finally {
		try {
			if(pstmt!=null)  pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
} 

//数据库的删除(管理员类型)
public static int deleteAdmin(String id) {
	String sql="delete from administrator where id=?";
	PreparedStatement pstmt=null;
	int count=-1;
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1, id);
		count=pstmt.executeUpdate();
		
		if(count==-1)
			return -1;
		else if(count==0)
			return 0;
		else
			return 1;
		}catch(Exception e) {
		throw new RuntimeException("e");	
	}finally {
		try {
			if(pstmt!=null)  pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

//删除某人的全部订单
public static int deleteAllReservationOfOne(String cstmId) {
	String sql="delete from reservationroom where customer=?";
	PreparedStatement pstmt=null;
	int result=-1;
	
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1, cstmId);
		result=pstmt.executeUpdate();
		
		if(result==1)
			return 1;         //1代表删除成功
		else if(result==0)
			return 0;             //0代表删除失败
		else
			return -1;         //系统出错
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null) pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return -1;
}
//删除具体订单
public static int deleteReservation(reservationPo reservation) {
	String sql="delete from reservationroom where customer=? and room=? and time=? and hotel=?";
	PreparedStatement pstmt=null;
	int count=-1;
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1,reservation.getCstm());
		pstmt.setString(2,reservation.getRoom());
		pstmt.setString(3,reservation.getTime());
		pstmt.setString(4, reservation.getHotel());
		count=pstmt.executeUpdate();
		if(count==0)
			return 0;       //0代表删除订单失败
		else if(count==-1)
			return -1;       //-1代表系统出现错误
		else
			return 1;          //1代表删除成功
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null) pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return 0;
}

//删除房间
public static int deleteRoomData(String hotel,String roomId) {
	String sql="delete from room where hotel=? and id=?";
	PreparedStatement pstmt=null;
	int result=-1;
	
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1,hotel);
		pstmt.setString(2,roomId);
		result=pstmt.executeUpdate();
		
		if(result==1)
			return 1;
		else if(result==0)
			return 0;
		else
			return -1;
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null)  pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return -1;
}


//删除与某个room 有关的订单
public static int deleteAllReservationOfRoom(String hotel, String roomId) {
	String sql="delete from reservationroom where hotel=? and room=?";
	PreparedStatement pstmt=null;
	int result=-1;
	 
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1,hotel);
		pstmt.setString(2, roomId);
		result=pstmt.executeUpdate();
		
		if(result==1)
			return 1;
		else if(result==0)
			return 0;
		else 
			return -1;
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null)  pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return -1;
}
//数据的更改(顾客类型)
public static int updateCstm(userPo user) {
	String sql="update customer set name=?,pwd=?,phone=? where id=?";
	PreparedStatement pstmt=null;
	int count=-1;
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1,user.getName());
		pstmt.setString(2,user.getPwd());
		pstmt.setString(3,user.getphone());
		pstmt.setString(4, user.getId());
		count=pstmt.executeUpdate();
		if(count==-1)
			return -1;    //-1代表系统出现异常
		else if(count==0)
			return 0;         //0代表修改失败
		else
			return 1;           //1代表修改成功
		}catch(Exception e) {
		e.printStackTrace();	
	}finally {
		try {
			if(pstmt!=null)  pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return 0;
}

//更改房间的信息
public static int updateRoomData(String hotel,String roomId,roomPo room) {
	String sql="update room set hotel=? , id=? , type=? , area=? , bed=? , price=? where hotel=? and id=?";
	PreparedStatement pstmt=null;
	int result=-1;
	
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1,room.getHotel());
		pstmt.setString(2,room.getId());
		pstmt.setString(3,room.getType());
		pstmt.setString(4,room.getArea());
		pstmt.setString(5,room.getBed());
		pstmt.setString(6,room.getPrice());
		pstmt.setString(7,hotel);
		pstmt.setString(8,roomId);
		result=pstmt.executeUpdate();
		
		if(result==1)
			return 1;           //说明修改成功
		else if(result==0)
			return 0;             //说明修改失败
		else
			return -1;               //说明系统出现异常
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null) pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return 0;
	
}

//更改管理员的数据
public static int updateAdminData(userPo adminData) {
	String sql="update administrator set name=?, pwd=?, phone=? where id=? ";
	PreparedStatement pstmt=null;
	int result=-1;
	
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1,adminData.getName());
		pstmt.setString(2,adminData.getPwd());
		pstmt.setString(3,adminData.getphone());
		pstmt.setString(4,adminData.getId());
		result=pstmt.executeUpdate();
		
		if(result==1)
			return 1;        //说明修改成功
		else if(result==0)
			return 0;         //说明修改失败
		else
			return -1;           //说明系统操作异常
	}catch(Exception e) {
		throw new RuntimeException("e");	
	}finally {
		try {
			if(pstmt!=null)  pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

//增添酒店
public static int superAdd(newHotelPo hotel) {
	String sql="insert into newhotel values(?,?,?,?)";
	PreparedStatement pstmt=null;
	int result=-1;
	
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1,hotel.getName());
		pstmt.setString(2,hotel.getEvaluate());
		pstmt.setInt(3,hotel.getLike());
		pstmt.setInt(3,hotel.getUnlike());
		result=pstmt.executeUpdate();
		
		if(result==1)
			return 1;          //1代表成功
		else
			return 0;        //0代表失败
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
	try {
		if(pstmt!=null)  pstmt.close();
	}catch(Exception e) {
		e.printStackTrace();
		}
	}
	return -1;
}

//删除酒店
public static int superDelete(String name) {
	String sql="delete from room where name=?";
	PreparedStatement pstmt=null;
	int result=-1;
	
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1,name);
		result=pstmt.executeUpdate();
		
		if(result==1)
			return 1;          //1代表成功
		else
			return 0;        //0代表失败
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
	try {
		if(pstmt!=null)  pstmt.close();
	}catch(Exception e) {
		e.printStackTrace();
		}
	}
	return -1;
}

//调出所有的酒店的信息
public static List<newHotelPo> superShowAllHotel(){
	String sql="select * from newhotel";
	List<newHotelPo> newHotels=new ArrayList<newHotelPo>();
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	try {
		pstmt=jdbc().prepareStatement(sql);
		rs=pstmt.executeQuery();
		
		while(rs.next()) {
			String name=rs.getString("name");
			String evaluate=rs.getString("evaluate");
			int like=rs.getInt("like");
			int unlike=rs.getInt("unlike");
			
			newHotelPo newHotel=new newHotelPo(name, evaluate, like, unlike);
			newHotels.add(newHotel);
		}
		return newHotels;
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null) pstmt.close();
			if(rs!=null) rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return null;
}

//调出某个酒店的信息
public static newHotelPo superOne(String name) {
	String sql="select * from newhotel where name=?";
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1,name);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			String nname=rs.getString("name");
			String evaluate=rs.getString("evaluate");
			int like=rs.getInt("like");
			int unlike=rs.getInt("unlike");
			newHotelPo hotel= new newHotelPo(nname, evaluate, like, unlike);
			return hotel;
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
	try {
		if(pstmt!=null)  pstmt.close();
	}catch(Exception e) {
		e.printStackTrace();
		}
	}
	return null;
}
//修改super的信息
public static int superUpdate(String name ,String evaluate) {
	String sql="update newhotel set evaluate=? where name=?";
	PreparedStatement pstmt=null;
	int result=-1;
	
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1,evaluate);
		pstmt.setString(2, name);
		result=pstmt.executeUpdate();
		
		if(result==1)
			return 1;          //1代表成功
		else
			return 0;        //0代表失败
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
	try {
		if(pstmt!=null)  pstmt.close();
	}catch(Exception e) {
		e.printStackTrace();
		}
	}
	return -1;
}

//删除有关某酒店的所有信息
public static int deleteHotelOfReservation(String name) {
	String sql="delete from reservationroom where hotel=?";
	PreparedStatement pstmt=null;
	int result=-1;
	
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1,name);
		result=pstmt.executeUpdate();
		
		if(result==1)
			return 1;          //1代表成功
		else
			return 0;        //0代表失败
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
	try {
		if(pstmt!=null)  pstmt.close();
	}catch(Exception e) {
		e.printStackTrace();
		}
	}
	return -1;
}

//删除有关某酒店的信息
public static int deleteHotelOfRoom(String name) {
	String sql="delete from room where hotel=?";
	PreparedStatement pstmt=null;
	int result=-1;
	
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1,name);
		result=pstmt.executeUpdate();
		
		if(result==1)
			return 1;          //1代表成功
		else
			return 0;        //0代表失败
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
	try {
		if(pstmt!=null)  pstmt.close();
	}catch(Exception e) {
		e.printStackTrace();
		}
	}
	return -1;
}

//数据库的查询(面向全部数据--顾客)
public List<userPo> selectAll() {
	List<userPo> users=new ArrayList<userPo>();
	String sql="select * from customer";
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try {
		pstmt=jdbc().prepareStatement(sql);
		rs=pstmt.executeQuery(); 
		while(rs.next()) {
			String id=rs.getString("id");
			String name=rs.getString("name");
			String pwd=rs.getString("pwd");
			String phone=rs.getString("phone");
			userPo user=new userPo(id,name,pwd,phone);
			users.add(user);
		}
		return users;
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
		try {
			if(pstmt!=null)  pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return null;
}

//数据库的查询(面向单个--顾客)
public static userPo selectOne(String ID) {
	String sql="select * from customer where id=?";
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString( 1, ID);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			String id=rs.getString("id");
			String name=rs.getString("name");
			String pwd=rs.getString("pwd");
			String phone=rs.getString("phone");
			userPo user=new userPo(id,name,pwd,phone);
			return user;
		}
	}catch(Exception e) {
		throw new RuntimeException("e");
	}finally {
		try {
			if(rs!=null) rs.close();
			if(pstmt!=null) pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return null;
}

//show出某个管理员的信息
public static userPo selectAdmin(String adminId) {
	String sql="select * from administrator where id=?";
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1, adminId);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			String id=rs.getString("id");
			String name=rs.getString("name");
			String pwd=rs.getString("pwd");
			String phone=rs.getString("phone");
			
			//定义实体类来装数据
			userPo adminData=new userPo(id, name, pwd, phone);
			return adminData;
		}
		return null;
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null) pstmt.close();
			if(rs!=null) rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return null;
}
//show出所有订单
public List<reservationPo> selectReservationShow(){
	String sql="select * from reservationroom";
	List<reservationPo> reservations=new ArrayList<reservationPo>();
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try {
		pstmt=jdbc().prepareStatement(sql); 
		rs=pstmt.executeQuery();
		while(rs.next()) {
			String hotel=rs.getString("hotel");
			String customer=rs.getString("customer");
			String room=rs.getString("room");
			String pay=rs.getString("payOrNo");
			String time=rs.getString("time");
			
			reservationPo reservation=new reservationPo(hotel,customer,room,pay,time);
			reservations.add(reservation);
		}
		return reservations;
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null)  pstmt.close();
			if(rs!=null)  rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return null;
}

//查看某个房间是否有订单
public static int selectReservationOfRoom(String hotel,String roomId) {
	String sql="select count(*) from reservationroom where hotel=? and room=?";
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	int count = -1;
	
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1, hotel);
		pstmt.setString(2, roomId);
		rs=pstmt.executeQuery();
		
		if(rs.next())
			count=rs.getInt(1);
		if(count>0)
			return 1;          //说明该房间有订单
		else if(count == 0)
			return 0;        //说明该订单不存在
		else 
			return -1;           //说明系统出错
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null) pstmt.close();
			if(rs!=null) rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return -1;
}

//show出某个房间
public static roomPo roomShowOne(String hotel,String roomId) {
	String sql="select * from room where hotel=? and id=?";
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1,hotel);
		pstmt.setString(2,roomId);
		rs=pstmt.executeQuery();
		
		if(rs.next()) {
			String ihotel=rs.getString("hotel");
			String id=rs.getString("id");
			String type=rs.getString("type");
			String area=rs.getString("area");
			String bed=rs.getString("bed");
			String price=rs.getString("price");
			roomPo room=new roomPo(ihotel, id, type, area, bed, price);
			return room;
		}
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(pstmt!=null)  pstmt.close();
			if(rs!=null)  rs.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	return null;
}
//show出所有的房间
public List<roomPo> roomShowAll() {
	List<roomPo> rooms=new ArrayList<roomPo>();
	String sql="select * from room";
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try {
		pstmt=jdbc().prepareStatement(sql);
		rs=pstmt.executeQuery(); 
		while(rs.next()) {
			String hotel=rs.getString("hotel");
			String id=rs.getString("id");
			String type=rs.getString("type");
			String area=rs.getString("area");
			String bed=rs.getString("bed");
			String price=rs.getString("price");
			roomPo room=new roomPo(hotel,id,type,area,bed,price);
			rooms.add(room);
		}
		return rooms;
		}catch(Exception e) {
		throw new RuntimeException("e");	
	}finally {
		try {
			if(pstmt!=null)  pstmt.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	}

//得到servlet传来的客户的cstmId码和roomId码之后进行存储进数据表reservationroom
public static int reservationRoom(reservationPo reservation) {
	String sql="insert into reservationroom (hotel,customer,room,payOrNo,time) values(?,?,?,?,?)";
	PreparedStatement pstmt=null;
	int count = -1;
	System.out.println("dao reservation room"+reservation.getHotel());
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1, reservation.getHotel());
		pstmt.setString(2, reservation.getCstm());
		pstmt.setString(3, reservation.getRoom());
		pstmt.setString(4, reservation.getPay());
		pstmt.setString(5, reservation.getTime());
		count=pstmt.executeUpdate();
		
		if(count==1)
			return 1;         //表示数据存入成功
		else if(count==-1)
			return -1;         //表示pstmt的执行出现错误
		else
			return 0;      //表示数据库的添加失败
		}catch(Exception e) {
		e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null) pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	return 0;
}

//查询某顾客的预约情况
public static List<reservationPo> cstmrReservationData(String cstmId){
	//定义容器来接收数据
	List<reservationPo> reservations = new ArrayList<reservationPo>();
	
	String sql="select * from reservationroom where customer=?";
	PreparedStatement pstmt=null;
	ResultSet rs=null;
	try {
		pstmt=jdbc().prepareStatement(sql);
		pstmt.setString(1, cstmId);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			String hotel=rs.getString("hotel");
			String cstm=rs.getString("customer");
			String room=rs.getString("room");
			String pay=rs.getString("payOrNo");
			String time=rs.getString("time");
			reservationPo reservation = new reservationPo(hotel,cstm,room,pay,time);
			reservations.add(reservation);
		}
		return reservations;
	}catch(Exception e) {
		e.printStackTrace();
	}
	return null;
}

}

