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
import kr.co.rgrg.user.mypage.vo.UpdateWebsiteVO;

@Controller
public class MypageController {
	
	/**
	 * Mypage 첫화면
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mypage/main.do", method=RequestMethod.POST)
	public String getMypage(HttpSession session, Model model) {
		// 임시변수 ///////////////////
		String id = "user1";
		////////////////////////////
		MypageService ms = new MypageService();
		MypageDomain md = ms.getMypage(id);
		
		model.addAttribute("member_data", md);
		
		return "mypage/main";
	}
	
	/**
	 * 프로필 이미지 변경
	 * @param session
	 * @param upiVO
	 * @return
	 */
	@RequestMapping(value="/mypage/modify_profile_img.do", method=RequestMethod.GET)
	public String modifyProfileImg(HttpSession session, UpdateProfileImgVO upiVO) {
		
		MypageService ms = new MypageService();
		boolean result = ms.modifyProfileImg(upiVO);
		
		return "";
	}
	
	/**
	 * 프로필 상태메세지 변경
	 * @param session
	 * @param upVO
	 * @return
	 */
	@RequestMapping(value="/mypage/modify_profile_msg.do", method=RequestMethod.GET)
	public String modifyProfile(HttpSession session, UpdateProfileVO upVO) {
		
		MypageService ms = new MypageService();
		boolean result = ms.modifyProfile(upVO);
		
		return "";
	}
	
	@RequestMapping(value="/mypage/modify_title.do", method=RequestMethod.GET)
	public String modifyBlogTitle(HttpSession session, UpdateBlogTitleVO ubtVO) {
		
		return "";
	}
	
	@RequestMapping(value="/mypage/modify_website.do", method=RequestMethod.GET)
	public String modifyWebsite(HttpSession session, UpdateWebsiteVO uwVO) {
		
		return "";
	}
	
	@RequestMapping(value="/mypage/modify_email.do", method=RequestMethod.GET)
	public String modifyEmail(HttpSession session, UpdateEmailVO ueVO) {
		
		return "";
	}
	
	@RequestMapping(value="/mypage/remove_member.do", method=RequestMethod.GET)
	public String getRemoveMember() {
		
		return "";
	}
	
	@RequestMapping(value="/mypage/remove_member_chk.do", method=RequestMethod.GET)
	public String removeMemberChk(HttpSession session, Model model, PassChkVO pcVO) {
		
		return "";
	}
	
	@RequestMapping(value="/mypage/get_modify_pass.do", method=RequestMethod.GET)
	public String getModifyPassForm() {
		
		return "";
	}
	
	@RequestMapping(value="/mypage/modify_pass_chk.do", method=RequestMethod.GET)
	public String modifyPassChk(PassChkVO pcVO, HttpSession session) {
		
		return "";
	}
	
	@RequestMapping(value="/mypage/modify_pass.do", method=RequestMethod.GET)
	public String modifyPass(UpdatePassVO upVO, HttpSession session) {
		
		return "";
	}
	
	@RequestMapping(value="/mypage/get_statistics.do", method=RequestMethod.GET)
	public String getStatistics(HttpSession session, Model model) {
		
		return "";
	}
}
