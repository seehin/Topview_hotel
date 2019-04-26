<%@page import="com.seehin.www.po.newHotelPo"%>
<%@page import="java.util.List"%>
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
	line-height: 30px;//字行之间的距离
}

.btn{
padding:5px;
width:222px;
border-radius:4px;          /*长度*/
background:#6C8CD5;           /*背景颜色*/
border:0;             /*边框变为0*/
cursor:pointer;   /*箭头变手*/
}
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
<br/>
<input type="button" value="返回" onclick="window.location.href='adminChangeServlet'">
<div><div id="wordheight">
<font size=30><font color='white'>超级模式</font></font>

<%
List<newHotelPo> hotels=(List<newHotelPo>)session.getAttribute("hotels"); 
%>
<%for(newHotelPo hotel:hotels) {%>
<table class="hovertable" align="center" cellspacing="0">
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
<textarea placeholder=<%=hotel.getEvaluate() %> style="resize:none;width:400px;height:100px;"></textarea>
<%} %>

<br/><br/><br/><br/><br/><br/>
<%
String superAdd=(String)request.getAttribute("superAdd");
if(superAdd!=null){
	out.print("<font color='red'>"+superAdd+"</font>");
}
%>
<input type="button" value="增加酒店" onclick="window.location.href='superAddHotel.jsp'">
</div></div>
</body>
</html>