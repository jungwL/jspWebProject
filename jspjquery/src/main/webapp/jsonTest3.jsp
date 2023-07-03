<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Json 테스트</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	function fn_jsonoutput() {
		let jsonData='{"name" : "손흥민","age" :31,"gender":"남자", "nickname" : "손세이셔널" }'; //배열로 저장
		let jsonInfo=JSON.parse(jsonData);
		let output="<h2>회원 나이 </h2>";
		output+="================<br>";
		output+="이름 : " + jsonData.name + "<br>";
		output+="나이 : " + jsonData.age + "<br>";
		output+="성별 : " + jsonData.gender + "<br>";
		output+="닉네임 : " + jsonData.nickname + "<br>";
		$('#reuslt').html(output);
	}
</script>
</head>
<body>
	<input type="button" value="JSON데이터 출력" onclick="fn_jsonoutput();">
	<div id="result">
		
	</div>
</body>
</html>