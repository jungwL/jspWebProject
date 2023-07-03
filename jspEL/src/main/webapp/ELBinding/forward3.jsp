<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="jspEL.ex01.*,java.util.*"
    %>
<%
	request.setCharacterEncoding("utf-8");
	List memberList = new ArrayList();
	MemberVO memVo1 = new MemberVO("son","1234","쏘니","sonny@gamil.com");
	MemberVO memVo2 = new MemberVO("lee","333","이강인","kangin@gmail.com");
	memberList.add(memVo1);
	memberList.add(memVo2);
	request.setAttribute("memberList", memberList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>바인딩 실습</title>
</head>
<body>
	<jsp:forward page="member3.jsp"/>
</body>
</html>