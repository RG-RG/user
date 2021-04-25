<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Co-doing</title>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<link rel="icon" href="./images/icon/favicon.ico" />
<link rel="shortcut icon" href="favicon.ico" />

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<link rel="stylesheet" href="../../../css/reset.css">
<link rel="stylesheet" href="../../../css/member/style.css"> 
<link rel="stylesheet" href="../../../css/common/common_header_footer.css">

<style type="text/css">
.section_main{ margin:0 auto; width: 70rem; margin-top: 17.2rem }
#containerTitle{ font-size: 3rem; font-weight: bold; margin-left: 0.85rem }
#containerContent{ margin-top: 6.3rem }
#memberForm{ margin: 0 auto; width: 60rem }
#memberForm div {z-index: -1;}
.input-group-text{ background-color: #FFFFFF; border-right: none; font-size: 1.5rem; padding: 2.5rem; height: 1rem; color: #333;}
.form-control{ border-left: none; height: 5.2rem; font-size: 1.5rem; }
#idLabel, #mailLabel, #nickLabel{ padding-right: 6.9rem }
#idStatus, #mailStatus, #nickStatus, #passStatus, #passLenStatus{ font-size: 1.3rem; text-align: right; }
#passLabel{ padding-right: 5.6rem }
#container{ margin-bottom: 0rem }
#container { margin-bottom: 10rem }
</style>
<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
	
	$("#id").keydown(function(evt){
		var id = $("#id").val();
		if( evt.which == 13 || evt.which == 9 ){
			$.ajax({
				url : "/dup_id.do",
				type : "GET",
				data : "id="+id,
				dataType : "JSON",
				error : function(xhr){
					alert("error : " + xhr.status + " / " +xhr.statusText);
				},
				success : function(json){
					if ( json.dup_id ) { //아이디 중복
						$("#idStatus").css('color', '#D32F2F');
						$("#idStatus").html("이미 존재하는 아이디입니다.");
						$("#idStatus").css('display', 'block');
					} else {
						$("#idStatus").css('display', 'none');
					}//end else
				}//success
			});//ajax
		}//end if
		if (evt.which == 13){
			$("#auth_email").focus();
		}//end if
	});//keydown

 	$("#id").blur(function(){
		var id = $("#id").val();
		$.ajax({
			url : "/dup_id.do",
			type : "GET",
			data : "id="+id,
			dataType : "JSON",
			error : function(xhr){
				alert("error : " + xhr.status + " / " +xhr.statusText);
			},
			success : function(json){
				if ( json.dup_id ) { //아이디 중복
					$("#idStatus").css('color', '#D32F2F');
					$("#idStatus").html("이미 존재하는 아이디입니다.");
					$("#idStatus").css('display', 'block');
				} else {
					$("#idStatus").css('display', 'none');
				}//end else
			}//success
		});//ajax
	});//blur
	
	$("#auth_email").keydown(function(evt){
		var auth_email = $("#auth_email").val();
		if( evt.which == 13 || evt.which == 9 ){
			$.ajax({
				url : "/dup_email.do",
				type : "GET",
				data : "auth_email="+auth_email,
				dataType : "JSON",
				error : function(xhr){
					alert("error : " + xhr.status + " / " +xhr.statusText);
				},
				success : function(json){
					if ( json.dup_email ) { //이메일 중복
						$("#mailStatus").css('color', '#D32F2F');
						$("#mailStatus").html("이미 존재하는 이메일입니다.");
						$("#mailStatus").css('display', 'block');
					} else {
						$("#mailStatus").css('display', 'none');
					}//end else
				}//success
			});//ajax
		}//end if
		if (evt.which == 13){
			$("#nickname").focus();
		}//end if
	});//keydown
	
	$("#auth_email").blur(function(evt){
		var auth_email = $("#auth_email").val();
		$.ajax({
			url : "/dup_email.do",
			type : "GET",
			data : "auth_email="+auth_email,
			dataType : "JSON",
			error : function(xhr){
				alert("error : " + xhr.status + " / " +xhr.statusText);
			},
			success : function(json){
				if ( json.dup_email ) { //이메일 중복
					$("#mailStatus").css('color', '#D32F2F');
					$("#mailStatus").html("이미 존재하는 이메일입니다.");
					$("#mailStatus").css('display', 'block');
				} else {
					$("#mailStatus").css('display', 'none');
				}//end else
			}//success
		});//ajax
	});//blur
	
	$("#nickname").keydown(function(evt){
		if (evt.which == 13) {
			$("#pass").focus();
		}//end if
	});//keydown
	
	$("#pass").keyup(function(evt){
		if( $("#pass").val().length < 8 ){
			$("#passLenStatus").css('color', '#D32F2F');
			$("#passLenStatus").html("비밀번호는 최소 8자 이상이어야 합니다.");
			$("#passLenStatus").css('display', 'block');
		}else{
			$("#passLenStatus").css('display', 'none');
		}//end else
	});//keyup
	
	$("#pass").blur(function(evt){
		if( $("#pass").val().length < 8 ){
			$("#passLenStatus").css('color', '#D32F2F');
			$("#passLenStatus").html("비밀번호는 최소 8자 이상이어야 합니다.");
			$("#passLenStatus").css('display', 'block');
		}else{
			$("#passLenStatus").css('display', 'none');
		}//end else	
	});//blur
	
	$("#pass").keydown(function(evt){
		if (evt.which == 13) {
			$("#passChk").focus();
		}//end if
	});//keydown
	
	$("#passChk").keydown(function(evt){
		if (evt.which == 13) {
			chkNull();
		}//end if
	});//keydown

	$(".btn-secondary").click(function(){
		chkNull();
	});//click
	
});//ready

