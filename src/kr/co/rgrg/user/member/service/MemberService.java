package kr.co.rgrg.user.member.service;

import kr.co.rgrg.user.member.dao.MemberDAO;
import kr.co.rgrg.user.member.vo.FindPassVO;
import kr.co.rgrg.user.member.vo.JoinVO;
import kr.co.rgrg.user.member.vo.LoginVO;
import kr.co.rgrg.user.member.vo.SocialJoinVO;
import kr.co.rgrg.user.member.vo.UpdatePassVO;

public class MemberService {
	
	/**
	 * 일반 회원가입을 하는 일
	 * @param jVO
	 * @return
	 */
	public boolean join(JoinVO jVO) {
		boolean flag = false;
		
		MemberDAO memDAO = MemberDAO.getInstance();
		jVO.setBlog_name(jVO.getNickname());
		/////////////////////////비밀번호 암호화 해야함
		flag = memDAO.insertMember(jVO) == 1;
		
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
		
		MemberDAO memDAO = MemberDAO.getInstance();
		id = memDAO.selectId(newId);
		
		return id;
	}//dupId
	
	/**
	 * 이메일 중복을 체크하는 일
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
	 * 로그인을 하는 일
	 * @param lVO
	 * @return
	 */
	public String login(LoginVO lVO) {
		String id = "";
		
		MemberDAO memDAO = MemberDAO.getInstance();
		/////////////////////////비밀번호 암호화 해야함
		id = memDAO.selectLogin(lVO);
		
		return id;
	}//login
	
	/**
	 * 아이디 찾기를 위해 입력한 이메일이 DB에 있는지 확인하는 일
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
	 * 아이디 찾기
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
	 * 비밀번호 찾기를 위해 입력한 이메일이 DB에 있는지 확인하는 일
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
	 * 비밀번호 변경
	 * @param upVO
	 * @return
	 */
	public boolean modifyPass(UpdatePassVO upVO) {
		boolean flag = false;
		
		MemberDAO memDAO = MemberDAO.getInstance();
		/////////////////////////비밀번호 암호화 해야함
		flag = memDAO.updatePass(upVO) == 1;
		
		return flag;
	}//modifyPass
	
}//MemberService
