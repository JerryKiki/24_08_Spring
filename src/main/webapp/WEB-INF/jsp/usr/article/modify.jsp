<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${Oldarticle.id }번 게시글 수정"></c:set>
<%@ include file="../common/head.jspf"%>
	
	<form onsubmit="modifyForm__submit(this); return false;" style="font-size: 1.5rem;" action="doModify">
		<input type="hidden" value="${Oldarticle.id }" name="id" />
		<div>
			<div>기존 제목 : ${Oldarticle.title }</div>
			<label>새 제목 : </label>
			<input type="text" name="title"/>
		</div>
		<div>
		<div>기존 내용 : ${Oldarticle.body }</div>
		<label>새 내용 : </label>
		<input type="text" name="body"/>
		</div>
		<div>
			<input style="cursor: pointer; background-color:#36BA98; color: white; padding: 5px 10px; margin-top: 20px; border-radius: 10px;" type="submit" value="수정 완료">
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
	
		function modifyForm__submit(form) {
			console.log("form.title.value : " + form.title.value);
			console.log("form.body.value : " + form.body.value);
			
			let title = form.title.value.trim();
			let body = form.body.value.trim();

			if(title.length == 0 && body.length == 0) {
				alert('title과 body 둘 중 하나는 입력해야 합니다.');
				return;
			}
			
			//다 확인했으면 그냥 여기서 submit
			form.submit();
		}
	
	</script>