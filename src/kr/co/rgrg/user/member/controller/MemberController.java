package kr.co.rgrg.user.member.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.api.plus.Person;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.GrantType;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import kr.co.rgrg.user.member.domain.LoginDomain;
import kr.co.rgrg.user.member.service.MemberService;
import kr.co.rgrg.user.member.vo.FindPassVO;
import kr.co.rgrg.user.member.vo.JoinVO;
import kr.co.rgrg.user.member.vo.LoginVO;
import kr.co.rgrg.user.member.vo.MailVO;
import kr.co.rgrg.user.member.vo.SocialJoinVO;
import kr.co.rgrg.user.member.vo.UpdatePassVO;

@SessionAttributes("id")
@Controller
public class MemberController {

	@Inject
	BCryptPasswordEncoder passEncoder;
	
	@Autowired
	MailSender sender;
	
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;
	
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;
	
	/**
	 * 회원가입 이용약관 폼
	 * @return
	 */
	@RequestMapping(value="member/join_clause", method=GET)
	public String joinClause() {
		return "member/join_clause";
	}//joinClause
	
	/**
	 * 일반 회원가입 폼을 불러오는 일
	 * @return
	 */
	@RequestMapping(value="member/join_form", method=GET)
	public String joinForm() {
		return "member/join_form";
	}//joinForm
	
	/**
	 * 일반 회원가입을 하는 일
	 * @param jVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="member/join", method=POST)
	public String join(JoinVO jVO, HttpServletRequest request) {
		jVO.setPass(passEncoder.encode(jVO.getPass()));
		boolean joinFlag = new MemberService().join(jVO);
		
		return "member/join";
	}//join
	
	/**
	 * 아이디 중복을 체크하는 일
	 * @param id
	 * @return
	 */
	@RequestMapping(value="member/dup_id", method=GET)
	@ResponseBody
	public String dupId(String id) {
		String json = "";
		json = new MemberService().dupId(id);
		return json;
	}//dupId
	
	/**
	 * 이메일 중복을 체크하는 일
	 * @param auth_email
	 * @return
	 */
	@RequestMapping(value="member/dup_email", method=GET)
	@ResponseBody
	public String dupEmail(String auth_email) {
		String json = "";
		json = new MemberService().dupEmail(auth_email);
		return json;
	}//dupEmail
	
	/**
	 * 로그인을 하는 일
	 * @param lVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="member/login", method=POST)
	@ResponseBody
	public String login(LoginVO lVO, Model model) {
		LoginDomain ld = null;
		JSONObject json = new JSONObject();

		try {
			
			ld = new MemberService().login(lVO);
			boolean loginFlag = passEncoder.matches(lVO.getPass(), ld.getPass());
			
			if (ld != null && loginFlag) {
				model.addAttribute("id", ld.getId());
				json.put("login_result", "success");
			} else {
				json.put("login_result", "fail");
			}//end if
			
		} catch (NullPointerException ne) {
			json.put("login_result", "fail");
		}//end catch
		
		return json.toJSONString();
	}//login
	
	/**
	 * 카카오 로그인 폼을 불러오는 일
	 * @return
	 */
	@RequestMapping(value="member/kakao_login_form", method=GET)
	@ResponseBody
	public String KakaoLoginForm() {
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaa");
		String url = "https://kauth.kakao.com/oauth/authorize?client_id=d6ec4bf19e500ff83a89cb9c5a00ebc5";
		url += "&redirect_uri=http://localhost/rgrg_user/rgrg/member/get_kakao_info&response_type=code";
		return url;
	}//KakaoLoginForm
	
	/**
	 * 카카오 로그인 토큰을 가져오는 일
	 * @return
	 */
	@RequestMapping(value="member/get_kakao_token", method=GET)
	public String getKakaoToken(HttpServletRequest request, String code) {
		System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbb");
		String accessToken = "";
		
		RestTemplate rt = new RestTemplate();
		String url = "https://kauth.kakao.com/oauth/token";
		HttpHeaders headers = new HttpHeaders();
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("grant_type", "authorization_code");
		params.set("client_id", "d6ec4bf19e500ff83a89cb9c5a00ebc5");
		params.set("redirect_uri", "http://localhost/rgrg_user/rgrg/member/get_kakao_info");
		params.set("code", code);
		
		HttpEntity<MultiValueMap<String, Object>> restRequest = new HttpEntity<>(params, headers);
		ResponseEntity<JSONObject> apiResponse = rt.postForEntity(url, restRequest, JSONObject.class);
		JSONObject responseBody = apiResponse.getBody();
		accessToken = (String) responseBody.get("access_token");
		
		return accessToken;
	}//getKakaoToken
	
