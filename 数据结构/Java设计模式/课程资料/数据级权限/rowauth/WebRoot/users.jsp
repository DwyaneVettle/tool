<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>员工页面</title>

  </head>
  	
  <body>
  	<div>
  		当前用户:${ loginEmp.name }&nbsp;
  		<a href="login.jsp">转向登录</a>&nbsp;
  	</div>
  	<h1>员工列表</h1>
  	<table width="500" border="1">
  		<thead >
  			<tr>
  				<th>员工名</th>
  				<th>行级授权</th>
  			</tr>
  		</thead>
  		<tbody>
  			<c:forEach items="${ empList }" var="emp">
  				<tr>
  					<td>${ emp.name }</td>
  					<td><a href="authorPage.action?empID=${ emp.id }">授权</a></td>
  				</tr>
  			</c:forEach>
  		</tbody>
  	</table>
  
  
  </body>
</html>
