<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head lang="en">
    <meta charset="UTF-8" />
    <title>Co-doing</title>

    <!-- Google CDN -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
    <!-- TOAST UI -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.css" />
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/2.0.0/toastui-editor.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/post/reset.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/post/style.css" />
    <script>
        $(function () {
      	  editor.setMarkdown($("#post_content").val());
	      $("#title").keypress(function(e) { 

		      	if (e.keyCode == 13) e.preventDefault(); 

		      });
      	/* 태그 동작 */
          $("#post_tag").keyup(function (key) {
            let $div = $("<div class='tag tag-init'>" + $("#post_tag").val() + "</div>");
            let $tag = $("<input type='hidden' name='tags' value='" + $("#post_tag").val() +"' />")

            if (key.keyCode === 13 || key.keyCode == 32) {
              if ($("#post_tag").val() != false) {
             	  $("#post_form").append($tag);
                $("#post_tag").before($div);
                $("#post_tag").val("");
              }

              //태그 입력 시 animation 효과
              var new_tag = document.querySelectorAll(".tag-init");
              setTimeout(function(){
            	  new_tag[new_tag.length-1].classList.add('tag-fade')
              })
            }else if (key.keyCode == 8) {
          	  $("[class='tag tag-init tag-fade']:last").remove();
          	  $("[name='tags']:last").remove();
            }
            console.log(key.keyCode)
          });

      	/* 출간하기 클릭 */
          $("#save_content").click(function(){
          	save_post();
          	$("#post_form").submit();
          })

          /* 임시저장 클릭 */
          $("#save_no_publish").click(function(){
          	save_post();
          	$("#publish_flag").val("F");
          	$("#post_form").attr("action", "new_post.do")
          	$("#post_form").submit();
          })

          /* 임시저장 글 이어서 작성할지 */
      <c:if test="${ not empty temp_post_flag }">

      		let temp_flag = confirm("임시저장된 게시글이 있습니다. 이어서 작성하시겠습니까?");

      		//임시저장 게시글 삭제하는 부분, 게시글 삭제 url로 ajax
      		// ajax되는지도 확인해야됨
      		if(temp_flag === true) {
      			$("#post_form").attr("action", "temp_post_form.do")
      	$("#post_form").submit();
      		} else {

      			$("#post_form").attr("action", "cancel.do")
      	$("#post_form").submit();
      		}
      </c:if>

      /* 나기가 버튼 */
      $("#exit").click(function(){
       if ($("#title").val().trim() != "" && editor.getMarkdown().trim() != "") { // 제목과 내용 모두 빈칸이 아닐 때만 확인
        let flag = confirm("수정중인 글을 저장하시겠습니까?");
        if(flag) {
      	  let url = "";
      	  save_post();
      	  console.log("저장하기")
             	$("#publish_flag").val("F");
      	  if (${empty post_data.post_num}) {
      		  console.log("새로운 임시저장")
              	$("#post_form").attr("action", "new_post.do")
      	  } else {
      		  console.log("그냥 임시저장")
      		  $("#post_form").attr("action", "save_modify_post.do");
      	  }
        } else {
      	  if (${not empty post_data.post_num}) {
      		  console.log("원래 있던거 삭제")
      		  $("#post_form").attr("action", "cancel.do");
      	  }
        }
        $("#post_form").submit();
       } else {
        location.href = "main.do"
       }
      })
      });

        function save_post(){
        	console.log(editor.getMarkdown())
        	$("#post_title").val($("#title").val())
        	$("#post_content").val(editor.getMarkdown());
        }
    </script>
  </head>
  <body style="font-family: IBMPlexSansKR-Regular">
    <c:if test="${ empty id }">
      <script>
        alert("로그인 후 이용 가능합니다.");
        history.back();
      </script>
    </c:if>
    <section class="editor_content">
      <div class="title_area">
        <textarea type="text" rows="1" class="post_title" name="post_title" id="title" placeholder="제목을 입력해주세요">${ post_data.post_title }</textarea>
        <div class="title_line"></div>
        <div class="tag_area" id="tag_area">
          <c:forEach var="tag" items="${ post_data.tag_name }">
            <div class="tag">${ tag }</div>
          </c:forEach>
          <input type="text" class="post_tag" id="post_tag" placeholder="태그를 입력해주세요" />
        </div>
      </div>
      <div id="editor" class="editor"></div>
      <div class="btn_wrapper">
        <button class="post_btn cancel_post" id="exit">나가기</button>
        <div class="save_area">
          <div class="save_btn">
            <button class="post_btn save_post" id="save_content" form="post_form">출간하기</button>
          </div>
          <div class="temporary_btn">
            <button class="post_btn temporary_post" id="save_no_publish" form="post_form">임시저장</button>
          </div>
        </div>
      </div>

      <script src="https://uicdn.toast.com/editor/2.0.0/toastui-editor-all.min.js"></script>

      <script>
      
        const content = [].join("\n");

        const editor = new toastui.Editor({
          el: document.querySelector("#editor"),
          height: "600px",
          initialEditType: "markdown",
          previewStyle: "vertical",
          placeholder: "내용을 입력하세요",
          hideModeSwitch: true,
          hooks: {
            addImageBlobHook(blob, callback) {
              const formData = new FormData();
              formData.append("image", blob);
				console.log(blob)
	   
			$.ajax({
	          url: "https://api.imgbb.com/1/upload?key=446cf7f566319051cb6af82271a8c3fb",
	          dataType: "JSON",
	          type: "POST",
	          timeout: 0,
	          data: formData,
	          contentType: false,
	          mimType: "multipart/form-data",
	          processData: false,
	          error: function (xhr) {
	            console.log(xhr.status + " / " + xhr.statusText);
	            alert("에러");
	          },
	          success: function (jsonObj) {
	        	  console.log(jsonObj);
	        	  console.log(jsonObj.data.url);
	        	  callback(decodeURIComponent(jsonObj.data.url));
	          },
	        });           },
           }
        });
      </script>
    </section>
    <form action="post_publish.do" method="post" id="post_form" enctype="multipart/form-data">
      <c:if test="${ post_data.post_num ne null }">
        <input type="hidden" name="post_num" value="${ post_data.post_num }" />
      </c:if>
      <input type="hidden" name="post_title" id="post_title" value="${ post_data.post_title }" />
      <input type="hidden" name="post_content" id="post_content" value="${ post_data.post_content }" />
      <input type="hidden" name="thumbnail" id="thumbnail" value="${ post_data.thumbnail }" />
      <input type="hidden" name="publish_flag" id="publish_flag" value="${ post_data.publish_flag }" />
      <c:forEach var="tag" items="${ post_data.tag_name }">
        <input type="hidden" name="tags" value="${ tag }" />
      </c:forEach>
      <input type="hidden" name="hidden_flag" value="F" />
    </form>
  </body>
</html>
