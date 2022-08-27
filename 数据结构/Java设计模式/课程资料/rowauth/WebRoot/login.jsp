<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP 'login.jsp' starting page</title>

  </head>
  
  <body>
   	<form action="login.action" method="post" >
   		用户名:<input name="name"/><br/>
   		<input type="submit" value="登录">
   	</form>
  </body>
</html>
