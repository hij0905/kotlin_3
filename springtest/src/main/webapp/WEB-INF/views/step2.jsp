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
	<a href="http://192.168.25.39/LoginForm" >로그인폼 이동</a><br />
	<P>  Now Time : ${Access} </P>
	<section id="movieZone" style="display:flex;">
		<div id="movieInfo"></div>
		<div id="selectionDate"><h2>Screening Date</h2></div> <br>
		<div id="selectionTime"><h2>Selection Time</h2></div>
	</section>
</body>
<script>
let screeningData;
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
	let request = new XMLHttpRequest();
	request.onreadystatechange = function(){
		if(this.readyState == 4 && this.status == 200){
			let jsonData = decodeURIComponent(request.response);
			alert(jsonData);
			screeningData = JSON.parse(jsonData);
			screening();
			//console.log("서버 갔다 옴");
		}
	};
	request.open("POST", "Step3", true)
	request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
	request.send("sCode=Step3&mvCode="+mvCode+"&mvDate="+mvDate);
}
//screeningData[index].mvTime
function tScreenClick(index){
	let dateTime = screeningData[index].mvDate.substring(0, 10).replace(/-/g,""); // g를 붙여야 모두 replace 실행됨!
	//let dateTime = screeningData[index].mvDate.replace(/:/g,"").replace(/-/g,"").replace("+","");
	let formData = "sCode=Step4&mvCode="+ screeningData[index].mvCode + 
	"&mvThCode=1&mvDateTime="+ dateTime + screeningData[index].mvTime
	+ "&mvScreen="+ screeningData[index].mvScreen ;
	let form = document.createElement("form");
	form.action = "Step4?" + formData;
	form.method = "post";
	document.body.appendChild(form);
	form.submit();
}

function screening(){
	let sTime = document.getElementById("selectionTime");
	
	for (index=0; index< screeningData.length; index++) {
		let i = index;
		let tDiv = document.createElement('div');
		let tImg = document.createElement('img');
		tImg.src = "/resources/img/" + screeningData[index].mvGrade+".JPG";
		
		let tScreen = document.createElement("input");
		tScreen.type = "button";
		
		tScreen.value = screeningData[index].mvTime.substring(0, 2)+ ":" +screeningData[index].mvTime.substring(2)
		+ " " + screeningData[index].mvScreen + "관";
		
		
		tScreen.style.cursor = "pointer";
		tScreen.addEventListener('click', function(){
			tScreenClick(i);
		});
		
		tDiv.appendChild(tImg);
		tDiv.appendChild(tScreen);
		
		sTime.appendChild(tDiv);	
// 		/* mvScreen */
// 		let mvScreen = document.createElement('Div');
// 		mvScreen.textContent = screeningData[index].mvScreen + "관";
// 		time.appendChild(mvScreen);

// 		/* mvTime */
// 		let mvTime = document.createElement('Div');
// 		var sctime = screeningData[index].mvTime
// 		mvTime.textContent = "시간 = " + sctime.substring(0, 2) + ":"
// 				+ sctime.substring(2);
// 		mvTime.style.cursor = "pointer";
// 		mvTime.addEventListener('click', 
// 				function(){divClick2(screeningData[0].mvCode, mvDate, sc);});//onClick 역할
// 		time.appendChild(mvTime);

// 		/* mvGrade */
// 		let mvGrade = document.createElement('Div');

// 		var gr = screeningData[index].mvGrade
// 		switch (gr) {
// 		case "W":
// 			var grade = "전체 관람가";
// 			break;
// 		case "T":
// 			var grade = "12세 관람가";
// 			break;
// 		case "F":
// 			var grade = "15세 관람가";
// 			break;
// 		case "A":
// 			var grade = "청소년 관람 불가";
// 			break;
// 		}
// 		mvGrade.textContent = grade;
// 		time.appendChild(mvGrade);
		}
	}
</script>
</html>
