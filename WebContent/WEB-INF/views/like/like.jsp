<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">
#wrap{ width: 900px; height: 940px; margin: 0px auto; }

#header{ width: 900px; height: 220px; }
#container{ width:900px; height: 600px; position: relative;}
#footer{ width:900px; height: 120px; position: relative;}
</style>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">

<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){
	
});//ready
</script>
</head>
<body>

<div id="wrap">
	<div id="header">
	</div>
	<div id="container">
	좋아한 포스트<br/>
	<c:if test="${ empty like_list }">
		<tr>
		<td colspan="6" style="height: 80px; text-align: center; vertical-align: middle;">좋아한 포스트가 없습니다.</td>
		</tr>
	</c:if>
	<c:forEach var="like" items="${ like_list }">
	    <tr id="tableContent${ like.post_num }" class="tableContent">
	      <td style="vertical-align: middle; text-align: center;">
	      	<a href="${ like.post_num }" style="font-weight: bold;">
	      	<c:out value="${ like.post_title }"/></a><br/>
	      	<img alt="미리보기 이미지" src="/common/images/post/${ like.thumbnail }" style="width: 80px"/><br/>
	      	<c:out value="${ like.id }"/><br/>
	      	<c:out value="${ like.post_content }"/><br/>
	      	<c:out value="${ like.input_date }"/><br/>
	      	<c:out value="${ like.view_cnt }"/><br/>
	      </td>
	    </tr>
	</c:forEach>
	<a href="like/get_like.do?param_page=">더보기</a>
	</div>
	<div id="footer">
	</div>
</div>

</body>
</html>