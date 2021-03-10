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
	 * 회원가입 이용약관 폼 - 소셜 / 일반 둘 다 보여줌
	 * @return
	 */
	@RequestMapping(value="/member/join_clause.do", method=GET)
	public String joinClause() {
		return "member/join_clause";
	}//joinClause
	
	/**
	 * 일반 회원가입 폼을 불러오는 일
	 * @return
	 */
	@RequestMapping(value="/member/join_form.do", method=GET)
	public String joinForm() {
		return "member/join_form";
	}//joinForm
	
	/**
	 * 일반 회원가입을 하는 일
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
	 * 소셜 회원가입을 하는 일
	 * @param sjVO
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/member/social_join.do", method=GET)
	public String socialJoin(SocialJoinVO sjVO, HttpServletRequest request) {
		return "member/social_join";
	}//socialJoin
	
	/**
	 * 아이디 중복을 체크하는 일
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
	 * 이메일 중복을 체크하는 일
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
	 * 닉네임 중복을 체크하는 일
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
	 * 로그인 폼을 불러오는 일
	 * @return
	 */
	@RequestMapping(value="/member/login_form.do", method=GET)
	public String loginForm() {
		return "member/login_form";
	}//loginForm
	
	/**
	 * 로그인을 하는 일
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
	 * 소셜 로그인 폼을 불러오는 일? 소셜 로그인을 하는 일?
	 * @return
	 */
	@RequestMapping(value="/member/social_login_form.do", method=GET)
	public String socialLoginForm(Model model) {
		//SocialLoginVO? - API 써보면서 확인
		return "member/social_login_form";
	}//socialLoginForm
	
	/**
	 * 로그아웃 하는 일
	 * @param ss
	 * @return
	 */
	@RequestMapping(value="/member/logout.do", method=GET)
	public String logout(SessionStatus ss) {
		ss.setComplete();
		return "redirect:index.do";
	}//logout
	
	/**
	 * 아이디 찾기 폼을 불러오는 일
	 * @return
	 */
	@RequestMapping(value="/member/find_id_form.do", method=GET)
	public String findIdForm() {
		return "member/find_id_form";
	}//findIdForm
	
	/**
	 * 아이디 찾기를 위해 입력한 이메일이 DB에 있는지 확인하는 일
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
	 * 아이디 찾기
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
	 * 비밀번호 찾기 폼을 보여주는 일
	 * @return
	 */
	@RequestMapping(value="/member/find_pass_form.do", method=GET)
	public String findPassForm() {
		return "member/find_pass_form";
	}//findPassForm
	
	/**
	 * 비밀번호 찾기를 위해 입력한 이메일이 DB에 있는지 확인하는 일
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
	 * 비밀번호 변경 폼을 보여주는 일
	 * @return
	 */
	@RequestMapping(value="/member/modify_pass_form.do", method = GET)
	public String modifyPassForm() {
		return "member/modify_pass_form";
	}//modifyPassForm
	
	/**
	 * 비밀번호 변경
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
