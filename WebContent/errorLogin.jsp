
<%@page import="com.sun.glass.ui.Timer"%>
<%@page import="com.sun.org.apache.xalan.internal.xsltc.compiler.sym"%>
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
String result=(String)session.getAttribute("loginResult");
%>
	<div id="wordheight">
		<div style="font-size: 50px; color: red"><%=result %></div>
	</div>
	<% 
			response.setHeader("Refresh","2;URL=login.jsp");
		%>
</body>
</html>