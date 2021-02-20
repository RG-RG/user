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
	 * 아이디 중복을 체크하는 일???
	 * @param newId
	 * @return
	 */
	public String selectId(String newId) {
		String id = "";
		return id;
	}//selectId
	
	/**
	 * 이메일 중복을 체크하는 일???
	 * @param newEmail
	 * @return
	 */
	public String selectEmail(String newEmail) {
		String email = "";
		return email;
	}//selectEmail
	
	/**
	 * 로그인을 하는 일 (아이디 가져오는 거 맞나요..????)
	 * @param lVO
	 * @return
	 */
	public String selectLogin(LoginVO lVO) {
		String id = "";
		return id;
	}//selectLogin
	
	/**
	 * 아이디 찾기를 위해 이메일 인증번호를 확인하는 일?
	 * @param email
	 * @return
	 */
	public String selectFindIdChkEmail(String email) {
		String authMsg = "";
		return authMsg;
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
	 * 비밀번호 찾기를 위해 이메일 인증번호를 확인하는 일
	 * @param fpVO
	 * @return
	 */
	public String selectFindPassChkEmail(FindPassVO fpVO) {
		String authMsg = "";
		return authMsg;
	}//selectFindPassChkEmail
	
	/**
	 * 비밀번호 찾기
	 * @param authMsg
	 * @return
	 */
	public String selectFindPass(String authMsg) {
		//원래 파라미터는 FindPassVO인데 authMsg가 맞는 거 같아서 바꿨는데 맞나요?
		String pass = "";
		return pass;
		//비밀번호를 바로 알려주나요 아님 바꾸게 하나요 아님 뒷자리만 별표로?
	}//selectFindPass
	
}//MemberDAO
