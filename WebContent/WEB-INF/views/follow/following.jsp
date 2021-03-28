<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Co-doing</title>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<link rel="stylesheet" href="http://localhost/css/reset.css">
<link rel="stylesheet" href="http://localhost/css/follow/style.css"> 
<link rel="stylesheet" href="http://localhost/css/common/common_header_footer.css">
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
#profile_img{ width: 6rem; height: 6rem; margin-top: 1.7rem; margin-left: 1rem}
#infoTd{ padding-left: 0.2rem; padding-right: 5rem; padding-bottom: 0rem; cursor: pointer; width: 47rem; word-break: break-all; height: auto }
#nickname{ font-size: 1.7rem; font-weight: bold; padding-top: 2.2rem; margin-bottom: 0.5rem; color: #5E5E5E }
#statement_msg{ font-size: 1.4rem; color: #A2A2A2; overflow: hidden; }
.btn-dark{ font-size: 1.4rem; border-radius: 4rem; padding: 1rem; padding-top:0.5rem; padding-bottom:0.5rem; width: 8rem; float: right; margin-right: 2rem; margin-top: 2.9rem }
.btn-light{ width: 70rem; font-size: 1.4rem; padding: 0.7rem; border-radius: 0.5rem; color: #666; margin-top: 1rem }
</style>
<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
	
});//ready

function moveBlog(id){
	location.href = "/" + id+"/blog.do";
}//moveBlog

function follow(id){
	var text = $("#followBtn"+id).text();
	
	if (text == "팔로우"){
		$.ajax({
			url : "/follow.do",
			type : "POST",
			data : "following_id="+id,
			dataType : "JSON",
			error : function(xhr){
				alert("error : " + xhr.status + " / " +xhr.statusText);
			},
			success : function(json){
				if (json.follow_result) {
					$("#followBtn"+id).text('언팔로우');
				} else {
					alert("오류가 발생했습니다. 잠시 후 다시 실행해주시기 바랍니다.");
				}//end else
			}//success
		});//ajax
	} else if (text == "언팔로우"){
		$.ajax({
			url : "/unfollow.do",
			type : "POST",
			data : "following_id="+id,
			dataType : "JSON",
			error : function(xhr){
				alert("error : " + xhr.status + " / " +xhr.statusText);
			},
			success : function(json){
				if (json.unfollow_result) {
					$("#followBtn"+id).text('팔로우');
				} else {
					alert("오류가 발생했습니다. 잠시 후 다시 실행해주시기 바랍니다.");
				}//end else
			}//success
		});//ajax 
	}//end else
}//follow

function unfollow(id){
	var text = $("#unfollowBtn"+id).text();
	
	if (text == "팔로우"){
		$.ajax({
			url : "/follow.do",
			type : "POST",
			data : "following_id="+id,
			dataType : "JSON",
			error : function(xhr){
				alert("error : " + xhr.status + " / " +xhr.statusText);
			},
			success : function(json){
				if (json.follow_result) {
					$("#unfollowBtn"+id).text('언팔로우');
				} else {
					alert("오류가 발생했습니다. 잠시 후 다시 실행해주시기 바랍니다.");
				}//end else
			}//success
		});//ajax
	} else if (text == "언팔로우"){
		$.ajax({
			url : "/unfollow.do",
			type : "POST",
			data : "following_id="+id,
			dataType : "JSON",
			error : function(xhr){
				alert("error : " + xhr.status + " / " +xhr.statusText);
			},
			success : function(json){
				if (json.unfollow_result) {
					$("#unfollowBtn"+id).text('팔로우');
				} else {
					alert("오류가 발생했습니다. 잠시 후 다시 실행해주시기 바랍니다.");
				}//end else
			}//success
		});//ajax 
	}//end else	
}//unfollow

