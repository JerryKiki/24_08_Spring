<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${article.id }번 게시글 상세"></c:set>
<%@ include file="../common/head.jspf"%>
	
	<div class="details">
		<div>번호 : ${article.id }</div>
		<div>날짜 : ${article.regDate.substring(0,10) }</div>
		<div>제목 : ${article.title }</div>
		<div>내용 : ${article.body }</div>
		<div>작성자 : ${article.nickname }</div>
	</div>
	
	<br />
	
	<c:if test="${canAccess }">
		<ul class="menu flex mx-auto justify-between" style="font-size: 1.2rem;">
			<li><a href="doModify?id=${article.id }">수정</a></li>
			<li><a href="doModify?id=${article.id }">삭제</a></li>
		</ul>
	</c:if>
	
	<br />
	
	<div><a href="getArticles">▶ 리스트로 돌아가기</a></div>
	
<%@ include file="../common/foot.jspf"%>

	<!-- CSS -->
	
	<style type="text/css">
	
	.menu > li {
		padding: 0 10px;
		margin: 0 10px;
		background-color: #36BA98;
		color: white;
		border-radius: 10px;
	}
	
	.details {
		font-size: 1.5rem;
		color: #36BA98;
	}
	
	</style>