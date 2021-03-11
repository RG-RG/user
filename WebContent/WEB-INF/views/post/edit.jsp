<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head lang="en">
    <meta charset="UTF-8" />
    <title>게시글 작성</title>


    <!-- Google CDN -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
    <!-- TOAST UI -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.css" />
    <link rel="stylesheet" href="https://uicdn.toast.com/editor/2.0.0/toastui-editor.min.css" />
	<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/post/reset.css" />
	<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/post/style.css" />
    <script>
      $(function () {
        $("#post_tag").keydown(function (key) {
          let $div = $("<div class='tag' >" + $("#post_tag").val() + "</div>");
          let $tag = $("<input type='hidden' name='tags' value='" + $("#post_tag").val() +"' />")
          if (key.keyCode == 13) {
            if ($("#post_tag").val() != false) {
           	  $("#post_form").append($tag);
              $("#post_tag").before($div);
              $("#post_tag").val("");
              /* $("#post_tag").attr("placeholder", ""); */
            }
          }
          
          if (key.keyCode == 8) {
        	  $("[class='tag']:last").remove();
        	  $("[name='tags']:last").remove();
          }
        });
        
        $("#save_content").click(function(){
        	
        });
        
        $("#title").keydown(function(){
        	$("#post_title").val($("#title").val());
        });
        
        $("#save_content").click(function(){
        	html_save();
        })
      });
    </script>
  </head>
  <body>
    <section class="editor_content">
      <div class="title_area">
        <textarea type="text" rows="1" class="post_title" name="post_title" id="title" placeholder="제목을 입력해주세요"></textarea>
        <div class="title_line"></div>
        <div class="tag_area" id="tag_area">
          <input type="text" class="post_tag" id="post_tag" placeholder="태그를 입력해주세요" />
        </div>
      </div>
      <div id="editor" class="editor"></div>
      <!-- <input type="hidden" name="post_content"/> -->
      <div class="btn_wrapper">
        <button class="post_btn cancel_post">나가기</button>
        <div class="save_area">
          <div class="save_btn">
            <button class="post_btn save_post" id="save_content" form="post_form">출간하기</button>
          </div>
          <div class="temporary_btn">
            <button class="post_btn temporary_post">임시저장</button>
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
        });
        
        function html_save(){
        	$("#post_content").val(editor.getHtml());
        }
      </script>
    </section>

    <form action="/rgrg/post/post_publish.do" method="post" id="post_form">
    	<input type="hidden" name="post_title" id="post_title"/>
    	<input type="hidden" name="post_content" id="post_content"/>
    </form>
  </body>
</html>
