<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>

<html lang="en">


<head>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${ blog_profile.blog_name }</title>
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

	<!-- Google CDN -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
	
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	
	<link rel="stylesheet" href="../../../css/reset.css">
	<link rel="stylesheet" href="../../../css/common/see_more_btn.css">
	<link rel="stylesheet" href="../../../css/common/common_header_footer.css">
	<link rel="stylesheet" href="../../../css/blog/myBlog_main.css">
	
<script type="text/javascript">
	$(function(){
		/* 처음 로드 될때 본문 마크다운 자르는 부분 */
		<c:forEach var="tmp" items="${ post_list }">
			var temp=parseMd($("#post_content${tmp.post_num}").text())
			$("#post_content${tmp.post_num}").text(temp)
		</c:forEach>
		    
	});//ready

	/* 마크다운 제거 함수 */
	function parseMd(md){
		  //ul
		  md = md.replace(/^\s*\n\*/gm, '');
		  md = md.replace(/^(\*.+)\s*\n([^\*])/gm, '');
		  md = md.replace(/^\*(.+)/gm, '');
		  //ol
		  md = md.replace(/^\s*\n\d\./gm, '');
		  md = md.replace(/^(\d\..+)\s*\n([^\d\.])/gm, '');
		  md = md.replace(/^\d\.(.+)/gm, '');
		  //blockquote
		  md = md.replace(/^\>(.+)/gm, '');
		  //h
		  md = md.replace(/[\#]{6}(.+)/g, '');
		  md = md.replace(/[\#]{5}(.+)/g, '');
		  md = md.replace(/[\#]{4}(.+)/g, '');
		  md = md.replace(/[\#]{3}(.+)/g, '');
		  md = md.replace(/[\#]{2}(.+)/g, '');
		  md = md.replace(/[\#]{1}(.+)/g, '');
		  //alt h
		  md = md.replace(/^(.+)\n\=+/gm, '');
		  md = md.replace(/^(.+)\n\-+/gm, '');
		  //images
		  md = md.replace(/\!\[([^\]]+)\]\(([^\)]+)\)/g, '');
		  //links
		  md = md.replace(/[\[]{1}([^\]]+)[\]]{1}[\(]{1}([^\)\"]+)(\"(.+)\")?[\)]{1}/g, '');
		  //font styles
		  md = md.replace(/[\*\_]{2}([^\*\_]+)[\*\_]{2}/g, '');
		  md = md.replace(/[\*\_]{1}([^\*\_]+)[\*\_]{1}/g, '');
		  md = md.replace(/[\~]{2}([^\~]+)[\~]{2}/g, '');
		  //pre
		  md = md.replace(/^\s*\n\`\`\`(([^\s]+))?/gm, '');
		  md = md.replace(/^\`\`\`\s*\n/gm, '');
		  //code
		  md = md.replace(/[\`]{1}([^\`]+)[\`]{1}/g, '');
		  //p
		  md = md.replace(/^\s*(\n)?(.+)/gm, function(m){
		    return  /\<(\/)?(h\d|ul|ol|li|blockquote|pre|img)/.test(m) ? m : ''+m+'';
		  });
		  //strip p from pre
		  md = md.replace(/(\<pre.+\>)\s*\n\<p\>(.+)\<\/p\>/gm, '');
		  if(md.length>30){
			  md=md.substring(0,30).concat('...')
		  }//end if
		  return md;
	}//parseMd

	if(${ empty blog_profile}){
		alert("조회 중 문제가 발생하였습니다. 다시 시도해주세요.")
		location.href=history.back();
	}//end if

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
			error:function(xhr){
				alert("에러");
				console.log(xhr.status+" / "+xhr.statusText);
			},
			success:function(jsonObj){
				
		      	if(jsonObj.flag=="success"){
					var output='';
					$.each(jsonObj.post_list, function(idx, list){
						output+='<div class="post">';
						output+='<div class="post_img" style="background-image: url('+ list.thumbnail +')"></div>';
						output+='<div class="post_title"  onclick="javascript:location.href =\'/${ blog_profile.id }/blog/post.do?post='+list.post_num+'\'">';
						output+=list.post_title;
						output+='</div>';
						output+='<div class="post_content">'+ parseMd(list.post_content) +'</div>';
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
</head>
<body>
	<!-- 헤더(사이드바) -->
	<c:import url="../common/common_header.jsp" />

    <section class="section_main">

        <!-- 프로필 화면 -->
        <div class="my_profile">
            <div class="profile_img"><img src="${ blog_profile.profile_img }"></div>
            <div class="profile_nickname"><c:out value="${ blog_profile.nickname }"/></div>
            <div class="profile_comment"><c:out value="${ blog_profile.statement_msg }"/></div>
            <div class="profile_follow">
                <div>
                    <a href="/${ blog_profile.id }/get_follower.do"><span class="f_title">팔로워</span></a><span class="f_num"><c:out value="${ blog_profile.follower_cnt }"/></span>
                    <a href="/${ blog_profile.id }/get_following.do"><span class="f_title">팔로잉</span></a><span class="f_num"><c:out value="${ blog_profile.following_cnt }"/></span>
                </div>
                <div>
                	<!-- 본인이 아닐 경우에만 follow 버튼을 보여줌 -->
					<c:if test="${ post_profile.id != sessionScope.id }">
						<span id="btn_follow" class="btn_follow"> 
							<!-- 팔로우 하는 경우에는 언팔로우를, 아직 팔로우 하지 않은 사람이라면 팔로우를 보여줌 -->
							<c:if test="${ follow_flag }">unfollow</c:if>
							<c:if test="${ not follow_flag }">follow</c:if>
						</span>
					</c:if>
                </div>
            </div>
            <div></div>
            <div>
                <a href="${ blog_profile.github }"><i class="fab fa-github"></i></a>
                <a href="${ blog_profile.website }"><i class="fas fa-link"></i></a>
                <a href="${ blog_profile.visible_email }"><i class="fas fa-link"></i></a>
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
                <a href="/post_form.do"><span><i class="fas fa-edit"></i>새 글 작성</span></a>
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
	                    <div class="post_img" style="background-image: url(${ post.thumbnail })"></div>
	                    <div class="post_title"  onclick="javascript:location.href ='/${ blog_profile.id }/blog/post.do?post=${ post.post_num }'">
	                    <c:out value="${ post.post_title }"/>
	                    </div>
	                    <div id="post_content${post.post_num}" class="post_content">${ post.post_content }</div>
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
<script src="../../../js/control_navbar.js"></script>

</html>