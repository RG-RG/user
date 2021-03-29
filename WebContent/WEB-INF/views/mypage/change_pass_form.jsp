<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(function(){
		$("#change_pass").keyup(function(){
			if($("#change_pass").val().length < 8){
				$("#warning").css("display", "");
			} else {
				$("#warning").css("display", "none");
			}
		});
		
		$("#change_pass_chk").keyup(function(){
			if($("#change_pass_chk").val() === $("#change_pass").val()){
				$("#warning_same").css("display", "none")
			}else{
				$("#warning_same").css("display", "")
			}
		});
		
	    $("#chk_pass_btn").click(function(){
	    	if($("#warning").css("display") !== "none" || $("#warning_same").css("display") !== "none" || $("#change_pass").val() === "") {
	    		alert("비밀번호를 확인해주세요");
	    		return;
	    	}
		    	let pass = $("#change_pass").val();
		        $.ajax({
			          url: "mypage_modify_pass.do",
			          dataType: "JSON",
			          type: "POST",
			          data: "pass=" + pass,
			          error: function (xhr) {
			            console.log(xhr.status + " / " + xhr.statusText);
			            console.log("에러");
			          },
			          success: function (data) {
			        	
			            if (data.result_flag === true) {
			              console.log("성공");
			              alert("비밀번호가 변경되었습니다.")
			              location.href = '/mypage.do';
			            }else{
		            	  alert("비밀번호 변경에 실패했습니다. 잠시후 다시 시도해주세요");
			            }
			          },
			        });
	    	
	    });
	})
</script>
    <h3>바꿀 비밀번호 입력</h3>
	    <div class="social">
	      <div class="input">
	        <input type="password" class="form-control chk_pass_input" id="change_pass" placeholder="새로운 비밀번호 입력"/>
	        <span style="color: red; display: none" id="warning" >비밀번호는 8자 이상이어야 합니다</span>
	      </div>
	    </div>
	    <h3>바꿀 비밀번호 확인</h3>
	    <div class="social">
	      <div class="input">
	        <input type="password" class="form-control chk_pass_input" id="change_pass_chk" placeholder="비밀번호 확인"/>
	        <span style="color: red; display: none" id="warning_same" >비밀번호가 다릅니다</span>
	      </div>
	    </div>
	    <button class="chk_pass_btn" id="chk_pass_btn">확인</button>
