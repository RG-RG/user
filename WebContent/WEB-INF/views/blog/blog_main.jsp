<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<script type="text/javascript">
if(${ empty blog_profile}){
	alert("조회 중 문제가 발생하였습니다. 다시 시도해주세요.")
	location.href=history.back();
}//end if
</script>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>my Blog main</title>
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

    <link rel="stylesheet" href="http://localhost/css/blog/reset.css">
    <link rel="stylesheet" href="http://localhost/css/blog/myBlog_main.css">
</head>
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous"> -->

<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
	
});//ready

function morePost(next_page, search_word, search_tag){
	alert(next_page);
	alert(search_word);
	alert(search_tag);
	var data;
	$.ajax({
		url:"more/"+next_page,
		type:"POST",
		if(search_word!=''){
			data:{search:search_word}
		}//end if
		if(search_tag!=''){
			data:{search:search_tag}
		}//end if
		dataType:"JSON",
		error:function(xhr){
			alert("에러");
			console.log(xhr.status+" / "+xhr.statusText);
		},
		success:function(jsonObj){
	      	if(jsonObj.flag=="success"){
				alert(jsonObj.total_cnt)
	      	}else{
	      		alert("삭제 중 문제가 발생하였습니다. 다시 시도해주세요.")
	      	}//end else
		}//success
	});//ajax
}//morePost

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
        <!-- 프로필 화면 -->
        <div class="my_profile">
            <div class="profile_img"><img src="https://cdn.pixabay.com/photo/2016/01/19/16/49/${ blog_profile.profile_img }"></div>
            <div class="profile_nickname"><c:out value="${ blog_profile.nickname }"/></div>
            <div class="profile_comment"><c:out value="${ blog_profile.statement_msg }"/></div>
            <div class="profile_follow">
                <div>
                    <span class="f_title">팔로워</span><span class="f_num"><c:out value="${ blog_profile.follower_cnt }"/></span>
                    <span class="f_title">팔로잉</span><span class="f_num"><c:out value="${ blog_profile.following_cnt }"/></span>
                </div>
                <div>
                    <a href="${ blog_profile.github }"><i class="fab fa-github"></i></a>
                    <a href="${ blog_profile.website }"><i class="fas fa-link"></i></a>
                    <a href="${ blog_profile.visible_email }"><i class="fas fa-link"></i></a>
                </div>
            </div>
        </div> <!-- 프로필화면 end -->

        <!-- 검색, 새 글 작성 버튼 -->
        <div class="btn_set">
            <div>
                <input type="text" class="input_search" placeholder="검색어를 입력해주세요">
                <button class="btn_search">검색</button>
            </div>
            <div>
            <c:if test="${ sessionScope.id==blog_profile.id }">
                <span><i class="fas fa-edit"></i>새 글 작성</span>
            </c:if>
            </div>
        </div> <!-- 검색, 새 글 작성 버튼 end -->
        
        <div class="blog_post">
            <div class="category">
                <span>전체보기(<c:out value="${ blog_profile.post_cnt }"/>)</span>
                <ul>
                <c:forEach var="tag" items="${ tag_list }">
                    <li><c:out value="${ tag.tag_name }"/>(<c:out value="${ tag.tag_cnt }"/>)</li>
                </c:forEach>
                </ul>
            </div>
            <div class="post_list">
            <c:forEach var="post" items="${ post_list }">
                <div class="post">
                    <div class="post_img"><img src="https://cdn.pixabay.com/photo/2015/09/05/22/33/${ post.thumbnail }"></div>
                    <div class="post_title"  onclick="javascript:location.href ='/rgrg/${ blog_profile.id }/blog/post/${ post.post_num }'">
                    <c:out value="${ post.post_title }"/>
                    </div>
                    <div class="post_content"><c:out value="${ post.post_content.substring(0,4).concat('...') }"/></div>
                    <div class="post_tags">
                    <c:forEach var="post_tag" items="${ post.tag_name }">
                    #<c:out value="${ post_tag }"/>
                    </c:forEach>
                    </div>
                    <div class="post_info">
                    <span><c:out value="${ post.input_date }"/></span>
                    <span>조회 <c:out value="${ post.view_cnt }"/></span>
                    <span>댓글 <c:out value="${ post.comment_cnt }"/></span>
                    </div>
                </div>
            </c:forEach>
            </div>
            <div>
            <c:if test="${ end_num lt total_cnt }">
			<span onclick="morePost(${cur_page+1},'${search_word}','${search_tag}')">더보기</span>
            </c:if>
            </div>
        </div>
    </section>
    
</body>
<script src="http://localhost/js/control_navbar.js"></script>

</html>