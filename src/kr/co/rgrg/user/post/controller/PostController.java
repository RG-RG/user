package kr.co.rgrg.user.post.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import kr.co.rgrg.user.post.domain.PostDomain;
import kr.co.rgrg.user.post.service.PostService;
import kr.co.rgrg.user.post.vo.ModifyPostVO;
import kr.co.rgrg.user.post.vo.PostVO;

@Controller
public class PostController {
	private static final Logger logger = LoggerFactory.getLogger(PostService.class);


	//json 데이터로 응답을 보내기 위한
	@Autowired
	MappingJackson2JsonView jsonView;
	
	/**
	 * 게시글 작성하기 - 임시저장된 글이 있다면 임시저장된 글 부터 불러오기
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/post/post_form.do", method= RequestMethod.GET)
	public String writePostForm(HttpSession session, Model model) {
		
		model.addAttribute("temp_post_flag", new PostService().searchPublishPost("user1"));
		return "post/edit";
	}
	
	@RequestMapping(value="/post/temp_post_form.do", method= RequestMethod.POST)
	public String getTempPostForm(Model model, HttpSession session) {
		
		PostService ps = new PostService();
		String post_num = ps.searchPublishPost("user1");
		PostDomain pdomain = ps.searchEditPost(post_num);
		System.out.println(post_num);
		model.addAttribute("post_data", pdomain);
		
		return "post/edit";
	}
	
	/**
	 * 출간하기 form
	 * @return
	 */
	@RequestMapping(value="/post/post_publish.do", method= RequestMethod.POST)
	public String publishPost() {
		
		return "post/edit_publish";
	}
	
	/**
	 * 게시글 최초 저장하기
	 * @param pVO
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/post/new_post.do", method= RequestMethod.POST)
	public String saveNewPost(PostVO pVO, HttpSession session, MultipartFile thumbnail_img, Model model) throws Exception {
		pVO.setId("user1");
		PostService ps = new PostService();
		
		String upload_result = "";
		boolean post_result = false;
		try {
			if(thumbnail_img != null) {
				upload_result = ps.saveFile(thumbnail_img);
				pVO.setThumbnail(upload_result);				
			}
			
			post_result = ps.savePost(pVO);
			System.out.println(post_result);
		} catch (NullPointerException e) {
			upload_result = "no_file";
		}
		
		if(post_result && upload_result != null) {
			model.addAttribute("posting_result", "success");
		} else {
			model.addAttribute("posting_result", "fail");
		}
		return "main/main";
	}
	

	/**
	 * 수정할 게시글 받아오기
	 * @param post_num
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/post/get_modify_post.do", method= RequestMethod.GET)
	public String getModifyPost(String post_num, Model model,HttpSession session) {
		
		PostService ps = new PostService();
		PostDomain pdomain = ps.searchEditPost(post_num);
		model.addAttribute("post_data", pdomain);
		
		return "post/edit";
	}
	
	/**
	 * 게시글 수정 저장하기
	 * @param mpVO
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/post/save_modify_post.do", method= RequestMethod.POST)
	public String saveModifyPost(ModifyPostVO mpVO, MultipartFile thumbnail_img, HttpSession session, Model model) {
		mpVO.setId("user1");
		PostService ps = new PostService();
		// result에 따라 어디로 이동할지
		
		String upload_result = "";
		boolean post_result = false;
		try {
			if(thumbnail_img != null) {
				upload_result = ps.saveFile(thumbnail_img);
				mpVO.setThumbnail(upload_result);				
			}
			
			post_result = ps.modifyPost(mpVO);
			System.out.println(post_result);
		} catch (NullPointerException e) {
			upload_result = "no_file";
		}
		
		if(post_result && upload_result != null) {
			model.addAttribute("posting_result", "success");
		} else {
			model.addAttribute("posting_result", "fail");
		}
		
		return "main/main";
	}
	
	@RequestMapping(value="/post/cancel.do", method= RequestMethod.GET)
	public String cancelPost(String post_num) {
		
		PostService ps = new PostService();
		
		return "post/cancel";
	}
}