function chkEmail(str) {
    var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    if (regExp.test(str)) return true;
    else return false;
}//chkEmail

function chkNull(){
	
	//null 검사
	if ($("#id").val().trim()==""){
		alert("아이디를 입력해주세요.");
		$("#id").focus();
		return;
	}//end if
	if ($("#auth_email").val().trim()==""){
		alert("이메일을 입력해주세요.");
		$("#auth_email").focus();
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
	
	//중복 검사
	if ($("#idStatus").css('display') == 'block'){
		alert("이미 존재하는 아이디입니다.");
		$("#id").focus();
		return;
	}//end if
	
	if ($("#mailStatus").css('display') == 'block'){
		alert("이미 존재하는 이메일입니다.");
		$("#auth_email").focus();
		return;
	}//end if
	
	 if ( !chkEmail($("#auth_email").val()) ) { //이메일 유효성 검사
		alert("유효하지 않은 이메일 형식입니다.");
	 	return;
	 }//end if
	 
	 if( $("#pass").val().length < 8 ){
		 alert("비밀번호는 최소 8자 이상이어야 합니다.");
		 return;
	 }//end if
	
	$("#memberForm").submit();	
}//chkNull

</script>
</head>
<body style="font-family: IBMPlexSansKR-Regular">
    <jsp:include page="../common/common_header.jsp"/>
    
    <section class="section_main">
    	<div id="container">
        	<div id="containerTitle">
        		Co-doing과 함께 블로그를 시작해보세요.
        	</div>
        	<div id="containerContent">
        		<form id="memberForm" action="/join.do" method="post">
	  				<div class="input-group mb-3">
					  <span class="input-group-text" id="idLabel">아이디</span>
					  <input type="text" id="id" name="id" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" placeholder="회원님이 사용할 아이디를 입력해주세요." autocomplete="off">
					</div>
					<div id="idStatus"></div>
	  				<div class="input-group mb-3">
					  <span class="input-group-text" id="mailLabel">이메일</span>
					  <input type="text" id="auth_email" name="auth_email" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" placeholder="중요한 알림을 수신할 이메일을 입력해주세요." autocomplete="off">
					</div>
					<div id="mailStatus"></div>
	  				<div class="input-group mb-3">
					  <span class="input-group-text" id="nickLabel">닉네임</span>
					  <input type="text" id="nickname" name="nickname" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" placeholder="회원님을 나타내는 닉네임을 입력해주세요." autocomplete="off">
					</div>
	  				<div class="input-group mb-3">
					  <span class="input-group-text" id="passLabel">비밀번호</span>
					  <input type="password" id="pass" name="pass" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" placeholder="회원님이 사용할 비밀번호를 입력해주세요.">
					</div>
					<div id="passLenStatus"></div>
	  				<div class="input-group mb-3">
					  <span class="input-group-text">비밀번호 확인</span>
					  <input type="password" id="passChk" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" placeholder="비밀번호를 다시 한 번 입력해주세요.">
					</div>
					<div id="passStatus"></div>
        		</form>
				<div id="buttons">
					<button type="button" class="btn btn-light" onclick="javascript: history.back();">이전</button>
					<button type="button" class="btn btn-secondary">다음</button>
				</div>
        	</div>
        </div>
    </section>
    
    <jsp:include page="../common/common_footer.jsp"/>
</body>
<script src="../../../js/control_navbar.js"></script>

</html>