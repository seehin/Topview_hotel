package com.seehin.www.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seehin.www.dao.loginDao;
import com.seehin.www.po.roomPo;

@WebServlet("/adminUpdateRoomServlet")
public class adminUpdateRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		//此处的代防止乱码
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");

		//得到要修改的房间的特征值
		String hotel=(String)session.getAttribute("hotelOfRoom");
		String broomId=(String)session.getAttribute("iroomId");
		
		//此处取得jsp的action传来的数据
		String ihotel=hotel;
		String croomId=request.getParameter("roomId");
		String type=request.getParameter("type");
		String area=request.getParameter("area");
		String bed=request.getParameter("bed");
		String price=request.getParameter("price");
		
		//定义roomPo来封装新的数据
		roomPo room=new roomPo(ihotel, croomId, type, area, bed, price);
		
		//此处直接调用dao层的方法，不通过service了，以更加方便
		int result=loginDao.updateRoomData(hotel, broomId, room);
		if(result==1) {
			request.setAttribute("adminChangeRoomResult", "修改成功！");
			request.getRequestDispatcher("adminChangeServlet").forward(request, response);
		}
		else {
			request.setAttribute("adminChangeRoomResult", "修改失败！");
			request.getRequestDispatcher("adminChangeServlet").forward(request, response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
