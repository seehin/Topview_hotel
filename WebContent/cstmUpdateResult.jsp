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
	<div>
		<div id="wordheight">
			<% 
String result=(String)session.getAttribute("cstmUpdateResult");
%>
			<font size='200'><font color='red'><%=result%></font></font>
			<% 
response.setHeader("Refresh","2;URL=cstmChangeDataServlet");

%>
		</div>
	</div>
</body>
</html>