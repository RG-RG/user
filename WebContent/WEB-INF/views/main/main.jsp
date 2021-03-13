<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>사용자화면 - 메인</title>
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

    <link rel="stylesheet" href="http://localhost/rgrg_user/css/reset.css">
    <link rel="stylesheet" href="http://localhost/rgrg_user/css/main/user_main.css">

<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    
<script type="text/javascript">
	$(function(){
	});//ready
	
	
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
						output += '<div class="post">';
						output += '<div class="post_img" style="background-image: url('+list.thumbnail+')"></div>'
						output += '<div class="post_title">'+list.post_title+'</div>'
						output += '<div class="post_content">'+list.post_content+'</div>'
						output += '<div class="post_info">'
							output +='<span class="post_writer">by. '+list.id+'</span> ・ <span class="post_date">'+list.input_date+'</span>'
						output +='</div>';
						output +='</div>';
					});//each
					
					$("#section_main").append(output);
					
					var more='<div id="more_btn" onclick="more_page('+(page+1)+')">더보기</div>'
					if(jsonObj.last_flag){
						more='';
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
            <div class="post_title">${ userMain.post_title }</div>
            <div class="post_content"> ${ userMain.post_content }</div>
            <div class="post_info">
                <span class="post_writer">by. ${ userMain.id }</span> ・ <span class="post_date">${ userMain.input_date }</span>
            </div>
        </div>
        </c:forEach>
    </div>
   	<div id="more_div"><div id="more_btn" onclick="more_page(2)">더보기</div></div>
    
	
	<!--푸터 -->
	<c:import url="../common/common_footer.jsp" />
</body>

    <script src="http://localhost/rgrg_user/js/control_navbar.js"></script>
</html>