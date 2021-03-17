<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사용자 - 내 보관함</title>
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

    <link rel="stylesheet" href="http://localhost/css/blog/reset.css">
    <link rel="stylesheet" href="http://localhost/css/blog/like_post.css">
</head>
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous"> -->

<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
	
});//ready

function moreLike(next_page){
	alert(next_page)
	$.ajax({
		url:"list/"+next_page,
		type:"POST",
		dataType:"JSON",
		error:function(xhr){
			alert("에러");
			console.log(xhr.status+" / "+xhr.statusText);
		},
		success:function(jsonObj){
	      	if(jsonObj.flag=="success"){
				var output='';
				$.each(jsonObj.post_list, function(idx, list){
					output+='<div class="post">';
					output+='<div class="post_img">';
					output+=list.thumbnail;
					output+='</div>';
					output+='<div class="post_title" onclick="javascript:location.href=\'/rgrg/'+list.id+'/blog/post/'+list.post_num+'\'">'+list.post_title+'</div>';
					output+='<div class="post_content">'+list.post_content.substring(0,10).concat('...')+'</div>';
					output+='<div class="post_info">';
					output+='<span class="post_writer">by. '+list.nickname+' ・'+list.input_cate+'</span>';
					output+='<span class="post_like">';
					output+='<span>';
					output+='💗';
					output+='</span>';
					output+='<span>'+list.like_cnt+'</span>';
					output+='</span>';
					output+='</div>';
					output+='</div>';
				});//each
				
				$("#main_content").append(output);
				
				var more='';
				if(jsonObj.end_num<jsonObj.total_cnt){
					more+='<div onclick="moreLike('+next_page+')" class="more_btn">더 보기</div>';
				}//end if
				$("#main_btn").html(more);
	      	}else{
	      		alert("문제가 발생하였습니다. 다시 시도해주세요.")
	      	}//end else
		}//success
	});//ajax
}//moreLike

</script>
<body>
    <!-- 헤더 -->
    <!-- jsp에서는 c:import로 바꿔주면 돼요~ -->
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
    <!-- 헤더 end -->

    <section class="section_main">
        <!-- 타이틀 -->
        <div class="main_title">좋아한 포스트</div>

        <div id="main_content" class="main_content">
            <!-- main_content 안에 들어있는거여서
            c:foreach 해서 post를 반복문 돌리면 됩니당
            ajax append도 여기 하면 될 것 같아용 -->
			<c:forEach var="like" items="${ like_list }">
            <div class="post">
                <div class="post_img"> <!-- 이미지는 여기 넣거나, background image를 CSS로 줘서 해도 됩니당 편한대로..!--> </div>
                <div class="post_title" onclick="javascript:location.href='/rgrg/${ like.id }/blog/post/${ like.post_num }'"><c:out value="${ like.post_title }"/></div>
                <div class="post_content"><c:out value="${ like.post_content.substring(0,10).concat('...') }"/></div>
                <div class="post_info">
                    <span class="post_writer">by. <c:out value="${ like.nickname }"/> ・<c:out value="${ like.input_date }"/></span>
                    <span class="post_like">
                        <span>💗</span>
                        <span><c:out value="${ like.like_cnt }"/></span>
                    </span>
                </div>
            </div>
			</c:forEach>
        </div> <!-- main content end -->

        <!-- 더보기 버튼 -->
        <div id="main_btn" class="main_btn">
        <c:if test="${ end_num lt total_cnt }">
            <div onclick="moreLike(${ cur_page+1 })" class="more_btn">더 보기</div>
        </c:if>
        </div>
        <!-- 버튼 end -->
    </section>

    <script src="http://localhost/js/control_navbar.js"></script>
</body>
</html>