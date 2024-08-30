<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="JOIN"></c:set>
<%@ include file="../common/head.jspf"%>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/lodash.js/4.17.21/lodash.min.js"></script>
	<!-- JS -->
	<script>
	let validLoginId = "";
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
			alert('아이디를 입력해주세요.');
			return;
		}
		if (form.loginId.value != validLoginId) {
			alert('사용할 수 없는 아이디입니다.');
			form.loginId.focus();
			return;
		}
		if (validLoginId == form.loginId.value) {
			return;
		}
		if(loginPw.length == 0) {
			alert('비밀번호를 입력해주세요.');
			return;
		}
		if(loginPwConfirm.length == 0) {
			alert('비밀번호 확인을 입력해주세요.');
			return;
		}
		
		if(loginPw != loginPwConfirm) {
			alert('비밀번호가 일치하지 않습니다.');
			form.loginPw.focus();
			return;
		}
		
		if(name.length == 0) {
			alert('이름을 입력해주세요.');
			return;
		}
		
		if(nickname.length == 0) {
			alert('닉네임을 입력해주세요.');
			return;
		}
		
		if(cellphoneNum.length == 0) {
			alert('휴대폰 번호를 입력해주세요.');
			return;
		}
		
		if(email.length == 0) {
			alert('이메일을 입력해주세요.');
			return;
		}
		
		//다 확인했으면 그냥 여기서 submit
		submitJoinFormDone = true;
		form.submit();
	}
	
	function checkLoginIdDup(el) {
		$('.checkDup-msg').empty();
		const form = $(el).closest('form').get(0);
		if (form.loginId.value.length == 0) {
			validLoginId = '';
			return;
		}
		$.get('../member/getLoginIdDup', {
			isAjax : 'Y',
			loginId : form.loginId.value
		}, function(data) {
			$('.checkDup-msg').html('<div class="">' + data.msg + '</div>')
			if (data.success) {
				validLoginId = data.data1;
			} else {
				validLoginId = '';
			}
		}, 'json');
	}
// 	checkLoginIdDup(); // 매번 실행
	const checkLoginIdDupDebounced = _.debounce(checkLoginIdDup, 600); // 실행 빈도 조절
	
	</script>

	<form onsubmit="JoinForm__submit(this); return false;" style="font-size: 1.5rem;" action="doJoin" method="POST">
		<div style="margin-bottom: 5px;">
			<label>아이디 : </label>
			<input class="input input-bordered input-sm" onkeyup="checkLoginIdDupDebounced(this);" type="text" autocomplete="off" name="loginId"/>
		</div>
		<div style="margin-bottom: 10px;" class="checkDup-msg"></div>
		<div style="margin-bottom: 10px;">
			<label>비밀번호 : </label>
			<input class="input input-bordered input-sm" type="password" autocomplete="off" name="loginPw"/>
		</div>
		<div style="margin-bottom: 10px;">
			<label>비밀번호 확인 : </label>
			<input class="input input-bordered input-sm" type="password" autocomplete="off" name="loginPwConfirm"/>
		</div>
		<div style="margin-bottom: 10px;">
			<label>이름 : </label>
			<input class="input input-bordered input-sm" type="text" autocomplete="off" name="name"/>
		</div>
		<div style="margin-bottom: 10px;">
			<label>닉네임 : </label>
			<input class="input input-bordered input-sm" type="text" autocomplete="off" name="nickname"/>
		</div>
		<div style="margin-bottom: 10px;">
			<label>휴대폰번호 : </label>
			<input class="input input-bordered input-sm" type="tel" autocomplete="off" name="cellphoneNum" pattern="[0-9]{3}-[0-9]{4}-[0-9]{4}" required/>
		</div>
		<div style="margin-bottom: 10px;">
			<label>이메일 : </label>
			<input class="input input-bordered input-sm" type="email" autocomplete="off" name="email"/>
		</div>
		<div>
			<input style="cursor: pointer; background-color:#36BA98; color: white; padding: 5px 10px; margin-top: 20px; border-radius: 10px;" type="submit" value="회원가입">
		</div>
	</form>
	
	<br />
	
	<div><button onclick="history.back();">▶ 뒤로가기</button></div>


<%@ include file="../common/foot.jspf"%>

