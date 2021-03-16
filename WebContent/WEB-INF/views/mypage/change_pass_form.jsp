<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage/style.css" />
    <h3>바꿀 비밀번호 입력</h3>
    <form action="" method="post">
	    <div class="social">
	      <div class="input">
	        <input type="text" class="form-control chk_pass_input" id="change_pass" placeholder="새로운 비밀번호 입력"/>
	      </div>
	    </div>
	    <h3>바꿀 비밀번호 확인</h3>
	    <div class="social">
	      <div class="input">
	        <input type="text" class="form-control chk_pass_input" id="change_pass_chk" placeholder="비밀번호 확인"/>
	      </div>
	    </div>
	    <button class="chk_pass_btn">확인</button>
	</form>