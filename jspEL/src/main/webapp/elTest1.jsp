<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>표현언어로 여러가지 데이터 출력</h1>
	
	<h3>
		숫자값=${200} <br>
		홍길동님 ${"안녕하세요"} <br>
		연산 => ${20+30} <br>
		실수값=> ${5.3} <br>
		boolean값=> ${true}<br>
		연산=> ${"10" +1} <br>
		null값=> ${null+10}<br>
	</h3>
</body>
</html>