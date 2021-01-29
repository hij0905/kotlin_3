<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>LogInForm</title>
</head>
<body>
	<input type="text" name="mId" placeholder="아이디를 입력해주세요" />
	<input type="password" name="mPw" placeholder="비밀번호를 입력해주세요" />
	<input type="button" value="서버요청" onClick="moveLoginForm()"/>

	${mId }<br />
	${mPw }
<%-- 	${memberId }<br /> --%>
<%-- 	${memberPw }<br /> --%>
</body>
<script>
function moveLoginForm(){
	var mId = document.getElementsByName("mId")[0];
	var mPw = document.getElementsByName("mPw")[0];
	
	var form = document.createElement("form");
	form.action= "Login?memberInfo="+mId.value+"&memberInfo="+mPw.value;
	form.method = "POST";
	
	form.appendChild(mId);
	form.appendChild(mPw);
	document.body.appendChild(form);
	
	form.submit();
}
</script>
</html>