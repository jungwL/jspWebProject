<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	String name=(String)session.getAttribute("name");
    	session.setAttribute("address", "서울시 종로구"); //자동으로 session이라는 이름으로 생성된다.
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>세션 바인딩 실습</title>
</head>
<body>
	<h2>이름은 <%=name %>입니다.</h2>
	<a href="session2.jsp">두번쨰 페이지로 이동</a>
</body>
</html>