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
                <span>전체 보기(<c:out value="${ blog_profile.post_cnt }"/>)</span>
                <ul>
                <c:forEach var="tag" items="${ tag_list }">
                    <li><c:out value="${ tag.tag_name }"/>(<c:out value="${ tag.tag_cnt }"/>)</li>
                </c:forEach>
                </ul>
            </div>
            <div class="post_list">
                <div class="post">
                    <div class="post_img"><img src="https://cdn.pixabay.com/photo/2015/09/05/22/33/office-925806__340.jpg"></div>
                    <div class="post_title">홍길동의 파이썬 기본 문법 정리</div>
                    <div class="post_content">안녕하세요. 오늘은 파이썬 기본 문법에 대해 정리하려 합니다! 정말 어려워서 힘들었...</div>
                    <div class="post_tags">#파이썬 #어려워 #잘해지고싶당</div>
                    <div class="post_info"><span>2021.01.01  12:34</span><span>조회 0</span><span>댓글 0</span></div>
                </div>

                <div class="post">
                    <div class="post_img"><img src="https://cdn.pixabay.com/photo/2015/09/05/22/33/office-925806__340.jpg"></div>
                    <div class="post_title">홍길동의 파이썬 기본 문법 정리</div>
                    <div class="post_content">안녕하세요. 오늘은 파이썬 기본 문법에 대해 정리하려 합니다! 정말 어려워서 힘들었...</div>
                    <div class="post_tags">#파이썬 #어려워 #잘해지고싶당</div>
                    <div class="post_info"><span>2021.01.01  12:34</span><span>조회 0</span><span>댓글 0</span></div>
                </div>

                <div class="post">
                    <div class="post_img"><img src="https://cdn.pixabay.com/photo/2015/09/05/22/33/office-925806__340.jpg"></div>
                    <div class="post_title">홍길동의 파이썬 기본 문법 정리</div>
                    <div class="post_content">안녕하세요. 오늘은 파이썬 기본 문법에 대해 정리하려 합니다! 정말 어려워서 힘들었...</div>
                    <div class="post_tags">#파이썬 #어려워 #잘해지고싶당</div>
                    <div class="post_info"><span>2021.01.01  12:34</span><span>조회 0</span><span>댓글 0</span></div>
                </div>

                <div class="post">
                    <div class="post_img"><img src="https://cdn.pixabay.com/photo/2015/09/05/22/33/office-925806__340.jpg"></div>
                    <div class="post_title">홍길동의 파이썬 기본 문법 정리</div>
                    <div class="post_content">안녕하세요. 오늘은 파이썬 기본 문법에 대해 정리하려 합니다! 정말 어려워서 힘들었...</div>
                    <div class="post_tags">#파이썬 #어려워 #잘해지고싶당</div>
                    <div class="post_info"><span>2021.01.01  12:34</span><span>조회 0</span><span>댓글 0</span></div>
                </div>

                <div class="post">
                    <div class="post_img"><img src="https://cdn.pixabay.com/photo/2015/09/05/22/33/office-925806__340.jpg"></div>
                    <div class="post_title">홍길동의 파이썬 기본 문법 정리</div>
                    <div class="post_content">안녕하세요. 오늘은 파이썬 기본 문법에 대해 정리하려 합니다! 정말 어려워서 힘들었...</div>
                    <div class="post_tags">#파이썬 #어려워 #잘해지고싶당</div>
                    <div class="post_info"><span>2021.01.01  12:34</span><span>조회 0</span><span>댓글 0</span></div>
                </div>
            </div>
        </div>
    </section>
    
</body>
<script src="http://localhost/js/control_navbar.js"></script>

</html>