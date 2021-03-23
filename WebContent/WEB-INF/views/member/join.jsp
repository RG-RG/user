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
<link rel="stylesheet" href="http://localhost/rgrg_user/css/reset.css">
<link rel="stylesheet" href="http://localhost/rgrg_user/css/member/style.css"> 
<link rel="stylesheet" href="http://localhost/rgrg_user/css/common/common_header_footer.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<style type="text/css">
.section_main{ margin:0 auto; width: 70rem; margin-top: 27rem }
#containerTitle{ font-size: 5rem; font-weight: bold; width: 63rem; margin: 0 auto; }
#msg{ font-size: 2rem; }
#containerContent{ margin:0 auto; margin-top: 4rem; width: 59rem }
#buttons{ margin:0 auto; margin-top: 7rem; width: 12rem }
.btn-secondary{ width: 12rem; height: 4rem; font-size: 1.5rem !important; }
</style>
<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
	$(".btn-secondary").click(function(){
		location.href="/rgrg_user/rgrg/main/main";
	});//click
});//ready
</script>
</head>
<body style="font-family: IBMPlexSansKR-Regular">
    <jsp:include page="../common/common_header.jsp"/>

    <section class="section_main">
    	<div id="container">
        	<div id="containerTitle">
        		RGRG 가입이 완료되었습니다.
        	</div>
        	<div id="containerContent">
        		<span id="msg">RGRG에서 다른 글들을 살펴보고, 회원님의 글을 자유롭게 작성해보세요.</span>
				<div id="buttons">
					<button type="button" class="btn btn-secondary">메인으로</button>
				</div>
        	</div>
        </div>
    </section>
    
    <jsp:include page="../common/common_footer.jsp"/>
</body>
<script src="http://localhost/rgrg_user/js/control_navbar.js"></script>

</html>