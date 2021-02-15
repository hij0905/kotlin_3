<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link href="/resources/css/kotlin.css" rel="stylesheet" />
</head>
<body onLoad = "init()">
	<a href="http://172.30.1.27/LoginForm" >로그인폼 이동</a><br />
	<P>  Now Time : ${Access} </P>
	<section id="seatZone">좌석 선택 페이지입니다.</section>
</body>
<script>
	function init(){
		let sZone = document.getElementById("seatZone");
		let seatInfo = JSON.parse('${SeatInfo}');
		alert(seatInfo.length);
		let rows;
		let cols;
		
		for(rowIndex = 0; rowIndex < 20; rowIndex++){
			rows = document.createElement("div");
			rows.className = "row";
			for(colIndex = 0 ; colIndex < 20; colIndex++){
				cols = document.createElement("div");
				cols.setAttribute("name", "seat");
				cols.className = "able";
				
				rows.appendChild(cols);
			}
			sZone.appendChild(rows);
		}
		
		/* Inactive Seat 배치*/
		let seat = document.getElementsByName("seat");
		
		for(index=0; index<seatInfo.length; index++){
				seat[seatInfo[index].seatNum-1].className = (seatInfo[index].seatType == "H")? "hall":
					((seatInfo[index].seatType == "R")? "reserve":"repair");
			}
		
		/*Active Seat에 Event 추가*/
		   let rowChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		   let cnt = -1;
		   for(index =0;index<seat.length;index++){
		      if(seat[index].className !="hall"){
		         cnt++;
		         let first = rowChar.substr(parseInt(cnt/10),1);
		            let second = (cnt%10)+1;
		            if(seat[index].className == "able"){
		               seat[index].setAttribute("onClick","selectSeat("+(index+1)+",'"+first+second+"')");
		         }
		      }
		   }
		}
	
	function selectSeat(sNumber,cNumber){
	   let type = prompt("성인:A | 청소년:J | 어린이:C");
	   alert(sNumber+":"+cNumber+":"+type);
	}

</script>
</html>