<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${article.id }번 게시글 상세"></c:set>
<%@ include file="../common/head.jspf"%>

	<!-- JS -->
	
	<!-- <iframe src="http://localhost:8080/usr/article/doIncreaseHitCount?id=757" frameborder="0"></iframe> -->
	<script>
		const params = {};
		params.id = parseInt('${param.id}');
	</script>
	
	<script>
		function ArticleDetail__doIncreaseViewCount() {
			const localStorageKey = 'article__' + params.id + '__alreadyOnView';

			if (localStorage.getItem(localStorageKey)) {
				return;
			}

			localStorage.setItem(localStorageKey, true);
			
			$.get('../article/doIncreaseViewCountRd', {
				id : params.id,
				ajaxMode : 'Y'
			}, function(data) {
				console.log(data);
				console.log(data.data1);
				$('.article-detail__view-count').empty().html("조회수 : " + data.data1);
			}, 'json')
		}
		$(function() {
			//ArticleDetail__doIncreaseHitCount();
			setTimeout(ArticleDetail__doIncreaseViewCount, 1000);
		})
	</script>
	
	<div class="details">
		<div>번호 : ${article.id }</div>
		<div>날짜 : ${article.regDate.substring(0,10) }</div>
		<div>제목 : ${article.title }</div>
		<div>내용 : ${article.body }</div>
		<div>작성자 : ${article.nickname }</div>
		<span class="article-detail__view-count">조회수 : ${article.view }</span>
		<div>좋아요 : ${article.like }</div>
	</div>
	
	<br />
	
	<c:if test="${canAccess }">
		<ul class="can-access-menu flex items-center flex-row mx-auto" style="font-size: 1.2rem;">
			<li><a href="doModify?id=${article.id }">수정</a></li>
			<li><a href="doDelete?id=${article.id }">삭제</a></li>
		</ul>
	</c:if>
	
<%-- 	<c:if test="${!canAccess }"> --%>
<%-- 		<c:choose> --%>
<%--     		<c:when test="${likeInfo[article.id]}"> --%>
<%--         		<a href="doLike?id=${article.id}">♥</a> --%>
<%-- 			</c:when> --%>
<%-- 			<c:otherwise> --%>
<%-- 				<a href="doLike?id=${article.id}">♡</a> --%>
<%-- 			</c:otherwise> --%>
<%-- 		</c:choose> --%>
<%-- 	</c:if> --%>
	
	<br />
	
	<div><a href="getArticles">▶ 리스트로 돌아가기</a></div>
	<div><a href="#" onclick="location.href = document.referrer;">▶ 뒤로가기</a></div>
	
<%@ include file="../common/foot.jspf"%>

	<!-- CSS -->
	
	<style type="text/css">
	
	.can-access-menu > li {
		display:inline-block;
		margin: 0 10px;
		background-color: #36BA98;
		color: white;
		border-radius: 10px;
	}
	
	.can-access-menu > li > a {
		display: block;
		padding: 5px 10px;
	}
	
	.details {
		font-size: 1.5rem;
		color: #36BA98;
	}
	
	</style>