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
#containerContent{ margin-top: 4rem }
#emailDiv{ padding-left: 7rem }
.form-label{ font-size: 1.5rem; font-weight: bold }
.form-control { font-size: 1.5rem; padding: 2.3rem; width: 50rem; margin-top: 0.5rem; display: inline; margin-right: 3rem }
#auth_email, #auth_msg{ vertical-align: middle; }
#sendBtn, #confirmBtn{ vertical-align: middle; margin-top: 0.3rem }
#authMsgDiv{ margin-top: 4rem; padding-left: 7rem }
.mb-3{ margin-top: 0.5rem }
#buttons{ margin:0 auto; margin-top: 7.5rem; width: 12rem }
.btn-secondary{ width: 12rem; height: 4.5rem; font-size: 1.5rem !important; margin-left: 0rem }
#nextBtn{ background-color: #000000 }
#container{ margin-bottom: 10.2rem }
</style>
<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

<script type="text/javascript">
$(function(){

	var authFlag = false;
	
	$("#auth_email").keydown(function(evt){
		if( evt.which == 13 ) {
			$("#auth_msg").focus();
		}//end if
	});//keydown
	
	$("#auth_msg").keydown(function(evt){
		if( evt.which == 13 ) {
			if (!authFlag) {
				alert("이메일 인증을 완료해주세요.");
			}else{
				$("#findIdForm").submit();
			}//end else
		}//end if
	});//keydown
	
	$("#sendBtn").click(function(){
		var auth_email = $("#auth_email").val();
		
		//null 검사
		if (auth_email.trim() == ""){
			alert("이메일을 입력해주세요.");
			$("#auth_email").focus()
			return;
		}//end if
		
		//이메일 유효성 검사
		if (!chkEmail(auth_email)){
			alert("유효하지 않은 이메일 형식입니다.");
			return;
		}//end if
		
		//이메일 확인 및 인증번호 확인
		$.ajax({
			url : "/find_id_chk.do",
			type : "GET",
			data : "auth_email="+auth_email,
			dataType : "JSON",
			error : function(xhr) {
				alert("error : " + xhr.status + " / " + xhr.statusText);
			},
			success : function(json) {
				if(!json.id_chk_email){
					alert("해당 이메일과 일치하는 가입정보가 없습니다.");
				}else{
					alert("이메일로 인증번호를 발송했습니다.");
					$.ajax({
						url : "/send_mail.do",
						type : "GET",
						data : "from=codoingofficial@gmail.com&to="+auth_email,
						dataType : "JSON",
						async : false,
						error : function(xhr) {
							alert("error : " + xhr.status + " / " + xhr.statusText);
						},
						success : function(json) {
							key = json.key;
						}//success
					});//ajax
				}//end else
			}//success
		});//ajax
	});//click
	
	$("#confirmBtn").click(function(){
		//null 검사
		if ($("#auth_msg").val().trim() == ""){
			alert("인증번호를 입력해주세요.");
			$("#auth_msg").focus();
			return;
		}//end if
		
		//인증번호 일치 검사
		if( $("#auth_msg").val().trim() != key ){
			alert("인증번호가 일치하지 않습니다. 인증번호를 재발송 해주세요.");
			$("#auth_msg").val('');
			$("#auth_msg").focus();
			key = "";
			return;
		} else {
			alert("인증번호를 확인했습니다.");
			authFlag = true;
		}//end else
	});//click
	
	$("#nextBtn").click(function(){
		if (!authFlag) {
			alert("이메일 인증을 완료해주세요.");
		}else{
			$("#findIdForm").submit();
		}//end else
	});//click
	
});//ready

function chkEmail(str) {
    var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
    if (regExp.test(str)) return true;
    else return false;
}//chkEmail

</script>
</head>
<body style="font-family: IBMPlexSansKR-Regular">
    <jsp:include page="../common/common_header.jsp"/>
    
    <section class="section_main">
    	<div id="container">
        	<div id="containerTitle">
        		아이디 찾기
        		<hr id="titleLine">
        	</div>
        	<div id="containerContent">
        		<form id="findIdForm" action="/find_id.do" method="POST">
        		<div id="emailDiv">
					<label for="exampleFormControlInput1" class="form-label">이메일</label>
					<div class="mb-3">
					  <input type="email" class="form-control" id="auth_email" name="auth_email" placeholder="가입하신 이메일을 입력해주세요." autocomplete="off">
					  <button type="button" class="btn btn-secondary" id="sendBtn">인증번호 전송</button>
					</div>
        		</div>
        		</form>
        		<div id="authMsgDiv">
				<label for="exampleFormControlInput1" class="form-label">인증번호</label>
					<div class="mb-3">
					  <input type="text" class="form-control" id="auth_msg" placeholder="메일로 발송된 인증번호를 입력해주세요." autocomplete="off">
					  <button type="button" class="btn btn-secondary" id="confirmBtn">확인</button>
					</div>
        		</div>
				<div id="buttons">
				  <button type="button" class="btn btn-secondary" id="nextBtn">다음</button>
				</div>
        	</div>
        </div>
    </section>
    
    <jsp:include page="../common/common_footer.jsp"/>
</body>
<script src="../../../js/control_navbar.js"></script>

</html>