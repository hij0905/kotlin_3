<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<input type ="text" name="mId" placeholder="아이디 입력">
<input type ="text" name="mPwd" placeholder="비밀번호 입력">
<input type= "button" value = "서버 전송" onClick="moveLoginForm()">
</body>

<br>

${mId }
${mPwd }
<br>
memberInfo :: 
${mId2 }
${mPwd2 }

<script>
	function moveLoginForm() {
		var mId = document.getElementsByName("mId")[0];
		var mPwd = document.getElementsByName("mPwd")[0];
		var form = document.createElement("form");
		﻿form.action = "Login?memberInfo= " + mId.value + " &memberInfo=" + mPwd.value;
		form.method = "post";
		form.appendChild(mId);
		form.appendChild(mPwd);
		document.body.appendChild(form);
		form.submit();
	}
</script>
</html>