<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name=request.getParameter("name");
	String age=request.getParameter("age");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>jsp스크립트 요소</title>
</head>
<body>
	<h1><%=name %>님 환영합니다.</h1>
	<h2>당신의 나이는<%=age %>입니다.</h2>
</body>
</html>