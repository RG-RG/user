<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${ post_detail.post_title }</title>

<link rel="stylesheet"
	href="../../../css/blog/reset.css">
<link rel="stylesheet"
	href="../../../css/blog/blog_post.css">
<link rel="stylesheet"
	href="../../../css/common/common_header_footer.css">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.css">
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/2.0.0/toastui-editor.min.css">
</head>


<!-- Google CDN -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
	crossorigin="anonymous"></script>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

<!-- toast ui -->
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor-viewer.min.css" />
<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-viewer.js"></script>

<script type="text/javascript">
	if(${ not empty post_detail_fail}){
		alert("조회 중 문제가 발생하였습니다. 다시 시도해주세요.")
		location.href=history.back();
	}//end if
</script>

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
				url:"remove.do?post=${post_detail.post_num}",
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
	
	
	/* 좋아요 눌렀을 때 */
	$("#postLike").click(function(){
		if(${ not empty sessionScope.id }){
			var url=''
			var like_flag=''
			if(!$("#like_flag_hid").val()){
				url="/like/remove.do?post=${post_detail.post_num}";
				like_flag=false;
			}else{
				url="/like/add.do?post=${post_detail.post_num}";
				like_flag=true;
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
			      		//성공했을 때 디자인 변경 
 						var heart = '<i class="far fa-heart"></i>'
 						var heartCnt = 0
 						
						console.log("like flag --- "+ jsonObj.like );
						
						if( jsonObj.like == "add" ) {
							heart = '<i class="like_heart fas fa-heart"></i>'
 							heartCnt = ( ${post_detail.like_cnt} + 1 )
						}
						if( jsonObj.like == "remove" ){
							heart = '<i class="far fa-heart"></i>'
							heartCnt = ( ${post_detail.like_cnt} - 1 )
						}
						
						$("#postLike").html(heart)
						$("#postLikeCnt").html(heartCnt)
						$("#like_flag_hid").val(like_flag)
			      	}else{
			      		alert("문제가 발생하였습니다. 다시 시도해주세요.")
			      	}//end else
				}//success
			});//ajax
		}else{
			alert("로그인 후 다시 시도해주세요.")
		}//end else
	});//click
	
	
	/* 팔로우 할 때 */
 	$("#btn_follow").click(function(){
		if(${ not empty sessionScope.id }){
			var url="";
			var text="";
			if(${follow_flag}){
				url="${post_profile.id}/unfollow.do";
				text="unfollow";
			}else{
				url="${post_profile.id}/follow.do";
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
			      	if(jsonObj.follow_result){
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
	
	
	/* 댓글 추가 */
 	$("#commAddClk").click(function(){
		if(${ not empty sessionScope.id }){
			var comm=$("#commAddCont").val()
			$.ajax({
				url:"/comm/add.do?post=${post_detail.post_num}",
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
						input+='<div id="comm_div_'+jsonObj.comm_num+'" class="comment comment-init">'
							input+='<div class="c_writer_info">'
								input+='<img src="'+jsonObj.profile_img+'">'
							input+='<div>'
								input+='<span class="c_writer"><a href="/rgrg_user/rgrg/${ comm.id }/blog">'+jsonObj.nickname+'</a></span>'
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
						
						$("#comm_zero").text('');
			      	}else{
			      		alert("문제가 발생하였습니다. 다시 시도해주세요.");
			      	}//end else
				}//success
			}).done(function(){
				console.log("댓글추가")
				 
			 	var new_comment = document.querySelectorAll(".comment-init");
				 
				setTimeout(function(){
					new_comment[new_comment.length-1].classList.add('comment-fade');
				}, 5);
			});//ajax
		}else{
			alert("로그인 후 다시 시도해주세요.");
		}//end else
	});//click
	

	
});//ready

function changeCommCnt(cnt){
	$("#commCnt").text(cnt)
	$("#commCnt2").text('댓글 '+cnt)
}//changeCommCnt
	
function commRemoveClk(comm_num){
	if(${ not empty sessionScope.id }){
		if(confirm("댓글을 삭제하시겠습니까?")){
			$.ajax({
				url:"/comm/remove.do?comm="+comm_num,
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
					 	if(commCnt-1==0){
					 		$("#comm_zero").text('댓글이 없습니다.');
					 	}//end if
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
			var comm=$("#comm_modify_"+comm_num).val()
			$.ajax({
				url:"/comm/modify.do?comm="+comm_num,
				type:"POST",
				data:{comm_content:comm},
				dataType:"JSON",
				error:function(xhr){
					alert("에러");
					console.log(xhr.status+" / "+xhr.statusText);
				},
				success:function(jsonObj){
			      	if(jsonObj.flag=="success"){
			      		$("#commModify"+comm_num).text("수정");
			      		$("#comm_content_"+comm_num).html(comm);
			      		$("#comm_cont_val_"+comm_num).val(comm);
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
	<!-- 헤더(사이드바) -->
	<c:import url="../common/common_header.jsp" />

	<section class="section_main">
		<!-- 왼쪽의 좋아요/댓글/공유 -->
		<div class="post_side_tab">
			<input id="like_flag_hid" type="hidden" value="${ like_flag }"/>
			<ul>
				<li id="postLike" class="postLike"><c:choose>
						<c:when test="${ like_flag }">
							<i class="like_heart fas fa-heart"></i>
						</c:when>
						<c:when test="${ not like_flag }">
							<i class="far fa-heart"></i>
						</c:when>
					</c:choose></li>
				<li id="postLikeCnt"><c:out value="${ post_detail.like_cnt }"/></li>
				<li><i class="far fa-comment-alt"></i></li>
				<li id="commCnt"><c:out value="${ post_detail.comment_cnt }" /></li>
				<li id="shareButton" class="postShare"><i
					class="fas fa-share-alt"></i></li>
			</ul>
		</div>

		<!-- 우측의 글 본문 -->
		<div class="post">
			<div class="post_title">
				<c:out value="${ post_detail.post_title }" />
			</div>
			<div class="post_info tabs">
				<div>
					<span class="writer">by <c:out
							value="${ post_detail.nickname } " /></span>・ <span class="date"><c:out
							value="${ post_detail.input_date } " /> </span> <span class="locked"><c:if
							test="${ post_detail.hidden_flag=='T' }">・<i
								class="fas fa-lock"></i>비공개</c:if></span>
				</div>
				<div class="btns">
					<c:if test="${ sessionScope.id==post_profile.id }">
						<form id="modifyPost"
							action="${post_profile.id}/blog/post/modify/${post_detail.post_num}"
							method="post">
							<span class="btn">수정</span>
						</form>
						<a id="removePost"><span class="btn">삭제</span></a>
					</c:if>
				</div>
			</div>

			<!-- 본문 내용 -->
			<div class="post_content">
				<div id="viewer"></div>
				<input type="hidden" id="content_hid"
					value="${ post_detail.post_content }" />
				<script type="text/javascript">
		         	 var content=$("#content_hid").val()
			       	 const viewer = new toastui.Editor({
			       	    el: document.querySelector('#viewer'),
			       	    initialValue: content,
			       	    viewer: true
			       	  });
	            </script>
			</div>

			<!-- 태그 -->
			<div class="post_tags tabs">
				<c:forEach var="tag" items="${ post_detail.tag_name }">
					<span>#<c:out value="${ tag }" /></span>
				</c:forEach>
			</div>

			<!-- 작성자 프로필 -->
			<div class="writer_info">
				<a href="/${ post_profile.id }/blog.do">
				<img src="${ post_profile.profile_img }">
				</a>
				<div class="info">
					<div class="w_nickname">
						<a href="/${ post_profile.id }/blog.do">
						<span class="nickname"><c:out
								value="${ post_profile.nickname }" /></span></a>
						<c:if test="${ sessionScope.id!=post_profile.id }">
							<span id="btn_follow" class="btn_follow"> <c:if
									test="${ follow_flag }">
                    			unfollow
                    		</c:if> <c:if test="${ not follow_flag }">
                    			follow
                    		</c:if>
							</span>
						</c:if>
					</div>
					<div class="w_comment">
						<c:out value="${ post_profile.statement_msg }" />
					</div>
					<div class="w_link">
						<a href="${ post_profile.github }"><i class="fab fa-github"></i></a>
						<a href="${ post_profile.website }"><i class="fas fa-link"></i></a>
					</div>
				</div>
			</div>
			<!-- 댓글 작성 칸 -->
			<div class="write_comment">
				<div id="commCnt2" class="comment_cnt">
					댓글
					<c:out value="${ post_detail.comment_cnt }" />
				</div>
				<div class="comment_input">
					<textarea id="commAddCont" class="c_input" type="text"
						placeholder="댓글을 입력해주세요."></textarea>
					<div>
						<span><input type="checkbox" name="chk_secret" value="true">비밀
							댓글</span> <span id="commAddClk" class="btn_comment btn">댓글 쓰기</span>
					</div>
				</div>
			</div>
			<!-- 댓글 목록 -->
			<div id="comments_list" class="comments_list">

				<div id="comm_zero" class="comm_zero">
					<c:if test="${ empty comm_list }">
						댓글이 없습니다.
					</c:if>
				</div>

				<c:forEach var="comm" items="${ comm_list }">
					<div id="comm_div_${ comm.comm_num }" class="comment">
						<div class="c_writer_info">
							<img src="${ comm.profile_img }">
							<div>
								<span class="c_writer"><a
									href="/${ comm.id }/blog.do"><c:out
											value="${ comm.nickname }" /></a></span> <span><c:out
										value="${ comm.input_date }" /></span>
							</div>
							<div>
								<c:if test="${ sessionScope.id==comm.id }">
									<span id="commModify${ comm.comm_num }"
										onclick="commModifyClk(${ comm.comm_num })" class="btn">수정</span>
									<span id="commRemove"
										onclick="commRemoveClk(${ comm.comm_num })" class="btn">삭제</span>
								</c:if>
							</div>
						</div>
						<input type="hidden" id="comm_cont_val_${ comm.comm_num }"
							value="${ comm.comm_content }" />
						<div id="comm_content_${ comm.comm_num }" class="c_content">
							<c:out value="${ comm.comm_content }" />
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>

	<!--푸터 -->
	<c:import url="../common/common_footer.jsp" />
</body>
<script src="../../../js/control_navbar.js"></script>

</html>