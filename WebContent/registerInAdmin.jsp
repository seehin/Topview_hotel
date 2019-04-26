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
	margin-top: 200px;
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
	<div>
		<form action="adminAddCstmServlet" method="post">
			<div>
				<div id="wordheight">
					<font color='blue'><font size='200'>注&nbsp;册</font></font><br />
					<br /> <input autoComplete="off" type="search" placeholder="证件码(18位)" size="30" name="uid"><br /> 
					<input autoComplete="off" type="search" placeholder="用户名" size="30" name="uname"><br /> 
					<input autoComplete="off" type="password" placeholder="密码(20位以内)" size="30" name="aupwd"><br />
					<input autoComplete="off" type="password" placeholder="再次确认密码" size="30" name="bupwd"><br /> 
					<input autoComplete="off" type="search" placeholder="电话(11位)" size="30" name="uphone"><br />
					<input type="submit" value="注册">
				</div>
			</div>
		</form>
	</div>
</body>
</html>