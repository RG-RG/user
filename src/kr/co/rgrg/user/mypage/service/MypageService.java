package kr.co.rgrg.user.mypage.service;

import java.util.List;

import kr.co.rgrg.user.mypage.dao.MypageDAO;
import kr.co.rgrg.user.mypage.domain.MypageDomain;
import kr.co.rgrg.user.mypage.domain.StatisticsDomain;
import kr.co.rgrg.user.mypage.vo.PassChkVO;
import kr.co.rgrg.user.mypage.vo.UpdateBlogTitleVO;
import kr.co.rgrg.user.mypage.vo.UpdateEmailVO;
import kr.co.rgrg.user.mypage.vo.UpdatePassVO;
import kr.co.rgrg.user.mypage.vo.UpdateProfileImgVO;
import kr.co.rgrg.user.mypage.vo.UpdateProfileVO;
import kr.co.rgrg.user.mypage.vo.UpdateWebsiteVO;

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
	public boolean modifyProfileImg(UpdateProfileImgVO upiVO) {
		boolean result = false;
		
		MypageDAO mDAO = MypageDAO.getInstance();
		if(mDAO.updateProfileImg(upiVO) > 0) {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * DAO를 사용해서 닉네임, 상태메세지를 변경하고 결과를 Controller로 보내는 일
	 * @param upVO
	 * @return
	 */
	public boolean modifyProfile(UpdateProfileVO upVO) {
		boolean result = false;
		
		MypageDAO mDAO = MypageDAO.getInstance();
		if(mDAO.updateProfile(upVO) > 0) {
			result = true;
		}
		
		return result;
	}
	
	public boolean modifyBlogTitle(UpdateBlogTitleVO ubtVO) {
		boolean result = false;
		
		
		
		return result;
	}
	
	public boolean modifyWebsite(UpdateWebsiteVO uwVO) {
		boolean result = false;
		
		return result;
	}
	
	public boolean modifyEmail(UpdateEmailVO ueVO) {
		boolean result = false;
		
		return result;
	}
	
	public boolean removeMemberChk(PassChkVO pcVO) {
		boolean flag = false;
		
		return  flag;
	}
	
	public boolean modifyPassChk(PassChkVO pcVO) {
		boolean flag = false;
		
		return flag;
	}
	
	public boolean modifyPass(UpdatePassVO upVO) {
		boolean flag = false;
		
		return flag;
	}
	
	public List<StatisticsDomain> getStatistics(String url) {
		List<StatisticsDomain> list = null;
		
		return list;
	}
}
