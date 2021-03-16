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
	 * DAO�� ����ؼ� �г���, ���¸޼����� �����ϰ� ����� Controller�� ������ ��
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
	 * ��α� ������ �ٲ� ����� �����ؼ� Controller�� �Ѱ��ִ� ��
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
	 * ������Ʈ, ����� ������ �ٲ� ����� �����ؼ� Controller�� �������ִ� ��
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
	 * �̸��� ������ ������ ����� �����ؼ� Controller�� �Ѱ��ִ� ��
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
	 * ��й�ȣ�� ���� ����� Controller�� ����
	 * ��ġ - Ż��
	 * ����ġ - ���и޼���
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
	 * ��й�ȣ ���� �� ��й�ȣ Ȯ��
	 * @param pcVO
	 * @return
	 */
	public String searchPass(PassChkVO pcVO) {
		MypageDAO mDAO = MypageDAO.getInstance();
		
		return mDAO.selectPass(pcVO);
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
