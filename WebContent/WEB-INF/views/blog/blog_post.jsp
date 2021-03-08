<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
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
                <li>2</li>
                <li><i class="far fa-comment-alt"></i></li>
                <li>3</li>
                <li><i class="fas fa-share-alt"></i></li>
            </ul>
        </div>
        <!-- 우측의 글 본문 -->
        <div class="post">
            <div class="post_title">[Python] 파이썬 기초문법 정리1</div>
            <div class="post_info tabs">
                <div>
                    <span class="writer">by 홍길동</span>
                    <span class="date">2021.01.23</span>
                    <!-- if로 공개/비공개 확인하여 비공개 일 때에만 visible하게 처리해야 함 -->
                    <!-- '공개'인 경우에는 class에 hidden 추가하게 처리하기 -->
                    <span class="locked">비공개</span>
                </div>
                <div class="btns">
                    <span>수정</span>
                    <span>삭제</span>
                </div>
            </div>
            <div class="post_tags tabs">#파이썬 #기초문법 #어려워</div>
            <div class="post_content">
                Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iure unde et voluptas, adipisci reiciendis quod placeat suscipit libero nulla fugit ducimus accusamus voluptate porro impedit, commodi fuga, corporis cum quaerat?<br><br>
                Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iure unde et voluptas, adipisci reiciendis quod placeat suscipit libero nulla fugit ducimus accusamus voluptate porro impedit, commodi fuga, corporis cum quaerat?
                Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iure unde et voluptas, adipisci reiciendis quod placeat suscipit libero nulla fugit ducimus accusamus voluptate porro impedit, commodi fuga, corporis cum quaerat?
                Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iure unde et voluptas, adipisci reiciendis quod placeat suscipit libero nulla fugit ducimus accusamus voluptate porro impedit, commodi fuga, corporis cum quaerat?
                Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iure unde et voluptas, adipisci reiciendis quod placeat suscipit libero nulla fugit ducimus accusamus voluptate porro impedit, commodi fuga, corporis cum quaerat?<br><br>
                Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iure unde et voluptas, adipisci reiciendis quod placeat suscipit libero nulla fugit ducimus accusamus voluptate porro impedit, commodi fuga, corporis cum quaerat?
                Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iure unde et voluptas, adipisci reiciendis quod placeat suscipit libero nulla fugit ducimus accusamus voluptate porro impedit, commodi fuga, corporis cum quaerat?
                Lorem, ipsum dolor sit amet consectetur adipisicing elit. Iure unde et voluptas, adipisci reiciendis quod placeat suscipit libero nulla fugit ducimus accusamus voluptate porro impedit, commodi fuga, corporis cum quaerat?
            </div>
            <!-- 작성자 프로필 -->
            <div class="writer_info">
                <img src="https://cdn.pixabay.com/photo/2016/01/19/16/49/laptop-1149412_960_720.jpg">
                <div class="info">
                    <div class="w_nickname"><span class="nickname">홍길동</span><span class="btn_follow">follow</span></div>
                    <div class="w_comment">안녕하세요 홍길동입니다. 저의 블로그에 방문해주셔서 감사합니다~~ 함께 공부해보아요~~ 저의 블로그에 방문해주셔서 감사합니다~~ 함께 공부해보아요~~</div>
                    <div class="w_link">
                        <i class="fab fa-github"></i><i class="fas fa-link"></i><i class="fas fa-link"></i>
                    </div>
                </div>
            </div>
            <!-- 댓글 작성 칸 -->
            <div class="write_comment">
                <div class="comment_cnt">댓글 3</div>
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
                <div class="comment">
                    <div class="c_writer_info">
                        <img src="https://cdn.pixabay.com/photo/2018/04/20/17/18/cat-3336579_960_720.jpg">
                        <div>
                            <span>김철수</span>
                            <span>2021.01.23 12:23</span>
                        </div>
                        <div>
                            <span>수정</span><span>삭제</span>
                        </div>
                    </div>
                    <div class="c_content">
                        Lorem ipsum dolor sit, amet consectetur adipisicing elit. Illum optio corporis ex repellat veritatis harum nihil quo, dolor, nisi reiciendis sit minima consequuntur enim assumenda dolores odio omnis facilis quibusdam.
                        Lorem ipsum dolor sit, amet consectetur adipisicing elit. Illum optio corporis ex repellat veritatis harum nihil quo, dolor, nisi reiciendis sit minima consequuntur enim assumenda dolores odio omnis facilis quibusdam.
                    </div>
                </div>

                <div class="comment">
                    <div class="c_writer_info">
                        <img src="https://cdn.pixabay.com/photo/2013/11/08/21/12/cat-207583_960_720.jpg">
                        <div>
                            <span>김영희</span>
                            <span>2021.01.23 12:23</span>
                        </div>
                        <div>
                            <span>수정</span><span>삭제</span>
                        </div>
                    </div>
                    <div class="c_content">
                        비밀 댓글 입니다.
                    </div>
                </div>

                <div class="comment">
                    <div class="c_writer_info">
                        <img src="https://cdn.pixabay.com/photo/2018/04/20/17/18/cat-3336579_960_720.jpg">
                        <div>
                            <span>김철수</span>
                            <span>2021.01.23 12:23</span>
                        </div>
                        <div>
                            <span>수정</span><span>삭제</span>
                        </div>
                    </div>
                    <div class="c_content">
                        Lorem ipsum dolor sit, amet consectetur adipisicing elit. Illum optio corporis ex repellat veritatis harum nihil quo, dolor, nisi reiciendis sit minima consequuntur enim assumenda dolores odio omnis facilis quibusdam.
                        Lorem ipsum dolor sit, amet consectetur adipisicing elit. Illum optio corporis ex repellat veritatis harum nihil quo, dolor, nisi reiciendis sit minima consequuntur enim assumenda dolores odio omnis facilis quibusdam.
                    </div>
                </div>
            </div>
        </div>
    </section>
    
</body>
<script src="http://localhost/rgrg_user/js/control_navbar.js"></script>

</html>