	/**
	 * 카카오 사용자 아이디를 가져오는 일
	 * @return
	 */
	@RequestMapping(value="member/get_kakao_id", method=GET)
	public String getKakaoId(String accessToken) {
		System.out.println("cccccccccccccccccccccccccccc");
		String kakaoId = "";
		
		RestTemplate rt = new RestTemplate();
		String url = "https://kauth.kakao.com/v2/user/me";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "bearer" + accessToken);
		headers.set("KakaoAK", "d6ec4bf19e500ff83a89cb9c5a00ebc5");
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
	    params.add("property_keys", "[\"id\"]");
		
		HttpEntity<MultiValueMap<String, Object>> restRequest = new HttpEntity<>(params, headers);
		ResponseEntity<JSONObject> apiResponse = rt.postForEntity(url, restRequest, JSONObject.class);
		JSONObject responseBody = apiResponse.getBody();
		
		kakaoId = (String)responseBody.get("id");
		
		return kakaoId;
	}//getKakaoId
	
	/**
	 * 카카오 로그인 정보를 조회하는 일
	 * @return
	 */
	@RequestMapping(value="member/get_kakao_info", method=GET)
	public String getKakaoInfo(HttpServletRequest request, HttpServletResponse response, String code) {
		System.out.println("ddddddddddddddddddddddddd");
		
		String accessToken = getKakaoToken(request, code);
		String kakaoId = getKakaoId(accessToken);
		if( kakaoId != null && !kakaoId.equals("") ) {
			
			return "redirect:/index.html";
		} else {
			System.out.println("NNNNNNNNNNNNNNN");
		}//end else
		return "";
	}//getKakaoInfo
	
	/**
	 * 구글 로그인 폼을 불러오는 일
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="member/google_login_form", method=POST)
	@ResponseBody
	public String googleLoginForm(HttpSession session, Model model) {
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		String url = oauthOperations.buildAuthorizeUrl(GrantType.AUTHORIZATION_CODE, googleOAuth2Parameters);
		return url;
	}//googleLoginForm
	
	/**
	 * 구글 로그인을 하는 일
	 * @param code
	 * @param model
	 * @return
	 */
	@RequestMapping(value="member/google_login", method=GET)
	public String googleLogin(@RequestParam String code, Model model) {
		OAuth2Operations oauthOperations = googleConnectionFactory.getOAuthOperations();
		AccessGrant accessGrant = oauthOperations.exchangeForAccess(code, googleOAuth2Parameters.getRedirectUri(), null);
		String accessToken = accessGrant.getAccessToken();
		System.out.println("11111111111111111111111111");
		Long expireTime = accessGrant.getExpireTime();
		if (expireTime != null && expireTime < System.currentTimeMillis()) {
			accessToken = accessGrant.getRefreshToken();
		}//end if
		
		System.out.println("22222222222222222222222222222222222");
		Connection<Google> connection = googleConnectionFactory.createConnection(accessGrant);
		System.out.println("3333333333333333333333333333333333333333");
		Google google = connection == null ? new GoogleTemplate(accessToken) : connection.getApi();
		
		System.out.println("44444444444444444444444444");
		PlusOperations plusOperations = google.plusOperations();
		System.out.println("55555555555555555555555555");
		Person profile = plusOperations.getGoogleProfile();
		System.out.println("66666666666666666666666666666666");
		System.out.println(profile.getId());
		System.out.println(profile.getAccountEmail());
		System.out.println(profile.getEmailAddresses());
		System.out.println(profile.getEmails());
		System.out.println(profile.getDisplayName());
		System.out.println(profile.getImageUrl());
//		JoinVO jVO = new JoinVO();
//		jVO.setId(profile.get);
//		jVO.setAuth_email(auth_email);
//		jVO.setNickname(nickname);
//		jVO.setPass(pass);
//		jVO.setBlog_name(blog_name);
//		new MemberService().googleLogin(jVO);
		
//		model.addAttribute("id", jVO.getId());
		
		return "redirect:/";
	}//googleLogin
	
	/**
	 * 로그아웃 하는 일
	 * @param ss
	 * @return
	 */
	@RequestMapping(value="member/logout", method=GET)
	public ModelAndView logout(SessionStatus ss) {
		ModelAndView mav = new ModelAndView();
		ss.setComplete();
		
		RedirectView rv = new RedirectView();
		rv.setUrl("/rgrg_user/rgrg/main/main");
		rv.setExposeModelAttributes(false);
		mav.setView(rv);
		return mav;
	}//logout
	
	/**
	 * 인증을 위해 메일을 보내는 일
	 * @param mVO
	 * @return
	 */
	@RequestMapping(value="member/send_mail", method=GET)
	@ResponseBody
	public String sendMail(MailVO mVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleMailMessage message = new SimpleMailMessage();
		
		Random random = new Random();
		String key = "";
		
		//인증번호 생성
		for (int i = 0; i < 3; i++) {
			int index = random.nextInt(25) + 65; // A~Z까지 랜덤 알파벳 생성
			key += (char) index;
		}//end for
		int numIndex = random.nextInt(8999) + 1000; // 4자리 정수를 생성
		key += numIndex;
		
		message.setTo(mVO.getTo());
		message.setFrom(mVO.getFrom());
		message.setSubject("[Co-doing] 이메일 인증");
		message.setText("Co-doing 회원정보 확인을 위한 인증번호는 " + key + " 입니다.\n만약 고객님의 활동이 아니시라면 codoingofficial@gmail.com 로 알려주시기 바랍니다.");
		sender.send(message);
		map.put("key", key);
		
		return JSONObject.toJSONString(map);
	}//sendMail
	
	/**
	 * 아이디 찾기 폼을 불러오는 일
	 * @return
	 */
	@RequestMapping(value="member/find_id_form", method=GET)
	public String findIdForm() {
		return "member/find_id_form";
	}//findIdForm
	
	/**
	 * 아이디 찾기를 위해 입력한 이메일이 DB에 있는지 확인하는 일
	 * @param auth_email
	 * @return
	 */
	@RequestMapping(value="member/find_id_chk", method=GET)
	@ResponseBody
	public String findIdChkEmail(String auth_email) {
		String json = "";
		json = new MemberService().findIdChkEmail(auth_email);
		return json;
	}//findIdChkEmail
	
	/**
	 * 아이디 찾기
	 * @param auth_email
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="member/find_id", method=POST)
	public String findId(String auth_email, HttpServletRequest request, Model model) {
		String id = "";
		id = new MemberService().findId(auth_email);
		model.addAttribute("id", id);
		return "member/find_id";
	}//findId
	
	/**
	 * 비밀번호 찾기 폼을 보여주는 일
	 * @return
	 */
	@RequestMapping(value="member/find_pass_form", method=GET)
	public String findPassForm() {
		return "member/find_pass_form";
	}//findPassForm
	
	/**
	 * 비밀번호 찾기를 위해 입력한 이메일이 DB에 있는지 확인하는 일
	 * @param fpVO
	 * @return
	 */
	@RequestMapping(value="member/find_pass_chk", method=GET)
	@ResponseBody
	public String findPassChkEmail(FindPassVO fpVO) {
		String json = "";
		json = new MemberService().findPassChkEmail(fpVO);
		return json;
	}//findPassChkEmail
	
	/**
	 * 비밀번호 변경 폼을 보여주는 일
	 * @return
	 */
	@RequestMapping(value="member/modify_pass_form", method = POST)
	public String modifyPassForm() {
		return "member/modify_pass_form";
	}//modifyPassForm
	
	/**
	 * 비밀번호 변경
	 * @param upVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="member/modify_pass", method=POST)
	public ModelAndView modifyPass(UpdatePassVO upVO, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		upVO.setId(request.getParameter("id"));
		upVO.setAuth_email(request.getParameter("auth_email"));
		upVO.setPass(passEncoder.encode(upVO.getPass()));
		boolean passFlag = new MemberService().modifyPass(upVO);

		RedirectView rv = new RedirectView();
		rv.setUrl("/rgrg_user/rgrg/main/main");
		rv.setExposeModelAttributes(false);
		mav.setView(rv);
		return mav;
	}//modifyPass
	
}//MemberController
