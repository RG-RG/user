<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>RGRG</title>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<link rel="stylesheet" href="http://localhost/rgrg_user/css/reset.css">
<link rel="stylesheet" href="http://localhost/rgrg_user/css/follow/style.css"> 
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<style type="text/css">
.section_main{ margin:0 auto; width: 70rem; margin-top: 8rem }
#containerTitle{ font-size: 3.5rem; font-weight: bold; width: 10rem; margin: 0 auto }
#titleLine{ margin:0 auto; margin-top: 2.2rem; width: 25rem;  height: 0.1rem; background: #bbb; background-image: -webkit-linear-gradient(left, #eee, #777, #eee); background-image: -moz-linear-gradient(left, #eee, #777, #eee); background-image: -ms-linear-gradient(left, #eee, #777, #eee); background-image: -o-linear-gradient(left, #eee, #777, #eee); }
#containerContent{ margin:0 auto; margin-top: 5rem; width: 70rem; min-height:60rem; }
#container{ margin-bottom: 10rem }
table{ table-layout: fixed; }
tr{ border-top: hidden; }
#zeroTable{ margin-top: 20rem }
#zero{ font-size: 2.2rem; text-align: center; color: #5E5E5E; }
#imgTd{ width: 10rem; cursor: pointer; }
#profile_img{ width: 9rem; height: 9rem; padding: 2rem; }
#infoTd{ padding-left: 0.2rem; padding-right: 5rem; padding-bottom: 1.7rem; cursor: pointer; width: 47rem; word-break: break-all; height: auto }
#nickname{ font-size: 1.7rem; font-weight: bold; padding-top: 2rem; margin-bottom: 0.5rem; color: #5E5E5E }
#statement_msg{ font-size: 1.4rem; color: #A2A2A2; overflow: hidden; }
.btn-dark{ font-size: 1.4rem; border-radius: 4rem; padding: 1rem; padding-top:0.5rem; padding-bottom:0.5rem; width: 8rem; float: right; margin-right: 2rem; margin-top: 2.9rem }
.btn-light{ width: 70rem; font-size: 1.4rem; padding: 0.7rem; border-radius: 0.5rem; color: #666 }
</style>
<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){

});//ready

function moveBlog(id){
	location.href = "/rgrg_user/rgrg/"+id+"/blog/index";
}//moveBlog

function follow(id){
	$.ajax({
		url : "follow",
		type : "POST",
		data : "following_id="+id,
		dataType : "JSON",
		error : function(xhr){
			alert("error : " + xhr.status + " / " +xhr.statusText);
		},
		success : function(json){
			//버튼 언팔로우로 바꾸기
		}//success
	});//ajax
}//follow

function unfollow(id){
	$.ajax({
		url : "unfollow",
		type : "POST",
		data : "following_id="+id,
		dataType : "JSON",
		error : function(xhr){
			alert("error : " + xhr.status + " / " +xhr.statusText);
		},
		success : function(json){
			//버튼 팔로우로 바꾸기
		}//success
	});//ajax
}//unfollow

</script>
</head>
<body style="font-family: IBMPlexSansKR-Regular">
    <jsp:include page="../common/common_header.jsp"/>

    <section class="section_main">
    	<div id="container">
        	<div id="containerTitle">
        		팔로워
        	</div>
        	<hr id="titleLine">
        	<div id="containerContent">
        		<div id="followerListDiv">
        			<c:if test="${ empty follower_list }">
						<table id="zeroTable" class="table">
							<tr><td id="zero">팔로워 목록이 없습니다.</td></tr>
						</table>
        			</c:if>
        			<c:if test="${ not empty follower_list }">
        				<c:forEach var="fd" items="${ follower_list }">
						<input type="hidden" id="id" value="${ fd.id }"/>
						<table class="table">
						  <tbody>
						    <tr>
						      <td id="imgTd" onclick="moveBlog('${ fd.id }');">
								  <img src="https://cdn.pixabay.com/photo/2019/07/08/04/58/hamster-4323773_960_720.jpg" class="rounded-circle" id="profile_img">
								  <%-- <img src="${ fd.profile_img }" class="rounded-circle" id="profile_img"> --%>
						      </td>
						      <c:if test="${ not empty fd.statement_msg }">
							      <td id="infoTd" onclick="moveBlog('${ fd.id }');">
							      	<p id="nickname">${ fd.nickname }</p>
								    <p id="statement_msg">${ fd.statement_msg }</p>
							      </td>
						      </c:if>
						      <c:if test="${ fd.statement_msg eq null }">
							      <td id="infoTd" style="padding-top: 2rem" onclick="moveBlog('${ fd.id }');">
							      	<p id="nickname">${ fd.nickname }</p>
							      </td>
						      </c:if>
						      <td id="followTd">
						      	<c:if test="${ fd.following_id eq null }">
							      	<button type="button" class="btn btn-dark" id="followBtn" onclick="follow('${ fd.id }');">팔로우</button>
						      	</c:if>
					      		<c:if test="${ fd.following_id eq fd.id }">
							      	<button type="button" class="btn btn-dark" id="unfollowBtn" onclick="unfollow('${ fd.id }');">언팔로우</button>
					      		</c:if>
						      </td>
						    </tr>
						  </tbody>
						</table>        		
        				</c:forEach>
        			</c:if>
        		</div>
        		<div>
        		<c:if test="${ not empty follower_list }">
        			<button type="button" class="btn btn-light">더보기</button>
        		</c:if>
        		</div>
        	</div>
        </div>
    </section>
    
    <jsp:include page="../common/common_footer.jsp"/>
</body>
<script src="http://localhost/rgrg_user/js/control_navbar.js"></script>

</html>