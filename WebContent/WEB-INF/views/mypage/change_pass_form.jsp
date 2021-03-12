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
    <link rel="stylesheet" href="reset.css" />
    <link rel="stylesheet" href="change_pass.css" />
    <link rel="stylesheet" href="style.css" />
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
          <div>바꿀 비밀번호 입력</div>
          <input class="change_pass" type="text" name="" id="change_pass" placeholder="비밀번호 입력" />
          <div>바꿀 비밀번호 확인</div>
          <input class="change_pass_chk" type="text" name="" id="change_pass_chk" placeholder="비밀번호 입력" />
          <button class="chk_pass_btn">확인</button>
        </section>
      </section>
    </main>
  </body>
</html>
