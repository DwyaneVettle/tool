<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>用户页面</title>

  </head>
  	
  <body>
  	<div>
  		当前用户:${ loginUser.name }&nbsp;
  		<a href="login.jsp">转向登录</a>&nbsp;
  	</div>
  	<h1>用户列表</h1>
  	<table width="500" border="1">
  		<thead >
  			<tr>
  				<th>用户名</th>
  				<th>列级授权</th>
  			</tr>
  		</thead>
  		<tbody>
  			<c:forEach items="${ usersList }" var="user">
  				<tr>
  					<td>${ user.name }</td>
  					<td><a href="choiceAuthorPage.action?userID=${ user.id }">授权</a></td>
  				</tr>
  			</c:forEach>
  		</tbody>
  	</table>
  
  
  </body>
</html>
