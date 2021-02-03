<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
<head>
   <title>Home</title>
   <link rel="stylesheet" href="/resources/css/movie.css">
</head>
<body>
   <P> Now Time : ${Access}   </P>
   <a href="http://172.30.1.8/LoginForm">로그인폼 이동</a>
	
	
   ${mList }
</body>

<script>
function step1(mvCode) {
	location.href = "/Step1?mvCode="+mvCode;
}
</script>
</html>