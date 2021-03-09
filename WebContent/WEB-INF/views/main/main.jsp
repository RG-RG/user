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

    
    <script type="text/javascript">
		$(function(){
			
		});//ready
	</script>
</head>

<body>
	<!-- 헤더(사이드바) -->
	<c:import url="../common/common_header.jsp" />

    <section class="section_main">
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
    </section>
	
	<!--푸터 -->
	<c:import url="../common/common_footer.jsp" />
</body>

    <script src="http://localhost/rgrg_user/js/control_navbar.js"></script>
</html>