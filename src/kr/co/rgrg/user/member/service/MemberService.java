package kr.co.rgrg.user.member.service;

import kr.co.rgrg.user.member.vo.FindPassVO;
import kr.co.rgrg.user.member.vo.JoinVO;
import kr.co.rgrg.user.member.vo.LoginVO;
import kr.co.rgrg.user.member.vo.SocialJoinVO;

public class MemberService {
	
	/**
	 * 일반 회원가입을 하는 일
	 * @param jVO
	 * @return
	 */
	public boolean join(JoinVO jVO) {
		boolean flag = false;
		return flag;
	}//join
	
	/**
	 * 소셜 회원가입을 하는 일
	 * @param sjVO
	 * @return
	 */
	public boolean socialJoin(SocialJoinVO sjVO) {
		boolean flag = false;
		return flag;
	}//socialJoin
	
	/**
	 * 아이디 중복을 체크하는 일
	 * @param newId
	 * @return
	 */
	public String dupId(String newId) {
		String id = "";
		return id;
	}//dupId
	
	/**
	 * 이메일 중복을 체크하는 일
	 * @param newEmail
	 * @return
	 */
	public String dupEmail(String newEmail) {
		String email = "";
		return email;
	}//dupEmail
	
	/**
	 * 로그인을 하는 일
	 * @param lVO
	 * @return
	 */
	public String login(LoginVO lVO) {
		String id = "";
		return id;
	}//login
	
	/**
	 * 아이디 찾기를 위해 이메일 인증번호를 확인하는 일
	 * @param email
	 * @return
	 */
	public String findIdChkEmail(String email) {
		String authMsg = "";
		return authMsg;
	}//findIdChkEmail
	
	/**
	 * 아이디 찾기
	 * @param authMsg
	 * @return
	 */
	public String findId(String authMsg) {
		String id = "";
		return id;
	}//findId
	
	/**
	 * 비밀번호 찾기를 위해 인증번호를 확인하는 일
	 * @param fpVO
	 * @return
	 */
	public String findPassChkEmail(FindPassVO fpVO) {
		String authMsg = "";
		return authMsg;
	}//findPassChkEmail
	
	/**
	 * 비밀번호 찾기
	 * @param authMsg
	 * @return
	 */
	public String findPass(String authMsg) {
		String pass = "";
		return pass;
	}//findPass
	
}//MemberService
