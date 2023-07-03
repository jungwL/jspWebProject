<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일업로드 창</title>
</head>
<body>
	<form action="${contextPath}/upload.do" method="post" enctype="multipart/form-data">
		이름 : <input type="text" name="name"><br>
		제목 : <input type="text" name="title"><br>
		내용 : <input type="text" name="content"><br>
		이미지 파일 1 : <input type="file" name="file1"><br>
		이미지 파일 2 : <input type="file" name="file2"><br>
		<input type="submit" value="업로드"><br>
	</form>
</body>
</html>