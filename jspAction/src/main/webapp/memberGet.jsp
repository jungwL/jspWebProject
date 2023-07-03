<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.*, jspAction.ex01.*"%>
<%
	request.setCharacterEncoding("utf-8");
%>
	<jsp:useBean id="memberVO"  class="jspAction.ex01.MemberVO" scope="page"/>
	<jsp:setProperty property="*" name="memberVO"/>  
	 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원정보 목록창</title>
</head>
<body>
	<h2 align="center">회원 정보</h2>
	<table border="1" align="center" width="700">
		<tr align="center" bgcolor="green">
			<th>아이디</th><th>비밀번호</th><th>이름</th><th>이메일</th>
		</tr>
			<tr align="center">
				<td><jsp:getProperty property="id" name="memberVO"/></td>
				<td><jsp:getProperty property="pwd" name="memberVO"/></td>
				<td><jsp:getProperty property="name" name="memberVO"/></td>
				<td><jsp:getProperty property="email" name="memberVO"/></td>
			</tr>
	</table>
</body>
</html>