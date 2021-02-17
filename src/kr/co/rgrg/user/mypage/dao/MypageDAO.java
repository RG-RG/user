package kr.co.rgrg.user.mypage.dao;

import java.util.List;

import kr.co.rgrg.user.mypage.domain.MypageDomain;
import kr.co.rgrg.user.mypage.domain.StatisticsDomain;
import kr.co.rgrg.user.mypage.vo.PassChkVO;
import kr.co.rgrg.user.mypage.vo.UpdateBlogTitleVO;
import kr.co.rgrg.user.mypage.vo.UpdateEmailVO;
import kr.co.rgrg.user.mypage.vo.UpdatePassVO;
import kr.co.rgrg.user.mypage.vo.UpdateProfileImgVO;
import kr.co.rgrg.user.mypage.vo.UpdateProfileVO;
import kr.co.rgrg.user.mypage.vo.UpdateWebsiteVO;

public class MypageDAO {
	private static MypageDAO mpDAO;
	
	public MypageDAO() {
		org.apache.ibatis.logging.LogFactory.useLog4JLogging();
	}
	
	public static MypageDAO getInstance() {
		if(mpDAO == null) {
			mpDAO = new MypageDAO();
		}//end if
		
		return mpDAO;
	}//getInstance
	
	public MypageDomain selectMypage(String id) {
		MypageDomain mpDomain = null;
		return mpDomain;
	}
	
	public int updateProfileImg(UpdateProfileImgVO upiVO) {
		int result = 0;
		
		return result;
	}
	
	public int updateProfile(UpdateProfileVO upVO) {
		int result = 0;
		
		return result;
	}
	
	public int updateBlogTitle(UpdateBlogTitleVO ubtVO) {
		int result = 0;
		
		return result;
	}
	
	public int updateWebsite(UpdateWebsiteVO uwVO) {
		int result = 0;
		
		return result;
	}
	
	public int updateEmail(UpdateEmailVO ueVO) {
		int result = 0;
		
		return result;
	}
	
	public String selectPass(PassChkVO pcVO) {
		String pass = "";
		
		return pass;
	}
	
	public int deleteMember(String id) {
		int result = 0;
		
		return result;
	}
	
	public int updatePass(UpdatePassVO upVO) {
		int result = 0;
		
		return result;
	}
	
	public List<StatisticsDomain> selectStatistics(String url) {
		List<StatisticsDomain> list = null;
		
		return list;
	}
}
