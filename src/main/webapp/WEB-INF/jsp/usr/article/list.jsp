<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${boardCode } LIST"></c:set>
<%@ include file="../common/head.jspf"%>

	<c:if test="${searched }">
		<div style="margin-right: auto; margin-bottom: 3px; font-size: 1.2rem;">${searchItem }에 대한 ${searchKeyword } 검색 결과</div>
	</c:if>
	<div style="margin-right: auto; margin-bottom: 10px; font-size: 1.2rem;">전체 게시글 수 : ${totalCount }개</div>

	<table class="table-fixed border-collapse w-full border-2" style="border-color: #36BA98;">
		<thead>
			<tr>
				<th style="text-align: center;">ID</th>
				<th style="text-align: center;">Registration Date</th>
				<th style="text-align: center;">Board</th>
				<th style="text-align: center;">Title</th>
				<th style="text-align: center;">Author</th>
				<th style="text-align: center;">View</th>
				<c:if test="${isLogined }">
					<th style="text-align: center;">Modify</th>
					<th style="text-align: center;">Delete</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<c:if test="${!noneArticle }">
			<c:forEach var="article" items="${articles}">
				<tr>
					<td style="text-align: center;">${article.id}</td>
					<td style="text-align: center;">${article.regDate.substring(0,10)}</td>
					<td style="text-align: center;">${article.code }</td>
					<td style="text-align: center;"><a href="getArticle?id=${article.id}">${article.title}</a></td>
					<td style="text-align: center;">${article.nickname}</td>
					<td style="text-align: center;">${article.view}</td>
					<c:if test="${isLogined }">
						<td style="text-align: center"><a href="doModify?id=${article.id}">수정</a></td>
						<td style="text-align: center"><a href="doDelete?id=${article.id}">삭제</a></td>
					</c:if>
				</tr>
			</c:forEach>
			</c:if>
			<c:if test="${noneArticle }">
				<tr style="text-align: center;">
				<c:if test="${isLogined }">
					<td colspan='7' style="text-align: center;">아직 아무런 게시글이 없습니다.</td>
				</c:if>
				<c:if test="${!isLogined }">
					<td colspan='5' style="text-align: center;">아직 아무런 게시글이 없습니다.</td>
				</c:if>
				</tr>
			</c:if>
		</tbody>
	</table>
	
	<c:if test="${!noneArticle }">
		<div class = "page" style = "font-size: 1.2rem; margin-top: 10px;">
			<c:if test="${!searched }">
				<c:forEach var="i" begin="${startNum }" end="${endNum }">
						<a href="getArticles?page=${i }&boardId=${boardId}" <c:if test="${i == pageNum }">class = "cPage"</c:if>>
							<c:if test="${i < 10}">0</c:if>${i }
						</a>
				</c:forEach>
			</c:if>
			<c:if test="${searched }">
				<c:forEach var="i" begin="${startNum }" end="${endNum }">
						<a href="getArticles?page=${i }&boardId=${boardId}&searchItem=${searchItem}&searchKeyword=${searchKeyword}" <c:if test="${i == pageNum }">class = "cPage"</c:if>>
							<c:if test="${i < 10}">0</c:if>${i }
						</a>
				</c:forEach>
			</c:if>
		</div>
	</c:if>

	
	<div><button onclick="location.replace('doWrite');">게시글 작성</button></div>
	
	<div class="search mx-auto" style="margin-top: 30px;">
		<div style="text-align: left">▶ Search On This Board</div>
		<form onsubmit="searchForm__submit(this); return false;" style="font-size: 1.4rem;" action="getArticles" class="flex justify-center items-center">
		<input type="hidden" value="${boardId }" name="boardId" />
		<div>
			<select name="searchItem" id="column">
				<option value="select">Select</option>
				<option value="title">title</option>
				<option value="body">body</option>
				<option value="nickname">author</option>
			</select>
		</div>
		<div>
			<input type="text" autocomplete="off" name="searchKeyword" style="margin: 0 10px"/>
		</div>
		<div>
			<input style="cursor: pointer; background-color:#36BA98; color: white; padding: 2px 10px; border-radius: 10px;" type="submit" value="검색">
		</div>
		
		</form>
	</div>
	

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
	
	.cPage {
		color: #36BA98;
	}
	</style>
	
	<!-- JS -->
	<script type="text/javascript">
	
		function searchForm__submit(form) {
			console.log("form.searchItem.value : " + form.searchItem.value);
			console.log("form.searchKeyword.value : " + form.searchKeyword.value);
			
			let searchItem = form.searchItem.value;
			let searchKeyword = form.searchKeyword.value.trim();
			
			if(searchItem == "select") {
				alert('검색할 항목을 선택하세요.');
				return;
			}

			if(searchKeyword.length == 0) {
				alert('검색할 키워드를 입력하세요.');
				return;
			}
			
			//다 확인했으면 그냥 여기서 submit
			form.submit();
		}
	
	</script>
	
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