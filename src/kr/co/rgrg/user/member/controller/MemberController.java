package kr.co.rgrg.user.member.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

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
	
//	@Autowired
//	private JavaMailSenderImpl mailSender;
	
	@Autowired
	MailSender sender;
	
	/**
	 * ȸ������ �̿��� �� - �Ҽ� / �Ϲ� �� �� ������
	 * @return
	 */
	@RequestMapping(value="rgrg/member/join_clause", method=GET)
	public String joinClause() {
		return "member/join_clause";
	}//joinClause
	
	/**
	 * �Ϲ� ȸ������ ���� �ҷ����� ��
	 * @return
	 */
	@RequestMapping(value="rgrg/member/join_form", method=GET)
	public String joinForm() {
		return "member/join_form";
	}//joinForm
	
	/**
	 * �Ϲ� ȸ�������� �ϴ� ��
	 * @param jVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="rgrg/member/join", method=POST)
	public String join(JoinVO jVO, HttpServletRequest request) {
		jVO.setPass(passEncoder.encode(jVO.getPass()));
		boolean joinFlag = new MemberService().join(jVO);
		
		return "member/join";
	}//join
	
	/**
	 * �Ҽ� ȸ�������� �ϴ� ��
	 * @param sjVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="rgrg/member/social_join", method=GET)
	public String socialJoin(SocialJoinVO sjVO, HttpServletRequest request) {
		return "member/social_join";
	}//socialJoin
	
	/**
	 * ���̵� �ߺ��� üũ�ϴ� ��
	 * @param id
	 * @return
	 */
	@RequestMapping(value="rgrg/member/dup_id", method=GET)
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
	@RequestMapping(value="rgrg/member/dup_email", method=GET)
	@ResponseBody
	public String dupEmail(String auth_email) {
		String json = "";
		json = new MemberService().dupEmail(auth_email);
		return json;
	}//dupEmail
	
	/**
	 * �г��� �ߺ��� üũ�ϴ� ��
	 * @param nickname
	 * @return
	 */
	@RequestMapping(value="rgrg/member/dup_nick", method=GET)
	@ResponseBody
	public String dupNick(String nickname) {
		String json = "";
		json = new MemberService().dupNick(nickname);
		return json;
	}//dupNick
	
	/**
	 * �α��� ���� �ҷ����� ��
	 * @return
	 */
	@RequestMapping(value="rgrg/member/login_form", method=GET)
	public String loginForm() {
		return "member/login_form";
	}//loginForm
	
	/**
	 * �α����� �ϴ� ��
	 * @param lVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="rgrg/member/login", method=GET)
	public String login(LoginVO lVO, Model model) {
		LoginDomain ld = null;
		ld = new MemberService().login(lVO);
		boolean loginFlag = passEncoder.matches(lVO.getPass(), ld.getPass());
		
		String url = "";
		if (ld != null && loginFlag) {
			model.addAttribute("id", ld.getId());
			url = "redirect:index.do";
		} else {
			model.addAttribute("login_result", "fail");
			url = "forward:/member/login_form.do";
		}//end if
		
		return url;
	}//login
	
	/**
	 * �Ҽ� �α��� ���� �ҷ����� ��? �Ҽ� �α����� �ϴ� ��?
	 * @return
	 */
	@RequestMapping(value="rgrg/member/social_login_form", method=GET)
	public String socialLoginForm(Model model) {
		//SocialLoginVO? - API �Ẹ�鼭 Ȯ��
		return "member/social_login_form";
	}//socialLoginForm
	
	/**
	 * �α׾ƿ� �ϴ� ��
	 * @param ss
	 * @return
	 */
	@RequestMapping(value="rgrg/member/logout", method=GET)
	public String logout(SessionStatus ss) {
		ss.setComplete();
		return "redirect:index.do";
	}//logout
	
	/**
	 * ������ ���� ������ ������ ��
	 * @param mVO
	 * @return
	 */
	@RequestMapping(value="rgrg/member/send_mail", method=GET)
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
		message.setSubject("[RGRG] �̸��� ����");
		message.setText("RGRG ȸ������ Ȯ���� ���� ������ȣ�� " + key + " �Դϴ�.\n���� ������ Ȱ���� �ƴϽö�� rgrgofficial@gmail.com �� �˷��ֽñ� �ٶ��ϴ�.");
		sender.send(message);
		map.put("key", key);
		
		return JSONObject.toJSONString(map);
	}//sendMail
	
	/**
	 * ���̵� ã�� ���� �ҷ����� ��
	 * @return
	 */
	@RequestMapping(value="rgrg/member/find_id_form", method=GET)
	public String findIdForm() {
		return "member/find_id_form";
	}//findIdForm
	
	/**
	 * ���̵� ã�⸦ ���� �Է��� �̸����� DB�� �ִ��� Ȯ���ϴ� ��
	 * @param auth_email
	 * @return
	 */
	@RequestMapping(value="rgrg/member/find_id_chk", method=GET)
	@ResponseBody
	public String findIdChkEmail(String auth_email) {
		String json = "";
		json = new MemberService().findIdChkEmail(auth_email);
		return json;
	}//findIdChkEmail
	
	/**
	 * ���̵� ã��
	 * @param authEmail
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="rgrg/member/find_id", method=GET)
	public String findId(String authEmail, HttpServletRequest request, Model model) {
		String id = "";
		id = new MemberService().findId(authEmail);
		model.addAttribute("id", id);
		return "member/find_id";
	}//findId
	
	/**
	 * ��й�ȣ ã�� ���� �����ִ� ��
	 * @return
	 */
	@RequestMapping(value="rgrg/member/find_pass_form", method=GET)
	public String findPassForm() {
		return "member/find_pass_form";
	}//findPassForm
	
	/**
	 * ��й�ȣ ã�⸦ ���� �Է��� �̸����� DB�� �ִ��� Ȯ���ϴ� ��
	 * @param fpVO
	 * @return
	 */
	@RequestMapping(value="rgrg/member/find_pass_chk", method=GET)
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
	@RequestMapping(value="rgrg/member/modify_pass_form", method = GET)
	public String modifyPassForm() {
		return "member/modify_pass_form";
	}//modifyPassForm
	
	/**
	 * ��й�ȣ ����
	 * @param upVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="rgrg/member/modify_pass", method=GET)
	public String modifyPass(UpdatePassVO upVO, HttpServletRequest request) {
		upVO.setId(request.getParameter("id"));
		upVO.setAuth_email(request.getParameter("auth_email"));
		upVO.setPass(passEncoder.encode(upVO.getPass()));
		boolean passFlag = new MemberService().modifyPass(upVO);
		return "redirect:index.do";
	}//modifyPass
	
}//MemberController
