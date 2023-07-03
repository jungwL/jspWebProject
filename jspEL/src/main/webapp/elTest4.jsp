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
	<h1>표현언어로 여러가지 데이터 출력(논리연산자)</h1>
	
	<h3>
		\${50 == 50 && 20 == 20} : ${50 == 50 && 20 == 20} <br> <!-- 비교연산자 우선 -->
		\${50 == 50 and 20 == 20} : ${50 == 50 and 20 == 20} <br>
		\${50 == 50 || 20 == 20} : ${50 == 50 || 20 == 20} <br>
		\${50 == 50 or 20 == 20} : ${50 == 50 or 20 == 20} <br>
		\${ !(80 == 20)} : ${!(80 == 20)} <br>
		\${ not(80 == 20)} : ${ not(80 == 20)} <br>
	</h3>
</body>
</html>