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

import com.seehin.www.dao.loginDao;
import com.seehin.www.po.reservationPo;
import com.seehin.www.po.userPo;


@WebServlet("/cstmDataServlet")
public class cstmDataServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		response.setCharacterEncoding("utf-8");


		String cstmId=(String)session.getAttribute("cstmId");
		//定义一个容器来装id对应的客户信息
		userPo cstm;
		//定义容器
		List<reservationPo> reservations=new ArrayList<reservationPo>();
		//取出id对应的顾客的信息
		cstm=loginDao.selectOne(cstmId);
		//取出顾客的预约的情况
		reservations=loginDao.cstmrReservationData(cstmId);
		session.setAttribute("cstmData", cstm);
		session.setAttribute("reservations",reservations);
		//跳转到顾客详情页面
		request.getRequestDispatcher("cstmData.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
