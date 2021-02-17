package kr.co.rgrg.user.mypage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.rgrg.user.mypage.vo.PassChkVO;
import kr.co.rgrg.user.mypage.vo.UpdateBlogTitleVO;
import kr.co.rgrg.user.mypage.vo.UpdateEmailVO;
import kr.co.rgrg.user.mypage.vo.UpdatePassVO;
import kr.co.rgrg.user.mypage.vo.UpdateProfileImgVO;
import kr.co.rgrg.user.mypage.vo.UpdateProfileVO;
import kr.co.rgrg.user.mypage.vo.UpdateWebsiteVO;

@Controller
public class MypageController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getMypage(HttpSession session, Model model) {
		
		return "";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String modifyProfileImg(HttpSession session, UpdateProfileImgVO upiVO) {
		
		return "";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String modifyProfile(HttpSession session, UpdateProfileVO upVO) {
		
		return "";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String modifyBlogTitle(HttpSession session, UpdateBlogTitleVO ubtVO) {
		
		return "";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String modifyWebsite(HttpSession session, UpdateWebsiteVO uwVO) {
		
		return "";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String modifyEmail(HttpSession session, UpdateEmailVO ueVO) {
		
		return "";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getRemoveMember() {
		
		return "";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String removeMemberChk(HttpSession session, Model model, PassChkVO pcVO) {
		
		return "";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getModifyPassForm() {
		
		return "";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String modifyPassChk(PassChkVO pcVO, HttpSession session) {
		
		return "";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String modifyPass(UpdatePassVO upVO, HttpSession session) {
		
		return "";
	}
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String getStatistics(HttpSession session, Model model) {
		
		return "";
	}
}
