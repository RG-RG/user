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
	 * �Ϲ� ȸ�������� �ϴ� ��
	 * @param jVO
	 * @return
	 */
	public int insertMember(JoinVO jVO) {
		int cnt = 0;
		return cnt;
	}//insertMember
	
	/**
	 * �Ҽ� ȸ�������� �ϴ� ��
	 * @param sjVO
	 * @return
	 */
	public int insertSocialMember(SocialJoinVO sjVO) {
		int cnt = 0;
		return cnt;
	}//insertSocialMember
	
	/**
	 * ���̵� �ߺ��� üũ�ϴ� ��
	 * @param newId
	 * @return
	 */
	public String selectId(String newId) {
		String id = "";
		return id;
	}//selectId
	
	/**
	 * �̸��� �ߺ��� üũ�ϴ� ��
	 * @param newEmail
	 * @return
	 */
	public String selectEmail(String newEmail) {
		String email = "";
		return email;
	}//selectEmail
	
	/**
	 * �α����� �ϴ� ��
	 * @param lVO
	 * @return
	 */
	public String selectLogin(LoginVO lVO) {
		String id = "";
		return id;
	}//selectLogin
	
	/**
	 * ���̵� ã�⸦ ���� �Է��� �̸����� DB�� �ִ��� Ȯ���ϴ� ��
	 * @param authEmail
	 * @return
	 */
	public String selectFindIdChkEmail(String authEmail) {
		String email = "";
		return email;
	}//selectFindIdChkEmail
	
	/**
	 * ���̵� ã��
	 * @param authMsg
	 * @return
	 */
	public String selectFindId(String authMsg) {
		String id = "";
		return id;
	}//selectFindId
	
	/**
	 * ��й�ȣ ã�⸦ ���� �Է��� �̸����� DB�� �ִ��� Ȯ���ϴ� ��
	 * @param fpVO
	 * @return
	 */
	public String selectFindPassChkEmail(FindPassVO fpVO) {
		String email = "";
		return email;
	}//selectFindPassChkEmail
	
	/**
	 * ��й�ȣ ����
	 * @param newPass
	 * @return
	 */
	public int updatePass(String newPass) {
		int cnt = 0;
		return cnt;
	}//updatePass
	
}//MemberDAO
