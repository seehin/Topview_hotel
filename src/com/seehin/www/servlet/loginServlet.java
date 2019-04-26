package com.seehin.www.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seehin.www.po.loginDataPo;
import com.www.seehin.service.loginService;


@WebServlet("/loginServlet")
public class loginServlet extends HttpServlet {
       
    
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		//接收登陆端的数据
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String id= request.getParameter("uid");
		String name= request.getParameter("uname");
		String pwd= request.getParameter("upwd");
		
		//封装登陆实体类
		loginDataPo login=new loginDataPo(id,name,pwd);
		
		//判断用户类型---是顾客还是管理员
		//result 用于接受结果  1---顾客     2---管理员     0---未注册    -1---出错
		int result =loginService.loginIndentify(login);
		HttpSession session = request.getSession();
		if(result==0||result==-1) {           //用户未注册
			session.setAttribute("loginResult", "登陆失败");
			request.getRequestDispatcher("errorLogin.jsp").forward(request, response);
		}
		else {
			//将值存进id域，然后传给相应的servlet
			session.setAttribute("id", id);
			if(result==1)   request.getRequestDispatcher("cstmShowServlet").forward(request, response);    //顾客
			if(result==2)   request.getRequestDispatcher("adminShowServlet").forward(request, response);    //管理员
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
