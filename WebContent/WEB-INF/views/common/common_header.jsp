<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
				<li><span><a href="">LOGIN</a></span> / <span><a href="">JOIN</a></span></li>
			</c:if>
            <!-- 로그인 됐을 때 : 내 블로그, 보관함 -->
			<c:if test="${ not empty sessionScope.id }">
				<li><span><a href="">내 블로그</a></span> / <span><a href="">보관함</a></span></li>
			</c:if>
            
            <li><a href="main.do?sort=like">인기순 보기</a></li>
            <li><a href="main.do?sort=date">최신순 보기</a></li>
            <li>search</li>
        </ul>
    </section>