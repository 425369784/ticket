<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%     
	String path = request.getContextPath();     
	String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path+"/" ;
%>
<!DOCTYPE>
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户登录</title>
<link href="css/login.css" rel="stylesheet" type="text/css" >
</head>
<body>
	<form action="user/userServlet?action=login" method="post">
		<h1>用户登录</h1>
		<input type="text" name="username" placeholder="请输入账号" required="required" pattern="^[a-zA-Z0-9_]{6,16}$" title="只能是6-11位字母、数字、_" />
		<input type="password" name="password" placeholder="请输入密码"  required="required" pattern="^[a-zA-Z0-9_]{6,16}$" title="只能是6-16位字母、数字、_" />
		${requestScope.error }
		<input type="submit" value="登录" />
	</form>
</body>
</html>