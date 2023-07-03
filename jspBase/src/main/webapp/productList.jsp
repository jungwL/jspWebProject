<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품목록 리스트</title>
<style type="text/css">
	.list {
		width: 80%;
		padding: 0 10px;
		margin: 0 auto;
		overflow: hidden;
	}
	.list li {
		position: relative;
		margin-left: 10px;
		color:#2d2d2d;
		line-height: 150px;
		list-style: none;
		border-bottom: 2px solid #ccc;
	}
	.pdimg {
		margin-left: 50px;
	}
	.list li img {
		float: left;
		position: absolute;
	}
	.list li a {
		color: #2d2d2d;
		text-decoration: none;
		margin-left: 340px;
	}
	.list li a:hover {
		text-decoration: underline;
	}
	.list li span {
		color: blue;
		margin-bottom: 330px;
	}
</style>
</head>
<body>
	<h2 align="center">상품목록 리스트</h2>
	<ul class="list">
		<li>
			<span class="pdimg">상품이미지</span>
			<span>상품명</span>
			<span>상품선택</span>
		</li>
		<%
			String [] pdName={"냉장고" , "컴퓨터", "세탁기","에어컨","청소기"};
			for(int i=0; i<5 ; i++){	
		%>
		<li>
			<a class="pdimg" href="#"><img src="images/<%=i %>.png"></a>
			<a href="#"><strong><%=pdName[i] %></strong></a>
			<a href="#"><input type="checkbox" name="chk"></a>
		</li>
		<%
			}
		%>
	</ul>
</body>
</html>