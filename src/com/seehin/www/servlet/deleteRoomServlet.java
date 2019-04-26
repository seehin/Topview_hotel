package com.seehin.www.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seehin.www.dao.loginDao;


@WebServlet("/deleteRoomServlet")
public class deleteRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");
		
		String hotel = request.getParameter("hotel");
		String roomId = request.getParameter("roomId");
		
		int result=-1;
		
		//删除房间       这里直接调用dao 层的方法
		if(loginDao.selectReservationOfRoom(hotel, roomId)==1) {       //此处先判断订单是否存在
			result=loginDao.deleteAllReservationOfRoom(hotel, roomId);
			if(result==0||result==-1) {
				request.setAttribute("deleteRoomAA", "删除失败！");
				request.getRequestDispatcher("adminChangeServlet").forward(request, response);
			}
		}
			//删除有关房间的订单
		result=loginDao.deleteRoomData(hotel, roomId);
		//判断执行结果
		if(result==1) {
			request.setAttribute("deleteRoomAA", "删除成功！");
			request.getRequestDispatcher("adminChangeServlet").forward(request, response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
