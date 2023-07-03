<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	session.setAttribute("name", "김철수");
    	application.setAttribute("address", "서울시 영등포구");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>어플리케이션 바인딩 실습</title>
</head>
<body>
	<h2>이름은 세션객체에 저장하고 주소는 애플리케이션 객체에 저장</h2>
	<a href="appTest2.jsp">두번째 웹 페이지로 이동</a>
</body>
</html>