<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<script type="text/javascript">
if(${ not empty post_detail_fail}){
	alert("조회 중 문제가 발생하였습니다. 다시 시도해주세요.")
	location.href=history.back();
}//end if

</script>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>블로그 글 보기</title>
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

    <link rel="stylesheet" href="http://localhost/css/blog/reset.css" >
    <link rel="stylesheet" href="http://localhost/css/blog/blog_post.css">
    
</head>

<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous"> -->

<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
	
	$("#shareButton").click(function(){
	  if (navigator.share) {
	    navigator.share({
	      title: 'WebShare API Demo',
	      url: window.location.href
	    }).then(() => {
	      console.log('Thanks for sharing!');
	    })
	    .catch(console.error);
	  } else {
	    shareDialog.classList.add('is-open');
	  }
	});
	
	$("#removePost").click(function(){
		if(${ not empty sessionScope.id }){
			$.ajax({
				url:"remove/${post_detail.post_num}",
				type:"POST",
				dataType:"JSON",
				error:function(xhr){
					alert("에러");
					console.log(xhr.status+" / "+xhr.statusText);
				},
				success:function(jsonObj){
			      	if(jsonObj.flag=="success"){
						history.back();
			      	}else{
			      		alert("삭제 중 문제가 발생하였습니다. 다시 시도해주세요.")
			      	}//end else
				}//success
			});//ajax
		}else{
			alert("로그인 후 다시 시도해주세요.")
		}
	});//click
	
	$("#postLike").click(function(){
		if(${ not empty sessionScope.id }){
			if(${like_flag}){
				url="like/remove/${post_detail.post_num}"
			}else{
				url="like/add/${post_detail.post_num}"
			}//end else
			$.ajax({
				url:url,
				type:"POST",
				dataType:"JSON",
				error:function(xhr){
					alert("에러");
					console.log(xhr.status+" / "+xhr.statusText);
				},
				success:function(jsonObj){
			      	if(jsonObj.flag=="success"){
						//디자인 수정
			      	}else{
			      		alert("문제가 발생하였습니다. 다시 시도해주세요.")
			      	}//end else
				}//success
			});//ajax
		}else{
			alert("로그인 후 다시 시도해주세요.")
		}//end else
	});//click
	
 	$("#btn_follow").click(function(){
		if(${ not empty sessionScope.id }){
			var url="";
			var text="";
			if(${follow_flag}){
				url="팔로우 취소";
				text="unfollow";
			}else{
				url="팔로우 추가";
				text="follow";
			}//end else
			$.ajax({
				url:url,
				type:"POST",
				dataType:"JSON",
				error:function(xhr){
					alert("에러");
					console.log(xhr.status+" / "+xhr.statusText);
				},
				success:function(jsonObj){
			      	if(jsonObj.flag=="success"){
						$("#btn_follow").text(text);
			      	}else{
			      		alert("문제가 발생하였습니다. 다시 시도해주세요.");
			      	}//end else
				}//success
			});//ajax
		}else{
			alert("로그인 후 다시 시도해주세요.");
		}//end else
	});//click
	
 	$("#commAddClk").click(function(){
		if(${ not empty sessionScope.id }){
			var comm=$("#commAddCont").val()
			$.ajax({
				url:"comm/add/${post_detail.post_num}",
				type:"POST",
				data:{comm_content:comm},
				dataType:"JSON",
				error:function(xhr){
					alert("에러");
					console.log(xhr.status+" / "+xhr.statusText);
				},
				success:function(jsonObj){
			      	if(jsonObj.flag=="success"){
						var input="";
						input+='<div id="comm_div_'+jsonObj.comm_num+'" class="comment">'
						input+='<div class="c_writer_info">'
						input+='<img src="https://cdn.pixabay.com/photo/2018/04/20/17/18/'+jsonObj.profile_img+'">'
						input+='<div>'
						input+='<span>'+jsonObj.nickname+'</span>'
						input+='<span>'+jsonObj.input_date+'</span>'
						input+='</div>'
						input+='<div>'
						input+='<span id="commModify'+jsonObj.comm_num+'" onclick="commModifyClk('+jsonObj.comm_num+')" class="btn" >수정</span>'
						input+='<span id="commRemove" onclick="commRemoveClk('+jsonObj.comm_num+')" class="btn" >삭제</span>'
						input+='</div>'
						input+='</div>'
						input+='<input type="hidden" id="comm_cont_val_'+jsonObj.comm_num+'" value="'+jsonObj.comm_content+'"/>'
						input+='<div id="comm_content_'+jsonObj.comm_num+'" class="c_content">'
						input+=jsonObj.comm_content
						input+='</div>'
						input+='</div>'
						$("#comments_list").append(input);
						$("#commAddCont").val("");
						commCnt=Number($("#commCnt").text())
					 	changeCommCnt(commCnt+1);
			      	}else{
			      		alert("문제가 발생하였습니다. 다시 시도해주세요.");
			      	}//end else
				}//success
			});//ajax
		}else{
			alert("로그인 후 다시 시도해주세요.");
		}//end else
	});//click
	
});//ready

