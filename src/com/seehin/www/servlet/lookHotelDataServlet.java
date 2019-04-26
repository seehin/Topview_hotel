package com.seehin.www.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seehin.www.dao.loginDao;
import com.seehin.www.po.newHotelPo;


@WebServlet("/lookHotelDataServlet")
public class lookHotelDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		String name=request.getParameter("name");
		
		//此处定义一个newHotelPo 实体类来承接数据
		newHotelPo hotel=null;
		
		//此处直接调用dao层的方法
		hotel=loginDao.superOne(name);

		if(hotel!=null) {
			session.setAttribute("hotelLooking", hotel);
			request.getRequestDispatcher("hotelDataLook.jsp").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
