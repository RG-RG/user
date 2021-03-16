<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 로그인 / 회원가입 모달 -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.0.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-modal/0.9.1/jquery.modal.min.css" />

<!--  사용자 common header 사이드바  -->

    <section class="section_header">
        <div class="header_nav">
            <span class="btn btn_nav_open"><i class="fas fa-bars"></i></span>
        </div>
        <ul class="nav_bar">
        	<!-- 로고, 닫힘버튼 -->
            <li><span>GLOG</span><span class="btn btn_nav_close"><i class="fas fa-chevron-left"></i></span></li>
            <!-- 로그인 안됐을 때 : 로그인, 회원가입 -->
       		<c:if test="${ empty sessionScope.id }">
				<li><span><a href="/rgrg_user/member/login.html" rel="modal:open">LOGIN</a></span> / <span><a href="/rgrg_user/member/login.html" rel="modal:open">JOIN</a></span></li>
			</c:if>
            <!-- 로그인 됐을 때 : 내 블로그, 보관함 -->
			<c:if test="${ not empty sessionScope.id }">
				<li><span><a href="">내 블로그</a></span> / <span><a href="">내 보관함</a></span></li>
			</c:if>
            
            <li><a href="main?sort=view_cnt">조회순 보기</a></li>
            <li><a href="main?sort=input_date">최신순 보기</a></li>
            <li><input type="text" class="search_input" id="search_input" onkeypress="handleEnter()"><button id="search_btn"><i class="fas fa-search"></i></button></li>
            <!-- 로그인 됐을 때 : 로그아웃 -->
			<c:if test="${ not empty sessionScope.id }">
				<li><span><a href="/rgrg_user/rgrg/member/logout">로그아웃</a></span></li>
			</c:if>
        </ul>
    </section>