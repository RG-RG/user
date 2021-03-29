package kr.co.rgrg.user.mypage.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
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

@SessionAttributes("id")
@Controller
public class MypageController {
	
	private static final Logger logger = LoggerFactory.getLogger(MypageController.class);

	// 업로드된 파일이 저장될 위치
	private final String PATH = "images\\profile\\";
	
	//json 데이터로 응답을 보내기 위한
	@Autowired
	MappingJackson2JsonView jsonView;
	
	@Inject
	BCryptPasswordEncoder passEncoder;

	/**
	 * Mypage 첫화면
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mypage.do", method= {RequestMethod.GET, RequestMethod.POST})
	public String getMypage(HttpSession session, Model model) {
		String id = (String)session.getAttribute("id");
		System.out.println(id + "======================================");
		if(id != null) {
			MypageService ms = new MypageService();
			MypageDomain md = ms.getMypage(id);
			
			model.addAttribute("member_data", md);
		}
		
		return "mypage/mypage";
	}
	
	@RequestMapping(value="/change_info_form.do", method=RequestMethod.GET)
	public String getChangeInfo(HttpSession session, Model model) {
		String id = (String)session.getAttribute("id");
		
		if (id != null) {
			MypageService ms = new MypageService();
			MypageDomain md = ms.getMypage(id);
			
			model.addAttribute("member_data", md);
		}
		return "mypage/change_info";
	}
	
	/**
	 * 프로필 이미지 변경
	 * @param session
	 * @param upiVO
	 * @return
	 */
	@RequestMapping(value="/modify_profile_img.do", method=RequestMethod.POST)
	@ResponseBody
	public String modifyProfileImg(HttpSession session , UpdateProfileImgVO upiVO) throws Exception{
		String id = (String)session.getAttribute("id");
		String result = null;
		
		if (id != null) {
			upiVO.setId(id);
			System.out.println(upiVO.getProfile_img());
			String fileName = upiVO.getProfile_img();
			upiVO.setProfile_img(fileName);
			result = new MypageService().modifyProfileImg(upiVO);
		}
		
		return result;
	}
	
