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
.section_main{ margin:0 auto; width: 70rem; margin-top: 18rem }
#containerTitle{ font-size: 3rem; font-weight: bold; color: #000000}
#titleLine{ margin-top: 2rem; border-top: 0.2rem solid #000000 }
#containerContent{ margin-top: 6.5rem }
#modifyPassForm{ margin: 0 auto; width: 60rem }
.mb-3{ margin-top: 2rem; }
.input-group-text{ background-color: #FFFFFF; border-right: none; font-size: 1.5rem; padding: 2.5rem; height: 1rem; color: #333;}
.form-control{ border-left: none; height: 5.2rem; font-size: 1.5rem; }
#passChk, #passChkLabel{ margin-top: 1.5rem }
#passLabel{ padding-right: 5.6rem }
#passLenStatus{ font-size: 1.3rem; text-align: right; }
#buttons{ margin:0 auto; margin-top: 7rem; width: 12rem }
.btn-secondary{ width: 12rem; height: 4.5rem; font-size: 1.5rem !important; background-color: #000000; margin-left: 0rem }
#container{ margin-bottom: 15.3rem }
</style>
<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
	
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
	
	$("#confirmBtn").click(function(){
		chkNull();
	});//click
	
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
	
});//ready

function chkNull(){
	
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
	
	 if( $("#pass").val().length < 8 ){
		 alert("비밀번호는 최소 8자 이상이어야 합니다.");
		 return;
	 }//end if
	
	alert("비밀번호가 변경되었습니다.");
	$("#modifyPassForm").submit();	
	
}//chkNull

</script>
</head>
<body style="font-family: IBMPlexSansKR-Regular">
    <jsp:include page="../common/common_header.jsp"/>
    
    <section class="section_main">
    	<div id="container">
        	<div id="containerTitle">
        		비밀번호 변경
        		<hr id="titleLine">
        	</div>
        	<div id="containerContent">
        		<form id="modifyPassForm" action="/modify_pass.do" method="POST">
        		<input type="hidden" id="id" name="id" value="${ param.id }">
        		<input type="hidden" id="auth_email" name="auth_email" value="${ param.auth_email }">
  				<div class="input-group mb-3">
				  <span class="input-group-text" id="passLabel">비밀번호</span>
				  <input type="password" id="pass" name="pass" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" placeholder="새로운 비밀번호를 입력해주세요.">
				</div>
				<div id="passLenStatus"></div>
  				<div class="input-group mb-3">
				  <span class="input-group-text" id="passChkLabel">비밀번호 확인</span>
				  <input type="password" id="passChk" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" placeholder="비밀번호를 다시 한 번 입력해주세요.">
				</div>        		
        		</form>
				<div id="buttons">
				  <button type="button" class="btn btn-secondary" id="confirmBtn">확인</button>
				</div>
        	</div>
        </div>
    </section>
    
    <jsp:include page="../common/common_footer.jsp"/>
</body>
<script src="../../../js/control_navbar.js"></script>

</html>