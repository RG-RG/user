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

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" href="../../../css/reset.css">
<link rel="stylesheet" href="../../../css/member/style.css">
<link rel="stylesheet" href="../../../css/common/common_header_footer.css">

<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){

	var agreeChk = document.getElementById("flexCheckDefault");

	$(".form-check-label").click(function(){
		if (!$(agreeChk).is(":checked")){
			$(agreeChk).prop("checked", true);
		} else {
			$(agreeChk).prop("checked", false);
		}//end else
	});//click
	
	$(".btn-light").click(function(){
		location.href="/main.do";
	});//click
	
	$(".btn-secondary").click(function(){
		if (!$(agreeChk).is(":checked")){
			alert("약관에 동의해주세요.");
			return;
		}//end if
		location.href="/join_form.do";
	});//click
	
});//ready
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
  				<div class="form-check">
  					<input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
					<label class="form-check-label">서비스 이용약관 동의 (필수)</label>
				</div>
        		<div class="mb-3">
  					<textarea class="form-control" id="exampleFormControlTextarea1" rows="3" readonly="readonly" disabled="disabled">
제1조 (목적)
본 약관은 회원(본 약관에 동의한 자를 말하며 이하 "회원"이라고 합니다)이 주식회사 카카오(이하 "회사"라고 합니다)가 제공하는 티스토리(Tistory) 서비스(이하 "서비스"라고 합니다)를 이용함에 있어 회사와 회원의 권리·의무 및 책임사항을 규정함을 목적으로 합니다.
  					</textarea>
				</div>
				<div id="buttons">
					<button type="button" class="btn btn-light">취소</button>
					<button type="button" class="btn btn-secondary">다음</button>
				</div>
        	</div>
        </div>
    </section>
    
    <jsp:include page="../common/common_footer.jsp"/>
</body>
<script src="../../../js/control_navbar.js"></script>

</html>