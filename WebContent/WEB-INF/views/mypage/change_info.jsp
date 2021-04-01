<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <!-- Google CDN -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
    
<script>
$(function(){
    /* 블로그 제목 수정 */
    $("#edit_name_btn").click(function () {
      if ($("#blog_name_form").css("display") === "none") {
        edit_mode(this);

        let blog_name = document.getElementById("blog_name").innerText;

        $("#blog_name_input").attr("placeholder", blog_name);
        $("#blog_name_input").attr("value", blog_name);
      }
    });
    
    /* 저장하고 input 숨기기 */
    $("#blog_name_save").click(function () {
      let result = saveBlogName();
      if(result = "success") {
	      display_mode(this);    	  
      }
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
    
    /* 저장하고 input 숨기기 */
    $("#social_save").click(function () {
      let save_result = saveSocial();
      console.log(save_result)
      if (save_result === "success") {
    	  display_mode(this);
      }
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

})
	/* 이메일 유효성검사 */
	function chkEmail(str) {
	   var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
	  if (regExp.test(str)) return true;
	  else return false;
	} //chkEmail
	
	/* 블로그 제목 업데이트 */
	function saveBlogName() {
	  let newBlogName = document.getElementById("blog_name_input").value;
	  
	  let save_result;
	  $.ajax({
	    url: "modify_title.do",
	    dataType: "JSON",
	    type: "POST",
	    async: false,
	    data: "blog_name=" + newBlogName,
	    error: function (xhr) {
	      console.log(xhr.status + " / " + xhr.statusText);
	      alert("에러");
	    },
	    success: function (jsonObj) {
	      if (jsonObj.result === "success") {
	        console.log("성공");
	        $("#blog_name").text(newBlogName);
	      }
	      save_result = jsonObj.result;
	    },
	  });
	  
	  return save_result;
	}
	
	/* 소셜 정보 변경 */
	function saveSocial() {
	  let newGithub = document.getElementById("github_input").value;
	  let newWebsite = document.getElementById("website_input").value;
	  let newEmail = document.getElementById("visible_email_input").value;
	
	  if (!chkEmail(newEmail)) {
	    alert("이메일 형식으로 입력해주세요");
	    return;
	  }
	  let result;
	  $.ajax({
	    url: "modify_social.do",
	    dataType: "JSON",
	    type: "POST",
	    async: false,
	    data: "github=" + newGithub + "&website=" + newWebsite + "&visible_email=" + newEmail,
	    error: function (xhr) {
	      console.log(xhr.status + " / " + xhr.statusText);
	      alert("에러");
	    },
	    success: function (jsonObj) {
	      if (jsonObj.result === "success") {
	        console.log("성공");
	        $("#github>span").text(newGithub);
	        $("#website>span").text(newWebsite);
	        $("#visible_email>span").text(newEmail);
	      }
	      result = jsonObj.result;
	    },
	  });
	  return result;
	  
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
	    async: false,
	    data: "pass=" + deleteChk,
	    error: function (xhr) {
	      console.log(xhr.status + " / " + xhr.statusText);
	      alert("탈퇴 중 에러가 발생했습니다. 잠시후 다시 시도해주세요");
	    },
	    success: function (jsonObj) {
	  	  console.log(jsonObj.result)
	  	  if(jsonObj.result == "success"){
		      console.log("성공");
			  alert("탈퇴되었습니다.")
			  location.href="logout.do";	  
	  	  } else {
	  		  alert("비밀번호를 다시 입력해주세요")
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
	
	function display_mode(param) {
		  let node_list = $(param).parent().parent().parent().children();
		  node_list[0].style.display = "";
		  node_list[1].style.display = "";
		  node_list[2].style.display = "none";
	}
</script>
           <div class="info blog_title">
            <h3>블로그 제목</h3>
            <div class="block">
              <div class="contents" id="blog_name">${ member_data.blog_name }</div>
              <div class="edit" id="blog_name_edit">
                <button type="button" class="edit_btn" id="edit_name_btn">수정</button>
              </div>
              <form id="blog_name_form" class="edit_form" style="display: none">
                <div class="">
                  <div class="input">
                    <input type="text" class="form-control" id="blog_name_input" />
                  </div>
                </div>
                <div class="btn_wrapper">
                  <button type="button" class="save_btn" id="blog_name_save">저장</button>
                </div>
              </form>
            </div>
          </div>
          <div class="info social_data">
            <h3>소셜 정보</h3>
            <div class="block">
              <div class="contents">
                <div id="github" class="col-form-label"><i class="social_label fab fa-github"></i><span> ${ member_data.github }</span></div>
                <div id="website" class="col-form-label"><i class="social_label fas fa-home"></i><span> ${ member_data.website }</span></div>
                <div id="visible_email" class="col-form-label"><i class="social_label fas fa-envelope"></i><span> ${ member_data.visible_email }</span></div>
              </div>
              <div class="edit">
                <button type="button" class="edit_btn" id="edit_social_btn">수정</button>
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
                  <button type="button" class="save_btn" id="social_save">저장</button>
                </div>
              </form>
            </div>
          </div>
          <div class="info email">
            <h3>이메일 정보</h3>
            <div class="block">
              <div class="contents" id="email">${ member_data.auth_email }</div>
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
                <button type="button" color="red" class="delete_mem_btn btns" id="delete_mem_btn">회원 탈퇴</button>
              </div>
              <div></div>
              <form id="delete_form" class="edit_form" style="display: none">
                <div class="col-xs-10">
                  <div class="input">
                    <input type="password" class="form-control" id="delete_input" placeholder="비밀번호를 입력해주세요" onkeypress="if(event.keyCode==13) {fnSearch(); return false;}"/>
                  </div>
                </div>
                <div class="btn_wrapper">
                  <button type="button" color="red" class="delete_mem_btn btns" id="delete_save">탈퇴</button>
                </div>
              </form>
            </div>
          </div>
