<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RGRG</title>
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    <link rel="stylesheet" href="http://localhost/rgrg_user/css/member/reset.css">
    <link rel="stylesheet" href="http://localhost/rgrg_user/css/member/style.css"> 
    <link rel="stylesheet" type="text/css" href="https://cdn.rawgit.com/moonspam/NanumSquare/master/nanumsquare.css">
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<style type="text/css">
@font-face {
    font-family: 'IBMPlexSansKR-Regular';
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_20-07@1.0/IBMPlexSansKR-Regular.woff') format('woff');
    font-weight: normal;
    font-style: normal;
}
.section_main{ margin:0 auto; width: 70rem; margin-top: 18rem }
#containerTitle{ font-size: 3rem; font-weight: bold; }
#containerContent{ margin-top: 6rem }
#memberForm{ margin: 0 auto; width: 60rem }
.mb-3{ margin-top: 2rem; }
.input-group-text{ background-color: #FFFFFF; border-right: none; font-size: 1.5rem; padding: 2.5rem; height: 1rem; color: #333;}
.form-control{ border-left: none; height: 5.2rem; font-size: 1.5rem; }
#idLabel, #mailLabel, #nickLabel{ padding-right: 6.9rem }
#passLabel{ padding-right: 5.6rem }
#pass, #passChk{ font-family: IBMPlexSansKR-Regular; }
#buttons{ margin:0 auto; margin-top: 6rem; width: 30rem }
.btn-secondary{ margin-left: 2rem; }
.btn-light, .btn-secondary{ width: 12rem; height: 4rem; font-size: 1.3rem !important; }
</style>
<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
	
	$("#id").keydown(function(evt){
		if( evt.which == 13 ){
			//아이디 중복 검사 ajax
		}//end if
	});//keydown

	$("#id").blur(function(evt){
			//아이디 중복 검사 ajax
	});//blur
	
	$("#auth_mail").keydown(function(evt){
		if( evt.which == 13 ){
			//이메일 중복 검사 ajax
		}//end if
	});//keydown
	
	$("#auth_mail").blur(function(evt){
			//이메일 중복 검사 ajax
	});//blur
	
/* 	$("#pass").keyup(function(evt){
		if( evt.which != 13 ){
			//비밀번호 일치 검사
		}//end if
	});//keyup
	
	$("#passChk").keyup(function(evt){
		if( evt.which != 13 ){
			//비밀번호 일치 검사
		}//end if
	});//keyup */
	
	$(".btn-secondary").click(function(){
		if ($("#id").val().trim()==""){
			alert("아이디를 입력해주세요.");
			$("#id").focus();
			return;
		}//end if
		if ($("#auth_mail").val().trim()==""){
			alert("이메일을 입력해주세요.");
			$("#auth_mail").focus();
			return;
		}//end if
		if ($("#nickname").val().trim()==""){
			alert("닉네임을 입력해주세요.");
			$("#nickname").focus();
			return;
		}//end if
		if ($("#pass").val().trim()==""){
			alert("비밀번호를 입력해주세요.");
			$("#pass").focus();
			return;
		}//end if
		if ($("#passChk").val().trim()==""){
			alert("비밀번호를 다시 한 번 입력해주세요.");
			$("#passChk").focus();
			return;
		}//end if
		if ($("#pass").val() != $("#passChk").val()){
			alert("비밀번호가 일치하지 않습니다.");
			$("#passChk").focus();
			return;
		}//end if
		//$("#memberForm").submit();
	});//click
	
});//ready

function chkEmail(str) {
    var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    if (regExp.test(str)) return true;
    else return false;
}//chkEmail

</script>
</head>
<body style="font-family: nanumsquare">
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

    <section class="section_main">
    	<div id="container">
        	<div id="containerTitle">
        		RGRG와 함께 블로그를 시작해보세요.
        	</div>
        	<div id="containerContent">
        		<form id="memberForm" action="join.do" method="post">
	  				<div class="input-group mb-3">
					  <span class="input-group-text" id="idLabel">아이디</span>
					  <input type="text" id="id" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" placeholder="회원님이 사용할 아이디를 입력해주세요.">
					</div>
	  				<div class="input-group mb-3">
					  <span class="input-group-text" id="mailLabel">이메일</span>
					  <input type="text" id="auth_mail" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" placeholder="중요한 알림을 수신할 이메일을 입력해주세요.">
					</div>
	  				<div class="input-group mb-3">
					  <span class="input-group-text" id="nickLabel">닉네임</span>
					  <input type="text" id="nickname" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" placeholder="회원님을 나타내는 닉네임을 입력해주세요.">
					</div>
	  				<div class="input-group mb-3">
					  <span class="input-group-text" id="passLabel">비밀번호</span>
					  <input type="password" id="pass" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" placeholder="회원님이 사용할 비밀번호를 입력해주세요.">
					</div>
	  				<div class="input-group mb-3">
					  <span class="input-group-text">비밀번호 확인</span>
					  <input type="password" id="passChk" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" placeholder="비밀번호를 다시 한 번 입력해주세요.">
					</div>
        		</form>
				<div id="buttons">
					<button type="button" class="btn btn-light" onclick="javascript: history.back();">이전</button>
					<button type="button" class="btn btn-secondary">다음</button>
				</div>
        	</div>
        </div>
    </section>
    
</body>
<script src="http://localhost/rgrg_user/js/control_navbar.js"></script>

</html>