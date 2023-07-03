<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String id=(String)request.getAttribute("id");
	String pwd=(String)request.getAttribute("pwd");
	String name=(String)request.getAttribute("name");
	String email=(String)application.getAttribute("email");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2 align="center">회원 정보출력</h2>
	<table border="1" align="center" width="700">
		<tr align="center" bgcolor="green">
			<th>아이디</th><th>비밀번호</th><th>이름</th><th>이메일</th>
		</tr>
		<tr align="center">
			<td>${memberList[0].id }</td>
			<td>${memberList[0].pwd }</td>
			<td>${memberList[0].name }</td>
			<td>${memberList[0].email }</td>
		</tr>
		<tr align="center">
			<td>${memberList[1].id }</td>
			<td>${memberList[1].pwd }</td>
			<td>${memberList[1].name }</td>
			<td>${memberList[1].email }</td>
		</tr>
	</table>
	<p align="center"><a href="/jspEL/memberForm.html">새 회원 등록하기</a></p>
</body>
</html>