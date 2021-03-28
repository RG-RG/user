<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<script type="text/javascript">
	
		$(function(){
	        $("#chk_pass_btn").click(function(){
	        	//alert("비밀번호 확인 버튼 눌림")
	        	pass_chk();
	        });
		})
 	  function pass_chk(){
	        let pass = document.getElementById("chk_pass_input").value;
	        
	        $.ajax({
	          url: "modify_pass_chk.do",
	          dataType: "JSON",
	          type: "POST",
	          data: "pass=" + pass,
	          error: function (xhr) {
	            console.log(xhr.status + " / " + xhr.statusText);
	            alert("에러");
	          },
	          success: function (jsonObj) {
	            alert("비밀번호 체크 "+jsonObj.result);
	            if (jsonObj.result === "success") {
	              console.log("성공");
	              change_pass_form()
	            }else{
            	  mng_pass();
            	  alert("실패");
	            }
	          },
	        });
 	  }
		
      function change_pass_form() {
 	      console.log("비밀번호 변경 페이지 이동");
          var ajaxOption = {
            url: "mypage_modify_pass_form.do",
            async: true,
            type: "POST",
            dataType: "html",
            cache: false,
          };

          $.ajax(ajaxOption).done(function (data) {
            // Contents 영역 삭제
            $("#pass_chk_form").children().remove();
            // Contents 영역 교체
            $("#pass_chk_form").html(data);
          });
      }
	</script>
	
        <div id="pass_chk_form">
          <h3>원래 비밀번호 확인</h3>
		    <div class="social">
		      <div class="input">
		        <input type="text" class="form-control chk_pass_input" id="chk_pass_input" placeholder="비밀번호를 입력해주세요"/>
		      </div>
	        <button class="chk_pass_btn" id="chk_pass_btn">확인</button>
		    </div>
        </div>
