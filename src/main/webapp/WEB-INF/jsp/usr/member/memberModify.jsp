<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="회원정보 수정"></c:set>
<%@ include file="../common/head.jspf"%>

	<form onsubmit="ModifyForm__submit(this); return false;" style="font-size: 1.5rem;" action="doUpdateMemberInfo">
		<input type="hidden" value="${loginedMember.id }" name="memberId" />
		<div style="margin-bottom: 10px;">
			가입일시 : ${loginedMember.regDate }
		</div>
		<div style="margin-bottom: 10px;">
			아이디 : ${loginedMember.loginId }
		</div>
		<div style="margin-bottom: 10px;">
			<label>비밀번호 : </label>
			<input type="password" autocomplete="off" name="loginPw"/>
		</div>
		<div style="margin-bottom: 10px;">
			<label>비밀번호 확인 : </label>
			<input type="password" autocomplete="off" name="loginPwConfirm"/>
		</div>
		<div style="margin-bottom: 10px;">
			<label>이름 : </label>
			<input type="text" autocomplete="off" name="name" value="${loginedMember.name }"/>
		</div>
		<div style="margin-bottom: 10px;">
			<label>닉네임 : </label>
			<input type="text" autocomplete="off" name="nickname" value="${loginedMember.nickname }"/>
		</div>
		<div style="margin-bottom: 10px;">
			<label>휴대폰번호 : </label>
			<input type="tel" autocomplete="off" name="cellphoneNum" value="${loginedMember.cellphoneNum }" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" required/>
		</div>
		<div style="margin-bottom: 10px;">
			<label>이메일 : </label>
			<input type="email" autocomplete="off" value="${loginedMember.email }" name="email"/>
		</div>
		<div>
			<input style="cursor: pointer; background-color:#36BA98; color: white; padding: 5px 10px; margin-top: 20px; border-radius: 10px;" type="submit" value="수정">
		</div>
	</form>

<%@ include file="../common/foot.jspf"%>

<!-- JS -->
	<script type="text/javascript">
	
	function ModifyForm__submit(form) {
		console.log("form.loginPw.value : " + form.loginPw.value);
		console.log("form.loginPwConfirm.value : " + form.loginPwConfirm.value);
		console.log("form.name.value : " + form.name.value);
		console.log("form.nickname.value : " + form.nickname.value);
		console.log("form.cellphoneNum.value : " + form.cellphoneNum.value);
		console.log("form.email.value : " + form.email.value);
		
		let loginPw = form.loginPw.value.trim();
		let loginPwConfirm = form.loginPwConfirm.value.trim();
		let name = form.name.value.trim();
		let nickname = form.nickname.value.trim();
		let cellphoneNum = form.cellphoneNum.value.trim();
		let email = form.email.value.trim();
		
		if(loginPw.length != 0 && loginPwConfirm.length == 0) {
			alert('비밀번호를 수정하시려면, 비밀번호 확인을 입력해주세요');
			return;
		}
		
		if(loginPw != loginPwConfirm) {
			alert('비밀번호가 일치하지 않습니다.');
			form.loginPw.focus();
			return;
		}
		
		if(name.length == 0) {
			alert('이름을 입력해주세요');
			return;
		}
		
		if(nickname.length == 0) {
			alert('닉네임을 입력해주세요');
			return;
		}
		
		if(cellphoneNum.length == 0) {
			alert('휴대폰 번호를 입력해주세요');
			return;
		}
		
		if(email.length == 0) {
			alert('이메일을 입력해주세요');
			return;
		}
		
		//다 확인했으면 그냥 여기서 submit
		form.submit();
	}
	
	</script>