function moreView(url_id, page){
	if (page == ""){
		page = 1;
	}//end if
	var next_page = +page + 1;
	$.ajax({
		url : "/get_more_following.do",
		type : "POST",
		data : "page="+next_page,
		contentType: "application/x-www-form-urlencoded; charset=utf-8",
		dataType : "JSON",
		error : function(xhr){
			alert("error : " + xhr.status + " / " + xhr.statusText);
		},
		success : function(json){
			var output = '';
			$.each(json.following_list, function(idx, list){
				output += '<table class="table"><tbody>';
				output += '<tr><td id="imgTd" onclick="moveBlog(\'' + list.id + '\');">';
				output += '<img src="/rgrg_user/images/profile/' + list.profile_img + '" class="rounded-circle" id="profile_img"></td>';
				if (list.statement_msg != null){
					output += '<td id="infoTd" onclick="moveBlog(\'' + list.id + '\');">';
					output += '<p id="nickname">' + list.nickname + '</p>';
					output += '<p id="statement_msg">' + list.statement_msg + '</p></td>';
				} else {
					output += '<td id="infoTd" style="padding-top: 2rem" onclick="moveBlog(\'' + list.id + '\');">';
					output += '<p id="nickname">' + list.nickname + '</p></td>';
				}//end else
				output += '<td id="followTd">';
				if (list.following_id == null){
					output += '<button type="button" class="btn btn-dark" id="followBtn' + list.id + '" onclick="follow(\'' + list.id + '\');" value="follow">팔로우</button>';
				} else {
					output += '<button type="button" class="btn btn-dark" id="unfollowBtn' + list.id + '" onclick="unfollow(\'' + list.id + '\');" value="unfollow">언팔로우</button>';
				}//end else
				output += '</td></tr></tbody></table>';
			});//each
			$("#followingListDiv").append(output);
			
			output = '';
			if ( json.following_list != null && !json.end ){
				output += '<button type="button" class="btn btn-light" onclick="moreView(\'' + url_id + '\', \''+ next_page + '\');">더보기</button>';
			}//end if
			$("#moreDiv").html(output);
		}//success
	});//ajax
}//moreView

</script>
</head>
<body style="font-family: IBMPlexSansKR-Regular">
    <jsp:include page="../common/common_header.jsp"/>

    <section class="section_main">
    	<div id="container">
        	<div id="containerTitle">
        		팔로잉
        	</div>
        	<hr id="titleLine">
        	<div id="containerContent">
        		<div id="followingListDiv">
        			<c:if test="${ empty following_list }">
						<table id="zeroTable" class="table">
							<tr><td id="zero">팔로잉 목록이 없습니다.</td></tr>
						</table>
        			</c:if>
        			<c:if test="${ not empty following_list }">
        				<c:forEach var="fd" items="${ following_list }">
						<table class="table">
						  <tbody>
						    <tr>
						      <td id="imgTd" onclick="moveBlog('${ fd.id }');">
								  <img src="/rgrg_user/images/profile/${ fd.profile_img }" class="rounded-circle" id="profile_img">
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
							      	<button type="button" class="btn btn-dark" id="followBtn${ fd.id }" onclick="follow('${ fd.id }');" value="follow">팔로우</button>
						      	</c:if>
					      		<c:if test="${ fd.following_id eq fd.id }">
							      	<button type="button" class="btn btn-dark" id="unfollowBtn${ fd.id }" onclick="unfollow('${ fd.id }');" value="unfollow">언팔로우</button>
					      		</c:if>
						      </td>
						    </tr>
						  </tbody>
						</table>        		
        				</c:forEach>
        			</c:if>
        		</div>
        		<div id="moreDiv">
        		<!-- 더보기O : 팔로잉 리스트가 있으면서, list_end가 follower_cnt보다 작거나 같은 경우  -->
        		<c:if test="${ not empty following_list && list_end le following_cnt }">
        			<button type="button" class="btn btn-light" onclick="moreView('${ url_id }', '${ param.page }');">더보기</button>
        		</c:if>
        		</div>
        	</div>
        </div>
    </section>
    
    <jsp:include page="../common/common_footer.jsp"/>
</body>
<script src="http://localhost/js/control_navbar.js"></script>

</html>