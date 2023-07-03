<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
      isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSON</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
	//ajax로 서버로 JSON 데이터 전송하기
	function fn_jsonprocss(_object) {
		$.ajax({
			type:"post",
			async:false,
			url : "${contextPath}/json3",
			data : {object : _object },
			success: function(data,textStatus) {
				let jsonInfo=JSON.parse(data);
				if(_object == "member"){
					let memberInfo="<h2>**회원정보**</h2>";
					memberInfo+="=================<br>";
					for(let i in jsonInfo.members){
						memberInfo+="이름 : " + jsonInfo.members[i].name + " <br>";
						memberInfo+="나이 : " + jsonInfo.members[i].age + " <br>";
						memberInfo+="직업 : " + jsonInfo.members[i].job + " <br><br>";
						
					}
					$('#output1').html(memberInfo);
				}else if(_object == "book"){
					let bookInfo="<h2>**도서정보**</h2>";
					bookInfo+="===================<br>";
					for(let i in jsonInfo.books){
						bookInfo+="도서이름 : " + jsonInfo.books[i].bname+"<br>";
						bookInfo+="출판사 : " + jsonInfo.books[i].writer+"<br>";
						bookInfo+="도서이미지 : " + "<img src='"+jsonInfo.books[i].bimg+"' width=300,height=450 ><br>";
					}
					$('#output2').html(bookInfo);
				}
				
			},
			error : function(data,textStatus){
				alert("에러 발생!");
			}
		});
	}
</script>
</head>
<body>
	<input type="button" value="회원정보 수신하기" onclick="fn_jsonprocss('member');">
	<div id="output1">
	
	</div>
	<input type="button" value="도서정보 수신하기" onclick="fn_jsonprocss('book');">
	<div id="output2">
	
	</div>
	<!-- 회원정보 이름 나이 잡 -->
	<!--  도서정보 수신하기 누르면  도서정보 나오게 책제목 출판사 이미지-->
</body>
</html>