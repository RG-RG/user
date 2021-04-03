package kr.co.rgrg.user.member.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.google.api.client.auth.openidconnect.IdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.util.Utils;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;

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
	
	@Value("${kakao.clientid}")
	private String kakaoClientId;
	
	@Value("${kakao.redirecturi}")
	private String kakaoRedirectUri;
	
	@Value("${google.clientid}")
	private String googleClientId;
	
	/**
	 * ȸ������ �̿��� ��
	 * @return
	 */
	@RequestMapping(value="join_clause.do", method=GET)
	public String joinClause() {
		return "member/join_clause";
	}//joinClause
	
	/**
	 * �Ϲ� ȸ������ ���� �ҷ����� ��
	 * @return
	 */
	@RequestMapping(value="join_form.do", method=GET)
	public String joinForm() {
		return "member/join_form";
	}//joinForm
	
	/**
	 * �Ϲ� ȸ�������� �ϴ� ��
	 * @param jVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="join.do", method=POST)
	public String join(JoinVO jVO, HttpServletRequest request) {
		jVO.setPass(passEncoder.encode(jVO.getPass()));
		boolean joinFlag = new MemberService().join(jVO);
		
		return "member/join_process";
	}//join
	
	/**
	 * �Ϲ� ȸ������ ����� �����ִ� �� - �ߺ� ���� ������
	 * @return
	 */
	@RequestMapping(value="join_result.do", method=GET)
	public String joinResult() {
		return "member/join";
	}//joinResult
	
	/**
	 * ���̵� �ߺ��� üũ�ϴ� ��
	 * @param id
	 * @return
	 */
	@RequestMapping(value="dup_id.do", method=GET)
	@ResponseBody
	public String dupId(String id) {
		String json = "";
		json = new MemberService().dupId(id);
		return json;
	}//dupId
	
	/**
	 * �̸��� �ߺ��� üũ�ϴ� ��
	 * @param auth_email
	 * @return
	 */
	@RequestMapping(value="dup_email.do", method=GET)
	@ResponseBody
	public String dupEmail(String auth_email) {
		String json = "";
		json = new MemberService().dupEmail(auth_email);
		return json;
	}//dupEmail
	
	/**
	 * �α����� �ϴ� ��
	 * @param lVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="login.do", method=POST)
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
	 * īī�� �α��� ���� �ҷ����� ��
	 * @return
	 */
	@RequestMapping(value="kakao_login_form.do", method=GET)
	@ResponseBody
	public String KakaoLoginForm() {
		String url = "https://kauth.kakao.com/oauth/authorize?client_id=" + kakaoClientId;
		url += "&redirect_uri=" + kakaoRedirectUri + "&response_type=code";
		return url;
	}//KakaoLoginForm
	
	/**
	 * īī�� �α��� ��ū�� �������� ��
	 * @return
	 */
	@RequestMapping(value="get_kakao_token.do", method=POST)
	public String getKakaoToken(HttpServletRequest request, String code) {
		String accessToken = "";
		
		RestTemplate rt = new RestTemplate();
		String url = "https://kauth.kakao.com/oauth/token";
		HttpHeaders headers = new HttpHeaders();
		MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
		params.set("grant_type", "authorization_code");
		params.set("client_id", kakaoClientId);
		params.set("redirect_uri", kakaoRedirectUri);
		params.set("code", code);
		
		HttpEntity<MultiValueMap<String, Object>> restRequest = new HttpEntity<>(params, headers);
		ResponseEntity<JSONObject> apiResponse = rt.postForEntity(url, restRequest, JSONObject.class);
		JSONObject responseBody = apiResponse.getBody();
		accessToken = (String) responseBody.get("access_token");
		
		return accessToken;
	}//getKakaoToken
	
	/**
	 * īī�� ����� ������ �������� ��
	 * @return
	 */
	@RequestMapping(value="get_kakao_info.do", method= {GET, POST})
	public JSONObject getKakaoInfo(String accessToken) {
		RestTemplate rt = new RestTemplate();
		String url = "https://kapi.kakao.com/v2/user/me";
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "Bearer " + accessToken);
		
		HttpEntity<MultiValueMap<String, Object>> restRequest = new HttpEntity<>(headers);
		ResponseEntity<JSONObject> apiResponse = rt.postForEntity(url, restRequest, JSONObject.class);
		JSONObject responseBody = apiResponse.getBody();
		
		return responseBody;
	}//getKakaoInfo
	
	/**
	 * īī�� �α��� ������ ��ȸ�ϴ� ��
	 * @return
	 */
	@RequestMapping(value="get_kakao_login_info.do", method=GET)
	public String getKakaoLoginInfo(HttpServletRequest request, HttpServletResponse response, String code, Model model) {
		String accessToken = getKakaoToken(request, code);
		JSONObject json = getKakaoInfo(accessToken);
		
		String id = Integer.toString((Integer) json.get("id"));
		if(dupId(id).contains("false")) { //ȸ�������� �� �Ǿ� �ִ� ���
			LinkedHashMap<String, Object> kakaoAccount =  (LinkedHashMap<String, Object>) json.get("kakao_account");
			LinkedHashMap<String, Object> kakaoProfile =  (LinkedHashMap<String, Object>) kakaoAccount.get("profile");
			
			SocialJoinVO sjVO = new SocialJoinVO();
			sjVO.setId(id);
			sjVO.setAuth_email((String) kakaoAccount.get("email"));
			sjVO.setNickname((String) kakaoProfile.get("nickname"));
			sjVO.setBlog_name((String) kakaoProfile.get("nickname"));
			if ((String) kakaoProfile.get("profile_image_url") != null) {
				sjVO.setProfile_img((String) kakaoProfile.get("profile_image_url"));
			} else { //������ ������ ���� ���
				sjVO.setProfile_img("default.png");
			}//end else
			sjVO.setPlatform("kakao");
			sjVO.setAccess_token(accessToken);
			
			new MemberService().socialJoin(sjVO);			
		}//end if
		
		model.addAttribute("id", id);
		
		return "redirect:/index.html";
	}//getKakaoLoginInfo
	
	/**
	 * ���� �α����� �ϴ� ��
	 * @param idtoken
	 * @param model
	 * @return
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	@RequestMapping(value="google_login.do", method=POST)
	@ResponseBody
	public String googleLogin(String idtoken, Model model) throws GeneralSecurityException, IOException {
		HttpTransport transport = Utils.getDefaultTransport();
		JsonFactory jsonFactory = Utils.getDefaultJsonFactory();
		
		GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
				.setAudience(Collections.singletonList(googleClientId)).build();
		
		JSONObject json = new JSONObject();
		
		GoogleIdToken idToken = verifier.verify(idtoken);
		if (idToken != null) {
			Payload payload = idToken.getPayload();
			
			String id = payload.getSubject();
			if (dupId(id).contains("false")) { //ȸ�������� �� �Ǿ� �ִ� ���
				SocialJoinVO sjVO = new SocialJoinVO();
				sjVO.setId(id);
				sjVO.setAuth_email((String) payload.get("email"));
				sjVO.setNickname((String) payload.get("name"));
				sjVO.setBlog_name((String) payload.get("name"));
				sjVO.setProfile_img((String) payload.get("picture"));
				sjVO.setPlatform("google");
				sjVO.setAccess_token(idtoken);
				
				new MemberService().socialJoin(sjVO);
			}//end if
			
			model.addAttribute("id", id);
			json.put("login_result", "success");
			
		} else { //��ȿ���� ���� ��ū
			json.put("login_result", "fail");
		}//end else
		
		return json.toJSONString();
	}//googleLogin
	
	/**
	 * �α׾ƿ� �ϴ� ��
	 * @param ss
	 * @return
	 */
	@RequestMapping(value="logout.do", method=GET)
	public ModelAndView logout(SessionStatus ss) {
		ModelAndView mav = new ModelAndView();
		ss.setComplete();
		
		RedirectView rv = new RedirectView();
		rv.setUrl("/main.do");
		rv.setExposeModelAttributes(false);
		mav.setView(rv);
		return mav;
	}//logout
	
	/**
	 * ������ ���� ������ ������ ��
	 * @param mVO
	 * @return
	 */
	@RequestMapping(value="send_mail.do", method=GET)
	@ResponseBody
	public String sendMail(MailVO mVO) {
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleMailMessage message = new SimpleMailMessage();
		
		Random random = new Random();
		String key = "";
		
		//������ȣ ����
		for (int i = 0; i < 3; i++) {
			int index = random.nextInt(25) + 65; // A~Z���� ���� ���ĺ� ����
			key += (char) index;
		}//end for
		int numIndex = random.nextInt(8999) + 1000; // 4�ڸ� ������ ����
		key += numIndex;
		
		message.setTo(mVO.getTo());
		message.setFrom(mVO.getFrom());
		message.setSubject("[Co-doing] �̸��� ����");
		message.setText("Co-doing ȸ������ Ȯ���� ���� ������ȣ�� " + key + " �Դϴ�.\n���� ������ Ȱ���� �ƴϽö�� codoingofficial@gmail.com �� �˷��ֽñ� �ٶ��ϴ�.");
		sender.send(message);
		map.put("key", key);
		
		return JSONObject.toJSONString(map);
	}//sendMail
	
	/**
	 * ���̵� ã�� ���� �ҷ����� ��
	 * @return
	 */
	@RequestMapping(value="find_id_form.do", method=GET)
	public String findIdForm() {
		return "member/find_id_form";
	}//findIdForm
	
	/**
	 * ���̵� ã�⸦ ���� �Է��� �̸����� DB�� �ִ��� Ȯ���ϴ� ��
	 * @param auth_email
	 * @return
	 */
	@RequestMapping(value="find_id_chk.do", method=GET)
	@ResponseBody
	public String findIdChkEmail(String auth_email) {
		String json = "";
		json = new MemberService().findIdChkEmail(auth_email);
		return json;
	}//findIdChkEmail
	
	/**
	 * ���̵� ã��
	 * @param auth_email
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="find_id.do", method=POST)
	public String findId(String auth_email, HttpServletRequest request, Model model) {
		String id = "";
		id = new MemberService().findId(auth_email);
		model.addAttribute("id_result", id);
		return "member/find_id";
	}//findId
	
	/**
	 * ��й�ȣ ã�� ���� �����ִ� ��
	 * @return
	 */
	@RequestMapping(value="find_pass_form.do", method=GET)
	public String findPassForm() {
		return "member/find_pass_form";
	}//findPassForm
	
	/**
	 * ��й�ȣ ã�⸦ ���� �Է��� �̸����� DB�� �ִ��� Ȯ���ϴ� ��
	 * @param fpVO
	 * @return
	 */
	@RequestMapping(value="find_pass_chk.do", method=GET)
	@ResponseBody
	public String findPassChkEmail(FindPassVO fpVO) {
		String json = "";
		json = new MemberService().findPassChkEmail(fpVO);
		return json;
	}//findPassChkEmail
	
	/**
	 * ��й�ȣ ���� ���� �����ִ� ��
	 * @return
	 */
	@RequestMapping(value="modify_pass_form.do", method = POST)
	public String modifyPassForm() {
		return "member/modify_pass_form";
	}//modifyPassForm
	
	/**
	 * ��й�ȣ ����
	 * @param upVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="modify_pass.do", method=POST)
	public ModelAndView modifyPass(UpdatePassVO upVO, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		upVO.setId(request.getParameter("id"));
		upVO.setAuth_email(request.getParameter("auth_email"));
		upVO.setPass(passEncoder.encode(upVO.getPass()));
		boolean passFlag = new MemberService().modifyPass(upVO);

		RedirectView rv = new RedirectView();
		rv.setUrl("/main.do");
		rv.setExposeModelAttributes(false);
		mav.setView(rv);
		return mav;
	}//modifyPass
	
}//MemberController
