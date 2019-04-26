<%@page import="com.seehin.www.po.newHotelPo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
body{
background-image:url('untitled.png');
background-repeat: no-repeat;
background-size:1555px 770px;
}
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
newHotelPo hotel=(newHotelPo)session.getAttribute("hotelLooking");
%>

<font color='blue'><font size=30><%=hotel.getName() %></font></font>

<div><div id="wordheight">
<table class="hovertable" align="center">
<tr>
<th>酒店</th>
<th>LIKE</th>
<th>UNLIKE</th>
</tr>

<tr>
<td><%=hotel.getName() %></td>
<td><%=hotel.getLike() %></td>
<td><%=hotel.getUnlike() %></td>
</tr>
</table>
<br/>
<textarea placeholder=<%=hotel.getEvaluate() %> style="resize:none;width:300px;height:100px;"></textarea>
<br/>
<input type="button" value="返回" onclick="window.location.href='cstmShowServlet'">
</div></div>


</body>
</html>