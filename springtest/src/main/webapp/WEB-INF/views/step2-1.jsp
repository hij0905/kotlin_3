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
   <img src="resources/img/${selectMovie.getMvImage()}" /><br><br>
              이름 : ${selectMovie.getMvName() }<br> 
	    등급 : ${selectMovie.getMvGrade() } <br>
	    내용 : ${selectMovie.getMvComments() } <br>
	    코드 : ${selectMovie.getMvCode() } <br><br>

   <div id="showdate0" onclick='step2(0)'></div>
   <div id="showdate1" onclick='step2(1)'></div>
   <div id="showdate2" onclick='step2(2)'></div>
   <div id="showdate3" onclick='step2(3)'></div>
   <div id="showdate4" onclick='step2(4)'></div>
   <div id="showdate5" onclick='step2(5)'></div>
   <div id="showdate6" onclick='step2(6)'></div>

</body>
<script>
   // inner html
   function init() {
      for (var i = 0; i < 7; i++) {
         document.getElementById('showdate' + i).innerHTML = "<input id='"+ i +"' type='button' value='"+showDate(i)+ "' \>";
      }
   }
   function step2(val) {
      var mvCode = "${selectMovie.getMvCode()}";
      var mvDate = document.getElementById(val).value;
      alert(mvDate);
      location.href = "/Step2?mvCode="+mvCode +"&mDate="+mvDate;
      
      // 여기에선 get방식으로 처리할거임. 넘겨야할 데이터는 mv_code, date 값
   }
   function showDate(num) {
      let dayStr = "${movieDate}";
      let day = (dayStr.split(" "))[0].split("-");
      let now = new Date();
      now.setFullYear(parseInt(day[0]), parseInt(day[1]), parseInt(day[2]));
      now.setDate(now.getDate()+ num );
      
      var date = now.getFullYear() + "-" + now.getMonth() + "-" + now.getDate();
      return date;
   }

</script>


</html>