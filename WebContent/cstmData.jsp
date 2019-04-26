<%@page import="com.seehin.www.po.reservationPo"%>
<%@page import="java.util.List"%>
<%@page import="com.seehin.www.po.userPo"%>
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
	margin-top: 125px;
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
			<font color='blue' style="font-size: 30px;">用户信息</font>
			<table class="hovertable" align="center">
				<tr>
					<th>身份证</th>
					<th>用户名</th>
					<th>电话</th>
				</tr>

				<%--此处显示出顾客的信息（除了密码） --%>
				<tr>
					<td><%=cstm.getId() %></td>
					<td><%=cstm.getName() %></td>
					<td><%=cstm.getphone() %></td>
				</tr>
			</table>
			<br /> <font color='blue' style="font-size: 30px;">预约信息</font>
			<table class="hovertable" align="center">
				<tr>
					<th>酒店</th>
					<th>顾客</th>
					<th>房间</th>
					<th>是否付费</th>
				</tr>
				<%--此处用循环来将预约信息显示出来--%>
				<% for(reservationPo reservation:reservations)
					{
					%>
				<tr>
					<td><%=reservation.getHotel() %></td>
					<td><%=reservation.getCstm() %></td>
					<td><%=reservation.getRoom() %></td>
					<td><%=reservation.getPay() %></td>
				</tr>
				<%
					}
					%>
			</table>
			<br /> <a href="cstmShowServlet">返回</a> &nbsp;&nbsp;&nbsp;&nbsp; <a
				href="cstmChangeDataServlet">修改</a>
		</div>
	</div>
</body>
</html>