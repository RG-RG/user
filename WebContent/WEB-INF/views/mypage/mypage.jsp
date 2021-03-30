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
          saveProfile();
        });

        /* 블로그 제목 수정 */
        $("#edit_name_btn").click(function () {
          if ($("#blog_name_form").css("display") === "none") {
            edit_mode(this);

            let blog_name = document.getElementById("blog_name").innerText;

            $("#blog_name_input").attr("placeholder", blog_name);
            $("#blog_name_input").attr("value", blog_name);
          }
        });
        $("#blog_name_save").click(function () {
          saveBlogName();
          let newBlogName = document.getElementById("blog_name_input").value;
          $("#blog_name").innerText = newBlogName;
        });

        /* 소셜 정보 변경 */
        $("#edit_social_btn").click(function () {
          if ($("#social_form").css("display") === "none") {
            edit_mode(this);

            let github = document.getElementById("github").innerText.trim();
            let website = document.getElementById("website").innerText.trim();
            let visible_email = document.getElementById("visible_email").innerText.trim();

            $("#github_input").attr("placeholder", github);
            $("#github_input").attr("value", github);
            $("#website_input").attr("placeholder", website);
            $("#website_input").attr("value", website);
            $("#visible_email_input").attr("placeholder", visible_email);
            $("#visible_email_input").attr("value", visible_email);
          }
        });
        $("#social_save").click(function () {
          saveSocial();
        });

        /* 이메일 정보 변경 */
        $("#edit_email_btn").click(function () {
          if ($("#email_form").css("display") === "none") {
            edit_mode(this);

            let email = document.getElementById("email").innerText;

            $("#email_input").attr("placeholder", email);
            $("#email_input").attr("value", email);
          }
        });

        $("#email_save").click(function () {
          saveEmail();
        });

        $("#email_toggle").change(function () {
          changeAlarm();
        });

        $("#delete_mem_btn").click(function () {
          edit_mode(this);
        });

        $("#delete_save").click(function () {
          deleteMember();
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

      /* 이메일 유효성검사 */
      function chkEmail(str) {
        var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        if (regExp.test(str)) return true;
        else return false;
      } //chkEmail

      /* 닉네임, 상태메세지 업데이트 */
      function saveProfile() {
        let newNickname = document.getElementById("nickname_input").value;
        let newStatement = document.getElementById("statement_input").value;
        $.ajax({
          url: "modify_profile.do",
          dataType: "JSON",
          type: "POST",
          data: "nickname=" + newNickname + "&statement_msg=" + newStatement,
          error: function (xhr) {
            alert("에러");
            console.log(xhr.status + " / " + xhr.statusText);
          },
          success: function (jsonObj) {
            if (jsonObj.result === "success") {
              console.log("성공");
              $("#display_profile > h2").innerText(newNickName);
              $("#display_profile > p").innerText(newStatement);
            }
          },
        });
      }

      /* 블로그 제목 업데이트 */
      function saveBlogName() {
        let newBlogName = document.getElementById("blog_name_input").value;
        $.ajax({
          url: "modify_title.do",
          dataType: "JSON",
          type: "POST",
          data: "blog_name=" + newBlogName,
          error: function (xhr) {
            console.log(xhr.status + " / " + xhr.statusText);
            alert("에러");
          },
          success: function (jsonObj) {
            if (jsonObj.result === "success") {
              console.log("성공");
              $("#blog_name").innerText(newBlogName);
            }
          },
        });
      }

      /* 소셜 정보 변경 */
      function saveSocial() {
        let newGithub = document.getElementById("github_input").value;
        let newWebsite = document.getElementById("website_input").value;
        let newEmail = document.getElementById("visible_email_input").value;

        if (chkEmail(newEmail)) {
          alert("이메일 형식으로 입력해주세요");
          return;
        }

        $.ajax({
          url: "modify_social.do",
          dataType: "JSON",
          type: "POST",
          data: "github=" + newGithub + "&website=" + newWebsite + "&visible_email=" + newEmail,
          error: function (xhr) {
            console.log(xhr.status + " / " + xhr.statusText);
            alert("에러");
          },
          success: function (jsonObj) {
            if (jsonObj.result === "success") {
              console.log("성공");
              $("#github").innerText(newGithub);
              $("#website").innerText(newWebsite);
              $("#visible_email").innerText(newEmail);
            }
          },
        });
      }

      /* 이메일 변경 */
      function saveEmail() {
        let newEmail = document.getElementById("email_input").value;
        $.ajax({
          url: "modify_email.do",
          dataType: "JSON",
          type: "POST",
          data: "auth_email=" + newEmail,
          error: function (xhr) {
            console.log(xhr.status + " / " + xhr.statusText);
            alert("에러");
          },
          success: function (jsonObj) {
            if (jsonObj.result === "success") {
              console.log("성공");
              $("#email").innerText(newEmail);
            }
          },
        });
      }

      /* 이메일 알림 변경 */
      function changeAlarm() {
        let flag = document.getElementById("email_toggle").checked;
        let alarm_flag = "";
        if (flag === true) {
          alarm_flag = "T";
        } else {
          alarm_flag = "F";
        }
        console.log(flag, alarm_flag);
        $.ajax({
          url: "modify_email_flag.do",
          dataType: "JSON",
          type: "POST",
          data: "alarm_flag=" + alarm_flag,
          error: function (xhr) {
            console.log(xhr.status + " / " + xhr.statusText);
            alert("에러가 발생했습니다. 잠시후 다시 시도해주세요");
          },
          success: function (jsonObj) {
            if (jsonObj.result === "success") {
              console.log("성공");
            }
          },
        });
      }

      /* 탈퇴하기 비밀번호 체크 */
      function deleteMember() {
    	  let del_chk = confirm("정말로 탈퇴하시겠습니까?");
    	  if(!del_chk) {
    		  return;
    	  }
        let deleteChk = document.getElementById("delete_input").value;
        $.ajax({
          url: "remove_member.do",
          dataType: "JSON",
          type: "POST",
          data: "pass=" + deleteChk,
          error: function (xhr) {
            console.log(xhr.status + " / " + xhr.statusText);
            alert("탈퇴 중 에러가 발생했습니다. 잠시후 다시 시도해주세요");
          },
          success: function (jsonObj) {
        	  console.log(jsonObj.result)
              console.log("성공");
          },
        });
        alert("탈퇴되었습니다.")
        location.href="logout.do";
      }

      function edit_mode(param) {
        let node_list = $(param).parent().parent().children();
        node_list[0].style.display = "none";
        node_list[1].style.display = "none";
        node_list[2].style.display = "";
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
          <button class="upload_img_btn btns" id="img_upload_btn">이미지 업로드</button>
          <button class="delete_img_btn btns" id="img_delete_btn">이미지 제거</button>
        </div>
        
        <div class="text_area">
          <div id="display_profile">
            <h2>${ member_data.nickname }</h2>
            <p>${ member_data.statement_msg }</p>
            <button class="edit_btn" id="edit_profile_btn">수정</button>
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
              <button class="save_btn" id="profile_save">저장</button>
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
        
           <div class="info blog_title">
            <h3>블로그 제목</h3>
            <div class="block">
              <div class="contents" id="blog_name">${ member_data.blog_name }</div>
              <div class="edit" id="blog_name_edit">
                <button class="edit_btn" id="edit_name_btn">수정</button>
              </div>
              <form id="blog_name_form" class="edit_form" style="display: none">
                <div class="">
                  <div class="input">
                    <input type="text" class="form-control" id="blog_name_input" />
                  </div>
                </div>
                <div class="btn_wrapper">
                  <button class="save_btn" id="blog_name_save">저장</button>
                </div>
              </form>
            </div>
          </div>
          <div class="info social_data">
            <h3>소셜 정보</h3>
            <div class="block">
              <div class="contents">
                <div id="github" class="col-form-label"><i class="social_label fab fa-github"></i> ${ member_data.github }</div>
                <div id="website" class="col-form-label"><i class="social_label fas fa-home"></i> ${ member_data.website }</div>
                <div id="visible_email" class="col-form-label"><i class="social_label fas fa-envelope"></i> ${ member_data.visible_email }</div>
              </div>
              <div class="edit">
                <button class="edit_btn" id="edit_social_btn">수정</button>
              </div>
              <form id="social_form" class="edit_form" style="display: none">
                <div class="new_social">
                  <div class="social">
                    <label for="github_input" class="col-sm-2 col-form-label"><i class="fab fa-github"></i></label>
                    <div class="input">
                      <input type="text" class="form-control form-control-sm" id="github_input" />
                    </div>
                  </div>
                  <div class="social">
                    <label for="website_input" class="col-sm-2 col-form-label"><i class="fas fa-home"></i></label>
                    <div class="input">
                      <input type="text" class="form-control form-control-sm" id="website_input" />
                    </div>
                  </div>
                  <div class="social">
                    <label for="email_input" class="col-sm-2 col-form-label"><i class="fas fa-envelope"></i></label>
                    <div class="input">
                      <input type="text" class="form-control form-control-sm" id="visible_email_input" />
                    </div>
                  </div>
                </div>
                <div class="btn_wrapper">
                  <button class="save_btn" id="social_save">저장</button>
                </div>
              </form>
            </div>
          </div>
          <div class="info email">
            <h3>이메일 정보</h3>
            <div class="block">
              <div class="contents" id="email">${ member_data.auth_email }</div>
              <div class="edit">
                <button class="edit_btn btns" id="edit_email_btn">수정</button>
              </div>
              <form id="email_form" class="edit_form" style="display: none">
                <div class="">
                  <div class="input">
                    <input type="text" class="form-control" id="email_input" />
                  </div>
                </div>
                <div class="btn_wrapper">
                  <button class="save_btn btns" id="email_save">저장</button>
                </div>
              </form>
            </div>
          </div>
          <div class="info alarm">
            <h3>이메일 알림</h3>
            <div class="block">
              <div class="contents">
                <c:set var="email_flag" value="${ member_data.alarm_flag }" />
                <c:choose>
                  <c:when test="${ email_flag eq 'T' }">
                    <input type="checkbox" id="email_toggle" checked="checked" />
                  </c:when>
                  <c:otherwise>
                    <input type="checkbox" id="email_toggle" />
                  </c:otherwise>
                </c:choose>
                <label for="email_toggle" class="toggle"><span>선택</span></label>
              </div>
            </div>
          </div>
          <div class="info delete">
            <h3>회원 탈퇴</h3>
            <div class="block">
              <div class="contents">
                <button color="red" class="delete_mem_btn btns" id="delete_mem_btn">회원 탈퇴</button>
              </div>
              <div></div>
              <form id="delete_form" class="edit_form" style="display: none">
                <div class="col-xs-10">
                  <div class="input">
                    <input type="password" class="form-control" id="delete_input" placeholder="비밀번호를 입력해주세요" />
                  </div>
                </div>
                <div class="btn_wrapper">
                  <button color="red" class="delete_mem_btn btns" id="delete_save">탈퇴</button>
                </div>
              </form>
            </div>
          </div>
          
          
        </section>
      </section>
    </main>
    <jsp:include page="../common/common_footer.jsp" />
  </body>
  <script src="${pageContext.request.contextPath}/js/control_navbar.js"></script>
</html>
