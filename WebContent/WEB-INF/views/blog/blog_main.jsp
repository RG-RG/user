<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<script type="text/javascript">
if(${ empty blog_profile}){
	alert("조회 중 문제가 발생하였습니다. 다시 시도해주세요.")
	location.href=history.back();
}//end if
</script>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${ blog_profile.blog_name }</title>
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

    <link rel="stylesheet" href="http://localhost/css/blog/reset.css">
    <link rel="stylesheet" href="http://localhost/css/blog/myBlog_main.css">
    <link rel="stylesheet" href="http://localhost/css/common/common_header_footer.css">
    <link rel="stylesheet" href="http://localhost/css/common/see_more_btn.css">
</head>

<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
	
});//ready

function morePost(next_page, search_word, search_tag){
	var param=""
	if(search_word!=''){
		param="&search="+search_word
	}//end if
	if(search_tag!=''){
		param="&tag="+search_tag
	}//end if
	$.ajax({
		url:"/${ blog_profile.id }/blog/more.do?cur_page="+next_page+param,
		type:"POST",
		dataType:"JSON",
		/* contentType: "application/json;charset=UTF-8", */
		error:function(xhr){
			alert("에러");
			console.log(xhr.status+" / "+xhr.statusText);
		},
		success:function(jsonObj){
			
	      	if(jsonObj.flag=="success"){
				var output='';
				$.each(jsonObj.post_list, function(idx, list){
	                var cur_content = list.post_content;
	                if( list.post_content.length > 20 ) {
	                   cur_content = cur_content.substring(0,20).concat('···');
	                } 
					output+='<div class="post">';
					output+='<div class="post_img"><img src="https://cdn.pixabay.com/photo/2015/09/05/22/33/'+list.thumbnail+'"></div>';
					output+='<div class="post_title"  onclick="javascript:location.href =\'/${ blog_profile.id }/blog/post.do?post='+list.post_num+'\'">';
					output+=list.post_title;
					output+='</div>';
					output+='<div class="post_content">'+ cur_content +'</div>';
					output+='<div class="post_tags">';
					$.each(list.tag_list, function(idx2, list2){
						output+='#'+list2.tag_name;
					});//each
					output+='</div>';
					output+='<div class="post_info">';
					output+='<span>'+list.input_date+'</span>';
					output+='<span>조회 '+list.view_cnt+'</span>';
					output+='<span>댓글 '+list.comment_cnt+'</span>';
					output+='</div>';
					output+='</div>';
				});//each
				$("#post_list").append(output);
				
				var more='';
				var word='';
				var tag='';
				if(jsonObj.search_word!=null && jsonObj.search_word!='' && jsonObj.search_word!='undefined'){
					word=jsonObj.search_word;
				}//end if
				if(jsonObj.search_tag!=null && jsonObj.search_tag!='' && jsonObj.search_tag!='undefined'){
					tag=jsonObj.search_tag;
				}//end if
				if(jsonObj.end_num<jsonObj.total_cnt){
					more+='<span class="more_btn" onclick="morePost('+(jsonObj.cur_page+1)+',\''+word+'\',\''+tag+'\')">더 보기</span>';
				}//end if
				$("#viewMore").html(more);
	      	}else{
	      		alert("문제가 발생하였습니다. 다시 시도해주세요.")
	      	}//end else
		}//success
	});//ajax
}//morePost

function enterkey() { 
	if (window.event.keyCode == 13) { 
		searchBtn();
	} 
}//enterkey

function searchBtn(){
	var text=$("#search_txt").val()
	if(text.trim().length==0){
		alert("검색어를 입력해주세요");
	}else{
		location.href="blog.do?search="+text;
	}//end else
}//searchBtn

</script>
<body>
	<!-- 헤더(사이드바) -->
	<c:import url="../common/common_header.jsp" />

    <section class="section_main">

        <!-- 프로필 화면 -->
        <div class="my_profile">
            <div class="profile_img"><img src="https://cdn.pixabay.com/photo/2016/01/19/16/49/${ blog_profile.profile_img }"></div>
            <div class="profile_nickname"><c:out value="${ blog_profile.nickname }"/></div>
            <div class="profile_comment"><c:out value="${ blog_profile.statement_msg }"/></div>
            <div class="profile_follow">
                <div>
                    <span class="f_title"><a href="">팔로워</a></span><span class="f_num"><c:out value="${ blog_profile.follower_cnt }"/></span>
                    <span class="f_title"><a href="">팔로잉</a></span><span class="f_num"><c:out value="${ blog_profile.following_cnt }"/></span>
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
                <input type="text" id="search_txt" onkeyup="enterkey()" class="input_search" placeholder="검색어를 입력해주세요">
                <button onclick="searchBtn()" class="btn_search">검색</button>
            </div>
            <div>
            <c:if test="${ sessionScope.id==blog_profile.id }">
                <span><i class="fas fa-edit"></i>새 글 작성</span>
            </c:if>
            </div>
        </div> <!-- 검색, 새 글 작성 버튼 end -->
        
        <div class="blog_post">
            <div class="category">
                <span>전체 (<c:out value="${ blog_profile.post_cnt }"/>)</span>
                <ul>
                <c:forEach var="tag" items="${ tag_list }">
                    <li onclick="javascript:location.href='blog.do?tag=${ tag.tag_name }'"><c:out value="${ tag.tag_name }"/>(<c:out value="${ tag.tag_cnt }"/>)</li>
                </c:forEach>
                </ul>
            </div>
            
            <div id="post_list" class="post_list">
	            <!-- 포스트가 0건인 경우 -->
		        <c:if test="${ empty post_list }">
		        	<div class="no_post">글이 없습니다. 첫 글을 작성해보세요!</div>
		        </c:if>
		        
	            <c:forEach var="post" items="${ post_list }">
	                <div class="post">
	                    <div class="post_img"><img src="${ post.thumbnail }"></div>
	                    <div class="post_title"  onclick="javascript:location.href ='/${ blog_profile.id }/blog/post.do?post=${ post.post_num }'">
	                    <c:out value="${ post.post_title }"/>
	                    </div>
	                    
	                     <div class="post_content">
	                          <c:if test="${ fn:length(post.post_content) <= 20 }">${ post.post_content }</c:if>
	                     <c:if test="${ fn:length(post.post_content) > 20 }">${ post.post_content.substring(0,20).concat('···') }</c:if>
	                    </div>
	                    
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
        </div>
        
       	<!-- 더보기 버튼 -->
        <div id="viewMore" class="more_div">
        	<c:if test="${ end_num lt total_cnt }">
				<span class="more_btn" onclick="morePost(${cur_page+1},'${search_word}','${search_tag}')">더 보기</span>
        	</c:if>
        </div>
    </section>
    
   	<!--푸터 -->
	<c:import url="../common/common_footer.jsp" />
</body>
<script src="http://localhost/js/control_navbar.js"></script>

</html>