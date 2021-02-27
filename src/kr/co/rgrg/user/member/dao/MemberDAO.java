package kr.co.rgrg.user.member.dao;

import org.apache.ibatis.session.SqlSession;

import kr.co.rgrg.user.dao.GetRgrgHandler;
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
	 * �Ϲ� ȸ�������� �ϴ� ��
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
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		id = ss.selectOne("kr.co.rgrg.user.member.selectId", newId);
		ss.close();
		
		return id;
	}//selectId
	
	/**
	 * �̸��� �ߺ��� üũ�ϴ� ��
	 * @param newEmail
	 * @return
	 */
	public String selectEmail(String newEmail) {
		String email = "";
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		email = ss.selectOne("kr.co.rgrg.user.member.selectEmail", newEmail);
		ss.close();
		
		return email;
	}//selectEmail
	
	/**
	 * �α����� �ϴ� ��
	 * @param lVO
	 * @return
	 */
	public String selectLogin(LoginVO lVO) {
		String id = "";
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		id = ss.selectOne("kr.co.rgrg.user.member.selectLogin", lVO);
		ss.close();
		
		return id;
	}//selectLogin
	
	/**
	 * ���̵� ã�⸦ ���� �Է��� �̸����� DB�� �ִ��� Ȯ���ϴ� ��
	 * @param authEmail
	 * @return
	 */
	public String selectFindIdChkEmail(String authEmail) {
		String email = "";
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		email = ss.selectOne("kr.co.rgrg.user.member.selectFindIdChkEmail", authEmail);
		ss.close();
		
		return email;
	}//selectFindIdChkEmail
	
	/**
	 * ���̵� ã��
	 * @param authEmail
	 * @return
	 */
	public String selectFindId(String authEmail) {
		String id = "";
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		id = ss.selectOne("kr.co.rgrg.user.member.selectFindId", authEmail);
		ss.close();
		
		return id;
	}//selectFindId
	
	/**
	 * ��й�ȣ ã�⸦ ���� �Է��� �̸����� DB�� �ִ��� Ȯ���ϴ� ��
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
	 * ��й�ȣ ����
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
