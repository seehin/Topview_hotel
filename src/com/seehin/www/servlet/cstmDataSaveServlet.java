package com.seehin.www.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seehin.www.po.userPo;
import com.www.seehin.service.cstmDataUpdateService;


@WebServlet("/cstmDataSaveServlet")
public class cstmDataSaveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		HttpSession session = request.getSession();
		//定义string类型接受程序执行结果
		String updateResult=null;
		//将jsp中的输入值放在此处
		String cstmId = (String)session.getAttribute("cstmId");
		String cstmName = request.getParameter("cstmChangeName");
		String cstmPhone = request.getParameter("cstmChangePhone");
		String cstmPwd = request.getParameter("cstmChangePwd");
		
		//用userPo实体类封装
		userPo cstm=new userPo(cstmId,cstmName,cstmPwd,cstmPhone);
		//此处调用service层的修改功能
		updateResult=cstmDataUpdateService.cstmDataUpdate(cstm);
		if(updateResult.equals("ok")) {
			session.setAttribute("cstmUpdateResult", "用户信息修改成功！");
			request.getRequestDispatcher("cstmUpdateResult.jsp").forward(request, response);
		}
		else {
			session.setAttribute("cstmUpdateResult", "用户信息修改失败");
			request.getRequestDispatcher("cstmUpdateResult.jsp").forward(request, response);
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
