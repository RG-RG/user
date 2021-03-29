package kr.co.rgrg.user.mypage.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.rgrg.user.dao.GetRgrgHandler;
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
	 * ���������� ùȭ�鿡 �ʿ��� ���� DB���� ��ȸ
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
	 * ������ �̹����� �����ϴ� ��
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
	 * �г���, ���¸޼����� �����ϴ� ��
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
	
	/**
	 * ��α� �̸��� �����ϴ� ��
	 * @param ubtVO
	 * @return
	 */
	public int updateBlogTitle(UpdateBlogTitleVO ubtVO) {
		int result = 0;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		result = ss.update("updateBlogTitle", ubtVO);
		ss.commit();
		ss.close();
		
		return result;
	}//updateBlogTitle
	
	/**
	 * ������Ʈ ������ �����ϴ� ��
	 * @param uwVO
	 * @return
	 */
	public int updateSocialData(UpdateSocialDataVO usVO) {
		int result = 0;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		result = ss.update("updateSocialData", usVO);
		ss.commit();
		ss.close();
		
		return result;
	}
	
	/**
	 * �̸��� ������ �����ϴ� ��
	 * @param ueVO
	 * @return
	 */
	public int updateEmail(UpdateEmailVO ueVO) {
		int result = 0;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		result = ss.update("updateEmail", ueVO);
		ss.commit();
		ss.close();
		
		return result;
	}
	
	public int updateEmailFlag(UpdateEmailFlagVO uefVO) {
		int result = 0;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		result = ss.update("updateEmailFlag", uefVO);
		ss.commit();
		ss.close();
		
		return result;
	}
	
	/**
	 * ���̵� ����� ��й�ȣ�� ��ȸ
	 * @param pcVO
	 * @return
	 */
	public String selectPass(PassChkVO pcVO) {
		String pass = "";
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		pass = ss.selectOne("selectPass", pcVO);
		ss.close();
		
		return pass;
	}
	
	/**
	 * Ż���ϱ�
	 * @param id
	 * @return
	 */
	public int deleteMember(String id) {
		int result = 0;
		
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		result = ss.update("updateMember", id);
		ss.commit();
		ss.close();
		
		return result;
	}
	
	public int updatePass(UpdatePassVO upVO) {
		int result = 0;
		SqlSession ss = GetRgrgHandler.getInstance().getSqlSession();
		result = ss.update("kr.co.rgrg.user.mypage.updatePass", upVO);
		ss.commit();
		ss.close();
		return result;
	}
	
	public List<StatisticsDomain> selectStatistics(String url) {
		List<StatisticsDomain> list = null;
		
		return list;
	}

}
