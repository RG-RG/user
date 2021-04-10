<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>보관함</title>
    
    <link rel="icon" href="../../../images/icon/favicon.ico" />
	<link rel="shortcut icon" href="favicon.ico" />
    
    <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
    
   	<link rel="icon" href="../../../images/icon/favicon.ico" />
	<link rel="shortcut icon" href="favicon.ico" />

    <link rel="stylesheet" href="../../../css/reset.css">
    <link rel="stylesheet" href="../../../css/blog/like_post.css">
    <link rel="stylesheet" href="../../../css/common/common_header_footer.css">
    <link rel="stylesheet" href="../../../css/common/see_more_btn.css">
</head>
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous"> -->

<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>

<!-- toast ui -->
<link rel="stylesheet"
	href="https://uicdn.toast.com/editor/latest/toastui-editor-viewer.min.css" />
<script
	src="https://uicdn.toast.com/editor/latest/toastui-editor-viewer.js"></script>
<script type="text/javascript">
$(function(){

/* 처음 로드 될때 본문 마크다운 자르는 부분 */
<c:forEach var="tmp" items="${ like_list }">
	var temp=parseMd($("#post_content${tmp.post_num}").text())
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
	  if(md.length>30){
		  md=md.substring(0,30).concat('...')
	  }//end if
	  return md;
}//parseMd

function moreLike(next_page){
	$.ajax({
		url:"/like/more.do?page="+next_page,
		type:"POST",
		dataType:"JSON",
		error:function(xhr){
			alert("에러");
			console.log(xhr.status+" / "+xhr.statusText);
		},
		success:function(jsonObj){
	      	if(jsonObj.flag=="success"){
				var output='';
				$.each(jsonObj.like_list, function(idx, list){
					output+='<div class="post">';
					output+='<div class="post_img"';
					if(jsonObj.thumbnail!=null){
						output+=' style="background-image: url(${ userMain.thumbnail })"';
					}//end if
					output+='></div>';
					output+='<div class="post_title" onclick="javascript:location.href=\'/'+list.id+'/blog/post.do?post='+list.post_num+'\'">'+list.post_title+'</div>';
					
					/* 글에서 마크다운 제거 */
					output+='<div class="post_content">'
					output+=parseMd(list.post_content)
					output+='</div>'
					
					output+='<div class="post_info">';
					output+='<span class="post_writer">by. <a href="/'+list.id+'/blog.do">'+list.nickname+'</a> ・'+list.input_date+'</span>';
					output+='<span class="post_like">';
					output+='<span id="heart'+list.post_num+'"><i class="like_heart fas fa-heart" onclick="clkHeart(\'remove\','+list.post_num+')"></i></span>';
					output+='<span id="postLikeCnt'+list.post_num+'">'+list.like_cnt+'</span>';
					output+='</span>';
					output+='</div>';
					output+='</div>';
				});//each
				
				$("#main_content_div").append(output);
				
				var more='';
				if(jsonObj.end_num<jsonObj.total_cnt){
					more+='<div onclick="moreLike('+(next_page+1)+')" class="more_btn">더 보기</div>';
				}//end if
				$("#main_btn").html(more);
	      	}else{
	      		alert("문제가 발생하였습니다. 다시 시도해주세요.")
	      	}//end else
		}//success
	});//ajax
}//moreLike

function clkHeart(flag, post_num){
	if(${ not empty sessionScope.id }){
		if(flag=="remove"){
			url="/like/remove.do?post="+post_num;
		}else{
			url="/like/add.do?post="+post_num;
		}//end else
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			error:function(xhr){
				alert("에러");
				console.log(xhr.status+" / "+xhr.statusText);
			},
			success:function(jsonObj){
		      	if(jsonObj.flag=="success"){
		      		//성공했을 때 디자인 변경 
					var heart = '<i class="far fa-heart"></i>'
					var heartCnt = 0
						
					if( jsonObj.like == "add" ) {
						heart = '<i class="like_heart fas fa-heart" onclick="clkHeart(\'remove\','+post_num+')"></i>'
							heartCnt = Number($("#postLikeCnt"+post_num).text()) + 1
					}
					if( jsonObj.like == "remove" ){
						heart = '<i class="far fa-heart" onclick="clkHeart(\'add\','+post_num+')"></i>'
						heartCnt = Number($("#postLikeCnt"+post_num).text()) - 1
					}
					
					$("#heart"+post_num).html(heart)
					$("#postLikeCnt"+post_num).text(heartCnt)
		      	}else{
		      		alert("문제가 발생하였습니다. 다시 시도해주세요.")
		      	}//end else
			}//success
		});//ajax
	}else{
		alert("로그인 후 다시 시도해주세요.")
	}//end else
}//clkHeart

</script>
<body>
	<!-- 헤더(사이드바) -->
	<c:import url="../common/common_header.jsp" />

    <section class="section_main">
        <!-- 타이틀 -->
        <div class="main_title">좋아한 포스트</div>

        <div id="main_content_div" class="main_content">
            <!-- main_content 안에 들어있는거여서
            c:foreach 해서 post를 반복문 돌리면 됩니당
            ajax append도 여기 하면 될 것 같아용 -->
			<c:forEach var="like" items="${ like_list }">
            <div class="post">
	        	<div class="post_img"<c:if test="${ not empty like.thumbnail }"> style="background-image: url(${ userMain.thumbnail })"</c:if>></div>
                <div class="post_title" onclick="javascript:location.href='/${ like.id }/blog/post.do?post=${ like.post_num }'"><c:out value="${ like.post_title }"/></div>
                <div id="post_content${ like.post_num }" class="post_content">${ like.post_content }</div>
                <div class="post_info">
                    <span class="post_writer">by. <a href="/${ like.id }/blog.do"><c:out value="${ like.nickname }"/></a> ・<c:out value="${ like.input_date }"/></span>
                    <span class="post_like">
                        <span id="heart${ like.post_num }"><i class="like_heart fas fa-heart" onclick="clkHeart('remove',${ like.post_num })"></i></span>
                        <span id="postLikeCnt${ like.post_num }"><c:out value="${ like.like_cnt }"/></span>
                    </span>
                </div>
            </div>
			</c:forEach>
        </div> <!-- main content end -->
        <!-- 더보기 버튼 -->
        <div id="main_btn" class="main_btn">
        <c:if test="${ end_num lt total_cnt }">
            <div onclick="moreLike(${ cur_page+1 })" class="more_btn">더 보기</div>
        </c:if>
        </div>
        <!-- 버튼 end -->
    </section>

    	<!--푸터 -->
	<c:import url="../common/common_footer.jsp" />
</body>

    <script src="../../../js/control_navbar.js"></script>
</html>