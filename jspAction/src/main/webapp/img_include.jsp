<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	request.setCharacterEncoding("utf-8");
	String name =request.getParameter("name");
	String imgName=request.getParameter("imgName");
%>
<html>
<head>
<meta charset="UTF-8">
<title>Include 액션 태그</title>
</head>
<body>
	<h2>이름은 <%=name %></h2>
	<img alt="" src="./images/<%=imgName %>">
</body>
</html>