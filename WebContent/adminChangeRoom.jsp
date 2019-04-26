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
	margin-top: 125px;
}

input::-ms-input-placeholder {
	text-align: center;
}

input::-webkit-input-placeholder {
	text-align: center;
}
</style>
</head>
<body>
	<%
roomPo room=(roomPo)session.getAttribute("room");   //取出room的数据
session.setAttribute("hotelOfRoom", room.getHotel());
session.setAttribute("iroomId", room.getId());
%>

	<form action="adminUpdateRoomServlet" method="post">
		<div>
			<div id="wordheight">

				<input autoComplete="off" placeholder=<%=room.getHotel() %>>
				<input autoComplete="off" type="search"
					placeholder=<%=room.getId()%> value=<%=room.getId()%> name="roomId">
				<input autoComplete="off" type="search"
					placeholder=<%=room.getType()%> value=<%=room.getType()%>
					name="type"> <input autoComplete="off" type="search"
					placeholder=<%=room.getArea()%> value=<%=room.getArea()%>
					name="area"> <input autoComplete="off" type="search"
					placeholder=<%=room.getBed()%> value=<%=room.getBed()%> name="bed">
				<input autoComplete="off" type="search"
					placeholder=<%=room.getPrice()%> value=<%=room.getPrice()%>
					name="price"> <input type="submit" value="保存">
			</div>
		</div>
	</form>
</body>
</html>