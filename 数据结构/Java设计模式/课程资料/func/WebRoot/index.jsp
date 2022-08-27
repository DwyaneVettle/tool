<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
<title>My JSP 'login.jsp' starting page</title>
</head>

<body>
	登录用户:${ loginUser.name }
	<ul>
		<c:forEach items="${ funcList }" var="func">
			<li><a href="/func${ func.url }">${ func.pfName }</a></li>
		</c:forEach>
	</ul>



</body>
</html>
