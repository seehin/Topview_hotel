package com.seehin.www.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seehin.www.po.roomPo;
import com.www.seehin.service.adminAddRoomService;

@WebServlet("/adminAddRoomServlet")
public class adminAddRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		String hotel=request.getParameter("hotel");
		String id=request.getParameter("id");
		String type=request.getParameter("type");
		String area=request.getParameter("area");
		String bed=request.getParameter("bed");
		String price=request.getParameter("price");
		
		//封装成roomPo实体类
		roomPo room=new roomPo(hotel, id, type, area, bed, price);
		
		//传到service
		int result=-1;
		result=adminAddRoomService.adminAddRoom(room);
		if(result==-2) {
			request.setAttribute("adminAddRoomResult","您输入的酒店不存在");
			request.getRequestDispatcher("adminShowServlet").forward(request, response);
		}
		else if(result==0) {
			request.setAttribute("adminAddRoomResult","添加失败！");
			request.getRequestDispatcher("adminShowServlet").forward(request, response);
		}
		else if(result==1) {
			request.setAttribute("adminAddRoomResult","添加成功！");
			request.getRequestDispatcher("adminShowServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doGet(request, response);
	}

}
