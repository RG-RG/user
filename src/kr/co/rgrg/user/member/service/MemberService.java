package kr.co.rgrg.user.member.service;

import kr.co.rgrg.user.member.vo.FindPassVO;
import kr.co.rgrg.user.member.vo.JoinVO;
import kr.co.rgrg.user.member.vo.LoginVO;
import kr.co.rgrg.user.member.vo.SocialJoinVO;

public class MemberService {
	
	/**
	 * �Ϲ� ȸ�������� �ϴ� ��
	 * @param jVO
	 * @return
	 */
	public boolean join(JoinVO jVO) {
		boolean flag = false;
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
		return id;
	}//dupId
	
	/**
	 * �̸��� �ߺ��� üũ�ϴ� ��
	 * @param newEmail
	 * @return
	 */
	public String dupEmail(String newEmail) {
		String email = "";
		return email;
	}//dupEmail
	
	/**
	 * �α����� �ϴ� ��
	 * @param lVO
	 * @return
	 */
	public String login(LoginVO lVO) {
		String id = "";
		return id;
	}//login
	
	/**
	 * ���̵� ã�⸦ ���� �Է��� �̸����� DB�� �ִ��� Ȯ���ϴ� ��
	 * @param authEmail
	 * @return
	 */
	public String findIdChkEmail(String authEmail) {
		String email = "";
		return email;
	}//findIdChkEmail
	
	/**
	 * ���̵� ã��
	 * @param authMsg
	 * @return
	 */
	public String findId(String authMsg) {
		String id = "";
		return id;
	}//findId
	
	/**
	 * ��й�ȣ ã�⸦ ���� �Է��� �̸����� DB�� �ִ��� Ȯ���ϴ� ��
	 * @param fpVO
	 * @return
	 */
	public String findPassChkEmail(FindPassVO fpVO) {
		String email = "";
		return email;
	}//findPassChkEmail
	
	/**
	 * ��й�ȣ ����
	 * @param newPass
	 * @return
	 */
	public boolean modifyPass(String newPass) {
		boolean flag = false;
		return flag;
	}//modifyPass
	
}//MemberService
