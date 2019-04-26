<%@page import="com.seehin.www.po.reservationPo"%>
<%@page import="com.seehin.www.po.roomPo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
	background-color: #c3dde0;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

table.hovertable tr {
	background-color: #d4e3e5;
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
	<div>
		<div id="wordheight">

			<%
//session中已经有顾客的id了
String adminId=(String)session.getAttribute("adminId"); 
%>

		欢迎<%=adminId %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
		<input type="button" value="退出" onclick="window.location.href='login.jsp'">
		<input type="button" value="管理中心" onclick="window.location.href='adminChangeServlet'"> <br />
		<br />

			<%
String adminAddRoomResult=(String)request.getAttribute("adminAddRoomResult");
if(adminAddRoomResult!=null)
	out.print("<font color='red'>"+adminAddRoomResult+"</font>");
%>


			<table class="hovertable" align="center">
				<tr>
					<th>房号</th>
					<th>类型</th>
					<th>面积</th>
					<th>床型(单/双)</th>
					<th>价格</th>
				</tr>
				<%
	List<roomPo> rooms=(List<roomPo>)session.getAttribute("rooms");
	
	for(roomPo room:rooms){
%>
				<tr>
					<td><%=room.getId() %></td>
					<td><%=room.getType() %></td>
					<td><%=room.getArea() %></td>
					<td><%=room.getBed() %></td>
					<td><%=room.getPrice() %></td>
				</tr>
				<%
	} 
%>
			</table>
	<div align="center">
		</div>
	</div>
	<br />
	<br />
	<br />
	<div>
		<div id="wordheight">
			<table class="hovertable" align="center">

				<tr>
					<th>酒店</th>
					<th>客户</th>
					<th>房间</th>
					<th>是否支付</th>
				</tr>
				<%
	List<reservationPo> reservations =(List<reservationPo>)session.getAttribute("reservations");
	for(reservationPo reservation : reservations){
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
		</div>
	</div>
</body>
</html>