package kr.co.rgrg.user.member.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.rgrg.user.member.vo.FindPassVO;
import kr.co.rgrg.user.member.vo.JoinVO;
import kr.co.rgrg.user.member.vo.LoginVO;
import kr.co.rgrg.user.member.vo.SocialJoinVO;

@SessionAttributes("id")
@Controller
public class MemberController {

	/**
	 * ȸ������ �̿��� ��? - �Ҽ� / �Ϲ� �� �� �����ִ°���?
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String joinClause() {
		return "";
	}//joinClause
	
	/**
	 * �Ϲ� ȸ������ ���� �ҷ����� ��
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String joinForm() {
		return "";
	}//joinForm
	
	/**
	 * �Ϲ� ȸ�������� �ϴ� ��
	 * @param jVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String join(JoinVO jVO, HttpServletRequest request) {
		return "";
	}//join
	
	/**
	 * �Ҽ� ȸ�������� �ϴ� ��
	 * @param sjVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String socialJoin(SocialJoinVO sjVO, HttpServletRequest request) {
		return "";
	}//socialJoin
	
	/**
	 * ���̵� �ߺ��� üũ�ϴ� ��
	 * @param newId
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String dupId(String newId) {
		return "";
	}//dupId
	
	/**
	 * �̸��� �ߺ��� üũ�ϴ� ��
	 * @param newEmail
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String dupEmail(String newEmail) {
		return "";
	}//dupEmail
	
	/**
	 * �α��� ���� �ҷ����� ��
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String loginForm() {
		return "";
	}//loginForm
	
	/**
	 * �α����� �ϴ� ��
	 * @param lVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String login(LoginVO lVO, Model model) {
		return "";
	}//login
	
	/**
	 * �Ҽ� �α��� ���� �ҷ����� ��? �Ҽ� �α����� �ϴ� ��?
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String socialLoginForm(Model model) {
		//SocialLoginVO�� ������?
		return "";
	}//socialLoginForm
	
	/**
	 * �α׾ƿ� �ϴ� ��
	 * @param ss
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String logout(SessionStatus ss) {
		return "";
	}//logout
	
	/**
	 * ���̵� ã�� ���� �ҷ����� ��
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String findIdForm() {
		return "";
	}//findIdForm
	
	/**
	 * ���̵� ã�⸦ ���� �̸��� ������ȣ�� Ȯ���ϴ� ��
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String findIdChkEmail(String email, Model model) {
		return "";
	}//findIdChkEmail
	
	/**
	 * ���̵� ã��
	 * @param authMsg
	 * @param model
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String findId(String authMsg, Model model) {
		return "";
	}//findId
	
	/**
	 * ��й�ȣ ã�� ���� �����ִ� ��
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String findPassForm() {
		return "";
	}//findPassForm
	
	/**
	 * ��й�ȣ ã�⸦ ���� �̸��� ������ȣ�� Ȯ���ϴ� ��
	 * @param fpVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String findPassChkEmail(FindPassVO fpVO, Model model) {
		return "";
	}//findPassChkEmail
	
	/**
	 * ��й�ȣ ã��
	 * @param authMsg
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String findPass(String authMsg) {
		return "";
	}//findPass
	
}//MemberController
