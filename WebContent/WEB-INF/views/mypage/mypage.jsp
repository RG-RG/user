<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Co-doing</title>
    <script src="https://kit.fontawesome.com/7a3682f726.js" crossorigin="anonymous"></script>
    <!-- Google CDN -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/reset.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/common/common_header_footer.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/mypage/style.css" />
	
	<!-- Chart.js -->
	<script	src="https://cdn.jsdelivr.net/npm/chart.js@2.9.4/dist/Chart.min.js"></script>
    <script type="text/javascript">
      $(function () {
        let display_profile = document.getElementById("display_profile");
        let nickname = display_profile.getElementsByTagName("h2")[0].textContent;
        let statement = display_profile.getElementsByTagName("p")[0].textContent;

        /* 닉네임, 상태메세지 수정 */
        $("#edit_profile_btn").click(function () {
          if ($("#profile_form").css("display") === "none") {
            $("#display_profile").hide();
            $("#profile_form").show();

            let display_profile = document.getElementById("display_profile");
            let nickname = display_profile.getElementsByTagName("h2")[0].textContent;
            let statement = display_profile.getElementsByTagName("p")[0].textContent;

            $("#nickname_input").attr("placeholder", nickname);
            $("#nickname_input").attr("value", nickname);

            $("#statement_input").attr("placeholder", statement);
            $("#statement_input").attr("value", statement);
          }
        });
        $("#profile_save").click(function () {
          let result = saveProfile();
          if(result == "success") {
        	  let text_area = $("#text_area").children();
        	  text_area[0].style.display = "";
        	  text_area[1].style.display = "none";
          }
        });

        $("#img_upload_btn").click(function () {
          $("#profile_img").click();
        });

        $("#img_delete_btn").click(function () {
          $.ajax({
            url: "modify_profile_img.do",
            dataType: "JSON",
            type: "POST",
            data: "profile_img=default.png",
            error: function (xhr) {
              alert("에러");
              console.log(xhr.status + " / " + xhr.statusText);
            },
            success: function (jsonObj) {
              if (jsonObj.result === "success") {
                console.log("성공");
              }
            },
          });
        });

        /* 이미지 업로드 */
        $("#profile_img").change(function () {
          let temp = $("#profile_img").val().split("\\");

          if (temp[temp.length - 1].endsWith("jpg") || temp[temp.length - 1].endsWith("png") || temp[temp.length - 1].endsWith("jpeg")) {
            let profile_img = temp[temp.length - 1];
            $.ajax({
              url: "modify_profile_img.do",
              dataType: "JSON",
              type: "POST",
              data: "profile_img=" + profile_img,
              error: function (xhr) {
                alert("에러");
                console.log(xhr.status + " / " + xhr.statusText);
              },
              success: function (jsonObj) {
                if (jsonObj.result === "success") {
                  console.log("성공");
                }
              },
            });

            let form = new FormData(document.getElementById("image_upload_form"));
            console.log(form);
            $.ajax({
              url: "upload_img_file.do",
              enctype: "/multipart/form-data",
              data: form,
              dataType: "json",
              processData: false,
              contentType: false,
              type: "POST",
              success: function (data) {
                if (data.result.code === "success") {
                  console.log("성공");
                } else {
                  console.log("실패");
                }
              },
              error: function (error) {
                alert(error.responseText);
              },
            });
          } else {
            alert("다른 형식의 파일을 선택해주세요");
          }
        });
      }); //ready

      /* 닉네임, 상태메세지 업데이트 */
      function saveProfile() {
        let newNickname = document.getElementById("nickname_input").value;
        let newStatement = document.getElementById("statement_input").value;
        
        let save_result;
        $.ajax({
          url: "modify_profile.do",
          dataType: "JSON",
          type: "POST",
          async: false,
          data: "nickname=" + newNickname + "&statement_msg=" + newStatement,
          error: function (xhr) {
            alert("에러");
            console.log(xhr.status + " / " + xhr.statusText);
          },
          success: function (jsonObj) {
            if (jsonObj.result === "success") {
              console.log("성공");
              $("#display_profile > h2").text(newNickname);
              $("#display_profile > p").text(newStatement);
              save_result = jsonObj.result;
            }
          },
        });
        
        return save_result;
      }

      function mng_menu(url) {
        console.log(url + " 관리 페이지 이동");
        var ajaxOption = {
          url: url,
          async: true,
          type: "GET",
          dataType: "html",
          cache: false,
        };

        $.ajax(ajaxOption).done(function (data) {
          // Contents 영역 삭제
          $("#mng_form").children().remove();
          // Contents 영역 교체
          $("#mng_form").html(data);
        });
      }
    </script>
  </head>
  <body style="font-family: IBMPlexSansKR-Regular">
    
    <!-- 사용자 header, side bar -->
    <jsp:include page="../common/common_header.jsp" />
    
    
    <!-- 로그인 안할 시 마이페이지 접근 불가 -->
    <c:if test="${ empty member_data }">
      <script>
        alert("로그인 후 이용 가능합니다.");
        location.href="main.do";
      </script>
    </c:if>
    
    
    <main class="section_main">
      <!-- 프로필 영역 -->
      <section class="profile">
      	<!-- 이미지 변경 부분 -->
        <div class="img_area">
          <form id="image_upload_form.do" action="" method="post" enctype="multipart/form-data">
            <input type="file" id="profile_img" name="profile_img" style="display: none" />
          </form>
          <img src="/images/profile/${ member_data.profile_img }" alt="" class="current_img" />
          <button type="button" class="upload_img_btn btns" id="img_upload_btn">이미지 업로드</button>
          <button type="button" class="delete_img_btn btns" id="img_delete_btn">이미지 제거</button>
        </div>
        
        <div class="text_area" id="text_area">
          <div id="display_profile">
            <h2>${ member_data.nickname }</h2>
            <p>${ member_data.statement_msg }</p>
            <button type="button" class="edit_btn" id="edit_profile_btn">수정</button>
          </div>
          <form id="profile_form" style="display: none">
            <div class="row">
              <div class="col-sm-10">
                <input type="text" class="form-control form-control-lg" id="nickname_input" />
              </div>
            </div>
            <div class="row mb-3">
              <div class="col-sm-10">
                <input type="text" class="form-control" id="statement_input" />
              </div>
            </div>
            <div class="btn_wrapper">
              <button type="button" class="save_btn" id="profile_save">저장</button>
            </div>
          </form>
        </div>
      </section>
      
      <!-- 개인정보 영역 -->
      <section class="mgn_content">
        <section class="navi">
          <h3 class="menu" onclick="mng_menu('change_info_form.do')" style="cursor: pointer">정보 관리</h3>
          <h3 class="menu" onclick="mng_menu('modify_pass_chk_form.do')" style="cursor: pointer">비밀번호 변경</h3>
          <h3 class="menu" onclick="mng_menu('analytics_form.do')" style="cursor: pointer">방문자 통계</h3>
        </section>
        <section class="mng_form" id="mng_form">
        	<jsp:include page="change_info.jsp" />
        </section>
      </section>
    </main>
    <jsp:include page="../common/common_footer.jsp" />
  </body>
  <script src="${pageContext.request.contextPath}/js/control_navbar.js"></script>
</html>
