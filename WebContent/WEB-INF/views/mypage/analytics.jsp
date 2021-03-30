<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--     <!-- Google CDN -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/mypage/reset.css" />
	<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/mypage/style.css" /> --%>
<script>
$(function(){
	console.log("페이지 로드 됨")
	queryReports("7daysAgo", "today", "hits", "pageTitle", "포스팅 별 조회수")
 	queryReports("7daysAgo", "today", "hits", "date", "방문자 수") 
})
function queryReports(startDate, endDate, expression, name, label) {
	
	var key = new Array();
	var value = new Array();
	
	var param = {
			startDate : startDate,
			endDate : endDate,
			metrics : expression,
			dimensions : name
		}
	$.ajax({
		method:'GET',
		dataType:'json',
		url:"analytics.do",
		contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
		data:param,
		async:false,
		success:function(result){
			console.log(result.reports[0].data.rows)
			result = result.reports[0].data.rows;
			for(var i=0; i<result.length; i++){
		    	key[i]=result[i].dimensions[0];
		    	value[i]=result[i].metrics[0].values[0];
		    	console.log(key[i], value[i])
			}
			 
		},
		error:function(error){
			
		}
	})
	console.log(key, value, label)
	if(label === "방문자 수") {
    	drawChart(key,value,label); 		
	} else {
		postViewTable(key, value);
	}
}

function drawChart(key, value, label_name){
	
	for(var i=0; i<key.length; i++){
		var year=key[i].substring(0,4);
		var month=key[i].substring(4,6);
		var date=key[i].substring(6,8);
		
		key[i]=month+'-'+date;
	}
	
	 /*차트*/
	 var ctx = document.getElementById('siteUse').getContext('2d');
	 var myChart = new Chart(ctx, {
	     type: 'line',
	     data: {
	         labels: key,
	         datasets: [{
	        	 label : label_name,
	             data: value,
	             backgroundColor: [
	                 'rgba(255, 255, 255, 0)'
	             ],
	             borderColor: [
	                 'rgba(244, 121, 42, 1)'
	             ],
	             borderWidth: 1,
	             lineTension: 0
	         }]
	     },
	     options: {
	         scales: {
	             yAxes: [{
	                 ticks: {
	                     beginAtZero: true
	                 },
	             }]
	         }
	     }
	 });

	
}

function postViewTable(key, value) {
	console.log(key, value)
	for(let i = 0; i < key.length; i++){
		let tag = "<div class='data'>"+ (i+1) +"</div><div class='data'>"+ key[i] +"</div><div class='data'>"+ value[i] +"</div>";
		$("#viewTable").append(tag);		
	}
}
</script>
<h3>방문자 수</h3>
 <canvas id="siteUse"></canvas>
<h3>게시글 별 조회수</h3>
 <div id="viewTable" class="view-table">
 	<div class="column"></div>
 	<div class="column">제목</div>
 	<div class="column">조회수</div>
 </div>
