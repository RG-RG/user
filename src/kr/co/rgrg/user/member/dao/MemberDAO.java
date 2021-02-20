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
	 * ���̵� �ߺ��� üũ�ϴ� ��???
	 * @param newId
	 * @return
	 */
	public String selectId(String newId) {
		String id = "";
		return id;
	}//selectId
	
	/**
	 * �̸��� �ߺ��� üũ�ϴ� ��???
	 * @param newEmail
	 * @return
	 */
	public String selectEmail(String newEmail) {
		String email = "";
		return email;
	}//selectEmail
	
	/**
	 * �α����� �ϴ� �� (���̵� �������� �� �³���..????)
	 * @param lVO
	 * @return
	 */
	public String selectLogin(LoginVO lVO) {
		String id = "";
		return id;
	}//selectLogin
	
	/**
	 * ���̵� ã�⸦ ���� �̸��� ������ȣ�� Ȯ���ϴ� ��?
	 * @param email
	 * @return
	 */
	public String selectFindIdChkEmail(String email) {
		String authMsg = "";
		return authMsg;
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
	 * ��й�ȣ ã�⸦ ���� �̸��� ������ȣ�� Ȯ���ϴ� ��
	 * @param fpVO
	 * @return
	 */
	public String selectFindPassChkEmail(FindPassVO fpVO) {
		String authMsg = "";
		return authMsg;
	}//selectFindPassChkEmail
	
	/**
	 * ��й�ȣ ã��
	 * @param authMsg
	 * @return
	 */
	public String selectFindPass(String authMsg) {
		//���� �Ķ���ʹ� FindPassVO�ε� authMsg�� �´� �� ���Ƽ� �ٲ�µ� �³���?
		String pass = "";
		return pass;
		//��й�ȣ�� �ٷ� �˷��ֳ��� �ƴ� �ٲٰ� �ϳ��� �ƴ� ���ڸ��� ��ǥ��?
	}//selectFindPass
	
}//MemberDAO
