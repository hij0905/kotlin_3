<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>Home</title>
	<link href="/resources/css/kotlin.css" rel="stylesheet"/> 
</head>
<body onLoad="init()">
	<a href="http://172.30.1.8/LoginForm" >로그인폼 이동</a><br />
	<P>  Now Time : ${Access} </P>
	<section id="movieZone" style="display:flex;">
		<div id="movieInfo"></div>
		<div id="selectionDate"><h2>Screening Date</</h2></div> <br>
		<div id="selectionTime">selectionTime</div>
	</section>
</body>
<script>
function init(){
	/* Convert Date */
	let dateList = document.getElementById("selectionDate");
	let dayStr = "${Access}"; // 2021-02-03
	let day = dayStr.split("-"); // 2021  02  03
	let now = new Date(parseInt(day[0]), parseInt(day[1])-1, parseInt(day[2]));
	//now.setFullYear(parseInt(day[0]), parseInt(day[1])-1, parseInt(day[2]));
	
	for(i=0;i<7;i++){
		now.setDate(now.getDate()+ ((i==0)?0:1));
		let dateDiv = document.createElement('Div');
		let month = ((now.getMonth()+1)<10)? "0"+(now.getMonth()+1):(now.getMonth()+1);
		let date = (now.getDate() < 10)? "0" + now.getDate() : now.getDate();
		dateDiv.textContent = now.getFullYear() + "-" + month + "-" + date;
		dateDiv.style.cursor = "pointer";
		dateDiv.addEventListener('click', function(){divClick(movie[0].mvCode, this.textContent);});//onClick 역할
		dateList.appendChild(dateDiv);
	}
	
	
	
	let movieInfo = document.getElementById("movieInfo");
	/* Append movieInfo Div */
	let movie = JSON.parse('${movieData}');
	
	let mvImage = document.createElement('Div');
	mvImage.style.width = "316px";
	mvImage.style.height = "400px";
	mvImage.style.margin = "0px 10px 20px 0px";
	mvImage.style.backgroundImage = "url(/resources/img/" + movie[0].mvImage + ")";
	mvImage.style.backgroundSize = "contain";
	movieInfo.appendChild(mvImage);
	
	let mvTitle = document.createElement('Div');              
	mvTitle.textContent = movie[0].mvName;
	mvImage.className = "movie";
	movieInfo.appendChild(mvTitle);
	
	let mvGrade = document.createElement('Div');              
	mvGrade.textContent = movie[0].mvGrade;
	mvGrade.className = "movie";
	movieInfo.appendChild(mvGrade);
	
	let mvStatus = document.createElement('Div');              
	mvStatus.textContent = movie[0].mvStatus;
	mvStatus.className = "movie";
	movieInfo.appendChild(mvStatus);
	
	let mvComments = document.createElement('Div');              
	mvComments.textContent = movie[0].mvComments;
	mvComments.className = "movie";
	movieInfo.appendChild(mvComments);
}

function divClick(mvCode, mvDate){
	//alert(mvCode + " : " + mvDate);
	//location.href = "/Step2?mvCode="+mvCode +"&mvDate="+date;
	let request = new XMLHttpRequest();
	request.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			console.log("서버 갔다 옴");
		}
	};
	request.open("POST", "Step3?sCode=Step3&"+"mvCode="+mvCode+"&mvDate="+mvDate, true);
	request.send();
	
	
	//서버전송
// 	let form = document.createElement("form");
// 	//form.action = "Step2?sCode=Step2&mvCode=" + mvCode;
// 	form.action = "Step2?mvCode=" + mvCode;
// 	//location.href = "/Step2?mvCode="+mvCode +"&mDate="+mvDate;
// 	form.method = "post"
	
// 	document.body.appendChild(form);
// 	form.submit();
}
	
</script>
</html>
