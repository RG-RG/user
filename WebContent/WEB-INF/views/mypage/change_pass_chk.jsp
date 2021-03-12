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
        <form action="/rgrg/mypage/modify_pass_chk.do" method="post">
          <div>원래 비밀번호 확인</div>
          <input class="chk_pass_input" type="text" name="pass" id="chk_pass_input" placeholder="비밀번호 입력" />
          <button type="submit" class="chk_pass_btn">확인</button>
        </form>
        </section>
      </section>
    </main>
  </body>
</html>