<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${article.id }번 게시글 상세"></c:set>
<%@ include file="../common/head.jspf"%>

	<!-- JS -->
	
	<!-- <iframe src="http://localhost:8080/usr/article/doIncreaseHitCount?id=757" frameborder="0"></iframe> -->
	<!-- 파라미터 -->
	<script>
	    const params = {};
	    params.id = parseInt('${param.id}');
	</script>
	
	<!-- 조회수 -->
	<script>
	    function ArticleDetail__doIncreaseViewCount() {
	        const localStorageKey = 'article__' + params.id + '__alreadyOnView';
	
	        if (localStorage.getItem(localStorageKey)) {
	            return;
	        }
	
	        localStorage.setItem(localStorageKey, true);
	
	        $.get('../article/doIncreaseViewCountRd', {
	            id: params.id,
	            ajaxMode: 'Y'
	        }, function(data) {
	            console.log(data);
	            console.log(data.data1);
	            $('.article-detail__view-count').empty().html("조회수 : " + data.data1);
	        }, 'json'); // 세미콜론 추가
	    }
	
	    $(function() {
	        //ArticleDetail__doIncreaseHitCount();
	        setTimeout(ArticleDetail__doIncreaseViewCount, 1000);
	    });
	</script>
	
	<!-- 좋아요/싫어요 -->
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
	
	                if (data.resultCode == 'S-1') {
	                    if (newInfo.hasOwnProperty(articleId)) {
	                        likeIcon.text('LIKE ♥');
	                    } else {
	                        likeIcon.text('LIKE ♡');
	                    }
	                } else if (data.resultCode == 'S-2') {
	                    if (newInfo.hasOwnProperty(articleId)) {
	                        likeIcon.text('LIKE ♥');
	                        dislikeIcon.text('DISLIKE ▽');
	                    } else {
	                        likeIcon.text('LIKE ♡');
	                        dislikeIcon.text('DISLIKE ▼');
	                    }
	                } else if (data.resultCode == 'S-3') {
	                    if (newInfo.hasOwnProperty(articleId)) {
	                        dislikeIcon.text('DISLIKE ▼');
	                    } else {
	                        dislikeIcon.text('DISLIKE ▽');
	                    }
	                } else if (data.resultCode == 'S-4') {
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
	                if (confirm('로그인 후 이용 가능합니다. 로그인 하시겠습니까?')) {
	                    // 로그인 페이지에 현재 페이지의 정보를 포함해서 보내기
	                    location.href = "../member/doLogin?url=" + encodeURIComponent(window.location.href); // 암호화까지!
	                }
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
	            console.log(data);
	            const likeCount = data;
	            $(`#likeCount-` + articleId).text("좋아요 : " + likeCount);
	        }, 'json'); // 세미콜론 추가
	    }
	</script>

	<!-- 댓글 작성 -->
	<script>
		function replyForm__submit(form) {
			console.log("form.relId.value : " + form.relTypeCode.value);
			console.log("form.relId.value : " + form.relId.value);
			console.log("form.memberId.value : " + form.memberId.value);
			console.log("form.body.value : " + form.body.value);
			
			let relTypeCode = form.relTypeCode.value;
			let relId = form.relId.value;
			let memberId = form.memberId.value;
			let body = form.body.value.trim();
			
			if(memberId == NaN) {
				alert('로그인 후 이용할 수 있습니다.');
				return;
			}
			
			if(body.length == 0) {
				alert('내용을 입력하세요.');
				return;
			}
			
			//submit대신 ajax로 새로고침요청
			ReloadPage__After__MakeReply(relTypeCode, relId, memberId, body);
		}
		
		function ReloadPage__After__MakeReply(relTypeCode, relId, memberId, body) {
		    $.get('../reply/writeReply', {
		    	relTypeCode: relTypeCode,
		    	relId: relId,
		        memberId: memberId,
		        body: body,
		        ajaxMode: 'Y'
		    }, function(data) {
		        console.log(data);
		        console.log(data.data1);
		        const newReply = data.data1;
		        addReplyToTable(newReply);
		        initFormBody();
		    }, 'json');
		}
		
		function addReplyToTable(reply) {
		    // tbody 요소 선택
		    const $tbody = $('.replies tbody');

		    // 새로운 tr 요소 생성
		    const $tr = $('<tr>').addClass('hover');

		    // 각 td 요소 생성 및 내용 추가
		    const $tdNickname = $('<td>').css('text-align', 'center').text(reply.nickname);
		    const $tdRegDate = $('<td>').css('text-align', 'center').text(reply.regDate.substring(0, 10));
		    const $tdBody = $('<td>').css('text-align', 'center').text(reply.body);
		    $tdBody.attr('id', 'replyBody-' + reply.id);
		    const $tdLike = $('<td>').css('text-align', 'center').text(reply.like);
		 	
		 // 수정 링크를 생성
	        const modifyHref = 'acitveModifyForm(' + reply.id + ', \'${loginedMemberId}\', this); return false;';
	        const $tdModify = $('<td>').css('text-align', 'center')
	            .append($('<a>').attr('href', '#')
	            .attr('onclick', modifyHref)
	            .text('수정'));

	        // 삭제 링크를 생성
	        const deleteHref = 'doDeleteReply(' + reply.id + ', \'${loginedMemberId}\', this); return false;';
	        const $tdDelete = $('<td>').css('text-align', 'center')
	            .append($('<a>').attr('href', '#')
	            .attr('onclick', deleteHref)
	            .text('삭제'));

		    // td 요소를 tr에 추가
		    $tr.append($tdNickname, $tdRegDate, $tdBody, $tdLike, $tdModify, $tdDelete);
		    
		    // tr 요소를 tbody에 추가
		    $tbody.append($tr);

		    // no_reply_row 클래스를 가진 행이 존재하면 삭제
		    $('.repliesTable').find('.no_reply_row').remove();
		}
		
		function initFormBody() {
			const $bodyInput = $('.reply_body_input');
			
			$bodyInput.val('');
		}
	</script>
	
	<!-- 댓글삭제 -->
	<script>
	function doDeleteReply(replyId, memberId, reply) {
	    if (confirm("삭제하시겠습니까?")) {
	        $.get('../reply/deleteReply', {
	            id: replyId,
	            memberId: memberId,
	            ajaxMode: 'Y'
	        }, function(data) {
	            console.log(data);
	            console.log(data.data1);
	
	            if (data.resultCode.startsWith('F-')) {
	                alert(data.msg);
	            } else if (data.resultCode === 'S-1') {
	                alert("삭제되었습니다.");
	                // 삭제 버튼의 td 요소를 포함하는 tr을 삭제
	                $(reply).closest('tr').remove();
	                
	                // tbody 요소 선택
	                const $tbody = $('.repliesTable tbody');
	                
	                // tbody에 tr 요소가 하나도 남아있지 않으면 "아직 댓글이 없습니다." 메시지 추가
	                if ($tbody.find('tr').length === 0) {
	                    const $noReplyRow = $('<tr>').attr('class', 'no_reply_row')
	                        	.append($('<td>')
	                            .attr('colspan', '6')
	                            .css('text-align', 'center')
	                            .text('아직 댓글이 없습니다.')
	                        );
	                    $tbody.append($noReplyRow);
	                }
	            }
	        }, 'json');
	    }
	}
	</script>

	
	<!-- 댓글수정 -->
	<script>
	function acitveModifyForm(replyId, memberId, reply) {
	    let modifyForm = document.modifyReplyForm;
	    let $modifyReply = $('.modifyReply');
	    let $currentRow = $(reply).closest('tr');
	    
	    // 현재 열려 있는 수정 폼이 현재 댓글의 폼이 아닌 경우 초기화
	    if (!$modifyReply.hasClass('hidden') && modifyForm.id.value != replyId) {
	        initAndHideModifyForm(); // 기존 폼을 숨기고 초기화
	    }
	    
	    // 현재 댓글의 폼이 숨겨져 있는 경우
	    if ($modifyReply.hasClass('hidden')) {
	        modifyForm.id.value = replyId;
	        modifyForm.memberId.value = memberId;
	        $currentRow.addClass('modifing');
	        $modifyReply.removeClass('hidden');
	    } else {
	        // 이미 수정 폼이 보이는 상태에서 동일 버튼 클릭 시 폼을 숨깁니다.
	        modifyForm.id.value = 0;
	        modifyForm.body.value = '';
	        $currentRow.removeClass('modifing');
	        $modifyReply.addClass('hidden');
	    }
	}
	
	function modifyForm__submit(form) {
		console.log("form.id.value : " + form.id.value);
		console.log("form.memberId.value : " + form.memberId.value);
		console.log("form.body.value : " + form.body.value);
		
		let replyId = form.id.value;
		let memberId = form.memberId.value;
		let body = form.body.value.trim();
		
		if(isNaN(memberId)) {
			alert('로그인 후 이용할 수 있습니다.');
			return;
		}
		
		if(body.length == 0) {
			alert('내용을 입력하세요.');
			return;
		}
		
		//submit대신 ajax로 새로고침요청
		ReloadPage__After__ModifyReply(replyId, memberId, body);
	}
	
	function ReloadPage__After__ModifyReply(replyId, memberId, body) {
	    $.get('../reply/modifyReply', {
	    	id: replyId,
	        memberId: memberId,
	        body: body,
	        ajaxMode: 'Y'
	    }, function(data) {
	        console.log(data);
	        console.log(data.data1);
	        const modifiedReply = data.data1;
	        $('#replyBody-' + replyId).text(modifiedReply.body);
	        initAndHideModifyForm();
	    }, 'json');
	}
	
	function initAndHideModifyForm() {
		let modifyForm = document.modifyReplyForm;
		modifyForm.id.value = 0;
		modifyForm.body.value = '';
		$('.modifyReply').toggleClass('hidden');
		$('tr.modifing').removeClass('modifing');	
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
			<li><a href="javascript:void(0);" onclick="if(confirm('수정하시겠습니까?')) window.location.href='doModify?id=${article.id}';">수정</a></li>
			<li><a href="javascript:void(0);" onclick="if(confirm('삭제하시겠습니까?')) window.location.href='doDelete?id=${article.id}';">삭제</a></li>
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
						<span id="dislikeIcon-${article.id}">DISLIKE ▽</span>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</c:if>
	
	<div class="replies mx-auto w-3/4" style="margin-top: 20px;">
		<div style="text-align: left; font-size: 1.2rem">▶ 댓글</div>
		<table class="repliesTable table table-fixed border-collapse mx-auto" style="background-color: white; font-family: 'Pretendard-Regular'; font-size: 1rem; font-weight: bold;">
			<thead style="font-size: 1rem; font-weight: bold;">
				<tr>
					<th style="text-align: center;">Nickname</th>
					<th style="text-align: center;">Registration Date</th>
					<th style="text-align: center;">Body</th>
					<th style="text-align: center;">Popularity</th>
					<th style="text-align: center;">Modify</th>
					<th style="text-align: center;">Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${!noneReply}">
					<c:forEach var="reply" items="${replies}">
						<tr class="hover">
							<td style="text-align: center;">${reply.nickname}</td>
							<td style="text-align: center;">${reply.regDate.substring(0,10)}</td>
							<td style="text-align: center;" id="replyBody-${reply.id }">${reply.body }</td>
							<td style="text-align: center;">${reply.like }</td>
							<c:if test="${reply.memberId == loginedMember.id }">
								<td style="text-align: center;"><a href="#" onclick="acitveModifyForm('${reply.id}', '${loginedMember.id}', this); return false;">수정</a></td>
								<td style="text-align: center;"><a href="#" onclick="doDeleteReply('${reply.id}', '${loginedMember.id}', this); return false;">삭제</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${noneReply}">
					<tr class="no_reply_row" >
						<td colspan='6' style="text-align: center;">아직 댓글이 없습니다.</td>
					</tr>
				</c:if>
			</tbody>
		</table>
	</div>
	
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

	<div class="modifyReply mx-auto w-3/4 hidden" style="margin-top: 30px;">
		<div style="text-align: left">▶ Modify Reply</div>
		<form name="modifyReplyForm" onsubmit="modifyForm__submit(this); return false;" style="font-size: 1.4rem; width: 100%;" action="../reply/writeReply" class="flex justify-center items-center">
			<input type="hidden" value="0" name="id" />
			<input type="hidden" value="${loginedMember.id }" name="memberId" />
			<input class="reply_body_input input input-bordered input-sm" type="text" autocomplete="off" name="body" style="margin: 0 10px; width: 100%"/>
			<input style="cursor: pointer; background-color:#36BA98; color: white; padding: 2px 10px; border-radius: 10px; font-size:1.2rem;" type="submit" value="수정">
		</form>
	</div>
	
	<c:if test="${isLogined}">
		<div class="makeReply mx-auto w-3/4" style="margin-top: 30px;">
			<div style="text-align: left">▶ Make Reply</div>
			<form onsubmit="replyForm__submit(this); return false;" style="font-size: 1.4rem; width: 100%;" action="../reply/writeReply" class="flex justify-center items-center">
				<input type="hidden" value="1" name="relTypeCode" />
				<input type="hidden" value="${article.id }" name="relId" />
				<input type="hidden" value="${loginedMember.id }" name="memberId" />
				<input class="reply_body_input input input-bordered input-sm" type="text" autocomplete="off" name="body" style="margin: 0 10px; width: 100%"/>
				<input style="cursor: pointer; background-color:#36BA98; color: white; padding: 2px 10px; border-radius: 10px; font-size:1.2rem;" type="submit" value="등록">
			</form>
		</div>
	</c:if>
	
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
	
	tr.modifing {
		color: #FF4E88;
		text-decoration: underline;
	}
	
	</style>