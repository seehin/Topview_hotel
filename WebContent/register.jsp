<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="js/jquery-3.2.1.js"></script>
<script type="text/javascript">

function check(form){
	var uid=$("#id").val();
	var name=$("#name").val();
	var apwd=$("#apwd").val();
	var bpwd=$("#bpwd").val();
	var phone=$("#phone").val();
	var select=$("#select").val();
	var code=$("#code").val();
	
	if(uid==""){
		alert("证件码不能为空！")
		return false;
	}
	else if(uid.length!=18){
		alert("证件码要有18位")
		return false;
	}
	else if(name==""){
		alert("用户名不能为空！")
		return false;
	}
	else if(apwd==""||bpwd==""){
		alert("请将密码填写完整！")
		return false;
	}
	else if(apwd!=bpwd){
		alert("两次密码输入不正确！")
		return false;
	}
	else if(select==""){
		alert("请选择注册类型！")
		return false;
	}
	if(select=="admin"){
		if(code==""){
			alert("若注册管理员，请填入正确的指令码！")
			return false;
		}
		else if(code!="admin-123456"){
			alert("注册管理员指令错误！")
			return false;
		}
		
	}
	alert("确认注册")
	return true;	
}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
.btn{
padding:5px;
width:222px;
border-radius:4px;          /*长度*/
background:#6C8CD5;           /*背景颜色*/
border:0;             /*边框变为0*/
cursor:pointer;   /*箭头变手*/
}
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
		<form action="registerServlet" method="post"  onsubmit="return check()">
			<div>
				<div id="wordheight">
					<font color='blue'><font size='200'>注&nbsp;册</font></font><br />
					<br /> <input autoComplete="off" type="search" placeholder="证件码(18位)" size="30" id="id" name="uid"><br /> 
					<input autoComplete="off" type="search" placeholder="用户名" size="30" id="name" name="uname"><br/> 
					<input autoComplete="off" type="password" placeholder="密码(20位以内)" size="30" id="apwd" name="aupwd"><br />
					<input autoComplete="off" type="password" placeholder="再次确认密码" size="30" id="bpwd" name="bupwd"><br /> 
					<input autoComplete="off" type="search" placeholder="电话(11位)" size="30" id="phone" name="uphone"><br />
					<select style="width: 227px; text-align-last: center;" name="utype" id="select">
						<option value="0" hidden>注册类型</option>
						<option value="cstm">普通客户</option>
						<option value="admin">管理员</option>
					</select><br /> <input autoComplete="off" type="search"
						placeholder="申请码(非管理员注册可不填)" size="30" id="code" name="ucode"><br />
					<input type="submit" class="btn" value="注册">
					
				</div>
			</div>
		</form>
		<input type="button" value="返回" onclick="window.location.href='login.jsp'">
	</div>
</body>
</html>