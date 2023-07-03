<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%
   	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	int height = Integer.parseInt(request.getParameter("height"));
	int weight = Integer.parseInt(request.getParameter("weight"));
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>비만도 체크 결과</h2>
	<%
		String result2=""; //결과 변수 만들기
		Double result=(height-100) * 0.9;
	if(result+5>weight && result-5 <weight) {
		result2="정상입니다";
	}else if(result-5>weight) {
		result2="밥 더드세요 말랐어요";
	}else {
		result2="비만입니다.";
	}
	 %>
	 <h3><%=result2 %></h3>
	 <a href="/jspBase/bmi.html">입력창으로 이동하기</a>
</body>
</html>