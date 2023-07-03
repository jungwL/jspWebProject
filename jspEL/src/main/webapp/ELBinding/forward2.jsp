<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="jspEL.ex01.*"
    %>
<%
	request.setCharacterEncoding("utf-8");
	MemberVO member= new MemberVO("park","1234","박찬호","park@naver.com");
	request.setAttribute("member", member);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>바인딩 실습</title>
</head>
<body>
	<jsp:forward page="member2.jsp"/>
</body>
</html>