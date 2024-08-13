<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="/resource/common.css" />
<script src="/resource/common.js"></script>
<title>게시글 상세보기</title>
</head>
<body>
	<div style="font-size: 3rem; font-weight: bold; margin: 10px; margin-bottom:20px; color: #FF4E88">Article Detail</div>
	
	<div class="details">
		<div>번호 : ${article.id }</div>
		<div>날짜 : ${article.regDate.substring(0,10) }</div>
		<div>제목 : ${article.title }</div>
		<div>내용 : ${article.body }</div>
		<div>작성자 : ${article.nickname }</div>
	</div>
	
	<br />
	
	<div><a href="getArticles">▶ 리스트로 돌아가기</a></div>
	
</body>
</html>

	<!-- CSS -->
	
	<style type="text/css">
	
	.details {
		font-size: 1.5rem;
		color: #36BA98;
	}
	
	</style>