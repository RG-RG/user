package kr.co.rgrg.user.mypage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.rgrg.user.mypage.domain.MypageDomain;
import kr.co.rgrg.user.mypage.service.MypageService;
import kr.co.rgrg.user.mypage.vo.PassChkVO;
import kr.co.rgrg.user.mypage.vo.UpdateBlogTitleVO;
import kr.co.rgrg.user.mypage.vo.UpdateEmailVO;
import kr.co.rgrg.user.mypage.vo.UpdatePassVO;
import kr.co.rgrg.user.mypage.vo.UpdateProfileImgVO;
import kr.co.rgrg.user.mypage.vo.UpdateProfileVO;
import kr.co.rgrg.user.mypage.vo.UpdateSocialDataVO;

@Controller
public class MypageController {
	
	/**
	 * Mypage ùȭ��
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mypage/index.do", method={RequestMethod.GET, RequestMethod.POST})
	public String getMypage(HttpSession session, Model model) {
		// �ӽú��� ///////////////////
		String id = "user1";
		////////////////////////////
		MypageService ms = new MypageService();
		MypageDomain md = ms.getMypage(id);
		
		model.addAttribute("member_data", md);
		
		return "mypage/mypage";
	}
	
	/**
	 * ������ �̹��� ����
	 * @param session
	 * @param upiVO
	 * @return
	 */
	@RequestMapping(value="/mypage/modify_profile_img.do", method=RequestMethod.GET)
	public String modifyProfileImg(HttpSession session, UpdateProfileImgVO upiVO, Model model) {
		
		MypageService ms = new MypageService();
		boolean result = ms.modifyProfileImg(upiVO);
		
		model.addAttribute("result_flag", result);
		
		return "mypage/change_img";
	}
	
	/**
	 * ������ ���¸޼��� ����
	 * @param session
	 * @param upVO
	 * @return
	 */
	@RequestMapping(value="/mypage/modify_profile_msg.do", method=RequestMethod.GET)
	public String modifyProfile(HttpSession session, UpdateProfileVO upVO, Model model) {
		
		MypageService ms = new MypageService();
		boolean result = ms.modifyProfile(upVO);
		
		model.addAttribute("result_flag", result);
		
		return "mypage/change_profile";
	}
	
	/**
	 * ��α� Ÿ��Ʋ ����
	 * @param session
	 * @param ubtVO
	 * @return
	 */
	@RequestMapping(value="/mypage/modify_title.do", method=RequestMethod.GET)
	public String modifyBlogTitle(HttpSession session, UpdateBlogTitleVO ubtVO, Model model) {
		
		MypageService ms = new MypageService();
		boolean result = ms.modifyBlogTitle(ubtVO);
		
		model.addAttribute("result_flag", result);
		
		return "mypage/change_title";
	}
	
	/**
	 * ������Ʈ ���� ����
	 * @param session
	 * @param uwVO
	 * @return
	 */
	@RequestMapping(value="/mypage/modify_website.do", method=RequestMethod.GET)
	public String modifyWebsite(HttpSession session, UpdateSocialDataVO usVO, Model model) {
		
		MypageService ms = new MypageService();
		boolean result = ms.modifySocialData(usVO);
		model.addAttribute("result_flag", result);
		
		return "mypage/change_website";
	}
	
	/**
	 * �̸��� ���� ����
	 * @param session
	 * @param ueVO
	 * @return
	 */
	@RequestMapping(value="/mypage/modify_email.do", method=RequestMethod.GET)
	public String modifyEmail(HttpSession session, UpdateEmailVO ueVO, Model model) {
		
		MypageService ms = new MypageService();
		boolean result = ms.modifyEmail(ueVO);
		model.addAttribute("result_flag", result);
		return "mypage/change_email";
	}
	
	@RequestMapping(value="/mypage/remove_member.do", method=RequestMethod.GET)
	public String getRemoveMember() {
		
		return "mypage/remove_member_form";
	}
	
	@RequestMapping(value="/mypage/remove_member_chk.do", method=RequestMethod.GET)
	public String removeMemberChk(HttpSession session, Model model, PassChkVO pcVO) {
		
		MypageService ms = new MypageService();
		boolean result = ms.removeMemberChk(pcVO);
		model.addAttribute("result_flag", result);
		return "mypage/remove_member";
	}
	
	@RequestMapping(value="/mypage/get_modify_pass.do", method=RequestMethod.GET)
	public String getModifyPassForm() {
		
		return "change_pass_form";
	}
	
	@RequestMapping(value="/mypage/modify_pass_chk.do", method=RequestMethod.GET)
	public String modifyPassChk(PassChkVO pcVO, HttpSession session, Model model) {
		MypageService ms = new MypageService();
		boolean result = ms.modifyPassChk(pcVO);
		model.addAttribute("result_flag", result);
		
		return "mypage/change_pass_chk";
	}
	
	@RequestMapping(value="/mypage/modify_pass.do", method=RequestMethod.GET)
	public String modifyPass(UpdatePassVO upVO, HttpSession session, Model model) {
		MypageService ms = new MypageService();
		boolean result = ms.modifyPass(upVO);
		model.addAttribute("result_flag", result);
		
		return "mypage/change_pass";
	}
	
	@RequestMapping(value="/mypage/get_statistics.do", method=RequestMethod.GET)
	public String getStatistics(HttpSession session, Model model) {
		
		return "";
	}
}
