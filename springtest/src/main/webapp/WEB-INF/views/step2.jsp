<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Home</title>
<!-- <link href="/resources/css/kotlin.css" rel="stylesheet"/> -->
</head>
<body onLoad="init()">
	<a href="http://192.168.0.252/LoginForm">로그인폼 이동</a>
	<br />
	<P>Now Time : ${Access}</P>
	<section id="movieZone" style="display: flex;">
		<div id="movieInfo"></div>
		<div id="selectionDate">selectionDate</div>
		<div id="selectionTime">selectionTime</div>
		<div id="selectionGrade">selectionGrade</div>
		<div id="selectionScreen">selectionScreen</div>
	</section>
</body>
<script>

let screeningData;

function init(){
	/* Convert Date */
	let dateList = document.getElementById("selectionDate");
	let dayStr = "${Access}";
	
	
	let day = dayStr.split("-");
	//let now = new Date();
	//now.setFullYear(parseInt(day[0]), parseInt(day[1])-1, parseInt(day[2]));
	
	let now = new Date(parseInt(day[0]), parseInt(day[1])-1, parseInt(day[2]));
	
	for(i=0; i< 7; i++){
		now.setDate(now.getDate() + ((i == 0) ? 0:1));
		let dateDiv = document.createElement('Div');
		let month = ((now.getMonth()+1) < 10)? "0"+ (now.getMonth()+1) : (now.getMonth()+1);
		let date = (now.getDate() < 10)? "0" + now.getDate() : now.getDate();
		dateDiv.textContent = now.getFullYear() + "-" + month + "-" + date;
		dateDiv.style.cursor = "pointer";
		dateDiv.addEventListener('click', function(){divClick(movie[0].mvCode, this.textContent);});
		dateList.appendChild(dateDiv);
	}
	
	let movieInfo = document.getElementById("movieInfo");
	/* Append movieInfo Div */
	let movie = JSON.parse('${movieData}');
	
	let mvImage = document.createElement('Div');
	mvImage.style.width = "150px";
	mvImage.style.height = "300px";
	mvImage.style.margin = "0px 10px 20px 0px";
	mvImage.style.backgroundImage = "url(/resources/img/" + movie[0].mvImage + ")";
	mvImage.style.backgroundSize = "contain";
	movieInfo.appendChild(mvImage);
	
	let mvTitle = document.createElement('Div');              
	mvTitle.textContent = movie[0].mvName;
	mvImage.class = "movie";
	movieInfo.appendChild(mvTitle);
	
	let mvGrade = document.createElement('Div');              
	mvGrade.textContent = movie[0].mvGrade;
	mvGrade.class = "movie";
	movieInfo.appendChild(mvGrade);
	
	let mvStatus = document.createElement('Div');              
	mvStatus.textContent = movie[0].mvStatus;
	mvStatus.class = "movie";
	movieInfo.appendChild(mvStatus);
	
	let mvComments = document.createElement('Div');              
	mvComments.textContent = movie[0].mvComments;
	mvComments.class = "movie";
	movieInfo.appendChild(mvComments);
}

function divClick(mvCode, mvDate){
	//서버전송
	let request = new XMLHttpRequest();
	 request.onreadystatechange = function(){
		    if(this.readyState == 4 && this.status == 200){
		    	let jsonData = decodeURIComponent(request.response);
		    	screeningData = JSON.parse(jsonData);
		       screening();
		    }
		 };
	 request.open("POST", "Step3", true);
	 request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8")
	 request.send("sCode=Step3&mvCode="+mvCode+"&mvDate="+mvDate);
}

function screening() {
	let selectionTime = document.getElementById("selectionTime");
	let selectionInfo = document.getElementById("selectionInfo");
	let selectionGrade = document.getElementById("selectionGrade");
	let selectionScreen = document.getElementById("selectionScreen");
	
	//mvCode, mvName, mvGrade, myTime, screen (여기서 띄워야할건 mvTime, screen, mvGrade ());
	// 전체 (W) ,12세 (T) 15세 (F) 청불 (A)
	alert(screeningData.length);
	for(index = 0; index < screeningData.length; index++){
		let i = index;
		let mvTime = document.createElement('Div');              
		mvTime.textContent = screeningData[index].time;
		mvTime.style.cursor = "pointer";
		// 이걸 펑션에 바로 인식이안됌.. 아래는 나중에 수정.
		selectionTime.appendChild(mvTime);
		mvTime.addEventListener('click', function(){selectInfo(i);});
		let mvGrade = document.createElement('Div');
		var grade;
		switch (screeningData[index].mvGrade){
			case "W" : grade = "전체이용가";  break;
			case "T" : grade = "12세";  break;
			case "F" : grade = "15세"; 	break;
			case "A" : grade = "성인";  break;
			default :  grade = "db 확인"; break;
		}

		mvGrade.textContent = grade;
		selectionGrade.appendChild(mvGrade);
	
		let myScreen = document.createElement('Div');
		myScreen.textContent = screeningData[index].screen + "관";
		selectionScreen.appendChild(myScreen);
	}
}

function selectInfo(index) {
	var form = document.createElement("form");
	let formData = "sCode=Step4&mvCode="+ screeningData[index].mvCode +"&mvTime="+screeningData[index].mvTime +"&mvGrade="+screeningData[index].mvGrade+"&screen="+screeningData[index].screen;
	form.action = "Step4?" + formData; 
	form.method = "post";
	document.body.appendChild(form);
	form.submit();
}
	
	
</script>
</html>