function changeCommCnt(cnt){
	$("#commCnt").text(cnt)
}//changeCommCnt
	
function commRemoveClk(comm_num){
	if(${ not empty sessionScope.id }){
		if(confirm("댓글을 삭제하시겠습니까?")){
			$.ajax({
				url:"comm/remove/"+comm_num,
				type:"POST",
				dataType:"JSON",
				error:function(xhr){
					alert("에러");
					console.log(xhr.status+" / "+xhr.statusText);
				},
				success:function(jsonObj){
			      	if(jsonObj.flag=="success"){
					 	$("#comm_div_"+comm_num).html("");
					 	commCnt=Number($("#commCnt").text())
					 	changeCommCnt(commCnt-1);
			      	}else{
			      		alert("문제가 발생하였습니다. 다시 시도해주세요.")
			      	}//end else
				}//success
			});//ajax
		}//end if
	}else{
		alert("로그인 후 다시 시도해주세요.")
	}//end else
}//commRemoveClk

function commModifyClk(comm_num){
	var input=""
	if($("#commModify"+comm_num).text()=="수정"){
		$("#commModify"+comm_num).text("취소");
 		input='<textarea id="comm_modify_'+comm_num+'" class="c_input" type="text" placeholder="댓글을 입력해주세요." style="width:100%">';
		input+=$("#comm_cont_val_"+comm_num).val()
		input+='</textarea>';
        input+='<div><input type="checkbox" name="chk_secret" value="true">';
        input+='<span onclick="commModifyBtn('+comm_num+')" class="btn_comment btn">댓글 수정</span></div>';
	}else{
		$("#commModify"+comm_num).text("수정");
		input=$("#comm_cont_val_"+comm_num).val()
	}//end else
	$("#comm_content_"+comm_num).html(input);
}//commModifyClk

function commModifyBtn(comm_num){
	if(${ not empty sessionScope.id }){
		if(confirm("댓글을 수정하시겠습니까?")){
			alert($("#comm_modify_"+comm_num).val())
			var comm=$("#comm_modify_"+comm_num).val()
			$.ajax({
				url:"comm/modify/"+comm_num,
				type:"POST",
				data:{comm_content:comm},
				dataType:"JSON",
				error:function(xhr){
					alert("에러");
					console.log(xhr.status+" / "+xhr.statusText);
				},
				success:function(jsonObj){
			      	if(jsonObj.flag=="success"){
			      		$("#comm_content_"+comm_num).html(comm);
			      	}else{
			      		alert("문제가 발생하였습니다. 다시 시도해주세요.")
			      	}//end else
				}//success
			});//ajax
		}//end if
	}else{
		alert("로그인 후 다시 시도해주세요.")
	}//end else
}//commModifyBtn

</script>

