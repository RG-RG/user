<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Co-doing</title>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<link rel="icon" href="./images/icon/favicon.ico" />
<link rel="shortcut icon" href="favicon.ico" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" href="../../../css/reset.css">
<link rel="stylesheet" href="../../../css/member/style.css">
<link rel="stylesheet" href="../../../css/common/common_header_footer.css">
<style type="text/css">
#serviceAgreeText, #infoAgreeText{ padding: 2rem }
#infoForm{ margin-top: 4rem }
#buttons{ margin-top: 10rem }
#container { margin-bottom: 7rem }
</style>
<!-- Google CDN -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script type="text/javascript">
$(function(){

	var serviceAgreeChk = document.getElementById("serviceAgreeChk");
	var infoAgreeChk = document.getElementById("infoAgreeChk");

	$("#serviceLabel").click(function(){
		if (!$(serviceAgreeChk).is(":checked")){
			$(serviceAgreeChk).prop("checked", true);
		} else {
			$(serviceAgreeChk).prop("checked", false);
		}//end else
	});//click
	
	$("#infoLabel").click(function(){
		if (!$(infoAgreeChk).is(":checked")){
			$(infoAgreeChk).prop("checked", true);
		} else {
			$(infoAgreeChk).prop("checked", false);
		}//end else
	});//click
	
	$(".btn-light").click(function(){
		location.href="/main.do";
	});//click
	
	$(".btn-secondary").click(function(){
		if (!$(serviceAgreeChk).is(":checked")){
			alert("약관에 동의해주세요.");
			return;
		}//end if
		if (!$(infoAgreeChk).is(":checked")){
			alert("약관에 동의해주세요.");
			return;
		}//end if
		location.href="/join_form.do";
	});//click
	
});//ready
</script>
</head>
<body style="font-family: IBMPlexSansKR-Regular">
    <jsp:include page="../common/common_header.jsp"/>
    
    <section class="section_main">
    	<div id="container">
        	<div id="containerTitle">
        		Co-doing과 함께 블로그를 시작해보세요.
        	</div>
        	<div id="containerContent">
  				<div class="form-check">
  					<input class="form-check-input" type="checkbox" value="" id="serviceAgreeChk">
					<label class="form-check-label" id="serviceLabel">서비스 이용약관 동의 (필수)</label>
				</div>
        		<div class="mb-3">
  					<textarea class="form-control" id="serviceAgreeText" rows="3" readonly="readonly" disabled="disabled">
제 1조 목적
본 약관은 회원(본 약관에 동의한 자를 말하며 이하 "회원"이라고 합니다)이 코두잉(이하 "조직"이라고 합니다)가 제공하는 서비스를 이용함에 있어 조직과 회원의 권리 의무 및 책임사항을 규정함을 목적으로 합니다.

제 2조 정의
이 약관에서 사용하는 용어의 정의는 다음과 같습니다.
1. "회원"이라 함은 조직의 서비스에 접속하여 이 약관에 따라 "조직"과 이용계약을 체결하고 "조직"이 제공하는 "서비스" 를 이용하는 고객을 말합니다.
2. 아이디(ID)"라 함은 ”회원“의 식별과 ”서비스“ 이용을 위하여 ”회원“이 정하고 ”조직“이 승인하는 문자와 숫자의 조합을 말합니다.
3. "해지“라 함은 조직 또는 회원이 서비스 개통 후 이용계약을 해약하는 것을 말합니다.
4. “게시물”이라 함은 “회원”이 “서비스”를 이용함에 있어 “서비스상”에 게시한 부호·문자·음성·음향·화상·동영상 등의 정보 형태의 글, 사진, 동영상 및 각종 파일과 링크 등을 말합니다.

제 3조 개인정보보호 의무
1. "조직"은 "정보통신망법" 등 관계 법령이 정하는 바에 따라 "회원"의 "개인정보"를 보호하기 위해 노력합니다. "개인정보"의 보호 및 사용에 대해서는 관련법 및 "조직"의 개인정보보호정책이 적용됩니다. 다만, "조직"의 공식 사이트 이외의 링크된 사이트에서는 "조직"의 개인정보보호정책이 적용되지 않습니다.

