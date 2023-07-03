<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isELIgnored="false"
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<c:set var="articlesList" value="${articleMap.articlesList}"/>
<c:set var="totArticles" value="${articleMap.totArticles}"/>
<c:set var="section" value="${articleMap.section}"/>
<c:set var="pageNum" value="${articleMap.pageNum}"/>
<% request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 목록창</title>
<style type="text/css">
	a {
		text-decoration: none;
		color: black;
	}
	.selPage {
		color: red;
		font-weight: bold;
	}
	.noline{
		color:black;
		font-weight: normal;
	}
	a:hover{
		text-decoration: underline;
	}
</style>
</head>
<body>
	<table align="center" border="1" width="80%">
		<tr align="center" bgcolor="skyblue">
			<th>글번호</th><th>작성자</th><th>제목</th><th>작성일</th>
		</tr>
		<c:choose>
			<c:when test="${empty articlesList}">  
				<tr>
					<td colspan="4" align="center">등록된 글이 없습니다.</td>
				</tr>
			</c:when>
			<c:when test="${!empty articlesList}"> 
				<c:forEach var="article" items="${articlesList}" varStatus="articleNum">
					<tr align="center">
						<!--  <td width="5%">${articleNum.count}</td>-->
						<td width="5%">${(pageNum-1)*10+articleNum.count}</td>
						<td width="10%">${article.id}</td>
						<td align="left" width="50%">
							<span style="padding-left:10px"></span>
							<c:choose>
								<c:when test="${article.level > 1 }"> 
									<c:forEach begin="1" end="${article.level-1}" step="1">
										<span style="padding-left:30px"></span>
									</c:forEach>
									[답변]<a href="${contextPath}/board/viewArticle.do?articleNo=${article.articleNo}">${article.title}</a>
								</c:when>
								<c:otherwise>
									<a href="${contextPath}/board/viewArticle.do?articleNo=${article.articleNo}">${article.title}</a>
								</c:otherwise>
							</c:choose>
						</td>
						<td width="10%">${article.writeDate }</td>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</table>
	<div align="center">
		<c:if test="${totArticles !=0 }">
			<c:choose>
				<c:when test="${totArticles > 100}">
					<c:forEach var="page" begin="1" end="10" step="1">
						<c:if test="${section > 1 && page ==1 }">
							<a href="${contextPath}/board/listArticles.do?section=${section-1}&pageNum=10"> prev</a> <!-- 첫번쨰 섹션으로이동 -->
						</c:if>
						<c:choose>
							<c:when test="${(section-1)*10+pageNum==(section-1)*10+page }">
								<a class="selPage" href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${(section-1)*10+page}</a>
							</c:when>
							<c:otherwise>
								<a class="noLine" href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${(section-1)*10+page}</a>
							</c:otherwise>
						</c:choose>
						<c:if test="${page==10 && (totArticles/100)>section }">
							<a href="${contextPath }/board/listArticles.do?section=${section+1}&pageNum=1"> next</a>
						</c:if>
					</c:forEach>
				</c:when>
				<c:when test="${totArticles <=100 }">
				<c:if test="${(totArticles mod 10) ==0}"> <!-- 데이터 갯수가 딱 떨어질 경우 예)40-->
					<c:set var="totArticles" value="${totArticles-1}" />
				</c:if>
					<c:forEach var="page" begin="1" end="${totArticles/10+1}" step="1">
						<c:choose>
							<c:when test="${page==pageNum}">
								<a class="selPage" href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page}</a>
							</c:when>
							<c:otherwise>
								<a class="noline" href="${contextPath }/board/listArticles.do?section=${section}&pageNum=${page}">${page}</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:when>
			</c:choose>
		</c:if>
	</div>
	<p align="center"><a href="${contextPath }/board/articleForm.do">글쓰기</a></p>
</body>
</html>