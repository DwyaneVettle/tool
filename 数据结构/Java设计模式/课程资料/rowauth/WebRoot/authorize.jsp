<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>授权页面</title>
  </head>
  	
  <body>
  	<div>
  		当前用户:${ loginEmp.name }&nbsp;
  		<a href="login.jsp">转向登录</a>&nbsp;
  	</div>
  	<h1>选择数据级别</h1>
	<form action="author.action" method="post">
		<table border="1" width="300">
			<thead>
				<tr>
					<th>部门</th>
					<th>选择权限</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${ empAuthList }" var="auth">
					<tr>
						<td>${ auth.deptName }</td>
						<td>
							<input type="checkbox" name="rowDatas"  value="${ auth.deptID }" ${ auth.employeeID == 0 ? "" : "checked" }/>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input name="empID" value="${ empID }" type="hidden"/>
		<input type="submit" value="保存"/>
	</form>  
  
  </body>
</html>