제 4조 약관의 게시와 개정
1. "조직"은 이 약관의 내용을 "회원"이 쉽게 알 수 있도록 초기화면에 게시합니다.
2. "조직"은 "약관의규제에관한법률", "정보통신망이용촉진및정보보호에관한법률(이하 "정보통신망법")" 등 관련법을 위배하지 않는 범위에서 이 약관을 개정할 수 있습니다.
3. "조직"이 약관을 개정할 경우에는 적용일자 및 개정사유를 명시하여 현행약관과 함께 제1항의 방식에 따라 그 개정약관의 적용일자 30일 전부터 적용일자 전일까지 공지합니다. 다만, 회원에게 불리한 내용으로 약관을 개정하는 경우에는 공지 외에 회원정보에 등록된 이메일 등 전자적 수단을 통해 별도로 명확히 통지하도록 합니다.
4. "조직"이 전항에 따라 공지하면서 회원에게 30일 기간 이내에 의사표시를 하지 않으면 승인한 것으로 본다는 뜻을 명확하게 공지하였음에도 회원이 명시적으로 거부의사를 밝히지 않은 경우에회원이 개정약관에 동의한 것으로 봅니다.
5. "회원"이 개정약관에 동의하지 않는 경우 조직은 개정약관의 내용을 적용할 수 없으며, 이 경우 회원은 이용계약을 해지할 수 있습니다. 다만, 기존 약관을 적용할 수 없는 특별한 사정이 있는 경우에는 조직은 이용계약을 해지할 수 있습니다.

제 5조 권리의 귀속 및 저작물의 이용
1. 조직이 회원에게 제공하는 각종 서비스에 대한 저작권을 포함한 일체의 권리는 조직에 귀속되며 회원이 서비스를 이용하는 과정에서 작성한 게시물 등(이하 “게시물 등”이라 합니다)에 대한 저작권을 포함한 일체에 관한 권리는 별도의 의사표시가 없는 한 각 회원에게 귀속됩니다.
2. 게시물 등은 조직이 운영하는 인터넷 사이트 및 모바일 어플리케이션을 통해 노출될 수 있으며, 검색결과 내지 관련 프로모션 등에도 노출될 수 있습니다. 또한, 해당 노출을 위해 필요한 범위 내에서는 일부 수정, 편집되어 게시될 수 있습니다. 이 경우, 조직은 저작권법 규정을 준수하며, 회원은 언제든지 고객센터 또는 각 서비스 내 관리기능을 통해 해당 게시물 등에 대해 삭제, 비공개의 조치를 취할 수 있습니다.
3. 조직은 제2항 이외의 방법으로 회원의 게시물 등을 이용하고자 하는 경우에는 전화, 팩스, 전자우편 등을 통해 사전에 회원의 동의를 얻습니다.

제 6조 서비스의 변경, 중단, 일시 중지
1. 조직은 서비스의 일부 또는 전부를 조직의 사업 계획 및 서비스 운영정책에 따라 수정·변경 및 중단할 수 있으며, 이에 대하여 관련 법령에 특별한 규정이 없는 한 회원에게 별도의 보상을 하지 않습니다.
2. 조직은 서비스용 설비 점검·보수·공사 및 기간통신사업자의 전기통신 서비스의 중지, 서비스 이용의 폭주나 국가비상사태 등을 사유로 서비스 제공에 장애가 발생한 경우 그 사유가 해소될 때까지 서비스를 일시 중지할 수 있습니다.
3. 조직은 본 조에 따른 서비스의 변경·중단·일시 중지의 사유가 발생한 경우, 서비스를 통해 공지하는 등의 방법으로 회원에게 통지합니다.

제 7조 "회원"에 대한 통지
1. "조직"이 "회원"에 대한 통지를 하는 경우 본 약관에 별도 규정이 없는 한 "회원"이 지정한 전자우편주소, 알림 메시지 등으로 할 수 있습니다.

