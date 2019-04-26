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
	<div id="wordheight">
		<div style="font-size: 50px; color: red">注册失败</div>
	</div>
	<% 
			response.setHeader("Refresh","2;URL=register.jsp");
		%>
</body>
</html>