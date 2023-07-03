<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*,jspEL.ex01.*"
    %>
<%
	request.setCharacterEncoding("utf-8");
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	String name=request.getParameter("name");
	String email=request.getParameter("email");
%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보창</title><!--  -->
</head>
<body>
	<h2 align="center">회원 정보출력</h2>
	<table border="1" align="center" width="700">
		<tr align="center" bgcolor="green">
			<th>아이디</th><th>비밀번호</th><th>이름</th><th>이메일</th><th>가입일</th>
		</tr>
				<tr align="center">
				<td><%=id %></td>
				<td><%=pwd %></td>
				<td><%=name %></td>
				<td><%=email %></td>
			</tr>
	</table>
	<p align="center"><a href="/jspEL/memberForm.html">새 회원 등록하기</a></p>
</body>
</html>