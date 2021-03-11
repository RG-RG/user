package kr.co.rgrg.user.mypage.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.rgrg.user.mypage.domain.MypageDomain;
import kr.co.rgrg.user.mypage.service.MypageService;
import kr.co.rgrg.user.mypage.vo.PassChkVO;
import kr.co.rgrg.user.mypage.vo.UpdateBlogTitleVO;
import kr.co.rgrg.user.mypage.vo.UpdateEmailFlagVO;
import kr.co.rgrg.user.mypage.vo.UpdateEmailVO;
import kr.co.rgrg.user.mypage.vo.UpdatePassVO;
import kr.co.rgrg.user.mypage.vo.UpdateProfileImgVO;
import kr.co.rgrg.user.mypage.vo.UpdateProfileVO;
import kr.co.rgrg.user.mypage.vo.UpdateSocialDataVO;

@Controller
public class MypageController {
	
	/**
	 * Mypage 첫화면
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mypage/index", method={RequestMethod.GET, RequestMethod.POST})
	public String getMypage(HttpSession session, Model model) {
		// 임시변수 ///////////////////
		String id = "user1";
		////////////////////////////
		MypageService ms = new MypageService();
		MypageDomain md = ms.getMypage(id);
		
		model.addAttribute("member_data", md);
		
		return "mypage/mypage";
	}
	
	/**
	 * 프로필 이미지 변경
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
	 * 프로필 상태메세지 변경
	 * @param session
	 * @param upVO
	 * @return
	 */
	@RequestMapping(value="/mypage/modify_profile.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String modifyProfile(HttpSession session, UpdateProfileVO upVO) {
		upVO.setId("user1");
		MypageService ms = new MypageService();
			
		return ms.modifyProfile(upVO);
	}
	
	/**
	 * 블로그 타이틀 수정
	 * @param session
	 * @param ubtVO
	 * @return
	 */
	@RequestMapping(value="/mypage/modify_title.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String modifyBlogTitle(HttpSession session, UpdateBlogTitleVO ubtVO) {
		ubtVO.setId("user1");
		MypageService ms = new MypageService();
		return ms.modifyBlogTitle(ubtVO);
	}
	
	/**
	 * 웹사이트 정보 수정
	 * @param session
	 * @param uwVO
	 * @return
	 */
	@RequestMapping(value="/mypage/modify_social.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String modifySocial(HttpSession session, UpdateSocialDataVO usVO) {
		usVO.setId("user1");
		MypageService ms = new MypageService();
		return ms.modifySocialData(usVO);
	}
	
	/**
	 * 이메일 정보 수정
	 * @param session
	 * @param ueVO
	 * @return
	 */
	@RequestMapping(value="/mypage/modify_email.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String modifyEmail(HttpSession session, UpdateEmailVO ueVO) {
		ueVO.setId("user1");
		MypageService ms = new MypageService();
		
		return ms.modifyEmail(ueVO);
	}
	
	/**
	 * 이메일 알림 수신 여부
	 * @param session
	 * @param uefVO
	 * @return
	 */
	@RequestMapping(value="/mypage/modify_email_flag.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String modifyEmailFlag(HttpSession session, UpdateEmailFlagVO uefVO) {
		uefVO.setId("user1");
		MypageService ms = new MypageService();
		return ms.modifyEmailFlag(uefVO);
	}
	
	@RequestMapping(value="/mypage/remove_member.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String removeMemberChk(HttpSession session, PassChkVO pcVO) {
		pcVO.setId("user1");
		MypageService ms = new MypageService();
		
		return ms.removeMemberChk(pcVO);
	}
	
	@RequestMapping(value="/mypage/modify_pass_form.do", method=RequestMethod.GET)
	public String getModifyPassChkForm() {
		
		return "mypage/change_pass_chk";
	}
	
	@RequestMapping(value="/mypage/modify_pass_chk.do", method=RequestMethod.POST)
	public String modifyPassChk(PassChkVO pcVO, HttpSession session, Model model) {
		MypageService ms = new MypageService();
		pcVO.setId("user1");
		boolean result = ms.modifyPassChk(pcVO);
		if (result) {
			return "mypage/change_pass_form";			
		}else {
			return "mypage/change_pass_chk";
		}
		
		
	}
	
	
	@RequestMapping(value="/mypage/modify_pass.do", method=RequestMethod.POST)
	public String modifyPass(UpdatePassVO upVO, HttpSession session, Model model) {
		MypageService ms = new MypageService();
		upVO.setId("user1");
		boolean result = ms.modifyPass(upVO);
		model.addAttribute("result_flag", result);
		
		return "mypage/change_pass";
	}
	
	@RequestMapping(value="/mypage/get_statistics.do", method=RequestMethod.GET)
	public String getStatistics(HttpSession session, Model model) {
		
		return "";
	}
}
