<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="LIST"></c:set>
<%@ include file="../common/head.jspf"%>

	<table class="table-fixed border-collapse w-3/4 border-2" style="border-color: #36BA98;">
		<thead>
			<tr>
				<th style="text-align: center;">ID</th>
				<th style="text-align: center;">Registration Date</th>
				<th style="text-align: center;">Title</th>
				<th style="text-align: center;">Author</th>
				<c:if test="${isLogined }">
					<th style="text-align: center;">Modify</th>
					<th style="text-align: center;">Delete</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="article" items="${articles}">
				<tr>
					<td style="text-align: center;">${article.id}</td>
					<td style="text-align: center;">${article.regDate.substring(0,10)}</td>
					<td style="text-align: center;"><a href="getArticle?id=${article.id}">${article.title}</a></td>
					<td style="text-align: center;">${article.nickname}</td>
					<c:if test="${isLogined }">
						<td style="text-align: center"><a href="doModify?id=${article.id}">수정</a></td>
						<td style="text-align: center"><a href="doDelete?id=${article.id}">삭제</a></td>
					</c:if>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div><button onclick="location.replace('doWrite');">게시글 작성</button></div>

	<!-- CSS -->
	
	<style type="text/css">
	td, th {
		font-family: 'Pretendard-Regular';
		border: 2px solid #36BA98;
		color: black;
		padding: 5px;
	}
	
	button {
		font-size: 1.2rem;
		background-color: #36BA98;
		color: white;
		margin-top: 20px;
		padding: 10px;
		border-radius: 10px;
	}
	</style>
	
<%@ include file="../common/foot.jspf"%>
	
	
	
	
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