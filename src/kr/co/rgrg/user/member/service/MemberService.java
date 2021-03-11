package kr.co.rgrg.user.member.service;

import org.json.simple.JSONObject;

import kr.co.rgrg.user.member.dao.MemberDAO;
import kr.co.rgrg.user.member.domain.LoginDomain;
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
	 * @param id
	 * @return
	 */
	public String dupId(String id) {
		String dupId = "";
		
		MemberDAO memDAO = MemberDAO.getInstance();
		dupId = memDAO.selectId(id);
		JSONObject json = new JSONObject();
		json.put("dup_id", dupId != null);
		
		return json.toJSONString();
	}//dupId
	
	/**
	 * 이메일 중복을 체크하는 일
	 * @param auth_email
	 * @return
	 */
	public String dupEmail(String auth_email) {
		String dupEmail = "";
		
		MemberDAO memDAO = MemberDAO.getInstance();
		dupEmail = memDAO.selectEmail(auth_email);
		JSONObject json = new JSONObject();
		json.put("dup_email", dupEmail != null);
		
		return json.toJSONString();
	}//dupEmail
	
	/**
	 * 닉네임 중복을 체크하는 일
	 * @param nickname
	 * @return
	 */
	public String dupNick(String nickname) {
		String dupNick = "";
		
		MemberDAO memDAO = MemberDAO.getInstance();
		dupNick = memDAO.selectNickname(nickname);
		JSONObject json = new JSONObject();
		json.put("dup_nick", dupNick != null);
		
		return json.toJSONString();
	}//dupNick
	
	/**
	 * 로그인을 하는 일
	 * @param lVO
	 * @return
	 */
	public LoginDomain login(LoginVO lVO) {
		LoginDomain ld = null;
		
		MemberDAO memDAO = MemberDAO.getInstance();
		ld = memDAO.selectLogin(lVO);
		
		return ld;
	}//login
	
	/**
	 * 아이디 찾기를 위해 입력한 이메일이 DB에 있는지 확인하는 일
	 * @param auth_email
	 * @return
	 */
	public String findIdChkEmail(String auth_email) {
		String chkEmail = "";
		
		MemberDAO memDAO = MemberDAO.getInstance();
		chkEmail = memDAO.selectFindIdChkEmail(auth_email);
		JSONObject json = new JSONObject();
		json.put("id_chk_email", chkEmail != null);
		
		return json.toJSONString();
	}//findIdChkEmail
	
	/**
	 * 아이디 찾기
	 * @param auth_email
	 * @return
	 */
	public String findId(String auth_email) {
		String id = "";
		
		MemberDAO memDAO = MemberDAO.getInstance();
		id = memDAO.selectFindId(auth_email);
		
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
		JSONObject json = new JSONObject();
		json.put("pass_chk_email", email != null);
		
		return json.toJSONString();
	}//findPassChkEmail
	
	/**
	 * 비밀번호 변경
	 * @param upVO
	 * @return
	 */
	public boolean modifyPass(UpdatePassVO upVO) {
		boolean flag = false;
		
		MemberDAO memDAO = MemberDAO.getInstance();
		flag = memDAO.updatePass(upVO) == 1;
		
		return flag;
	}//modifyPass
	
}//MemberService
