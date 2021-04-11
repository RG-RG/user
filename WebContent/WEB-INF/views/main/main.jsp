<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
 	<link rel="icon" href="./images/icon/favicon.ico" />
	<link rel="shortcut icon" href="favicon.ico" />
    <title>Co-doing</title>
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
	<link rel="icon" href="./images/icon/favicon.ico" />
	<link rel="shortcut icon" href="favicon.ico" />
    <link rel="stylesheet" href="./css/reset.css">
    <link rel="stylesheet" href="./css/main/user_main.css">
    <link rel="stylesheet" href="./css/common/common_header_footer.css">
    <link rel="stylesheet" href="./css/common/see_more_btn.css">

<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>

<script type="text/javascript">
/* 익스플로러 구분 */
var agent = navigator.userAgent.toLowerCase();

if ( (navigator.appName == 'Netscape' && navigator.userAgent.search('Trident') != -1) || (agent.indexOf("msie") != -1) ) {
	alert("크롬 브라우저에 최적화되어 있습니다\n크롬 브라우저를 사용해주세요");
}

$(function(){
/* 처음 로드 될때 본문 마크다운 자르는 부분 */
	<c:forEach var="tmp" items="${ main_list }">
		var origin_text = $("#post_content${tmp.post_num}").text();
		console.log(origin_text);
		var temp = parseMd(origin_text);
		console.log(temp)
		$("#post_content${tmp.post_num}").text(temp)
	</c:forEach>
});//ready
	

