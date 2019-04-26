package com.seehin.www.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seehin.www.po.reservationPo;
import com.www.seehin.service.reservationRoomService;

@WebServlet("/reservationRoomServlet")
public class reservationRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//接收cstmWeb中传来的客户的id码
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");


		HttpSession session = request.getSession();
		String cstmId=(String) session.getAttribute("cstmId");
		//接收cstmWeb传来的房号
		String hotel=request.getParameter("hotel");
		String roomId=request.getParameter("roomId");
		String time=request.getParameter("time");
		//定义客户预定的房间的支付情况
		String pay="否";
		
		//封装预定实用类Po
		reservationPo reservation=new reservationPo(hotel,cstmId,roomId,pay,time);
		//此处调用dao层的方法将cstmId和roomId存进数据表中(同时预约成功后payOrNo的默认值是‘否’)
		int result=reservationRoomService.reservationRoom(reservation);
		//value作为request.setAttribute的域返回给jsp 
		String value1="你已经预定该房间了";
		String value2="预定成功";
		String value3="抱歉！该房间已被预定";
		System.out.println("reservationservlet"+result);
		//预定数据存进了数据库之后要对程序的执行情况判断)
		if(result==-2)
			request.setAttribute("value", value1);
		else if(result==1)
			request.setAttribute("value", value2);
		else
			request.setAttribute("value", value3);
		request.getRequestDispatcher("reservationResult.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
