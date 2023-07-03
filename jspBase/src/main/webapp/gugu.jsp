<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%
   	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html;charset=utf-8");
	int dan = Integer.parseInt(request.getParameter("dan"));
	String result;
   %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" width="200">
		<tr align="center" bgcolor="skyblue">
			<th colspan="2">**<%=dan %>단 **</th>
	<%
		for(int i =1 ; i<10; i++) {
			
	%>
		<tr>
		<td width="100"><%=dan %> X <%=i %></td>
		<td width="100"> <%=dan*i %></td>
		</tr>
	<%
		
		}
	%>
	</table>
	<a href="/jspBase/gugu.html">구구단입력창으로 돌아가기</a>
</body>
</html>