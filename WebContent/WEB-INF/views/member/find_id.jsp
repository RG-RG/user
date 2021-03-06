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
#containerContent{ margin-top: 7rem }
#result{ font-size: 1.8rem; text-align: center; }
#buttons{ margin:0 auto; margin-top: 8rem; width: 12rem }
.btn-secondary{ width: 12rem; height: 4.5rem; font-size: 1.5rem !important; background-color: #000000; margin-left: 0rem }
#container{ margin-bottom: 25rem }
</style>
<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
	$("#mainBtn").click(function(){
		location.href = "/main.do";
	});//click
});//ready

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
        		<div id="result">
		        	회원님의 아이디는 <b>${ id_result }</b> 입니다.
        		</div>
				<div id="buttons">
				  <button type="button" class="btn btn-secondary" id="mainBtn">메인으로</button>
				</div>
        	</div>
        </div>
    </section>
    
    <jsp:include page="../common/common_footer.jsp"/>
</body>
<script src="../../../js/control_navbar.js"></script>

</html>