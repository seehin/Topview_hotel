package com.seehin.www.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seehin.www.dao.loginDao;
import com.seehin.www.po.roomPo;

@WebServlet("/roomDataChangeServlet")
public class roomDataChangeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//防止乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		HttpSession session = request.getSession();
		//取到jsp中传来的数据
		String hotel=request.getParameter("hotel");
		String roomId=request.getParameter("roomId");
		
		//定义一个roomPo的实体类（这里不用经过service层，直接调用dao层的方法）
		roomPo room = loginDao.roomShowOne(hotel, roomId);
		
		//存进session里面以便jsp的调用
		session.setAttribute("OneRoom", room);
		
		request.getRequestDispatcher("adminChangeRoom.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
