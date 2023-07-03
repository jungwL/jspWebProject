<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String msg= "아이디를 입력하지 않았습니다. 아이디를 입력해쥬세요";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결과 창</title>
</head>
<body>
	<%
		
		String id=request.getParameter("user_id");
		if(id.length() ==0) {
			
		
	%>
	<jsp:forward page="login.jsp">
		<jsp:param value="<%=msg %>" name="msg"/>
	</jsp:forward>
	<%
		}
	%>
	<h2>환영합니다.<%=id %>님!!</h2>
</body>
</html>