/* 마크다운 제거 함수 */
function parseMd(md){
	  //ul
	  md = md.replace(/^\s*\n\*/gm, '');
	  md = md.replace(/^(\*.+)\s*\n([^\*])/gm, '');
	  md = md.replace(/^\*(.+)/gm, '');
	  //ol
	  md = md.replace(/^\s*\n\d\./gm, '');
	  md = md.replace(/^(\d\..+)\s*\n([^\d\.])/gm, '');
	  md = md.replace(/^\d\.(.+)/gm, '');
	  //blockquote
	  md = md.replace(/^\>(.+)/gm, '');
	  //h
	  md = md.replace(/[\#]{6}(.+)/g, '');
	  md = md.replace(/[\#]{5}(.+)/g, '');
	  md = md.replace(/[\#]{4}(.+)/g, '');
	  md = md.replace(/[\#]{3}(.+)/g, '');
	  md = md.replace(/[\#]{2}(.+)/g, '');
	  md = md.replace(/[\#]{1}(.+)/g, '');
	  //alt h
	  md = md.replace(/^(.+)\n\=+/gm, '');
	  md = md.replace(/^(.+)\n\-+/gm, '');
	  //images
	  md = md.replace(/\!\[([^\]]+)\]\(([^\)]+)\)/g, '');
	  //links
	  md = md.replace(/[\[]{1}([^\]]+)[\]]{1}[\(]{1}([^\)\"]+)(\"(.+)\")?[\)]{1}/g, '');
	  //font styles
	  md = md.replace(/[\*\_]{2}([^\*\_]+)[\*\_]{2}/g, '');
	  md = md.replace(/[\*\_]{1}([^\*\_]+)[\*\_]{1}/g, '');
	  md = md.replace(/[\~]{2}([^\~]+)[\~]{2}/g, '');
	  //pre
	  md = md.replace(/^\s*\n\`\`\`(([^\s]+))?/gm, '');
	  md = md.replace(/^\`\`\`\s*\n/gm, '');
	  //code
	  md = md.replace(/[\`]{1}([^\`]+)[\`]{1}/g, '');
	  //p
	  md = md.replace(/^\s*(\n)?(.+)/gm, function(m){
	    return  /\<(\/)?(h\d|ul|ol|li|blockquote|pre|img)/.test(m) ? m : ''+m+'';
	  });
	  //strip p from pre
	  md = md.replace(/(\<pre.+\>)\s*\n\<p\>(.+)\<\/p\>/gm, '');
	  
	  if(md.length>25){
		  md = md.substring(0,25).concat('...')
	  }//end if
	  return md;
}//parseMd
	
function more_page(page){
	$.ajax({
		url:"main/more.do?cur_page="+page,
		type:"POST",
		dataType:"JSON",
		error:function(xhr){
			alert("에러입니다");
			console.log(xhr.status+" / "+xhr.statusText);
		},
		success:function(jsonObj){
	      	if(jsonObj.flag=="success"){
				//성공한 경우 "더보기"한 내용을 붙여주는 곳
				//반복문으로 돌면서 div를 추가해줘야 함
				var output = ""
				$.each(jsonObj.main_list, function(idx, list){
					
					var img_url = '<div class="post_img"></div>';
					if ( list.thumbnail != null ) {
						img_url = '<div class="post_img" style="background-image: url('+ list.thumbnail +')"></div>';
					}
					
					output += '<div class="post">';
						output += img_url
						output += '<div class="post_title"><a href="../'+ list.id +'/blog/post.do?post='+ list.post_num +'">'+ list.post_title +'</a></div>'
						output += '<div class="post_content" id="post_content'+list.post_num+'">'
						output += parseMd(list.post_content)
						output += '</div>'
						
						output += '<div class="post_info">'
							output +='<span class="post_writer"><a href="../'+ list.id +'/blog.do">by. '+ list.id +'</a></span> ・ <span class="post_date">'+ list.input_date +'</span>'
						output +='</div>';
					output +='</div>';
					
				});//each
				$("#section_main").append(output);
				
				//더보기 버튼
				var more=''
				if(jsonObj.total_cnt > jsonObj.end_num) {
					more='<span id="more_btn" class="more_btn" onclick="more_page('+(jsonObj.cur_page+1)+')">더 보기</span>'
				}
				
				$("#more_div").html(more)
	      	}else{
	      		alert("문제가 발생하였습니다. 다시 시도해주세요.")
	      	}//end else
		}//success
	});//ajax
}//more page end
	
function handleEnter() {
	var searchText = document.getElementById('search_input').value;
	if (window.event.keyCode == 13){
		//console.log(searchText);
		location.href="main.do?search="+searchText;
	}
}
</script>

</head>
<body>
	<!-- 헤더(사이드바) -->
	<c:import url="../common/common_header.jsp" />

    <div id="section_main" class="section_main">
        <!-- 타이틀 -->
        <div class="main_title">전체 글</div>
        
        <!-- 포스트가 0건인 경우 -->
        <c:if test="${ empty main_list }">
        	<div class="no_post">글이 없습니다. 첫 글을 작성해보세요!</div>
        </c:if>
        
        <!-- 포스트 -->
        <c:forEach var="userMain" items="${ main_list }">
        <div class="post">
        	<c:if test="${ empty userMain.thumbnail }">
        		<div class="post_img"></div>
        	</c:if>
        	<c:if test="${ not empty userMain.thumbnail }">
	            <div class="post_img" style="background-image: url(${ userMain.thumbnail })"></div>
        	</c:if>
            <div class="post_title"><a href="../${ userMain.id }/blog/post.do?post=${ userMain.post_num }">${ userMain.post_title }</a></div>
            <div class="post_content" id="post_content${ userMain.post_num }">${ userMain.post_content }</div>
            <div class="post_info">
                <span class="post_writer"><a href="../${ userMain.id }/blog.do">by. ${ userMain.nickname }</a></span> ・ 
                <span class="post_date">${ userMain.input_date }</span>
                 	 ・ <span class="post_like">💗 ${ userMain.like_cnt }</span>
            </div>
        </div>
        </c:forEach>
    </div>
   	
   	<!-- 더보기 버튼 -->
   	<div id="more_div" class="more_div">
   		<c:if test="${ end_num lt total_cnt }">
	   		<span id="more_btn" class="more_btn" onclick="more_page(${ cur_page + 1})">더 보기</span>
   		</c:if> 
	</div>
	
	<!--푸터 -->
	<c:import url="../common/common_footer.jsp" />
</body>

    <script src="./js/control_navbar.js"></script>
</html>