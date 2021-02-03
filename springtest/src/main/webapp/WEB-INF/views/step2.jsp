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
	</section>
</body>
<script>
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
		       alert(jsonData);
		    }
		 };
	 request.open("POST", "/Step3?sCode=Step3&mvCode="+mvCode+"&mvDate="+mvDate,true);
	 request.send();
}
	
</script>
</html>
