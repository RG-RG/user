package kr.co.rgrg.user.member.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
import kr.co.rgrg.user.member.vo.SocialJoinVO;
import kr.co.rgrg.user.member.vo.UpdatePassVO;

@SessionAttributes("id")
@Controller
public class MemberController {

	@Inject
	BCryptPasswordEncoder passEncoder;
	
	/**
	 * ȸ������ �̿��� �� - �Ҽ� / �Ϲ� �� �� ������
	 * @return
	 */
	@RequestMapping(value="/member/join_clause.do", method=GET)
	public String joinClause() {
		return "member/join_clause";
	}//joinClause
	
	/**
	 * �Ϲ� ȸ������ ���� �ҷ����� ��
	 * @return
	 */
	@RequestMapping(value="/member/join_form.do", method=GET)
	public String joinForm() {
		return "member/join_form";
	}//joinForm
	
	/**
	 * �Ϲ� ȸ�������� �ϴ� ��
	 * @param jVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/member/join.do", method=POST)
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
	@RequestMapping(value="/member/social_join.do", method=GET)
	public String socialJoin(SocialJoinVO sjVO, HttpServletRequest request) {
		return "member/social_join";
	}//socialJoin
	
	/**
	 * ���̵� �ߺ��� üũ�ϴ� ��
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/member/dup_id.do", method=GET)
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
	@RequestMapping(value="/member/dup_email.do", method=GET)
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
	@RequestMapping(value="/member/dup_nick.do", method=GET)
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
	@RequestMapping(value="/member/login_form.do", method=GET)
	public String loginForm() {
		return "member/login_form";
	}//loginForm
	
	/**
	 * �α����� �ϴ� ��
	 * @param lVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/member/login.do", method=GET)
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
	@RequestMapping(value="/member/social_login_form.do", method=GET)
	public String socialLoginForm(Model model) {
		//SocialLoginVO? - API �Ẹ�鼭 Ȯ��
		return "member/social_login_form";
	}//socialLoginForm
	
	/**
	 * �α׾ƿ� �ϴ� ��
	 * @param ss
	 * @return
	 */
	@RequestMapping(value="/member/logout.do", method=GET)
	public String logout(SessionStatus ss) {
		ss.setComplete();
		return "redirect:index.do";
	}//logout
	
	/**
	 * ���̵� ã�� ���� �ҷ����� ��
	 * @return
	 */
	@RequestMapping(value="/member/find_id_form.do", method=GET)
	public String findIdForm() {
		return "member/find_id_form";
	}//findIdForm
	
	/**
	 * ���̵� ã�⸦ ���� �Է��� �̸����� DB�� �ִ��� Ȯ���ϴ� ��
	 * @param authEmail
	 * @return
	 */
	@RequestMapping(value="/member/find_id_chk.do", method=GET)
	@ResponseBody
	public String findIdChkEmail(String authEmail) {
		String json = "";
		json = new MemberService().findIdChkEmail(authEmail);
		return json;
	}//findIdChkEmail
	
	/**
	 * ���̵� ã��
	 * @param authEmail
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/member/find_id.do", method=GET)
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
	@RequestMapping(value="/member/find_pass_form.do", method=GET)
	public String findPassForm() {
		return "member/find_pass_form";
	}//findPassForm
	
	/**
	 * ��й�ȣ ã�⸦ ���� �Է��� �̸����� DB�� �ִ��� Ȯ���ϴ� ��
	 * @param fpVO
	 * @return
	 */
	@RequestMapping(value="/member/find_pass_chk.do", method=GET)
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
	@RequestMapping(value="/member/modify_pass_form.do", method = GET)
	public String modifyPassForm() {
		return "member/modify_pass_form";
	}//modifyPassForm
	
	/**
	 * ��й�ȣ ����
	 * @param upVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/member/modify_pass.do", method=GET)
	public String modifyPass(UpdatePassVO upVO, HttpServletRequest request) {
		upVO.setId(request.getParameter("id"));
		upVO.setAuth_email(request.getParameter("auth_email"));
		upVO.setPass(passEncoder.encode(upVO.getPass()));
		boolean passFlag = new MemberService().modifyPass(upVO);
		return "redirect:index.do";
	}//modifyPass
	
}//MemberController
