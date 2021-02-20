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
	 * 회원가입 이용약관 폼? - 소셜 / 일반 둘 다 보여주는건지?
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String joinClause() {
		return "";
	}//joinClause
	
	/**
	 * 일반 회원가입 폼을 불러오는 일
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String joinForm() {
		return "";
	}//joinForm
	
	/**
	 * 일반 회원가입을 하는 일
	 * @param jVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String join(JoinVO jVO, HttpServletRequest request) {
		return "";
	}//join
	
	/**
	 * 소셜 회원가입을 하는 일
	 * @param sjVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String socialJoin(SocialJoinVO sjVO, HttpServletRequest request) {
		return "";
	}//socialJoin
	
	/**
	 * 아이디 중복을 체크하는 일
	 * @param newId
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String dupId(String newId) {
		return "";
	}//dupId
	
	/**
	 * 이메일 중복을 체크하는 일
	 * @param newEmail
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String dupEmail(String newEmail) {
		return "";
	}//dupEmail
	
	/**
	 * 로그인 폼을 불러오는 일
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String loginForm() {
		return "";
	}//loginForm
	
	/**
	 * 로그인을 하는 일
	 * @param lVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String login(LoginVO lVO, Model model) {
		return "";
	}//login
	
	/**
	 * 소셜 로그인 폼을 불러오는 일? 소셜 로그인을 하는 일?
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String socialLoginForm(Model model) {
		//SocialLoginVO가 뭐예용?
		return "";
	}//socialLoginForm
	
	/**
	 * 로그아웃 하는 일
	 * @param ss
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String logout(SessionStatus ss) {
		return "";
	}//logout
	
	/**
	 * 아이디 찾기 폼을 불러오는 일
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String findIdForm() {
		return "";
	}//findIdForm
	
	/**
	 * 아이디 찾기를 위해 이메일 인증번호를 확인하는 일
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String findIdChkEmail(String email, Model model) {
		return "";
	}//findIdChkEmail
	
	/**
	 * 아이디 찾기
	 * @param authMsg
	 * @param model
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String findId(String authMsg, Model model) {
		return "";
	}//findId
	
	/**
	 * 비밀번호 찾기 폼을 보여주는 일
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String findPassForm() {
		return "";
	}//findPassForm
	
	/**
	 * 비밀번호 찾기를 위해 이메일 인증번호를 확인하는 일
	 * @param fpVO
	 * @param model
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String findPassChkEmail(FindPassVO fpVO, Model model) {
		return "";
	}//findPassChkEmail
	
	/**
	 * 비밀번호 찾기
	 * @param authMsg
	 * @return
	 */
	@RequestMapping(value="", method=GET)
	public String findPass(String authMsg) {
		return "";
	}//findPass
	
}//MemberController
