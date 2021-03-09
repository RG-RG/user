<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>마이페이지</title>
	<script src="https://kit.fontawesome.com/7a3682f726.js"	crossorigin="anonymous"></script>
	    <!-- Google CDN -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/js/bootstrap.bundle.min.js" integrity="sha384-b5kHyXgcpbZJO/tY9Ul7kGkf1S0CWuKcCD38l8YkeH8z8QjE0GmW1gYU5S9FOnJ0" crossorigin="anonymous"></script>
	<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/mypage/reset.css" />
	<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/mypage/style.css" />
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
        $("#profile_save").click(function(){
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
        $("#blog_name_save").click(function(){
        	saveBlogName();
        });
        
        /* 소셜 정보 변경 */
        $("#edit_social_btn").click(function () {
            if ($("#social_form").css("display") === "none") {
                edit_mode(this);
                
                let social = document.getElementById("social");
               	console.log(social)
                
/*                 $("#social_input").attr("placeholder", blog_name);
                $("#social_input").attr("value", blog_name); */
                
              }
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
        
        $("#email_save").click(function(){
        	saveEmail();
        });

      }); //ready
      
      /* 닉네임, 상태메세지 업데이트 */
      function saveProfile () {
    	let newNickname = document.getElementById("nickname_input").value;
    	let newStatement = document.getElementById("statement_input").value;
        $.ajax({
          url: "modify_profile.do",
          dataType: "JSON",
          type: "POST",
          data: "nickname=" + newNickname + "&statement_msg=" + newStatement,
          error:function(xhr){
        	alert("에러");
        	console.log(xhr.status+" / "+xhr.statusText);
          },
          success: function (jsonObj) {
            if(jsonObj.result === "success"){
            	console.log("성공")
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
              data: "blog_name=" + newBlogName ,
              error:function(xhr){
            	console.log(xhr.status+" / "+xhr.statusText);
            	alert("에러");
              },
              success: function (jsonObj) {
                if(jsonObj.result === "success"){
                	console.log("성공")
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
              data: "auth_email=" + newEmail ,
              error:function(xhr){
            	console.log(xhr.status+" / "+xhr.statusText);
            	alert("에러");
              },
              success: function (jsonObj) {
                if(jsonObj.result === "success"){
                	console.log("성공")
                }
              },
            });
    	  
      }
      
      function edit_mode(param) {
        let node_list = $(param).parent().parent().children();
        node_list[0].style.display = "none";
        node_list[1].style.display = "none";
        node_list[2].style.display = "";
      }
    </script>
</head>
<body>
	<main>
		<section class="profile">
			<div class="img_area">
				<!-- <div class="current_img">이미지 칸</div> -->
				<img
					src="https://cdn.pixabay.com/photo/2021/02/07/09/11/sunset-5990540_960_720.jpg"
					alt="" class="current_img" />
				<button class="upload_img_btn">이미지 업로드</button>
				<button class="delete_img_btn">이미지 제거</button>
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
							<input type="text" class="form-control form-control-lg"	id="nickname_input" />
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
		<section class="mgn_content">
			<section class="navi">
				<h3 class="menu">정보 관리</h3>
				<h3 class="menu">비밀번호 변경</h3>
				<h3 class="menu">방문자 통계</h3>
			</section>
			<section class="mng_form">
				<div class="info blog_title">
					<h3>블로그 제목</h3>
					<div class="block">
						<div class="contents" id="blog_name">${ member_data.blog_name }</div>
						<div class="edit" id="blog_name_edit">
		                <button class="edit_btn" id="edit_name_btn">수정</button>
		              </div>
		              <form id="blog_name_form" class="edit_form" style="display: none">
		                <div class="row mb-3">
		                  <div class="col-sm-10">
		                    <input type="text" class="form-control " id="blog_name_input" />
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
							<ul class="social_list">
								<li><i class="fab fa-github"></i> ${ member_data.github }</li>
								<li><i class="fas fa-home"></i> ${ member_data.website }</li>
								<li><i class="fas fa-envelope"></i> ${ member_data.visible_email }</li>
							</ul>
						</div>
						<div class="edit">
							<button class="edit_btn" id="edit_social_btn">수정</button>
						</div>
						<form id="social_form" class="edit_form" style="display: none">
			                <div class="row mb-3">
			                  <div class="col-sm-10">
			                    <input type="text" class="form-control " id="social_input" />
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
							<button class="edit_btn" id="edit_email_btn">수정</button>
						</div>
			              <form id="email_form" class="edit_form" style="display: none">
			                <div class="row mb-3">
			                  <div class="col-sm-10">
			                    <input type="text" class="form-control " id="email_input" />
			                  </div>
			                </div>
			                <div class="btn_wrapper">
			                  <button class="save_btn" id="email_save">저장</button>
			                </div>
			              </form>
					</div>
				</div>
				<div class="info alarm">
					<h3>이메일 알림</h3>
					<div class="block">
						<div class="contents">
							<input type="checkbox" id="email_toggle" /> <label
								for="email_toggle">${ member_data.alarm_flag }<span>선택</span></label>
						</div>
					</div>
				</div>
				<div class="info delete">
					<h3>회원 탈퇴</h3>
					<div class="contents">
						<button color="red" class="delete_mem_btn">회원 탈퇴</button>
					</div>
				</div>
			</section>
		</section>
	</main>
</body>
</html>
