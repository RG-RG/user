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
<link rel="stylesheet" href="../../../css/reset.css">
<link rel="stylesheet" href="../../../css/member/style.css"> 
<link rel="stylesheet" href="../../../css/common/common_header_footer.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<style type="text/css">
.section_main{ margin:0 auto; width: 80rem; margin-top: 30rem; height: 41.9rem }
#containerTitle{ font-size: 4rem; font-weight: bold; width: 62rem; margin: 0 auto; }
#msg{ font-size: 1.8rem; }
#containerContent{ margin:0 auto; margin-top: 6rem; width: 58.5rem }
#buttons{ margin:0 auto; margin-top: 8rem; width: 12rem }
.btn-secondary{ width: 12rem; height: 4rem; font-size: 1.5rem !important; margin: 0 auto }
#container { margin-bottom: 10rem }
</style>
<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
	$(".btn-secondary").click(function(){
		location.href="/main.do";
	});//click
});//ready
</script>
</head>
<body style="font-family: IBMPlexSansKR-Regular">
    <jsp:include page="../common/common_header.jsp"/>

    <section class="section_main">
    	<div id="container">
        	<div id="containerTitle">
        		🎉 Co-doing 가입이 완료되었습니다!
        	</div>
        	<div id="containerContent">
        		<span id="msg">이제 Co-doing에서 다른 글들을 살펴보고, 회원님의 글을 자유롭게 작성해보세요.</span>
				<div id="buttons">
					<button type="button" class="btn btn-secondary">메인으로</button>
				</div>
        	</div>
        </div>
    </section>
    
    <jsp:include page="../common/common_footer.jsp"/>
</body>
<script src="../../../js/control_navbar.js"></script>

</html>