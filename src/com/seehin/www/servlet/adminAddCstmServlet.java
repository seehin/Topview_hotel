package com.seehin.www.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seehin.www.po.registerPo;
import com.www.seehin.service.registerService;


@WebServlet("/adminAddCstmServlet")
public class adminAddCstmServlet extends HttpServlet {
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
		String result=null;
		
		//封装注册信息成实体类
		registerPo register=new registerPo(id,name,apwd,bpwd,phone,"cstm",null);
		//在service层调用方法实现添加
		result = registerService.register(register);
		//此处的返回值有多种，可以在此优化view 的界面
		if(result.equals("ok")) {
			request.setAttribute("adminAddCstmResult", "成功添加新用户！");
			request.getRequestDispatcher("adminChangeServlet").forward(request, response);
		}
		else {
			request.setAttribute("adminAddCstmResult", "添加失败！");
			request.getRequestDispatcher("adminChangeServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
