package com.seehin.www.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seehin.www.po.reservationPo;
import com.www.seehin.service.deleteReservationService;


@WebServlet("/adminDeleteRoomServlet")
public class adminDeleteRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	response.setCharacterEncoding("utf-8");

	
	//取到jsp中超链接传来的数据
	String hotel=request.getParameter("hotel");
	String cstmId=request.getParameter("cstmId");
	String roomId=request.getParameter("roomId");
	String time=request.getParameter("time");
	
	//封装reservation供service使用
	reservationPo reservation=new  reservationPo(hotel,cstmId,roomId,null,time);
	
	int result=-1;
	//执行service
	result=deleteReservationService.deleteReservation(reservation);
		
	if(result==1) {
		request.setAttribute("adminDeletReservationResult", "删除成功！");
		request.getRequestDispatcher("adminChangeServlet").forward(request, response); 
	}
	
	else if(result==0||result==-1||result==-2) {
		request.setAttribute("adminDeletReservationResult", "删除失败！");
		request.getRequestDispatcher("adminChangeServlet").forward(request, response);
	}
		
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
