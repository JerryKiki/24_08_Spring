<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resource/common.css" />
<title>ARTICLE LIST</title>
</head>
<body>
	
	<c:if test="${isLogined }">
	<div class="memberInfo">
		<div>로그인 정보</div>
		<div>아이디 : ${loginedMember.loginId }</div>
		<div>닉네임 : ${loginedMember.nickname }</div>
	</div>
	</c:if>

	<div style="font-size: 3rem; font-weight: bold; margin: 10px; margin-bottom:20px; color: #FF4E88">LIST</div>

	<hr />

	<table border="1" cellspacing="0" cellpadding="5" style="width: 70%; border-collapse: collapse; border-color: #36BA98;">
		<thead>
			<tr>
				<th style="text-align: center;">ID</th>
				<th style="text-align: center;">Registration Date</th>
				<th style="text-align: center;">Title</th>
				<th style="text-align: center;">Author</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${articles}">
				<tr>
					<td style="text-align: center;">${article.id}</td>
					<td style="text-align: center;">${article.regDate.substring(0,10)}</td>
					<td style="text-align: center;"><a href="http://localhost:8080/usr/article/getArticle?id=${article.id}">${article.title}</a></td>
					<td style="text-align: center;">${article.nickname}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>

	<!-- CSS -->
	
	<style type="text/css">
	
	td, th {
		font-family: 'Pretendard-Regular';
		color: black;
		padding: 5px;
	}
	
	</style>
	
	
	
	
<!-- 		<h3>기존 문법</h3> -->
<%--List<Article> articles = (List<Article>) request.getAttribute("articles"); %>	-->
<!-- 	<table style="border-collapse: collapse; border-color: #36BA98;" border="1px"> -->
<!-- 		<thead> -->
<!-- 			<tr style="text-align: center;"> -->
<!-- 				<th>번호</th> -->
<!-- 				<th>날짜</th> -->
<!-- 				<th>제목</th> -->
<!-- 				<th>내용</th> -->
<!-- 				<th>작성자</th> -->
<!-- 				<th>수정</th> -->
<!-- 				<th>삭제</th>	 -->
<!-- 			</tr>		 -->
<!-- 		</thead> -->
<!-- 		<tbody> -->
<%-- 			<% --%>
<%-- 			for (Article article : articles) {
<%-- 			%> --%>
<!-- 			<tr style="text-align: center;"> -->
<%-- 				<td><%=article.getId()%>번</td> --%>
<%-- 				<td><%=article.getRegDate()%></td> --%>
<%-- 				<td><%=article.getTitle()%></td> --%>
<%-- 				<td><%=article.getBody()%></td> --%>
<%-- 				<td><%=article.getNickname()%></td> --%>
<!-- 				<td>수정</td> -->
<!-- 				<td>삭제</td> -->
<!-- 			</tr> -->
<%-- 			<% --%>
<%--			}
<%-- 			%>		 --%>
<!-- 		</tbody> -->
<!-- 	</table> -->