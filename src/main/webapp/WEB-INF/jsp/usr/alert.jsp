<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alert</title>
</head>
<body>
	<c:if test="${isntLogined}">
		<script>alert('로그인 후에만 이용할 수 있습니다.'); location.replace('../member/doLogin');</script>
	</c:if>
	<c:if test="${noArticle}">
		<script>alert('유효하지 않은 게시글 번호입니다.'); history.back();</script>
	</c:if>
	<c:if test="${cannotAccess}">
		<script>alert('해당 게시글에 대한 권한이 없습니다.'); history.back();</script>
	</c:if>
	<c:if test="${articleModified}">
		<script>alert('게시글 수정이 완료되었습니다.'); location.replace('getArticles');</script>
	</c:if>
	<c:if test="${articleDeleted}">
		<script>alert('게시글 삭제가 완료되었습니다.'); location.replace('getArticles');</script>
	</c:if>
	<c:if test="${writeSuccess}">
		<script>alert('게시글이 작성되었습니다.'); location.replace('getArticles');</script>
	</c:if>
	<c:if test="${alreadyLogined}">
		<script>alert('이미 로그인되어 있습니다.'); history.back();</script>
	</c:if>
	<c:if test="${memberNotExists}">
		<script>alert('존재하지 않는 아이디입니다.'); history.back();</script>
	</c:if>
	<c:if test="${pwMismatch}">
		<script>alert('비밀번호가 틀립니다.'); history.back();</script>
	</c:if>
	<c:if test="${loginSuccess}">
		<script>alert('로그인 성공!'); location.replace('/');</script>
	</c:if>
	<c:if test="${alreadyLogouted}">
		<script>alert('이미 로그아웃 상태입니다.'); history.back();</script>
	</c:if>
	<c:if test="${logoutSuccess}">
		<script>alert('로그아웃 성공!'); location.replace('/');</script>
	</c:if>
	<c:if test="${joinSuccess}">
		<script>alert('회원가입 성공!'); location.replace('doLogin');</script>
	</c:if>
	<c:if test="${idDup}">
		<script>alert('이미 사용중인 아이디입니다.'); history.back();</script>
	</c:if>
	<c:if test="${memberDup}">
		<script>alert('이미 가입된 이름과 이메일입니다.'); history.back();</script>
	</c:if>
</body>
</html>