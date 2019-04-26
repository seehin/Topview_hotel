package com.seehin.www.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.seehin.www.po.roomPo;
import com.www.seehin.service.cstmRoomService;


@WebServlet("/cstmShowServlet")
public class cstmShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//定义好集合来传输room数据表中的数据
		HttpSession session = request.getSession();
		String cstmId=(String)session.getAttribute("id");
		//
		List<roomPo> rooms=new ArrayList<roomPo>();
		//拿到数据，放到rooms
		rooms=cstmRoomService.roomShow();
		//将rooms存到rooms域中，给jsp调用
		request.setAttribute("rooms", rooms);
		session.setAttribute("cstmId", cstmId);
		//此时cstmWeb.jsp可以通过request.getAttribute()来取得
		request.getRequestDispatcher("cstmWeb.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
