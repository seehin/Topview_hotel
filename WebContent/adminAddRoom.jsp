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
	<form action="adminAddRoomServlet" method="post">
		<div>
			<div id="wordheight">
				<font color='blue'><font size='200'>增加新房间</font></font><br /><br />
				<input autoComplete="off" type="search" placeholder="酒店名" size='30' name="hotel"> <br />
				<input autoComplete="off" type="search" placeholder="房号" size='30' name="id"><br />
				<input autoComplete="off" type="search" placeholder="房类型" size='30' name="type"> <br />
				<input autoComplete="off" type="search" placeholder="面积" size='30' name="area"><br />
				<input autoComplete="off" type="search" placeholder="床型" size='30' name="bed"><br />
				<input autoComplete="off" type="search" placeholder="价格" size='30' name="price"> <br />
				<input type="button" value="返回" onclick="window.location.href='adminShowServlet'">
				<input type="submit" value="保存">
			</div>
		</div>
	</form>
</body>
</html>