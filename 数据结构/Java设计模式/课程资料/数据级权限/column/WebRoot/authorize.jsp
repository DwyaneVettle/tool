<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>授权页面</title>
  </head>
  	
  <body>
  	<div>
  		当前用户:${ loginUser.name }&nbsp;
  		<a href="login.jsp">转向登录</a>&nbsp;
  	</div>
  	<h1>选择字段</h1>
	<form action="author.action" method="post">
		<div>
			<c:forEach items="${ authorList }" var="auth">
				<label>
					${ auth.comment }
					<input type="checkbox" name="field" value="${ auth.columName  }"  ${ auth.fieldName eq null ? "" : "checked" }/>
					&nbsp;&nbsp;
				</label>
			</c:forEach>
			<input name="userID" value="${ userID }" type="hidden"/>
		</div>
		<input type="submit" value="保存"/>
	</form>  
  
  </body>
</html>