	@RequestMapping(value="/upload_img_file.do", method=RequestMethod.POST, produces="text/plain")
	@ResponseBody
	public ModelAndView upload(HttpSession session, MultipartHttpServletRequest request) throws Exception{
		String id = (String)session.getAttribute("id");
		ModelAndView model = new ModelAndView();
		model.setView(jsonView);
		
		Iterator<String> itr = request.getFileNames();
		
		if(itr.hasNext() && id != null) {
			List<MultipartFile> mpf = request.getFiles(itr.next());
			
			for(int i= 0; i<mpf.size(); i++) {
				String root_path = request.getSession().getServletContext().getRealPath("/");  
				String fileName = mpf.get(i).getOriginalFilename();
				
				System.out.println(root_path + PATH + fileName);
				File file = new File(root_path + PATH + fileName);
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
	@RequestMapping(value="/modify_profile.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String modifyProfile(HttpSession session, UpdateProfileVO upVO) {
		String id = (String)session.getAttribute("id");
		String result = null;
		
		if (id != null) {
			upVO.setId(id);
			result = new MypageService().modifyProfile(upVO);			
		}
		
		return result;
	}
	
	/**
	 * 블로그 타이틀 수정
	 * @param session
	 * @param ubtVO
	 * @return
	 */
	@RequestMapping(value="/modify_title.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String modifyBlogTitle(HttpSession session, UpdateBlogTitleVO ubtVO) {
		String id = (String)session.getAttribute("id");
		String result = null;
		
		if( id != null) {
			ubtVO.setId(id);
			result = new MypageService().modifyBlogTitle(ubtVO);	
		}
		
		return result;
	}
	
	/**
	 * 웹사이트 정보 수정
	 * @param session
	 * @param uwVO
	 * @return
	 */
	@RequestMapping(value="/modify_social.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String modifySocial(HttpSession session, UpdateSocialDataVO usVO) {
		String id = (String)session.getAttribute("id");
		String result = null;
		
		if(id != null) {
			usVO.setId(id);
			result = new MypageService().modifySocialData(usVO);
		}
		
		return result;
	}
	
	/**
	 * 이메일 정보 수정
	 * @param session
	 * @param ueVO
	 * @return
	 */
	@RequestMapping(value="/modify_email.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String modifyEmail(HttpSession session, UpdateEmailVO ueVO) {
		String id = (String)session.getAttribute("id");
		String result = null;
		
		if(id != null) {
			ueVO.setId(id);
			result = new MypageService().modifyEmail(ueVO);			
		}
		
		return result;
	}
	
	/**
	 * 이메일 알림 수신 여부
	 * @param session
	 * @param uefVO
	 * @return
	 */
	@RequestMapping(value="/modify_email_flag.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String modifyEmailFlag(HttpSession session, UpdateEmailFlagVO uefVO) {
		String id = (String)session.getAttribute("id");
		String result = null;
		
		if(id != null) {
			uefVO.setId(id);
			result = new MypageService().modifyEmailFlag(uefVO);
		}
		
		return result;
	}
	
	@RequestMapping(value="/remove_member.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public String removeMemberChk(HttpSession session, PassChkVO pcVO) {
		String id = (String)session.getAttribute("id");
		String result = null;
		
		if(id != null) {
			pcVO.setId(id);
			JSONObject json = new JSONObject();
			
			try {
				boolean flag = passEncoder.matches(pcVO.getPass(), new MypageService().searchPass(pcVO));
				String removeMemberFlag = "fail";
				if(flag == true) {
					removeMemberFlag = new MypageService().removeMember(pcVO);
				}
				System.out.println(removeMemberFlag);
				json.put("result", removeMemberFlag);
			} catch (NullPointerException ne) {
				json.put("result", "fail");
			}
			result = json.toJSONString();
		}
		
		return result;
	}
	
	@RequestMapping(value="/modify_pass_chk_form.do", method=RequestMethod.GET)
	public String getModifyPassChkForm() {
		
		return "mypage/change_pass_chk";
	}
	
	@RequestMapping(value="/modify_pass_chk.do", method=RequestMethod.POST)
	@ResponseBody
	public String modifyPassChk(PassChkVO pcVO, HttpSession session) {
		String id = (String)session.getAttribute("id");
		String result = null;
		
		if(id != null) {
			pcVO.setId(id);
			JSONObject json = new JSONObject();
			
			try {
				boolean flag = passEncoder.matches(pcVO.getPass(), new MypageService().searchPass(pcVO));
				if(flag == true) {
					json.put("result", "success");				
				}else {
					json.put("result", "fail");
				}
				
			} catch (NullPointerException ne) {
				json.put("result", "fail");
			}
			
			result = json.toJSONString();
		}
		
		return result;
	}
	
	@RequestMapping(value="/mypage_modify_pass_form.do", method=RequestMethod.POST)
	public String ModifyPassForm() {
		System.out.println("비밀번호 바꾸기 jsp");
		return "mypage/change_pass_form";
	}
	
	/**
	 * 비밀번호를 변경하는 일
	 * @param upVO
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/mypage_modify_pass.do", method=RequestMethod.POST)
	@ResponseBody
	public String modifyPass(UpdatePassVO upVO, HttpSession session, Model model) {
		String id = (String)session.getAttribute("id");
		String result = null;
		
		if(id != null) {
			upVO.setId(id);
			JSONObject json = new JSONObject();
			upVO.setPass(passEncoder.encode(upVO.getPass()));
			boolean result_flag = new MypageService().modifyPass(upVO);
			json.put("result_flag", result_flag);
			
			result = json.toJSONString();
		}
		
		return result;
	}

	@RequestMapping(value="/analytics_form.do", method=RequestMethod.GET)
	public String getStatisticsForm(HttpSession session, Model model) {
		String id = (String)session.getAttribute("id");
		
		return "mypage/analytics";
	}
	
	@RequestMapping(value="/analytics.do", method=RequestMethod.GET)
	@ResponseBody
	public String getStatistics(HttpSession session, Model model, HttpServletRequest request) {
		String id = (String)session.getAttribute("id");
		
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		String analyticsResult = HelloAnalytics.getResult(id, startDate, endDate);
		
		String result = analyticsResult;
		
		System.out.println(result);
		
		
		return result;
	}
}
