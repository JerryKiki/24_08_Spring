<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
</head>
<body>
	<div style="font-size: 3rem; font-weight: bold; margin: 10px; margin-bottom:20px; color: #FF4E88">${Oldarticle.id }번 게시물 수정</div>
	
	<form action="doModify?id=${Oldarticle.id }">
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
			<input type='submit' value='수정'>
		</div>
	</form>
	
	<br />
	
	<div><a href="getArticles">▶ 리스트로 돌아가기</a></div>
	
</body>
</html>

	<!-- CSS -->
	
	<style type="text/css">
	
	@font-face {
    	font-family: 'MaplestoryOTFBold';
    	src: url('https://fastly.jsdelivr.net/gh/projectnoonnu/noonfonts_20-04@2.1/MaplestoryOTFBold.woff') format('woff');
    	font-weight: normal;
    	font-style: normal;
	}
	
	@font-face {
    	font-family: 'Pretendard-Regular';
    	src: url('https://fastly.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
    	font-weight: 400;
    	font-style: normal;
	}
	
	html {
		margin: 0;
		padding: 0;
		background-color: white;
	
	}
	
	body {
		font-family: 'MaplestoryOTFBold';
		font-size: 1.5rem;
		margin: 0;
		padding: 20px;
		width: 60vw;
		height: 100vh;
		margin-left: auto;
		margin-right: auto;
		text-align: center;
		display: flex;
		flex-direction: column;
		align-items: center;
		background-color: #FFDA76;
		color: #36BA98;
		
		box-sizing: border-box;
	}
	
	a {
		color: #FF8C9E;
		text-decoration: none;
		font-size: 1.5rem;
	}
	
	a:hover {
		color: #FF4E88;
		text-decoration: underline;
	}
	
	.details {
		font-size: 1.5rem;
	}
	
	</style>