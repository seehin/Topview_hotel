<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<script type="text/javascript">

function check(){
	var uid=$("#uid").val();
	var name=$("#uname").val();
	var pwd=$("#upwd").val();
	if(!uid.length==18){
		alert("要求输入18位数字")
		return false;}
	else if(uname==null){
		alert("用户名不能为空")
		return false;}
	else if(pwd==null){
		 alert("密码不能为空")
		 return false;}
}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body{
background-image:url('timg (6).jpg');
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
	<%--删除掉session里面的所有的数据 --%>
	<%
session.invalidate();
%>
	<form action="loginServlet" method="post" onsubmit="return check()">
		<div>
			<div id="wordheight">
				<font color='blue'><font size='200'>登陆页面</font></font><br />
					<br /> 
				<input type="search" placeholder="证件号" size="30" id="uid" name="uid"><br />
				<input type="search" placeholder="用户名" size="30" id="uname" name="uname"><br />
				<input type="password" placeholder="密码" size="30" id="upwd" name="upwd"><br />
				<input type="submit" class="btn" value="登陆"><br/><br />
				<input type="button" value="注册" onclick="window.location.href='register.jsp'">
			</div>
		</div>
	</form>
</body>
</html>