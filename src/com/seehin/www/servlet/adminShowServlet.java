package com.seehin.www.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seehin.www.po.reservationPo;
import com.seehin.www.po.roomPo;
import com.www.seehin.service.adminRoomService;


@WebServlet("/adminShowServlet")
public class adminShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		//定义好集合来传输room数据表中的数据
		HttpSession session = request.getSession();
		//拿到登陆者的id码
		String adminId=(String)session.getAttribute("id");
		
		//定义roomPo来接收管理员查看房间的情况
		List<roomPo> rooms=new ArrayList<roomPo>();
		//定义reservationPo来接收订单数据
		List<reservationPo> reservations=new ArrayList<reservationPo>();
		
		//拿到数据，放到rooms
		rooms=adminRoomService.roomShow();
		
		//拿到订单的相关数据
		reservations=adminRoomService.reservationShow();
		
		//将rooms存到rooms域中，将reservations存到reservations域中，给jsp调用
		session.setAttribute("rooms", rooms);
		session.setAttribute("reservations", reservations);
		
		//此处将用户的id 存在session里面有助于转回到adminWeb页面的时候左上角可以正常取值
		session.setAttribute("adminId", adminId);
		
		//此时cstmWeb.jsp可以通过request.getAttribute()来取得
		request.getRequestDispatcher("adminWeb.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
