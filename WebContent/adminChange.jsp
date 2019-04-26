<%@page import="com.seehin.www.po.roomPo"%>
<%@page import="com.seehin.www.po.reservationPo"%>
<%@page import="com.seehin.www.po.userPo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<script type="text/javascript">

function check(){
	var code=$("#code").val();
	if(code=="super-123"){
		alert("进入超级模")
		return true;}
	else {
		alert("系统检测到你不是超级管理员")
		return false;
	}
}
</script>
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
<br/><br/> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
<input type="button" value="返回"  onclick="window.location.href='adminShowServlet'">


	<%--取出调来的数据 --%>
	<%
 	userPo adminData=(userPo)session.getAttribute("adminData");
 	List<userPo> cstmData=(List<userPo>)session.getAttribute("cstmAllData");
 	List<roomPo> rooms=(List<roomPo>)session.getAttribute("rooms");
 	List<reservationPo> reservations=(List<reservationPo>)session.getAttribute("allReservations");
 	
 	session.setAttribute("adminId", adminData.getId());
 %>


	<div><div id="wordheight">
			<%--显示管理员的信息 --%>
			<form action="adminDataSaveServlet" method="post">

				<font color='blue'><font size='50'> 管理中心 </font></font> <br /> <br />
				<br />

<font color='blue'><font size=5>管理员信息</font> </font><br/> 
				<%
 String changeResult=(String)request.getAttribute("adminDataChangeResult");
 if(changeResult!=null)
	 out.print("<font color='red'>"+changeResult+"</font>");
 %>


				<table class="hovertable" align="center">

					<tr>
						<th>证件号</th>
						<th>用户名</th>
						<th>密码</th>
						<th>电话</th>
					</tr>

					<tr>
						<td><input autoComplete="off"
							placeholder=<%=adminData.getId() %>></td>
						<td><input autoComplete="off" type="search"
							value=<%=adminData.getName()%>
							placeholder=<%=adminData.getName()%> name="adminName"></td>
						<td><input autoComplete="off" type="search"
							value=<%=adminData.getPwd()%> placeholder=<%=adminData.getPwd()%>
							name="adminPwd"></td>
						<td><input autoComplete="off" type="search"
							value=<%=adminData.getphone()%>
							placeholder=<%=adminData.getphone()%> name="adminPhone"></td>
					</tr>
				</table>
				<br /> <input type="submit" value="保存">
			</form>
<br/><br/>
<%--显示顾客的信息 --%>
<font color='blue'><font size=5>顾客信息</font> </font><br/>
<%
String result=(String)request.getAttribute("adminAddCstmResult");
String deleteResult=(String)request.getAttribute("adminDeleteResult");
if(result!=null){
	out.print("<font color='red'>"+result+"</font>");
}
if(deleteResult!=null)
	out.print("<font color='red'>"+deleteResult+"</font>");
%>

			<table class="hovertable" align="center">
				<tr>
					<th>证件码</th>
					<th>用户名</th>
					<th>密码</th>
					<th>电话</th>
					<th></th>
				</tr>

				<%
			 for(userPo cstm: cstmData){
			 %>
				<tr>
					<td><%=cstm.getId() %></td>
					<td><%=cstm.getName() %></td>
					<td><%=cstm.getPwd() %></td>
					<td><%=cstm.getphone() %></td>
					<td><a href="adminDeleteCstmServlet?cstmId=<%=cstm.getId()%>">删除用户</a></td>
				</tr>
				<%
			 }
			 %>
			</table>
<br/>

			<input type="button" value="增加用户" onclick="window.location.href='registerInAdmin.jsp'">
<br/><br/><br/>
			<%--显示所有的房间--%>
<font color='blue'><font size=5>房间信息</font> </font>
			<%
String updateRoomResult=(String)request.getAttribute("adminChangeRoomResult");
if(updateRoomResult!=null)
out.print("<font color='red'>"+updateRoomResult+"</font>"); 
%>

<div>
		<div id="wordheight">
			<table class="hovertable" align="center">
				<tr>
					<th>酒店</th>
					<th>房号</th>
					<th>类型</th>
					<th>面积</th>
					<th>床型</th>
					<th>价格</th>
					<th></th>
					<th></th>
				</tr>

				<%for(roomPo room:rooms){%>
				<tr>
					<td><%=room.getHotel() %></td>
					<td><%=room.getId() %></td>
					<td><%=room.getType() %></td>
					<td><%=room.getArea() %></td>
					<td><%=room.getBed() %></td>
					<td><%=room.getPrice() %></td>
					<td><a href="roomDataChangeServlet?hotel=<%=room.getHotel()%>&roomId=<%=room.getId()%>">修改</a></td>
					<td><a href="deleteRoomServlet?hotel=<%=room.getHotel()%>&roomId=<%=room.getId()%>">删除</a></td>

				</tr>
				<%
			}
			%>
			</table><br/>
			<input type="button" value="增加房间"  onclick="window.location.href='adminAddRoom.jsp'" >
</div></div>
<br/><br/><br/>
			<%--显示所有订单 --%>
			<font color='blue'><font size=5>订单信息</font> </font><br/>
			<%
			String deleteReservation=(String)request.getAttribute("adminDeletReservationResult");
			if(deleteReservation!=null){
				out.print("<font color='red'>"+deleteReservation+"</font>");
			}
			%>
			<table class="hovertable" align="center">
				<tr>
					<th>酒店</th>
					<th>顾客</th>
					<th>房间</th>
					<th>是否支付</th>
					<th>预定时间</th>
					<th></th>
				</tr>
				<%
for(reservationPo reservation: reservations){
%>
				<tr>
					<td><%=reservation.getHotel() %></td>
					<td><%=reservation.getCstm() %></td>
					<td><%=reservation.getRoom() %></td>
					<td><%=reservation.getPay() %></td>
					<td><%=reservation.getTime() %></td>
					<td><a
						href="adminDeleteRoomServlet?hotel=<%=reservation.getHotel()%>&cstmId=
						<%=reservation.getCstm()%>&roomId=<%=reservation.getRoom()%>&time=<%=
						reservation.getTime()%>">删除订单</a></td>
				</tr>
				<%
}
%>
			</table>

<br/><br/><br/>

<form action="superAdminServlet" method="post" onsubmit="return check()">

<input autoComplete="off" type="search" placeholder="输入超级模式指令码"  name="superCode" id="code">
<input type="submit" value="进入">

</form>
<br/><br/>
		</div>
	</div>


</body>
</html>