<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>마이페이지</title>
    <link rel="stylesheet" href="http://localhost/rgrg_user/css/mypage/reset.css" />
    <link rel="stylesheet" href="http://localhost/rgrg_user/css/mypage/style.css" />
  </head>
  <body>
    <main>
      <section class="profile">
        <div class="img_area">
          <!-- <div class="current_img">이미지 칸</div> -->
          <img src="" alt="" class="current_img" />
          <button class="upload_img_btn">이미지 업로드</button>
          <button class="delete_img_btn">이미지 제거</button>
        </div>
        <div class="text_area">
          <h2>닉네임</h2>
          <p>상태메시지</p>
          <button class="edit_profile">수정</button>
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
              <div class="contents">블로그 제목</div>
              <div class="edit">
                <button class="edit_btn">수정</button>
              </div>
            </div>
          </div>
          <div class="info social_data">
            <h3>소셜 정보</h3>
            <div class="block">
              <div class="contents">
                <ul class="social_list">
                  <li>깃허브</li>
                  <li>웹사이트</li>
                </ul>
              </div>
              <div class="edit">
                <button class="edit_btn">수정</button>
              </div>
            </div>
            <!-- <div class="github"></div>
            <div class="website"></div> -->
          </div>
          <div class="info email">
            <h3>이메일 정보</h3>
            <div class="block">
              <div class="contents">email@email.com</div>
              <div class="edit">
                <button class="edit_btn">수정</button>
              </div>
            </div>
          </div>
          <div class="info alarm">
            <h3>이메일 알림</h3>
            <div class="block">
              <div class="contents">
                <input type="checkbox" id="email_toggle" />
                <label for="email_toggle"><span>선택</span></label>
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
