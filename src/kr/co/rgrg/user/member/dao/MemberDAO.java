package kr.co.rgrg.user.member.dao;

import org.apache.ibatis.session.SqlSession;

import kr.co.rgrg.user.dao.GetRgrgHandler;
import kr.co.rgrg.user.member.domain.LoginDomain;
import kr.co.rgrg.user.member.vo.FindPassVO;
import kr.co.rgrg.user.member.vo.JoinVO;
import kr.co.rgrg.user.member.vo.LoginVO;
import kr.co.rgrg.user.member.vo.SocialJoinVO;
import kr.co.rgrg.user.member.vo.UpdatePassVO;

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
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		cnt = ss.insert("kr.co.rgrg.user.member.insertMember", jVO);
		ss.commit();
		ss.close();
		
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
	 * @param id
	 * @return
	 */
	public String selectId(String id) {
		String dupId = "";
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		dupId = ss.selectOne("kr.co.rgrg.user.member.selectId", id);
		ss.close();
		
		return dupId;
	}//selectId
	
	/**
	 * 이메일 중복을 체크하는 일
	 * @param auth_email
	 * @return
	 */
	public String selectEmail(String auth_email) {
		String dupEmail = "";
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		dupEmail = ss.selectOne("kr.co.rgrg.user.member.selectEmail", auth_email);
		ss.close();
		
		return dupEmail;
	}//selectEmail
	
	/**
	 * 닉네임 중복을 체크하는 일
	 * @param newNick
	 * @return
	 */
	public String selectNickname(String nickname) {
		String dupNick = "";
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		dupNick = ss.selectOne("kr.co.rgrg.user.member.selectNickname", nickname);
		ss.close();
		
		return dupNick;
	}//selectNickname
	
	/**
	 * 로그인을 하는 일
	 * @param lVO
	 * @return
	 */
	public LoginDomain selectLogin(LoginVO lVO) {
		LoginDomain ld = null;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		ld = ss.selectOne("kr.co.rgrg.user.member.selectLogin", lVO);
		ss.close();
		
		return ld;
	}//selectLogin
	
	/**
	 * 아이디 찾기를 위해 입력한 이메일이 DB에 있는지 확인하는 일
	 * @param auth_email
	 * @return
	 */
	public String selectFindIdChkEmail(String auth_email) {
		String chkEmail = "";
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		chkEmail = ss.selectOne("kr.co.rgrg.user.member.selectFindIdChkEmail", auth_email);
		ss.close();
		
		return chkEmail;
	}//selectFindIdChkEmail
	
	/**
	 * 아이디 찾기
	 * @param auth_email
	 * @return
	 */
	public String selectFindId(String auth_email) {
		String id = "";
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		id = ss.selectOne("kr.co.rgrg.user.member.selectFindId", auth_email);
		ss.close();
		
		return id;
	}//selectFindId
	
	/**
	 * 비밀번호 찾기를 위해 입력한 이메일이 DB에 있는지 확인하는 일
	 * @param fpVO
	 * @return
	 */
	public String selectFindPassChkEmail(FindPassVO fpVO) {
		String email = "";
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		email = ss.selectOne("kr.co.rgrg.user.member.selectFindPassChkEmail", fpVO);
		ss.close();
		
		return email;
	}//selectFindPassChkEmail
	
	/**
	 * 비밀번호 변경
	 * @param newPass
	 * @return
	 */
	public int updatePass(UpdatePassVO upVO) {
		int cnt = 0;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		cnt = ss.update("kr.co.rgrg.user.member.updatePass", upVO);
		ss.commit();
		ss.close();
		
		return cnt;
	}//updatePass
	
}//MemberDAO
