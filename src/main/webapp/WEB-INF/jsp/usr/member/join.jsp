<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="JOIN"></c:set>
<%@ include file="../common/head.jspf"%>

	<form onsubmit="JoinForm__submit(this); return false;" style="font-size: 1.5rem;" action="doJoin">
		<div style="margin-bottom: 10px;">
			<label>아이디 : </label>
			<input type="text" autocomplete="off" name="loginId"/>
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
			<input type="text" autocomplete="off" name="name"/>
		</div>
		<div style="margin-bottom: 10px;">
			<label>닉네임 : </label>
			<input type="text" autocomplete="off" name="nickname"/>
		</div>
		<div style="margin-bottom: 10px;">
			<label>휴대폰번호 : </label>
			<input type="tel" autocomplete="off" name="cellphoneNum" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" required/>
		</div>
		<div style="margin-bottom: 10px;">
			<label>이메일 : </label>
			<input type="email" autocomplete="off" name="email"/>
		</div>
		<div>
			<input style="cursor: pointer; background-color:#36BA98; color: white; padding: 5px 10px; margin-top: 20px; border-radius: 10px;" type="submit" value="회원가입">
		</div>
	</form>
	
	<br />
	
	<div><button onclick="history.back();">▶ 뒤로가기</button></div>


<%@ include file="../common/foot.jspf"%>

<!-- JS -->
	<script type="text/javascript">
	
	function JoinForm__submit(form) {
		console.log("form.loginId.value : " + form.loginId.value);
		console.log("form.loginPw.value : " + form.loginPw.value);
		console.log("form.loginPwConfirm.value : " + form.loginPwConfirm.value);
		console.log("form.name.value : " + form.name.value);
		console.log("form.nickname.value : " + form.nickname.value);
		console.log("form.cellphoneNum.value : " + form.cellphoneNum.value);
		console.log("form.email.value : " + form.email.value);
		
		let loginId = form.loginId.value.trim();
		let loginPw = form.loginPw.value.trim();
		let loginPwConfirm = form.loginPwConfirm.value.trim();
		let name = form.name.value.trim();
		let nickname = form.nickname.value.trim();
		let cellphoneNum = form.cellphoneNum.value.trim();
		let email = form.email.value.trim();
		
		if(loginId.length == 0) {
			alert('아이디를 입력해주세요');
			return;
		}
		if(loginPw.length == 0) {
			alert('비밀번호를 입력해주세요');
			return;
		}
		if(loginPwConfirm.length == 0) {
			alert('비밀번호 확인을 입력해주세요');
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