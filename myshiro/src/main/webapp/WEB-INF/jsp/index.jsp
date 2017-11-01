<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<h2>Hello World!</h2>
	<shiro:hasRole name="admin">
	<a href="${pageContext.request.contextPath }/sys/home">Home</a>
	<br><br>
	</shiro:hasRole>
	<a href="${pageContext.request.contextPath }/sys/home1">Home1</a>
	<br><br>
	<a href="${pageContext.request.contextPath }/sys/home2">Home2</a>
	<br><br>
	<a href="${pageContext.request.contextPath }/sys/logout">Logout</a>
</body>
</html>
