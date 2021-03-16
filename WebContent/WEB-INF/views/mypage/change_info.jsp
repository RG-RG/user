<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/mypage/reset.css" />
	<link rel="stylesheet"	href="${pageContext.request.contextPath}/css/mypage/style.css" />
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
                <button class="edit_btn" id="edit_email_btn">수정</button>
              </div>
              <form id="email_form" class="edit_form" style="display: none">
                <div class="">
                  <div class="input">
                    <input type="text" class="form-control" id="email_input" />
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
                <button color="red" class="delete_mem_btn" id="delete_mem_btn">회원 탈퇴</button>
              </div>
              <div></div>
              <form id="delete_form" class="edit_form" style="display: none">
                <div class="col-xs-10">
                  <div class="input">
                    <input type="text" class="form-control" id="delete_input" placeholder="비밀번호를 입력해주세요"/>
                  </div>
                </div>
                <div class="btn_wrapper">
                  <button color="red" class="delete_mem_btn" id="delete_save">탈퇴</button>
                </div>
              </form>
            </div>
          </div>
