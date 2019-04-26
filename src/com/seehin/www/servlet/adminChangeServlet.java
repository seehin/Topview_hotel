package com.seehin.www.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seehin.www.po.reservationPo;
import com.seehin.www.po.roomPo;
import com.seehin.www.po.userPo;
import com.www.seehin.service.adminChangeService;

@WebServlet("/adminChangeServlet")
public class adminChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//取得管理员的Id
		String adminId=(String)session.getAttribute("adminId");
		
		//---修改自身的信息(此处显示管理员的信息)
		userPo adminData=adminChangeService.adminData(adminId);
		
		//---调出所有的顾客的数据(先在此处显示所有的顾客)
		List<userPo> cstmAllData=adminChangeService.adminUseCstmDataShow();
		//---修改 room 的信息
		List<roomPo> rooms=adminChangeService.adminShowRoom();
		//---修改订单(在此处显示所有的订单)
		List<reservationPo> allReservatiions=adminChangeService.allReservationsShow();
	
		//查看客户的请求()
		
		//将得到的数据保存到session里面  
		session.setAttribute("adminData", adminData);
		session.setAttribute("cstmAllData", cstmAllData);
		session.setAttribute("rooms", rooms);
		session.setAttribute("allReservations", allReservatiions);
		
		//实现页面的跳转
		request.getRequestDispatcher("adminChange.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