제 8조 이용제한 등
1. "조직"은 "회원"이 본 약관의 의무를 위반하거나 서비스의 정상적인 운영을 방해한 경우, 서비스 이용을 경고, 계약해지로 단계적으로 제한할 수 있습니다.
2. "조직"은 전항에도 불구하고, "저작권법" 및 "컴퓨터프로그램보호법"을 위반한 불법프로그램의 제공 및 운영방해, "정보통신망법"을 위반한 불법통신 및 해킹, 악성프로그램의 배포, 접속권한 초과행위 등과 같이 관련법을 위반한 경우에는 즉시 계약해지를 할 수 있습니다. 본 항에 따른 계약해지 시 서비스 이용을 통해 획득한 혜택 등도 모두 소멸되며, 조직은 이에 대해 별도로 보상하지 않습니다.
3. 조직은 본 조의 이용제한 범위 내에서 제한의 조건 및 세부내용은 이용제한정책 등에서 정한 바에 의합니다.
4. 본 조에 따라 서비스 이용을 제한하거나 계약을 해지하는 경우에는 "조직"은 제6조["회원"에 대한 통지]에 따라 통지합니다.
5. "회원"은 본 조에 따른 이용제한 등에 대해 "조직"이 정한 절차에 따라 이의신청을 할 수 있습니다. 이 때 이의가 정당하다고 조직이 인정하는 경우 조직은 즉시 서비스의 이용을 재개합니다.

제 9조 게시물의 관리
1. "회원"의 게시물이 "정보통신망법" 및 "저작권법"등 관련법에 위반되는 내용을 포함하는 경우, 권리자는 관련법이 정한 절차에 따라 해당 게시물의 게시중단 및 삭제 등을 요청할 수 있으며, "조직"은 관련법에 따라 조치를 취하여야 합니다.
2. "조직"은 전항에 따른 권리자의 요청이 없는 경우라도 권리침해가 인정될 만한 사유가 있거나 기타 조직 정책 및 관련법에 위반되는 경우에는 관련법에 따라 해당 게시물에 대해 삭제 조치를 취할 수 있습니다.
3. 본 조에 따른 세부절차는 "정보통신망법" 및 "저작권법"이 규정한 범위 내에서 조직이 정한 게시중단요청서비스에 따릅니다.

제 10조 권리의 귀속
1. "서비스"에 대한 저작권 및 지적재산권은 조직에 귀속됩니다. 단, 회원의 게시물 및 제휴계약에 따라 제공된 저작물 등은 제외합니다.
2. "조직"은 서비스와 관련하여 회원에게 조직이 정한 이용조건에 따라 계정, 아이디, 콘텐츠 등을 이용할 수 있는 이용권만을 부여하며, "회원"은 이를 양도, 판매, 담보제공 등의 처분행위를 할 수 없습니다.

제 11조 책임제한
1. "조직"은 천재지변 또는 이에 준하는 불가항력으로 인하여 "서비스"를 제공할 수 없는 경우에는 "서비스" 제공에 관한 책임이 면제됩니다.
2. "조직"은 "회원" 의 귀책사유로 인한 서비스 이용의 장애에 대하여는 책임을 지지 않습니다.
3. "조직"은 "회원"이 "서비스"와 관련하여 게재한 정보, 자료, 사실의 신뢰도, 정확성 등의 내용에 관하여는 책임을 지지 않습니다.
4. "조직"은 "회원" 간 또는 "회원"과 제3자 상호간에 "서비스"를 매개로 하여 거래 등을 한 경우에는 책임이 면제됩니다.
5. "조직"은 무료로 제공되는 서비스 이용과 관련하여 관련법에 특별한 규정이 없는 한 책임을 지지 않습니다.

제 12조 준거법 및 재판관할
1. "조직"과 "회원" 간 제기된 소송은 대한민국법을 준거법으로 합니다.
2. "조직"과 "회원"간 발생한 분쟁에 관한 소송은 제소 당시의 "회원"의 주소에 의하고, 주소가 없는 경우 거소를 관할하는 지방법원의 전속관할로 합니다. 단, 제소 당시 "회원"의 주소 또는 거소가 명확하지 아니한 경우의 관할법원은 민사소송법에 따라 정합니다.
3. 해외에 주소나 거소가 있는 '회원' 의 경우 "조직"과 “회원”간 발생한 분쟁에 관한 소송은 전항에도 불구하고 대한민국 서울중앙지방법원을 관할 법원으로 합니다.

