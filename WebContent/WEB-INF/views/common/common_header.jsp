<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 로그인 / 회원가입 모달 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />
<!-- Global site tag (gtag.js) - Google Analytics -->
<script async src="https://www.googletagmanager.com/gtag/js?id=UA-192721092-1"></script>
<script>
  window.dataLayer = window.dataLayer || [];
  function gtag(){dataLayer.push(arguments);}
  gtag('js', new Date());
  gtag('config', 'UA-192721092-1');
</script>
<!--  사용자 common header 사이드바  -->

    <section class="section_header">
        <div class="header_nav">
            <span class="navBtn btn_nav_open"><i class="fas fa-bars"></i></span>
        </div>
        <ul class="nav_bar">
        	<!-- 로고, 닫힘버튼 -->
            <li><span><a href="/main.do"><img src="/images/codoing2.png" /></a></span><span class="navBtn btn_nav_close"><i class="fas fa-chevron-left"></i></span></li>
            <!-- 로그인 안됐을 때 : 로그인, 회원가입 -->
       		<c:if test="${ empty sessionScope.id }">
				<li><span><a href="/member/login.html" rel="modal:open">LOGIN</a></span> / <span><a href="/member/login.html" rel="modal:open">JOIN</a></span></li>
			</c:if>
            <!-- 로그인 됐을 때 : 내 블로그, 보관함, 마이페이지 -->
			<c:if test="${ not empty sessionScope.id }">
				<li><span><a href="/${ sessionScope.id }/blog.do">내 블로그</a></span> / <span> <a href="/like.do">내 보관함</a></span></li>
				<li><span><a href="/mypage.do">마이 페이지</a></span> / <span><a href="/post_form.do">새 글 작성</a></span></li>
				<li></li>
			</c:if>
            
            <li><a href="/main.do?sort=input_date">최신순 보기</a></li>
            <li><a href="/main.do?sort=like_cnt">인기순 보기</a></li>
            <li><input type="text" class="search_input" id="search_input" onkeypress="handleEnter()"><button id="search_btn"><i class="fas fa-search"></i></button></li>
            <!-- 로그인 됐을 때 : 로그아웃 -->
			<c:if test="${ not empty sessionScope.id }">
				<li><span><a href="/logout.do">로그아웃</a></span></li>
			</c:if>
        </ul>
        <div class="close_bar hidden"></div>
    </section>