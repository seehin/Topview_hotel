package com.seehin.www.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seehin.www.po.registerPo;
import com.www.seehin.service.registerService;


@WebServlet("/registerServlet")
public class registerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		String id=request.getParameter("uid");
		String name=request.getParameter("uname");
		String apwd=request.getParameter("aupwd");
		String bpwd=request.getParameter("bupwd");
		String phone=request.getParameter("uphone");
		String type=request.getParameter("utype");
		String code=request.getParameter("ucode");
		String result=null;
		//封装注册信息成实体类
		registerPo register=new registerPo(id,name,apwd,bpwd,phone,type,code);
		//在service层调用方法实现添加
		result = registerService.register(register);
		//此处的返回值有多种，可以在此优化view 的界面
		if(!result.equals("ok")) {
			request.getRequestDispatcher("errorRegister.jsp").forward(request, response);
		}
		else
			request.getRequestDispatcher("successRegister.jsp").forward(request, response);
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
