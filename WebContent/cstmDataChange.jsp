<%@page import="java.util.List"%>
<%@page import="com.seehin.www.po.userPo"%>
<%@page import="com.seehin.www.po.reservationPo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
div {
	text-align: center;
	line-height: 30px;
	//
	字行之间的距离
}

#wordheight {
	margin-top: 100px;
}

input::-ms-input-placeholder {
	text-align: center;
}

input::-webkit-input-placeholder {
	text-align: center;
}
</style>
<style type="text/css">
table.hovertable {
	font-family: verdana, arial, sans-serif;
	font-size: 20px;
	color: #333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}

table.hovertable th {
	background-color: #3AA6D0;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

table.hovertable tr {
	background-color: #62B1D0;
}

table.hovertable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
</style>
</head>
<body>
	<%
//在session中接受到id对应的顾客的数据和顾客的订单数据
	userPo cstm=(userPo)session.getAttribute("cstmData");
	List<reservationPo> reservations=(List<reservationPo>)session.getAttribute("reservations");
%>
	<div>
		<div id="wordheight">

			<%--此处将输入的修改数据传到 ‘action’ 所指的路径中 --%>
			<form action="cstmDataSaveServlet" method="post">

				<font color='blue' style="font-size: 30px;">用户信息</font>
				<table class="hovertable" align="center">
					<tr>
						<th>证件号</th>
						<th>用户名</th>
						<th>电话</th>
						<th>密码</th>
					</tr>
					<%--此处显示出顾客的信息（除了密码） --%>
					<tr>
						<td><input autoComplete="off" placeholder=<%=cstm.getId()%>
							name="cstmChangeId"></td>
						<td><input autoComplete="off" type="search"
							value=<%=cstm.getName() %> name="cstmChangeName"></td>
						<td><input autoComplete="off" type="search"
							value=<%=cstm.getphone() %> name="cstmChangePhone"></td>
						<td><input autoComplete="off" type="search"
							placeholder=<%=cstm.getPwd() %> name="cstmChangePwd"></td>
					</tr>
				</table>
				<br />
				<br /> <input type="submit" value="保存">
			</form>
			<br /> <font color='blue' style="font-size: 30px;">预约信息</font>
			<table class="hovertable" align="center">
				<tr>
					<th>酒店</th>
					<th>顾客</th>
					<th>房间</th>
					<th>是否付费</th>
					<th>时间</th>
					<th></th>
				</tr>
				<br />
				<%--此处用循环来将预约信息显示出来--%>
				<%
					String deleteResult=(String)request.getAttribute("deleteReservation");
				if(deleteResult!=null){	
				if(deleteResult.equals("ok"))
						out.print("<font color='red'>订单取消成功！</font>");	
					else if(deleteResult.equals("noReservation"))
						out.print("<font color='red'>抱歉，订单不存在！</font>");
					else if(deleteResult.equals("deleteError"))
						out.print("<font color='red'>抱歉，取消操作失败！</font>");
				session.setAttribute("deleteReservation", null);
				}
				%>
				<% for(reservationPo reservation:reservations)
					{
					%>
				<tr>
					<td><%=reservation.getHotel() %></td>
					<td><%=reservation.getCstm() %></td>
					<td><%=reservation.getRoom() %></td>
					<td><%=reservation.getPay() %></td>
					<td><%=reservation.getTime() %></td>
					<td><a
						href="cstmRoomNONeedUseServlet?hotel=<%=reservation.getHotel() %>&cstmId=<%=reservation.getCstm()%>
						&roomId=<%=reservation.getRoom()%>&time=<%=reservation.getTime() %>">
						退订</a></td>
				</tr>
				<%
					}
					%>
			</table>
			<br /> <a href="cstmDataServlet">返回</a>
			
		</div>
	</div>
</body>
</html>