부칙 (2021.04.08)
본 약관은 2021년 4월 8일부터 적용됩니다.</textarea>
				</div>
  				<div class="form-check" id="infoForm">
  					<input class="form-check-input" type="checkbox" value="" id="infoAgreeChk">
					<label class="form-check-label" id="infoLabel">개인정보 취급 방침 동의 (필수)</label>
				</div>
        		<div class="mb-3">
  					<textarea class="form-control" id="infoAgreeText" rows="3" readonly="readonly" disabled="disabled">
1. 개인정보의 수집 및 이용 목적
코두잉(이하 "조직")은 수집한 개인정보를 다음의 목적을 위해 활용합니다.
- 회원 관리
- 서비스 제공

2. 개인정보의 보유 및 이용기간
1) 소비자의 불만 또는 분쟁처리에 관한 기록
- 보존 이유: 전자상거래 등에서의 소비자보호에 관한 법률 제6조 및 시행령 제6조
- 보존 기간: 3년
2) 본인확인에 관한 기록
- 보존 이유: 정보통신망 이용촉진 및 정보보호에 관한 법률 제 44조의5 및 시행령 제 29조
- 보존 기간: 6개월
3) 접속에 관한 기록
- 보존 이유: 통신비밀보호법 제15조의2 및 시행령 제41조
- 보존 기간: 3개월

3. 수집하는 개인정보의 항목
조직은 회원가입, 서비스 이용 등을 위해 아래와 같은 개인정보를 수집하고 있습니다.
1) 수집항목
- 필수 입력 : 이메일 혹은 소셜 계정 정보
- 자동 수집항목 : IP정보, MAC정보, 이용 기록, 접속 로그, 쿠키, 접속 기록 등
2) 개인정보 수집방법: 홈페이지(회원 가입)

4. 개인정보의 파기절차 및 방법
조직은 원칙적으로 개인정보의 수집 및 이용목적이 달성된 경우에는 지체 없이 해당 개인정보를 파기합니다. 파기의 절차 및 방법은 다음과 같습니다.
1) 파기절차
이용자가 회원가입 등을 위해 입력한 정보는 목적 달성 후 별도의 DB에 옮겨져(종이의 경우 별도의 서류) 내부 방침 및 기타 관련 법령에 의한 정보보호 사유에 따라 일정기간(개인정보 보유 및 이용기간 참조) 저장된 후 파기됩니다. 동 개인정보는 법률에 의한 경우가 아니고서는 보유되는 이외의 다른 목적으로 이용되지 않습니다.
2) 파기방법
전자적 파일 형태의 개인정보는 기록을 재생할 수 없는 기술적 방법을 사용하여 삭제합니다. 종이에 출력된 개인정보는 분쇄기로 분쇄하거나 소각을 통하여 파기합니다.

5. 개인정보 제공
조직은 이용자의 개인정보를 원칙적으로 외부에 제공하지 않습니다. 다만, 아래의 경우에는 예외로 합니다.
- 이용자들이 사전에 동의한 경우
- 법령의 규정에 의거하거나, 수사 목적으로 사회사의 요구가 있는 경우

6. 개인정보의 안정성 확보조치에 관한 사항
1) 개인정보 암호화
2) 취급 직원의 최소화 및 교육

7. 개인정보 관리 책임
이메일: codoingofficial@gmail.com
기타 개인정보침해에 대한 신고나 상담이 필요한 경우에는 아래 회사에 문의하시기 바랍니다.
개인정보침해신고센터 (www.118.or.kr / 118)

8. 개인정보 취급방침 변경에 관한 사항
이 개인정보 취급방침은 2021년 4월 8일부터 적용됩니다.</textarea>
				</div>
				<div id="buttons">
					<button type="button" class="btn btn-light">취소</button>
					<button type="button" class="btn btn-secondary">다음</button>
				</div>
        	</div>
        </div>
    </section>
    
    <jsp:include page="../common/common_footer.jsp"/>
</body>
<script src="../../../js/control_navbar.js"></script>

</html>