<body>
    <section class="section_header">
        <div class="header_nav">
            <span class="btn btn_nav_open"><i class="fas fa-bars"></i></span>
        </div>
        <ul class="nav_bar">
            <li><span>GLOG</span><span class="btn btn_nav_close"><i class="fas fa-chevron-left"></i></span></li>
            <li><span>LOGIN</span> / <span>JOIN</span></li>
            <li>인기순 보기</li>
            <li>최신순 보기</li>
            <li>search</li>
        </ul>
    </section>

    <section class="section_main">
        <!-- 왼쪽의 좋아요/댓글/공유 -->
        <div class="post_side_tab">
            <ul>
                <li id="postLike">
                <c:choose>
                <c:when test="${ like_flag }"></c:when>
                <c:when test="${ not like_flag }"></c:when>
                </c:choose>
                <i class="far fa-heart"></i>
                </li>
                <li><c:out value="${ post_detail.like_cnt }"/></li>
                <li><i class="far fa-comment-alt"></i></li>
                <li id="commCnt"><c:out value="${ post_detail.comment_cnt }"/></li>
                <li id="shareButton"><i class="fas fa-share-alt"></i></li>
            </ul>
        </div>
        <!-- 우측의 글 본문 -->
        <div class="post">
            <div class="post_title"><c:out value="${ post_detail.post_title }"/></div>
            <div class="post_info tabs">
                <div>
                    <span class="writer">by <c:out value="${ post_detail.nickname } "/> </span>
                    <span class="date"><c:out value="${ post_detail.input_date } "/> </span>
                    <span class="locked"><c:if test="${ post_detail.hidden_flag=='T' }">비공개</c:if></span>
                </div>
                <div class="btns">
                <c:if test="${ sessionScope.id==post_profile.id }">
                    <form id="modifyPost" action="${post_profile.id}/blog/post/modify/${post_detail.post_num}" method="post">
                    <span class="btn">수정</span>
                    </form>
                    <a id="removePost"><span class="btn">삭제</span></a>
                </c:if>
                </div>
            </div>
            <div class="post_tags tabs">
            <c:forEach var="tag" items="${ post_detail.tag_name }">
            #<c:out value="${ tag }"/> 
            </c:forEach>
            </div>
            <div class="post_content">
            <c:out value="${ post_detail.post_content }"/>
            </div>
            <!-- 작성자 프로필 -->
            <div class="writer_info">
                <img src="https://cdn.pixabay.com/photo/2016/01/19/16/49/${ post_profile.profile_img } }">
                <div class="info">
                    <div class="w_nickname">
                    	<span class="nickname"><c:out value="${ post_profile.nickname }"/></span>
                    	<c:if test="${ sessionScope.id!=post_profile.id }">
                    		<span id="btn_follow" class="btn_follow">
	                    	<c:if test="${ follow_flag }">
                    		unfollow
                    		</c:if>
	                    	<c:if test="${ not follow_flag }">
                    		follow
                    		</c:if>
                    		</span>
                    	</c:if>
                   	</div>
                    <div class="w_comment"><c:out value="${ post_profile.statement_msg }"/></div>
                    <div class="w_link">
                        <a href="${ post_profile.github }"><i class="fab fa-github"></i></a>
                        <a href="${ post_profile.website }"><i class="fas fa-link"></i></a>
                    </div>
                </div>
            </div>
            <!-- 댓글 작성 칸 -->
            <div class="write_comment">
                <div class="comment_cnt">댓글 <c:out value="${ post_detail.comment_cnt }"/></div>
                <div class="comment_input">
                    <textarea id="commAddCont" class="c_input" type="text" placeholder="댓글을 입력해주세요."></textarea>
                    <div>
                        <input type="checkbox" name="chk_secret" value="true">
                        <span id="commAddClk" class="btn_comment btn">댓글 쓰기</span>
                    </div>
                </div>
            </div>
            <!-- 댓글 목록 -->
            <div id="comments_list" class="comments_list">
            
            <c:if test="${ empty comm_list }">
			<div>댓글이 없습니다.</div>
			</c:if>
			
			<c:forEach var="comm" items="${ comm_list }">
            <div id="comm_div_${ comm.comm_num }" class="comment">
                <div class="c_writer_info">
                    <img src="https://cdn.pixabay.com/photo/2018/04/20/17/18/${ comm.profile_img }">
                    <div>
                        <span><c:out value="${ comm.nickname }"/></span>
                        <span><c:out value="${ comm.input_date }"/></span>
                    </div>
                    <div>
                    <c:if test="${ sessionScope.id==comm.id }">
                        <span id="commModify${ comm.comm_num }" onclick="commModifyClk(${ comm.comm_num })" class="btn" >수정</span>
                        <span id="commRemove" onclick="commRemoveClk(${ comm.comm_num })" class="btn" >삭제</span>
                    </c:if>
                    </div>
                </div>
                <input type="hidden" id="comm_cont_val_${ comm.comm_num }" value="${ comm.comm_content }"/>
                <div id="comm_content_${ comm.comm_num }" class="c_content">
                <c:out value="${ comm.comm_content }"/>
                </div>
            </div>
			</c:forEach>
			
            </div>
        </div>
    </section>
    
</body>
<script src="http://localhost/js/control_navbar.js"></script>

</html>