<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="비밀번호 체크"></c:set>
<%@ include file="../common/head.jspf"%>


	<form onsubmit="modifyForm__submit(this); return false;" style="font-size: 1.5rem;" action="memberModify">
		<input type="hidden" value="${loginedMember.id }" name="memberId" />
		<div>아이디 : ${loginedMember.loginId }</div>
		<div>
			<label>비밀번호 확인 : </label>
			<input type="password" name="confirmPw" autocomplete="off"/>
		</div>
		<div>
			<input style="cursor: pointer; background-color:#36BA98; color: white; padding: 5px 10px; margin-top: 20px; border-radius: 10px;" type="submit" value="확인">
		</div>
	</form>

<%@ include file="../common/foot.jspf"%>

	<style type="text/css">

	.confirm-btn > li {
		display:inline-block;
		margin: 0 10px;
		background-color: #36BA98;
		color: white;
		border-radius: 10px;
	}
	
	.confirm-btn > li > a {
		display: block;
		padding: 5px 10px;
	}
	
	</style>
	
	<!-- JS -->
	<script type="text/javascript">
	
		function modifyForm__submit(form) {
			console.log("form.confirmPw.value : " + form.confirmPw.value);
			
			let confirmPw = form.confirmPw.value.trim();

			if(confirmPw.length == 0) {
				alert('비밀번호를 입력해주세요.');
				return;
			}
			
			//다 확인했으면 그냥 여기서 submit
			form.submit();
		}
	
	</script>