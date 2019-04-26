package com.seehin.www.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seehin.www.dao.loginDao;
import com.seehin.www.po.reservationPo;


@WebServlet("/cstmRoomNONeedUseServlet")
public class cstmRoomNONeedUseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");


		//定义判断某用户是否预约了某个房间
		int result=-1;
		//接收cstmChangeData传来的cstmId和roomId
		String hotel=request.getParameter("hotel");
		String cstmId=request.getParameter("cstmId");
		String roomId=request.getParameter("roomId");
		String time=request.getParameter("time");
		//封装在reservation实体类里面
		reservationPo reservation=new reservationPo(hotel,cstmId,roomId,null,time);
		
		//判断是否存在该订单（一般都是有这个订单的，否则顾客不会看到自己的订单然后删除，但这里可以防止之其他程序的出错）
		result=loginDao.judgeReservationRoom(reservation);
		if(result==0||result==-1) {
			request.setAttribute("deleteReservation", "noReservation");
			request.getRequestDispatcher("cstmChangeDataServlet").forward(request, response);
		}
		//该步执行取消订单操作
		result=loginDao.deleteReservation(reservation);
		if(result==1) {                   //删除成功
			request.setAttribute("deleteReservation", "ok");
			request.getRequestDispatcher("cstmChangeDataServlet").forward(request, response);
		}
		else if(result==-1||result==0) {           //删除失败
			request.setAttribute("deleteReservation", "deleteError");
			request.getRequestDispatcher("cstmChangeDataServlet").forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
