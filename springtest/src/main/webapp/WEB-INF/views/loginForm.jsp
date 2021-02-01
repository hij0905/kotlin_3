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
	<input type="button" value="서버요청" onClick="moveLoginForm()"/><br>

   ${member.getMId() }<br />
   ${member.getMName()} <br />
   ${member.getMPhone() }<br />
</body>
<script>
function moveLoginForm(){
	var mId = document.getElementsByName("mId")[0];
	var mPw = document.getElementsByName("mPw")[0];
	//var date = document.getElementsByName("date")[0];
	
	var form = document.createElement("form");
	form.action= "Login?serviceCode=A";
	form.method = "POST";
	
	form.appendChild(mId);
	form.appendChild(mPw);
	//form.appendChild(date);
	document.body.appendChild(form);
	
	form.submit();
}
</script>
</html>