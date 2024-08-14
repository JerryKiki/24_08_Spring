<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="게시글 작성"></c:set>
<%@ include file="../common/head.jspf"%>
	
	<form onsubmit="wirteForm__submit(this); return false;" style="font-size: 1.5rem;" action="doWrite">
		<div style="margin-bottom: 10px;">
			<label>제목 : </label>
			<input type="text" autocomplete="off" name="title"/>
		</div>
		<div>
		<label>내용 : </label>
		<input type="text" autocomplete="off" name="body"/>
		</div>
		<div>
			<input style="cursor: pointer; background-color:#36BA98; color: white; padding: 5px 20px; margin-top: 20px; border-radius: 10px;" type="submit" value="작성">
		</div>
	</form>
	
	<br />
	
	<div><button onclick="history.back();">▶ 뒤로가기</button></div>
	
<%@ include file="../common/foot.jspf"%>

	<!-- CSS -->
	
	<style type="text/css">

	</style>
	
	<!-- JS -->
	<script type="text/javascript">
	
		function wirteForm__submit(form) {
			console.log("form.title.value : " + form.title.value);
			console.log("form.body.value : " + form.body.value);
			
			let title = form.title.value.trim();
			let body = form.body.value.trim();

			if(title.length == 0) {
				alert('제목을 입력하세요.');
				return;
			}
			
			if(body.length == 0) {
				alert('내용을 입력하세요.');
				return;
			}
			
			//다 확인했으면 그냥 여기서 submit
			form.submit();
		}
	
	</script>