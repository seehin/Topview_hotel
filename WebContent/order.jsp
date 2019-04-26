<%@page import="com.seehin.www.po.roomPo"%>
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
<br/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<input type="button" value="返回" onclick="window.location.href='cstmShowServlet'">
<%
roomPo room=(roomPo)request.getAttribute("toReservationOrder");
%>
<div>
		<div id="wordheight">
		<font color='blue'><font size=7>订&nbsp;单</font></font>
		<table class="hovertable" align="center">
		<tr>
			<th>酒店</th>
			<th>房号</th>
			<th>类型</th>
			<th>面积</th>
			<th>床型(单/双)</th>
			<th>价格</th>
		</tr>
		
		<tr>
			<td><%=room.getHotel() %></td>
			<td><%=room.getId() %></td>
			<td><%=room.getType() %></td>
			<td><%=room.getArea() %></td>
			<td><%=room.getBed() %></td>
			<td><%=room.getPrice() %></td>
		</tr>
		</table>
		<br/><br/><br/>
		
		<%out.print("<font color='red'>注意：预约时间最长为2天</font>"); %>
		
		<form action="reservationRoomServlet" method="post">
		
		<input placeholder=<%=room.getHotel()%> value=<%=room.getHotel()%> name="hotel">
		<input placeholder=<%=room.getId() %> value=<%=room.getId()%> name="roomId">
		<br/>
		<select name="time" style="width:180px;">
		<option hidden>预约时间</option>
		<option value="今-早上">今-早上</option>
		<option value="今-晚上">今-晚上</option>
		<option value="明-早上">明-早上</option>
		<option value="明-晚上">明-晚上</option>
		</select><br/>
		<input type="submit" value="提交订单">
		</form>
		
		</div>
</div>
</body>
</html>