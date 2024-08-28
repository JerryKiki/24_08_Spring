<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="API TEST"></c:set>

<script>
	const API_KEY = '깃허브 업로드를 위해 삭제';
	
	async function getCData() {
		const url = 'https://api.odcloud.kr/api/15075891/v1/uddi:7cc7242f-7fd2-446a-8889-826404107afb?page=1&perPage=10&serviceKey='+ API_KEY;
		const response = await
		fetch(url);
		const data = await
		response.json();
		console.log("data", data);
		console.log("data.data", data.data);
		console.log("data.data[0]", data.data[0]);
		//console.log("data.body", data.body);
		//console.log("data.body.dataList", data.body.dataList);
		//console.log("data.body.dataList[0]", data.body.dataList[0]);
		//console.log("data.body.dataList[0].ygnm", data.body.dataList[0].ygnm);
	}
	
	async function getCData2() {
		const url = 'https://api.odcloud.kr/api/15075891/v1/uddi:7cc7242f-7fd2-446a-8889-826404107afb?page=2&perPage=10&serviceKey='+ API_KEY;
		const response = await
		fetch(url);
		const data = await
		response.json();
		console.log("data", data);
		//console.log("data.response", data.response);
		//console.log("data.response.body", data.response.body);
		//console.log("data.response.body.items", data.response.body.items);
		//console.log("data.response.body.items[0]", data.response.body.items[0]);
		//console.log("data.response.body.items[0].loCrdnt", data.response.body.items[0].loCrdnt);
		//console.log("data.response.body.items[0].laCrdnt", data.response.body.items[0].laCrdnt);
		
	}
	getCData();
	getCData2();
</script>


<%@ include file="../common/head.jspf"%>

<%@ include file="../common/foot.jspf"%>