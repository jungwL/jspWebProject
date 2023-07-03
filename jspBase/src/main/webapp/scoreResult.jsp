<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	String name = request.getParameter("name");
	int score =Integer.parseInt(request.getParameter("score"));
	
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2><%=name %>님의 시험점수는 <%=score %> 입니다.</h2>
	<%
		if(score >89 && score <=100) {
	%>
		<p>평가결과는 A입니다.</p>
	<%
		}else if(score >79 && score <=89){
	%>
		<p>평가결과는 B입니다.</p>
	<%
		}else if(score >69 && score <=79){
	%>
		<p>평가결과는 C입니다.</p>
	<%
		}else if(score >59 && score <=69){
	%>
		<p>평가결과는 D입니다.</p>
	<%
		}else {
	%>
		<p>평가결과는 F입니다.</p>
	<%
		}
	%>
</body>
</html>