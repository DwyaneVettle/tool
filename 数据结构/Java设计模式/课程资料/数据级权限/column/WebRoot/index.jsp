<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>My JSP 'login.jsp' starting page</title>

  </head>
  	
  <body>
  	<div>
  		当前用户:${ loginUser.name }&nbsp;
  		<a href="login.jsp">转向登录</a>&nbsp;
  		<c:if test="${ loginUser.name == 'admin' }"><a href="authorizationPage.action">授权设置</a></c:if>
  	</div>
  	<h1>产品列表</h1>
  	<table width="500" border="1">
  		<!-- 打印头部 -->
  		<thead >
  			<tr>
	  			<c:forEach items="${ columnsList }" var="columnsName">
	  				<c:if test="${not empty columnsName}"><th>${ columnsName.comment }</th></c:if>
	  			</c:forEach>
  			</tr>
  		</thead>
  		<tbody>
  			<c:forEach items="${ cupList }" var="cup">
  				<tr>
  					<c:if test="${not empty cup.id}"><td>${ cup.id }</td></c:if>
					<c:if test="${not empty cup.name}"><td>${ cup.name }</td></c:if>
					<c:if test="${not empty cup.version}"><td>${ cup.version }</td></c:if>
					<c:if test="${not empty cup.ordinary_price}"><td>${ cup.ordinary_price }</td></c:if>
					<c:if test="${not empty cup.wholesale_price}"><td>${ cup.wholesale_price }</td></c:if>
					<c:if test="${not empty cup.retail_price}"><td>${ cup.retail_price }</td></c:if>
					<c:if test="${not empty cup.membership_price}"><td>${ cup.membership_price }</td></c:if>
  				</tr>
  			</c:forEach>
  		
  		</tbody>
  	</table>
  
  
  </body>
</html>
