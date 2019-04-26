package com.seehin.www.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seehin.www.po.userPo;
import com.www.seehin.service.adminDataSaveService;

@WebServlet("/adminDataSaveServlet")
public class adminDataSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String adminid=(String)session.getAttribute("adminId");
		String adminName=request.getParameter("adminName");
		String adminPwd=request.getParameter("adminPwd");
		String adminPhone=request.getParameter("adminPhone");
		
		//封装管理员的信息
		userPo adminData=new userPo(adminid, adminName, adminPwd, adminPhone);
		
		//执行保存管理员的新数据程序
		int result=-1;
		result=adminDataSaveService.adminDataSave(adminData);
		
		if(result==1) {
			request.setAttribute("adminDataChangeResult", "修改成功");
			request.getRequestDispatcher("adminChangeServlet").forward(request, response);
		}
		else if(result==0||result==-1) {
			request.setAttribute("adminDataChangeResult", "修改失败");
			request.getRequestDispatcher("adminChangeServlet").forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
