package kr.co.rgrg.user.mypage.service;

import java.util.List;

import javax.inject.Inject;

import org.json.simple.JSONObject;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.co.rgrg.user.mypage.dao.MypageDAO;
import kr.co.rgrg.user.mypage.domain.MypageDomain;
import kr.co.rgrg.user.mypage.domain.StatisticsDomain;
import kr.co.rgrg.user.mypage.vo.PassChkVO;
import kr.co.rgrg.user.mypage.vo.UpdateBlogTitleVO;
import kr.co.rgrg.user.mypage.vo.UpdateEmailFlagVO;
import kr.co.rgrg.user.mypage.vo.UpdateEmailVO;
import kr.co.rgrg.user.mypage.vo.UpdatePassVO;
import kr.co.rgrg.user.mypage.vo.UpdateProfileImgVO;
import kr.co.rgrg.user.mypage.vo.UpdateProfileVO;
import kr.co.rgrg.user.mypage.vo.UpdateSocialDataVO;

public class MypageService {
	
	/**
	 * 마이페이지 첫화면 데이터를 DAO에서 받아서 Controller로 보내는 일
	 * @param id
	 * @return
	 */
	public MypageDomain getMypage(String id) {
		MypageDomain md = null;
		
		MypageDAO mDAO = MypageDAO.getInstance();
		md = mDAO.selectMypage(id);
		
		return md;
	}
	
	/**
	 * DAO를 사용해서 프로필이미지를 변경하고 결과를 Controller로 보내는 일
	 * 
	 * @param upiVO
	 * @return
	 */
	public String modifyProfileImg(UpdateProfileImgVO upiVO) {
		String result = "fail";
		JSONObject json = new JSONObject();
		
		MypageDAO mDAO = MypageDAO.getInstance();
		if(mDAO.updateProfileImg(upiVO) > 0) {
			result = "success";
		}
		
		json.put("result", result);
		return json.toJSONString();
	}
	
	/**
	 * DAO를 사용해서 닉네임, 상태메세지를 변경하고 결과를 Controller로 보내는 일
	 * @param upVO
	 * @return
	 */
	public String modifyProfile(UpdateProfileVO upVO) {
		String result = "fail";
		JSONObject json = new JSONObject();
		
		MypageDAO mDAO = MypageDAO.getInstance();
		if(mDAO.updateProfile(upVO) > 0) {
			result = "success";
		}
		
		json.put("result", result);
		return json.toJSONString();
	}
	
	/**
	 * 블로그 제목을 바꾼 결과를 가공해서 Controller로 넘겨주는 일
	 * @param ubtVO
	 * @return
	 */
	public String modifyBlogTitle(UpdateBlogTitleVO ubtVO) {
		String result = "fail";
		JSONObject json = new JSONObject();
		
		MypageDAO mDAO = MypageDAO.getInstance();
		if(mDAO.updateBlogTitle(ubtVO) == 1) {
			result = "success";
		}
		json.put("result", result);
		return json.toJSONString();
	}
	
	/**
	 * 웹사이트, 깃허브 정보를 바꾼 결과를 가공해서 Controller로 전달해주는 일
	 * @param uwVO
	 * @return
	 */
	public String modifySocialData(UpdateSocialDataVO usVO) {
		String result = "fail";
		JSONObject json = new JSONObject();
		
		MypageDAO mDAO = MypageDAO.getInstance();
		if(mDAO.updateSocialData(usVO) == 1) {
			result = "success";
		}
		
		json.put("result", result);
		return json.toJSONString();
	}
	
	/**
	 * 이메일 정보를 변경한 결과를 가공해서 Controller로 넘겨주는 일
	 * @param ueVO
	 * @return
	 */
	public String modifyEmail(UpdateEmailVO ueVO) {
		String result = "fail";
		JSONObject json = new JSONObject();
		MypageDAO mDAO = MypageDAO.getInstance();
		
		if(mDAO.updateEmail(ueVO) == 1) {
			result = "success";
		}
		
		json.put("result", result);
		return json.toJSONString();
	}
	
	public String modifyEmailFlag(UpdateEmailFlagVO uefVO) {
		String result = "fail";
		JSONObject json = new JSONObject();
		MypageDAO mDAO = MypageDAO.getInstance();
		
		if(mDAO.updateEmailFlag(uefVO) > 0) {
			result = "success";
		}
		
		json.put("result", result);
		return json.toJSONString();
	}
	
	/**
	 * 비밀번호를 비교한 결과를 Controller로 전달
	 * 일치 - 탈퇴
	 * 불일치 - 실패메세지
	 * @param pcVO
	 * @return
	 */
	public String removeMemberChk(PassChkVO pcVO) {
		String result = "false";
		JSONObject json = new JSONObject();
		MypageDAO mDAO = MypageDAO.getInstance();
		if(mDAO.selectPass(pcVO).equals(pcVO.getPass())) {
			if(mDAO.deleteMember(pcVO.getId()) == 1) {
				result = "true";				
			}
		}
		
		json.put("result", result);
		return  result;
	}
	
	/**
	 * 비밀번호 수정 전 비밀번호 확인
	 * @param pcVO
	 * @return
	 */
	public String searchPass(PassChkVO pcVO) {
		MypageDAO mDAO = MypageDAO.getInstance();
		
		return mDAO.selectPass(pcVO);
	}
	
	/**
	 * 비밀번호 수정
	 * @param upVO
	 * @return
	 */
	public boolean modifyPass(UpdatePassVO upVO) {
		boolean flag = false;
		
		MypageDAO mDAO = MypageDAO.getInstance();
		if(mDAO.updatePass(upVO) == 1) {
			flag = true;
		}
		
		return flag;
	}
	
	public List<StatisticsDomain> getStatistics(String url) {
		List<StatisticsDomain> list = null;
		
		return list;
	}
}
