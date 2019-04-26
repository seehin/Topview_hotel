package com.seehin.www.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seehin.www.dao.loginDao;
import com.seehin.www.po.newHotelPo;


@WebServlet("/superAddHotelServlet")
public class superAddHotelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		String name=request.getParameter("name");
		String evaluate=request.getParameter("evaluate");
		
		newHotelPo hotel=new newHotelPo(name, evaluate, 0, 0);
		
		//直接调用dao层方法
		int result=loginDao.superAdd(hotel);
		
		if(result==1) {
			request.setAttribute("superAdd","添加成功");
			request.getRequestDispatcher("superAdminServlet").forward(request, response);
			
		}
		else {
			request.setAttribute("superAdd","添加失败");
			request.getRequestDispatcher("superAdminServlet").forward(request, response);
		}
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
