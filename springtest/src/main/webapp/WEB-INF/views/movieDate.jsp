<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body onload="init()">

	<div onclick='step3("${selectMovie.getMvCode() }")'>${movieDate }
		이건 테스트 클릭 용</div>
	<img src="resources/img/${selectMovie.getMvImage()}" />
	<br> 이름 : ${selectMovie.getMvName() } 등급 :
	${selectMovie.getMvGrade() } 내용 : ${selectMovie.getMvComments() } 코드 :
	${selectMovie.getMvCode() }

	<div id="showdate0" onclick='step3("0")'></div>
	<div id="showdate1" onclick='step3("1")'></div>
	<div id="showdate2" onclick='step3("2")'></div>
	<div id="showdate3" onclick='step3("3")'></div>
	<div id="showdate4" onclick='step3("4")'></div>
	<div id="showdate5" onclick='step3("5")'></div>
	<div id="showdate6" onclick='step3("6")'></div>

</body>
<script>
	// inner html
	function init() {
		for (var i = 0; i < 7; i++) {
			document.getElementById('showdate' + i).innerHTML = showDate(i);
		}
	}
	function step3(val) {
		var mvCode = "${selectMovie.getMvCode()}";
		var mvDate = showDate(val)
		alert("넘어온 val 값 :: " + val + "  " + mvDate)
		location.href = "/Step3?mvCode="+mvCode +"&mDate="+mvDate;
		// 여기에선 get방식으로 처리할거임. 넘겨야할 데이터는 v_code, date 값
	}
	function showDate(num) {
		let dayStr = "${movieDate}";
		let day = (dayStr.split(" "))[0].split("-");
		let now = new Date();
		now.setFullYear(parseInt(day[0]), parseInt(day[1])-1, parseInt(day[2]));
		now.setDate(now.getDate() + num);
		var date = now.getFullYear() + "-" + now.getMonth() + "-" + now.getDate();
		return date;
	}
</script>


</html>