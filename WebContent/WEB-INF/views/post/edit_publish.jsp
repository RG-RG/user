<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head lang="en">
    <meta charset="UTF-8" />
   	<link rel="icon" href="./images/icon/favicon.ico" />
	<link rel="shortcut icon" href="favicon.ico" />
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
        $("[name='flag']").click(function () {
          if ($("[name='flag']:first").hasClass("selected")) {
            $("[name='flag']:first").removeClass("selected");
            $("[name='hidden_flag']").val("T");
          } else {
            $("[name='flag']:first").addClass("selected");
            $("[name='hidden_flag']").val("F");
          }
          if ($("[name='flag']:last").hasClass("selected")) {
            $("[name='flag']:last").removeClass("selected");
          } else {
            $("[name='flag']:last").addClass("selected");
          }
        });

        $("#thumbnail_upload").click(function () {
          $("#thumbnail_img").click();
        });

        $("#thumbnail_img").change(function () {
          let thumbnail_img = $("#thumbnail_img").val();
          $("#thumbnail").val(thumbnail_img);
        });

        $("#reupload").click(function () {
          $("#thumbnail_img").click();
        });

        $("#reupload").change(function () {
          let thumbnail_img = $("#thumbnail_img").val();
          $("#thumbnail").val(thumbnail_img);
        });

        /* 이미지 업로드 */
        $("#thumbnail_img").change(function () {
          if (this.files && this.files[0]) {
            var reader = new FileReader();
            
            let temp = $("#thumbnail_img").val().split("\\");
            if (temp[temp.length - 1].endsWith("jpg") || temp[temp.length - 1].endsWith("png") || temp[temp.length - 1].endsWith("jpeg")) {
            } else {
              console.log($("#thumbnail_img").val(), temp);
              alert("jpg, png, jpeg 파일만 업로드 가능합니다. 다른 형식의 파일을 선택해주세요");
              return;
            }
            
            reader.onload = function (data) {
              $("#thumbnail_file").attr("src", data.target.result).width(300);
              $("#thumbnail_upload").css("display", "none");
              $("#icon").css("display", "none");
              $("#edit_img_link").css("display", "");
            };
            console.log(this.files[0]);
            reader.readAsDataURL(this.files[0]);
          }
        });

        $("#delete").click(function () {
          console.log("지우기");
          $("#thumbnail_file").attr("src", "");
          $("#thumbnail_upload").css("display", "");
          $("#icon").css("display", "");
          $("#edit_img_link").css("display", "none");
        });

        $("#publish_btn").click(function () {
          if ($("#post_title").val() === "" || $("#post_content").val() === "") {
            alert("제목과 내용이 비어있습니다.");
            return;
          }
          if ($("#post_num").val() !== "") {
            $("#publish_form").attr("action", "save_modify_post.do");
          }
          $("#publish_form").submit();
        });

        $("#cancel").click(function () {
          history.back();
        });

        if(${ param.thumbnail ne null }){
        	$("#thumbnail_file").attr("src", "${pageContext.request.contextPath}/images/post/${ param.thumbnail }")
            $("#thumbnail_upload").css("display", "none");
            $("#icon").css("display", "none");
            $("#edit_img_link").css("display", "");
        }
      });
    </script>
  </head>
  <body style="font-family: IBMPlexSansKR-Regular">
    <form method="post" action="new_post.do" id="publish_form" enctype="multipart/form-data">
      <input type="hidden" name="post_num" id="post_num" value="${ param.post_num }" />
      <c:forEach var="tag" items="${ paramValues.tags }">
        <input type="hidden" name="tags" value="${ tag }" />
      </c:forEach>
      <input type="hidden" name="post_title" id="post_title" value="${ param.post_title }" />
      <input type="hidden" name="post_content" id="post_content" value="${ param.post_content }" />
      <input type="hidden" name="hidden_flag" value="F" />
      <input type="hidden" name="publish_flag" value="T" />
      <input type="hidden" name="thumbnail" id="thumbnail" value="${ param.thumbnail }" />
      <input type="file" id="thumbnail_img" name="thumbnail_img" style="display: none" />
    </form>
    <div class="content">
      <section class="publish_section">
        <div class="publish">
          <div class="thumbnail_area">
            <h3>썸네일 설정</h3>
            <div class="thumbnail">
              <svg id="icon" width="107" height="85" fill="none" viewBox="0 0 107 85">
                <path fill="#868E96" d="M105.155 0H1.845A1.844 1.844 0 0 0 0 1.845v81.172c0 1.02.826 1.845 1.845 1.845h103.31A1.844 1.844 0 0 0 107 83.017V1.845C107 .825 106.174 0 105.155 0zm-1.845 81.172H3.69V3.69h99.62v77.482z"></path>
                <path
                  fill="#868E96"
                  d="M29.517 40.84c5.666 0 10.274-4.608 10.274-10.271 0-5.668-4.608-10.276-10.274-10.276-5.665 0-10.274 4.608-10.274 10.274 0 5.665 4.609 10.274 10.274 10.274zm0-16.857a6.593 6.593 0 0 1 6.584 6.584 6.593 6.593 0 0 1-6.584 6.584 6.591 6.591 0 0 1-6.584-6.582c0-3.629 2.954-6.586 6.584-6.586zM12.914 73.793a1.84 1.84 0 0 0 1.217-.46l30.095-26.495 19.005 19.004a1.843 1.843 0 0 0 2.609 0 1.843 1.843 0 0 0 0-2.609l-8.868-8.868 16.937-18.548 20.775 19.044a1.846 1.846 0 0 0 2.492-2.72L75.038 31.846a1.902 1.902 0 0 0-1.328-.483c-.489.022-.95.238-1.28.6L54.36 51.752l-8.75-8.75a1.847 1.847 0 0 0-2.523-.081l-31.394 27.64a1.845 1.845 0 0 0 1.22 3.231z"
                ></path>
              </svg>
              <img src id="thumbnail_file" class="preview" />
              <button type="button" class="thumbnail_upload" id="thumbnail_upload">썸네일 업로드</button>
              <div id="edit_img_link" style="display: none" class="edit_group">
                <button type="button" class="" id="reupload">재업로드</button>
                <button type="button" class="" id="delete">제거</button>
              </div>
            </div>
          </div>
          <div class="show_area">
            <h3>공개 설정</h3>
            <div class="show" id="show">
              <button class="display selected" name="flag">전체 공개</button>
              <button class="hide" name="flag">비공개</button>
            </div>
          </div>
        </div>
      </section>
      <div class="fin_area">
        <button class="cancel" id="cancel">취소</button>
        <button class="publish_btn" id="publish_btn">출간하기</button>
      </div>
    </div>
  </body>
</html>
