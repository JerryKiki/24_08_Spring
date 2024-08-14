<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="LOGIN"></c:set>
<%@ include file="../common/head.jspf"%>

	<form onsubmit="LoginForm__submit(this); return false;" style="font-size: 1.5rem;" action="doLogin">
		<div style="margin-bottom: 10px;">
			<label>아이디 : </label>
			<input type="text" name="loginId"/>
		</div>
		<div>
		<label>비밀번호 : </label>
		<input type="password" autocomplete="off" name="loginPw"/>
		</div>
		<div>
			<input style="cursor: pointer; background-color:#36BA98; color: white; padding: 5px 20px; margin-top: 20px; border-radius: 10px;" type="submit" value="로그인">
		</div>
	</form>
	
	<br />
	
	<div><button onclick="history.back();">▶ 뒤로가기</button></div>


<%@ include file="../common/foot.jspf"%>

<!-- JS -->
	<script type="text/javascript">
	
		function LoginForm__submit(form) {
			console.log("form.loginId.value : " + form.loginId.value);
			console.log("form.loginPw.value : " + form.loginPw.value);
			
			let loginId = form.loginId.value.trim();
			let loginPw = form.loginPw.value.trim();

			if(loginId.length == 0) {
				alert('아이디를 입력해주세요');
				return;
			}
			if(loginPw.length == 0) {
				alert('비밀번호를 입력해주세요');
				return;
			}
			
			//다 확인했으면 그냥 여기서 submit
			form.submit();
		}
	
	</script>
