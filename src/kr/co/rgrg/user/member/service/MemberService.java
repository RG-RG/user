package kr.co.rgrg.user.member.service;

import kr.co.rgrg.user.member.dao.MemberDAO;
import kr.co.rgrg.user.member.vo.FindPassVO;
import kr.co.rgrg.user.member.vo.JoinVO;
import kr.co.rgrg.user.member.vo.LoginVO;
import kr.co.rgrg.user.member.vo.SocialJoinVO;
import kr.co.rgrg.user.member.vo.UpdatePassVO;

public class MemberService {
	
	/**
	 * �Ϲ� ȸ�������� �ϴ� ��
	 * @param jVO
	 * @return
	 */
	public boolean join(JoinVO jVO) {
		boolean flag = false;
		
		MemberDAO memDAO = MemberDAO.getInstance();
		jVO.setBlog_name(jVO.getNickname());
		/////////////////////////��й�ȣ ��ȣȭ �ؾ���
		flag = memDAO.insertMember(jVO) == 1;
		
		return flag;
	}//join
	
	/**
	 * �Ҽ� ȸ�������� �ϴ� ��
	 * @param sjVO
	 * @return
	 */
	public boolean socialJoin(SocialJoinVO sjVO) {
		boolean flag = false;
		return flag;
	}//socialJoin
	
	/**
	 * ���̵� �ߺ��� üũ�ϴ� ��
	 * @param newId
	 * @return
	 */
	public String dupId(String newId) {
		String id = "";
		
		MemberDAO memDAO = MemberDAO.getInstance();
		id = memDAO.selectId(newId);
		
		return id;
	}//dupId
	
	/**
	 * �̸��� �ߺ��� üũ�ϴ� ��
	 * @param newEmail
	 * @return
	 */
	public String dupEmail(String newEmail) {
		String email = "";
		
		MemberDAO memDAO = MemberDAO.getInstance();
		email = memDAO.selectEmail(newEmail);
		
		return email;
	}//dupEmail
	
	/**
	 * �α����� �ϴ� ��
	 * @param lVO
	 * @return
	 */
	public String login(LoginVO lVO) {
		String id = "";
		
		MemberDAO memDAO = MemberDAO.getInstance();
		/////////////////////////��й�ȣ ��ȣȭ �ؾ���
		id = memDAO.selectLogin(lVO);
		
		return id;
	}//login
	
	/**
	 * ���̵� ã�⸦ ���� �Է��� �̸����� DB�� �ִ��� Ȯ���ϴ� ��
	 * @param authEmail
	 * @return
	 */
	public String findIdChkEmail(String authEmail) {
		String email = "";
		
		MemberDAO memDAO = MemberDAO.getInstance();
		email = memDAO.selectFindIdChkEmail(authEmail);
		
		return email;
	}//findIdChkEmail
	
	/**
	 * ���̵� ã��
	 * @param authEmail
	 * @return
	 */
	public String findId(String authEmail) {
		String id = "";
		
		MemberDAO memDAO = MemberDAO.getInstance();
		id = memDAO.selectFindId(authEmail);
		
		return id;
	}//findId
	
	/**
	 * ��й�ȣ ã�⸦ ���� �Է��� �̸����� DB�� �ִ��� Ȯ���ϴ� ��
	 * @param fpVO
	 * @return
	 */
	public String findPassChkEmail(FindPassVO fpVO) {
		String email = "";
		
		MemberDAO memDAO = MemberDAO.getInstance();
		email = memDAO.selectFindPassChkEmail(fpVO);
		
		return email;
	}//findPassChkEmail
	
	/**
	 * ��й�ȣ ����
	 * @param upVO
	 * @return
	 */
	public boolean modifyPass(UpdatePassVO upVO) {
		boolean flag = false;
		
		MemberDAO memDAO = MemberDAO.getInstance();
		/////////////////////////��й�ȣ ��ȣȭ �ؾ���
		flag = memDAO.updatePass(upVO) == 1;
		
		return flag;
	}//modifyPass
	
}//MemberService
