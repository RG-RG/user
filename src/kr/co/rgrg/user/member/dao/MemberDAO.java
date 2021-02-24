package kr.co.rgrg.user.member.dao;

import kr.co.rgrg.user.member.vo.FindPassVO;
import kr.co.rgrg.user.member.vo.JoinVO;
import kr.co.rgrg.user.member.vo.LoginVO;
import kr.co.rgrg.user.member.vo.SocialJoinVO;

public class MemberDAO {
	
	private static MemberDAO memDAO;
	
	private MemberDAO() {
	}//MemberDAO
	
	public static MemberDAO getInstance() {
		if (memDAO == null) {
			memDAO = new MemberDAO();
		}//end if
		return memDAO;
	}//getInstance

	/**
	 * 일반 회원가입을 하는 일
	 * @param jVO
	 * @return
	 */
	public int insertMember(JoinVO jVO) {
		int cnt = 0;
		return cnt;
	}//insertMember
	
	/**
	 * 소셜 회원가입을 하는 일
	 * @param sjVO
	 * @return
	 */
	public int insertSocialMember(SocialJoinVO sjVO) {
		int cnt = 0;
		return cnt;
	}//insertSocialMember
	
	/**
	 * 아이디 중복을 체크하는 일
	 * @param newId
	 * @return
	 */
	public String selectId(String newId) {
		String id = "";
		return id;
	}//selectId
	
	/**
	 * 이메일 중복을 체크하는 일
	 * @param newEmail
	 * @return
	 */
	public String selectEmail(String newEmail) {
		String email = "";
		return email;
	}//selectEmail
	
	/**
	 * 로그인을 하는 일
	 * @param lVO
	 * @return
	 */
	public String selectLogin(LoginVO lVO) {
		String id = "";
		return id;
	}//selectLogin
	
	/**
	 * 아이디 찾기를 위해 입력한 이메일이 DB에 있는지 확인하는 일
	 * @param authEmail
	 * @return
	 */
	public String selectFindIdChkEmail(String authEmail) {
		String email = "";
		return email;
	}//selectFindIdChkEmail
	
	/**
	 * 아이디 찾기
	 * @param authMsg
	 * @return
	 */
	public String selectFindId(String authMsg) {
		String id = "";
		return id;
	}//selectFindId
	
	/**
	 * 비밀번호 찾기를 위해 입력한 이메일이 DB에 있는지 확인하는 일
	 * @param fpVO
	 * @return
	 */
	public String selectFindPassChkEmail(FindPassVO fpVO) {
		String email = "";
		return email;
	}//selectFindPassChkEmail
	
	/**
	 * 비밀번호 변경
	 * @param newPass
	 * @return
	 */
	public int updatePass(String newPass) {
		int cnt = 0;
		return cnt;
	}//updatePass
	
}//MemberDAO
