package com.seehin.www.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seehin.www.dao.loginDao;
import com.seehin.www.po.roomPo;


@WebServlet("/toReservationServlet")
public class toReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hotel=request.getParameter("hotel");
		String roomId=request.getParameter("roomId");
		//调出指定房间的信息
		roomPo room=loginDao.roomShowOne(hotel,roomId);
		//将指定房间的信息存进request中
		request.setAttribute("toReservationOrder", room);
		
		request.getRequestDispatcher("order.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
