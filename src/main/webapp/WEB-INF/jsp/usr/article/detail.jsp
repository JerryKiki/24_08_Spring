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
	
	<script>
	    function ArticleList__doUpdateAfterLike(articleId, likePoint) {
	        $.get('../article/doLikeArticle', {
	            id: articleId,
	            point: likePoint,
	            ajaxMode: 'Y'
	        }, function(data) {
	            console.log(data);
	            console.log(data.resultCode);
	            
	            if (data.resultCode.startsWith('S-')) {
	                console.log('Updated Info:', data.data1);
	                const newInfo = data.data1;
	                const likeIcon = $(`#likeIcon-` + articleId);
	                const dislikeIcon = $(`#dislikeIcon-` + articleId);
	                
	                if(data.resultCode == 'S-1') {
	                	 if (newInfo.hasOwnProperty(articleId)) {
	                        likeIcon.text('LIKE ♥');
	                      } else {
	                      	likeIcon.text('LIKE ♡');
	                      }
	                } else if(data.resultCode == 'S-2') {
	                	if (newInfo.hasOwnProperty(articleId)) {
	                        likeIcon.text('LIKE ♥');
	                        dislikeIcon.text('DISLIKE ▽');
	                     } else {
	                     	likeIcon.text('LIKE ♡');
	                     	dislikeIcon.text('DISLIKE ▼');
	                     }
	                } else if(data.resultCode == 'S-3') {
	                	if (newInfo.hasOwnProperty(articleId)) {
	                		dislikeIcon.text('DISLIKE ▼');
	                	} else {
	                		dislikeIcon.text('DISLIKE ▽');
	                	}
	                } else if(data.resultCode == 'S-4') {
	                	if (newInfo.hasOwnProperty(articleId)) {
	                		dislikeIcon.text('DISLIKE ▼');
	                		likeIcon.text('LIKE ♡');
	                	} else {
	                		dislikeIcon.text('DISLIKE ▽');
	                		likeIcon.text('LIKE ♥');
	                	}
	                }              
	                ArticleList__doUpdateLikeCount(articleId);
	            } else if (data.resultCode.startsWith('F-1')) {
	                alert('존재하지 않는 게시글입니다.');
	            } else if (data.resultCode.startsWith('F-2')) {
	                alert('자신의 게시글에는 좋아요할 수 없습니다.');
	            } else if (data.resultCode.startsWith('F-A')) {
	                alert('로그인 후 이용해주세요.');
	            }
	        }, 'json').fail(function(jqXHR, textStatus, errorThrown) {
	            console.error('AJAX request failed:', textStatus, errorThrown);
	            alert('서버 요청이 실패했습니다.');
	        });
	    }
	    
	    function ArticleList__doUpdateLikeCount(articleId) {
	    	$.get('../article/doGetLikeCount', {
	    		id: articleId,
	    		ajaxMode: 'Y'
	    	}, function(data) {
	    		console.log(data)
	    		const likeCount = data;
	    		$(`#likeCount-` + articleId).text("좋아요 : " + likeCount);
	    		
	    	}, 'json')
	    }
	</script>
	
	<div class="details">
		<div>번호 : ${article.id }</div>
		<div>날짜 : ${article.regDate.substring(0,10) }</div>
		<div>제목 : ${article.title }</div>
		<div>내용 : ${article.body }</div>
		<div>작성자 : ${article.nickname }</div>
		<div><span class="article-detail__view-count">조회수 : ${article.view }</span></div>
		<div><span id="likeCount-${article.id}">좋아요 : ${article.like }</span></div>
	</div>
	
	<br />
	
	<c:if test="${canAccess }">
		<ul class="can-access-menu flex items-center flex-row mx-auto" style="font-size: 1.2rem;">
			<li><a href="doModify?id=${article.id }">수정</a></li>
			<li><a href="doDelete?id=${article.id }">삭제</a></li>
		</ul>
	</c:if>
	<c:if test="${!canAccess }">
		<div class="like_dislike_btns flex items-center flex-row mx-auto">
			<div class="likes btn" style="background-color: #36BA98; color: white; font-weight: bold; font-size: 1.4rem; margin-right: 5px;" onclick="ArticleList__doUpdateAfterLike(${article.id}, 1);">
				<c:choose>
					<c:when test="${likeInfo[article.id]}">
						<span id="likeIcon-${article.id}">LIKE ♥</span>
					</c:when>
					<c:otherwise>
						<span id="likeIcon-${article.id}">LIKE ♡</span>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="dislikes btn" style="background-color: #36BA98; color: white; font-weight: bold; font-size: 1.4rem; margin-left: 5px;" onclick="ArticleList__doUpdateAfterLike(${article.id}, -1);">
				<c:choose>
					<c:when test="${dislikeInfo[article.id]}">
						<span id="dislikeIcon-${article.id}">DISLIKE ▼</span>
					</c:when>
					<c:otherwise>
						<span id="dislikeIcon-${article.id}">DISLIKE▽</span>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
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