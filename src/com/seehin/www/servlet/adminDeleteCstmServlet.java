package com.seehin.www.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.www.seehin.service.deleteCstmService;

@WebServlet("/adminDeleteCstmServlet")
public class adminDeleteCstmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cstmId=request.getParameter("cstmId");
		
		//执行删除用户程序
		int result=-1;
		//删除客户
		result=deleteCstmService.deleteCstm(cstmId);
		if(result==-1||result==0) {
			request.setAttribute("adminDeleteResult", "删除失败！");
			request.getRequestDispatcher("adminChangeServlet").forward(request, response);
		}
		//删除与该客户有关的所有信息------订单
		result=deleteCstmService.deleteAllOfCstm(cstmId);
		System.out.println( "admindeletecstm----"+result);
		if(result==1) {
			request.setAttribute("adminDeleteResult", "删除成功！");
			request.getRequestDispatcher("adminChangeServlet").forward(request, response);
		}
		else if(result==-1||result==0) {
			request.setAttribute("adminDeleteResult", "删除失败！");
			request.getRequestDispatcher("adminChangeServlet").forward(request, response);
		}
	}   


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
