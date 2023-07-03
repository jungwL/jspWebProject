<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    	request.setCharacterEncoding("utf-8");
    	response.setContentType("text/html;charset=utf-8");
    	String id=request.getParameter("user_id");
    	String pwd=request.getParameter("user_pwd");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 결과 출력</title>
</head>
<body>
	<%
		if(id == null || id.length() ==0) {	
	%>
	<p>아이디를 입력하세요</p>
	<a href="/jspBase/login.html">로그인 하기</a>
	<%
		}else { 
			if(id.equals("admin") && pwd.equals("7474")) {
				
	%>
			<h2>관리자로 로그인 했습니다.</h2>
			<input type="button" value="상품정보 등록하기">
			<input type="button" value="상품정보 수정하기">
	<%
		}else{
	%>
		<h2>환영합니다. <%=id %>님!!</h2>
	<%
		}
	}
	%>
</body>
</html>