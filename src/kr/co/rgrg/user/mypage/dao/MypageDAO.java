package kr.co.rgrg.user.mypage.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.rgrg.user.dao.GetRgrgHandler;
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
	
	/**
	 * 마이페이지 첫화면에 필요한 정보 DB에서 조회
	 * @param id
	 * @return
	 */
	public MypageDomain selectMypage(String id) {
		MypageDomain md = null;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		md = ss.selectOne("selectMemberData", id);
		ss.close();
		
		return md;
	}//selectMypage
	
	/**
	 * 프로필 이미지를 변경하는 일
	 * @param upiVO
	 * @return
	 */
	public int updateProfileImg(UpdateProfileImgVO upiVO) {
		int result = 0;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		result = ss.update("updateProfileImg", upiVO);
		ss.commit();
		ss.close();
		
		return result;
	}//updateProfileImg
	
	/**
	 * 닉네임, 상태메세지를 변경하는 일
	 * @param upVO
	 * @return
	 */
	public int updateProfile(UpdateProfileVO upVO) {
		int result = 0;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		result = ss.update("updateProfile", upVO);
		ss.commit();
		ss.close();
		
		return result;
	}//updateProfile
	
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
	
//	public static void main(String[] args) {
//		MypageDAO mDAO = MypageDAO.getInstance();
////		UpdateProfileVO upVO = new UpdateProfileVO();
////		upVO.setId("user1");
////		upVO.setNickname("바뀐 닉네임");
////		upVO.setStatement("바뀐 상태메세지");
////		int result = mDAO.updateProfileMsg(upVO);
////		
//		MypageDomain md = null; 
//		md = mDAO.selectMypage("user1");
//		System.out.println(md);
//	}
}
