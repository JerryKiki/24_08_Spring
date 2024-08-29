<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="My Page"></c:set>
<%@ include file="../common/head.jspf"%>

	<div class="myInfo">
		<div>회원번호 : ${loginedMember.id }</div>
		<div>가입날짜 : ${loginedMember.regDate.substring(0,10) }</div>
		<div>아이디 : ${loginedMember.loginId }</div>
		<div>이름 : ${loginedMember.name }</div>
		<div>닉네임 : ${loginedMember.nickname }</div>
		<div>전화번호 : ${loginedMember.cellphoneNum }</div>
		<div>이메일 : ${loginedMember.email }</div>
	</div>
	
	<ul class="modify-btn flex items-center flex-row mx-auto" style="font-size: 1.2rem; margin-top: 20px;">
		<li><a href="javascript:void(0);" onclick="if(confirm('수정하시겠습니까?')) window.location.href='confirmPw?memberId=${loginedMember.id}';">수정</a></li>
	</ul>

<%@ include file="../common/foot.jspf"%>


<!-- CSS -->
	
	<style type="text/css">
	
	.myInfo {
		font-size: 1.5rem;
		color: #36BA98;
	}
	
	.modify-btn > li {
		display:inline-block;
		margin: 0 10px;
		background-color: #36BA98;
		color: white;
		border-radius: 10px;
	}
	
	.modify-btn > li > a {
		display: block;
		padding: 5px 10px;
	}
	
	</style>