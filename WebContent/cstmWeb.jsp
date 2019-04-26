
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
	<%
//session中已经有顾客的id了
String cstmId=(String)session.getAttribute("cstmId"); 
%>

	欢迎<%=cstmId %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type="button" value="退出"
		onclick="window.location.href='login.jsp'">
	<input type="button" value="个人中心"
		onclick="window.location.href='cstmDataServlet'">
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />

	<%--制作表格的模型 --%>
	<table class="hovertable" align="center">
		<tr>
			<th>酒店</th>
			<th>房号</th>
			<th>类型</th>
			<th>面积</th>
			<th>床型(单/双)</th>
			<th>价格</th>
			<th></th>
		</tr>
		<%--通过循环来讲rooms集合中的数据表示出来 --%>
		<%
	List<roomPo> rooms=(List<roomPo>)request.getAttribute("rooms");
	for(roomPo room:rooms){
		%>
		<tr>
			<td><a href="lookHotelDataServlet?name=<%=room.getHotel() %>"><%=room.getHotel() %></a></td>
			<td><%=room.getId() %></td>
			<td><%=room.getType() %></td>
			<td><%=room.getArea() %></td>
			<td><%=room.getBed() %></td>
			<td><%=room.getPrice() %></td>
			<td><a href="toReservationServlet?hotel=<%=room.getHotel()%>&roomId=<%=room.getId()%>">预定</a></td>
		</tr>
		<%
	}
%>
	</table>
</body>
</html>