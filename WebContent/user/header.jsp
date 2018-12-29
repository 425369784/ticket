<%@page import="pojo.User"%>
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
<title>Insert title here</title>
<link href="css/header.css" rel="stylesheet" type="text/css" >
</head>
<body>
<nav class="navbar navbar-default" role="navigation">
	<div class="container-fluid"> 
	<div class="navbar-header">
		<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#example-navbar-collapse">
			<span class="sr-only">切换导航</span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		</button>
		<a class="navbar-brand">网上景点门票预订系统-用户</a>
	</div>
	<div class="collapse navbar-collapse" id="example-navbar-collapse">
		<ul class="nav navbar-nav  navbar-right">
			<li><a href="user/ticket.jsp">门票预订</a></li>
			<li><a href="user/uticket.jsp">我的门票</a></li>
			<li class="dropdown">
				<a href="#" class="dropdown-toggle"
						data-toggle="dropdown">                   
						${sessionScope.user.account } <b class="caret"></b>
	                </a>
				 <ul class="dropdown-menu">
	                    <li><a href="user/userServlet?action=logout">退出</a></li>
	                </ul>
			</li>
		</ul>
	</div>
	</div>
</nav>
	
	
</body>
</html>