package kr.co.rgrg.user.mypage.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.co.rgrg.user.dao.GetRgrgHandler;
import kr.co.rgrg.user.mypage.dao.MypageDAO;
import kr.co.rgrg.user.mypage.domain.MypageDomain;
import kr.co.rgrg.user.mypage.domain.StatisticsDomain;
import kr.co.rgrg.user.mypage.vo.PassChkVO;
import kr.co.rgrg.user.mypage.vo.UpdateBlogTitleVO;
import kr.co.rgrg.user.mypage.vo.UpdateEmailVO;
import kr.co.rgrg.user.mypage.vo.UpdatePassVO;
import kr.co.rgrg.user.mypage.vo.UpdateProfileImgVO;
import kr.co.rgrg.user.mypage.vo.UpdateProfileVO;
import kr.co.rgrg.user.mypage.vo.UpdateSocialDataVO;

public class MypageService {
	
	/**
	 * ���������� ùȭ�� �����͸� DAO���� �޾Ƽ� Controller�� ������ ��
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
	 * DAO�� ����ؼ� �������̹����� �����ϰ� ����� Controller�� ������ ��
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
	 * DAO�� ����ؼ� �г���, ���¸޼����� �����ϰ� ����� Controller�� ������ ��
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
	
	/**
	 * ��α� ������ �ٲ� ����� �����ؼ� Controller�� �Ѱ��ִ� ��
	 * @param ubtVO
	 * @return
	 */
	public boolean modifyBlogTitle(UpdateBlogTitleVO ubtVO) {
		boolean result = false;
		
		MypageDAO mDAO = MypageDAO.getInstance();
		if(mDAO.updateBlogTitle(ubtVO) == 1) {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * ������Ʈ, ����� ������ �ٲ� ����� �����ؼ� Controller�� �������ִ� ��
	 * @param uwVO
	 * @return
	 */
	public boolean modifySocialData(UpdateSocialDataVO usVO) {
		boolean result = false;
		
		MypageDAO mDAO = MypageDAO.getInstance();
		if(mDAO.updateSocialData(usVO) == 1) {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * �̸��� ������ ������ ����� �����ؼ� Controller�� �Ѱ��ִ� ��
	 * @param ueVO
	 * @return
	 */
	public boolean modifyEmail(UpdateEmailVO ueVO) {
		boolean result = false;
		
		MypageDAO mDAO = MypageDAO.getInstance();
		if(mDAO.updateEmail(ueVO) == 1) {
			result = true;
		}
		
		return result;
	}
	
	/**
	 * ��й�ȣ�� ���� ����� Controller�� ����
	 * ��ġ - Ż��
	 * ����ġ - ���и޼���
	 * @param pcVO
	 * @return
	 */
	public boolean removeMemberChk(PassChkVO pcVO) {
		boolean result = false;
		
		MypageDAO mDAO = MypageDAO.getInstance();
		if(mDAO.selectPass(pcVO).equals(pcVO.getPass())) {
			if(mDAO.deleteMember(pcVO.getId()) == 1) {
				result = true;				
			}
		}
		
		return  result;
	}
	
	/**
	 * ��й�ȣ ���� �� ��й�ȣ Ȯ��
	 * @param pcVO
	 * @return
	 */
	public boolean modifyPassChk(PassChkVO pcVO) {
		boolean flag = false;
		
		MypageDAO mDAO = MypageDAO.getInstance();
		if(pcVO.getPass() == mDAO.selectPass(pcVO)) {
			flag = true;
		}
		
		return flag;
	}
	
	/**
	 * ��й�ȣ ����
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
