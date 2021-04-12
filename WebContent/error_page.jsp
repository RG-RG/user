<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%  response.setStatus(HttpServletResponse.SC_OK); %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Co-doing</title>
    
	<link rel="icon" href="/images/icon/favicon.ico" />
	<link rel="shortcut icon" href="favicon.ico" />

    <link rel="stylesheet" href="/css/reset.css">
    <link rel="stylesheet" href="/css/common/common_header_footer.css">
    <link rel="stylesheet" href="/css/common/error_page.css">
</head>
<body>
    <h1>SORRY!</h1>
    <div class="comment">
    	<c:if test="${requestScope['javax.servlet.error.status_code'] == 404}">
        	<p class="error_msg">페이지를 찾을 수 없습니다😥</p> 
        </c:if>
        
       	<c:if test="${requestScope['javax.servlet.error.status_code'] == 500}">
        	<p class="error_msg">서버에 오류가 발생했습니다😥</p> 
        </c:if>
        
        
      	<p>오류가 계속된다면 <a href="mailto:codoingofficial@gmail.com">codoingofficial@gmail.com</a>으로 연락주시길 바랍니다.</p> 
      	<p><a href="/main.do" class="back_main">메인으로 돌아가기</a></p>
    </div>
    
   	 <c:import url="WEB-INF\views\common\common_footer.jsp" />
</body>
</html>