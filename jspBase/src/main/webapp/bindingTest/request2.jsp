<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String name=(String)request.getAttribute("name");
	String address=(String)request.getAttribute("address");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>request 바인딩 실습</title>
</head>
<body>
	<h2>이름은 <%=name %>입니다.</h2>
	<h2>주소는 <%=address %>입니다.</h2>
</body>
</html>