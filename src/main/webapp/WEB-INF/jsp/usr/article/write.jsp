<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="게시글 작성"></c:set>
<%@ include file="../common/head.jspf"%>
<%@ include file="../common/toastUiEditorLib.jspf"%>
	
	<form onsubmit="wirteForm__submit(this); return false;" style="font-size: 1.5rem;" action="doWrite" method="POST">
		<input type="hidden" name="body" />
		<div>
			<label>게시판 : </label>
			<select name="boardId" id="board">
				<option value="select">Select a board</option>
				<option value="1">notice</option>
				<option value="2">free</option>
				<option value="3">QnA</option>
			</select>
		</div>
		<div style="margin-bottom: 10px;">
			<label>제목 : </label>
			<input class="input input-bordered input-sm" type="text" autocomplete="off" name="title"/>
		</div>
		<div>
			<div class="toast-ui-editor" style="background-color:white;">
				<script type="text/x-template"></script>
			</div>
		</div>
		<div>
			<input style="cursor: pointer; background-color:#36BA98; color: white; padding: 5px 20px; margin-top: 20px; border-radius: 10px;" type="submit" value="작성">
		</div>
	</form>
	
	<br />
	
	<div><button onclick="location.href = document.referrer;">▶ 뒤로가기</button></div>
	
<%@ include file="../common/foot.jspf"%>

	<!-- CSS -->
	
	<style type="text/css">

	</style>
	
	<!-- JS -->
	<script type="text/javascript">
	
		function wirteForm__submit(form) {
			console.log("form.boardId.value : " + form.boardId.value);
			console.log("form.title.value : " + form.title.value);
			console.log("form.body.value : " + form.body.value);
			
			let title = form.title.value.trim();
			let body = form.body.value.trim();
			let boardId = form.boardId.value;
			
			if(boardId == "select") {
				alert('게시판을 선택하세요.');
				return;
			}

			if(title.length == 0) {
				alert('제목을 입력하세요.');
				return;
			}
			
			const editor = $(form).find('.toast-ui-editor').data('data-toast-editor');
			const markdown = editor.getMarkdown().trim();
			if (markdown.length == 0) {
				alert('내용을 입력하세요.');
				return;
			}
			
			form.body.value = markdown;
			form.submit();
		}
	
	</script>