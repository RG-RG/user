<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Co-doing</title>
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

    <link rel="stylesheet" href="http://localhost/css/reset.css">
    <link rel="stylesheet" href="http://localhost/css/main/user_main.css">
    <link rel="stylesheet" href="http://localhost/css/common/common_header_footer.css">
    <link rel="stylesheet" href="http://localhost/css/common/see_more_btn.css">

<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    
<script type="text/javascript">
	function handleEnter() {
		var searchText = document.getElementById('search_input').value;
		if (window.event.keyCode == 13){
			//console.log(searchText);
			location.href="main?search="+searchText;
		}
	}
	
	function more_page(page){
		$.ajax({
			url:"main/"+page,
			type:"POST",
			dataType:"JSON",
			error:function(xhr){
				alert("에러입니다");
				console.log(xhr.status+" / "+xhr.statusText);
			},
			success:function(jsonObj){
		      	if(jsonObj.flag=="success"){
					//성공한 경우 "더보기"한 내용을 붙여주는 곳
					//반복문으로 돌면서 div를 추가해줘야 함
					var output = ""
					$.each(jsonObj.main_list, function(idx, list){
						
						var cur_content = list.post_content;
						if( list.post_content.length > 20 ) {
							cur_content = cur_content.substring(0,20).concat('···');
						} 
						
						output += '<div class="post">';
							output += '<div class="post_img" style="background-image: url('+ list.thumbnail +')"></div>'
							output += '<div class="post_title"><a href="../'+ list.id +'/blog/post/'+ list.post_num +'">'+ list.post_title +'</a></div>'
							output += '<div class="post_content">'+ cur_content +'</div>'
							output += '<div class="post_info">'
								output +='<span class="post_writer"><a href="../'+ list.id +'/blog">by. '+ list.id +'</a></span> ・ <span class="post_date">'+ list.input_date.substring(0,16) +'</span>'
							output +='</div>';
						output +='</div>';
					});//each
					$("#section_main").append(output);
					
					//더보기 버튼
					var more=''
					if(jsonObj.total_cnt > jsonObj.end_num) {
						more='<span id="more_btn" class="more_btn" onclick="more_page('+(page+1)+')">더 보기</span>'
					}
					
					$("#more_div").html(more)
		      	}else{
		      		alert("문제가 발생하였습니다. 다시 시도해주세요.")
		      	}//end else
			}//success
		});//ajax
	}
</script>
</head>

<body>
	<!-- 헤더(사이드바) -->
	<c:import url="../common/common_header.jsp" />

    <div id="section_main" class="section_main">
        <!-- 타이틀 -->
        <div class="main_title">전체 글</div>
        
        <!-- 포스트가 0건인 경우 -->
        <c:if test="${ empty main_list }">
        	<div class="no_post">글이 없습니다. 첫 글을 작성해보세요!</div>
        </c:if>
        
        <!-- 포스트 -->
        <c:forEach var="userMain" items="${ main_list }">
        <div class="post">
            <div class="post_img" style="background-image: url(${ userMain.thumbnail })"></div>
            <div class="post_title"><a href="../${ userMain.id }/blog/post/${ userMain.post_num }">${ userMain.post_title }</a></div>
            <div class="post_content"> 
            	<c:if test="${ fn:length(userMain.post_content) <= 20 }">${ userMain.post_content }</c:if>
            	<c:if test="${ fn:length(userMain.post_content) > 20 }">${ userMain.post_content.substring(0,20).concat('···') }</c:if>
            </div>
            <div class="post_info">
                <span class="post_writer"><a href="../${ userMain.id }/blog">by. ${ userMain.id }</a></span> ・ <span class="post_date">${ userMain.input_date.substring(0, 16) }</span>
            </div>
        </div>
        </c:forEach>
    </div>
   	
   	<!-- 더보기 버튼 -->
   	<div id="more_div" class="more_div">
   		<c:if test="${ end_num lt total_cnt }">
	   		<span id="more_btn" class="more_btn" onclick="more_page(${ cur_page + 1})">더 보기</span>
   		</c:if> 
	</div>
	
	<!--푸터 -->
	<c:import url="../common/common_footer.jsp" />
</body>

    <script src="http://localhost/js/control_navbar.js"></script>
</html>