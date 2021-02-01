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
<input type ="hidden" name ="serviceCode" value="A">
</body>
<br>
${member.getMId() }
${member.getMPwd() }
${member.getMName()}
${member.getMPhone()}
<script>
	function moveLoginForm() {
		var mId = document.getElementsByName("mId")[0];
		var mPwd = document.getElementsByName("mPwd")[0];
		var serviceCode = document.getElementsByName("serviceCode")[0];
		var form = document.createElement("form");
		form.action = "Login";
		form.method = "post";
		form.appendChild(mId);
		form.appendChild(mPwd);
		form.appendChild(serviceCode);
		document.body.appendChild(form);
		form.submit();
	}
</script>
</html>