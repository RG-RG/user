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
	
	/**
	 * ȸ������ �̿��� �� - �Ҽ� / �Ϲ� �� �� ������
	 * @return
	 */
	@RequestMapping(value="member/join_clause", method=GET)
	public String joinClause() {
		return "member/join_clause";
	}//joinClause
	
	/**
	 * �Ϲ� ȸ������ ���� �ҷ����� ��
	 * @return
	 */
	@RequestMapping(value="member/join_form", method=GET)
	public String joinForm() {
		return "member/join_form";
	}//joinForm
	
	/**
	 * �Ϲ� ȸ�������� �ϴ� ��
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
	 * �Ҽ� ȸ�������� �ϴ� ��
	 * @param sjVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="member/social_join", method=GET)
	public String socialJoin(SocialJoinVO sjVO, HttpServletRequest request) {
		return "member/social_join";
	}//socialJoin
	
	/**
	 * ���̵� �ߺ��� üũ�ϴ� ��
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
	 * �̸��� �ߺ��� üũ�ϴ� ��
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
	 * �α����� �ϴ� ��
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
	 * �Ҽ� �α��� ���� �ҷ����� ��? �Ҽ� �α����� �ϴ� ��?
	 * @return
	 */
	@RequestMapping(value="member/social_login_form", method=GET)
	public String socialLoginForm(Model model) {
		//SocialLoginVO? - API �Ẹ�鼭 Ȯ��
		return "member/social_login_form";
	}//socialLoginForm
	
	/**
	 * �α׾ƿ� �ϴ� ��
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
	 * ������ ���� ������ ������ ��
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
	@RequestMapping(value="member/find_id_form", method=GET)
	public String findIdForm() {
		return "member/find_id_form";
	}//findIdForm
	
	/**
	 * ���̵� ã�⸦ ���� �Է��� �̸����� DB�� �ִ��� Ȯ���ϴ� ��
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
	 * ���̵� ã��
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
	 * ��й�ȣ ã�� ���� �����ִ� ��
	 * @return
	 */
	@RequestMapping(value="member/find_pass_form", method=GET)
	public String findPassForm() {
		return "member/find_pass_form";
	}//findPassForm
	
	/**
	 * ��й�ȣ ã�⸦ ���� �Է��� �̸����� DB�� �ִ��� Ȯ���ϴ� ��
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
	 * ��й�ȣ ���� ���� �����ִ� ��
	 * @return
	 */
	@RequestMapping(value="member/modify_pass_form", method = POST)
	public String modifyPassForm() {
		return "member/modify_pass_form";
	}//modifyPassForm
	
	/**
	 * ��й�ȣ ����
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
