package kr.co.rgrg.user.member.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import kr.co.rgrg.user.member.vo.FindPassVO;
import kr.co.rgrg.user.member.vo.JoinVO;
import kr.co.rgrg.user.member.vo.LoginVO;
import kr.co.rgrg.user.member.vo.SocialJoinVO;
import kr.co.rgrg.user.member.vo.UpdatePassVO;

@SessionAttributes("id")
@Controller
public class MemberController {

	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	/**
	 * 회원가입 이용약관 폼 - 소셜 / 일반 둘 다 보여줌
	 * @return
	 */
	@RequestMapping(value="/member/join_clause.do", method=GET)
	public String joinClause() {
		return "";
	}//joinClause
	
	/**
	 * 일반 회원가입 폼을 불러오는 일
	 * @return
	 */
	@RequestMapping(value="/member/join_form.do", method=GET)
	public String joinForm() {
		return "";
	}//joinForm
	
	/**
	 * 일반 회원가입을 하는 일
	 * @param jVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/member/join.do", method=GET)
	public String join(JoinVO jVO, HttpServletRequest request) {
		return "";
	}//join
	
	/**
	 * 소셜 회원가입을 하는 일
	 * @param sjVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/member/social_join.do", method=GET)
	public String socialJoin(SocialJoinVO sjVO, HttpServletRequest request) {
		return "";
	}//socialJoin
	
	/**
	 * 아이디 중복을 체크하는 일
	 * @param newId
	 * @return
	 */
	@RequestMapping(value="/member/dup_id.do", method=GET)
	@ResponseBody
	public String dupId(String newId) {
		return "";
	}//dupId
	
	/**
	 * 이메일 중복을 체크하는 일
	 * @param newEmail
	 * @return
	 */
	@RequestMapping(value="/member/dup_email.do", method=GET)
	@ResponseBody
	public String dupEmail(String newEmail) {
		return "";
	}//dupEmail
	
	/**
	 * 로그인 폼을 불러오는 일
	 * @return
	 */
	@RequestMapping(value="/member/login_form.do", method=GET)
	public String loginForm() {
		return "";
	}//loginForm
	
	/**
	 * 로그인을 하는 일
	 * @param lVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/member/login.do", method=GET)
	public String login(LoginVO lVO, Model model) {
		return "";
	}//login
	
	/**
	 * 소셜 로그인 폼을 불러오는 일? 소셜 로그인을 하는 일?
	 * @return
	 */
	@RequestMapping(value="/member/social_login_form.do", method=GET)
	public String socialLoginForm(Model model) {
		//SocialLoginVO? - API 써보면서 확인
		return "";
	}//socialLoginForm
	
	/**
	 * 로그아웃 하는 일
	 * @param ss
	 * @return
	 */
	@RequestMapping(value="/member/logout.do", method=GET)
	public String logout(SessionStatus ss) {
		return "";
	}//logout
	
	/**
	 * 아이디 찾기 폼을 불러오는 일
	 * @return
	 */
	@RequestMapping(value="/member/find_id_form.do", method=GET)
	public String findIdForm() {
		return "";
	}//findIdForm
	
	/**
	 * 아이디 찾기를 위해 입력한 이메일이 DB에 있는지 확인하는 일
	 * @return
	 */
	@RequestMapping(value="/member/find_id_chk.do", method=GET)
	@ResponseBody
	public String findIdChkEmail(String authEmail, Model model) {
		return "";
	}//findIdChkEmail
	
	/**
	 * 아이디 찾기
	 * @param authEmail
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/member/find_id.do", method=GET)
	public String findId(String authEmail, HttpServletRequest request, Model model) {
		return "";
	}//findId
	
	/**
	 * 비밀번호 찾기 폼을 보여주는 일
	 * @return
	 */
	@RequestMapping(value="/member/find_pass_form.do", method=GET)
	public String findPassForm() {
		return "";
	}//findPassForm
	
	/**
	 * 비밀번호 찾기를 위해 입력한 이메일이 DB에 있는지 확인하는 일
	 * @param fpVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/member/find_pass_chk.do", method=GET)
	@ResponseBody
	public String findPassChkEmail(FindPassVO fpVO, Model model) {
		return "";
	}//findPassChkEmail
	
	/**
	 * 비밀번호 변경
	 * @param upVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/member/modify_pass.do", method=GET)
	public String modifyPass(UpdatePassVO upVO, HttpServletRequest request) {
		return "";
	}//modifyPass
	
}//MemberController
