package kr.co.rgrg.user.mypage.controller;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.plaf.multi.MultiFileChooserUI;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

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
	
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);

	// 업로드된 파일이 저장될 위치
	private final String PATH = "C:\\Users\\doyeon\\git\\user\\WebContent\\images\\profile\\";
	
	//json 데이터로 응답을 보내기 위한
	@Autowired
	MappingJackson2JsonView jsonView;

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
	@RequestMapping(value="/mypage/modify_profile_img.do", method=RequestMethod.POST)
	@ResponseBody
	public String modifyProfileImg(HttpSession session , UpdateProfileImgVO upiVO) throws Exception{
		upiVO.setId("user1");
		
		MypageService ms = new MypageService();
		
		return ms.modifyProfileImg(upiVO);
	}
	
	@RequestMapping(value="/mypage/upload_img_file", method=RequestMethod.POST, produces="text/plain")
	@ResponseBody
	public ModelAndView upload(HttpSession session, MultipartHttpServletRequest request) throws Exception{
		
		ModelAndView model = new ModelAndView();
		model.setView(jsonView);
		
		Iterator<String> itr = request.getFileNames();
		
		if(itr.hasNext()) {
			List<MultipartFile> mpf = request.getFiles(itr.next());
			
			for(int i= 0; i<mpf.size(); i++) {
				System.out.println(mpf.get(i));
				
				String temp = mpf.get(i).getOriginalFilename().substring(mpf.get(i).getOriginalFilename().lastIndexOf("/")+1);
				File file = new File(PATH + "user1" + temp);
				logger.info(file.getAbsolutePath());
				mpf.get(i).transferTo(file);
			}
			
			JSONObject json = new JSONObject();
			json.put("code", "success");
			model.addObject("result", json);
			return model;
		} else {

			JSONObject json = new JSONObject();
			json.put("code", "fail");
			model.addObject("result", json);
			return model;
		}
	
	
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
