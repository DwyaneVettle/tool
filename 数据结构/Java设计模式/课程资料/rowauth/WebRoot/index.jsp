<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP 'login.jsp' starting page</title>

  </head>
  	
  <body>
  	<div>
  		当前用户:${ loginEmp.name }&nbsp;
  		<a href="login.jsp">转向登录</a>&nbsp;
  		<c:if test="${ loginEmp.name == 'admin' }"><a href="empListPage.action">员工授权</a></c:if>
  	</div>
  	<h1>部门开销表</h1>
  	<table width="500" border="1">
  		<!-- 打印头部 -->
  		<thead >
  			<tr>
	  			<th>部门</th>
	  			<th>报销金额</th>
	  			<th>报销时间</th>
	  			<th>备注</th>
  			</tr>
  		</thead>
  		<tbody>
  			<c:forEach items="${ cuspList }" var="cpsu">
  				<tr>
  					<td>${ cpsu.deptName }</td>
  					<td>${ cpsu.expenditure }</td>
  					<td>${ cpsu.time }</td>
  					<td>${ cpsu.mark }</td>
  				</tr>
  			</c:forEach>
  		
  		</tbody>
  	</table>
  
  
  </body>
</html>
