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

    <link rel="stylesheet" href="http://localhost/rgrg_user/css/blog/reset.css" >
    <link rel="stylesheet" href="http://localhost/rgrg_user/css/blog/blog_post.css">
    
</head>

<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous"> -->

<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
	
});//ready
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
                <li><i class="far fa-heart"></i></li>
                <li><c:out value="${ post_detail.like_cnt }"/></li>
                <li><i class="far fa-comment-alt"></i></li>
                <li><c:out value="${ post_detail.comment_cnt }"/></li>
                <li><i class="fas fa-share-alt"></i></li>
            </ul>
        </div>
        <!-- 우측의 글 본문 -->
        <div class="post">
            <div class="post_title"><c:out value="${ post_detail.post_title }"/></div>
            <div class="post_info tabs">
                <div>
                    <span class="writer">by <c:out value="${ post_detail.nickname } "/></span>
                    <span class="date"><c:out value="${ post_detail.input_date } "/></span>
                    <!-- if로 공개/비공개 확인하여 비공개 일 때에만 visible하게 처리해야 함 -->
                    <!-- '공개'인 경우에는 class에 hidden 추가하게 처리하기 -->
                    <span class="locked"><c:if test="${ post_detail.hidden_flag=='T' }">비공개</c:if></span>
                </div>
                <div class="btns">
                    <span>수정</span>
                    <span>삭제</span>
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
                    <div class="w_nickname"><span class="nickname"><c:out value="${ post_profile.nickname }"/></span><span class="btn_follow">follow</span></div>
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
                    <textarea class="c_input" type="text" placeholder="댓글을 입력해주세요."></textarea>
                    <div>
                        <input type="checkbox" name="chk_secret" value="true">
                        <span class="btn_comment">댓글 쓰기</span>
                    </div>
                </div>
            </div>
            <!-- 댓글 목록 -->
            <!-- 목록의 수정, 삭제는 본인에게만 보이도록 확인해야 할 것 같음 -->
            <div class="comments_list">
            <c:if test="${ empty comm_list }">
			<div>댓글이 없습니다.</div>
			</c:if>
			<c:forEach var="comm" items="${ comm_list }">
            <div class="comment">
                <div class="c_writer_info">
                    <img src="https://cdn.pixabay.com/photo/2018/04/20/17/18/${ comm.profile_img }">
                    <div>
                        <span><c:out value="${ comm.nickname }"/></span>
                        <span><c:out value="${ comm.input_date }"/></span>
                    </div>
                    <div>
                    <c:if test="${ sessionScope.id==comm.id }">
                        <span>수정</span><span>삭제</span>
                    </c:if>
                    </div>
                </div>
                <div class="c_content">
                <c:out value="${ comm.comm_content }"/>
                </div>
            </div>
			</c:forEach>
            </div>
        </div>
    </section>
    
</body>
<script src="http://localhost/rgrg_user/js/control_navbar.js"></script>

</html>