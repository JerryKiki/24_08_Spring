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
		<script>alert('로그인 후에만 이용할 수 있습니다.'); location.replace('getArticles');</script>
	</c:if>
	<c:if test="${noArticle}">
		<script>alert('유효하지 않은 게시글 번호입니다.'); location.replace('getArticles');</script>
	</c:if>
	<c:if test="${cannotModify}">
		<script>alert('해당 게시글에 대한 권한이 없습니다.'); location.replace('getArticles');</script>
	</c:if>
	<c:if test="${articleModified}">
		<script>alert('게시글 수정이 완료되었습니다.'); location.replace('getArticles');</script>
	</c:if>
</body>
</html>