<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1>${welcome} </h1>
	<P>  The time on the server is ${serverTime}. </P>
	<a href="http://172.30.1.8/LoginForm">로그인폼 이동</a>
</body>
</html>
