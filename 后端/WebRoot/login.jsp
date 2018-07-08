<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.0.js"></script>
	<script type="text/javascript">
		function change() {
			var d = new Date();
			$("#captcha").attr("src","GetCaptcha?"+ d.getTime());
		}
	
	</script>
  </head>
  
  <body>
  <p>${msg }</p>
    <form action="<c:url value='/LoginServlet'/>" method="post">
    用户名：<input type="text" name="username"/>
    密   码：<input type="text" name="password"/>
    验证码：<input type="text" name="captcha" /><img id="captcha" src="<c:url value='/GetCaptcha' />"/>
    <input type="submit" value="提交"/> 
    </form>
        <button onclick="change()">看不清？</button>
  </body>
</html>
