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
	 * 아이디 찾기를 위해 입력한 이메일이 DB에 있는지 확인하는 일
	 * @param authEmail
	 * @return
	 */
	public String findIdChkEmail(String authEmail) {
		String email = "";
		return email;
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
	 * 비밀번호 찾기를 위해 입력한 이메일이 DB에 있는지 확인하는 일
	 * @param fpVO
	 * @return
	 */
	public String findPassChkEmail(FindPassVO fpVO) {
		String email = "";
		return email;
	}//findPassChkEmail
	
	/**
	 * 비밀번호 변경
	 * @param newPass
	 * @return
	 */
	public boolean modifyPass(String newPass) {
		boolean flag = false;
		return flag;
	}//modifyPass
	
}//MemberService
